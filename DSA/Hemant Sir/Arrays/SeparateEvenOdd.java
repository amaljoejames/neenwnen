/**
 * Question: Separate even and odd elements into two arrays.
 * 
 * Topics Included:
 * - Array traversal
 * - Conditional statements
 * - Dynamic array sizing
 * - Multiple array creation
 * 
 * Algorithm:
 * 1. First pass: Count even and odd elements to determine array sizes
 * 2. Create two arrays with appropriate sizes
 * 3. Second pass: Fill even and odd arrays with respective elements
 * 4. Return both arrays
 * 
 * Pseudocode:
 * FUNCTION separateEvenOdd(array):
 *     evenCount = 0, oddCount = 0
 *     FOR each element in array:
 *         IF element % 2 == 0: evenCount++
 *         ELSE: oddCount++
 *     
 *     evenArray = new int[evenCount]
 *     oddArray = new int[oddCount]
 *     evenIndex = 0, oddIndex = 0
 *     
 *     FOR each element in array:
 *         IF element % 2 == 0:
 *             evenArray[evenIndex] = element
 *             evenIndex++
 *         ELSE:
 *             oddArray[oddIndex] = element
 *             oddIndex++
 *     
 *     RETURN (evenArray, oddArray)
 * 
 * Explanation with Example:
 * Input array: [4, 7, 2, 9, 6, 3, 8, 5]
 * Dry run:
 * - First pass: evenCount = 4, oddCount = 4
 * - Create evenArray[4], oddArray[4]
 * - Second pass:
 *   - 4 (even) → evenArray[0] = 4
 *   - 7 (odd) → oddArray[0] = 7
 *   - 2 (even) → evenArray[1] = 2
 *   - 9 (odd) → oddArray[1] = 9
 *   - 6 (even) → evenArray[2] = 6
 *   - 3 (odd) → oddArray[2] = 3
 *   - 8 (even) → evenArray[3] = 8
 *   - 5 (odd) → oddArray[3] = 5
 * Final result: evenArray = [4, 2, 6, 8], oddArray = [7, 9, 3, 5]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(n) - requires extra space for two new arrays
 */

public class SeparateEvenOdd {
    
    /**
     * Method to separate even and odd elements into two arrays
     * @param arr The input array
     * @return A 2D array where result[0] is even array and result[1] is odd array
     */
    public static int[][] separateEvenOdd(int[] arr) {
        // First pass: count even and odd elements
        int evenCount = 0;
        int oddCount = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }
        
        // Create arrays with appropriate sizes
        int[] evenArray = new int[evenCount];
        int[] oddArray = new int[oddCount];
        int evenIndex = 0;
        int oddIndex = 0;
        
        // Second pass: fill the arrays
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                evenArray[evenIndex] = arr[i];
                evenIndex++;
            } else {
                oddArray[oddIndex] = arr[i];
                oddIndex++;
            }
        }
        
        return new int[][]{evenArray, oddArray};
    }
    
    public static void main(String[] args) {
        // Question: Separate even and odd elements into two arrays
        // Topics: Array traversal, conditional statements, dynamic array sizing, multiple array creation
        // Algorithm: Count even/odd elements, create arrays, fill with respective elements
        // Pseudocode: count even/odd → create arrays → fill arrays → return (evenArray, oddArray)
        // Example: [4, 7, 2, 9, 6, 3, 8, 5] → even=[4,2,6,8], odd=[7,9,3,5]
        // Dry run: evenCount=4,oddCount=4 → evenArray[4],oddArray[4] → fill: 4,7,2,9,6,3,8,5
        // Time: O(n), Space: O(n)
        
        int[] array = {4, 7, 2, 9, 6, 3, 8, 5};
        int[][] result = separateEvenOdd(array);
        int[] evenArray = result[0];
        int[] oddArray = result[1];
        
        System.out.println("Original array: [4, 7, 2, 9, 6, 3, 8, 5]");
        
        System.out.print("Even elements: [");
        for (int i = 0; i < evenArray.length; i++) {
            System.out.print(evenArray[i]);
            if (i < evenArray.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        System.out.print("Odd elements: [");
        for (int i = 0; i < oddArray.length; i++) {
            System.out.print(oddArray[i]);
            if (i < oddArray.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
