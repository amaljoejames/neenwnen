/**
 * Question: Replace all zeroes with ones.
 * 
 * Topics Included:
 * - Array traversal
 * - Conditional statements
 * - In-place modification
 * - Comparison operations
 * 
 * Algorithm:
 * 1. Traverse through each element of the array
 * 2. Check if the current element is zero
 * 3. If zero, replace it with one
 * 4. Continue until all elements are processed
 * 5. Return the modified array
 * 
 * Pseudocode:
 * FUNCTION replaceZeroWithOne(array):
 *     FOR i from 0 to length-1:
 *         IF array[i] == 0:
 *             array[i] = 1
 *     RETURN array
 * 
 * Explanation with Example:
 * Input array: [3, 0, 8, 0, 7, 0, 4, 2]
 * Dry run:
 * - Index 0: 3 != 0 (no change)
 * - Index 1: 0 == 0 → array[1] = 1 → [3, 1, 8, 0, 7, 0, 4, 2]
 * - Index 2: 8 != 0 (no change)
 * - Index 3: 0 == 0 → array[3] = 1 → [3, 1, 8, 1, 7, 0, 4, 2]
 * - Index 4: 7 != 0 (no change)
 * - Index 5: 0 == 0 → array[5] = 1 → [3, 1, 8, 1, 7, 1, 4, 2]
 * - Index 6: 4 != 0 (no change)
 * - Index 7: 2 != 0 (no change)
 * Final result: [3, 1, 8, 1, 7, 1, 4, 2]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space (in-place modification)
 */

public class ReplaceZeroWithOne {
    
    /**
     * Method to replace all zeroes with ones
     * @param arr The input array
     * @return The modified array
     */
    public static int[] replaceZeroWithOne(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            }
        }
        return arr;
    }
    
    public static void main(String[] args) {
        // Question: Replace all zeroes with ones
        // Topics: Array traversal, conditional statements, in-place modification, comparison operations
        // Algorithm: Traverse array, replace zero elements with one
        // Pseudocode: for i=0 to length-1: if array[i] == 0: array[i] = 1; return array
        // Example: [3, 0, 8, 0, 7, 0, 4, 2] → [3, 1, 8, 1, 7, 1, 4, 2]
        // Dry run: [3,0,8,0,7,0,4,2] → [3,1,8,0,7,0,4,2] → [3,1,8,1,7,0,4,2] → [3,1,8,1,7,1,4,2]
        // Time: O(n), Space: O(1)
        
        int[] array = {3, 0, 8, 0, 7, 0, 4, 2};
        int[] result = replaceZeroWithOne(array);
        
        System.out.println("Original array: [3, 0, 8, 0, 7, 0, 4, 2]");
        System.out.print("After replacing zeroes with ones: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
