/**
 * Question: Find missing number in a sequence from 1 to n.
 * 
 * Topics Included:
 * - Array manipulation
 * - Mathematical series
 * - Summation formula
 * - Missing element detection
 * 
 * Algorithm:
 * 1. Calculate the expected sum of numbers from 1 to n using formula n*(n+1)/2
 * 2. Calculate the actual sum of elements in the array
 * 3. The missing number is the difference between expected and actual sum
 * 4. Return the missing number
 * 
 * Pseudocode:
 * FUNCTION findMissingNumber(array):
 *     n = length of array + 1  // Since one number is missing
 *     expectedSum = n * (n + 1) / 2
 *     actualSum = 0
 *     
 *     FOR each element in array:
 *         actualSum = actualSum + element
 *     
 *     RETURN expectedSum - actualSum
 * 
 * Explanation with Example:
 * Input array: [1, 2, 4, 6, 3, 7, 8] (missing 5)
 * Dry run:
 * - n = 7 + 1 = 8 (numbers should be from 1 to 8)
 * - Expected sum = 8 * (8 + 1) / 2 = 8 * 9 / 2 = 36
 * - Actual sum = 1 + 2 + 4 + 6 + 3 + 7 + 8 = 31
 * - Missing number = 36 - 31 = 5
 * Final result: 5
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class MissingNumbers {
    
    /**
     * Method to find the missing number in a sequence from 1 to n
     * @param arr The input array with one missing number
     * @return The missing number
     */
    public static int findMissingNumber(int[] arr) {
        int n = arr.length + 1; // Since one number is missing
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            actualSum += arr[i];
        }
        
        return expectedSum - actualSum;
    }
    
    public static void main(String[] args) {
        // Question: Find missing number in a sequence from 1 to n
        // Topics: Array manipulation, mathematical series, summation formula, missing element detection
        // Algorithm: Use summation formula to find expected sum, subtract actual sum to get missing number
        // Pseudocode: n = length + 1; expectedSum = n*(n+1)/2; actualSum = sum(array); return expectedSum - actualSum
        // Example: [1, 2, 4, 6, 3, 7, 8] → missing number = 5
        // Dry run: n=8, expectedSum=36, actualSum=31, missing=36-31=5
        // Time: O(n), Space: O(1)
        
        int[] array = {1, 2, 4, 6, 3, 7, 8};
        int result = findMissingNumber(array);
        
        System.out.println("Array: [1, 2, 4, 6, 3, 7, 8]");
        System.out.println("Missing number: " + result);
    }
}
