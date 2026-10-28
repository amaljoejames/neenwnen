/**
 * Question: Find the intersection of two arrays.
 * 
 * Topics Included:
 * - Array manipulation
 * - Set operations
 * - Intersection finding
 * - Duplicate handling
 * 
 * Algorithm:
 * 1. Create a set from the first array for O(1) lookups
 * 2. Create a set to store intersection results (to avoid duplicates)
 * 3. Traverse through the second array
 * 4. For each element, check if it exists in the first set
 * 5. If yes, add to the intersection set
 * 6. Convert the intersection set to an array and return
 * 
 * Pseudocode:
 * FUNCTION findIntersection(array1, array2):
 *     set1 = new HashSet(array1)
 *     intersectionSet = new HashSet()
 *     
 *     FOR each element in array2:
 *         IF set1 contains element:
 *             ADD element to intersectionSet
 *     
 *     RETURN intersectionSet.toArray()
 * 
 * Explanation with Example:
 * Input arrays: [1, 2, 3, 4, 5] and [3, 4, 5, 6, 7]
 * Dry run:
 * - Create set1 = {1, 2, 3, 4, 5}
 * - Create intersectionSet = {}
 * - Traverse array2:
 *   - Element 3: 3 exists in set1 → intersectionSet = {3}
 *   - Element 4: 4 exists in set1 → intersectionSet = {3, 4}
 *   - Element 5: 5 exists in set1 → intersectionSet = {3, 4, 5}
 *   - Element 6: 6 doesn't exist in set1 → skip
 *   - Element 7: 7 doesn't exist in set1 → skip
 * - Convert to array: [3, 4, 5]
 * Final result: [3, 4, 5]
 * 
 * Time Complexity: O(n + m) where n and m are lengths of the two arrays
 * Space Complexity: O(n + k) where k is the size of intersection
 */

import java.util.HashSet;
import java.util.Set;

public class IntersectionArrays {
    
    /**
     * Method to find the intersection of two arrays
     * @param arr1 The first array
     * @param arr2 The second array
     * @return An array containing the intersection elements
     */
    public static int[] findIntersection(int[] arr1, int[] arr2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : arr1) {
            set1.add(num);
        }
        
        Set<Integer> intersectionSet = new HashSet<>();
        for (int num : arr2) {
            if (set1.contains(num)) {
                intersectionSet.add(num);
            }
        }
        
        // Convert set to array
        int[] result = new int[intersectionSet.size()];
        int index = 0;
        for (int num : intersectionSet) {
            result[index++] = num;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Question: Find the intersection of two arrays
        // Topics: Array manipulation, set operations, intersection finding, duplicate handling
        // Algorithm: Use hash sets to find common elements between two arrays
        // Pseudocode: set1 = new HashSet(array1); intersectionSet = new HashSet(); for each element in array2: if set1 contains element: add to intersectionSet; return intersectionSet.toArray()
        // Example: [1, 2, 3, 4, 5] and [3, 4, 5, 6, 7] → [3, 4, 5]
        // Dry run: set1={1,2,3,4,5} → check array2 elements → 3,4,5 found → intersection={3,4,5}
        // Time: O(n+m), Space: O(n+k)
        
        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = {3, 4, 5, 6, 7};
        int[] result = findIntersection(array1, array2);
        
        System.out.println("Array 1: [1, 2, 3, 4, 5]");
        System.out.println("Array 2: [3, 4, 5, 6, 7]");
        System.out.print("Intersection: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
