/**
 * Question: Check if a string is a palindrome.
 * 
 * Topics Included:
 * - String manipulation
 * - Two-pointer technique
 * - Palindrome detection
 * - Character comparison
 * 
 * Algorithm:
 * 1. Initialize two pointers at both ends of string
 * 2. Compare characters at both pointers
 * 3. If characters match, move pointers inward
 * 4. If characters don't match, return false
 * 5. If pointers meet or cross, return true
 * 
 * Pseudocode:
 * FUNCTION isPalindrome(str):
 *     left = 0
 *     right = str.length() - 1
 *     
 *     WHILE left < right:
 *         IF str.charAt(left) != str.charAt(right):
 *             RETURN false
 *         left++
 *         right--
 *     
 *     RETURN true
 * 
 * Explanation with Example:
 * Input string: "racecar"
 * Dry run:
 * - Initial: left=0, right=6
 * - Compare 'r' and 'r': match, left=1, right=5
 * - Compare 'a' and 'a': match, left=2, right=4
 * - Compare 'c' and 'c': match, left=3, right=3
 * - left >= right, stop
 * Final result: true (palindrome)
 * 
 * Input string: "hello"
 * Dry run:
 * - Initial: left=0, right=4
 * - Compare 'h' and 'o': don't match
 * Final result: false (not palindrome)
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1)
 */

public class p2PalindromeCheck {
    
    /**
     * Method to check if a string is a palindrome
     * @param str The input string
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        // Question: Check if a string is a palindrome
        // Topics: String manipulation, two-pointer technique, palindrome detection, character comparison
        // Algorithm: Compare characters from both ends, move towards center
        // Pseudocode: two pointers → compare chars → if mismatch return false → return true
        // Example: "racecar" → true, "hello" → false
        // Dry run: "racecar" → r==r, a==a, c==c, e==e → true
        // Time: O(n), Space: O(1)
        
        String str1 = "racecar";
        String str2 = "hello";
        
        System.out.println("Is '" + str1 + "' a palindrome? " + isPalindrome(str1));
        System.out.println("Is '" + str2 + "' a palindrome? " + isPalindrome(str2));
    }
}
