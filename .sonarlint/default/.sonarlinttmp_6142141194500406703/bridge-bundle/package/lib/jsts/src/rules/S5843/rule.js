"use strict";
/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011-2024 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
// https://sonarsource.github.io/rspec/#/rspec/S5843/javascript
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const regexpp = __importStar(require("@eslint-community/regexpp"));
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
const DEFAULT_THRESHOLD = 20;
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, { schema: meta_1.schema }, true),
    create(context) {
        const threshold = context.options[0]?.threshold ?? DEFAULT_THRESHOLD;
        const services = context.sourceCode.parserServices;
        const regexNodes = [];
        return {
            'Literal[regex]:exit': (node) => {
                regexNodes.push(node);
            },
            'NewExpression:exit': (node) => {
                if ((0, helpers_1.isRegExpConstructor)(node)) {
                    regexNodes.push(node);
                }
            },
            'CallExpression:exit': (node) => {
                const callExpr = node;
                if ((0, helpers_1.isRequiredParserServices)(services) && (0, helpers_1.isStringRegexMethodCall)(callExpr, services)) {
                    regexNodes.push(callExpr.arguments[0]);
                }
                else if ((0, helpers_1.isRegExpConstructor)(callExpr)) {
                    regexNodes.push(callExpr);
                }
            },
            'Program:exit': () => {
                regexNodes.forEach(regexNode => checkRegexComplexity(regexNode, threshold, context));
            },
        };
    },
};
function checkRegexComplexity(regexNode, threshold, context) {
    for (const regexParts of findRegexParts(regexNode, context)) {
        let complexity = 0;
        const secondaryLocations = [];
        for (const regexPart of regexParts) {
            const calculator = new ComplexityCalculator(regexPart, context);
            calculator.visit();
            calculator.components.forEach(component => {
                secondaryLocations.push((0, helpers_1.toSecondaryLocation)(component.location, component.message));
            });
            complexity += calculator.complexity;
        }
        if (complexity > threshold) {
            (0, helpers_1.report)(context, {
                message: `Simplify this regular expression to reduce its complexity from ${complexity} to the ${threshold} allowed.`,
                node: regexParts[0],
            }, secondaryLocations, complexity - threshold);
        }
    }
}
function findRegexParts(node, context) {
    const finder = new RegexPartFinder(context);
    finder.find(node);
    return finder.parts;
}
class RegexPartFinder {
    constructor(context) {
        this.context = context;
        this.parts = [];
        this.handledIdentifiers = [];
    }
    find(node) {
        if ((0, helpers_1.isRegExpConstructor)(node)) {
            this.find(node.arguments[0]);
        }
        else if ((0, helpers_1.isRegexLiteral)(node)) {
            this.parts.push([node]);
        }
        else if ((0, helpers_1.isStringLiteral)(node)) {
            this.parts.push([node]);
        }
        else if ((0, helpers_1.isStaticTemplateLiteral)(node)) {
            this.parts.push([node]);
        }
        else if ((0, helpers_1.isIdentifier)(node)) {
            if (!this.handledIdentifiers.includes(node)) {
                this.handledIdentifiers.push(node);
                const initializer = (0, helpers_1.getUniqueWriteUsage)(this.context, node.name, node);
                if (initializer) {
                    this.find(initializer);
                }
            }
        }
        else if ((0, helpers_1.isBinaryPlus)(node)) {
            const literals = [];
            this.findInStringConcatenation(node.left, literals);
            this.findInStringConcatenation(node.right, literals);
            if (literals.length > 0) {
                this.parts.push(literals);
            }
        }
    }
    findInStringConcatenation(node, literals) {
        if ((0, helpers_1.isStringLiteral)(node)) {
            literals.push(node);
        }
        else if ((0, helpers_1.isBinaryPlus)(node)) {
            this.findInStringConcatenation(node.left, literals);
            this.findInStringConcatenation(node.right, literals);
        }
        else {
            this.find(node);
        }
    }
}
class ComplexityCalculator {
    constructor(regexPart, context) {
        this.regexPart = regexPart;
        this.context = context;
        this.nesting = 1;
        this.complexity = 0;
        this.components = [];
        this.regexPartAST = (0, helpers_1.getParsedRegex)(regexPart, context);
    }
    visit() {
        if (!this.regexPartAST) {
            return;
        }
        regexpp.visitRegExpAST(this.regexPartAST, {
            onAssertionEnter: (node) => {
                /* lookaround */
                if (node.kind === 'lookahead' || node.kind === 'lookbehind') {
                    const [start, end] = (0, helpers_1.getRegexpRange)(this.regexPart, node);
                    this.increaseComplexity(this.nesting, node, [
                        0,
                        -(end - start - 1) + (node.kind === 'lookahead' ? '?='.length : '?<='.length),
                    ]);
                    this.nesting++;
                    this.onDisjunctionEnter(node);
                }
            },
            onAssertionLeave: (node) => {
                /* lookaround */
                if (node.kind === 'lookahead' || node.kind === 'lookbehind') {
                    this.onDisjunctionLeave(node);
                    this.nesting--;
                }
            },
            onBackreferenceEnter: (node) => {
                this.increaseComplexity(1, node);
            },
            onCapturingGroupEnter: (node) => {
                /* disjunction */
                this.onDisjunctionEnter(node);
            },
            onCapturingGroupLeave: (node) => {
                /* disjunction */
                this.onDisjunctionLeave(node);
            },
            onCharacterClassEnter: (node) => {
                /* character class */
                const [start, end] = (0, helpers_1.getRegexpRange)(this.regexPart, node);
                this.increaseComplexity(1, node, [0, -(end - start - 1)]);
                this.nesting++;
            },
            onCharacterClassLeave: (_node) => {
                /* character class */
                this.nesting--;
            },
            onGroupEnter: (node) => {
                /* disjunction */
                this.onDisjunctionEnter(node);
            },
            onGroupLeave: (node) => {
                /* disjunction */
                this.onDisjunctionLeave(node);
            },
            onPatternEnter: (node) => {
                /* disjunction */
                this.onDisjunctionEnter(node);
            },
            onPatternLeave: (node) => {
                /* disjunction */
                this.onDisjunctionLeave(node);
            },
            onQuantifierEnter: (node) => {
                /* repetition */
                const [start] = (0, helpers_1.getRegexpRange)(this.regexPart, node);
                const [, end] = (0, helpers_1.getRegexpRange)(this.regexPart, node.element);
                this.increaseComplexity(this.nesting, node, [end - start, 0]);
                this.nesting++;
            },
            onQuantifierLeave: (_node) => {
                /* repetition */
                this.nesting--;
            },
        });
    }
    increaseComplexity(increment, node, offset) {
        this.complexity += increment;
        let message = '+' + increment;
        if (increment > 1) {
            message += ` (incl ${increment - 1} for nesting)`;
        }
        const loc = (0, helpers_1.getRegexpLocation)(this.regexPart, node, this.context, offset);
        if (loc) {
            this.components.push({
                location: {
                    loc,
                },
                message,
            });
        }
    }
    onDisjunctionEnter(node) {
        if (node.alternatives.length > 1) {
            let { alternatives } = node;
            let increment = this.nesting;
            while (alternatives.length > 1) {
                const [start, end] = (0, helpers_1.getRegexpRange)(this.regexPart, alternatives[1]);
                this.increaseComplexity(increment, alternatives[1], [-1, -(end - start)]);
                increment = 1;
                alternatives = alternatives.slice(1);
            }
            this.nesting++;
        }
    }
    onDisjunctionLeave(node) {
        if (node.alternatives.length > 1) {
            this.nesting--;
        }
    }
}
//# sourceMappingURL=rule.js.map