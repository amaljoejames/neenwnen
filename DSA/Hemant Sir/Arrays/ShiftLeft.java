/**
 * Question: Shift all elements to the left by one position.
 * 
 * Topics Included:
 * - Array manipulation
 * - In-place modification
 * - Index shifting
 * - Looping constructs
 * 
 * Algorithm:
 * 1. Store the first element in a temporary variable
 * 2. Shift each element one position to the left (i.e., array[i] = array[i+1])
 * 3. Place the stored first element at the last position
 * 4. Return the modified array
 * 
 * Pseudocode:
 * FUNCTION shiftLeft(array):
 *     IF length <= 1: RETURN array
 *     
 *     firstElement = array[0]
 *     FOR i from 0 to length-2:
 *         array[i] = array[i+1]
 *     array[length-1] = firstElement
 *     RETURN array
 * 
 * Explanation with Example:
 * Input array: [5, 8, 3, 9, 2, 7]
 * Dry run:
 * - Store firstElement = 5
 * - Index 0: array[0] = array[1] = 8 → [8, 8, 3, 9, 2, 7]
 * - Index 1: array[1] = array[2] = 3 → [8, 3, 3, 9, 2, 7]
 * - Index 2: array[2] = array[3] = 9 → [8, 3, 9, 9, 2, 7]
 * - Index 3: array[3] = array[4] = 2 → [8, 3, 9, 2, 2, 7]
 * - Index 4: array[4] = array[5] = 7 → [8, 3, 9, 2, 7, 7]
 * - Place firstElement at last: array[5] = 5 → [8, 3, 9, 2, 7, 5]
 * Final result: [8, 3, 9, 2, 7, 5]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space (in-place modification)
 */

public class ShiftLeft {
    
    /**
     * Method to shift all elements to the left by one position
     * @param arr The input array
     * @return The modified array after left shift
     */
    public static int[] shiftLeft(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        
        int firstElement = arr[0];
        
        // Shift elements to the left
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        
        // Place the first element at the last position
        arr[arr.length - 1] = firstElement;
        
        return arr;
    }
    
    public static void main(String[] args) {
        // Question: Shift all elements to the left by one position
        // Topics: Array manipulation, in-place modification, index shifting, looping constructs
        // Algorithm: Store first element, shift all elements left, place first element at end
        // Pseudocode: firstElement = array[0]; for i=0 to length-2: array[i] = array[i+1]; array[length-1] = firstElement; return array
        // Example: [5, 8, 3, 9, 2, 7] → [8, 3, 9, 2, 7, 5]
        // Dry run: store 5 → shift: 8→[0], 3→[1], 9→[2], 2→[3], 7→[4] → place 5 at [5] → [8,3,9,2,7,5]
        // Time: O(n), Space: O(1)
        
        int[] array = {5, 8, 3, 9, 2, 7};
        int[] result = shiftLeft(array);
        
        System.out.println("Original array: [5, 8, 3, 9, 2, 7]");
        System.out.print("After left shift: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
