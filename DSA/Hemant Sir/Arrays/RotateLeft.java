/**
 * Question: Rotate an array by k positions to the left.
 * 
 * Topics Included:
 * - Array rotation
 * - Modulo operation
 * - Temporary array usage
 * - Index mapping
 * 
 * Algorithm:
 * 1. Handle edge case where k >= array length using modulo
 * 2. Create a temporary array of same size
 * 3. Copy elements to new positions considering left rotation
 * 4. Copy back to original array
 * 5. Return the rotated array
 * 
 * Pseudocode:
 * FUNCTION rotateLeft(array, k):
 *     n = length of array
 *     IF n == 0: RETURN array
     
 *     k = k % n  // Handle k >= n
 *     tempArray = new int[n]
 *     
 *     FOR i from 0 to n-1:
 *         newPosition = (i - k + n) % n
 *         tempArray[newPosition] = array[i]
 *     
 *     FOR i from 0 to n-1:
 *         array[i] = tempArray[i]
 *     
 *     RETURN array
 * 
 * Explanation with Example:
 * Input array: [1, 2, 3, 4, 5, 6], k = 2
 * Dry run:
 * - n = 6, k = 2
 * - Create tempArray[6]
 * - Element placement:
 *   - array[0] = 1 → newPosition = (0 - 2 + 6) % 6 = 4 → tempArray[4] = 1
 *   - array[1] = 2 → newPosition = (1 - 2 + 6) % 6 = 5 → tempArray[5] = 2
 *   - array[2] = 3 → newPosition = (2 - 2 + 6) % 6 = 0 → tempArray[0] = 3
 *   - array[3] = 4 → newPosition = (3 - 2 + 6) % 6 = 1 → tempArray[1] = 4
 *   - array[4] = 5 → newPosition = (4 - 2 + 6) % 6 = 2 → tempArray[2] = 5
 *   - array[5] = 6 → newPosition = (5 - 2 + 6) % 6 = 3 → tempArray[3] = 6
 * - tempArray = [3, 4, 5, 6, 1, 2]
 * - Copy back to original array
 * Final result: [3, 4, 5, 6, 1, 2]
 * 
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(n) - requires extra space for temporary array
 */

public class RotateLeft {
    
    /**
     * Method to rotate an array by k positions to the left
     * @param arr The input array
     * @param k Number of positions to rotate
     * @return The rotated array
     */
    public static int[] rotateLeft(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) {
            return arr;
        }
        
        // Handle k >= n
        k = k % n;
        if (k == 0) {
            return arr;
        }
        
        int[] tempArray = new int[n];
        
        // Place elements at new positions
        for (int i = 0; i < n; i++) {
            int newPosition = (i - k + n) % n;
            tempArray[newPosition] = arr[i];
        }
        
        // Copy back to original array
        for (int i = 0; i < n; i++) {
            arr[i] = tempArray[i];
        }
        
        return arr;
    }
    
    public static void main(String[] args) {
        // Question: Rotate an array by k positions to the left
        // Topics: Array rotation, modulo operation, temporary array usage, index mapping
        // Algorithm: Use modulo to calculate new positions, create temp array, copy elements to new positions
        // Pseudocode: k = k % n; for i=0 to n-1: newPosition = (i - k + n) % n; tempArray[newPosition] = array[i]; copy back
        // Example: [1, 2, 3, 4, 5, 6], k=2 → [3, 4, 5, 6, 1, 2]
        // Dry run: 1→pos4, 2→pos5, 3→pos0, 4→pos1, 5→pos2, 6→pos3 → [3,4,5,6,1,2]
        // Time: O(n), Space: O(n)
        
        int[] array = {1, 2, 3, 4, 5, 6};
        int k = 2;
        int[] rotatedArray = rotateLeft(array, k);
        
        System.out.println("Original array: [1, 2, 3, 4, 5, 6]");
        System.out.println("Rotate by " + k + " positions to the left");
        System.out.print("Rotated array: [");
        for (int i = 0; i < rotatedArray.length; i++) {
            System.out.print(rotatedArray[i]);
            if (i < rotatedArray.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
