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
// https://sonarsource.github.io/rspec/#/rspec/S5863/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, undefined, true),
    create(context) {
        if (!helpers_1.Chai.isImported(context)) {
            return {};
        }
        return {
            ExpressionStatement(node) {
                const { expression } = node;
                checkExpect(context, expression);
                checkShould(context, expression);
                checkAssert(context, expression);
            },
        };
    },
};
function checkAssert(context, expression) {
    if (expression.type === 'CallExpression') {
        const { callee, arguments: args } = expression;
        if (callee.type === 'MemberExpression' && (0, helpers_1.isIdentifier)(callee.object, 'assert')) {
            findDuplicates(context, args);
        }
    }
}
function checkExpect(context, expression) {
    let currentExpression = expression;
    let args = [];
    while (true) {
        if (currentExpression.type === 'CallExpression') {
            args = [...currentExpression.arguments, ...args];
            currentExpression = currentExpression.callee;
        }
        else if (currentExpression.type === 'MemberExpression') {
            currentExpression = currentExpression.object;
        }
        else if ((0, helpers_1.isIdentifier)(currentExpression, 'expect')) {
            break;
        }
        else {
            return;
        }
    }
    findDuplicates(context, args);
}
function checkShould(context, expression) {
    let currentExpression = expression;
    let args = [];
    let hasShould = false;
    while (true) {
        if (currentExpression.type === 'CallExpression') {
            args = [...currentExpression.arguments, ...args];
            currentExpression = currentExpression.callee;
        }
        else if (currentExpression.type === 'MemberExpression') {
            if ((0, helpers_1.isIdentifier)(currentExpression.property, 'should')) {
                hasShould = true;
            }
            currentExpression = currentExpression.object;
        }
        else if ((0, helpers_1.isIdentifier)(currentExpression, 'should')) {
            break;
        }
        else if (hasShould) {
            args = [currentExpression, ...args];
            break;
        }
        else {
            return;
        }
    }
    findDuplicates(context, args);
}
function findDuplicates(context, args) {
    for (let i = 0; i < args.length; i++) {
        for (let j = i + 1; j < args.length; j++) {
            const duplicates = (0, helpers_1.areEquivalent)(args[i], args[j], context.sourceCode);
            if (duplicates && !(0, helpers_1.isLiteral)(args[i])) {
                (0, helpers_1.report)(context, { message: `Replace this argument or its duplicate.`, node: args[i] }, [
                    (0, helpers_1.toSecondaryLocation)(args[j]),
                ]);
            }
        }
    }
}
//# sourceMappingURL=rule.js.map