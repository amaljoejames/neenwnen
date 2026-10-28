/**
 * Question: Find the index of the last occurrence of a given element.
 * 
 * Topics Included:
 * - Array traversal
 * - Reverse traversal
 * - Index tracking
 * - Early termination
 * 
 * Algorithm:
 * 1. Traverse through the array from the end (reverse order)
 * 2. Check each element against the target element
 * 3. If found, return the current index immediately
 * 4. If not found after complete traversal, return -1
 * 
 * Pseudocode:
 * FUNCTION findLastOccurrence(array, target):
 *     FOR i from length-1 down to 0:
 *         IF array[i] == target:
 *             RETURN i
 *     RETURN -1
 * 
 * Explanation with Example:
 * Input array: [7, 3, 9, 3, 5, 8, 3, 2], target = 3
 * Dry run:
 * - Index 7: 2 != 3 (continue)
 * - Index 6: 3 == 3 (found! return 6 immediately)
 * Final result: 6
 * 
 * Another example: [7, 3, 9, 3, 5, 8, 3, 2], target = 6
 * - Index 7: 2 != 6 (continue)
 * - Index 6: 3 != 6 (continue)
 * - Index 5: 8 != 6 (continue)
 * - Index 4: 5 != 6 (continue)
 * - Index 3: 3 != 6 (continue)
 * - Index 2: 9 != 6 (continue)
 * - Index 1: 3 != 6 (continue)
 * - Index 0: 7 != 6 (continue)
 * - Complete traversal, return -1
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class LastOccurrence {
    
    /**
     * Method to find the index of the last occurrence of a given element
     * @param arr The input array
     * @param target The element to search for
     * @return The index of last occurrence, or -1 if not found
     */
    public static int findLastOccurrence(int[] arr, int target) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        // Question: Find the index of the last occurrence of a given element
        // Topics: Array traversal, reverse traversal, index tracking, early termination
        // Algorithm: Traverse from end, return index when first match found (which is last occurrence)
        // Pseudocode: for i=length-1 down to 0: if array[i] == target: return i; return -1
        // Example: [7,3,9,3,5,8,3,2], target=3 → last occurrence at index 6
        // Dry run: index7(2≠3), index6(3=3) → return 6
        // Time: O(n), Space: O(1)
        
        int[] array = {7, 3, 9, 3, 5, 8, 3, 2};
        int target = 3;
        int result = findLastOccurrence(array, target);
        
        System.out.println("Array: [7, 3, 9, 3, 5, 8, 3, 2]");
        System.out.println("Target element: " + target);
        if (result != -1) {
            System.out.println("Last occurrence at index: " + result);
        } else {
            System.out.println("Element not found in the array");
        }
    }
}
