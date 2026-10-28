/**
 * Question: Find the first repeating character in a string.
 * 
 * Topics Included:
 * - Hash set usage
 * - Character tracking
 * - String traversal
 * - Early termination
 * 
 * Algorithm:
 * 1. Create a hash set to store seen characters
 * 2. Traverse each character in the string
 * 3. For each character, check if it's already in the set
 * 4. If in set, return it as first repeating character
 * 5. If not in set, add to set
 * 6. If no repeating character found, return null
 * 
 * Pseudocode:
 * FUNCTION findFirstRepeating(str):
 *     seenSet = new HashSet()
 *     
 *     FOR each character ch in str:
 *         IF seenSet.contains(ch):
 *             RETURN ch
 *         ELSE:
 *             seenSet.add(ch)
 *     
 *     RETURN null
 * 
 * Explanation with Example:
 * Input string: "hello"
 * Dry run:
 * - 'h': not in set → set = {h}
 * - 'e': not in set → set = {h, e}
 * - 'l': not in set → set = {h, e, l}
 * - 'l': in set → return 'l'
 * Final result: 'l'
 * 
 * Input string: "abcde"
 * Dry run:
 * - 'a': not in set → set = {a}
 * - 'b': not in set → set = {a, b}
 * - 'c': not in set → set = {a, b, c}
 * - 'd': not in set → set = {a, b, c, d}
 * - 'e': not in set → set = {a, b, c, d, e}
 * Final result: null (no repeating character)
 * 
 * Input string: "swiss"
 * Dry run:
 * - 's': not in set → set = {s}
 * - 'w': not in set → set = {s, w}
 * - 'i': not in set → set = {s, w, i}
 * - 's': in set → return 's'
 * Final result: 's'
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(k) where k is the number of unique characters
 */

import java.util.HashSet;
import java.util.Set;

public class p11FirstRepeating {
    
    /**
     * Method to find the first repeating character in a string
     * @param str The input string
     * @return The first repeating character, or null if none exists
     */
    public static Character findFirstRepeating(String str) {
        Set<Character> seenSet = new HashSet<>();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (seenSet.contains(ch)) {
                return ch;
            } else {
                seenSet.add(ch);
            }
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        // Question: Find the first repeating character in a string
        // Topics: Hash set usage, character tracking, string traversal, early termination
        // Algorithm: Use hash set to track seen characters, return first character seen twice
        // Pseudocode: traverse string → if in set return → else add to set → return null
        // Example: "hello" → 'l', "abcde" → null
        // Dry run: "hello" → h(not), e(not), l(not), l(in set) → return 'l'
        // Time: O(n), Space: O(k)
        
        String str1 = "hello";
        String str2 = "abcde";
        String str3 = "swiss";
        
        System.out.println("String: " + str1 + ", First repeating: " + findFirstRepeating(str1));
        System.out.println("String: " + str2 + ", First repeating: " + findFirstRepeating(str2));
        System.out.println("String: " + str3 + ", First repeating: " + findFirstRepeating(str3));
    }
}
