import java.util.*;

/**
 * QUESTION: Coin Change Problem - Given a set of coin denominations and a target sum,
 * find the number of ways to make that sum using unlimited coins of each denomination.
 * Order of coins doesn't matter (combinations, not permutations).
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Coins: [2, 5, 3, 6]
 * - Target sum: 10
 * - Possible ways:
 *   1. 5 + 5
 *   2. 2 + 2 + 2 + 2 + 2
 *   3. 2 + 2 + 3 + 3
 *   4. 2 + 2 + 6
 *   5. 2 + 3 + 5
 * - Answer: 5 ways
 * 
 * APPROACH USED: Dynamic Programming with Tabulation (Bottom-Up)
 * 
 * PSEUDOCODE:
 * function coinChange(coins[], sum):
 *   n = coins.length
 *   dp = int[n+1][sum+1]
 *   
 *   // Initialization
 *   for i = 0 to n:
 *     dp[i][0] = 1  // One way to make sum 0 (use no coins)
 *   for j = 1 to sum:
 *     dp[0][j] = 0  // No way to make positive sum with 0 coins
 *   
 *   // Fill DP table
 *   for i = 1 to n:
 *     for j = 1 to sum:
 *       if coins[i-1] <= j:  // Current coin can be used
 *         dp[i][j] = dp[i][j-coins[i-1]] + dp[i-1][j]
 *         // dp[i][j-coins[i-1]]: Use current coin (can reuse)
 *         // dp[i-1][j]: Don't use current coin
 *       else:  // Current coin too large
 *         dp[i][j] = dp[i-1][j]
 *   
 *   return dp[n][sum]
 * 
 * EXPLANATION OF STEPS:
 * 1. DP Table: dp[i][j] represents number of ways to make sum j using first i coin types
 * 2. Initialization: 
 *    - dp[i][0] = 1 (one way to make sum 0: use no coins)
 *    - dp[0][j] = 0 for j > 0 (no coins, no way to make positive sum)
 * 3. For each coin type and each sum:
 *    - If coin value <= target sum: Two choices
 *      a) Use current coin: dp[i][j-coin[i-1]] (can reuse same coin)
 *      b) Don't use current coin: dp[i-1][j]
 *      Total ways = sum of both choices
 *    - If coin value > target sum: Cannot use, only exclude option
 * 4. Final answer: dp[n][sum]
 * 
 * DRY RUN for coins [2,5,3,6], sum = 10:
 * 
 * DP Table construction:
 * dp[i][j] = number of ways to make sum j using first i coin types
 * 
 * After processing coin 2:
 * dp[1][2] = 1, dp[1][4] = 1, dp[1][6] = 1, dp[1][8] = 1, dp[1][10] = 1
 * (Only using 2's: 2+2+2+2+2 = 10)
 * 
 * After processing coin 5:
 * dp[2][5] = 1, dp[2][7] = 1, dp[2][10] = 2 (5+5 and 2+2+2+2+2)
 * 
 * After processing coin 3:
 * dp[3][3] = 1, dp[3][6] = 2 (2+2+2 and 3+3), dp[3][9] = 2, dp[3][10] = 4
 * 
 * After processing coin 6:
 * dp[4][6] = 3, dp[4][8] = 2, dp[4][10] = 5
 * 
 * Final answer: dp[4][10] = 5 ways
 */
public class CoinChange {
    
    public static int coinChange(int coins[], int sum) {
        int n = coins.length;
        int dp[][] = new int[n + 1][sum + 1];
        
        // Initialization
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;  // One way to make sum 0 (using no coins)
        }
        for (int j = 1; j <= sum; j++) {
            dp[0][j] = 0;  // No way to make positive sum with 0 coins
        }
        
        // O(n*sum) time complexity
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (coins[i - 1] <= j) {  // Current coin can be used
                    // dp[i][j-coins[i-1]]: Use current coin (can reuse)
                    // dp[i-1][j]: Don't use current coin
                    dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
                } else {  // Current coin too large
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[n][sum];
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case:
         * Coins: [2, 5, 3, 6]
         * Target sum: 10
         * 
         * Expected result: 5 ways
         * 
         * The 5 ways are:
         * 1. 5 + 5
         * 2. 2 + 2 + 2 + 2 + 2
         * 3. 2 + 2 + 3 + 3
         * 4. 2 + 2 + 6
         * 5. 2 + 3 + 5
         * 
         * DP Table filling process:
         * dp[i][j] represents number of ways to make sum j using first i coin types
         * 
         * Key insight: dp[i][j-coins[i-1]] allows unlimited use of current coin
         * dp[i-1][j] represents ways without using current coin
         */
        
        int coins[] = {2, 5, 3, 6};
        int sum = 10;
        
        System.out.println("Coin Change Problem");
        System.out.println("Coins: " + Arrays.toString(coins));
        System.out.println("Target sum: " + sum);
        System.out.println("Number of ways: " + coinChange(coins, sum));
        
        // Print DP table for verification
        System.out.println("\nDP Table:");
        System.out.print("Sum\\Coins\t");
        for (int j = 0; j <= sum; j++) {
            System.out.print(j + "\t");
        }
        System.out.println();
        
        int n = coins.length;
        int dp[][] = new int[n + 1][sum + 1];
        
        // Initialize
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        
        // Fill and display table
        for (int i = 1; i <= n; i++) {
            System.out.print("First " + i + "\t\t");
            for (int j = 0; j <= sum; j++) {
                if (j == 0) {
                    System.out.print(dp[i][j] + "\t");
                } else if (i == 0) {
                    System.out.print("0\t");
                } else if (coins[i - 1] <= j) {
                    dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
                    System.out.print(dp[i][j] + "\t");
                } else {
                    dp[i][j] = dp[i - 1][j];
                    System.out.print(dp[i][j] + "\t");
                }
            }
            System.out.println();
        }
        
        // Show all combinations for verification
        System.out.println("\nAll combinations for sum " + sum + ":");
        System.out.println("1. 5 + 5");
        System.out.println("2. 2 + 2 + 2 + 2 + 2");
        System.out.println("3. 2 + 2 + 3 + 3");
        System.out.println("4. 2 + 2 + 6");
        System.out.println("5. 2 + 3 + 5");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 1: Standard US coins
        int[] usCoins = {1, 5, 10, 25};
        System.out.println("US coins [1,5,10,25], sum 30: " + coinChange(usCoins, 30));
        
        // Test case 2: Only one coin type
        int[] singleCoin = {2};
        System.out.println("Single coin [2], sum 8: " + coinChange(singleCoin, 8));
        
        // Test case 3: No solution possible
        int[] noSol = {5, 7};
        System.out.println("Coins [5,7], sum 3: " + coinChange(noSol, 3));
    }
}
