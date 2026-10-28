/**
 * Question: Print all subsequences of a string.
 * 
 * Topics Included:
 * - Backtracking
 * - Recursion
 * - Subset generation
 * - String manipulation
 * 
 * Algorithm:
 * 1. Use recursion to generate all subsequences
 * 2. For each character, we have two choices:
 *    - Include the character in the subsequence
 *    - Exclude the character from the subsequence
 * 3. Recursively process remaining characters
 * 4. Print subsequence when end of string is reached
 * 
 * Pseudocode:
 * FUNCTION printSubsequences(str, index, current):
 *     IF index == str.length():
 *         PRINT current
 *         RETURN
 *     
 *     // Include current character
 *     printSubsequences(str, index + 1, current + str.charAt(index))
 *     
 *     // Exclude current character
 *     printSubsequences(str, index + 1, current)
 * 
 * Explanation with Example:
 * Input string: "abc"
 * Dry run:
 * - index=0, current="":
 *   - Include 'a': index=1, current="a"
 *     - Include 'b': index=2, current="ab"
 *       - Include 'c': index=3, current="abc" → print "abc"
 *       - Exclude 'c': index=3, current="ab" → print "ab"
 *     - Exclude 'b': index=2, current="a"
 *       - Include 'c': index=3, current="ac" → print "ac"
 *       - Exclude 'c': index=3, current="a" → print "a"
 *   - Exclude 'a': index=1, current=""
 *     - Include 'b': index=2, current="b"
 *       - Include 'c': index=3, current="bc" → print "bc"
 *       - Exclude 'c': index=3, current="b" → print "b"
 *     - Exclude 'b': index=2, current=""
 *       - Include 'c': index=3, current="c" → print "c"
 *       - Exclude 'c': index=3, current="" → print ""
 * Output: abc, ab, ac, a, bc, b, c, "" (8 subsequences)
 * 
 * Time Complexity: O(2^n) where n is the length of the string
 * Space Complexity: O(n) for recursion stack
 */

import java.util.ArrayList;
import java.util.List;

public class p40PrintSubsequences {
    
    /**
     * Method to generate all subsequences of a string
     * @param str The input string
     * @return List of all subsequences
     */
    public static List<String> generateSubsequences(String str) {
        List<String> result = new ArrayList<>();
        generateSubsequencesHelper(str, 0, "", result);
        return result;
    }
    
    /**
     * Helper function to generate subsequences recursively
     */
    private static void generateSubsequencesHelper(String str, int index, String current, List<String> result) {
        if (index == str.length()) {
            result.add(current);
            return;
        }
        
        // Include current character
        generateSubsequencesHelper(str, index + 1, current + str.charAt(index), result);
        
        // Exclude current character
        generateSubsequencesHelper(str, index + 1, current, result);
    }
    
    public static void main(String[] args) {
        // Question: Print all subsequences of a string
        // Topics: Backtracking, recursion, subset generation, string manipulation
        // Algorithm: For each character, include or exclude, recurse, collect at end
        // Pseudocode: for each char → include → recurse → exclude → recurse → collect
        // Example: "abc" → ["abc","ab","ac","a","bc","b","c",""] (8 subsequences)
        // Dry run: "abc" → include/exclude each char → 2^3=8 subsequences
        // Time: O(2^n), Space: O(n)
        
        String str = "abc";
        List<String> subsequences = generateSubsequences(str);
        
        System.out.println("All subsequences of '" + str + "':");
        for (String subseq : subsequences) {
            if (subseq.isEmpty()) {
                System.out.println("(empty)");
            } else {
                System.out.println(subseq);
            }
        }
        System.out.println("Total: " + subsequences.size() + " subsequences");
    }
}
