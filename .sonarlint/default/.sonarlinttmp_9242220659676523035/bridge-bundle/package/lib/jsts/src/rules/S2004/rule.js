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
// https://sonarsource.github.io/rspec/#/rspec/S2004/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
const DEFAULT_THRESHOLD = 4;
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, { schema: meta_1.schema }, true),
    create(context) {
        const max = context.options[0]?.threshold ?? DEFAULT_THRESHOLD;
        const nestedStack = [];
        return {
            ':function'(node) {
                const fn = node;
                nestedStack.push(fn);
                if (nestedStack.length === max + 1) {
                    const secondaries = nestedStack.slice(0, -1);
                    (0, helpers_1.report)(context, {
                        loc: (0, helpers_1.getMainFunctionTokenLocation)(fn, fn.parent, context),
                        message: `Refactor this code to not nest functions more than ${max} levels deep.`,
                    }, secondaries.map(n => (0, helpers_1.toSecondaryLocation)({
                        loc: (0, helpers_1.getMainFunctionTokenLocation)(n, n.parent, context),
                    }, 'Nesting +1')));
                }
            },
            ':function:exit'() {
                nestedStack.pop();
            },
        };
    },
};
//# sourceMappingURL=rule.js.map