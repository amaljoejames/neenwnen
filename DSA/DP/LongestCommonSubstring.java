/**
 * QUESTION: Longest Common Substring - Given two strings, find the length of their 
 * longest common substring. A substring is a contiguous sequence of characters.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - String 1: "ABCDE"
 * - String 2: "ABGCE"
 * - Common substrings: "A", "B", "C", "E", "AB"
 * - Longest Common Substring: "AB" (length 2)
 * - Note: "ABC" is not common because it's not contiguous in second string
 * 
 * APPROACH USED: Dynamic Programming with Tabulation (Bottom-Up)
 * 
 * PSEUDOCODE:
 * function longestCommonSubstring(str1, str2):
 *   n = str1.length, m = str2.length
 *   dp = int[n+1][m+1]
 *   ans = 0
 *   
 *   // Initialize
 *   for i = 0 to n:
 *     dp[i][0] = 0
 *   for j = 0 to m:
 *     dp[0][j] = 0
 *   
 *   // Bottom-up DP
 *   for i = 1 to n:
 *     for j = 1 to m:
 *       if str1[i-1] == str2[j-1]:  // Characters match
 *         dp[i][j] = dp[i-1][j-1] + 1
 *         ans = max(ans, dp[i][j])
 *       else:  // Characters don't match
 *         dp[i][j] = 0  // Reset to 0 for substring
 *   
 *   return ans
 * 
 * EXPLANATION OF STEPS:
 * 1. DP Table: dp[i][j] represents length of longest common substring ending at position i in str1 and position j in str2
 * 2. Key difference from LCS:
 *    - If characters don't match, we reset to 0 (substring must be contiguous)
 *    - We keep track of maximum value seen so far
 * 3. For each character pair:
 *    - If characters match: Extend the previous common substring by 1
 *    - If characters don't match: Reset to 0 (continuity broken)
 * 4. Final answer: Maximum value in DP table
 * 
 * DRY RUN for str1 = "ABCDE", str2 = "ABGCE":
 * 
 * DP Table construction:
 *    "" A B G C E
 * "" 0 0 0 0 0 0
 * A  0 1 0 0 0 0
 * B  0 0 2 0 0 0
 * C  0 0 0 0 1 0
 * D  0 0 0 0 0 0
 * E  0 0 0 0 0 1
 * 
 * Maximum value in table: 2 (at position [2][2])
 * Longest Common Substring: "AB"
 */
public class LongestCommonSubstring {
    
    public static int longestCommonSubstring(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        
        int dp[][] = new int[n + 1][m + 1];
        int ans = 0;
        
        // Initialize
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 0;  // Empty string case
        }
        for (int j = 0; j < m + 1; j++) {
            dp[0][j] = 0;  // Empty string case
        }
        
        // Bottom-up DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;  // Extend substring
                    ans = Math.max(ans, dp[i][j]);     // Update maximum
                } else {
                    dp[i][j] = 0;  // Reset - substring broken
                }
            }
        }
        
        return ans;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case:
         * String 1: "ABCDE"
         * String 2: "ABGCE"
         * 
         * Expected longest common substring length: 2
         * Longest common substring: "AB"
         * 
         * Step-by-step DP table filling:
         * 
         * Characters at each position:
         * str1: A B C D E
         * str2: A B G C E
         * 
         * Key positions:
         * A-A: match, dp[1][1] = dp[0][0] + 1 = 1, ans = 1
         * B-B: match, dp[2][2] = dp[1][1] + 1 = 2, ans = 2
         * C-G: no match, dp[3][3] = 0
         * C-C: match, dp[3][4] = dp[2][3] + 1 = 1, ans = 2
         * E-E: match, dp[5][5] = dp[4][4] + 1 = 1, ans = 2
         * 
         * Maximum value found: 2 (at dp[2][2])
         * This represents substring "AB"
         */
        
        String str1 = "ABCDE";
        String str2 = "ABGCE";
        
        System.out.println("Longest Common Substring Problem");
        System.out.println("String 1: " + str1);
        System.out.println("String 2: " + str2);
        System.out.println("Longest Common Substring Length = " + longestCommonSubstring(str1, str2));
        
        // Print DP table for understanding
        System.out.println("\nDP Table:");
        System.out.print("    \t");
        for (int j = 0; j <= str2.length(); j++) {
            if (j == 0) {
                System.out.print("\"\"\t");
            } else {
                System.out.print(str2.charAt(j - 1) + "\t");
            }
        }
        System.out.println();
        
        int n = str1.length();
        int m = str2.length();
        int dp[][] = new int[n + 1][m + 1];
        int maxLen = 0;
        int maxI = 0, maxJ = 0;
        
        // Fill and display table
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                System.out.print("\"\"\t");
            } else {
                System.out.print(str1.charAt(i - 1) + "\t");
            }
            
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        maxI = i;
                        maxJ = j;
                    }
                } else {
                    dp[i][j] = 0;
                }
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        
        // Extract the longest common substring
        if (maxLen > 0) {
            String lcs = str1.substring(maxI - maxLen, maxI);
            System.out.println("\nLongest Common Substring: \"" + lcs + "\"");
            System.out.println("Length: " + maxLen);
        } else {
            System.out.println("\nNo common substring found");
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 1: Completely different strings
        String test1a = "abc";
        String test1b = "xyz";
        System.out.println("LCS of 'abc' and 'xyz': " + longestCommonSubstring(test1a, test1b)); // 0
        
        // Test case 2: One string is substring of another
        String test2a = "abcdef";
        String test2b = "cde";
        System.out.println("LCS of 'abcdef' and 'cde': " + longestCommonSubstring(test2a, test2b)); // 3
        
        // Test case 3: Same strings
        String test3a = "hello";
        String test3b = "hello";
        System.out.println("LCS of 'hello' and 'hello': " + longestCommonSubstring(test3a, test3b)); // 5
        
        // Test case 4: Multiple common substrings
        String test4a = "ababc";
        String test4b = "abcdab";
        System.out.println("LCS of 'ababc' and 'abcdab': " + longestCommonSubstring(test4a, test4b)); // 2 ("ab")
    }
}
