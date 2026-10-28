/**
 * Question: Find the sum of all elements in an array.
 * 
 * Topics Included:
 * - Array traversal
 * - Basic arithmetic operations
 * - Looping constructs
 * 
 * Algorithm:
 * 1. Initialize a variable sum to 0
 * 2. Iterate through each element of the array
 * 3. Add each element to the sum
 * 4. Return the final sum
 * 
 * Pseudocode:
 * FUNCTION findSum(array):
 *     sum = 0
 *     FOR each element in array:
 *         sum = sum + element
 *     RETURN sum
 * 
 * Explanation with Example:
 * Input array: [3, 7, 2, 9, 4]
 * Dry run:
 * - Initial sum = 0
 * - After 1st element (3): sum = 0 + 3 = 3
 * - After 2nd element (7): sum = 3 + 7 = 10
 * - After 3rd element (2): sum = 10 + 2 = 12
 * - After 4th element (9): sum = 12 + 9 = 21
 * - After 5th element (4): sum = 21 + 4 = 25
 * Final result: 25
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class SumOfElements {
    
    /**
     * Method to find the sum of all elements in an array
     * @param arr The input array
     * @return The sum of all elements
     */
    public static int findSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
    
    public static void main(String[] args) {
        // Question: Find the sum of all elements in an array
        // Topics: Array traversal, basic arithmetic operations, looping constructs
        // Algorithm: Initialize sum to 0, iterate through array, add each element to sum
        // Pseudocode: sum = 0; for each element: sum += element; return sum
        // Example: [3, 7, 2, 9, 4] -> sum = 25
        // Dry run: 0+3=3, 3+7=10, 10+2=12, 12+9=21, 21+4=25
        // Time: O(n), Space: O(1)
        
        int[] array = {3, 7, 2, 9, 4};
        int result = findSum(array);
        System.out.println("Array: [3, 7, 2, 9, 4]");
        System.out.println("Sum of all elements: " + result);
    }
}
