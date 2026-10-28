/**
 * Question: Find all permutations of a string.
 * 
 * Topics Included:
 * - Backtracking
 * - Recursion
 * - Permutation generation
 * - String manipulation
 * 
 * Algorithm:
 * 1. Use backtracking to generate all permutations
 * 2. Fix one character at a position
 * 3. Recursively generate permutations for remaining characters
 * 4. Swap characters to get different arrangements
 * 5. Add permutation to result when all positions are fixed
 * 
 * Pseudocode:
 * FUNCTION permute(str, l, r, result):
 *     IF l == r:
 *         result.add(str)
 *         RETURN
 *     
 *     FOR i = l to r:
 *         swap(str[l], str[i])
 *         permute(str, l + 1, r, result)
 *         swap(str[l], str[i])  // backtrack
 * 
 * FUNCTION getAllPermutations(str):
 *     result = new ArrayList()
 *     charArray = str.toCharArray()
 *     permute(charArray, 0, charArray.length - 1, result)
 *     RETURN result
 * 
 * Explanation with Example:
 * Input string: "abc"
 * Dry run:
 * - Fix 'a' at position 0:
 *   - Permute "bc" → "bc", "cb"
 *   - Results: "abc", "acb"
 * - Fix 'b' at position 0:
 *   - Permute "ac" → "ac", "ca"
 *   - Results: "bac", "bca"
 * - Fix 'c' at position 0:
 *   - Permute "ab" → "ab", "ba"
 *   - Results: "cab", "cba"
 * Final result: ["abc", "acb", "bac", "bca", "cab", "cba"]
 * 
 * Input string: "ab"
 * Dry run:
 * - Fix 'a' at position 0:
 *   - Permute "b" → "b"
 *   - Result: "ab"
 * - Fix 'b' at position 0:
 *   - Permute "a" → "a"
 *   - Result: "ba"
 * Final result: ["ab", "ba"]
 * 
 * Time Complexity: O(n * n!) where n is the length of the string
 * Space Complexity: O(n!) for storing all permutations
 */

import java.util.ArrayList;
import java.util.List;

public class p39AllPermutations {
    
    /**
     * Helper function to swap characters
     */
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * Recursive function to generate permutations
     */
    private static void permute(char[] str, int l, int r, List<String> result) {
        if (l == r) {
            result.add(new String(str));
            return;
        }
        
        for (int i = l; i <= r; i++) {
            swap(str, l, i);
            permute(str, l + 1, r, result);
            swap(str, l, i);  // backtrack
        }
    }
    
    /**
     * Method to find all permutations of a string
     * @param str The input string
     * @return List of all permutations
     */
    public static List<String> getAllPermutations(String str) {
        List<String> result = new ArrayList<>();
        char[] charArray = str.toCharArray();
        permute(charArray, 0, charArray.length - 1, result);
        return result;
    }
    
    public static void main(String[] args) {
        // Question: Find all permutations of a string
        // Topics: Backtracking, recursion, permutation generation, string manipulation
        // Algorithm: Fix character at position, recursively permute remaining, backtrack
        // Pseudocode: for each position → fix char → recurse → backtrack → collect results
        // Example: "abc" → ["abc","acb","bac","bca","cab","cba"]
        // Dry run: "abc" → fix a → permute bc → abc,acb; fix b → bac,bca; fix c → cab,cba
        // Time: O(n*n!), Space: O(n!)
        
        String str1 = "abc";
        String str2 = "ab";
        
        List<String> perms1 = getAllPermutations(str1);
        List<String> perms2 = getAllPermutations(str2);
        
        System.out.println("Permutations of '" + str1 + "': " + perms1);
        System.out.println("Permutations of '" + str2 + "': " + perms2);
    }
}
