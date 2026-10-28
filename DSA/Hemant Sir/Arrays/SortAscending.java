/**
 * Question: Sort an array in ascending order.
 * 
 * Topics Included:
 * - Sorting algorithms
 * - Bubble sort implementation
 * - Array manipulation
 * - Nested loops
 * - Swapping elements
 * 
 * Algorithm:
 * 1. Use bubble sort algorithm
 * 2. Compare adjacent elements
 * 3. Swap if elements are in wrong order
 * 4. Repeat passes until no swaps needed
 * 5. Return the sorted array
 * 
 * Pseudocode:
 * FUNCTION sortAscending(array):
 *     n = length of array
 *     FOR i from 0 to n-1:
 *         swapped = false
 *         FOR j from 0 to n-i-2:
 *             IF array[j] > array[j+1]:
 *                 swap array[j] and array[j+1]
 *                 swapped = true
 *         IF NOT swapped:
 *             BREAK
 *     RETURN array
 * 
 * Explanation with Example:
 * Input array: [64, 34, 25, 12, 22, 11, 90]
 * Dry run:
 * Pass 1:
 * - Compare 64,34: swap → [34, 64, 25, 12, 22, 11, 90]
 * - Compare 64,25: swap → [34, 25, 64, 12, 22, 11, 90]
 * - Compare 64,12: swap → [34, 25, 12, 64, 22, 11, 90]
 * - Compare 64,22: swap → [34, 25, 12, 22, 64, 11, 90]
 * - Compare 64,11: swap → [34, 25, 12, 22, 11, 64, 90]
 * - Compare 64,90: no swap → [34, 25, 12, 22, 11, 64, 90]
 * 
 * Pass 2:
 * - Compare 34,25: swap → [25, 34, 12, 22, 11, 64, 90]
 * - Compare 34,12: swap → [25, 12, 34, 22, 11, 64, 90]
 * - Compare 34,22: swap → [25, 12, 22, 34, 11, 64, 90]
 * - Compare 34,11: swap → [25, 12, 22, 11, 34, 64, 90]
 * - Compare 34,64: no swap → [25, 12, 22, 11, 34, 64, 90]
 * 
 * Continue until sorted...
 * Final result: [11, 12, 22, 25, 34, 64, 90]
 * 
 * Time Complexity: O(n²) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space (in-place sorting)
 */

public class SortAscending {
    
    /**
     * Method to sort an array in ascending order using bubble sort
     * @param arr The input array
     * @return The sorted array
     */
    public static int[] sortAscending(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            // If no swapping occurred, array is already sorted
            if (!swapped) {
                break;
            }
        }
        
        return arr;
    }
    
    public static void main(String[] args) {
        // Question: Sort an array in ascending order
        // Topics: Sorting algorithms, bubble sort, array manipulation, nested loops, swapping elements
        // Algorithm: Use bubble sort to compare and swap adjacent elements until sorted
        // Pseudocode: for i=0 to n-1: for j=0 to n-i-2: if array[j] > array[j+1]: swap; return array
        // Example: [64, 34, 25, 12, 22, 11, 90] → [11, 12, 22, 25, 34, 64, 90]
        // Dry run: bubble sort passes comparing adjacent elements and swapping when needed
        // Time: O(n²), Space: O(1)
        
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        int[] sortedArray = sortAscending(array);
        
        System.out.println("Original array: [64, 34, 25, 12, 22, 11, 90]");
        System.out.print("Sorted array: [");
        for (int i = 0; i < sortedArray.length; i++) {
            System.out.print(sortedArray[i]);
            if (i < sortedArray.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
