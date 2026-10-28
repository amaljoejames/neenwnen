/**
 * Question: Find the second smallest element in an array.
 * 
 * Topics Included:
 * - Array traversal
 * - Finding minimum values
 * - Multiple variable tracking
 * - Conditional statements
 * 
 * Algorithm:
 * 1. Initialize smallest and secondSmallest to maximum possible values
 * 2. Traverse through each element of the array
 * 3. If current element is smaller than smallest:
 *    - Update secondSmallest to smallest
 *    - Update smallest to current element
 * 4. Else if current element is smaller than secondSmallest and not equal to smallest:
 *    - Update secondSmallest to current element
 * 5. Return secondSmallest
 * 
 * Pseudocode:
 * FUNCTION findSecondSmallest(array):
 *     IF length < 2: RETURN -1
 *     
 *     smallest = Integer.MAX_VALUE
 *     secondSmallest = Integer.MAX_VALUE
 *     
 *     FOR each element in array:
 *         IF element < smallest:
 *             secondSmallest = smallest
 *             smallest = element
 *         ELSE IF element < secondSmallest AND element != smallest:
 *             secondSmallest = element
 *     
 *     RETURN secondSmallest
 * 
 * Explanation with Example:
 * Input array: [12, 35, 1, 10, 34, 1]
 * Dry run:
 * - Initial: smallest = +∞, secondSmallest = +∞
 * - Element 12: 12 < +∞ → smallest = 12, secondSmallest = +∞
 * - Element 35: 35 ≥ 12 and 35 < +∞ → secondSmallest = 35
 * - Element 1: 1 < 12 → secondSmallest = 12, smallest = 1
 * - Element 10: 10 ≥ 1 and 10 < 12 → secondSmallest = 10
 * - Element 34: 34 ≥ 1 and 34 ≥ 10 (no change)
 * - Element 1: 1 = smallest (no change)
 * Final result: secondSmallest = 10
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class SecondSmallest {
    
    /**
     * Method to find the second smallest element in an array
     * @param arr The input array
     * @return The second smallest element, or -1 if not found
     */
    public static int findSecondSmallest(int[] arr) {
        if (arr.length < 2) {
            return -1;
        }
        
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < smallest) {
                secondSmallest = smallest;
                smallest = arr[i];
            } else if (arr[i] < secondSmallest && arr[i] != smallest) {
                secondSmallest = arr[i];
            }
        }
        
        return secondSmallest;
    }
    
    public static void main(String[] args) {
        // Question: Find the second smallest element in an array
        // Topics: Array traversal, finding minimum values, multiple variable tracking, conditional statements
        // Algorithm: Track smallest and second smallest while traversing array
        // Pseudocode: smallest = +∞, secondSmallest = +∞; for each element: update smallest and secondSmallest as needed; return secondSmallest
        // Example: [12, 35, 1, 10, 34, 1] → second smallest = 10
        // Dry run: smallest=+∞,secondSmallest=+∞ → 12→smallest=12 → 35→secondSmallest=35 → 1→smallest=1,secondSmallest=12 → 10→secondSmallest=10
        // Time: O(n), Space: O(1)
        
        int[] array = {12, 35, 1, 10, 34, 1};
        int result = findSecondSmallest(array);
        
        System.out.println("Array: [12, 35, 1, 10, 34, 1]");
        if (result != Integer.MAX_VALUE) {
            System.out.println("Second smallest element: " + result);
        } else {
            System.out.println("Second smallest element not found");
        }
    }
}
