/**
 * Question: Find the difference between the largest and smallest element.
 * 
 * Topics Included:
 * - Array traversal
 * - Finding maximum and minimum values
 * - Arithmetic operations
 * - Comparison operations
 * 
 * Algorithm:
 * 1. Initialize max and min with the first element
 * 2. Traverse through the array starting from the second element
 * 3. Update max if a larger element is found
 * 4. Update min if a smaller element is found
 * 5. Calculate the difference (max - min)
 * 6. Return the difference
 * 
 * Pseudocode:
 * FUNCTION findMaxMinDifference(array):
 *     IF length == 0: RETURN 0
 *     
 *     max = array[0]
 *     min = array[0]
 *     
 *     FOR i from 1 to length-1:
 *         IF array[i] > max:
 *             max = array[i]
 *         IF array[i] < min:
 *             min = array[i]
 *     
 *     RETURN max - min
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
 * - Difference: 25 - 3 = 22
 * Final result: 22
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class MaxMinDifference {
    
    /**
     * Method to find the difference between the largest and smallest element
     * @param arr The input array
     * @return The difference between max and min elements
     */
    public static int findMaxMinDifference(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        
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
        
        return max - min;
    }
    
    public static void main(String[] args) {
        // Question: Find the difference between the largest and smallest element
        // Topics: Array traversal, finding maximum and minimum values, arithmetic operations, comparison operations
        // Algorithm: Find max and min elements, calculate their difference
        // Pseudocode: max = min = array[0]; for each element: update max and min; return max - min
        // Example: [15, 8, 25, 3, 12, 20] → max=25, min=3, difference=22
        // Dry run: max=15,min=15 → max=15,min=8 → max=25,min=8 → max=25,min=3 → max=25,min=3 → max=25,min=3 → difference=22
        // Time: O(n), Space: O(1)
        
        int[] array = {15, 8, 25, 3, 12, 20};
        int result = findMaxMinDifference(array);
        
        System.out.println("Array: [15, 8, 25, 3, 12, 20]");
        System.out.println("Difference between largest and smallest element: " + result);
    }
}
