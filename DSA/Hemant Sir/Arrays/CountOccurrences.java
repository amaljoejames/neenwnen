/**
 * Question: Count the occurrences of a specific element.
 * 
 * Topics Included:
 * - Array traversal
 * - Counter variable
 * - Comparison operations
 * - Looping constructs
 * 
 * Algorithm:
 * 1. Initialize a counter to 0
 * 2. Traverse through each element of the array
 * 3. If element matches the target, increment the counter
 * 4. Return the final count
 * 
 * Pseudocode:
 * FUNCTION countOccurrences(array, target):
 *     count = 0
 *     FOR each element in array:
 *         IF element == target:
 *             count = count + 1
 *     RETURN count
 * 
 * Explanation with Example:
 * Input array: [2, 5, 2, 8, 2, 9, 5, 2], target = 2
 * Dry run:
 * - Initial count = 0
 * - Element 2: count = 0 + 1 = 1 (match found)
 * - Element 5: count = 1 (no match)
 * - Element 2: count = 1 + 1 = 2 (match found)
 * - Element 8: count = 2 (no match)
 * - Element 2: count = 2 + 1 = 3 (match found)
 * - Element 9: count = 3 (no match)
 * - Element 5: count = 3 (no match)
 * - Element 2: count = 3 + 1 = 4 (match found)
 * Final result: 4
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class CountOccurrences {
    
    /**
     * Method to count occurrences of a specific element in an array
     * @param arr The input array
     * @param target The element to count
     * @return The number of occurrences of the target element
     */
    public static int countOccurrences(int[] arr, int target) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        // Question: Count the occurrences of a specific element
        // Topics: Array traversal, counter variable, comparison operations, looping constructs
        // Algorithm: Initialize counter to 0, traverse array, increment counter when match found
        // Pseudocode: count = 0; for each element: if element == target: count++; return count
        // Example: [2, 5, 2, 8, 2, 9, 5, 2], target = 2 -> count = 4
        // Dry run: count=0→1→1→2→2→3→3→3→4
        // Time: O(n), Space: O(1)
        
        int[] array = {2, 5, 2, 8, 2, 9, 5, 2};
        int target = 2;
        int result = countOccurrences(array, target);
        System.out.println("Array: [2, 5, 2, 8, 2, 9, 5, 2]");
        System.out.println("Target element: " + target);
        System.out.println("Number of occurrences: " + result);
    }
}
