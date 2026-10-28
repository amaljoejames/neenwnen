/**
 * Question: Replace all occurrences of a specific element with another number.
 * 
 * Topics Included:
 * - Array traversal
 * - In-place modification
 * - Comparison operations
 * - Assignment operations
 * 
 * Algorithm:
 * 1. Traverse through each element of the array
 * 2. If element matches the target, replace it with the replacement value
 * 3. Continue until all elements are processed
 * 4. Return the modified array
 * 
 * Pseudocode:
 * FUNCTION replaceAllOccurrences(array, target, replacement):
 *     FOR i from 0 to length-1:
 *         IF array[i] == target:
 *             array[i] = replacement
 *     RETURN array
 * 
 * Explanation with Example:
 * Input array: [1, 3, 5, 3, 7, 3, 9], target = 3, replacement = 8
 * Dry run:
 * - Index 0: 1 != 3 (no change)
 * - Index 1: 3 == 3 (replace with 8) → [1, 8, 5, 3, 7, 3, 9]
 * - Index 2: 5 != 3 (no change)
 * - Index 3: 3 == 3 (replace with 8) → [1, 8, 5, 8, 7, 3, 9]
 * - Index 4: 7 != 3 (no change)
 * - Index 5: 3 == 3 (replace with 8) → [1, 8, 5, 8, 7, 8, 9]
 * - Index 6: 9 != 3 (no change)
 * Final result: [1, 8, 5, 8, 7, 8, 9]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space (in-place modification)
 */

public class ReplaceAllOccurrences {
    
    /**
     * Method to replace all occurrences of a specific element with another number
     * @param arr The input array
     * @param target The element to be replaced
     * @param replacement The replacement value
     * @return The modified array
     */
    public static int[] replaceAllOccurrences(int[] arr, int target, int replacement) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                arr[i] = replacement;
            }
        }
        return arr;
    }
    
    public static void main(String[] args) {
        // Question: Replace all occurrences of a specific element with another number
        // Topics: Array traversal, in-place modification, comparison operations, assignment operations
        // Algorithm: Traverse array, replace target elements with replacement value
        // Pseudocode: for each element: if element == target: element = replacement; return array
        // Example: [1, 3, 5, 3, 7, 3, 9], target = 3, replacement = 8 → [1, 8, 5, 8, 7, 8, 9]
        // Dry run: [1,3,5,3,7,3,9] → [1,8,5,3,7,3,9] → [1,8,5,8,7,3,9] → [1,8,5,8,7,8,9]
        // Time: O(n), Space: O(1)
        
        int[] array = {1, 3, 5, 3, 7, 3, 9};
        int target = 3;
        int replacement = 8;
        int[] result = replaceAllOccurrences(array, target, replacement);
        
        System.out.println("Original array: [1, 3, 5, 3, 7, 3, 9]");
        System.out.println("Target: " + target + ", Replacement: " + replacement);
        System.out.print("Modified array: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
