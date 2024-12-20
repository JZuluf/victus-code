"use strict";
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
var __exportStar = (this && this.__exportStar) || function(m, exports) {
    for (var p in m) if (p !== "default" && !Object.prototype.hasOwnProperty.call(exports, p)) __createBinding(exports, m, p);
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.SONAR_RUNTIME = void 0;
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
__exportStar(require("./accessibility"), exports);
__exportStar(require("./ancestor"), exports);
__exportStar(require("./ast"), exports);
__exportStar(require("./aws"), exports);
__exportStar(require("./chai"), exports);
__exportStar(require("./collection"), exports);
__exportStar(require("./conditions"), exports);
__exportStar(require("./decorators"), exports);
__exportStar(require("./equivalence"), exports);
__exportStar(require("./express"), exports);
__exportStar(require("./files"), exports);
__exportStar(require("./find-files"), exports);
__exportStar(require("./generate-meta"), exports);
__exportStar(require("./globals"), exports);
__exportStar(require("./jsx"), exports);
__exportStar(require("./location"), exports);
__exportStar(require("./lva"), exports);
__exportStar(require("./mocha"), exports);
__exportStar(require("./module"), exports);
__exportStar(require("./package-json"), exports);
__exportStar(require("./quickfix"), exports);
__exportStar(require("./reaching-definitions"), exports);
__exportStar(require("./regex"), exports);
__exportStar(require("./rule-detect-react"), exports);
__exportStar(require("./sinon"), exports);
__exportStar(require("./type"), exports);
__exportStar(require("./validate-version"), exports);
__exportStar(require("./vitest"), exports);
__exportStar(require("./vue"), exports);
__exportStar(require("./parser-services"), exports);
exports.SONAR_RUNTIME = 'sonar-runtime';
//# sourceMappingURL=index.js.map