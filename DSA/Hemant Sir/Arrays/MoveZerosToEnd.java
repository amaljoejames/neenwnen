/**
 * Question: Move all zeroes to the end without changing order of other elements.
 * 
 * Topics Included:
 * - Array manipulation
 * - Two-pointer technique
 * - In-place modification
 * - Element shifting
 * 
 * Algorithm:
 * 1. Use two pointers: one for non-zero elements, one for traversal
 * 2. Traverse through the array
 * 3. When a non-zero element is found, place it at the non-zero pointer position
 * 4. Increment the non-zero pointer
 * 5. After traversal, fill remaining positions with zeros
 * 6. Return the modified array
 * 
 * Pseudocode:
 * FUNCTION moveZerosToEnd(array):
 *     nonZeroIndex = 0
 *     
 *     FOR i from 0 to length-1:
 *         IF array[i] != 0:
 *             array[nonZeroIndex] = array[i]
 *             nonZeroIndex++
 *     
 *     FOR i from nonZeroIndex to length-1:
 *         array[i] = 0
 *     
 *     RETURN array
 * 
 * Explanation with Example:
 * Input array: [0, 5, 0, 3, 0, 8, 0, 2]
 * Dry run:
 * - Initial nonZeroIndex = 0
 * - Index 0: array[0] = 0 (skip)
 * - Index 1: array[1] = 5 ≠ 0 → array[0] = 5, nonZeroIndex = 1 → [5, 5, 0, 3, 0, 8, 0, 2]
 * - Index 2: array[2] = 0 (skip)
 * - Index 3: array[3] = 3 ≠ 0 → array[1] = 3, nonZeroIndex = 2 → [5, 3, 0, 3, 0, 8, 0, 2]
 * - Index 4: array[4] = 0 (skip)
 * - Index 5: array[5] = 8 ≠ 0 → array[2] = 8, nonZeroIndex = 3 → [5, 3, 8, 3, 0, 8, 0, 2]
 * - Index 6: array[6] = 0 (skip)
 * - Index 7: array[7] = 2 ≠ 0 → array[3] = 2, nonZeroIndex = 4 → [5, 3, 8, 2, 0, 8, 0, 2]
 * - Fill remaining with zeros: array[4] = 0, array[5] = 0, array[6] = 0, array[7] = 0
 * Final result: [5, 3, 8, 2, 0, 0, 0, 0]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space (in-place modification)
 */

public class MoveZerosToEnd {
    
    /**
     * Method to move all zeroes to the end without changing order of other elements
     * @param arr The input array
     * @return The modified array
     */
    public static int[] moveZerosToEnd(int[] arr) {
        int nonZeroIndex = 0;
        
        // Move all non-zero elements to the front
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[nonZeroIndex] = arr[i];
                nonZeroIndex++;
            }
        }
        
        // Fill remaining positions with zeros
        for (int i = nonZeroIndex; i < arr.length; i++) {
            arr[i] = 0;
        }
        
        return arr;
    }
    
    public static void main(String[] args) {
        // Question: Move all zeroes to the end without changing order of other elements
        // Topics: Array manipulation, two-pointer technique, in-place modification, element shifting
        // Algorithm: Use two pointers, move non-zeros to front, fill remaining with zeros
        // Pseudocode: nonZeroIndex=0; for i=0 to length-1: if array[i]!=0: array[nonZeroIndex]=array[i]; nonZeroIndex++; for i=nonZeroIndex to length-1: array[i]=0; return array
        // Example: [0, 5, 0, 3, 0, 8, 0, 2] → [5, 3, 8, 2, 0, 0, 0, 0]
        // Dry run: nonZeroIndex=0 → skip 0 → 5→[0],nonZeroIndex=1 → skip 0 → 3→[1],nonZeroIndex=2 → skip 0 → 8→[2],nonZeroIndex=3 → skip 0 → 2→[3],nonZeroIndex=4 → fill zeros at [4],[5],[6],[7]
        // Time: O(n), Space: O(1)
        
        int[] array = {0, 5, 0, 3, 0, 8, 0, 2};
        int[] result = moveZerosToEnd(array);
        
        System.out.println("Original array: [0, 5, 0, 3, 0, 8, 0, 2]");
        System.out.print("After moving zeros to end: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
