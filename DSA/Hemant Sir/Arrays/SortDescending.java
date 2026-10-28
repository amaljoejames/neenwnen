/**
 * Question: Sort an array in descending order.
 * 
 * Topics Included:
 * - Sorting algorithms
 * - Bubble sort implementation
 * - Array manipulation
 * - Nested loops
 * - Swapping elements
 * 
 * Algorithm:
 * 1. Use bubble sort algorithm with reversed comparison
 * 2. Compare adjacent elements
 * 3. Swap if left element is smaller than right element
 * 4. Repeat passes until no swaps needed
 * 5. Return the sorted array
 * 
 * Pseudocode:
 * FUNCTION sortDescending(array):
 *     n = length of array
 *     FOR i from 0 to n-1:
 *         FOR j from 0 to n-i-2:
 *             IF array[j] < array[j+1]:
 *                 swap array[j] and array[j+1]
 *     RETURN array
 * 
 * Explanation with Example:
 * Input array: [3, 5, 2, 8, 1]
 * Dry run:
 * Pass 1:
 * - Compare 3,5: no swap (3 < 5, but we want larger first) -> [3, 5, 2, 8, 1]
 * - Compare 5,2: swap (5 > 2) -> [3, 2, 5, 8, 1]
 * - Compare 5,8: no swap (5 < 8) -> [3, 2, 5, 8, 1]
 * - Compare 8,1: swap (8 > 1) -> [3, 2, 5, 1, 8]
 * 
 * Pass 2:
 * - Compare 3,2: swap (3 > 2) -> [2, 3, 5, 1, 8]
 * - Compare 3,5: no swap (3 < 5) -> [2, 3, 5, 1, 8]
 * - Compare 5,1: swap (5 > 1) -> [2, 3, 1, 5, 8]
 * 
 * Pass 3:
 * - Compare 2,3: no swap -> [2, 3, 1, 5, 8]
 * - Compare 3,1: swap (3 > 1) -> [2, 1, 3, 5, 8]
 * 
 * Pass 4:
 * - Compare 2,1: swap (2 > 1) -> [1, 2, 3, 5, 8]
 * 
 * Final result: [8, 5, 3, 2, 1]
 * 
 * Time Complexity: O(n²) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space (in-place sorting)
 */

public class SortDescending {
    
    /**
     * Method to sort an array in descending order using bubble sort
     * @param arr The input array
     * @return The sorted array
     */
    public static int[] sortDescending(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    // Swap elements: larger element moves to the left
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        
        return arr;
    }
    
    public static void main(String[] args) {
        // Question: Sort an array in descending order
        // Topics: Sorting algorithms, bubble sort, array manipulation, nested loops, swapping elements
        // Algorithm: Use bubble sort with reversed comparison to sort in descending order
        // Pseudocode: for i=0 to n-1: for j=0 to n-i-2: if array[j] < array[j+1]: swap; return array
        // Example: [3, 5, 2, 8, 1] -> [8, 5, 3, 2, 1]
        // Dry run: bubble sort passes comparing adjacent elements and swapping when left < right
        // Time: O(n²), Space: O(1)
        
        int[] array = {3, 5, 2, 8, 1};
        int[] sortedArray = sortDescending(array);
        
        System.out.println("Original array: [3, 5, 2, 8, 1]");
        System.out.print("Sorted array: [");
        for (int i = 0; i < sortedArray.length; i++) {
            System.out.print(sortedArray[i]);
            if (i < sortedArray.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
