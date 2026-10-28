/**
 * Question: Copy elements from one array to another.
 * 
 * Topics Included:
 * - Array traversal
 * - Array copying
 * - Index mapping
 * - Looping constructs
 * 
 * Algorithm:
 * 1. Create a new array with the same length as the source array
 * 2. Traverse through each element of the source array
 * 3. Copy each element to the corresponding position in the destination array
 * 4. Return the copied array
 * 
 * Pseudocode:
 * FUNCTION copyArray(sourceArray):
 *     destinationArray = new int[length of sourceArray]
 *     FOR i from 0 to length-1:
 *         destinationArray[i] = sourceArray[i]
 *     RETURN destinationArray
 * 
 * Explanation with Example:
 * Input array: [5, 12, 8, 3, 9, 15]
 * Dry run:
 * - Create destinationArray[6]
 * - Index 0: destinationArray[0] = sourceArray[0] = 5
 * - Index 1: destinationArray[1] = sourceArray[1] = 12
 * - Index 2: destinationArray[2] = sourceArray[2] = 8
 * - Index 3: destinationArray[3] = sourceArray[3] = 3
 * - Index 4: destinationArray[4] = sourceArray[4] = 9
 * - Index 5: destinationArray[5] = sourceArray[5] = 15
 * Final result: [5, 12, 8, 3, 9, 15]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(n) - requires extra space for the new array
 */

public class CopyArray {
    
    /**
     * Method to copy elements from one array to another
     * @param sourceArray The source array to copy from
     * @return A new array containing copied elements
     */
    public static int[] copyArray(int[] sourceArray) {
        int[] destinationArray = new int[sourceArray.length];
        
        for (int i = 0; i < sourceArray.length; i++) {
            destinationArray[i] = sourceArray[i];
        }
        
        return destinationArray;
    }
    
    public static void main(String[] args) {
        // Question: Copy elements from one array to another
        // Topics: Array traversal, array copying, index mapping, looping constructs
        // Algorithm: Create new array, copy elements from source to destination
        // Pseudocode: destinationArray = new int[length]; for i=0 to length-1: destinationArray[i] = sourceArray[i]; return destinationArray
        // Example: [5, 12, 8, 3, 9, 15] → [5, 12, 8, 3, 9, 15]
        // Dry run: create array[6] → copy: 5→[0], 12→[1], 8→[2], 3→[3], 9→[4], 15→[5]
        // Time: O(n), Space: O(n)
        
        int[] sourceArray = {5, 12, 8, 3, 9, 15};
        int[] copiedArray = copyArray(sourceArray);
        
        System.out.println("Source array: [5, 12, 8, 3, 9, 15]");
        System.out.print("Copied array: [");
        for (int i = 0; i < copiedArray.length; i++) {
            System.out.print(copiedArray[i]);
            if (i < copiedArray.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        // Verify that the arrays are separate objects
        copiedArray[0] = 100;
        System.out.println("After modifying copied array[0] = 100:");
        System.out.println("Source array[0]: " + sourceArray[0] + " (unchanged)");
        System.out.println("Copied array[0]: " + copiedArray[0] + " (modified)");
    }
}
