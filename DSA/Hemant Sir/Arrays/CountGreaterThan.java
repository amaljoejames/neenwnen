/**
 * Question: Count the number of elements greater than a given number.
 * 
 * Topics Included:
 * - Array traversal
 * - Comparison operations
 * - Counter variable
 * - Conditional statements
 * 
 * Algorithm:
 * 1. Initialize a counter to 0
 * 2. Traverse through each element of the array
 * 3. For each element, check if it's greater than the given number
 * 4. If yes, increment the counter
 * 5. Return the final count
 * 
 * Pseudocode:
 * FUNCTION countGreaterThan(array, target):
 *     count = 0
 *     FOR each element in array:
 *         IF element > target:
 *             count = count + 1
 *     RETURN count
 * 
 * Explanation with Example:
 * Input array: [5, 8, 2, 10, 3, 7, 12, 4], target = 6
 * Dry run:
 * - Initial count = 0
 * - Element 5: 5 > 6? No (count = 0)
 * - Element 8: 8 > 6? Yes (count = 1)
 * - Element 2: 2 > 6? No (count = 1)
 * - Element 10: 10 > 6? Yes (count = 2)
 * - Element 3: 3 > 6? No (count = 2)
 * - Element 7: 7 > 6? Yes (count = 3)
 * - Element 12: 12 > 6? Yes (count = 4)
 * - Element 4: 4 > 6? No (count = 4)
 * Final result: 4
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class CountGreaterThan {
    
    /**
     * Method to count the number of elements greater than a given number
     * @param arr The input array
     * @param target The target number
     * @return The count of elements greater than target
     */
    public static int countGreaterThan(int[] arr, int target) {
        int count = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > target) {
                count++;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        // Question: Count the number of elements greater than a given number
        // Topics: Array traversal, comparison operations, counter variable, conditional statements
        // Algorithm: Initialize counter, traverse array, increment counter when element > target
        // Pseudocode: count = 0; for each element: if element > target: count++; return count
        // Example: [5, 8, 2, 10, 3, 7, 12, 4], target=6 → count=4
        // Dry run: count=0 → 5>6?no → 8>6?yes(count=1) → 2>6?no → 10>6?yes(count=2) → 3>6?no → 7>6?yes(count=3) → 12>6?yes(count=4) → 4>6?no → result=4
        // Time: O(n), Space: O(1)
        
        int[] array = {5, 8, 2, 10, 3, 7, 12, 4};
        int target = 6;
        int result = countGreaterThan(array, target);
        
        System.out.println("Array: [5, 8, 2, 10, 3, 7, 12, 4]");
        System.out.println("Target number: " + target);
        System.out.println("Number of elements greater than " + target + ": " + result);
    }
}
