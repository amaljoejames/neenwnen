/**
 * Question: Replace negative numbers with zero.
 * 
 * Topics Included:
 * - Array traversal
 * - Conditional statements
 * - In-place modification
 * - Comparison operations
 * 
 * Algorithm:
 * 1. Traverse through each element of the array
 * 2. Check if the current element is negative
 * 3. If negative, replace it with zero
 * 4. Continue until all elements are processed
 * 5. Return the modified array
 * 
 * Pseudocode:
 * FUNCTION replaceNegativeWithZero(array):
 *     FOR i from 0 to length-1:
 *         IF array[i] < 0:
 *             array[i] = 0
 *     RETURN array
 * 
 * Explanation with Example:
 * Input array: [3, -5, 8, -2, 7, -9, 4, -1]
 * Dry run:
 * - Index 0: 3 >= 0 (no change)
 * - Index 1: -5 < 0 → array[1] = 0 → [3, 0, 8, -2, 7, -9, 4, -1]
 * - Index 2: 8 >= 0 (no change)
 * - Index 3: -2 < 0 → array[3] = 0 → [3, 0, 8, 0, 7, -9, 4, -1]
 * - Index 4: 7 >= 0 (no change)
 * - Index 5: -9 < 0 → array[5] = 0 → [3, 0, 8, 0, 7, 0, 4, -1]
 * - Index 6: 4 >= 0 (no change)
 * - Index 7: -1 < 0 → array[7] = 0 → [3, 0, 8, 0, 7, 0, 4, 0]
 * Final result: [3, 0, 8, 0, 7, 0, 4, 0]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space (in-place modification)
 */

public class ReplaceNegativeWithZero {
    
    /**
     * Method to replace negative numbers with zero
     * @param arr The input array
     * @return The modified array
     */
    public static int[] replaceNegativeWithZero(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                arr[i] = 0;
            }
        }
        return arr;
    }
    
    public static void main(String[] args) {
        // Question: Replace negative numbers with zero
        // Topics: Array traversal, conditional statements, in-place modification, comparison operations
        // Algorithm: Traverse array, replace negative elements with zero
        // Pseudocode: for i=0 to length-1: if array[i] < 0: array[i] = 0; return array
        // Example: [3, -5, 8, -2, 7, -9, 4, -1] → [3, 0, 8, 0, 7, 0, 4, 0]
        // Dry run: [3,-5,8,-2,7,-9,4,-1] → [3,0,8,-2,7,-9,4,-1] → [3,0,8,0,7,-9,4,-1] → [3,0,8,0,7,0,4,-1] → [3,0,8,0,7,0,4,0]
        // Time: O(n), Space: O(1)
        
        int[] array = {3, -5, 8, -2, 7, -9, 4, -1};
        int[] result = replaceNegativeWithZero(array);
        
        System.out.println("Original array: [3, -5, 8, -2, 7, -9, 4, -1]");
        System.out.print("After replacing negatives with zero: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
