/**
 * Question: Find the first non-repeating character in a string.
 * 
 * Topics Included:
 * - Hash map usage
 * - Frequency counting
 * - String traversal
 * - Order preservation
 * 
 * Algorithm:
 * 1. Create a hash map to store character frequencies
 * 2. First pass: Traverse string and count frequency of each character
 * 3. Second pass: Traverse string again
 * 4. For each character, check if its frequency is 1
 * 5. Return the first character with frequency 1
 * 6. If no such character, return null or appropriate indicator
 * 
 * Pseudocode:
 * FUNCTION findFirstNonRepeating(str):
 *     frequencyMap = new HashMap()
 *     
 *     // First pass: count frequencies
 *     FOR each character ch in str:
 *         IF frequencyMap.containsKey(ch):
 *             frequencyMap.put(ch, frequencyMap.get(ch) + 1)
 *         ELSE:
 *             frequencyMap.put(ch, 1)
 *     
 *     // Second pass: find first non-repeating
 *     FOR each character ch in str:
 *         IF frequencyMap.get(ch) == 1:
 *             RETURN ch
 *     
 *     RETURN null
 * 
 * Explanation with Example:
 * Input string: "swiss"
 * First pass (count frequencies):
 * - s:3, w:1, i:1
 * Second pass (find first with count 1):
 * - 's': frequency = 3 → skip
 * - 'w': frequency = 1 → return 'w'
 * Final result: 'w'
 * 
 * Input string: "aabbcc"
 * First pass:
 * - a:2, b:2, c:2
 * Second pass:
 * - All characters have frequency > 1
 * Final result: null (no non-repeating character)
 * 
 * Input string: "leetcode"
 * First pass:
 * - l:1, e:3, t:1, c:1, o:1, d:1
 * Second pass:
 * - 'l': frequency = 1 → return 'l'
 * Final result: 'l'
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(k) where k is the number of unique characters
 */

import java.util.HashMap;
import java.util.Map;

public class p10FirstNonRepeating {
    
    /**
     * Method to find the first non-repeating character in a string
     * @param str The input string
     * @return The first non-repeating character, or null if none exists
     */
    public static Character findFirstNonRepeating(String str) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        
        // First pass: count frequencies
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (frequencyMap.containsKey(ch)) {
                frequencyMap.put(ch, frequencyMap.get(ch) + 1);
            } else {
                frequencyMap.put(ch, 1);
            }
        }
        
        // Second pass: find first non-repeating
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (frequencyMap.get(ch) == 1) {
                return ch;
            }
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        // Question: Find the first non-repeating character in a string
        // Topics: Hash map usage, frequency counting, string traversal, order preservation
        // Algorithm: Two-pass approach - count frequencies, then find first with count 1
        // Pseudocode: first pass count → second pass find first with count=1 → return or null
        // Example: "swiss" → 'w', "aabbcc" → null
        // Dry run: "swiss" → s:3,w:1,i:1 → first with count 1 is 'w'
        // Time: O(n), Space: O(k)
        
        String str1 = "swiss";
        String str2 = "aabbcc";
        String str3 = "leetcode";
        
        System.out.println("String: " + str1 + ", First non-repeating: " + findFirstNonRepeating(str1));
        System.out.println("String: " + str2 + ", First non-repeating: " + findFirstNonRepeating(str2));
        System.out.println("String: " + str3 + ", First non-repeating: " + findFirstNonRepeating(str3));
    }
}
