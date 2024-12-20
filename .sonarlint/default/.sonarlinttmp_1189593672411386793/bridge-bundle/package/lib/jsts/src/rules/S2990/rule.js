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
// https://sonarsource.github.io/rspec/#/rspec/S2990/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, {
        hasSuggestions: true,
        messages: {
            removeThis: `Remove the use of "this".`,
            suggestRemoveThis: 'Remove "this"',
            suggestUseWindow: 'Replace "this" with "window" object',
        },
    }),
    create(context) {
        return {
            'MemberExpression[object.type="ThisExpression"]'(node) {
                const memberExpression = node;
                const scopeType = context.sourceCode.getScope(node).variableScope.type;
                const isInsideClass = context.sourceCode
                    .getAncestors(node)
                    .some(ancestor => ancestor.type === 'ClassDeclaration' || ancestor.type === 'ClassExpression');
                if ((scopeType === 'global' || scopeType === 'module') && !isInsideClass) {
                    const suggest = [];
                    if (!memberExpression.computed) {
                        const propertyText = context.sourceCode.getText(memberExpression.property);
                        suggest.push({
                            messageId: 'suggestRemoveThis',
                            fix: fixer => fixer.replaceText(node, propertyText),
                        }, {
                            messageId: 'suggestUseWindow',
                            fix: fixer => fixer.replaceText(memberExpression.object, 'window'),
                        });
                    }
                    context.report({
                        messageId: 'removeThis',
                        node: memberExpression.object,
                        suggest,
                    });
                }
            },
        };
    },
};
//# sourceMappingURL=rule.js.map