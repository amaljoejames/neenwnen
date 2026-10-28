/**
 * Question: Count frequency of each element.
 * 
 * Topics Included:
 * - Array traversal
 * - Frequency counting
 * - Hash map usage
 * - Element tracking
 * 
 * Algorithm:
 * 1. Create a map to store element frequencies
 * 2. Traverse through each element of the array
 * 3. For each element, increment its count in the map
 * 4. Return the frequency map
 * 
 * Pseudocode:
 * FUNCTION countFrequency(array):
 *     frequencyMap = new HashMap()
 *     
 *     FOR each element in array:
 *         IF element exists in frequencyMap:
 *             frequencyMap[element] = frequencyMap[element] + 1
 *         ELSE:
 *             frequencyMap[element] = 1
 *     
 *     RETURN frequencyMap
 * 
 * Explanation with Example:
 * Input array: [2, 3, 2, 4, 3, 2, 5, 4, 3]
 * Dry run:
 * - Element 2: frequencyMap = {2: 1}
 * - Element 3: frequencyMap = {2: 1, 3: 1}
 * - Element 2: frequencyMap = {2: 2, 3: 1}
 * - Element 4: frequencyMap = {2: 2, 3: 1, 4: 1}
 * - Element 3: frequencyMap = {2: 2, 3: 2, 4: 1}
 * - Element 2: frequencyMap = {2: 3, 3: 2, 4: 1}
 * - Element 5: frequencyMap = {2: 3, 3: 2, 4: 1, 5: 1}
 * - Element 4: frequencyMap = {2: 3, 3: 2, 4: 2, 5: 1}
 * - Element 3: frequencyMap = {2: 3, 3: 3, 4: 2, 5: 1}
 * Final result: {2: 3, 3: 3, 4: 2, 5: 1}
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(k) where k is the number of unique elements
 */

import java.util.HashMap;
import java.util.Map;

public class FrequencyCount {
    
    /**
     * Method to count frequency of each element in an array
     * @param arr The input array
     * @return A map containing element frequencies
     */
    public static Map<Integer, Integer> countFrequency(int[] arr) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            int element = arr[i];
            
            if (frequencyMap.containsKey(element)) {
                frequencyMap.put(element, frequencyMap.get(element) + 1);
            } else {
                frequencyMap.put(element, 1);
            }
        }
        
        return frequencyMap;
    }
    
    public static void main(String[] args) {
        // Question: Count frequency of each element
        // Topics: Array traversal, frequency counting, hash map usage, element tracking
        // Algorithm: Use hash map to track count of each element while traversing array
        // Pseudocode: frequencyMap = new HashMap(); for each element: increment count in map; return frequencyMap
        // Example: [2, 3, 2, 4, 3, 2, 5, 4, 3] → {2:3, 3:3, 4:2, 5:1}
        // Dry run: 2→{2:1}, 3→{2:1,3:1}, 2→{2:2,3:1}, 4→{2:2,3:1,4:1}, 3→{2:2,3:2,4:1}, 2→{2:3,3:2,4:1}, 5→{2:3,3:2,4:1,5:1}, 4→{2:3,3:2,4:2,5:1}, 3→{2:3,3:3,4:2,5:1}
        // Time: O(n), Space: O(k)
        
        int[] array = {2, 3, 2, 4, 3, 2, 5, 4, 3};
        Map<Integer, Integer> frequencyMap = countFrequency(array);
        
        System.out.println("Array: [2, 3, 2, 4, 3, 2, 5, 4, 3]");
        System.out.println("Element frequencies:");
        
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            System.out.println("Element " + entry.getKey() + ": " + entry.getValue() + " times");
        }
    }
}
