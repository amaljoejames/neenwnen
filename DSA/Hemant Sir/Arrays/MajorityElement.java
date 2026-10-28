/**
 * Question: Find the majority element (element occurring more than n/2 times).
 * 
 * Topics Included:
 * - Array traversal
 * - Boyer-Moore Voting Algorithm
 * - Frequency counting
 * - Majority element detection
 * 
 * Algorithm:
 * 1. Use Boyer-Moore Voting Algorithm to find candidate
 * 2. First pass: Find candidate for majority element
 * 3. Second pass: Verify if candidate is actually majority
 * 4. Return the majority element if found, otherwise return -1
 * 
 * Pseudocode:
 * FUNCTION findMajorityElement(array):
 *     // First pass: Find candidate
 *     candidate = null
 *     count = 0
 *     
 *     FOR each element in array:
 *         IF count == 0:
 *             candidate = element
 *             count = 1
 *         ELSE IF element == candidate:
 *             count++
 *         ELSE:
 *             count--
 *     
 *     // Second pass: Verify candidate
 *     actualCount = 0
 *     FOR each element in array:
 *         IF element == candidate:
 *             actualCount++
 *     
 *     IF actualCount > length/2:
 *         RETURN candidate
 *     ELSE:
 *         RETURN -1
 * 
 * Explanation with Example:
 * Input array: [2, 2, 1, 1, 1, 2, 2]
 * Dry run:
 * - First pass (find candidate):
 *   - Element 2: candidate = 2, count = 1
 *   - Element 2: candidate = 2, count = 2
 *   - Element 1: candidate = 2, count = 1
 *   - Element 1: candidate = 2, count = 0
 *   - Element 1: candidate = 1, count = 1
 *   - Element 2: candidate = 1, count = 0
 *   - Element 2: candidate = 2, count = 1
 *   - Final candidate: 2
 * 
 * - Second pass (verify):
 *   - Count of 2: 4 times
 *   - Array length: 7
 *   - 4 > 7/2 (3.5)? Yes
 * - Final result: 2
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(1) - constant space
 */

public class MajorityElement {
    
    /**
     * Method to find the majority element (occurring more than n/2 times)
     * @param arr The input array
     * @return The majority element, or -1 if none exists
     */
    public static int findMajorityElement(int[] arr) {
        // First pass: Find candidate using Boyer-Moore algorithm
        Integer candidate = null;
        int count = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (count == 0) {
                candidate = arr[i];
                count = 1;
            } else if (arr[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        
        // Second pass: Verify the candidate
        if (candidate == null) {
            return -1;
        }
        
        int actualCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == candidate) {
                actualCount++;
            }
        }
        
        return (actualCount > arr.length / 2) ? candidate : -1;
    }
    
    public static void main(String[] args) {
        // Question: Find the majority element (element occurring more than n/2 times)
        // Topics: Array traversal, Boyer-Moore Voting Algorithm, frequency counting, majority element detection
        // Algorithm: Use Boyer-Moore to find candidate, then verify it's actually majority
        // Pseudocode: find candidate using voting → verify candidate count > n/2 → return candidate or -1
        // Example: [2, 2, 1, 1, 1, 2, 2] → majority element = 2
        // Dry run: candidate=2,count=1→2→count=2→1→count=1→1→count=0→1→count=1→2→count=0→2→count=1 → verify 2 appears 4 times > 7/2 → return 2
        // Time: O(n), Space: O(1)
        
        int[] array = {2, 2, 1, 1, 1, 2, 2};
        int result = findMajorityElement(array);
        
        System.out.println("Array: [2, 2, 1, 1, 1, 2, 2]");
        if (result != -1) {
            System.out.println("Majority element: " + result);
        } else {
            System.out.println("No majority element found");
        }
        //when 2 or more ps wait for each other held by each other
    }
}
