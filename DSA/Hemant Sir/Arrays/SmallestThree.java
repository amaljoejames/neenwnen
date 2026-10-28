/**
 * Question: Find the smallest three elements in an array.
 * 
 * Topics Included:
 * - Array traversal
 * - Finding minimum values
 * - Multiple variable tracking
 * - Conditional statements
 * 
 * Algorithm:
 * 1. Initialize three variables to store smallest, second smallest, and third smallest
 * 2. Set them to maximum possible values initially
 * 3. Traverse through each element of the array
 * 4. Update the three variables based on comparisons
 * 5. Return the three smallest elements
 * 
 * Pseudocode:
 * FUNCTION findSmallestThree(array):
 *     IF length < 3: RETURN null
 *     
 *     first = Integer.MAX_VALUE
 *     second = Integer.MAX_VALUE
 *     third = Integer.MAX_VALUE
 *     
 *     FOR each element in array:
 *         IF element < first:
 *             third = second
 *             second = first
 *             first = element
 *         ELSE IF element < second AND element != first:
 *             third = second
 *             second = element
 *         ELSE IF element < third AND element != second AND element != first:
 *             third = element
 *     
 *     RETURN [first, second, third]
 * 
 * Explanation with Example:
 * Input array: [12, 13, 1, 10, 34, 1]
 * Dry run:
 * - Initial: first = +∞, second = +∞, third = +∞
 * - Element 12: first = 12, second = +∞, third = +∞
 * - Element 13: first = 12, second = 13, third = +∞
 * - Element 1: first = 1, second = 12, third = 13
 * - Element 10: first = 1, second = 10, third = 12
 * - Element 34: no change
 * - Element 1: no change (duplicate)
 * Final result: [1, 10, 12]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class SmallestThree {
    
    /**
     * Method to find the smallest three elements in an array
     * @param arr The input array
     * @return An array containing the three smallest elements
     */
    public static int[] findSmallestThree(int[] arr) {
        if (arr.length < 3) {
            return null;
        }
        
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        int third = Integer.MAX_VALUE;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < first) {
                third = second;
                second = first;
                first = arr[i];
            } else if (arr[i] < second && arr[i] != first) {
                third = second;
                second = arr[i];
            } else if (arr[i] < third && arr[i] != second && arr[i] != first) {
                third = arr[i];
            }
        }
        
        return new int[]{first, second, third};
    }
    
    public static void main(String[] args) {
        // Question: Find the smallest three elements in an array
        // Topics: Array traversal, finding minimum values, multiple variable tracking, conditional statements
        // Algorithm: Track first, second, and third smallest while traversing array
        // Pseudocode: first=second=third=+∞; for each element: update first, second, third as needed; return [first, second, third]
        // Example: [12, 13, 1, 10, 34, 1] → [1, 10, 12]
        // Dry run: first=+∞,second=+∞,third=+∞ → 12→first=12 → 13→second=13 → 1→first=1,second=12,third=13 → 10→second=10,third=12
        // Time: O(n), Space: O(1)
        
        int[] array = {12, 13, 1, 10, 34, 1};
        int[] result = findSmallestThree(array);
        
        System.out.println("Array: [12, 13, 1, 10, 34, 1]");
        if (result != null) {
            System.out.println("Three smallest elements:");
            System.out.println("First smallest: " + result[0]);
            System.out.println("Second smallest: " + result[1]);
            System.out.println("Third smallest: " + result[2]);
        } else {
            System.out.println("Array has less than 3 elements");
        }
    }
}
