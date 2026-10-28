/**
 * Question: Count the frequency of each character in a string.
 * 
 * Topics Included:
 * - Hash map usage
 * - Character counting
 * - String traversal
 * - Frequency calculation
 * 
 * Algorithm:
 * 1. Create a hash map to store character frequencies
 * 2. Traverse each character in the string
 * 3. For each character, increment its count in the map
 * 4. If character not in map, initialize with 1
 * 5. Return the map with frequencies
 * 
 * Pseudocode:
 * FUNCTION countCharacterFrequency(str):
 *     frequencyMap = new HashMap()
 *     
 *     FOR each character ch in str:
 *         IF frequencyMap.containsKey(ch):
 *             frequencyMap.put(ch, frequencyMap.get(ch) + 1)
 *         ELSE:
 *             frequencyMap.put(ch, 1)
 *     
 *     RETURN frequencyMap
 * 
 * Explanation with Example:
 * Input string: "hello"
 * Dry run:
 * - 'h': not in map → map = {h:1}
 * - 'e': not in map → map = {h:1, e:1}
 * - 'l': not in map → map = {h:1, e:1, l:1}
 * - 'l': in map → map = {h:1, e:1, l:2}
 * - 'o': not in map → map = {h:1, e:1, l:2, o:1}
 * Final result: {h=1, e=1, l=2, o=1}
 * 
 * Input string: "programming"
 * Dry run:
 * - p:1, r:1, o:1, g:1, r:2, a:1, m:1, m:2, i:1, n:1, g:2
 * Final result: {p=1, r=2, o=1, g=2, a=1, m=2, i=1, n=1}
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(k) where k is the number of unique characters
 */

import java.util.HashMap;
import java.util.Map;

public class p9CharacterFrequency {
    
    /**
     * Method to count the frequency of each character in a string
     * @param str The input string
     * @return Map with character frequencies
     */
    public static Map<Character, Integer> countCharacterFrequency(String str) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (frequencyMap.containsKey(ch)) {
                frequencyMap.put(ch, frequencyMap.get(ch) + 1);
            } else {
                frequencyMap.put(ch, 1);
            }
        }
        
        return frequencyMap;
    }
    
    public static void main(String[] args) {
        // Question: Count the frequency of each character in a string
        // Topics: Hash map usage, character counting, string traversal, frequency calculation
        // Algorithm: Use hash map to count occurrences of each character
        // Pseudocode: create map → traverse string → increment count in map → return map
        // Example: "hello" → {h=1, e=1, l=2, o=1}
        // Dry run: h:1, e:1, l:1, l:2, o:1 → {h=1, e=1, l=2, o=1}
        // Time: O(n), Space: O(k)
        
        String input = "hello";
        Map<Character, Integer> frequency = countCharacterFrequency(input);
        
        System.out.println("String: " + input);
        System.out.println("Character frequencies:");
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        }
    }
}
