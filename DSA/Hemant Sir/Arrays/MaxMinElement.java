/**
 * Question: Find the maximum/minimum element in an array.
 * 
 * Topics Included:
 * - Array traversal
 * - Comparison operations
 * - Conditional statements
 * - Looping constructs
 * 
 * Algorithm:
 * 1. Initialize max and min with the first element
 * 2. Iterate through the array starting from the second element
 * 3. Compare each element with current max and min
 * 4. Update max/min if a larger/smaller element is found
 * 5. Return both max and min values
 * 
 * Pseudocode:
 * FUNCTION findMaxMin(array):
 *     max = array[0]
 *     min = array[0]
 *     FOR i from 1 to length-1:
 *         IF array[i] > max:
 *             max = array[i]
 *         IF array[i] < min:
 *             min = array[i]
 *     RETURN (max, min)
 * 
 * Explanation with Example:
 * Input array: [15, 8, 25, 3, 12, 20]
 * Dry run:
 * - Initial: max = 15, min = 15
 * - Element 8: max = 15, min = 8 (8 < 15)
 * - Element 25: max = 25, min = 8 (25 > 15)
 * - Element 3: max = 25, min = 3 (3 < 8)
 * - Element 12: max = 25, min = 3 (no change)
 * - Element 20: max = 25, min = 3 (no change)
 * Final result: max = 25, min = 3
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class MaxMinElement {
    
    /**
     * Method to find maximum and minimum elements in an array
     * @param arr The input array
     * @return An array where result[0] is max and result[1] is min
     */
    public static int[] findMaxMin(int[] arr) {
        if (arr.length == 0) return new int[]{0, 0};
        
        int max = arr[0];
        int min = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        
        return new int[]{max, min};
    }
    
    public static void main(String[] args) {
        // Question: Find the maximum/minimum element in an array
        // Topics: Array traversal, comparison operations, conditional statements, looping constructs
        // Algorithm: Initialize max/min with first element, compare and update while traversing
        // Pseudocode: max = min = array[0]; for each element: update max/min if needed; return (max, min)
        // Example: [15, 8, 25, 3, 12, 20] -> max = 25, min = 3
        // Dry run: max=15,min=15 → max=15,min=8 → max=25,min=8 → max=25,min=3 → max=25,min=3 → max=25,min=3
        // Time: O(n), Space: O(1)
        
        int[] array = {15, 8, 25, 3, 12, 20};
        int[] result = findMaxMin(array);
        System.out.println("Array: [15, 8, 25, 3, 12, 20]");
        System.out.println("Maximum element: " + result[0]);
        System.out.println("Minimum element: " + result[1]);
    }
}
