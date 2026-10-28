/**
 * Question: Shift all elements to the right by one position.
 * 
 * Topics Included:
 * - Array manipulation
 * - In-place modification
 * - Index shifting
 * - Looping constructs
 * 
 * Algorithm:
 * 1. Store the last element in a temporary variable
 * 2. Shift each element one position to the right (i.e., array[i] = array[i-1])
 * 3. Place the stored last element at the first position
 * 4. Return the modified array
 * 
 * Pseudocode:
 * FUNCTION shiftRight(array):
 *     IF length <= 1: RETURN array
 *     
 *     lastElement = array[length-1]
 *     FOR i from length-1 down to 1:
 *         array[i] = array[i-1]
 *     array[0] = lastElement
 *     RETURN array
 * 
 * Explanation with Example:
 * Input array: [5, 8, 3, 9, 2, 7]
 * Dry run:
 * - Store lastElement = 7
 * - Index 5: array[5] = array[4] = 2 → [5, 8, 3, 9, 2, 2]
 * - Index 4: array[4] = array[3] = 9 → [5, 8, 3, 9, 9, 2]
 * - Index 3: array[3] = array[2] = 3 → [5, 8, 3, 3, 9, 2]
 * - Index 2: array[2] = array[1] = 8 → [5, 8, 8, 3, 9, 2]
 * - Index 1: array[1] = array[0] = 5 → [5, 5, 8, 3, 9, 2]
 * - Place lastElement at first: array[0] = 7 → [7, 5, 8, 3, 9, 2]
 * Final result: [7, 5, 8, 3, 9, 2]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space (in-place modification)
 */

public class ShiftRight {
    
    /**
     * Method to shift all elements to the right by one position
     * @param arr The input array
     * @return The modified array after right shift
     */
    public static int[] shiftRight(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        
        int lastElement = arr[arr.length - 1];
        
        // Shift elements to the right
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        
        // Place the last element at the first position
        arr[0] = lastElement;
        
        return arr;
    }
    
    public static void main(String[] args) {
        // Question: Shift all elements to the right by one position
        // Topics: Array manipulation, in-place modification, index shifting, looping constructs
        // Algorithm: Store last element, shift all elements right, place last element at beginning
        // Pseudocode: lastElement = array[length-1]; for i=length-1 down to 1: array[i] = array[i-1]; array[0] = lastElement; return array
        // Example: [5, 8, 3, 9, 2, 7] → [7, 5, 8, 3, 9, 2]
        // Dry run: store 7 → shift: 2→[5], 9→[4], 3→[3], 8→[2], 5→[1] → place 7 at [0] → [7,5,8,3,9,2]
        // Time: O(n), Space: O(1)
        
        int[] array = {5, 8, 3, 9, 2, 7};
        int[] result = shiftRight(array);
        
        System.out.println("Original array: [5, 8, 3, 9, 2, 7]");
        System.out.print("After right shift: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
