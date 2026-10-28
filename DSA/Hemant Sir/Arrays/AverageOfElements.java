/**
 * Question: Find the average of array elements.
 * 
 * Topics Included:
 * - Array traversal
 * - Arithmetic operations (sum and division)
 * - Type casting
 * - Looping constructs
 * 
 * Algorithm:
 * 1. Calculate the sum of all elements
 * 2. Divide the sum by the number of elements
 * 3. Return the average
 * 
 * Pseudocode:
 * FUNCTION findAverage(array):
 *     sum = 0
 *     FOR each element in array:
 *         sum = sum + element
 *     average = sum / length of array
 *     RETURN average
 * 
 * Explanation with Example:
 * Input array: [4, 8, 6, 2, 10]
 * Dry run:
 * - Calculate sum: 4 + 8 + 6 + 2 + 10 = 30
 * - Number of elements: 5
 * - Average: 30 / 5 = 6.0
 * Final result: 6.0
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class AverageOfElements {
    
    /**
     * Method to find the average of array elements
     * @param arr The input array
     * @return The average of all elements as double
     */
    public static double findAverage(int[] arr) {
        if (arr.length == 0) return 0.0;
        
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return (double) sum / arr.length;
    }
    
    public static void main(String[] args) {
        // Question: Find the average of array elements
        // Topics: Array traversal, arithmetic operations, type casting, looping constructs
        // Algorithm: Calculate sum, divide by number of elements
        // Pseudocode: sum = 0; for each element: sum += element; average = sum / length; return average
        // Example: [4, 8, 6, 2, 10] -> average = 6.0
        // Dry run: sum = 4+8+6+2+10=30, average = 30/5=6.0
        // Time: O(n), Space: O(1)
        
        int[] array = {4, 8, 6, 2, 10};
        double result = findAverage(array);
        System.out.println("Array: [4, 8, 6, 2, 10]");
        System.out.println("Average of elements: " + result);
    }
}
