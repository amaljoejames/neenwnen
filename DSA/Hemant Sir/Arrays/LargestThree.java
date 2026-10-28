/**
 * Question: Find the largest three elements in an array.
 * 
 * Topics Included:
 * - Array traversal
 * - Finding maximum values
 * - Multiple variable tracking
 * - Conditional statements
 * 
 * Algorithm:
 * 1. Initialize three variables to store largest, second largest, and third largest
 * 2. Set them to minimum possible values initially
 * 3. Traverse through each element of the array
 * 4. Update the three variables based on comparisons
 * 5. Return the three largest elements
 * 
 * Pseudocode:
 * FUNCTION findLargestThree(array):
 *     IF length < 3: RETURN null
 *     
 *     first = Integer.MIN_VALUE
 *     second = Integer.MIN_VALUE
 *     third = Integer.MIN_VALUE
 *     
 *     FOR each element in array:
 *         IF element > first:
 *             third = second
 *             second = first
 *             first = element
 *         ELSE IF element > second AND element != first:
 *             third = second
 *             second = element
 *         ELSE IF element > third AND element != second AND element != first:
 *             third = element
 *     
 *     RETURN [first, second, third]
 * 
 * Explanation with Example:
 * Input array: [12, 13, 1, 10, 34, 1]
 * Dry run:
 * - Initial: first = -∞, second = -∞, third = -∞
 * - Element 12: first = 12, second = -∞, third = -∞
 * - Element 13: first = 13, second = 12, third = -∞
 * - Element 1: no change
 * - Element 10: second = 12, third = 10 (10 < 12 but > -∞)
 * - Element 34: first = 34, second = 13, third = 12
 * - Element 1: no change
 * Final result: [34, 13, 12]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class LargestThree {
    
    /**
     * Method to find the largest three elements in an array
     * @param arr The input array
     * @return An array containing the three largest elements
     */
    public static int[] findLargestThree(int[] arr) {
        if (arr.length < 3) {
            return null;
        }
        
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > first) {
                third = second;
                second = first;
                first = arr[i];
            } else if (arr[i] > second && arr[i] != first) {
                third = second;
                second = arr[i];
            } else if (arr[i] > third && arr[i] != second && arr[i] != first) {
                third = arr[i];
            }
        }
        
        return new int[]{first, second, third};
    }
    
    public static void main(String[] args) {
        // Question: Find the largest three elements in an array
        // Topics: Array traversal, finding maximum values, multiple variable tracking, conditional statements
        // Algorithm: Track first, second, and third largest while traversing array
        // Pseudocode: first=second=third=-∞; for each element: update first, second, third as needed; return [first, second, third]
        // Example: [12, 13, 1, 10, 34, 1] → [34, 13, 12]
        // Dry run: first=-∞,second=-∞,third=-∞ → 12→first=12 → 13→first=13,second=12 → 10→third=10 → 34→first=34,second=13,third=12
        // Time: O(n), Space: O(1)
        
        int[] array = {12, 13, 1, 10, 34, 1};
        int[] result = findLargestThree(array);
        
        System.out.println("Array: [12, 13, 1, 10, 34, 1]");
        if (result != null) {
            System.out.println("Three largest elements:");
            System.out.println("First largest: " + result[0]);
            System.out.println("Second largest: " + result[1]);
            System.out.println("Third largest: " + result[2]);
        } else {
            System.out.println("Array has less than 3 elements");
        }
    }
}
