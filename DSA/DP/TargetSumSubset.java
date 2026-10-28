import java.util.*;

/**
 * QUESTION: Target Sum Subset Problem - Given an array of positive integers and a target sum,
 * determine if there exists a subset of the array elements that sum up to the target sum.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [4, 2, 7, 1, 3]
 * - Target sum: 10
 * - Possible subsets that sum to 10: [7, 3], [4, 2, 3, 1], [2, 7, 1]
 * - Answer: true (subset exists)
 * 
 * APPROACH USED: Dynamic Programming with Tabulation (Bottom-Up)
 * 
 * PSEUDOCODE:
 * function targetSumSubset(sum, arr):
 *   n = arr.length
 *   dp = boolean[n+1][sum+1]
 *   
 *   // Initialize: sum 0 is always possible with empty subset
 *   for i = 0 to n:
 *     dp[i][0] = true
 *   
 *   // Fill DP table
 *   for i = 1 to n:
 *     for j = 1 to sum:
 *       if arr[i-1] <= j:  // Current element can be included
 *         dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]]
 *       else:  // Current element too large
 *         dp[i][j] = dp[i-1][j]
 *   
 *   return dp[n][sum]
 * 
 * EXPLANATION OF STEPS:
 * 1. DP Table: dp[i][j] represents whether sum j is possible using first i elements
 * 2. Initialization: 
 *    - dp[i][0] = true for all i (sum 0 is always possible with empty subset)
 *    - dp[0][j] = false for j > 0 (no elements, positive sum impossible)
 * 3. For each element and each possible sum:
 *    - If current element <= target sum: Two choices
 *      a) Exclude current element: dp[i-1][j]
 *      b) Include current element: dp[i-1][j-arr[i-1]]
 *      Result = OR of both choices
 *    - If current element > target sum: Cannot include, only exclude option
 * 4. Final answer: dp[n][sum]
 * 
 * DRY RUN for array [4, 2, 7, 1, 3], sum = 10:
 * 
 * DP Table construction:
 * dp[i][j] = can we make sum j using first i elements?
 * 
 * Initial: dp[0][0] = true, dp[0][1..10] = false
 * 
 * After element 4:
 * dp[1][4] = true, dp[1][8] = true (4+4, but we only have one 4)
 * Actually: dp[1][4] = true (using element 4)
 * 
 * After element 2:
 * dp[2][2] = true, dp[2][4] = true, dp[2][6] = true (4+2)
 * 
 * After element 7:
 * dp[3][7] = true, dp[3][9] = true (7+2)
 * 
 * After element 1:
 * dp[4][1] = true, dp[4][3] = true (2+1), dp[4][5] = true (4+1)
 * 
 * After element 3:
 * dp[5][10] = true (7+3)
 * 
 * Final answer: dp[5][10] = true
 */
public class TargetSumSubset {
    
    public static boolean targetSumSubset(int sum, int arr[]) {
        int n = arr.length;
        boolean dp[][] = new boolean[n + 1][sum + 1];
        
        // Initialization: sum 0 is always possible with empty subset
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        
        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j) {  // Current element can be included
                    // Exclude OR include current element
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else {  // Current element too large, cannot include
                    dp[i][j] = dp[i - 1][j];  // Only exclude option
                }
            }
        }
        
        return dp[n][sum];
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case:
         * Array: [4, 2, 7, 1, 3]
         * Target sum: 10
         * 
         * Expected result: true
         * 
         * Step-by-step DP table filling:
         * 
         * Elements considered: 4, 2, 7, 1, 3
         * Target sums: 0 to 10
         * 
         * After processing all elements:
         * dp[5][10] should be true because:
         * - Subset [7, 3] sums to 10
         * - Subset [4, 2, 3, 1] also sums to 10
         * - Subset [2, 7, 1] also sums to 10
         */
        
        int numbers[] = {4, 2, 7, 1, 3};
        int targetSum = 10;
        
        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println("Target sum: " + targetSum);
        System.out.println("Is subset possible: " + targetSumSubset(targetSum, numbers));
        
        // Print DP table for verification
        System.out.println("\nDP Table (T = True, F = False):");
        System.out.print("Sum\\Elements\t");
        for (int j = 0; j <= targetSum; j++) {
            System.out.print(j + "\t");
        }
        System.out.println();
        
        int n = numbers.length;
        boolean dp[][] = new boolean[n + 1][targetSum + 1];
        
        // Initialize
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        
        // Fill table
        for (int i = 1; i <= n; i++) {
            System.out.print("First " + i + "\t\t");
            for (int j = 0; j <= targetSum; j++) {
                if (j == 0) {
                    System.out.print("T\t");
                } else if (i == 0) {
                    System.out.print("F\t");
                } else if (numbers[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - numbers[i - 1]];
                    System.out.print(dp[i][j] ? "T\t" : "F\t");
                } else {
                    dp[i][j] = dp[i - 1][j];
                    System.out.print(dp[i][j] ? "T\t" : "F\t");
                }
            }
            System.out.println();
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        int[] test1 = {1, 2, 3};
        System.out.println("Array [1,2,3], sum 6: " + targetSumSubset(6, test1)); // true
        
        int[] test2 = {1, 2, 5};
        System.out.println("Array [1,2,5], sum 4: " + targetSumSubset(4, test2)); // false
        
        int[] test3 = {2, 2, 2, 2};
        System.out.println("Array [2,2,2,2], sum 8: " + targetSumSubset(8, test3)); // true
    }
}
