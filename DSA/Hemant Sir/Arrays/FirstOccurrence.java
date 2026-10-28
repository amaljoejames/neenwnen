/**
 * Question: Find the index of the first occurrence of a given element.
 * 
 * Topics Included:
 * - Array traversal
 * - Linear search
 * - Index tracking
 * - Early termination
 * 
 * Algorithm:
 * 1. Traverse through the array from the beginning
 * 2. Check each element against the target element
 * 3. If found, return the current index immediately
 * 4. If not found after complete traversal, return -1
 * 
 * Pseudocode:
 * FUNCTION findFirstOccurrence(array, target):
 *     FOR i from 0 to length-1:
 *         IF array[i] == target:
 *             RETURN i
 *     RETURN -1
 * 
 * Explanation with Example:
 * Input array: [7, 3, 9, 3, 5, 8, 3, 2], target = 3
 * Dry run:
 * - Index 0: 7 != 3 (continue)
 * - Index 1: 3 == 3 (found! return 1 immediately)
 * Final result: 1
 * 
 * Another example: [7, 3, 9, 3, 5, 8, 3, 2], target = 6
 * - Index 0: 7 != 6 (continue)
 * - Index 1: 3 != 6 (continue)
 * - Index 2: 9 != 6 (continue)
 * - Index 3: 3 != 6 (continue)
 * - Index 4: 5 != 6 (continue)
 * - Index 5: 8 != 6 (continue)
 * - Index 6: 3 != 6 (continue)
 * - Index 7: 2 != 6 (continue)
 * - Complete traversal, return -1
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class FirstOccurrence {
    
    /**
     * Method to find the index of the first occurrence of a given element
     * @param arr The input array
     * @param target The element to search for
     * @return The index of first occurrence, or -1 if not found
     */
    public static int findFirstOccurrence(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        // Question: Find the index of the first occurrence of a given element
        // Topics: Array traversal, linear search, index tracking, early termination
        // Algorithm: Traverse from start, return index when first match found
        // Pseudocode: for i=0 to length-1: if array[i] == target: return i; return -1
        // Example: [7,3,9,3,5,8,3,2], target=3 → first occurrence at index 1
        // Dry run: index0(7≠3), index1(3=3) → return 1
        // Time: O(n), Space: O(1)
        
        int[] array = {7, 3, 9, 3, 5, 8, 3, 2};
        int target = 3;
        int result = findFirstOccurrence(array, target);
        
        System.out.println("Array: [7, 3, 9, 3, 5, 8, 3, 2]");
        System.out.println("Target element: " + target);
        if (result != -1) {
            System.out.println("First occurrence at index: " + result);
        } else {
            System.out.println("Element not found in the array");
        }
    }
}
