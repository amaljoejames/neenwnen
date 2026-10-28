/**
 * Question: Find all pairs of elements whose sum is equal to a given number.
 * 
 * Topics Included:
 * - Array traversal
 * - Pair finding
 * - Hash set usage
 * - Two-sum problem
 * 
 * Algorithm:
 * 1. Create a set to store visited elements
 * 2. Create a list to store pairs
 * 3. Traverse through each element of the array
 * 4. For each element, calculate complement (target - element)
 * 5. If complement exists in the set, add the pair to result
 * 6. Add current element to the set
 * 7. Return all found pairs
 * 
 * Pseudocode:
 * FUNCTION findPairsWithSum(array, target):
 *     visitedSet = new HashSet()
 *     pairsList = new ArrayList()
 *     
 *     FOR each element in array:
 *         complement = target - element
 *         IF visitedSet contains complement:
 *             ADD [complement, element] to pairsList
 *         ADD element to visitedSet
 *     
 *     RETURN pairsList
 * 
 * Explanation with Example:
 * Input array: [1, 4, 45, 6, 10, 8], target = 16
 * Dry run:
 * - Element 1: complement = 15, visitedSet = {} → add 1 to visitedSet
 * - Element 4: complement = 12, visitedSet = {1} → add 4 to visitedSet
 * - Element 45: complement = -29, visitedSet = {1, 4} → add 45 to visitedSet
 * - Element 6: complement = 10, visitedSet = {1, 4, 45} → add 6 to visitedSet
 * - Element 10: complement = 6, visitedSet = {1, 4, 45, 6} → 6 exists! add pair [6, 10], add 10 to visitedSet
 * - Element 8: complement = 8, visitedSet = {1, 4, 45, 6, 10} → add 8 to visitedSet
 * Final result: [[6, 10]]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(n) - requires extra space for the set and pairs
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PairSum {
    
    /**
     * Method to find all pairs of elements whose sum is equal to a given number
     * @param arr The input array
     * @param target The target sum
     * @return A list of pairs whose sum equals the target
     */
    public static List<int[]> findPairsWithSum(int[] arr, int target) {
        Set<Integer> visitedSet = new HashSet<>();
        List<int[]> pairsList = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            
            if (visitedSet.contains(complement)) {
                pairsList.add(new int[]{complement, arr[i]});
            }
            
            visitedSet.add(arr[i]);
        }
        
        return pairsList;
    }
    
    public static void main(String[] args) {
        // Question: Find all pairs of elements whose sum is equal to a given number
        // Topics: Array traversal, pair finding, hash set usage, two-sum problem
        // Algorithm: Use hash set to track visited elements, find complement for each element
        // Pseudocode: visitedSet = new HashSet(); for each element: complement = target - element; if visitedSet contains complement: add pair; add element to visitedSet
        // Example: [1, 4, 45, 6, 10, 8], target=16 → [[6, 10]]
        // Dry run: 1→{}, 4→{1}, 45→{1,4}, 6→{1,4,45}, 10→{1,4,45,6} complement=6 found → pair[6,10], 8→{1,4,45,6,10}
        // Time: O(n), Space: O(n)
        
        int[] array = {1, 4, 45, 6, 10, 8};
        int target = 16;
        List<int[]> pairs = findPairsWithSum(array, target);
        
        System.out.println("Array: [1, 4, 45, 6, 10, 8]");
        System.out.println("Target sum: " + target);
        System.out.println("Pairs with sum " + target + ":");
        
        if (pairs.isEmpty()) {
            System.out.println("No pairs found");
        } else {
            for (int[] pair : pairs) {
                System.out.println("[" + pair[0] + ", " + pair[1] + "]");
            }
        }
    }
}
