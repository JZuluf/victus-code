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
// https://sonarsource.github.io/rspec/#/rspec/S100/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
const functionExitSelector = [
    ':matches(',
    ['FunctionExpression', 'ArrowFunctionExpression', 'FunctionDeclaration'].join(','),
    ')',
    ':exit',
].join('');
const functionExpressionProperty = [
    'Property',
    '[key.type="Identifier"]',
    ':matches(',
    ['[value.type="FunctionExpression"]', '[value.type="ArrowFunctionExpression"]'].join(','),
    ')',
].join('');
const functionExpressionVariable = [
    'VariableDeclarator',
    '[id.type="Identifier"]',
    ':matches(',
    ['[init.type="FunctionExpression"]', '[init.type="ArrowFunctionExpression"]'].join(','),
    ')',
].join('');
const DEFAULT_FORMAT = '^[_a-z][a-zA-Z0-9]*$';
const messages = {
    renameFunction: "Rename this '{{function}}' function to match the regular expression '{{format}}'.",
};
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, { messages, schema: meta_1.schema }),
    create(context) {
        const format = context.options[0]?.format ?? DEFAULT_FORMAT;
        const knowledgeStack = [];
        return {
            [functionExpressionProperty]: (node) => {
                knowledgeStack.push({
                    node: node.key,
                    func: node.value,
                    returnsJSX: returnsJSX(node.value),
                });
            },
            [functionExpressionVariable]: (node) => {
                knowledgeStack.push({
                    node: node.id,
                    func: node.init,
                    returnsJSX: returnsJSX(node.init),
                });
            },
            'MethodDefinition[key.type="Identifier"]': (node) => {
                knowledgeStack.push({
                    node: node.key,
                    func: node.value,
                    returnsJSX: false,
                });
            },
            'FunctionDeclaration[id.type="Identifier"]': (node) => {
                knowledgeStack.push({
                    node: node.id,
                    func: node,
                    returnsJSX: false,
                });
            },
            [functionExitSelector]: (func) => {
                if (func === (0, helpers_1.last)(knowledgeStack)?.func) {
                    const knowledge = knowledgeStack.pop();
                    if (knowledge && !knowledge.returnsJSX) {
                        const { node } = knowledge;
                        if (!node.name.match(format)) {
                            context.report({
                                messageId: 'renameFunction',
                                data: {
                                    function: node.name,
                                    format,
                                },
                                node,
                            });
                        }
                    }
                }
            },
            ReturnStatement: (node) => {
                const knowledge = (0, helpers_1.last)(knowledgeStack);
                const ancestors = context.sourceCode.getAncestors(node);
                for (let i = ancestors.length - 1; i >= 0; i--) {
                    if (helpers_1.functionLike.has(ancestors[i].type)) {
                        const enclosingFunction = ancestors[i];
                        if (knowledge &&
                            knowledge.func === enclosingFunction &&
                            node.argument &&
                            node.argument.type.startsWith('JSX')) {
                            knowledge.returnsJSX = true;
                        }
                        return;
                    }
                }
            },
        };
    },
};
//handling arrow functions without return statement
function returnsJSX(node) {
    return node.body.type.startsWith('JSX');
}
//# sourceMappingURL=rule.js.map