/**
 * Question: Check if a string contains only digits.
 * 
 * Topics Included:
 * - Character validation
 * - String traversal
 * - ASCII range checking
 * - Input validation
 * 
 * Algorithm:
 * 1. Check if string is empty
 * 2. Traverse each character in the string
 * 3. Check if character is between '0' and '9'
 * 4. If any character is not a digit, return false
 * 5. If all characters are digits, return true
 * 
 * Pseudocode:
 * FUNCTION containsOnlyDigits(str):
 *     IF str is null OR str.isEmpty():
 *         RETURN false
 *     
 *     FOR each character ch in str:
 *         IF ch < '0' OR ch > '9':
 *             RETURN false
 *     
 *     RETURN true
 * 
 * Explanation with Example:
 * Input string: "12345"
 * Dry run:
 * - '1': between '0' and '9' ✓
 * - '2': between '0' and '9' ✓
 * - '3': between '0' and '9' ✓
 * - '4': between '0' and '9' ✓
 * - '5': between '0' and '9' ✓
 * Final result: true
 * 
 * Input string: "12a45"
 * Dry run:
 * - '1': between '0' and '9' ✓
 * - '2': between '0' and '9' ✓
 * - 'a': not between '0' and '9' → return false
 * Final result: false
 * 
 * Input string: "  123"
 * Dry run:
 * - ' ': not between '0' and '9' → return false
 * Final result: false
 * 
 * Input string: "9876543210"
 * Dry run:
 * - All characters are between '0' and '9' ✓
 * Final result: true
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1)
 */

public class p15CheckDigits {
    
    /**
     * Method to check if a string contains only digits
     * @param str The input string
     * @return true if only digits, false otherwise
     */
    public static boolean containsOnlyDigits(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // Check if character is not a digit
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        // Question: Check if a string contains only digits
        // Topics: Character validation, string traversal, ASCII range checking, input validation
        // Algorithm: Traverse string, check each character is between '0' and '9'
        // Pseudocode: check null/empty → traverse → if not digit return false → return true
        // Example: "12345" → true, "12a45" → false
        // Dry run: "12345" → 1✓,2✓,3✓,4✓,5✓ → true
        // Time: O(n), Space: O(1)
        
        String str1 = "12345";
        String str2 = "12a45";
        String str3 = "  123";
        String str4 = "9876543210";
        
        System.out.println("'" + str1 + "' contains only digits: " + containsOnlyDigits(str1));
        System.out.println("'" + str2 + "' contains only digits: " + containsOnlyDigits(str2));
        System.out.println("'" + str3 + "' contains only digits: " + containsOnlyDigits(str3));
        System.out.println("'" + str4 + "' contains only digits: " + containsOnlyDigits(str4));
    }
}
