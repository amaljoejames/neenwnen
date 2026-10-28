import java.util.*;

/**
 * QUESTION: Maximum Subarray Sum - Find the maximum sum of any contiguous subarray.
 * This is the classic Kadane's Algorithm problem.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [-2, -3, 4, -1, -2, 1, 5, -3]
 * - Subarray with maximum sum: [4, -1, -2, 1, 5] = 7
 * - Other subarrays:
 *   - [4] = 4
 *   - [4, -1] = 3
 *   - [4, -1, -2] = 1
 *   - [4, -1, -2, 1] = 2
 *   - [4, -1, -2, 1, 5] = 7 (maximum)
 * - Maximum sum: 7
 * 
 * APPROACH USED: Kadane's Algorithm (O(n) solution)
 * 
 * PSEUDOCODE:
 * function maxSubarraySum(arr):
 *   maxSum = Integer.MIN_VALUE
 *   currentSum = 0
 *   
 *   for i = 0 to arr.length-1:
 *     currentSum = currentSum + arr[i]
 *     
 *     if currentSum > maxSum:
 *       maxSum = currentSum
 *     
 *     if currentSum < 0:
 *       currentSum = 0
 *   
 *   return maxSum
 * 
 * EXPLANATION OF STEPS:
 * 1. Initialize maxSum to minimum value, currentSum to 0
 * 2. Add current element to currentSum
 * 3. Update maxSum if currentSum is larger
 * 4. Reset currentSum to 0 if it becomes negative
 * 5. A negative currentSum cannot contribute to future positive sums
 * 6. Time complexity: O(n), Space complexity: O(1)
 * 
 * DRY RUN for array = [-2, -3, 4, -1, -2, 1, 5, -3]:
 * 
 * i = 0: currentSum = -2, maxSum = -2, currentSum < 0 → currentSum = 0
 * i = 1: currentSum = -3, maxSum = -2, currentSum < 0 → currentSum = 0
 * i = 2: currentSum = 4, maxSum = 4, currentSum >= 0
 * i = 3: currentSum = 3, maxSum = 4, currentSum >= 0
 * i = 4: currentSum = 1, maxSum = 4, currentSum >= 0
 * i = 5: currentSum = 2, maxSum = 4, currentSum >= 0
 * i = 6: currentSum = 7, maxSum = 7, currentSum >= 0
 * i = 7: currentSum = 4, maxSum = 7, currentSum >= 0
 * 
 * Final maxSum = 7
 */
public class MaxSubarraySum {
    
    // Brute Force Approach - O(n³)
    public static void maxSubarraySumBruteForce(int arr[]) {
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int curr = 0;
                for (int k = i; k <= j; k++) {
                    curr += arr[k];
                }
                if (max < curr) {
                    max = curr;
                }
            }
        }
        System.out.println("Brute Force - Max sum: " + max);
    }
    
    // Prefix Sum Approach - O(n²)
    public static void maxSubarraySumPrefix(int arr[]) {
        int maxSum = Integer.MIN_VALUE;
        int prefix[] = new int[arr.length];
        prefix[0] = arr[0];
        
        // Build prefix array
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }
        
        // Find maximum subarray sum
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int currSum = i == 0 ? prefix[j] : prefix[j] - prefix[i - 1];
                if (maxSum < currSum) {
                    maxSum = currSum;
                }
            }
        }
        System.out.println("Prefix Sum - Max sum: " + maxSum);
    }
    
    // Kadane's Algorithm - O(n)
    public static void maxSubarraySumKadane(int arr[]) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            
            // Update maxSum if currentSum is greater
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            
            // Reset currentSum to 0 if it becomes negative
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        
        System.out.println("Kadane's Algorithm - Max sum: " + maxSum);
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [-2, -3, 4, -1, -2, 1, 5, -3]
         * Expected result: 7 (subarray [4, -1, -2, 1, 5])
         * 
         * Step-by-step using Kadane's Algorithm:
         * 
         * i = 0: currentSum = -2, maxSum = -2, reset currentSum = 0
         * i = 1: currentSum = -3, maxSum = -2, reset currentSum = 0
         * i = 2: currentSum = 4, maxSum = 4
         * i = 3: currentSum = 3, maxSum = 4
         * i = 4: currentSum = 1, maxSum = 4
         * i = 5: currentSum = 2, maxSum = 4
         * i = 6: currentSum = 7, maxSum = 7
         * i = 7: currentSum = 4, maxSum = 7
         * 
         * Final maxSum = 7
         */
        
        int arr[] = {-2, -3, 4, -1, -2, 1, 5, -3};
        
        System.out.println("Maximum Subarray Sum Problem");
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Expected result: 7 (subarray [4, -1, -2, 1, 5])");
        
        System.out.println("\nDifferent Approaches:");
        maxSubarraySumBruteForce(arr);
        maxSubarraySumPrefix(arr);
        maxSubarraySumKadane(arr);
        
        // Show detailed Kadane's Algorithm process
        System.out.println("\nDetailed Kadane's Algorithm Process:");
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Step-by-step:");
        
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            
            System.out.println("i = " + i + ": arr[" + i + "] = " + arr[i]);
            System.out.println("  currentSum = " + currentSum);
            System.out.println("  maxSum = " + maxSum);
            
            if (currentSum > maxSum) {
                maxSum = currentSum;
                System.out.println("  → maxSum updated to " + maxSum);
            }
            
            if (currentSum < 0) {
                currentSum = 0;
                System.out.println("  → currentSum reset to 0 (negative)");
            }
            System.out.println();
        }
        
        System.out.println("Final maximum subarray sum: " + maxSum);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        int[] test2 = {1, 2, 3, 4, 5};
        System.out.println("Test 2: " + Arrays.toString(test2));
        maxSubarraySumKadane(test2);
        
        int[] test3 = {-1, -2, -3, -4};
        System.out.println("Test 3: " + Arrays.toString(test3));
        maxSubarraySumKadane(test3);
        
        int[] test4 = {5, -4, 6, -2, 7};
        System.out.println("Test 4: " + Arrays.toString(test4));
        maxSubarraySumKadane(test4);
    }
}
