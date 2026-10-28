/**
 * Question: Count how many elements are even and how many are odd.
 * 
 * Topics Included:
 * - Array traversal
 * - Modulo operation
 * - Counter variables
 * - Conditional statements
 * 
 * Algorithm:
 * 1. Initialize two counters: evenCount and oddCount to 0
 * 2. Traverse through each element of the array
 * 3. For each element, check if it's even or odd using modulo operator
 * 4. Increment the appropriate counter
 * 5. Return both counts
 * 
 * Pseudocode:
 * FUNCTION countEvenOdd(array):
 *     evenCount = 0
 *     oddCount = 0
 *     FOR each element in array:
 *         IF element % 2 == 0:
 *             evenCount = evenCount + 1
 *         ELSE:
 *             oddCount = oddCount + 1
 *     RETURN (evenCount, oddCount)
 * 
 * Explanation with Example:
 * Input array: [3, 8, 5, 12, 7, 4, 9, 2]
 * Dry run:
 * - Initial: evenCount = 0, oddCount = 0
 * - Element 3: 3 % 2 = 1 (odd) → evenCount = 0, oddCount = 1
 * - Element 8: 8 % 2 = 0 (even) → evenCount = 1, oddCount = 1
 * - Element 5: 5 % 2 = 1 (odd) → evenCount = 1, oddCount = 2
 * - Element 12: 12 % 2 = 0 (even) → evenCount = 2, oddCount = 2
 * - Element 7: 7 % 2 = 1 (odd) → evenCount = 2, oddCount = 3
 * - Element 4: 4 % 2 = 0 (even) → evenCount = 3, oddCount = 3
 * - Element 9: 9 % 2 = 1 (odd) → evenCount = 3, oddCount = 4
 * - Element 2: 2 % 2 = 0 (even) → evenCount = 4, oddCount = 4
 * Final result: evenCount = 4, oddCount = 4
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class CountEvenOdd {
    
    /**
     * Method to count even and odd elements in an array
     * @param arr The input array
     * @return An array where result[0] is even count and result[1] is odd count
     */
    public static int[] countEvenOdd(int[] arr) {
        int evenCount = 0;
        int oddCount = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }
        
        return new int[]{evenCount, oddCount};
    }
    
    public static void main(String[] args) {
        // Question: Count how many elements are even and how many are odd
        // Topics: Array traversal, modulo operation, counter variables, conditional statements
        // Algorithm: Initialize counters, traverse array, use modulo to check even/odd
        // Pseudocode: evenCount=0, oddCount=0; for each element: if element%2==0: evenCount++; else: oddCount++; return (evenCount, oddCount)
        // Example: [3, 8, 5, 12, 7, 4, 9, 2] → even=4, odd=4
        // Dry run: even=0,odd=0 → even=0,odd=1 → even=1,odd=1 → even=1,odd=2 → even=2,odd=2 → even=2,odd=3 → even=3,odd=3 → even=3,odd=4 → even=4,odd=4
        // Time: O(n), Space: O(1)
        
        int[] array = {3, 8, 5, 12, 7, 4, 9, 2};
        int[] result = countEvenOdd(array);
        System.out.println("Array: [3, 8, 5, 12, 7, 4, 9, 2]");
        System.out.println("Number of even elements: " + result[0]);
        System.out.println("Number of odd elements: " + result[1]);
    }
}
