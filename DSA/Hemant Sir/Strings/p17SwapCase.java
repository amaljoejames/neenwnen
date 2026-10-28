/**
 * Question: Swap the case of each character (upper ↔ lower).
 * 
 * Topics Included:
 * - ASCII manipulation
 * - Case conversion
 * - String traversal
 * - Character classification
 * 
 * Algorithm:
 * 1. Traverse each character in the string
 * 2. Check if character is uppercase (65-90)
 * 3. If uppercase, convert to lowercase by adding 32
 * 4. Check if character is lowercase (97-122)
 * 5. If lowercase, convert to uppercase by subtracting 32
 * 6. Leave other characters unchanged
 * 7. Build result string with converted characters
 * 
 * Pseudocode:
 * FUNCTION swapCase(str):
 *     result = ""
 *     
 *     FOR each character ch in str:
 *         IF ch is uppercase (65 to 90):
 *             result += (char)(ch + 32)
 *         ELSE IF ch is lowercase (97 to 122):
 *             result += (char)(ch - 32)
 *         ELSE:
 *             result += ch
 *     
 *     RETURN result
 * 
 * Explanation with Example:
 * Input string: "Hello World"
 * Dry run:
 * - 'H': uppercase (72) → 72+32=104 → 'h'
 * - 'e': lowercase (101) → 101-32=69 → 'E'
 * - 'l': lowercase (108) → 108-32=76 → 'L'
 * - 'l': lowercase → 'L'
 * - 'o': lowercase (111) → 111-32=79 → 'O'
 * - ' ': unchanged → ' '
 * - 'W': uppercase (87) → 87+32=119 → 'w'
 * - 'o': lowercase → 'O'
 * - 'r': lowercase → 'R'
 * - 'l': lowercase → 'L'
 * - 'd': lowercase → 'D'
 * Final result: "hELLO wORLD"
 * 
 * Input string: "Java123"
 * Dry run:
 * - 'J': uppercase → 'j'
 * - 'a': lowercase → 'A'
 * - 'v': lowercase → 'V'
 * - 'a': lowercase → 'A'
 * - '1': unchanged → '1'
 * - '2': unchanged → '2'
 * - '3': unchanged → '3'
 * Final result: "jAVA123"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) - for result string
 */

public class p17SwapCase {
    
    /**
     * Method to swap the case of each character in a string
     * @param str The input string
     * @return The string with swapped case
     */
    public static String swapCase(String str) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (ch >= 65 && ch <= 90) {
                // Uppercase to lowercase
                result.append((char)(ch + 32));
            } else if (ch >= 97 && ch <= 122) {
                // Lowercase to uppercase
                result.append((char)(ch - 32));
            } else {
                // Leave unchanged
                result.append(ch);
            }
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        // Question: Swap the case of each character (upper ↔ lower)
        // Topics: ASCII manipulation, case conversion, string traversal, character classification
        // Algorithm: Check ASCII range, add/subtract 32 for case conversion
        // Pseudocode: traverse → if upper+32, if lower-32, else unchanged → return result
        // Example: "Hello" → "hELLO", "Java" → "jAVA"
        // Dry run: "Hello" → H→h, e→E, l→L, l→L, o→O → "hELLO"
        // Time: O(n), Space: O(n)
        
        String str1 = "Hello World";
        String str2 = "Java123";
        
        System.out.println("Original: " + str1 + ", Swapped: " + swapCase(str1));
        System.out.println("Original: " + str2 + ", Swapped: " + swapCase(str2));
    }
}
