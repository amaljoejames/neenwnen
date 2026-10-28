import java.util.*;

/**
 * QUESTION: Minimum Partition Problem - Given an array of positive integers, partition it 
 * into two subsets such that the difference between the sums of the subsets is minimized.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [1, 6, 11, 5]
 * - Total sum = 23
 * - Possible partitions:
 *   1. {1,6} vs {11,5} = 7 vs 16, difference = 9
 *   2. {1,11} vs {6,5} = 12 vs 11, difference = 1 ← BEST
 *   3. {1,5} vs {6,11} = 6 vs 17, difference = 11
 *   4. {6,11} vs {1,5} = 17 vs 6, difference = 11
 * - Minimum difference: 1
 * 
 * APPROACH USED: Dynamic Programming using 0-1 Knapsack approach
 * 
 * PSEUDOCODE:
 * function minPartition(arr):
 *   n = arr.length
 *   sum = sum of all elements in arr
 *   w = sum / 2
 *   
 *   dp = int[n+1][w+1]
 *   
 *   // 0/1 Knapsack style DP
 *   for i = 1 to n:
 *     for j = 1 to w:
 *       if arr[i-1] <= j:  // Can include current element
 *         dp[i][j] = max(
 *           arr[i-1] + dp[i-1][j-arr[i-1]],  // Include
 *           dp[i-1][j]                        // Exclude
 *         )
 *       else:  // Cannot include
 *         dp[i][j] = dp[i-1][j]
 *   
 *   sum1 = dp[n][w]        // Maximum sum achievable ≤ total/2
 *   sum2 = sum - sum1       // Remaining sum
 *   return abs(sum1 - sum2) // Minimum difference
 * 
 * EXPLANATION OF STEPS:
 * 1. Key Insight: We want to find a subset with sum as close as possible to total/2
 * 2. This is equivalent to 0-1 Knapsack where:
 *    - Items = array elements
 *    - Weights = array elements
 *    - Values = array elements
 *    - Capacity = total_sum/2
 * 3. Find maximum sum achievable ≤ total/2
 * 4. The other subset will have sum = total_sum - found_sum
 * 5. Minimum difference = |found_sum - (total_sum - found_sum)|
 * 
 * DRY RUN for array [1, 6, 11, 5]:
 * 
 * Total sum = 23, Target capacity = 23/2 = 11
 * 
 * We want to find subset with maximum sum ≤ 11
 * 
 * Consider elements:
 * - 1: Can include, sum = 1
 * - 6: Can include, sum = 1 + 6 = 7
 * - 11: Can include, sum = 11 (replacing 1+6)
 * - 5: Best combination is 6 + 5 = 11
 * 
 * Maximum achievable sum ≤ 11 = 11
 * Other subset sum = 23 - 11 = 12
 * Minimum difference = |11 - 12| = 1
 * 
 * Optimal partition: {11} vs {1,6,5} or {6,5} vs {1,11}
 */
public class MinimumPartition {
    
    public static int minPartition(int arr[]) {
        int n = arr.length;
        
        // Calculate total sum
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        
        int w = sum / 2;  // Target capacity
        
        int dp[][] = new int[n + 1][w + 1];
        
        // 0/1 Knapsack style DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (arr[i - 1] <= j) {  // Current element can be included
                    dp[i][j] = Math.max(
                        arr[i - 1] + dp[i - 1][j - arr[i - 1]],  // Include element
                        dp[i - 1][j]                           // Exclude element
                    );
                } else {  // Current element too large
                    dp[i][j] = dp[i - 1][j];  // Only exclude option
                }
            }
        }
        
        int sum1 = dp[n][w];        // Maximum sum achievable ≤ total/2
        int sum2 = sum - sum1;      // Remaining sum
        return Math.abs(sum1 - sum2); // Minimum difference
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case:
         * Array: [1, 6, 11, 5]
         * 
         * Expected minimum difference: 1
         * 
         * Step-by-step process:
         * 
         * Step 1: Calculate total sum
         * Total = 1 + 6 + 11 + 5 = 23
         * Target capacity = 23/2 = 11
         * 
         * Step 2: Apply 0-1 Knapsack to find max sum ≤ 11
         * 
         * DP Table construction (simplified):
         * dp[i][j] = maximum sum using first i elements with capacity j
         * 
         * Key findings:
         * - Using {1,6}: sum = 7
         * - Using {11}: sum = 11
         * - Using {6,5}: sum = 11
         * - Using {1,5,6}: sum = 12 (exceeds capacity)
         * 
         * Maximum achievable sum ≤ 11 = 11
         * 
         * Step 3: Calculate minimum difference
         * sum1 = 11, sum2 = 23 - 11 = 12
         * Difference = |11 - 12| = 1
         * 
         * Optimal partitions:
         * - {11} vs {1,6,5} = 11 vs 12
         * - {6,5} vs {1,11} = 11 vs 12
         */
        
        int arr[] = {1, 6, 11, 5};
        
        System.out.println("Minimum Partition Problem");
        System.out.println("Array: " + Arrays.toString(arr));
        
        // Calculate total sum
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }
        System.out.println("Total sum: " + totalSum);
        System.out.println("Target capacity (total/2): " + totalSum / 2);
        
        int result = minPartition(arr);
        System.out.println("Minimum difference: " + result);
        
        // Show DP table for understanding
        System.out.println("\nDP Table (0-1 Knapsack approach):");
        System.out.print("Capacity\\Elements\t");
        int w = totalSum / 2;
        for (int j = 0; j <= w; j++) {
            System.out.print(j + "\t");
        }
        System.out.println();
        
        int n = arr.length;
        int dp[][] = new int[n + 1][w + 1];
        
        // Fill and display DP table
        for (int i = 0; i <= n; i++) {
            System.out.print("First " + i + "\t\t");
            for (int j = 0; j <= w; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (arr[i - 1] <= j) {
                    dp[i][j] = Math.max(arr[i - 1] + dp[i - 1][j - arr[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        
        // Show optimal partition
        int sum1 = dp[n][w];
        int sum2 = totalSum - sum1;
        System.out.println("\nOptimal partition:");
        System.out.println("Subset 1 sum: " + sum1);
        System.out.println("Subset 2 sum: " + sum2);
        System.out.println("Minimum difference: " + Math.abs(sum1 - sum2));
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 1: Even number of elements
        int[] test1 = {2, 2, 2, 2};
        System.out.println("Array [2,2,2,2], min difference: " + minPartition(test1)); // 0
        
        // Test case 2: All same elements
        int[] test2 = {3, 3, 3};
        System.out.println("Array [3,3,3], min difference: " + minPartition(test2)); // 3
        
        // Test case 3: Single element
        int[] test3 = {10};
        System.out.println("Array [10], min difference: " + minPartition(test3)); // 10
        
        // Test case 4: Larger array
        int[] test4 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Array [1,2,3,4,5,6,7], min difference: " + minPartition(test4)); // 1
    }
}
