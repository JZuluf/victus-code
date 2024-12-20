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
// https://sonarsource.github.io/rspec/#/rspec/S4787/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("../helpers");
const meta_1 = require("./meta");
const getEncryptionRuleModule = (clientSideMethods, serverSideMethods) => ({
    meta: (0, helpers_1.generateMeta)(meta_1.meta, {
        messages: {
            safeEncryption: 'Make sure that encrypting data is safe here.',
        },
    }),
    create(context) {
        // for client side
        let usingCryptoInFile = false;
        return {
            Program() {
                // init flag for each file
                usingCryptoInFile = false;
            },
            MemberExpression(node) {
                // detect 'SubtleCrypto' object
                // which can be retrieved by 'crypto.subtle' or 'window.crypto.subtle'
                const { object, property } = node;
                if ((0, helpers_1.isIdentifier)(property, 'subtle') &&
                    ((0, helpers_1.isIdentifier)(object, 'crypto') || (0, helpers_1.isMemberWithProperty)(object, 'crypto'))) {
                    usingCryptoInFile = true;
                }
            },
            'CallExpression:exit'(node) {
                const { callee } = node;
                if (usingCryptoInFile) {
                    // e.g.: crypto.subtle.encrypt()
                    checkForClientSide(callee, context, clientSideMethods);
                }
                // e.g.
                // const crypto = require("crypto");
                // const cipher = crypto.createCipher(alg, key);
                checkForServerSide(callee, context, serverSideMethods);
            },
        };
    },
});
function checkForServerSide(callee, context, serverSideMethods) {
    const fqn = (0, helpers_1.getFullyQualifiedName)(context, callee);
    if (serverSideMethods.some(method => fqn === `crypto.${method}`)) {
        context.report({
            messageId: 'safeEncryption',
            node: callee,
        });
    }
}
function checkForClientSide(callee, context, clientSideMethods) {
    if ((0, helpers_1.isIdentifier)(callee, ...clientSideMethods) ||
        (0, helpers_1.isMemberWithProperty)(callee, ...clientSideMethods)) {
        context.report({
            messageId: 'safeEncryption',
            node: callee,
        });
    }
}
const clientSideEncryptMethods = ['encrypt', 'decrypt'];
const serverSideEncryptMethods = [
    'createCipher',
    'createCipheriv',
    'createDecipher',
    'createDecipheriv',
    'publicEncrypt',
    'publicDecrypt',
    'privateEncrypt',
    'privateDecrypt',
];
exports.rule = getEncryptionRuleModule(clientSideEncryptMethods, serverSideEncryptMethods);
//# sourceMappingURL=rule.js.map