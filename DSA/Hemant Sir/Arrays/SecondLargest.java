/**
 * Question: Find the second largest element in an array.
 * 
 * Topics Included:
 * - Array traversal
 * - Finding maximum values
 * - Multiple variable tracking
 * - Conditional statements
 * 
 * Algorithm:
 * 1. Initialize largest and secondLargest to minimum possible values
 * 2. Traverse through each element of the array
 * 3. If current element is greater than largest:
 *    - Update secondLargest to largest
 *    - Update largest to current element
 * 4. Else if current element is greater than secondLargest and not equal to largest:
 *    - Update secondLargest to current element
 * 5. Return secondLargest
 * 
 * Pseudocode:
 * FUNCTION findSecondLargest(array):
 *     IF length < 2: RETURN -1
 *     
 *     largest = Integer.MIN_VALUE
 *     secondLargest = Integer.MIN_VALUE
 *     
 *     FOR each element in array:
 *         IF element > largest:
 *             secondLargest = largest
 *             largest = element
 *         ELSE IF element > secondLargest AND element != largest:
 *             secondLargest = element
 *     
 *     RETURN secondLargest
 * 
 * Explanation with Example:
 * Input array: [12, 35, 1, 10, 34, 1]
 * Dry run:
 * - Initial: largest = -∞, secondLargest = -∞
 * - Element 12: 12 > -∞ → largest = 12, secondLargest = -∞
 * - Element 35: 35 > 12 → largest = 35, secondLargest = 12
 * - Element 1: 1 ≤ 35 and 1 ≤ 12 (no change)
 * - Element 10: 10 ≤ 35 and 10 ≤ 12 (no change)
 * - Element 34: 34 ≤ 35 and 34 > 12 → secondLargest = 34
 * - Element 1: 1 ≤ 35 and 1 ≤ 34 (no change)
 * Final result: secondLargest = 34
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class SecondLargest {
    
    /**
     * Method to find the second largest element in an array
     * @param arr The input array
     * @return The second largest element, or -1 if not found
     */
    public static int findSecondLargest(int[] arr) {
        if (arr.length < 2) {
            return -1;
        }
        
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }
        
        return secondLargest;
    }
    
    public static void main(String[] args) {
        // Question: Find the second largest element in an array
        // Topics: Array traversal, finding maximum values, multiple variable tracking, conditional statements
        // Algorithm: Track largest and second largest while traversing array
        // Pseudocode: largest = -∞, secondLargest = -∞; for each element: update largest and secondLargest as needed; return secondLargest
        // Example: [12, 35, 1, 10, 34, 1] → second largest = 34
        // Dry run: largest=-∞,secondLargest=-∞ → 12→largest=12 → 35→largest=35,secondLargest=12 → 34→secondLargest=34
        // Time: O(n), Space: O(1)
        
        int[] array = {12, 35, 1, 10, 34, 1};
        int result = findSecondLargest(array);
        
        System.out.println("Array: [12, 35, 1, 10, 34, 1]");
        if (result != Integer.MIN_VALUE) {
            System.out.println("Second largest element: " + result);
        } else {
            System.out.println("Second largest element not found");
        }
    }
}
