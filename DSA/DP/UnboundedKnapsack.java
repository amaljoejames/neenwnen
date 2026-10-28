import java.util.*;

/**
 * QUESTION: Unbounded Knapsack Problem - Given weights and values of n items, put these items 
 * in a knapsack of capacity W to get the maximum total value. Unlike 0-1 knapsack, here each 
 * item can be selected multiple times (unlimited supply).
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Items: values [15,14,10,45,30], weights [2,5,1,3,4]
 * - Knapsack capacity W = 7
 * - Since items can be repeated, we can take multiple of the same item
 * - Optimal solution might include: 7 times item with weight 1, value 10 = 70
 * - Or: 2 times item with weight 3 (value 45) + 1 time item with weight 1 (value 10) = 100
 * 
 * APPROACH USED: Dynamic Programming with Tabulation (Bottom-Up)
 * 
 * PSEUDOCODE:
 * function unboundedKnapsack(W, wt[], val[]):
 *   n = val.length
 *   dp = int[n+1][W+1]
 *   
 *   // Initialize
 *   for i = 0 to n:
 *     dp[i][0] = 0
 *   for j = 0 to W:
 *     dp[0][j] = 0
 *   
 *   // Fill DP table
 *   for i = 1 to n:
 *     for j = 1 to W:
 *       if wt[i-1] <= j:  // Current item can fit
 *         dp[i][j] = max(val[i-1] + dp[i][j-wt[i-1]],  // Include item (can reuse)
 *                        dp[i-1][j])                   // Exclude item
 *       else:  // Current item too heavy
 *         dp[i][j] = dp[i-1][j]
 *   
 *   return dp[n][W]
 * 
 * EXPLANATION OF STEPS:
 * 1. DP Table: dp[i][j] represents maximum value using first i items with capacity j
 * 2. Initialization: dp[i][0] = 0 and dp[0][j] = 0 (no capacity or no items = 0 value)
 * 3. Key difference from 0-1 knapsack: When including an item, we use dp[i][j-wt[i-1]] instead of dp[i-1][j-wt[i-1]]
 *    - This allows reusing the same item multiple times
 * 4. For each item and capacity:
 *    - If item can fit: Choose between including (and possibly reusing) or excluding
 *    - If item too heavy: Can only exclude
 * 5. Final answer: dp[n][W]
 * 
 * DRY RUN for example:
 * Items: val=[15,14,10,45,30], wt=[2,5,1,3,4], W=7
 * 
 * Key insight: Since item with weight 1 has value 10, we can fill capacity 7 with 7 such items
 * Total value = 7 * 10 = 70
 * 
 * But better: Use item with weight 3, value 45 twice + item with weight 1, value 10
 * Total weight = 3 + 3 + 1 = 7, Total value = 45 + 45 + 10 = 100
 * 
 * Even better: Use item with weight 2, value 15 three times + item with weight 1, value 10
 * Total weight = 2 + 2 + 2 + 1 = 7, Total value = 15 + 15 + 15 + 10 = 55
 * 
 * Best solution: Use item with weight 3, value 45 twice + item with weight 1, value 10 = 100
 */
public class UnboundedKnapsack {
    
    public static int unboundedKnapsack(int W, int wt[], int val[]) {
        int n = val.length;
        int dp[][] = new int[n + 1][W + 1];
        
        // Initialize DP table
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 0;  // Zero capacity = zero value
        }
        for (int j = 0; j < W + 1; j++) {
            dp[0][j] = 0;  // No items = zero value
        }
        
        // Fill the DP array
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                if (wt[i - 1] <= j) {  // Current item can fit
                    // Key difference: dp[i][j-wt[i-1]] allows reusing the same item
                    dp[i][j] = Math.max(
                        val[i - 1] + dp[i][j - wt[i - 1]],  // Include item (can reuse)
                        dp[i - 1][j]                         // Exclude item
                    );
                } else {  // Current item too heavy, cannot include
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[n][W];
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case:
         * Values: [15, 14, 10, 45, 30]
         * Weights: [2, 5, 1, 3, 4]
         * Capacity W = 7
         * 
         * Expected maximum value = 100
         * Optimal selection: 2 × item(weight=3,value=45) + 1 × item(weight=1,value=10)
         * Total weight = 3 + 3 + 1 = 7
         * Total value = 45 + 45 + 10 = 100
         * 
         * DP Table filling process:
         * dp[i][j] represents maximum value using first i items with capacity j
         * 
         * Key insight: Unlike 0-1 knapsack, when we include an item,
         * we can still use it again (dp[i][j-wt[i-1]] instead of dp[i-1][j-wt[i-1]])
         */
        
        int val[] = {15, 14, 10, 45, 30};
        int wt[] = {2, 5, 1, 3, 4};
        int W = 7;
        
        System.out.println("Unbounded Knapsack Problem");
        System.out.println("Values: " + Arrays.toString(val));
        System.out.println("Weights: " + Arrays.toString(wt));
        System.out.println("Capacity: " + W);
        System.out.println("Maximum value: " + unboundedKnapsack(W, wt, val));
        
        // Print DP table for understanding
        System.out.println("\nDP Table:");
        System.out.print("Capacity\\Items\t");
        for (int j = 0; j <= W; j++) {
            System.out.print(j + "\t");
        }
        System.out.println();
        
        int n = val.length;
        int dp[][] = new int[n + 1][W + 1];
        
        // Fill and display DP table
        for (int i = 0; i <= n; i++) {
            System.out.print("First " + i + "\t\t");
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(val[i - 1] + dp[i][j - wt[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 1: Coin change like problem
        int[] coins = {1, 2, 5};
        int[] coinValues = {1, 2, 5};
        System.out.println("Coins [1,2,5], capacity 11: " + unboundedKnapsack(11, coins, coinValues));
        // Should be 11 (11 × 1 or 5 + 6 × 1, etc.)
        
        // Test case 2: All items same weight
        int[] weights2 = {2, 2, 2};
        int[] values2 = {10, 20, 30};
        System.out.println("Values [10,20,30], weights [2,2,2], capacity 6: " + unboundedKnapsack(6, weights2, values2));
        // Should be 90 (3 × 30)
    }
}
