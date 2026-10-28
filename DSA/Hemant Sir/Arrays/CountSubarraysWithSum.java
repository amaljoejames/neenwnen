/**
 * Question: Count the number of subarrays with a given sum.
 * 
 * Topics Included:
 * - Array manipulation
 * - Hash map usage
 * - Prefix sum technique
 * - Subarray counting
 * 
 * Algorithm:
 * 1. Initialize a hash map to store prefix sum frequencies
 * 2. Initialize prefixSum = 0 and count = 0
 * 3. Traverse through the array
 * 4. For each element, add it to prefixSum
 * 5. If (prefixSum - target) exists in the map, add its frequency to count
 * 6. Increment the frequency of current prefixSum in the map
 * 7. Return the count
 * 
 * Pseudocode:
 * FUNCTION countSubarraysWithSum(array, target):
 *     prefixSumMap = new HashMap()
 *     prefixSumMap.put(0, 1)  // Empty subarray
 *     prefixSum = 0
 *     count = 0
 *     
 *     FOR each element in array:
 *         prefixSum = prefixSum + element
 *         complement = prefixSum - target
 *         
 *         IF prefixSumMap contains complement:
 *             count = count + prefixSumMap.get(complement)
 *         
 *         prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1)
 *     
 *     RETURN count
 * 
 * Explanation with Example:
 * Input array: [1, 2, 3, 4, 5], target = 9
 * Dry run:
 * - Initial: prefixSumMap = {0: 1}, prefixSum = 0, count = 0
 * - Element 1: prefixSum = 1, complement = 1 - 9 = -8
 *   - -8 not in map, count = 0
 *   - prefixSumMap = {0: 1, 1: 1}
 * - Element 2: prefixSum = 3, complement = 3 - 9 = -6
 *   - -6 not in map, count = 0
 *   - prefixSumMap = {0: 1, 1: 1, 3: 1}
 * - Element 3: prefixSum = 6, complement = 6 - 9 = -3
 *   - -3 not in map, count = 0
 *   - prefixSumMap = {0: 1, 1: 1, 3: 1, 6: 1}
 * - Element 4: prefixSum = 10, complement = 10 - 9 = 1
 *   - 1 exists in map with frequency 1, count = 0 + 1 = 1
 *   - prefixSumMap = {0: 1, 1: 1, 3: 1, 6: 1, 10: 1}
 * - Element 5: prefixSum = 15, complement = 15 - 9 = 6
 *   - 6 exists in map with frequency 1, count = 1 + 1 = 2
 *   - prefixSumMap = {0: 1, 1: 1, 3: 1, 6: 1, 10: 1, 15: 1}
 * Final result: 2
 * 
 * The subarrays are: [2,3,4] and [4,5]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(n) - requires extra space for the hash map
 */

import java.util.HashMap;
import java.util.Map;

public class CountSubarraysWithSum {
    
    /**
     * Method to count the number of subarrays with a given sum
     * @param arr The input array
     * @param target The target sum
     * @return The count of subarrays with the given sum
     */
    public static int countSubarraysWithSum(int[] arr, int target) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1); // Empty subarray
        
        int prefixSum = 0;
        int count = 0;
        
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            int complement = prefixSum - target;
            
            if (prefixSumMap.containsKey(complement)) {
                count += prefixSumMap.get(complement);
            }
            
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        // Question: Count the number of subarrays with a given sum
        // Topics: Array manipulation, hash map usage, prefix sum technique, subarray counting
        // Algorithm: Use prefix sum and hash map to efficiently count subarrays with target sum
        // Pseudocode: prefixSumMap = {0:1}; prefixSum = 0; count = 0; for each element: prefixSum += element; complement = prefixSum - target; if prefixSumMap contains complement: count += frequency; update prefixSumMap
        // Example: [1, 2, 3, 4, 5], target=9 → count=2 (subarrays: [2,3,4] and [4,5])
        // Dry run: prefixSumMap={0:1} → prefixSum=1,complement=-8 → prefixSumMap={0:1,1:1} → prefixSum=3,complement=-6 → prefixSumMap={0:1,1:1,3:1} → prefixSum=6,complement=-3 → prefixSumMap={0:1,1:1,3:1,6:1} → prefixSum=10,complement=1 found → count=1 → prefixSum=15,complement=6 found → count=2
        // Time: O(n), Space: O(n)
        
        int[] array = {1, 2, 3, 4, 5};
        int target = 9;
        int result = countSubarraysWithSum(array, target);
        
        System.out.println("Array: [1, 2, 3, 4, 5]");
        System.out.println("Target sum: " + target);
        System.out.println("Number of subarrays with sum " + target + ": " + result);
        System.out.println("Subarrays: [2,3,4] and [4,5]");
    }
}
