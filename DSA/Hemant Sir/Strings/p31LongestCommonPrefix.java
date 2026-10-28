/**
 * Question: Find the longest common prefix among an array of strings.
 * 
 * Topics Included:
 * - String comparison
 * - Prefix matching
 * - Array traversal
 * - Horizontal scanning
 * 
 * Algorithm:
 * 1. If array is empty, return empty string
 * 2. Take first string as reference prefix
 * 3. Compare prefix with each string in array
 * 4. Reduce prefix length until it matches all strings
 * 5. Return final prefix
 * 
 * Pseudocode:
 * FUNCTION longestCommonPrefix(strs):
 *     IF strs is null OR strs.length == 0:
 *         RETURN ""
 *     
 *     prefix = strs[0]
 *     
 *     FOR i = 1 to strs.length - 1:
 *         WHILE strs[i].indexOf(prefix) != 0:
 *             prefix = prefix.substring(0, prefix.length() - 1)
 *             IF prefix.isEmpty():
 *                 RETURN ""
 *     
 *     RETURN prefix
 * 
 * Explanation with Example:
 * Input: ["flower", "flow", "flight"]
 * Dry run:
 * - prefix = "flower"
 * - Compare with "flow": "flow".indexOf("flower") = -1
 *   - prefix = "flowe", indexOf = -1
 *   - prefix = "flow", indexOf = 0 ✓
 * - Compare with "flight": "flight".indexOf("flow") = -1
 *   - prefix = "flo", indexOf = -1
 *   - prefix = "fl", indexOf = 0 ✓
 * Final result: "fl"
 * 
 * Input: ["dog", "racecar", "car"]
 * Dry run:
 * - prefix = "dog"
 * - Compare with "racecar": indexOf = -1
 *   - prefix = "do", indexOf = -1
 *   - prefix = "d", indexOf = -1
 *   - prefix = "", return ""
 * Final result: ""
 * 
 * Time Complexity: O(n*m) where n is number of strings, m is length of shortest string
 * Space Complexity: O(1)
 */

public class p31LongestCommonPrefix {
    
    /**
     * Method to find the longest common prefix among array of strings
     * @param strs The array of strings
     * @return The longest common prefix
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        String prefix = strs[0];
        
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        
        return prefix;
    }
    
    public static void main(String[] args) {
        // Question: Find the longest common prefix among an array of strings
        // Topics: String comparison, prefix matching, array traversal, horizontal scanning
        // Algorithm: Use first string as prefix, reduce until matches all strings
        // Pseudocode: prefix = first → compare with each → reduce until match → return
        // Example: ["flower","flow","flight"] → "fl", ["dog","racecar","car"] → ""
        // Dry run: ["flower","flow","flight"] → flower→flow→fl → "fl"
        // Time: O(n*m), Space: O(1)
        
        String[] strs1 = {"flower", "flow", "flight"};
        String[] strs2 = {"dog", "racecar", "car"};
        String[] strs3 = {"interspecies", "interstellar", "interstate"};
        
        System.out.println("LCP of [flower, flow, flight]: " + longestCommonPrefix(strs1));
        System.out.println("LCP of [dog, racecar, car]: " + longestCommonPrefix(strs2));
        System.out.println("LCP of [interspecies, interstellar, interstate]: " + longestCommonPrefix(strs3));
    }
}
