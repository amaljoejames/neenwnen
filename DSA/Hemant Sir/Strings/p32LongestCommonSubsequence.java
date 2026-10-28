/**
 * Question: Find the longest common subsequence between two strings.
 * 
 * Topics Included:
 * - Dynamic programming
 * - Subsequence comparison
 * - Matrix filling
 * - Backtracking
 * 
 * Algorithm:
 * 1. Create a 2D DP table of size (m+1) x (n+1)
 * 2. Initialize first row and column with 0
 * 3. Fill DP table:
 *    - If characters match: dp[i][j] = dp[i-1][j-1] + 1
 *    - If characters don't match: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 * 4. The value at dp[m][n] is the length of LCS
 * 5. Backtrack to find the actual LCS string
 * 
 * Pseudocode:
 * FUNCTION longestCommonSubsequence(str1, str2):
 *     m = str1.length()
 *     n = str2.length()
 *     dp = new int[m+1][n+1]
 *     
 *     FOR i = 0 to m:
 *         dp[i][0] = 0
 *     FOR j = 0 to n:
 *         dp[0][j] = 0
 *     
 *     FOR i = 1 to m:
 *         FOR j = 1 to n:
 *             IF str1[i-1] == str2[j-1]:
 *                 dp[i][j] = dp[i-1][j-1] + 1
 *             ELSE:
 *                 dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 *     
 *     RETURN reconstructLCS(str1, str2, dp)
 * 
 * Explanation with Example:
 * Input: str1 = "abcde", str2 = "ace"
 * Dry run:
 * DP table:
 *     ""  a  c  e
 * ""   0  0  0  0
 * a    0  1  1  1
 * b    0  1  1  1
 * c    0  1  2  2
 * d    0  1  2  2
 * e    0  1  2  3
 * 
 * LCS length = 3
 * Backtrack: "ace"
 * Final result: "ace"
 * 
 * Time Complexity: O(m*n) where m and n are lengths of the two strings
 * Space Complexity: O(m*n) for DP table
 */

public class p32LongestCommonSubsequence {
    
    /**
     * Method to find the longest common subsequence between two strings
     * @param str1 The first string
     * @param str2 The second string
     * @return The longest common subsequence
     */
    public static String longestCommonSubsequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // Reconstruct LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        
        return lcs.reverse().toString();
    }
    
    public static void main(String[] args) {
        // Question: Find the longest common subsequence between two strings
        // Topics: Dynamic programming, subsequence comparison, matrix filling, backtracking
        // Algorithm: DP table, fill based on character match, backtrack to find LCS
        // Pseudocode: create DP table → fill based on match → backtrack → return LCS
        // Example: "abcde", "ace" → "ace", "AGGTAB", "GXTXAYB" → "GTAB"
        // Dry run: "abcde","ace" → dp filled → backtrack → "ace"
        // Time: O(m*n), Space: O(m*n)
        
        String str1 = "abcde";
        String str2 = "ace";
        String str3 = "AGGTAB";
        String str4 = "GXTXAYB";
        
        System.out.println("LCS of '" + str1 + "' and '" + str2 + "': " + longestCommonSubsequence(str1, str2));
        System.out.println("LCS of '" + str3 + "' and '" + str4 + "': " + longestCommonSubsequence(str3, str4));
    }
}
