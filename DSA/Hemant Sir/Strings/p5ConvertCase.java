/**
 * Question: Convert a string to uppercase and lowercase without using built-in methods.
 * 
 * Topics Included:
 * - ASCII manipulation
 * - Character case conversion
 * - String traversal
 * - ASCII arithmetic
 * 
 * Algorithm:
 * 1. For uppercase: Convert lowercase letters (97-122) to uppercase (65-90) by subtracting 32
 * 2. For lowercase: Convert uppercase letters (65-90) to lowercase (97-122) by adding 32
 * 3. Leave non-alphabetic characters unchanged
 * 4. Traverse each character and apply conversion
 * 5. Build new string with converted characters
 * 
 * Pseudocode:
 * FUNCTION toUpperCase(str):
 *     result = ""
 *     FOR each character ch in str:
 *         IF ch is lowercase (97 to 122):
 *             result += (char)(ch - 32)
 *         ELSE:
 *             result += ch
 *     RETURN result
 * 
 * FUNCTION toLowerCase(str):
 *     result = ""
 *     FOR each character ch in str:
 *         IF ch is uppercase (65 to 90):
 *             result += (char)(ch + 32)
 *         ELSE:
 *             result += ch
 *     RETURN result
 * 
 * Explanation with Example:
 * Input string: "Hello World"
 * To Uppercase:
 * - 'H': already uppercase → 'H'
 * - 'e': lowercase (101) → 101-32=69 → 'E'
 * - 'l': lowercase (108) → 108-32=76 → 'L'
 * - 'l': lowercase → 'L'
 * - 'o': lowercase (111) → 111-32=79 → 'O'
 * - ' ': unchanged → ' '
 * - 'W': uppercase → 'W'
 * - 'o': lowercase → 'O'
 * - 'r': lowercase → 'R'
 * - 'l': lowercase → 'L'
 * - 'd': lowercase → 'D'
 * Final result: "HELLO WORLD"
 * 
 * To Lowercase:
 * - 'H': uppercase (72) → 72+32=104 → 'h'
 * - 'e': lowercase → 'e'
 * - 'l': lowercase → 'l'
 * - 'l': lowercase → 'l'
 * - 'o': lowercase → 'o'
 * - ' ': unchanged → ' '
 * - 'W': uppercase (87) → 87+32=119 → 'w'
 * - 'o': lowercase → 'o'
 * - 'r': lowercase → 'r'
 * - 'l': lowercase → 'l'
 * - 'd': lowercase → 'd'
 * Final result: "hello world"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) - for result string
 */

public class p5ConvertCase {
    
    /**
     * Method to convert string to uppercase without built-in methods
     * @param str The input string
     * @return The uppercase string
     */
    public static String toUpperCase(String str) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // If lowercase (97-122), convert to uppercase by subtracting 32
            if (ch >= 97 && ch <= 122) {
                result.append((char)(ch - 32));
            } else {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Method to convert string to lowercase without built-in methods
     * @param str The input string
     * @return The lowercase string
     */
    public static String toLowerCase(String str) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // If uppercase (65-90), convert to lowercase by adding 32
            if (ch >= 65 && ch <= 90) {
                result.append((char)(ch + 32));
            } else {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        // Question: Convert a string to uppercase and lowercase without using built-in methods
        // Topics: ASCII manipulation, character case conversion, string traversal, ASCII arithmetic
        // Algorithm: Use ASCII values - subtract 32 for uppercase, add 32 for lowercase
        // Pseudocode: traverse string → check ASCII range → add/subtract 32 → build result
        // Example: "Hello" → "HELLO" (upper), "hello" (lower)
        // Dry run: 'h'(104)+32='H', 'e'(101)+32='E' → "HELLO"
        // Time: O(n), Space: O(n)
        
        String input = "Hello World";
        String upper = toUpperCase(input);
        String lower = toLowerCase(input);
        
        System.out.println("Original string: " + input);
        System.out.println("Uppercase: " + upper);
        System.out.println("Lowercase: " + lower);
    }
}
