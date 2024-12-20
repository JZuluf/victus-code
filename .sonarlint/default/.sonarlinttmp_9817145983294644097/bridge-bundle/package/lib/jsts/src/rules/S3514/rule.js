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
// https://sonarsource.github.io/rspec/#/rspec/S3514/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
const MAX_INDEX = 4;
const isAllowedIndex = (idx) => idx >= 0 && idx <= MAX_INDEX;
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, undefined, true),
    create(context) {
        function visitStatements(statements) {
            const declarationsByObject = new Map();
            for (const statement of statements) {
                if (statement.type === 'VariableDeclaration') {
                    visitDeclarations(declarationsByObject, statement.declarations);
                }
                else {
                    checkDeclarationsBlock(declarationsByObject);
                    declarationsByObject.clear();
                }
            }
            checkDeclarationsBlock(declarationsByObject);
        }
        function visitDeclarations(declarationsByObject, declarations) {
            for (const declaration of declarations) {
                const id = declaration.id;
                if (declaration.init && id.type === 'Identifier') {
                    const varName = id.name;
                    const expression = declaration.init;
                    if (expression.type !== 'MemberExpression') {
                        continue;
                    }
                    const property = expression.property;
                    if ((0, helpers_1.isIdentifier)(property, varName) ||
                        ((0, helpers_1.isNumberLiteral)(property) && isAllowedIndex(property.value))) {
                        addDeclaration(declarationsByObject, expression.object, declaration);
                    }
                }
            }
        }
        function addDeclaration(declarationsByObject, object, declaration) {
            const key = context.sourceCode.getText(object);
            const value = declarationsByObject.get(key);
            if (value) {
                value.push(declaration);
            }
            else {
                declarationsByObject.set(key, [declaration]);
            }
        }
        function checkDeclarationsBlock(declarationsByObject) {
            declarationsByObject.forEach((declarations, key) => {
                if (declarations.length > 1) {
                    const firstKind = getKind(declarations[0]);
                    const tail = declarations.slice(1);
                    if (tail.every(decl => getKind(decl) === firstKind)) {
                        (0, helpers_1.report)(context, {
                            node: declarations[0],
                            message: `Use destructuring syntax for these assignments from "${key}".`,
                        }, tail.map(node => (0, helpers_1.toSecondaryLocation)(node, 'Replace this assignment.')));
                    }
                }
            });
        }
        return {
            BlockStatement: (node) => {
                visitStatements(node.body);
            },
            SwitchCase: (node) => {
                visitStatements(node.consequent);
            },
            Program: (node) => {
                visitStatements(node.body);
            },
        };
    },
};
function getKind(declarator) {
    const declaration = (0, helpers_1.findFirstMatchingAncestor)(declarator, n => n.type === 'VariableDeclaration');
    return declaration?.kind;
}
//# sourceMappingURL=rule.js.map