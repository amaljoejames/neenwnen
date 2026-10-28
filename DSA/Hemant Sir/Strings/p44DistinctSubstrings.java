/**
 * Question: Find the number of distinct substrings of a string.
 * 
 * Topics Included:
 * - Hash set usage
 * - Substring generation
 * - String uniqueness
 * - Nested loops
 * 
 * Algorithm:
 * 1. Use a hash set to store unique substrings
 * 2. Use nested loops to generate all possible substrings
 * 3. Outer loop for starting index
 * 4. Inner loop for ending index
 * 5. Add each substring to the set
 * 6. Return the size of the set (number of distinct substrings)
 * 
 * Pseudocode:
 * FUNCTION countDistinctSubstrings(str):
 *     IF str is null OR str.isEmpty():
 *         RETURN 0
 *     
 *     distinctSubstrings = new HashSet()
 *     n = str.length()
 *     
 *     FOR i = 0 to n - 1:
 *         FOR j = i to n - 1:
 *             substring = str.substring(i, j + 1)
 *             distinctSubstrings.add(substring)
 *     
 *     RETURN distinctSubstrings.size()
 * 
 * Explanation with Example:
 * Input string: "abc"
 * Dry run:
 * - i=0:
 *   - j=0: "a" → set = {"a"}
 *   - j=1: "ab" → set = {"a", "ab"}
 *   - j=2: "abc" → set = {"a", "ab", "abc"}
 * - i=1:
 *   - j=1: "b" → set = {"a", "ab", "abc", "b"}
 *   - j=2: "bc" → set = {"a", "ab", "abc", "b", "bc"}
 * - i=2:
 *   - j=2: "c" → set = {"a", "ab", "abc", "b", "bc", "c"}
 * Final result: 6 distinct substrings
 * 
 * Input string: "aaa"
 * Dry run:
 * - i=0:
 *   - j=0: "a" → set = {"a"}
 *   - j=1: "aa" → set = {"a", "aa"}
 *   - j=2: "aaa" → set = {"a", "aa", "aaa"}
 * - i=1:
 *   - j=1: "a" → already in set
 *   - j=2: "aa" → already in set
 * - i=2:
 *   - j=2: "a" → already in set
 * Final result: 3 distinct substrings
 * 
 * Input string: "ababa"
 * Dry run:
 * - All substrings: a, ab, aba, abab, ababa, b, ba, bab, baba, a, ab, aba, b, ba, a
 * - Unique: a, ab, aba, abab, ababa, b, ba, bab, baba
 * Final result: 9 distinct substrings
 * 
 * Time Complexity: O(n²) where n is the length of the string
 * Space Complexity: O(n²) for storing all distinct substrings
 */

import java.util.HashSet;
import java.util.Set;

public class p44DistinctSubstrings {
    
    /**
     * Method to find the number of distinct substrings of a string
     * @param str The input string
     * @return The number of distinct substrings
     */
    public static int countDistinctSubstrings(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        
        Set<String> distinctSubstrings = new HashSet<>();
        int n = str.length();
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String substring = str.substring(i, j + 1);
                distinctSubstrings.add(substring);
            }
        }
        
        return distinctSubstrings.size();
    }
    
    /**
     * Method to also return the distinct substrings
     * @param str The input string
     * @return Set of distinct substrings
     */
    public static Set<String> getDistinctSubstrings(String str) {
        if (str == null || str.isEmpty()) {
            return new HashSet<>();
        }
        
        Set<String> distinctSubstrings = new HashSet<>();
        int n = str.length();
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String substring = str.substring(i, j + 1);
                distinctSubstrings.add(substring);
            }
        }
        
        return distinctSubstrings;
    }
    
    public static void main(String[] args) {
        // Question: Find the number of distinct substrings of a string
        // Topics: Hash set usage, substring generation, string uniqueness, nested loops
        // Algorithm: Generate all substrings using nested loops, add to set, return size
        // Pseudocode: nested loops → generate substrings → add to set → return set size
        // Example: "abc" → 6, "aaa" → 3, "ababa" → 9
        // Dry run: "abc" → a,ab,abc,b,bc,c → 6 unique
        // Time: O(n²), Space: O(n²)
        
        String str1 = "abc";
        String str2 = "aaa";
        String str3 = "ababa";
        
        System.out.println("Distinct substrings in '" + str1 + "': " + countDistinctSubstrings(str1));
        System.out.println("Distinct substrings in '" + str2 + "': " + countDistinctSubstrings(str2));
        System.out.println("Distinct substrings in '" + str3 + "': " + countDistinctSubstrings(str3));
        
        // Show actual substrings for str1
        System.out.println("\nSubstrings of '" + str1 + "': " + getDistinctSubstrings(str1));
    }
}
