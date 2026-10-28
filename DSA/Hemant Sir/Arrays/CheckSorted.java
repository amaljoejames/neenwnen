/**
 * Question: Check if an array is sorted (ascending).
 * 
 * Topics Included:
 * - Array traversal
 * - Comparison operations
 * - Adjacent element checking
 * - Early termination
 * 
 * Algorithm:
 * 1. Traverse through the array from the second element
 * 2. Compare each element with the previous element
 * 3. If any element is smaller than the previous one, array is not sorted
 * 4. If all comparisons pass, array is sorted
 * 5. Return true if sorted, false otherwise
 * 
 * Pseudocode:
 * FUNCTION isSorted(array):
 *     FOR i from 1 to length-1:
 *         IF array[i] < array[i-1]:
 *             RETURN false
 *     RETURN true
 * 
 * Explanation with Example:
 * Input array: [1, 3, 5, 7, 9, 11]
 * Dry run:
 * - Index 1: 3 >= 1 (continue)
 * - Index 2: 5 >= 3 (continue)
 * - Index 3: 7 >= 5 (continue)
 * - Index 4: 9 >= 7 (continue)
 * - Index 5: 11 >= 9 (continue)
 * - All comparisons passed, return true
 * 
 * Another example: [1, 3, 2, 7, 9, 11]
 * - Index 1: 3 >= 1 (continue)
 * - Index 2: 2 < 3 (return false immediately)
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class CheckSorted {
    
    /**
     * Method to check if an array is sorted in ascending order
     * @param arr The input array
     * @return true if array is sorted, false otherwise
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        // Question: Check if an array is sorted (ascending)
        // Topics: Array traversal, comparison operations, adjacent element checking, early termination
        // Algorithm: Compare each element with previous element, return false if any element is smaller
        // Pseudocode: for i=1 to length-1: if array[i] < array[i-1]: return false; return true
        // Example: [1, 3, 5, 7, 9, 11] → true, [1, 3, 2, 7, 9, 11] → false
        // Dry run: [1,3,5,7,9,11] → 3>=1, 5>=3, 7>=5, 9>=7, 11>=9 → true
        // Time: O(n), Space: O(1)
        
        // Test case 1: Sorted array
        int[] array1 = {1, 3, 5, 7, 9, 11};
        boolean result1 = isSorted(array1);
        
        System.out.println("Array 1: [1, 3, 5, 7, 9, 11]");
        System.out.println("Is sorted: " + result1);
        System.out.println();
        
        // Test case 2: Unsorted array
        int[] array2 = {1, 3, 2, 7, 9, 11};
        boolean result2 = isSorted(array2);
        
        System.out.println("Array 2: [1, 3, 2, 7, 9, 11]");
        System.out.println("Is sorted: " + result2);
    }
}
