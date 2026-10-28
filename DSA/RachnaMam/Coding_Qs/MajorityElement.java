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

    public static int findMajority(int[] arr) {
        int count = 0, candidate = 0;

        for (int n : arr) {
            if (count == 0) candidate = n;
            count += (n == candidate) ? 1 : -1;
        }

        count = 0;
        for (int n : arr)
            if (n == candidate) count++;

        return count > arr.length / 2 ? candidate : -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(findMajority(arr));
    }
}
