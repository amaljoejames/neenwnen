/**
 * Question: Find the element that appears only once while others appear twice.
 * 
 * Topics Included:
 * - Array manipulation
 * - XOR operation
 * - Bit manipulation
 * - Unique element detection
 * 
 * Algorithm:
 * 1. Initialize a variable result to 0
 * 2. Traverse through each element of the array
 * 3. Perform XOR operation between result and current element
 * 4. XOR has the property that a ^ a = 0 and a ^ 0 = a
 * 5. Since all elements appear twice except one, their XOR will cancel out
 * 6. The remaining result will be the unique element
 * 7. Return the result
 * 
 * Pseudocode:
 * FUNCTION findSingleOccurrence(array):
 *     result = 0
 *     
 *     FOR each element in array:
 *         result = result XOR element
 *     
 *     RETURN result
 * 
 * Explanation with Example:
 * Input array: [2, 3, 5, 4, 5, 3, 4] (2 appears once)
 * Dry run:
 * - Initial result = 0
 * - Element 2: result = 0 ^ 2 = 2
 * - Element 3: result = 2 ^ 3 = 1
 * - Element 5: result = 1 ^ 5 = 4
 * - Element 4: result = 4 ^ 4 = 0
 * - Element 5: result = 0 ^ 5 = 5
 * - Element 3: result = 5 ^ 3 = 6
 * - Element 4: result = 6 ^ 4 = 2
 * Final result: 2
 * 
 * Explanation: 3^3=0, 4^4=0, 5^5=0, so result = 2^0^0 = 2
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class SingleOccurrence {
    
    /**
     * Method to find the element that appears only once while others appear twice
     * @param arr The input array
     * @return The element that appears only once
     */
    public static int findSingleOccurrence(int[] arr) {
        int result = 0;
        
        for (int i = 0; i < arr.length; i++) {
            result = result ^ arr[i];
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Question: Find the element that appears only once while others appear twice
        // Topics: Array manipulation, XOR operation, bit manipulation, unique element detection
        // Algorithm: Use XOR to cancel out duplicate elements, leaving the unique one
        // Pseudocode: result = 0; for each element: result = result XOR element; return result
        // Example: [2, 3, 5, 4, 5, 3, 4] → unique element = 2
        // Dry run: result=0 → ^2=2 → ^3=1 → ^5=4 → ^4=0 → ^5=5 → ^3=6 → ^4=2 → result=2
        // Time: O(n), Space: O(1)
        
        int[] array = {2, 3, 5, 4, 5, 3, 4};
        int result = findSingleOccurrence(array);
        
        System.out.println("Array: [2, 3, 5, 4, 5, 3, 4]");
        System.out.println("Element that appears only once: " + result);
    }
}
