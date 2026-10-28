/**
 * Question: Check whether a string is a pangram (contains all 26 letters).
 * 
 * Topics Included:
 * - Set usage
 * - Character tracking
 * - Alphabet verification
 * - Complete check
 * 
 * Algorithm:
 * 1. Create a set to store unique characters
 * 2. Traverse the string and add each letter to the set
 * 3. Convert all characters to lowercase for case-insensitive check
 * 4. Check if set contains all 26 letters of alphabet
 * 5. Return true if all present, false otherwise
 * 
 * Pseudocode:
 * FUNCTION isPangram(str):
 *     charSet = new HashSet()
 *     
 *     FOR each character ch in str:
 *         IF ch is a letter:
 *             charSet.add(lowercase(ch))
 *     
 *     IF charSet.size() == 26:
 *         RETURN true
 *     ELSE:
 *         RETURN false
 * 
 * Explanation with Example:
 * Input string: "The quick brown fox jumps over the lazy dog"
 * Dry run:
 * - Add all unique letters to set: t, h, e, q, u, i, c, k, b, r, o, w, n, f, x, j, m, p, s, v, l, a, z, y, d, g
 * - Set size = 26 (all letters present)
 * Final result: true (pangram)
 * 
 * Input string: "Hello World"
 * Dry run:
 * - Add unique letters: h, e, l, o, w, r, d
 * - Set size = 7 (not all 26 letters)
 * Final result: false (not a pangram)
 * 
 * Input string: "Pack my box with five dozen liquor jugs"
 * Dry run:
 * - Add unique letters: p, a, c, k, m, y, b, o, x, w, i, h, f, v, e, d, z, n, l, q, u, j, r, g, s
 * - Set size = 25 (missing 't')
 * Final result: false (not a pangram)
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1) - fixed size for 26 letters
 */

import java.util.HashSet;
import java.util.Set;

public class p25CheckPangram {
    
    /**
     * Method to check if a string is a pangram
     * @param str The input string
     * @return true if pangram, false otherwise
     */
    public static boolean isPangram(String str) {
        Set<Character> charSet = new HashSet<>();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // Check if character is a letter
            if (Character.isLetter(ch)) {
                // Add lowercase version to set
                charSet.add(Character.toLowerCase(ch));
            }
        }
        
        // Check if all 26 letters are present
        return charSet.size() == 26;
    }
    
    public static void main(String[] args) {
        // Question: Check whether a string is a pangram (contains all 26 letters)
        // Topics: Set usage, character tracking, alphabet verification, complete check
        // Algorithm: Track unique letters, check if count equals 26
        // Pseudocode: add letters to set → check if set size = 26 → return true/false
        // Example: "The quick brown fox jumps over the lazy dog" → true, "Hello" → false
        // Dry run: "The quick brown fox jumps over the lazy dog" → all 26 letters → true
        // Time: O(n), Space: O(1)
        
        String str1 = "The quick brown fox jumps over the lazy dog";
        String str2 = "Hello World";
        String str3 = "Pack my box with five dozen liquor jugs";
        
        System.out.println("'" + str1 + "' is a pangram: " + isPangram(str1));
        System.out.println("'" + str2 + "' is a pangram: " + isPangram(str2));
        System.out.println("'" + str3 + "' is a pangram: " + isPangram(str3));
    }
}
