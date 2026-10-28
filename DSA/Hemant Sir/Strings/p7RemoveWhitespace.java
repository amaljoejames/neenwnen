/**
 * Question: Remove all whitespace characters from a string.
 * 
 * Topics Included:
 * - String manipulation
 * - Whitespace detection
 * - String building
 * - Character filtering
 * 
 * Algorithm:
 * 1. Initialize empty result string
 * 2. Traverse each character in the input string
 * 3. Check if character is not a whitespace
 * 4. If not whitespace, append to result
 * 5. Return the result string
 * 
 * Pseudocode:
 * FUNCTION removeWhitespace(str):
 *     result = ""
 *     FOR each character ch in str:
 *         IF ch is not whitespace:
 *             result += ch
 *     RETURN result
 * 
 * Explanation with Example:
 * Input string: "Hello World"
 * Dry run:
 * - 'H': not whitespace → result = "H"
 * - 'e': not whitespace → result = "He"
 * - 'l': not whitespace → result = "Hel"
 * - 'l': not whitespace → result = "Hell"
 * - 'o': not whitespace → result = "Hello"
 * - ' ': whitespace → skip
 * - 'W': not whitespace → result = "HelloW"
 * - 'o': not whitespace → result = "HelloWo"
 * - 'r': not whitespace → result = "HelloWor"
 * - 'l': not whitespace → result = "HelloWorl"
 * - 'd': not whitespace → result = "HelloWorld"
 * Final result: "HelloWorld"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) - for result string
 */

public class p7RemoveWhitespace {
    
    /**
     * Method to remove all whitespace characters from a string
     * @param str The input string
     * @return The string without whitespace
     */
    public static String removeWhitespace(String str) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // Check if character is not a whitespace
            if (!Character.isWhitespace(ch)) {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        // Question: Remove all whitespace characters from a string
        // Topics: String manipulation, whitespace detection, string building, character filtering
        // Algorithm: Traverse string, skip whitespace characters, build result
        // Pseudocode: traverse string → if not whitespace, append → return result
        // Example: "Hello World" → "HelloWorld"
        // Dry run: H,e,l,l,o,skip,W,o,r,l,d → "HelloWorld"
        // Time: O(n), Space: O(n)
        
        String input = "Hello World Java";
        String result = removeWhitespace(input);
        
        System.out.println("Original string: " + input);
        System.out.println("After removing whitespace: " + result);
    }
}
