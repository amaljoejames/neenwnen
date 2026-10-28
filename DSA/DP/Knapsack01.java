import java.util.*;

/**
 * QUESTION: 0-1 Knapsack Problem - Given weights and values of n items, put these items in a knapsack 
 * of capacity W to get the maximum total value in the knapsack. Each item can either be included 
 * completely or not included at all (0-1 property).
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Items: [15,14,10,45,30] (values)
 * - Weights: [2,5,1,3,4]
 * - Knapsack capacity W = 7
 * - Maximum value = 75 (items with weights 2+1+4 and values 15+10+30)
 * 
 * APPROACH USED: Dynamic Programming with both Memoization (Top-Down) and Tabulation (Bottom-Up)
 * 
 * PSEUDOCODE (Memoization):
 * function knapsack(W, wt[], val[], n, dp[][]):
 *   if W == 0 or n == 0: return 0
 *   if dp[n][W] != -1: return dp[n][W]
 *   if wt[n-1] <= W:  // Current item can fit
 *     dp[n][W] = max(val[n-1] + knapsack(W-wt[n-1], wt, val, n-1, dp),  // Include item
 *                    knapsack(W, wt, val, n-1, dp))                      // Exclude item
 *     return dp[n][W]
 *   else:  // Current item too heavy
 *     dp[n][W] = knapsack(W, wt, val, n-1, dp)
 *     return dp[n][W]
 * 
 * EXPLANATION OF STEPS:
 * 1. Base Cases: 
 *    - If capacity W = 0 or no items left (n = 0), return 0
 * 2. Memoization Check: If result already computed, return it
 * 3. Decision Making:
 *    - If current item weight <= capacity: We have two choices
 *      a) Include the item: value + knapsack(remaining capacity, remaining items)
 *      b) Exclude the item: knapsack(same capacity, remaining items)
 *      Take maximum of both choices
 *    - If current item weight > capacity: Cannot include, only exclude option
 * 4. Store result in DP table and return
 * 
 * DRY RUN for example:
 * Items: val=[15,14,10,45,30], wt=[2,5,1,3,4], W=7, n=5
 * 
 * Consider last item (30, weight 4):
 * - Can include: 30 + knapsack(W=3, n=4)
 * - Can exclude: knapsack(W=7, n=4)
 * 
 * For knapsack(W=3, n=4):
 * - Item (45, weight 3) can be included: 45 + knapsack(W=0, n=3) = 45
 * - Or exclude: knapsack(W=3, n=3)
 * 
 * Final optimal solution: Include items with weights 4, 2, 1 (values 30, 15, 10) = 55
 * Actually, better solution: Include items with weights 3, 2, 1 (values 45, 15, 10) = 70
 * Best solution: Include items with weights 4, 3 (values 30, 45) = 75
 */
public class Knapsack01 {
    
    // Memoization approach
    public static int knapsack(int W, int wt[], int val[], int n, int dp[][]) {
        if (W == 0 || n == 0) {
            return 0;  // Base case: no capacity or no items
        }
        
        if (dp[n][W] != -1) {
            return dp[n][W];  // Return memoized result
        }
        
        if (wt[n-1] <= W) {  // Current item can fit in knapsack
            dp[n][W] = Math.max(
                val[n-1] + knapsack(W - wt[n-1], wt, val, n-1, dp),  // Include item
                knapsack(W, wt, val, n-1, dp)                         // Exclude item
            );
            return dp[n][W];
        } else {  // Current item too heavy, cannot include
            dp[n][W] = knapsack(W, wt, val, n-1, dp);
            return dp[n][W];
        }
    }
    
    // Tabulation approach
    public static int knapsackTab(int W, int wt[], int val[]) {
        int n = val.length;
        int dp[][] = new int[n+1][W+1];
        
        // Initialization: 0 capacity or 0 items = 0 profit
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= W; j++) {
            dp[0][j] = 0;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                int v = val[i-1];  // Current item value
                int w = wt[i-1];  // Current item weight
                
                if (w <= j) {  // Item can fit
                    int incProfit = v + dp[i-1][j-w];  // Include item
                    int excProfit = dp[i-1][j];        // Exclude item
                    dp[i][j] = Math.max(incProfit, excProfit);
                } else {  // Item too heavy
                    dp[i][j] = dp[i-1][j];  // Only exclude option
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
         * Expected maximum value = 75
         * Optimal selection: Items with weights 3 and 4 (values 45 and 30)
         * 
         * DP Table filling process:
         * dp[i][j] represents maximum value using first i items with capacity j
         * 
         * Final dp[5][7] = 75
         */
        
        int val[] = {15, 14, 10, 45, 30};
        int wt[] = {2, 5, 1, 3, 4};
        int W = 7;
        
        // Memoization approach
        int dp[][] = new int[val.length + 1][W + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        
        System.out.println("Memoization approach:");
        System.out.println("Maximum value = " + knapsack(W, wt, val, val.length, dp));
        
        System.out.println("\nTabulation approach:");
        System.out.println("Maximum value = " + knapsackTab(W, wt, val));
        
        // Print DP table for tabulation approach
        System.out.println("\nDP Table (Tabulation):");
        int n = val.length;
        int dpTab[][] = new int[n+1][W+1];
        
        // Fill table again for display
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    dpTab[i][j] = 0;
                } else if (wt[i-1] <= j) {
                    dpTab[i][j] = Math.max(val[i-1] + dpTab[i-1][j-wt[i-1]], dpTab[i-1][j]);
                } else {
                    dpTab[i][j] = dpTab[i-1][j];
                }
                System.out.print(dpTab[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
