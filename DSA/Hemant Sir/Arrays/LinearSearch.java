/**
 * Question: Search for a given element (linear search).
 * 
 * Topics Included:
 * - Array traversal
 * - Linear search algorithm
 * - Conditional statements
 * - Looping constructs
 * 
 * Algorithm:
 * 1. Start from the first element of the array
 * 2. Compare each element with the target element
 * 3. If found, return the index
 * 4. If not found after traversing entire array, return -1
 * 
 * Pseudocode:
 * FUNCTION linearSearch(array, target):
 *     FOR i from 0 to length-1:
 *         IF array[i] == target:
 *             RETURN i
 *     RETURN -1
 * 
 * Explanation with Example:
 * Input array: [7, 3, 9, 1, 5, 8], target = 5
 * Dry run:
 * - Index 0: 7 != 5 (continue)
 * - Index 1: 3 != 5 (continue)
 * - Index 2: 9 != 5 (continue)
 * - Index 3: 1 != 5 (continue)
 * - Index 4: 5 == 5 (found! return 4)
 * Final result: 4
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class LinearSearch {
    
    /**
     * Method to search for an element using linear search
     * @param arr The input array
     * @param target The element to search for
     * @return The index of the target element, or -1 if not found
     */
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        // Question: Search for a given element (linear search)
        // Topics: Array traversal, linear search algorithm, conditional statements, looping constructs
        // Algorithm: Traverse array sequentially, compare each element with target
        // Pseudocode: for each element: if element == target: return index; return -1
        // Example: [7, 3, 9, 1, 5, 8], target = 5 -> found at index 4
        // Dry run: index0(7≠5), index1(3≠5), index2(9≠5), index3(1≠5), index4(5=5) → return 4
        // Time: O(n), Space: O(1)
        
        int[] array = {7, 3, 9, 1, 5, 8};
        int target = 5;
        int result = linearSearch(array, target);
        System.out.println("Array: [7, 3, 9, 1, 5, 8]");
        System.out.println("Target element: " + target);
        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found in the array");
        }
    }
}
