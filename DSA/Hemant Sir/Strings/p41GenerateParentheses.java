/**
 * Question: Generate all valid parentheses combinations for a given n.
 * 
 * Topics Included:
 * - Backtracking
 * - Recursion
 * - Parenthesis validation
 * - Combinatorics
 * 
 * Algorithm:
 * 1. Use backtracking to generate valid combinations
 * 2. Track count of open and close parentheses
 * 3. Add open parenthesis if count < n
 * 4. Add close parenthesis if close count < open count
 * 5. When length is 2*n, add to result
 * 
 * Pseudocode:
 * FUNCTION generateParentheses(n, openCount, closeCount, current, result):
 *     IF current.length() == 2 * n:
 *         result.add(current)
 *         RETURN
 *     
 *     IF openCount < n:
 *         generateParentheses(n, openCount + 1, closeCount, current + "(", result)
 *     
 *     IF closeCount < openCount:
 *         generateParentheses(n, openCount, closeCount + 1, current + ")", result)
 * 
 * Explanation with Example:
 * Input: n = 2
 * Dry run:
 * - Start: open=0, close=0, current=""
 * - Add '(': open=1, close=0, current="("
 *   - Add '(': open=2, close=0, current="(("
 *     - Cannot add more '(', add ')': open=2, close=1, current="(()"
 *       - Add ')': open=2, close=2, current="(())" → length=4 → add to result
 *   - Add ')': open=1, close=1, current="()"
 *     - Add '(': open=2, close=1, current="()("
 *       - Add ')': open=2, close=2, current="()()" → length=4 → add to result
 * Final result: ["(())", "()()"]
 * 
 * Input: n = 3
 * Dry run:
 * - Generates: "((()))", "(()())", "(())()", "()(())", "()()()"
 * Final result: 5 combinations
 * 
 * Time Complexity: O(4^n / sqrt(n)) - Catalan number C(n)
 * Space Complexity: O(n) for recursion stack
 */

import java.util.ArrayList;
import java.util.List;

public class p41GenerateParentheses {
    
    /**
     * Method to generate all valid parentheses combinations
     * @param n The number of pairs of parentheses
     * @return List of valid combinations
     */
    public static List<String> generateParentheses(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesesHelper(n, 0, 0, "", result);
        return result;
    }
    
    /**
     * Helper function to generate parentheses recursively
     */
    private static void generateParenthesesHelper(int n, int openCount, int closeCount, String current, List<String> result) {
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }
        
        // Add open parenthesis if we haven't used all
        if (openCount < n) {
            generateParenthesesHelper(n, openCount + 1, closeCount, current + "(", result);
        }
        
        // Add close parenthesis if it won't exceed open count
        if (closeCount < openCount) {
            generateParenthesesHelper(n, openCount, closeCount + 1, current + ")", result);
        }
    }
    
    public static void main(String[] args) {
        // Question: Generate all valid parentheses combinations for a given n
        // Topics: Backtracking, recursion, parenthesis validation, combinatorics
        // Algorithm: Track open/close counts, add '(' if < n, add ')' if < open, collect at 2n
        // Pseudocode: track counts → add '(' if < n → add ')' if < open → collect at 2n
        // Example: n=2 → ["(())","()()"], n=3 → ["((()))","(()())","(())()","()(())","()()()"]
        // Dry run: n=2 → (,(,),) → "(())"; (,), (,) → "()()"
        // Time: O(4^n/sqrt(n)), Space: O(n)
        
        int n1 = 2;
        int n2 = 3;
        
        List<String> result1 = generateParentheses(n1);
        List<String> result2 = generateParentheses(n2);
        
        System.out.println("Valid parentheses for n=" + n1 + ": " + result1);
        System.out.println("Valid parentheses for n=" + n2 + ": " + result2);
    }
}
