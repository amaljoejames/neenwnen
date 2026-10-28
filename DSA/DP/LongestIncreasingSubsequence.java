import java.util.*;

/**
 * QUESTION: Longest Increasing Subsequence (LIS) - Given an array of integers, find the length 
 * of the longest subsequence where elements are in strictly increasing order.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [50, 3, 10, 7, 40, 80]
 * - Increasing subsequences: [3, 7, 40, 80], [3, 10, 40, 80], [50, 80]
 * - Longest Increasing Subsequence: [3, 7, 40, 80] or [3, 10, 40, 80] (length 4)
 * 
 * APPROACH USED: Convert to LCS problem by creating sorted unique array and finding LCS
 * 
 * PSEUDOCODE:
 * function lis(arr1):
 *   set = HashSet()
 *   
 *   // Store unique elements
 *   for i = 0 to arr1.length-1:
 *     set.add(arr1[i])
 *   
 *   // Convert set to array
 *   arr2 = new int[set.size()]
 *   i = 0
 *   for num in set:
 *     arr2[i] = num
 *     i++
 *   
 *   // Sort array (important)
 *   Arrays.sort(arr2)
 *   
 *   // Apply LCS
 *   return lcs(arr1, arr2)
 * 
 * function lcs(arr1, arr2):
 *   n = arr1.length, m = arr2.length
 *   dp = int[n+1][m+1]
 *   
 *   // Initialize
 *   for i = 0 to n:
 *     dp[i][0] = 0
 *   for j = 0 to m:
 *     dp[0][j] = 0
 *   
 *   // Bottom-up
 *   for i = 1 to n:
 *     for j = 1 to m:
 *       if arr1[i-1] == arr2[j-1]:
 *         dp[i][j] = dp[i-1][j-1] + 1
 *       else:
 *         dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 *   
 *   return dp[n][m]
 * 
 * EXPLANATION OF STEPS:
 * 1. Key Insight: LIS problem can be converted to LCS problem
 * 2. Create a sorted version of the array with unique elements
 * 3. Find LCS between original array and sorted array
 * 4. The LCS will be the longest increasing subsequence
 * 5. This works because:
 *    - LCS preserves relative order from original array
 *    - Sorted array ensures increasing order
 *    - Unique elements prevent duplicates in subsequence
 * 
 * DRY RUN for array [50, 3, 10, 7, 40, 80]:
 * 
 * Step 1: Extract unique elements: {50, 3, 10, 7, 40, 80}
 * Step 2: Sort: [3, 7, 10, 40, 50, 80]
 * 
 * LCS between [50, 3, 10, 7, 40, 80] and [3, 7, 10, 40, 50, 80]:
 * 
 * Original: 50, 3, 10, 7, 40, 80
 * Sorted:   3, 7, 10, 40, 50, 80
 * 
 * LCS: 3, 10, 40, 80 (length 4)
 * Alternative LCS: 3, 7, 40, 80 (length 4)
 * 
 * Both are valid LIS of length 4
 */
public class LongestIncreasingSubsequence {
    
    // LCS function for two arrays
    public static int lcs(int arr1[], int arr2[]) {
        int n = arr1.length;
        int m = arr2.length;
        
        int dp[][] = new int[n + 1][m + 1];
        
        // Initialize
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < m + 1; j++) {
            dp[0][j] = 0;
        }
        
        // Bottom-up DP
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    int ans1 = dp[i - 1][j];
                    int ans2 = dp[i][j - 1];
                    dp[i][j] = Math.max(ans1, ans2);
                }
            }
        }
        
        return dp[n][m];
    }
    
    // Main LIS function
    public static int lis(int arr1[]) {
        HashSet<Integer> set = new HashSet<>();
        
        // Store unique elements
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }
        
        // Convert set to array
        int arr2[] = new int[set.size()];
        int i = 0;
        for (int num : set) {
            arr2[i] = num;
            i++;
        }
        
        // Sort array (important)
        Arrays.sort(arr2);
        
        // Apply LCS
        return lcs(arr1, arr2);
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case:
         * Array: [50, 3, 10, 7, 40, 80]
         * 
         * Expected LIS length: 4
         * Possible LIS: [3, 7, 40, 80] or [3, 10, 40, 80]
         * 
         * Step-by-step process:
         * 
         * Step 1: Extract unique elements
         * Original: [50, 3, 10, 7, 40, 80]
         * Unique: {50, 3, 10, 7, 40, 80}
         * 
         * Step 2: Sort unique elements
         * Sorted: [3, 7, 10, 40, 50, 80]
         * 
         * Step 3: Find LCS between original and sorted arrays
         * 
         * DP Table construction:
         * Original: 50, 3, 10, 7, 40, 80
         * Sorted:   3, 7, 10, 40, 50, 80
         * 
         * Key matches in LCS:
         * - 3 matches at position 2 in original, 1 in sorted
         * - 10 matches at position 3 in original, 3 in sorted  
         * - 40 matches at position 5 in original, 4 in sorted
         * - 80 matches at position 6 in original, 6 in sorted
         * 
         * LCS: [3, 10, 40, 80] or [3, 7, 40, 80]
         * Length: 4
         */
        
        int arr[] = {50, 3, 10, 7, 40, 80};
        
        System.out.println("Longest Increasing Subsequence Problem");
        System.out.println("Array: " + Arrays.toString(arr));
        
        int result = lis(arr);
        System.out.println("Length of LIS = " + result);
        
        // Show the process
        System.out.println("\nProcess:");
        System.out.println("Step 1: Extract unique elements");
        HashSet<Integer> unique = new HashSet<>();
        for (int num : arr) {
            unique.add(num);
        }
        System.out.println("Unique elements: " + unique);
        
        System.out.println("\nStep 2: Sort unique elements");
        int sorted[] = new int[unique.size()];
        int idx = 0;
        for (int num : unique) {
            sorted[idx++] = num;
        }
        Arrays.sort(sorted);
        System.out.println("Sorted array: " + Arrays.toString(sorted));
        
        System.out.println("\nStep 3: Find LCS between original and sorted arrays");
        System.out.println("Original: " + Arrays.toString(arr));
        System.out.println("Sorted:   " + Arrays.toString(sorted));
        System.out.println("LCS length = " + lcs(arr, sorted));
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 1: Already sorted
        int[] test1 = {1, 2, 3, 4, 5};
        System.out.println("Array [1,2,3,4,5], LIS length: " + lis(test1)); // 5
        
        // Test case 2: Reverse sorted
        int[] test2 = {5, 4, 3, 2, 1};
        System.out.println("Array [5,4,3,2,1], LIS length: " + lis(test2)); // 1
        
        // Test case 3: All same elements
        int[] test3 = {2, 2, 2, 2};
        System.out.println("Array [2,2,2,2], LIS length: " + lis(test3)); // 1
        
        // Test case 4: Mixed
        int[] test4 = {10, 22, 9, 33, 21, 50, 41, 60};
        System.out.println("Array [10,22,9,33,21,50,41,60], LIS length: " + lis(test4)); // 5
        
        // Test case 5: Empty array
        int[] test5 = {};
        System.out.println("Array [], LIS length: " + lis(test5)); // 0
    }
}
