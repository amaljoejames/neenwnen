/**
 * Question: Find the longest palindrome substring in a string.
 * 
 * Topics Included:
 * - Dynamic programming
 * - String manipulation
 * - Palindrome detection
 * - Substring analysis
 * 
 * Algorithm:
 * 1. Use expand around center approach
 * 2. For each character, expand outward to find palindrome
 * 3. Handle both odd and even length palindromes
 * 4. Track the longest palindrome found
 * 5. Return the longest palindrome substring
 * 
 * Pseudocode:
 * FUNCTION longestPalindromeSubstring(str):
 *     IF str is null OR str.length() < 1:
 *         RETURN ""
 *     
 *     start = 0
 *     end = 0
 *     
 *     FOR i = 0 to str.length() - 1:
 *         len1 = expandAroundCenter(str, i, i)      // Odd length
 *         len2 = expandAroundCenter(str, i, i + 1)  // Even length
 *         maxLen = max(len1, len2)
 *         
 *         IF maxLen > end - start:
 *             start = i - (maxLen - 1) / 2
 *             end = i + maxLen / 2
 *     
 *     RETURN str.substring(start, end + 1)
 * 
 * FUNCTION expandAroundCenter(str, left, right):
 *     WHILE left >= 0 AND right < str.length() AND str[left] == str[right]:
 *         left--
 *         right++
 *     RETURN right - left - 1
 * 
 * Explanation with Example:
 * Input string: "babad"
 * Dry run:
 * - i=0: expand(0,0) → "b" (len=1), expand(0,1) → no match → maxLen=1
 * - i=1: expand(1,1) → "a" (len=1), expand(1,2) → "bab" (len=3) → maxLen=3
 * - i=2: expand(2,2) → "a" (len=1), expand(2,3) → "aba" (len=3) → maxLen=3
 * - i=3: expand(3,3) → "b" (len=1), expand(3,4) → no match → maxLen=1
 * - i=4: expand(4,4) → "d" (len=1) → maxLen=1
 * Longest: "bab" or "aba"
 * Final result: "bab"
 * 
 * Time Complexity: O(n²) where n is the length of the string
 * Space Complexity: O(1)
 */

public class p27LongestPalindromeSubstring {
    
    /**
     * Helper function to expand around center
     */
    private static int expandAroundCenter(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
    
    /**
     * Method to find the longest palindrome substring
     * @param str The input string
     * @return The longest palindrome substring
     */
    public static String longestPalindromeSubstring(String str) {
        if (str == null || str.length() < 1) {
            return "";
        }
        
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < str.length(); i++) {
            int len1 = expandAroundCenter(str, i, i);      // Odd length
            int len2 = expandAroundCenter(str, i, i + 1);  // Even length
            int maxLen = Math.max(len1, len2);
            
            if (maxLen > end - start) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }
        
        return str.substring(start, end + 1);
    }
    
    public static void main(String[] args) {
        // Question: Find the longest palindrome substring in a string
        // Topics: Dynamic programming, string manipulation, palindrome detection, substring analysis
        // Algorithm: Expand around center approach, handle odd and even length palindromes
        // Pseudocode: for each char → expand outward → track longest → return
        // Example: "babad" → "bab" or "aba", "cbbd" → "bb"
        // Dry run: "babad" → i=1: "bab"(3), i=2: "aba"(3) → longest="bab"
        // Time: O(n²), Space: O(1)
        
        String str1 = "babad";
        String str2 = "cbbd";
        String str3 = "forgeeksskeegfor";
        
        System.out.println("String: " + str1 + ", Longest palindrome: " + longestPalindromeSubstring(str1));
        System.out.println("String: " + str2 + ", Longest palindrome: " + longestPalindromeSubstring(str2));
        System.out.println("String: " + str3 + ", Longest palindrome: " + longestPalindromeSubstring(str3));
    }
}
