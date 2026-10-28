/**
 * Question: Merge two arrays into a third array.
 * 
 * Topics Included:
 * - Array manipulation
 * - Multiple array handling
 * - Index management
 * - Looping constructs
 * 
 * Algorithm:
 * 1. Create a new array with size equal to sum of both array lengths
 * 2. Copy all elements from first array to the merged array
 * 3. Copy all elements from second array to the merged array
 * 4. Return the merged array
 * 
 * Pseudocode:
 * FUNCTION mergeArrays(array1, array2):
 *     mergedArray = new int[length of array1 + length of array2]
 *     index = 0
 *     
 *     FOR each element in array1:
 *         mergedArray[index] = element
 *         index = index + 1
 *     
 *     FOR each element in array2:
 *         mergedArray[index] = element
 *         index = index + 1
 *     
 *     RETURN mergedArray
 * 
 * Explanation with Example:
 * Input arrays: [1, 3, 5] and [2, 4, 6, 8]
 * Dry run:
 * - Create mergedArray[7] (3 + 4 = 7)
 * - Copy from array1:
 *   - mergedArray[0] = 1
 *   - mergedArray[1] = 3
 *   - mergedArray[2] = 5
 * - Copy from array2:
 *   - mergedArray[3] = 2
 *   - mergedArray[4] = 4
 *   - mergedArray[5] = 6
 *   - mergedArray[6] = 8
 * Final result: [1, 3, 5, 2, 4, 6, 8]
 * 
 * Time Complexity: O(n + m) where n and m are lengths of the two arrays
 * Space Complexity: O(n + m) - requires extra space for the merged array
 */

public class MergeArrays {
    
    /**
     * Method to merge two arrays into a third array
     * @param array1 The first array
     * @param array2 The second array
     * @return A new array containing elements from both arrays
     */
    public static int[] mergeArrays(int[] array1, int[] array2) {
        int[] mergedArray = new int[array1.length + array2.length];
        int index = 0;
        
        // Copy elements from first array
        for (int i = 0; i < array1.length; i++) {
            mergedArray[index] = array1[i];
            index++;
        }
        
        // Copy elements from second array
        for (int i = 0; i < array2.length; i++) {
            mergedArray[index] = array2[i];
            index++;
        }
        
        return mergedArray;
    }
    
    public static void main(String[] args) {
        // Question: Merge two arrays into a third array
        // Topics: Array manipulation, multiple array handling, index management, looping constructs
        // Algorithm: Create merged array, copy elements from both arrays sequentially
        // Pseudocode: mergedArray = new int[len1+len2]; copy array1; copy array2; return mergedArray
        // Example: [1,3,5] + [2,4,6,8] → [1,3,5,2,4,6,8]
        // Dry run: create array[7] → copy 1,3,5 → copy 2,4,6,8 → [1,3,5,2,4,6,8]
        // Time: O(n+m), Space: O(n+m)
        
        int[] array1 = {1, 3, 5};
        int[] array2 = {2, 4, 6, 8};
        int[] mergedArray = mergeArrays(array1, array2);
        
        System.out.println("Array 1: [1, 3, 5]");
        System.out.println("Array 2: [2, 4, 6, 8]");
        System.out.print("Merged array: [");
        for (int i = 0; i < mergedArray.length; i++) {
            System.out.print(mergedArray[i]);
            if (i < mergedArray.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
