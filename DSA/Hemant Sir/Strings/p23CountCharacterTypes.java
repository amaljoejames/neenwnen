/**
 * Question: Count uppercase, lowercase, digits, and special characters in a string.
 * 
 * Topics Included:
 * - Character classification
 * - ASCII range checking
 * - Multiple counter tracking
 * - String traversal
 * 
 * Algorithm:
 * 1. Initialize counters for uppercase, lowercase, digits, special characters
 * 2. Traverse each character in the string
 * 3. Check ASCII range of each character:
 *    - 65-90: uppercase letter
 *    - 97-122: lowercase letter
 *    - 48-57: digit
 *    - Otherwise: special character
 * 4. Increment appropriate counter
 * 5. Return all counts
 * 
 * Pseudocode:
 * FUNCTION countCharacterTypes(str):
 *     uppercase = 0
 *     lowercase = 0
 *     digits = 0
 *     special = 0
 *     
 *     FOR each character ch in str:
 *         IF ch >= 65 AND ch <= 90:
 *             uppercase++
 *         ELSE IF ch >= 97 AND ch <= 122:
 *             lowercase++
 *         ELSE IF ch >= 48 AND ch <= 57:
 *             digits++
 *         ELSE:
 *             special++
 *     
 *     RETURN (uppercase, lowercase, digits, special)
 * 
 * Explanation with Example:
 * Input string: "Hello World! 123"
 * Dry run:
 * - 'H': uppercase (72) → uppercase=1
 * - 'e': lowercase (101) → lowercase=1
 * - 'l': lowercase (108) → lowercase=2
 * - 'l': lowercase → lowercase=3
 * - 'o': lowercase (111) → lowercase=4
 * - ' ': special → special=1
 * - 'W': uppercase (87) → uppercase=2
 * - 'o': lowercase → lowercase=5
 * - 'r': lowercase → lowercase=6
 * - 'l': lowercase → lowercase=7
 * - 'd': lowercase → lowercase=8
 * - '!': special → special=2
 * - ' ': special → special=3
 * - '1': digit (49) → digits=1
 * - '2': digit (50) → digits=2
 * - '3': digit (51) → digits=3
 * Final result: Uppercase=2, Lowercase=8, Digits=3, Special=3
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1)
 */

public class p23CountCharacterTypes {
    
    /**
     * Method to count uppercase, lowercase, digits, and special characters
     * @param str The input string
     * @return Array with [uppercase, lowercase, digits, special]
     */
    public static int[] countCharacterTypes(String str) {
        int uppercase = 0;
        int lowercase = 0;
        int digits = 0;
        int special = 0;
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (ch >= 65 && ch <= 90) {
                uppercase++;
            } else if (ch >= 97 && ch <= 122) {
                lowercase++;
            } else if (ch >= 48 && ch <= 57) {
                digits++;
            } else {
                special++;
            }
        }
        
        return new int[]{uppercase, lowercase, digits, special};
    }
    
    public static void main(String[] args) {
        // Question: Count uppercase, lowercase, digits, and special characters in a string
        // Topics: Character classification, ASCII range checking, multiple counter tracking, string traversal
        // Algorithm: Check ASCII range of each character, increment appropriate counter
        // Pseudocode: initialize counters → traverse → check ASCII range → increment → return counts
        // Example: "Hello World! 123" → Uppercase=2, Lowercase=8, Digits=3, Special=3
        // Dry run: H(upper), e(lower), l(lower), l(lower), o(lower), (special), W(upper)... → 2,8,3,3
        // Time: O(n), Space: O(1)
        
        String str = "Hello World! 123";
        int[] counts = countCharacterTypes(str);
        
        System.out.println("String: " + str);
        System.out.println("Uppercase letters: " + counts[0]);
        System.out.println("Lowercase letters: " + counts[1]);
        System.out.println("Digits: " + counts[2]);
        System.out.println("Special characters: " + counts[3]);
    }
}
