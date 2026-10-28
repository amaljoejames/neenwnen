/**
 * Question: Print duplicate characters in a string.
 * 
 * Topics Included:
 * - Hash map usage
 * - Frequency counting
 * - Character tracking
 * - Duplicate detection
 * 
 * Algorithm:
 * 1. Create a hash map to store character frequencies
 * 2. Traverse the string and count frequency of each character
 * 3. Traverse the map and print characters with frequency > 1
 * 4. These are the duplicate characters
 * 
 * Pseudocode:
 * FUNCTION printDuplicateCharacters(str):
 *     frequencyMap = new HashMap()
 *     
 *     FOR each character ch in str:
 *         IF frequencyMap.containsKey(ch):
 *             frequencyMap.put(ch, frequencyMap.get(ch) + 1)
 *         ELSE:
 *             frequencyMap.put(ch, 1)
 *     
 *     FOR each entry in frequencyMap:
 *         IF entry.value > 1:
 *             PRINT entry.key
 * 
 * Explanation with Example:
 * Input string: "programming"
 * Dry run:
 * - Count frequencies: p=1, r=2, o=1, g=2, a=1, m=2, i=1, n=1
 * - Check for duplicates:
 *   - p: 1 → not duplicate
 *   - r: 2 → duplicate, print 'r'
 *   - o: 1 → not duplicate
 *   - g: 2 → duplicate, print 'g'
 *   - a: 1 → not duplicate
 *   - m: 2 → duplicate, print 'm'
 *   - i: 1 → not duplicate
 *   - n: 1 → not duplicate
 * Output: r, g, m
 * 
 * Input string: "hello"
 * Dry run:
 * - Count frequencies: h=1, e=1, l=2, o=1
 * - Check for duplicates: l=2 → duplicate
 * Output: l
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(k) where k is the number of unique characters
 */

import java.util.HashMap;
import java.util.Map;

public class p21PrintDuplicates {
    
    /**
     * Method to print duplicate characters in a string
     * @param str The input string
     */
    public static void printDuplicateCharacters(String str) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        
        // Count frequencies
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (frequencyMap.containsKey(ch)) {
                frequencyMap.put(ch, frequencyMap.get(ch) + 1);
            } else {
                frequencyMap.put(ch, 1);
            }
        }
        
        // Print duplicate characters
        System.out.println("Duplicate characters in '" + str + "':");
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + " (appears " + entry.getValue() + " times)");
            }
        }
    }
    
    public static void main(String[] args) {
        // Question: Print duplicate characters in a string
        // Topics: Hash map usage, frequency counting, character tracking, duplicate detection
        // Algorithm: Count frequencies, print characters with count > 1
        // Pseudocode: count frequencies → traverse map → print if count > 1
        // Example: "programming" → r, g, m, "hello" → l
        // Dry run: "programming" → p:1,r:2,g:2,m:2 → duplicates: r,g,m
        // Time: O(n), Space: O(k)
        
        String str1 = "programming";
        String str2 = "hello";
        
        printDuplicateCharacters(str1);
        printDuplicateCharacters(str2);
    }
}
