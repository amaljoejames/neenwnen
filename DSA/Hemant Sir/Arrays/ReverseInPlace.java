/**
 * Question: Reverse an array in place without using extra space.
 * 
 * Topics Included:
 * - Array manipulation
 * - In-place modification
 * - Two-pointer technique
 * - Swapping elements
 * 
 * Algorithm:
 * 1. Use two pointers: one at the start and one at the end
 * 2. Swap elements at these pointers
 * 3. Move the start pointer forward and end pointer backward
 * 4. Continue until pointers meet or cross each other
 * 5. Return the reversed array
 * 
 * Pseudocode:
 * FUNCTION reverseInPlace(array):
 *     start = 0
 *     end = length - 1
 *     
 *     WHILE start < end:
 *         swap array[start] and array[end]
 *         start = start + 1
 *         end = end - 1
 *     
 *     RETURN array
 * 
 * Explanation with Example:
 * Input array: [1, 2, 3, 4, 5, 6]
 * Dry run:
 * - Initial: start = 0, end = 5
 * - Iteration 1: swap array[0] and array[5] → [6, 2, 3, 4, 5, 1], start = 1, end = 4
 * - Iteration 2: swap array[1] and array[4] → [6, 5, 3, 4, 2, 1], start = 2, end = 3
 * - Iteration 3: swap array[2] and array[3] → [6, 5, 4, 3, 2, 1], start = 3, end = 2
 * - Loop ends as start >= end
 * Final result: [6, 5, 4, 3, 2, 1]
 * 
 * Time Complexity: O(n/2) = O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space (in-place modification)
 */

public class ReverseInPlace {
    
    /**
     * Method to reverse an array in place without using extra space
     * @param arr The input array
     * @return The reversed array
     */
    public static int[] reverseInPlace(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        
        while (start < end) {
            // Swap elements at start and end positions
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            
            // Move pointers towards center
            start++;
            end--;
        }
        
        return arr;
    }
    
    public static void main(String[] args) {
        // Question: Reverse an array in place without using extra space
        // Topics: Array manipulation, in-place modification, two-pointer technique, swapping elements
        // Algorithm: Use two pointers at start and end, swap elements and move towards center
        // Pseudocode: start=0, end=length-1; while start<end: swap array[start] and array[end]; start++, end--; return array
        // Example: [1, 2, 3, 4, 5, 6] → [6, 5, 4, 3, 2, 1]
        // Dry run: start=0,end=5 → swap(1,6) → start=1,end=4 → swap(2,5) → start=2,end=3 → swap(3,4) → start=3,end=2 (stop)
        // Time: O(n), Space: O(1)
        
        int[] array = {1, 2, 3, 4, 5, 6};
        int[] reversedArray = reverseInPlace(array);
        
        System.out.println("Original array: [1, 2, 3, 4, 5, 6]");
        System.out.print("Reversed array: [");
        for (int i = 0; i < reversedArray.length; i++) {
            System.out.print(reversedArray[i]);
            if (i < reversedArray.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
