/**
 * Question: Find the duplicate number in an array (with one duplicate).
 * 
 * Topics Included:
 * - Array manipulation
 * - Set operations
 * - Duplicate detection
 * - Early termination
 * 
 * Algorithm:
 * 1. Create a set to store visited elements
 * 2. Traverse through each element of the array
 * 3. For each element, check if it already exists in the set
 * 4. If yes, it's the duplicate, return it
 * 5. If no, add the element to the set
 * 6. If no duplicate found, return -1
 * 
 * Pseudocode:
 * FUNCTION findDuplicate(array):
 *     visitedSet = new HashSet()
 *     
 *     FOR each element in array:
 *         IF visitedSet contains element:
 *             RETURN element
 *         ELSE:
 *             ADD element to visitedSet
 *     
 *     RETURN -1
 * 
 * Explanation with Example:
 * Input array: [1, 3, 4, 2, 3, 5]
 * Dry run:
 * - Create visitedSet = {}
 * - Element 1: 1 not in visitedSet → add 1 → visitedSet = {1}
 * - Element 3: 3 not in visitedSet → add 3 → visitedSet = {1, 3}
 * - Element 4: 4 not in visitedSet → add 4 → visitedSet = {1, 3, 4}
 * - Element 2: 2 not in visitedSet → add 2 → visitedSet = {1, 3, 4, 2}
 * - Element 3: 3 exists in visitedSet → return 3 immediately
 * Final result: 3
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(n) - requires extra space for the set
 */

import java.util.HashSet;
import java.util.Set;

public class FindDuplicate {
    
    /**
     * Method to find the duplicate number in an array (with one duplicate)
     * @param arr The input array with one duplicate
     * @return The duplicate number, or -1 if none found
     */
    public static int findDuplicate(int[] arr) {
        Set<Integer> visitedSet = new HashSet<>();
        
        for (int i = 0; i < arr.length; i++) {
            if (visitedSet.contains(arr[i])) {
                return arr[i];
            }
            visitedSet.add(arr[i]);
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        // Question: Find the duplicate number in an array (with one duplicate)
        // Topics: Array manipulation, set operations, duplicate detection, early termination
        // Algorithm: Use hash set to track visited elements, return first duplicate found
        // Pseudocode: visitedSet = new HashSet(); for each element: if visitedSet contains element: return element; else: add element to visitedSet; return -1
        // Example: [1, 3, 4, 2, 3, 5] → duplicate = 3
        // Dry run: visitedSet={} → 1→{1} → 3→{1,3} → 4→{1,3,4} → 2→{1,3,4,2} → 3 exists → return 3
        // Time: O(n), Space: O(n)
        
        int[] array = {1, 3, 4, 2, 3, 5};
        int result = findDuplicate(array);
        
        System.out.println("Array: [1, 3, 4, 2, 3, 5]");
        if (result != -1) {
            System.out.println("Duplicate number: " + result);
        } else {
            System.out.println("No duplicate found");
        }
    }
}
