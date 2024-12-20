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
// https://sonarsource.github.io/rspec/#/rspec/S6441/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const eslint_plugin_react_1 = require("eslint-plugin-react");
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
const noUnusedClassComponentMethod = eslint_plugin_react_1.rules['no-unused-class-component-methods'];
function overrideContext(context, overrides) {
    Object.setPrototypeOf(overrides, context);
    return overrides;
}
exports.rule = {
    meta: (0, helpers_1.generateMeta)(meta_1.meta, {
        ...noUnusedClassComponentMethod.meta,
        messages: {
            ...noUnusedClassComponentMethod.meta.messages,
            unused: 'Remove this property or method or refactor this component, as "{{name}}" is not used inside component body',
            unusedWithClass: 'Remove this property or method or refactor "{{className}}", as "{{name}}" is not used inside component body',
        },
    }),
    create(context) {
        let isReact = false;
        const detectReactListener = helpers_1.detectReactRule.create(overrideContext(context, {
            report(_descriptor) {
                isReact = true;
            },
        }));
        const noUnusedClassComponentMethodListener = noUnusedClassComponentMethod.create(overrideContext(context, {
            report(descriptor) {
                if (isReact) {
                    context.report(descriptor);
                }
            },
        }));
        return (0, helpers_1.mergeRules)(detectReactListener, noUnusedClassComponentMethodListener);
    },
};
//# sourceMappingURL=rule.js.map