/**
 * Question: Remove duplicate characters from a string.
 * 
 * Topics Included:
 * - Hash set usage
 * - Character deduplication
 * - String building
 * - Order preservation
 * 
 * Algorithm:
 * 1. Create a hash set to track seen characters
 * 2. Initialize empty result string
 * 3. Traverse each character in the input string
 * 4. Check if character is already in the set
 * 5. If not in set, add to result and add to set
 * 6. If in set, skip the character
 * 7. Return the result string
 * 
 * Pseudocode:
 * FUNCTION removeDuplicates(str):
 *     seenSet = new HashSet()
 *     result = ""
 *     
 *     FOR each character ch in str:
 *         IF seenSet does NOT contain ch:
 *             result += ch
 *             seenSet.add(ch)
 *     
 *     RETURN result
 * 
 * Explanation with Example:
 * Input string: "programming"
 * Dry run:
 * - 'p': not in set → result = "p", set = {p}
 * - 'r': not in set → result = "pr", set = {p, r}
 * - 'o': not in set → result = "pro", set = {p, r, o}
 * - 'g': not in set → result = "prog", set = {p, r, o, g}
 * - 'r': in set → skip
 * - 'a': not in set → result = "proga", set = {p, r, o, g, a}
 * - 'm': not in set → result = "progam", set = {p, r, o, g, a, m}
 * - 'm': in set → skip
 * - 'i': not in set → result = "progami", set = {p, r, o, g, a, m, i}
 * - 'n': not in set → result = "progain", set = {p, r, o, g, a, m, i, n}
 * - 'g': in set → skip
 * Final result: "progain"
 * 
 * Input string: "aabbccdd"
 * Dry run:
 * - 'a': not in set → result = "a", set = {a}
 * - 'a': in set → skip
 * - 'b': not in set → result = "ab", set = {a, b}
 * - 'b': in set → skip
 * - 'c': not in set → result = "abc", set = {a, b, c}
 * - 'c': in set → skip
 * - 'd': not in set → result = "abcd", set = {a, b, c, d}
 * - 'd': in set → skip
 * Final result: "abcd"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(k) where k is the number of unique characters
 */

import java.util.HashSet;
import java.util.Set;

public class p14RemoveDuplicates {
    
    /**
     * Method to remove duplicate characters from a string
     * @param str The input string
     * @return The string with duplicates removed
     */
    public static String removeDuplicates(String str) {
        Set<Character> seenSet = new HashSet<>();
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (!seenSet.contains(ch)) {
                result.append(ch);
                seenSet.add(ch);
            }
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        // Question: Remove duplicate characters from a string
        // Topics: Hash set usage, character deduplication, string building, order preservation
        // Algorithm: Use hash set to track seen characters, skip duplicates
        // Pseudocode: traverse string → if not seen, add to result and set → if seen, skip
        // Example: "programming" → "progain", "aabbccdd" → "abcd"
        // Dry run: "programming" → p,r,o,g,r(skip),a,m,m(skip),i,n,g(skip) → "progain"
        // Time: O(n), Space: O(k)
        
        String str1 = "programming";
        String str2 = "aabbccdd";
        
        System.out.println("Original: " + str1 + ", After removing duplicates: " + removeDuplicates(str1));
        System.out.println("Original: " + str2 + ", After removing duplicates: " + removeDuplicates(str2));
    }
}
