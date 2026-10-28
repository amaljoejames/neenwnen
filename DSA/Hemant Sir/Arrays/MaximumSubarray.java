/**
 * Question: Find the subarray with maximum sum (Kadane's algorithm).
 * 
 * Topics Included:
 * - Array manipulation
 * - Kadane's algorithm
 * - Dynamic programming
 * - Maximum subarray problem
 * 
 * Algorithm:
 * 1. Initialize two variables: maxSoFar and maxEndingHere
 * 2. Set both to the first element of the array
 * 3. Traverse through the array starting from the second element
 * 4. For each element:
 *    - maxEndingHere = maximum of (current element, maxEndingHere + current element)
 *    - maxSoFar = maximum of (maxSoFar, maxEndingHere)
 * 5. Return maxSoFar
 * 
 * Pseudocode:
 * FUNCTION findMaximumSubarray(array):
 *     IF length == 0: RETURN 0
 *     
 *     maxSoFar = array[0]
 *     maxEndingHere = array[0]
 *     
 *     FOR i from 1 to length-1:
 *         maxEndingHere = maximum of (array[i], maxEndingHere + array[i])
 *         maxSoFar = maximum of (maxSoFar, maxEndingHere)
 *     
 *     RETURN maxSoFar
 * 
 * Explanation with Example:
 * Input array: [-2, -3, 4, -1, -2, 1, 5, -3]
 * Dry run:
 * - Initial: maxSoFar = -2, maxEndingHere = -2
 * - Index 1: maxEndingHere = max(-3, -2 + -3) = -3, maxSoFar = max(-2, -3) = -2
 * - Index 2: maxEndingHere = max(4, -3 + 4) = 4, maxSoFar = max(-2, 4) = 4
 * - Index 3: maxEndingHere = max(-1, 4 + -1) = 3, maxSoFar = max(4, 3) = 4
 * - Index 4: maxEndingHere = max(-2, 3 + -2) = 1, maxSoFar = max(4, 1) = 4
 * - Index 5: maxEndingHere = max(1, 1 + 1) = 2, maxSoFar = max(4, 2) = 4
 * - Index 6: maxEndingHere = max(5, 2 + 5) = 7, maxSoFar = max(4, 7) = 7
 * - Index 7: maxEndingHere = max(-3, 7 + -3) = 4, maxSoFar = max(7, 4) = 7
 * Final result: 7
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class MaximumSubarray {
    
    /**
     * Method to find the subarray with maximum sum (Kadane's algorithm)
     * @param arr The input array
     * @return The maximum subarray sum
     */
    public static int findMaximumSubarray(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    public static void main(String[] args) {
        // Question: Find the subarray with maximum sum (Kadane's algorithm)
        // Topics: Array manipulation, Kadane's algorithm, dynamic programming, maximum subarray problem
        // Algorithm: Track max ending here and max so far while traversing array
        // Pseudocode: maxSoFar = maxEndingHere = array[0]; for i=1 to length-1: maxEndingHere = max(array[i], maxEndingHere + array[i]); maxSoFar = max(maxSoFar, maxEndingHere); return maxSoFar
        // Example: [-2, -3, 4, -1, -2, 1, 5, -3] → maximum subarray sum = 7
        // Dry run: maxSoFar=-2,maxEndingHere=-2 → maxEndingHere=-3,maxSoFar=-2 → maxEndingHere=4,maxSoFar=4 → maxEndingHere=3,maxSoFar=4 → maxEndingHere=1,maxSoFar=4 → maxEndingHere=2,maxSoFar=4 → maxEndingHere=7,maxSoFar=7 → maxEndingHere=4,maxSoFar=7
        // Time: O(n), Space: O(1)
        
        int[] array = {-2, -3, 4, -1, -2, 1, 5, -3};
        int result = findMaximumSubarray(array);
        
        System.out.println("Array: [-2, -3, 4, -1, -2, 1, 5, -3]");
        System.out.println("Maximum subarray sum: " + result);
    }
}
