/**
 * QUESTION: Wildcard Matching - Given a pattern string with wildcards '?' and '*',
 * determine if it matches the entire input string. '?' matches any single character,
 * '*' matches any sequence of characters (including empty sequence).
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - String: "baaabab"
 * - Pattern: "*ba****ab"
 * - Pattern breakdown:
 *   *: matches empty or any chars
 *   b: matches 'b'
 *   a: matches 'a'
 *   *: matches "aa"
 *   *: matches empty
 *   *: matches empty
 *   *: matches empty
 *   a: matches 'a'
 *   b: matches 'b'
 * - Result: true (pattern matches string)
 * 
 * APPROACH USED: Dynamic Programming with Tabulation (Bottom-Up)
 * 
 * PSEUDOCODE:
 * function isMatch(s, p):
 *   n = s.length, m = p.length
 *   dp = boolean[n+1][m+1]
 *   
 *   // Initialize
 *   dp[0][0] = true  // Empty pattern matches empty string
 *   
 *   // When pattern is empty
 *   for i = 1 to n:
 *     dp[i][0] = false
 *   
 *   // When string is empty
 *   for j = 1 to m:
 *     if p[j-1] == '*':
 *       dp[0][j] = dp[0][j-1]
 *   
 *   // Bottom-up DP
 *   for i = 1 to n:
 *     for j = 1 to m:
 *       // Case 1: Same char OR '?'
 *       if s[i-1] == p[j-1] or p[j-1] == '?':
 *         dp[i][j] = dp[i-1][j-1]
 *       // Case 2: '*'
 *       else if p[j-1] == '*':
 *         dp[i][j] = dp[i-1][j]   // '*' matches multiple chars
 *                  || dp[i][j-1]  // '*' matches empty
 *       // Case 3: Not match
 *       else:
 *         dp[i][j] = false
 *   
 *   return dp[n][m]
 * 
 * EXPLANATION OF STEPS:
 * 1. DP Table: dp[i][j] represents if first i characters of string match first j characters of pattern
 * 2. Initialization:
 *    - dp[0][0] = true (empty pattern matches empty string)
 *    - dp[i][0] = false (non-empty string can't match empty pattern)
 *    - dp[0][j] = true only if pattern up to j consists of only '*'
 * 3. For each character pair:
 *    - If characters match or pattern has '?': Use diagonal value
 *    - If pattern has '*': Two choices
 *      a) dp[i-1][j]: '*' matches one or more characters
 *      b) dp[i][j-1]: '*' matches empty sequence
 *    - Otherwise: false
 * 4. Final answer: dp[n][m]
 * 
 * DRY RUN for s = "baaabab", p = "*ba****ab":
 * 
 * Key positions:
 * - dp[0][1] = true (first '*' matches empty)
 * - dp[1][1] = true (first '*' matches 'b')
 * - dp[1][2] = true (pattern '*b' matches 'b')
 * - dp[2][3] = true (pattern '*ba' matches 'ba')
 * - dp[4][4] = true (pattern '*ba*' matches 'baaa')
 * - dp[7][9] = true (full pattern matches full string)
 * 
 * Final answer: dp[7][9] = true
 */
public class WildcardMatching {
    
    public static boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        
        boolean dp[][] = new boolean[n + 1][m + 1];
        
        // Initialize
        dp[0][0] = true;  // Empty pattern matches empty string
        
        // When pattern is empty
        for (int i = 1; i <= n; i++) {
            dp[i][0] = false;  // Non-empty string can't match empty pattern
        }
        
        // When string is empty
        for (int j = 1; j <= m; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];  // '*' can match empty string
            }
        }
        
        // Bottom-up DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // Case 1: Same character OR '?'
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];  // Match, use diagonal value
                }
                // Case 2: '*'
                else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j]   // '*' matches multiple chars
                             || dp[i][j - 1]; // '*' matches empty
                }
                // Case 3: Not match
                else {
                    dp[i][j] = false;
                }
            }
        }
        
        return dp[n][m];
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case:
         * String: "baaabab"
         * Pattern: "*ba****ab"
         * 
         * Expected result: true
         * 
         * Step-by-step DP table filling:
         * 
         * String: b a a a b a b
         * Pattern: * b a * * * * a b
         * 
         * Key matching steps:
         * 1. dp[0][1] = true (first '*' matches empty)
         * 2. dp[1][1] = true (first '*' matches 'b')
         * 3. dp[1][2] = true ('b' matches 'b')
         * 4. dp[2][3] = true ('a' matches 'a')
         * 5. dp[4][4] = true (second '*' matches 'aa')
         * 6. dp[7][9] = true (full match)
         * 
         * Pattern interpretation:
         * *: matches empty at start
         * b: matches 'b' at position 1
         * a: matches 'a' at position 2
         * *: matches 'aa' at positions 3-4
         * * * *: match empty
         * a: matches 'a' at position 6
         * b: matches 'b' at position 7
         */
        
        String s = "baaabab";
        String p = "*ba****ab";
        
        System.out.println("Wildcard Matching Problem");
        System.out.println("String: " + s);
        System.out.println("Pattern: " + p);
        System.out.println("Match result: " + isMatch(s, p));
        
        // Print DP table for understanding
        System.out.println("\nDP Table (T = True, F = False):");
        System.out.print("    \t");
        for (int j = 0; j <= p.length(); j++) {
            if (j == 0) {
                System.out.print("\"\"\t");
            } else {
                System.out.print(p.charAt(j - 1) + "\t");
            }
        }
        System.out.println();
        
        int n = s.length();
        int m = p.length();
        boolean dp[][] = new boolean[n + 1][m + 1];
        
        // Initialize
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = false;
        }
        for (int j = 1; j <= m; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        
        // Fill and display table
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                System.out.print("\"\"\t");
            } else {
                System.out.print(s.charAt(i - 1) + "\t");
            }
            
            for (int j = 0; j <= m; j++) {
                if (i > 0 && j > 0) {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (p.charAt(j - 1) == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }
                System.out.print(dp[i][j] ? "T\t" : "F\t");
            }
            System.out.println();
        }
        
        // Show pattern matching explanation
        System.out.println("\nPattern matching explanation:");
        System.out.println("Pattern: * b a * * * * a b");
        System.out.println("String:  b a a a b a b");
        System.out.println("Match:   * b a * * * * a b");
        System.out.println("Explanation:");
        System.out.println("- First '*': matches empty (could also match leading characters)");
        System.out.println("- 'b': matches 'b' at position 1");
        System.out.println("- 'a': matches 'a' at position 2");
        System.out.println("- Second '*': matches 'aa' at positions 3-4");
        System.out.println("- Next three '*': match empty");
        System.out.println("- 'a': matches 'a' at position 6");
        System.out.println("- 'b': matches 'b' at position 7");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 1: Simple case
        String test1s = "aa";
        String test1p = "a";
        System.out.println("'" + test1s + "' vs '" + test1p + "': " + isMatch(test1s, test1p)); // false
        
        // Test case 2: With '*'
        String test2s = "aa";
        String test2p = "*";
        System.out.println("'" + test2s + "' vs '" + test2p + "': " + isMatch(test2s, test2p)); // true
        
        // Test case 3: With '?'
        String test3s = "cb";
        String test3p = "?b";
        System.out.println("'" + test3s + "' vs '" + test3p + "': " + isMatch(test3s, test3p)); // true
        
        // Test case 4: Complex pattern
        String test4s = "adceb";
        String test4p = "*a*b";
        System.out.println("'" + test4s + "' vs '" + test4p + "': " + isMatch(test4s, test4p)); // true
        
        // Test case 5: Multiple '*'
        String test5s = "acdcb";
        String test5p = "a*c?b";
        System.out.println("'" + test5s + "' vs '" + test5p + "': " + isMatch(test5s, test5p)); // false
    }
}
