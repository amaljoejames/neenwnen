/**
 * Question: Find the sum of even and sum of odd elements separately.
 * 
 * Topics Included:
 * - Array traversal
 * - Modulo operation
 * - Conditional statements
 * - Arithmetic operations
 * 
 * Algorithm:
 * 1. Initialize two variables: evenSum and oddSum to 0
 * 2. Traverse through each element of the array
 * 3. For each element, check if it's even or odd using modulo operator
 * 4. Add the element to the appropriate sum
 * 5. Return both sums
 * 
 * Pseudocode:
 * FUNCTION sumEvenOdd(array):
 *     evenSum = 0
 *     oddSum = 0
 *     
 *     FOR each element in array:
 *         IF element % 2 == 0:
 *             evenSum = evenSum + element
 *         ELSE:
 *             oddSum = oddSum + element
 *     
 *     RETURN [evenSum, oddSum]
 * 
 * Explanation with Example:
 * Input array: [3, 8, 5, 12, 7, 4, 9, 2]
 * Dry run:
 * - Initial: evenSum = 0, oddSum = 0
 * - Element 3: 3 % 2 = 1 (odd) → evenSum = 0, oddSum = 3
 * - Element 8: 8 % 2 = 0 (even) → evenSum = 8, oddSum = 3
 * - Element 5: 5 % 2 = 1 (odd) → evenSum = 8, oddSum = 8
 * - Element 12: 12 % 2 = 0 (even) → evenSum = 20, oddSum = 8
 * - Element 7: 7 % 2 = 1 (odd) → evenSum = 20, oddSum = 15
 * - Element 4: 4 % 2 = 0 (even) → evenSum = 24, oddSum = 15
 * - Element 9: 9 % 2 = 1 (odd) → evenSum = 24, oddSum = 24
 * - Element 2: 2 % 2 = 0 (even) → evenSum = 26, oddSum = 24
 * Final result: evenSum = 26, oddSum = 24
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class SumEvenOdd {
    
    /**
     * Method to find the sum of even and odd elements separately
     * @param arr The input array
     * @return An array where result[0] is even sum and result[1] is odd sum
     */
    public static int[] sumEvenOdd(int[] arr) {
        int evenSum = 0;
        int oddSum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                evenSum += arr[i];
            } else {
                oddSum += arr[i];
            }
        }
        
        return new int[]{evenSum, oddSum};
    }
    
    public static void main(String[] args) {
        // Question: Find the sum of even and sum of odd elements separately
        // Topics: Array traversal, modulo operation, conditional statements, arithmetic operations
        // Algorithm: Initialize sums, traverse array, use modulo to check even/odd, add to appropriate sum
        // Pseudocode: evenSum=0, oddSum=0; for each element: if element%2==0: evenSum+=element; else: oddSum+=element; return [evenSum, oddSum]
        // Example: [3, 8, 5, 12, 7, 4, 9, 2] → evenSum=26, oddSum=24
        // Dry run: evenSum=0,oddSum=0 → evenSum=0,oddSum=3 → evenSum=8,oddSum=3 → evenSum=8,oddSum=8 → evenSum=20,oddSum=8 → evenSum=20,oddSum=15 → evenSum=24,oddSum=15 → evenSum=24,oddSum=24 → evenSum=26,oddSum=24
        // Time: O(n), Space: O(1)
        
        int[] array = {3, 8, 5, 12, 7, 4, 9, 2};
        int[] result = sumEvenOdd(array);
        
        System.out.println("Array: [3, 8, 5, 12, 7, 4, 9, 2]");
        System.out.println("Sum of even elements: " + result[0]);
        System.out.println("Sum of odd elements: " + result[1]);
    }
}
