/**
 * Question: Remove all vowels from a string.
 * 
 * Topics Included:
 * - String manipulation
 * - Vowel detection
 * - Character filtering
 * - String building
 * 
 * Algorithm:
 * 1. Define vowels (a, e, i, o, u) both lowercase and uppercase
 * 2. Initialize empty result string
 * 3. Traverse each character in the input string
 * 4. Check if character is not a vowel
 * 5. If not a vowel, append to result
 * 6. Return the result string
 * 
 * Pseudocode:
 * FUNCTION removeVowels(str):
 *     vowels = "aeiouAEIOU"
 *     result = ""
 *     
 *     FOR each character ch in str:
 *         IF vowels.indexOf(ch) == -1:
 *             result += ch
 *     
 *     RETURN result
 * 
 * Explanation with Example:
 * Input string: "Hello World"
 * Dry run:
 * - 'H': not a vowel → result = "H"
 * - 'e': is a vowel → skip
 * - 'l': not a vowel → result = "Hl"
 * - 'l': not a vowel → result = "Hll"
 * - 'o': is a vowel → skip
 * - ' ': not a vowel → result = "Hll "
 * - 'W': not a vowel → result = "Hll W"
 * - 'o': is a vowel → skip
 * - 'r': not a vowel → result = "Hll Wr"
 * - 'l': not a vowel → result = "Hll Wrl"
 * - 'd': not a vowel → result = "Hll Wrld"
 * Final result: "Hll Wrld"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) - for result string
 */

public class p8RemoveVowels {
    
    /**
     * Method to remove all vowels from a string
     * @param str The input string
     * @return The string without vowels
     */
    public static String removeVowels(String str) {
        StringBuilder result = new StringBuilder();
        String vowels = "aeiouAEIOU";
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // Check if character is not a vowel
            if (vowels.indexOf(ch) == -1) {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        // Question: Remove all vowels from a string
        // Topics: String manipulation, vowel detection, character filtering, string building
        // Algorithm: Traverse string, skip vowels, build result
        // Pseudocode: define vowels → traverse → if not vowel, append → return result
        // Example: "Hello" → "Hll"
        // Dry run: H(not), e(skip), l(not), l(not), o(skip) → "Hll"
        // Time: O(n), Space: O(n)
        
        String input = "Hello World";
        String result = removeVowels(input);
        
        System.out.println("Original string: " + input);
        System.out.println("After removing vowels: " + result);
    }
}
