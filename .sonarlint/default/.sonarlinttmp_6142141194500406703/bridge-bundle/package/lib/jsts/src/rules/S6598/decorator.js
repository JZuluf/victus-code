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
// https://sonarsource.github.io/rspec/#/rspec/S6598/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.decorate = decorate;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
// Rewording one of the issue messages reported by the core implementation.
function decorate(rule) {
    return (0, helpers_1.interceptReport)({
        ...rule,
        meta: (0, helpers_1.generateMeta)(meta_1.meta, {
            ...rule.meta,
            messages: {
                ...rule.meta.messages,
                /* Map to a more friendly message */
                functionTypeOverCallableType: '{{ literalOrInterface }} has only a call signature, you should use a function type instead.',
            },
        }),
    }, (context, reportDescriptor) => {
        context.report({
            ...reportDescriptor,
        });
    });
}
//# sourceMappingURL=decorator.js.map