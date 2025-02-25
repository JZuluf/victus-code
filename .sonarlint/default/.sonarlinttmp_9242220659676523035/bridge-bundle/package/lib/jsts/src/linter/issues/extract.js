"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.extractHighlightedSymbols = extractHighlightedSymbols;
exports.extractCognitiveComplexity = extractCognitiveComplexity;
const cognitive_complexity_1 = require("../custom-rules/cognitive-complexity");
const symbol_highlighting_1 = require("../custom-rules/symbol-highlighting");
/**
 * Extracts the symbol highlighting
 *
 * The linter enables the internal custom rule for symbol highlighting
 * which eventually creates an issue to this end. The issue encodes the
 * symbol highlighting as a serialized JSON object in its message, which
 * can safely be extracted if it exists in the list of returned issues
 * after linting.
 *
 * @param issues the issues to process
 * @returns the symbol highlighting
 */
function extractHighlightedSymbols(issues) {
    const issue = findAndRemoveFirstIssue(issues, symbol_highlighting_1.rule.ruleId);
    if (issue) {
        return JSON.parse(issue.message);
    }
    return [];
}
/**
 * Extracts the cognitive complexity
 *
 * The linter enables the internal custom rule for cognitive complexity
 * which eventually creates an issue to this end. The issue encodes the
 * complexity as a number in its message, which can safely be extracted
 * if it exists in the list of returned issues after linting.
 *
 * @param issues the issues to process
 * @returns the cognitive complexity
 */
function extractCognitiveComplexity(issues) {
    const issue = findAndRemoveFirstIssue(issues, cognitive_complexity_1.rule.ruleId);
    if (issue && !isNaN(Number(issue.message))) {
        return Number(issue.message);
    }
    return undefined;
}
/**
 * Finds the first issue matching a rule id
 *
 * The functions removes the issue from the list if it exists.
 *
 * @param issues the issues to process
 * @param ruleId the rule id that is looked for
 * @returns the found issue, if any
 */
function findAndRemoveFirstIssue(issues, ruleId) {
    for (const issue of issues) {
        if (issue.ruleId === ruleId) {
            const index = issues.indexOf(issue);
            issues.splice(index, 1);
            return issue;
        }
    }
    return undefined;
}
//# sourceMappingURL=extract.js.map