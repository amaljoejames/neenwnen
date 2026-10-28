/**
 * Question: Check if an array contains a given number.
 * 
 * Topics Included:
 * - Array traversal
 * - Linear search
 * - Boolean operations
 * - Conditional statements
 * 
 * Algorithm:
 * 1. Traverse through each element of the array
 * 2. Check if any element matches the target number
 * 3. If found, return true
 * 4. If not found after complete traversal, return false
 * 
 * Pseudocode:
 * FUNCTION containsNumber(array, target):
 *     FOR each element in array:
 *         IF element == target:
 *             RETURN true
 *     RETURN false
 * 
 * Explanation with Example:
 * Input array: [4, 9, 2, 7, 5, 11], target = 7
 * Dry run:
 * - Element 4: 4 != 7 (continue)
 * - Element 9: 9 != 7 (continue)
 * - Element 2: 2 != 7 (continue)
 * - Element 7: 7 == 7 (found! return true)
 * Final result: true
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class ContainsNumber {
    
    /**
     * Method to check if an array contains a given number
     * @param arr The input array
     * @param target The number to search for
     * @return true if the number is found, false otherwise
     */
    public static boolean containsNumber(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        // Question: Check if an array contains a given number
        // Topics: Array traversal, linear search, boolean operations, conditional statements
        // Algorithm: Traverse array, check if any element matches target
        // Pseudocode: for each element: if element == target: return true; return false
        // Example: [4, 9, 2, 7, 5, 11], target = 7 → true
        // Dry run: 4≠7, 9≠7, 2≠7, 7=7 → return true
        // Time: O(n), Space: O(1)
        
        int[] array = {4, 9, 2, 7, 5, 11};
        int target = 7;
        boolean result = containsNumber(array, target);
        System.out.println("Array: [4, 9, 2, 7, 5, 11]");
        System.out.println("Target number: " + target);
        System.out.println("Array contains target: " + result);
    }
}
