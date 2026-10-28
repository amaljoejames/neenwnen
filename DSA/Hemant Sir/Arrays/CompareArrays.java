/**
 * Question: Compare two arrays for equality.
 * 
 * Topics Included:
 * - Array comparison
 * - Length checking
 * - Element-wise comparison
 * - Early termination
 * 
 * Algorithm:
 * 1. First check if both arrays have the same length
 * 2. If lengths are different, return false
 * 3. If lengths are same, compare each element at corresponding positions
 * 4. If any element doesn't match, return false immediately
 * 5. If all elements match, return true
 * 
 * Pseudocode:
 * FUNCTION compareArrays(array1, array2):
 *     IF length of array1 != length of array2:
 *         RETURN false
 *     
 *     FOR i from 0 to length-1:
 *         IF array1[i] != array2[i]:
 *             RETURN false
 *     
 *     RETURN true
 * 
 * Explanation with Example:
 * Input arrays: [2, 5, 8, 3] and [2, 5, 8, 3]
 * Dry run:
 * - Length check: 4 == 4 (continue)
 * - Index 0: 2 == 2 (continue)
 * - Index 1: 5 == 5 (continue)
 * - Index 2: 8 == 8 (continue)
 * - Index 3: 3 == 3 (continue)
 * - All elements matched, return true
 * 
 * Another example: [2, 5, 8, 3] and [2, 5, 8, 4]
 * - Index 0: 2 == 2 (continue)
 * - Index 1: 5 == 5 (continue)
 * - Index 2: 8 == 8 (continue)
 * - Index 3: 3 != 4 (return false immediately)
 * 
 * Time Complexity: O(n) where n is the number of elements in the arrays
 * Space Complexity: O(1) - constant space
 */

public class CompareArrays {
    
    /**
     * Method to compare two arrays for equality
     * @param array1 The first array
     * @param array2 The second array
     * @return true if arrays are equal, false otherwise
     */
    public static boolean compareArrays(int[] array1, int[] array2) {
        // Check if lengths are different
        if (array1.length != array2.length) {
            return false;
        }
        
        // Compare each element
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        // Question: Compare two arrays for equality
        // Topics: Array comparison, length checking, element-wise comparison, early termination
        // Algorithm: Check lengths, compare elements at corresponding positions
        // Pseudocode: if lengths differ: return false; for each element: if elements differ: return false; return true
        // Example: [2,5,8,3] vs [2,5,8,3] → true, [2,5,8,3] vs [2,5,8,4] → false
        // Dry run: length check → element comparison at each index
        // Time: O(n), Space: O(1)
        
        // Test case 1: Equal arrays
        int[] array1 = {2, 5, 8, 3};
        int[] array2 = {2, 5, 8, 3};
        boolean result1 = compareArrays(array1, array2);
        
        System.out.println("Array 1: [2, 5, 8, 3]");
        System.out.println("Array 2: [2, 5, 8, 3]");
        System.out.println("Arrays are equal: " + result1);
        System.out.println();
        
        // Test case 2: Different arrays
        int[] array3 = {2, 5, 8, 3};
        int[] array4 = {2, 5, 8, 4};
        boolean result2 = compareArrays(array3, array4);
        
        System.out.println("Array 3: [2, 5, 8, 3]");
        System.out.println("Array 4: [2, 5, 8, 4]");
        System.out.println("Arrays are equal: " + result2);
    }
}
