/**
 * Question: Rearrange array so that positive and negative numbers alternate.
 * 
 * Topics Included:
 * - Array manipulation
 * - Two-pointer technique
 * - Partitioning
 * - In-place modification
 * 
 * Algorithm:
 * 1. Separate positive and negative numbers
 * 2. Use two pointers to place them alternately
 * 3. Start with whichever type has more elements
 * 4. Fill the array by alternating between positive and negative
 * 5. Return the rearranged array
 * 
 * Pseudocode:
 * FUNCTION alternatePositiveNegative(array):
 *     positives = new ArrayList()
 *     negatives = new ArrayList()
 *     
 *     FOR each element in array:
 *         IF element >= 0:
 *             ADD element to positives
 *         ELSE:
 *             ADD element to negatives
 *     
 *     index = 0
 *     posIndex = 0
 *     negIndex = 0
 *     
 *     WHILE posIndex < positives.size() AND negIndex < negatives.size():
 *         array[index] = positives[posIndex]
 *         index++
 *         posIndex++
 *         array[index] = negatives[negIndex]
 *         index++
 *         negIndex++
 *     
 *     // Add remaining elements
 *     WHILE posIndex < positives.size():
 *         array[index] = positives[posIndex]
 *         index++
 *         posIndex++
 *     
 *     WHILE negIndex < negatives.size():
 *         array[index] = negatives[negIndex]
 *         index++
 *         negIndex++
 *     
 *     RETURN array
 * 
 * Explanation with Example:
 * Input array: [1, -2, 3, -4, 5, -6, 7, -8]
 * Dry run:
 * - Separate: positives = [1, 3, 5, 7], negatives = [-2, -4, -6, -8]
 * - Alternate placement:
 *   - array[0] = 1 (positive)
 *   - array[1] = -2 (negative)
 *   - array[2] = 3 (positive)
 *   - array[3] = -4 (negative)
 *   - array[4] = 5 (positive)
 *   - array[5] = -6 (negative)
 *   - array[6] = 7 (positive)
 *   - array[7] = -8 (negative)
 * Final result: [1, -2, 3, -4, 5, -6, 7, -8]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(n) - requires extra space for temporary lists
 */

import java.util.ArrayList;
import java.util.List;

public class AlternatePositiveNegative {
    
    /**
     * Method to rearrange array so that positive and negative numbers alternate
     * @param arr The input array
     * @return The rearranged array
     */
    public static int[] alternatePositiveNegative(int[] arr) {
        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();
        
        // Separate positive and negative numbers
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0) {
                positives.add(arr[i]);
            } else {
                negatives.add(arr[i]);
            }
        }
        
        int index = 0;
        int posIndex = 0;
        int negIndex = 0;
        
        // Alternate placement
        while (posIndex < positives.size() && negIndex < negatives.size()) {
            arr[index] = positives.get(posIndex);
            index++;
            posIndex++;
            
            arr[index] = negatives.get(negIndex);
            index++;
            negIndex++;
        }
        
        // Add remaining positive elements
        while (posIndex < positives.size()) {
            arr[index] = positives.get(posIndex);
            index++;
            posIndex++;
        }
        
        // Add remaining negative elements
        while (negIndex < negatives.size()) {
            arr[index] = negatives.get(negIndex);
            index++;
            negIndex++;
        }
        
        return arr;
    }
    
    public static void main(String[] args) {
        // Question: Rearrange array so that positive and negative numbers alternate
        // Topics: Array manipulation, two-pointer technique, partitioning, in-place modification
        // Algorithm: Separate positive/negative numbers, then place them alternately
        // Pseudocode: separate positives and negatives → alternate placement → add remaining elements → return array
        // Example: [1, -2, 3, -4, 5, -6, 7, -8] → [1, -2, 3, -4, 5, -6, 7, -8]
        // Dry run: positives=[1,3,5,7], negatives=[-2,-4,-6,-8] → 1,-2,3,-4,5,-6,7,-8
        // Time: O(n), Space: O(n)
        
        int[] array = {1, -2, 3, -4, 5, -6, 7, -8};
        int[] result = alternatePositiveNegative(array);
        
        System.out.println("Original array: [1, -2, 3, -4, 5, -6, 7, -8]");
        System.out.print("After alternating positive and negative: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
