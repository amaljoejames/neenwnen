/**
 * Question: Find the length of the longest subarray with all distinct elements.
 * 
 * Topics Included:
 * - Array manipulation
 * - Sliding window technique
 * - Hash set usage
 * - Distinct element tracking
 * 
 * Algorithm:
 * 1. Use sliding window technique with two pointers
 * 2. Use a hash set to track distinct elements in current window
 * 3. Expand the window by moving the right pointer
 * 4. If a duplicate is found, shrink the window from the left
 * 5. Keep track of the maximum window size
 * 6. Return the maximum length
 * 
 * Pseudocode:
 * FUNCTION findLongestDistinctSubarray(array):
 *     distinctSet = new HashSet()
 *     left = 0
 *     maxLength = 0
 *     
 *     FOR right from 0 to length-1:
 *         WHILE distinctSet contains array[right]:
 *             REMOVE array[left] from distinctSet
 *             left = left + 1
 *         
 *         ADD array[right] to distinctSet
 *         maxLength = maximum of (maxLength, right - left + 1)
 *     
 *     RETURN maxLength
 * 
 * Explanation with Example:
 * Input array: [1, 2, 3, 2, 4, 5]
 * Dry run:
 * - Initial: distinctSet = {}, left = 0, maxLength = 0
 * - right = 0: element = 1, distinctSet doesn't contain 1
 *   - Add 1 → distinctSet = {1}
 *   - maxLength = max(0, 0 - 0 + 1) = 1
 * - right = 1: element = 2, distinctSet doesn't contain 2
 *   - Add 2 → distinctSet = {1, 2}
 *   - maxLength = max(1, 1 - 0 + 1) = 2
 * - right = 2: element = 3, distinctSet doesn't contain 3
 *   - Add 3 → distinctSet = {1, 2, 3}
 *   - maxLength = max(2, 2 - 0 + 1) = 3
 * - right = 3: element = 2, distinctSet contains 2
 *   - Remove array[0] = 1 → distinctSet = {2, 3}, left = 1
 *   - Remove array[1] = 2 → distinctSet = {3}, left = 2
 *   - Add 2 → distinctSet = {2, 3}
 *   - maxLength = max(3, 3 - 2 + 1) = 3
 * - right = 4: element = 4, distinctSet doesn't contain 4
 *   - Add 4 → distinctSet = {2, 3, 4}
 *   - maxLength = max(3, 4 - 2 + 1) = 3
 * - right = 5: element = 5, distinctSet doesn't contain 5
 *   - Add 5 → distinctSet = {2, 3, 4, 5}
 *   - maxLength = max(3, 5 - 2 + 1) = 4
 * Final result: 4
 * 
 * The longest subarray with distinct elements is [2, 4, 5] with length 4
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(k) where k is the size of the sliding window
 */

import java.util.HashSet;
import java.util.Set;

public class LongestDistinctSubarray {
    
    /**
     * Method to find the length of the longest subarray with all distinct elements
     * @param arr The input array
     * @return The length of the longest subarray with distinct elements
     */
    public static int findLongestDistinctSubarray(int[] arr) {
        Set<Integer> distinctSet = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < arr.length; right++) {
            // If duplicate found, remove elements from left until duplicate is removed
            while (distinctSet.contains(arr[right])) {
                distinctSet.remove(arr[left]);
                left++;
            }
            
            // Add current element to the set
            distinctSet.add(arr[right]);
            
            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    public static void main(String[] args) {
        // Question: Find the length of the longest subarray with all distinct elements
        // Topics: Array manipulation, sliding window technique, hash set usage, distinct element tracking
        // Algorithm: Use sliding window with hash set to find longest subarray with all distinct elements
        // Pseudocode: distinctSet = new HashSet(); left = 0; maxLength = 0; for right=0 to length-1: while distinctSet contains array[right]: remove array[left]; left++; add array[right]; maxLength = max(maxLength, right-left+1); return maxLength
        // Example: [1, 2, 3, 2, 4, 5] → longest distinct subarray length = 4 ([2,4,5])
        // Dry run: right=0→{1},len=1 → right=1→{1,2},len=2 → right=2→{1,2,3},len=3 → right=3→duplicate 2 → remove 1,2 → {3},left=2 → add 2 → {2,3},len=2 → right=4→{2,3,4},len=3 → right=5→{2,3,4,5},len=4
        // Time: O(n), Space: O(k)
        
        int[] array = {1, 2, 3, 2, 4, 5};
        int result = findLongestDistinctSubarray(array);
        
        System.out.println("Array: [1, 2, 3, 2, 4, 5]");
        System.out.println("Length of longest subarray with distinct elements: " + result);
        System.out.println("Longest subarray: [2, 4, 5]");
    }
}
