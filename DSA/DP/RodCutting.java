import java.util.*;

/**
 * QUESTION: Rod Cutting Problem - Given a rod of length L and prices for different lengths,
 * find the maximum revenue obtainable by cutting the rod into pieces and selling them.
 * This is essentially the same as Unbounded Knapsack where length = weight and price = value.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Rod lengths: [1, 2, 3, 4, 5, 6, 7, 8]
 * - Prices: [1, 5, 8, 9, 10, 17, 17, 20]
 * - Total rod length: 8
 * - Optimal solution: Cut into pieces of lengths 2 and 6
 * - Revenue = price[2] + price[6] = 5 + 17 = 22
 * - Or: Cut into lengths 2, 2, 2, 2 = 4 × 5 = 20
 * - Or: Don't cut at all = price[8] = 20
 * - Best: lengths 2 and 6 = 22
 * 
 * APPROACH USED: Dynamic Programming with Tabulation (Bottom-Up)
 * 
 * PSEUDOCODE:
 * function rodCutting(length[], price[], totRod):
 *   n = price.length
 *   dp = int[n+1][totRod+1]
 *   
 *   // Initialize base cases
 *   for i = 0 to n:
 *     for j = 0 to totRod:
 *       if i == 0 or j == 0:
 *         dp[i][j] = 0
 *   
 *   // Fill DP table
 *   for i = 1 to n:
 *     for j = 1 to totRod:
 *       if length[i-1] <= j:  // Current piece can fit
 *         dp[i][j] = max(
 *           price[i-1] + dp[i][j-length[i-1]],  // Include this piece (can reuse)
 *           dp[i-1][j]                          // Exclude this piece
 *         )
 *       else:  // Current piece too long
 *         dp[i][j] = dp[i-1][j]
 *   
 *   return dp[n][totRod]
 * 
 * EXPLANATION OF STEPS:
 * 1. DP Table: dp[i][j] represents maximum revenue using first i piece types for rod length j
 * 2. Initialization: dp[0][j] = 0 and dp[i][0] = 0 (no pieces or no length = 0 revenue)
 * 3. Key insight: This is identical to Unbounded Knapsack
 *    - length[i-1] is like weight, price[i-1] is like value
 *    - We can use the same piece type multiple times
 * 4. For each piece type and rod length:
 *    - If piece can fit: Choose between including (and possibly reusing) or excluding
 *    - If piece too long: Can only exclude
 * 5. Final answer: dp[n][totRod]
 * 
 * DRY RUN for example:
 * Lengths: [1,2,3,4,5,6,7,8], Prices: [1,5,8,9,10,17,17,20], totRod = 8
 * 
 * Consider piece length 2, price 5:
 * - Can use it 4 times: 4 × 5 = 20
 * 
 * Consider piece length 6, price 17:
 * - Use once + length 2 piece: 17 + 5 = 22
 * 
 * Consider piece length 8, price 20:
 * - Use once: 20
 * 
 * Best solution: 22 (lengths 2 and 6)
 */
public class RodCutting {
    
    public static int rodCutting(int length[], int price[], int totRod) {
        int n = price.length;
        int dp[][] = new int[n + 1][totRod + 1];
        
        // Base case initialization
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < totRod + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;  // No pieces or no length = 0 revenue
                }
            }
        }
        
        // Fill DP table
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < totRod + 1; j++) {
                // valid case: current piece can fit in remaining length
                if (length[i - 1] <= j) {
                    dp[i][j] = Math.max(
                        price[i - 1] + dp[i][j - length[i - 1]],  // Include this piece (can reuse)
                        dp[i - 1][j]                              // Exclude this piece
                    );
                } else {  // invalid case: current piece too long
                    dp[i][j] = dp[i - 1][j];  // Only exclude option
                }
            }
        }
        
        return dp[n][totRod];
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case:
         * Lengths: [1, 2, 3, 4, 5, 6, 7, 8]
         * Prices:  [1, 5, 8, 9, 10, 17, 17, 20]
         * Total rod length: 8
         * 
         * Expected maximum revenue: 22
         * Optimal cutting: length 2 (price 5) + length 6 (price 17) = 22
         * 
         * Alternative solutions:
         * - Don't cut: price[8] = 20
         * - Four pieces of length 2: 4 × 5 = 20
         * - Two pieces of length 4: 2 × 9 = 18
         * - Length 3 + length 5: 8 + 10 = 18
         * 
         * DP Table filling process:
         * dp[i][j] represents maximum revenue using first i piece types for rod length j
         * 
         * Key insight: This is exactly Unbounded Knapsack where:
         * - length[i-1] = weight
         * - price[i-1] = value
         * - totRod = knapsack capacity
         */
        
        int length[] = {1, 2, 3, 4, 5, 6, 7, 8};
        int price[] = {1, 5, 8, 9, 10, 17, 17, 20};
        int rodLength = 8;
        
        System.out.println("Rod Cutting Problem");
        System.out.println("Lengths: " + Arrays.toString(length));
        System.out.println("Prices: " + Arrays.toString(price));
        System.out.println("Total rod length: " + rodLength);
        System.out.println("Maximum revenue: " + rodCutting(length, price, rodLength));
        
        // Print DP table for understanding
        System.out.println("\nDP Table:");
        System.out.print("Length\\Pieces\t");
        for (int j = 0; j <= rodLength; j++) {
            System.out.print(j + "\t");
        }
        System.out.println();
        
        int n = price.length;
        int dp[][] = new int[n + 1][rodLength + 1];
        
        // Fill and display DP table
        for (int i = 0; i <= n; i++) {
            System.out.print("First " + i + "\t\t");
            for (int j = 0; j <= rodLength; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (length[i - 1] <= j) {
                    dp[i][j] = Math.max(price[i - 1] + dp[i][j - length[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        
        // Show optimal cutting strategy
        System.out.println("\nOptimal cutting strategy analysis:");
        System.out.println("Option 1: Don't cut = price[8] = 20");
        System.out.println("Option 2: 4 × length 2 = 4 × 5 = 20");
        System.out.println("Option 3: 2 × length 4 = 2 × 9 = 18");
        System.out.println("Option 4: length 2 + length 6 = 5 + 17 = 22 ← BEST");
        System.out.println("Option 5: length 3 + length 5 = 8 + 10 = 18");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 1: Simple case
        int[] len1 = {1, 2, 3};
        int[] price1 = {1, 3, 4};
        System.out.println("Lengths [1,2,3], prices [1,3,4], rod 4: " + rodCutting(len1, price1, 4));
        // Should be 4 (2+2 or 1+3)
        
        // Test case 2: All same price
        int[] len2 = {1, 2, 3, 4};
        int[] price2 = {5, 5, 5, 5};
        System.out.println("All prices 5, rod 6: " + rodCutting(len2, price2, 6));
        // Should be 30 (6 × 1)
    }
}
