/**
 * Question: Find the longest common substring between two strings.
 * 
 * Topics Included:
 * - Dynamic programming
 * - Substring comparison
 * - Matrix filling
 * - Contiguous matching
 * 
 * Algorithm:
 * 1. Create a 2D DP table of size (m+1) x (n+1)
 * 2. Initialize first row and column with 0
 * 3. Fill DP table:
 *    - If characters match: dp[i][j] = dp[i-1][j-1] + 1
 *    - If characters don't match: dp[i][j] = 0
 * 4. Track maximum value and its position
 * 5. Extract substring using the maximum position
 * 
 * Pseudocode:
 * FUNCTION longestCommonSubstring(str1, str2):
 *     m = str1.length()
 *     n = str2.length()
 *     dp = new int[m+1][n+1]
 *     maxLength = 0
 *     endIndex = 0
 *     
 *     FOR i = 1 to m:
 *         FOR j = 1 to n:
 *             IF str1[i-1] == str2[j-1]:
 *                 dp[i][j] = dp[i-1][j-1] + 1
 *                 IF dp[i][j] > maxLength:
 *                     maxLength = dp[i][j]
 *                     endIndex = i
 *             ELSE:
 *                 dp[i][j] = 0
 *     
 *     RETURN str1.substring(endIndex - maxLength, endIndex)
 * 
 * Explanation with Example:
 * Input: str1 = "abcdef", str2 = "zcdemf"
 * Dry run:
 * DP table:
 *     ""  z  c  d  e  m  f
 * ""   0  0  0  0  0  0  0
 * a    0  0  0  0  0  0  0
 * b    0  0  0  0  0  0  0
 * c    0  0  1  0  0  0  0
 * d    0  0  0  2  0  0  0
 * e    0  0  0  0  3  0  0
 * f    0  0  0  0  0  0  1
 * 
 * Maximum length = 3 at position i=5
 * Substring: str1[5-3:5] = str1[2:5] = "cde"
 * Final result: "cde"
 * 
 * Time Complexity: O(m*n) where m and n are lengths of the two strings
 * Space Complexity: O(m*n) for DP table
 */

public class p33LongestCommonSubstring {
    
    /**
     * Method to find the longest common substring between two strings
     * @param str1 The first string
     * @param str2 The second string
     * @return The longest common substring
     */
    public static String longestCommonSubstring(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;
        int endIndex = 0;
        
        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        
        if (maxLength == 0) {
            return "";
        }
        
        return str1.substring(endIndex - maxLength, endIndex);
    }
    
    public static void main(String[] args) {
        // Question: Find the longest common substring between two strings
        // Topics: Dynamic programming, substring comparison, matrix filling, contiguous matching
        // Algorithm: DP table, fill based on match (reset on mismatch), track max, extract
        // Pseudocode: create DP → fill (match: +1, mismatch: 0) → track max → extract substring
        // Example: "abcdef", "zcdemf" → "cde", "OldSite:GeeksforGeeks.org", "NewSite:GeeksQuiz.com" → "Site"
        // Dry run: "abcdef","zcdemf" → c,d,e match consecutively → "cde"
        // Time: O(m*n), Space: O(m*n)
        
        String str1 = "abcdef";
        String str2 = "zcdemf";
        String str3 = "OldSite:GeeksforGeeks.org";
        String str4 = "NewSite:GeeksQuiz.com";
        
        System.out.println("LCS substring of '" + str1 + "' and '" + str2 + "': " + longestCommonSubstring(str1, str2));
        System.out.println("LCS substring of '" + str3 + "' and '" + str4 + "': " + longestCommonSubstring(str3, str4));
    }
}
