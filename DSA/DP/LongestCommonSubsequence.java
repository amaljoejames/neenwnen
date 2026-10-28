/**
 * QUESTION: Longest Common Subsequence (LCS) - Given two strings, find the length of their 
 * longest common subsequence. A subsequence is a sequence that appears in the same relative 
 * order but not necessarily contiguous.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - String 1: "abcdge"
 * - String 2: "abedg"
 * - Common subsequences: "ab", "ad", "ag", "bd", "bg", "dg", "abg", "adg"
 * - Longest Common Subsequence: "abdg" (length 4)
 * 
 * APPROACH USED: Dynamic Programming with Recursion, Memoization, and Tabulation
 * 
 * PSEUDOCODE (Tabulation):
 * function lcsTab(str1, str2):
 *   n = str1.length, m = str2.length
 *   dp = int[n+1][m+1]
 *   
 *   // Initialize base cases
 *   for i = 0 to n:
 *     for j = 0 to m:
 *       if i == 0 or j == 0:
 *         dp[i][j] = 0
 *   
 *   // Fill DP table
 *   for i = 1 to n:
 *     for j = 1 to m:
 *       if str1[i-1] == str2[j-1]:  // Characters match
 *         dp[i][j] = dp[i-1][j-1] + 1
 *       else:  // Characters don't match
 *         dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 *   
 *   return dp[n][m]
 * 
 * EXPLANATION OF STEPS:
 * 1. DP Table: dp[i][j] represents length of LCS of first i characters of str1 and first j characters of str2
 * 2. Base Cases: dp[0][j] = 0 and dp[i][0] = 0 (empty string with any string has LCS length 0)
 * 3. For each character pair:
 *    - If characters match: Include this character in LCS and add 1 to result of previous characters
 *    - If characters don't match: Take maximum of excluding current character from either string
 * 4. Final answer: dp[n][m]
 * 
 * DRY RUN for str1 = "abcdge", str2 = "abedg":
 * 
 * DP Table construction:
 *    "" a b e d g
 * "" 0 0 0 0 0 0
 * a  0 1 1 1 1 1
 * b  0 1 2 2 2 2
 * c  0 1 2 2 2 2
 * d  0 1 2 2 3 3
 * g  0 1 2 2 3 4
 * e  0 1 2 3 3 4
 * 
 * Final answer: dp[6][5] = 4
 * LCS: "abdg"
 */
public class LongestCommonSubsequence {
    
    // LCS - Recursion (exponential time)
    public static int lcs(String str1, String str2, int n, int m) {
        // Base case
        if (n == 0 || m == 0) {
            return 0;
        }
        
        // If characters are same
        if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
            return lcs(str1, str2, n - 1, m - 1) + 1;
        } 
        // If characters are different
        else {
            int ans1 = lcs(str1, str2, n - 1, m);    // Exclude from str1
            int ans2 = lcs(str1, str2, n, m - 1);    // Exclude from str2
            return Math.max(ans1, ans2);
        }
    }
    
    // LCS - Memoization (Top-Down DP)
    public static int lcs2(String str1, String str2, int n, int m, int dp[][]) {
        // Base case
        if (n == 0 || m == 0) {
            return 0;
        }
        
        // Already calculated
        if (dp[n][m] != -1) {
            return dp[n][m];
        }
        
        // Same character
        if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
            return dp[n][m] = lcs2(str1, str2, n - 1, m - 1, dp) + 1;
        } 
        // Different character
        else {
            int ans1 = lcs2(str1, str2, n - 1, m, dp);
            int ans2 = lcs2(str1, str2, n, m - 1, dp);
            return dp[n][m] = Math.max(ans1, ans2);
        }
    }
    
    // LCS - Tabulation (Bottom-Up DP)
    public static int lcsTab(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        
        int dp[][] = new int[n + 1][m + 1];
        
        // Base case initialization
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;  // Empty string case
                }
            }
        }
        
        // Fill DP table
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;  // Characters match
                } else {
                    int ans1 = dp[i - 1][j];  // Exclude from str1
                    int ans2 = dp[i][j - 1];  // Exclude from str2
                    dp[i][j] = Math.max(ans1, ans2);
                }
            }
        }
        
        return dp[n][m];
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case:
         * String 1: "abcdge"
         * String 2: "abedg"
         * 
         * Expected LCS length: 4
         * LCS: "abdg"
         * 
         * Step-by-step DP table filling:
         * 
         * Characters at each position:
         * str1: a b c d g e
         * str2: a b e d g
         * 
         * Matching positions:
         * a-a: match, dp[1][1] = dp[0][0] + 1 = 1
         * b-b: match, dp[2][2] = dp[1][1] + 1 = 2
         * c-e: no match, dp[3][3] = max(dp[2][3], dp[3][2]) = 2
         * d-d: match, dp[4][4] = dp[3][3] + 1 = 3
         * g-g: match, dp[5][5] = dp[4][4] + 1 = 4
         * e-e: match, dp[6][3] = dp[5][2] + 1 = 3
         * 
         * Final answer: dp[6][5] = 4
         */
        
        String str1 = "abcdge";
        String str2 = "abedg";
        
        int n = str1.length();
        int m = str2.length();
        
        System.out.println("Longest Common Subsequence Problem");
        System.out.println("String 1: " + str1);
        System.out.println("String 2: " + str2);
        
        // Recursive approach
        System.out.println("\nRecursive approach:");
        System.out.println("LCS length = " + lcs(str1, str2, n, m));
        
        // Memoization approach
        int dp[][] = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println("\nMemoization approach:");
        System.out.println("LCS length = " + lcs2(str1, str2, n, m, dp));
        
        // Tabulation approach
        System.out.println("\nTabulation approach:");
        System.out.println("LCS length = " + lcsTab(str1, str2));
        
        // Print DP table for tabulation approach
        System.out.println("\nDP Table (Tabulation):");
        System.out.print("    \t");
        for (int j = 0; j <= m; j++) {
            if (j == 0) {
                System.out.print("\"\"\t");
            } else {
                System.out.print(str2.charAt(j - 1) + "\t");
            }
        }
        System.out.println();
        
        int dpTab[][] = new int[n + 1][m + 1];
        
        // Fill and display table
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                System.out.print("\"\"\t");
            } else {
                System.out.print(str1.charAt(i - 1) + "\t");
            }
            
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dpTab[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dpTab[i][j] = dpTab[i - 1][j - 1] + 1;
                } else {
                    dpTab[i][j] = Math.max(dpTab[i - 1][j], dpTab[i][j - 1]);
                }
                System.out.print(dpTab[i][j] + "\t");
            }
            System.out.println();
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 1: Completely different strings
        String test1a = "abc";
        String test1b = "xyz";
        System.out.println("LCS of 'abc' and 'xyz': " + lcsTab(test1a, test1b)); // 0
        
        // Test case 2: One string is subset of another
        String test2a = "abcdef";
        String test2b = "abc";
        System.out.println("LCS of 'abcdef' and 'abc': " + lcsTab(test2a, test2b)); // 3
        
        // Test case 3: Same strings
        String test3a = "hello";
        String test3b = "hello";
        System.out.println("LCS of 'hello' and 'hello': " + lcsTab(test3a, test3b)); // 5
    }
}
