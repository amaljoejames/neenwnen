/**
 * Question: Find the longest substring without repeating characters.
 * 
 * Topics Included:
 * - Sliding window technique
 * - Hash set usage
 * - Character tracking
 * - Window manipulation
 * 
 * Algorithm:
 * 1. Use sliding window with two pointers (left and right)
 * 2. Use a hash set to track characters in current window
 * 3. Expand window by moving right pointer
 * 4. If character already in set, shrink window from left
 * 5. Track maximum window size encountered
 * 6. Return the longest substring found
 * 
 * Pseudocode:
 * FUNCTION longestUniqueSubstring(str):
 *     IF str is null OR str.length() == 0:
 *         RETURN ""
 *     
 *     charSet = new HashSet()
 *     left = 0
 *     maxLength = 0
 *     maxSubstring = ""
 *     
 *     FOR right = 0 to str.length() - 1:
 *         WHILE charSet.contains(str.charAt(right)):
 *             charSet.remove(str.charAt(left))
 *             left++
 *         
 *         charSet.add(str.charAt(right))
 *         
 *         IF right - left + 1 > maxLength:
 *             maxLength = right - left + 1
 *             maxSubstring = str.substring(left, right + 1)
 *     
 *     RETURN maxSubstring
 * 
 * Explanation with Example:
 * Input string: "abcabcbb"
 * Dry run:
 * - right=0: 'a' not in set → set={a}, len=1
 * - right=1: 'b' not in set → set={a,b}, len=2
 * - right=2: 'c' not in set → set={a,b,c}, len=3
 * - right=3: 'a' in set → remove 'a', left=1 → set={b,c,a}, len=3
 * - right=4: 'b' in set → remove 'b', left=2 → set={c,a,b}, len=3
 * - right=5: 'c' in set → remove 'c', left=3 → set={a,b,c}, len=3
 * - right=6: 'b' in set → remove 'a', left=4 → set={b,c}, remove 'b', left=5 → set={c}, add 'b' → set={c,b}, len=2
 * - right=7: 'b' in set → remove 'c', left=6 → set={b}, remove 'b', left=7 → set={}, add 'b' → set={b}, len=1
 * Maximum length: 3, substring: "abc"
 * Final result: "abc"
 * 
 * Input string: "bbbbb"
 * Dry run:
 * - All characters are 'b', window never grows beyond 1
 * Maximum length: 1, substring: "b"
 * Final result: "b"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(min(m, n)) where m is the size of character set
 */

import java.util.HashSet;
import java.util.Set;

public class p30LongestUniqueSubstring {
    
    /**
     * Method to find the longest substring without repeating characters
     * @param str The input string
     * @return The longest substring with unique characters
     */
    public static String longestUniqueSubstring(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        
        Set<Character> charSet = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        String maxSubstring = "";
        
        for (int right = 0; right < str.length(); right++) {
            // Remove characters from left until current character is not in set
            while (charSet.contains(str.charAt(right))) {
                charSet.remove(str.charAt(left));
                left++;
            }
            
            // Add current character to set
            charSet.add(str.charAt(right));
            
            // Update maximum if current window is larger
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                maxSubstring = str.substring(left, right + 1);
            }
        }
        
        return maxSubstring;
    }
    
    public static void main(String[] args) {
        // Question: Find the longest substring without repeating characters
        // Topics: Sliding window technique, hash set usage, character tracking, window manipulation
        // Algorithm: Sliding window, expand right, shrink left when duplicate found, track max
        // Pseudocode: sliding window → expand right → shrink left on duplicate → track max → return
        // Example: "abcabcbb" → "abc", "bbbbb" → "b"
        // Dry run: "abcabcbb" → abc(3), then shrink → max=3 → "abc"
        // Time: O(n), Space: O(min(m,n))
        
        String str1 = "abcabcbb";
        String str2 = "bbbbb";
        String str3 = "pwwkew";
        
        System.out.println("String: " + str1 + ", Longest unique: " + longestUniqueSubstring(str1));
        System.out.println("String: " + str2 + ", Longest unique: " + longestUniqueSubstring(str2));
        System.out.println("String: " + str3 + ", Longest unique: " + longestUniqueSubstring(str3));
    }
}
