/**
 * Question: Remove duplicates from an array.
 * 
 * Topics Included:
 * - Array manipulation
 * - Duplicate detection
 * - Dynamic array sizing
 * - Two-pointer technique
 * 
 * Algorithm:
 * 1. Sort the array first to bring duplicates together
 * 2. Use two-pointer technique to remove duplicates
 * 3. One pointer tracks unique elements, another traverses the array
 * 4. Create a new array with unique elements only
 * 5. Return the array without duplicates
 * 
 * Pseudocode:
 * FUNCTION removeDuplicates(array):
 *     IF length <= 1: RETURN array
 *     
 *     // Sort the array first
 *     sort array
 *     
 *     uniqueCount = 1
 *     FOR i from 1 to length-1:
 *         IF array[i] != array[i-1]:
 *             uniqueCount++
 *     
 *     resultArray = new int[uniqueCount]
 *     resultArray[0] = array[0]
 *     index = 1
 *     
 *     FOR i from 1 to length-1:
 *         IF array[i] != array[i-1]:
 *             resultArray[index] = array[i]
 *             index++
 *     
 *     RETURN resultArray
 * 
 * Explanation with Example:
 * Input array: [4, 2, 5, 2, 8, 4, 5, 9]
 * Dry run:
 * - Step 1: Sort array → [2, 2, 4, 4, 5, 5, 8, 9]
 * - Step 2: Count unique elements → 5 unique elements (2, 4, 5, 8, 9)
 * - Step 3: Create resultArray[5]
 * - Step 4: Fill unique elements:
 *   - resultArray[0] = 2
 *   - Skip second 2 (duplicate)
 *   - resultArray[1] = 4
 *   - Skip second 4 (duplicate)
 *   - resultArray[2] = 5
 *   - Skip second 5 (duplicate)
 *   - resultArray[3] = 8
 *   - resultArray[4] = 9
 * Final result: [2, 4, 5, 8, 9]
 * 
 * Time Complexity: O(n log n) due to sorting, where n is the number of elements
 * Space Complexity: O(n) - requires extra space for the result array
 */

public class RemoveDuplicates {
    
    /**
     * Method to remove duplicates from an array
     * @param arr The input array
     * @return A new array with duplicates removed
     */
    public static int[] removeDuplicates(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        
        // Sort the array first
        java.util.Arrays.sort(arr);
        
        // Count unique elements
        int uniqueCount = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                uniqueCount++;
            }
        }
        
        // Create result array with unique elements
        int[] resultArray = new int[uniqueCount];
        resultArray[0] = arr[0];
        int index = 1;
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                resultArray[index] = arr[i];
                index++;
            }
        }
        
        return resultArray;
    }
    
    public static void main(String[] args) {
        // Question: Remove duplicates from an array
        // Topics: Array manipulation, duplicate detection, dynamic array sizing, two-pointer technique
        // Algorithm: Sort array, count unique elements, create new array with unique elements
        // Pseudocode: sort array → count unique → create result array → fill with unique elements → return result
        // Example: [4, 2, 5, 2, 8, 4, 5, 9] → [2, 4, 5, 8, 9]
        // Dry run: sort → [2,2,4,4,5,5,8,9] → unique count=5 → result=[2,4,5,8,9]
        // Time: O(n log n), Space: O(n)
        
        int[] array = {4, 2, 5, 2, 8, 4, 5, 9};
        int[] result = removeDuplicates(array);
        
        System.out.println("Original array: [4, 2, 5, 2, 8, 4, 5, 9]");
        System.out.print("After removing duplicates: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
