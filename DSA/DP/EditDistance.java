/**
 * QUESTION: Edit Distance (Levenshtein Distance) - Given two strings, find the minimum 
 * number of operations required to convert one string to another. The allowed operations 
 * are insert, delete, and replace.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - String 1: "intention"
 * - String 2: "execution"
 * - Operations needed:
 *   1. Replace 'i' with 'e'
 *   2. Replace 'n' with 'x'
 *   3. Replace 't' with 'e'
 *   4. Replace 'n' with 'c'
 *   5. Insert 'u'
 *   6. Replace 'i' with 'u'
 *   7. Replace 'o' with 'i'
 *   8. Replace 'n' with 'o'
 * - Minimum operations: 5
 * 
 * APPROACH USED: Dynamic Programming with Tabulation (Bottom-Up)
 * 
 * PSEUDOCODE:
 * function editDistance(str1, str2):
 *   n = str1.length, m = str2.length
 *   dp = int[n+1][m+1]
 *   
 *   // Initialize
 *   for i = 0 to n:
 *     for j = 0 to m:
 *       if i == 0: dp[i][j] = j      // Insert all characters
 *       if j == 0: dp[i][j] = i      // Delete all characters
 *   
 *   // Bottom-up DP
 *   for i = 1 to n:
 *     for j = 1 to m:
 *       if str1[i-1] == str2[j-1]:  // Same character
 *         dp[i][j] = dp[i-1][j-1]
 *       else:  // Different character
 *         add = dp[i][j-1] + 1       // Insert
 *         del = dp[i-1][j] + 1       // Delete
 *         rep = dp[i-1][j-1] + 1     // Replace
 *         dp[i][j] = min(add, min(del, rep))
 *   
 *   return dp[n][m]
 * 
 * EXPLANATION OF STEPS:
 * 1. DP Table: dp[i][j] represents minimum operations to convert first i characters of str1 to first j characters of str2
 * 2. Initialization:
 *    - dp[0][j] = j (insert j characters to convert empty string to str2 prefix)
 *    - dp[i][0] = i (delete i characters to convert str1 prefix to empty string)
 * 3. For each character pair:
 *    - If characters match: No operation needed, take diagonal value
 *    - If characters don't match: Consider all three operations
 *      a) Insert: dp[i][j-1] + 1 (insert str2[j-1] into str1)
 *      b) Delete: dp[i-1][j] + 1 (delete str1[i-1])
 *      c) Replace: dp[i-1][j-1] + 1 (replace str1[i-1] with str2[j-1])
 * 4. Final answer: dp[n][m]
 * 
 * DRY RUN for str1 = "intention", str2 = "execution":
 * 
 * Key positions in DP table:
 * dp[1][1]: 'i' vs 'e' - different, min(1,1,1) = 1
 * dp[2][2]: 'n' vs 'x' - different, min(2,2,2) = 2
 * dp[3][3]: 't' vs 'e' - different, min(3,3,3) = 3
 * dp[4][4]: 'e' vs 'c' - different, min(4,4,4) = 4
 * dp[5][5]: 'n' vs 'u' - different, min(5,5,5) = 5
 * 
 * Final answer: dp[9][9] = 5
 */
public class EditDistance {
    
    public static int editDistance(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        
        int dp[][] = new int[n + 1][m + 1];
        
        // Initialize
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0) {
                    dp[i][j] = j;  // Insert all characters
                }
                if (j == 0) {
                    dp[i][j] = i;  // Delete all characters
                }
            }
        }
        
        // Bottom-up DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // Same character
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];  // No operation needed
                } 
                // Different character
                else {
                    int add = dp[i][j - 1] + 1;      // Insert operation
                    int del = dp[i - 1][j] + 1;      // Delete operation
                    int rep = dp[i - 1][j - 1] + 1;  // Replace operation
                    
                    dp[i][j] = Math.min(add, Math.min(del, rep));
                }
            }
        }
        
        return dp[n][m];
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case:
         * String 1: "intention"
         * String 2: "execution"
         * 
         * Expected edit distance: 5
         * 
         * Step-by-step DP table filling:
         * 
         * Characters:
         * str1: i n t e n t i o n
         * str2: e x e c u t i o n
         * 
         * Key transformations:
         * 1. i -> e (replace)
         * 2. n -> x (replace)
         * 3. t -> e (replace)
         * 4. e -> c (replace)
         * 5. n -> u (replace)
         * 6. t -> t (match)
         * 7. i -> i (match)
         * 8. o -> o (match)
         * 9. n -> n (match)
         * 
         * Total operations: 5 (replacements only needed)
         */
        
        String word1 = "vintner";
        String word2 = "writers";
        
        System.out.println("Edit Distance Problem");
        System.out.println("String 1: " + word1);
        System.out.println("String 2: " + word2);
        System.out.println("Edit Distance = " + editDistance(word1, word2));
        
        // Print DP table for understanding
        System.out.println("\nDP Table:");
        System.out.print("    \t");
        for (int j = 0; j <= word2.length(); j++) {
            if (j == 0) {
                System.out.print("\"\"\t");
            } else {
                System.out.print(word2.charAt(j - 1) + "\t");
            }
        }
        System.out.println();
        
        int n = word1.length();
        int m = word2.length();
        int dp[][] = new int[n + 1][m + 1];
        
        // Fill and display table
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                System.out.print("\"\"\t");
            } else {
                System.out.print(word1.charAt(i - 1) + "\t");
            }
            
            for (int j = 0; j <= m; j++) {
                if (i == 0) {
                    dp[i][j] = j;  // Insert operations
                } else if (j == 0) {
                    dp[i][j] = i;  // Delete operations
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];  // No operation
                } else {
                    int add = dp[i][j - 1] + 1;      // Insert
                    int del = dp[i - 1][j] + 1;      // Delete
                    int rep = dp[i - 1][j - 1] + 1;  // Replace
                    dp[i][j] = Math.min(add, Math.min(del, rep));
                }
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 1: Same strings
        String test1a = "hello";
        String test1b = "hello";
        System.out.println("Edit distance between 'hello' and 'hello': " + editDistance(test1a, test1b)); // 0
        
        // Test case 2: One empty string
        String test2a = "abc";
        String test2b = "";
        System.out.println("Edit distance between 'abc' and '': " + editDistance(test2a, test2b)); // 3
        
        // Test case 3: Simple case
        String test3a = "kitten";
        String test3b = "sitting";
        System.out.println("Edit distance between 'kitten' and 'sitting': " + editDistance(test3a, test3b)); // 3
        
        // Test case 4: Single character difference
        String test4a = "book";
        String test4b = "back";
        System.out.println("Edit distance between 'book' and 'back': " + editDistance(test4a, test4b)); // 2
        
        // Test case 5: Completely different
        String test5a = "abc";
        String test5b = "xyz";
        System.out.println("Edit distance between 'abc' and 'xyz': " + editDistance(test5a, test5b)); // 3
    }
}
