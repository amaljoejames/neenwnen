import java.util.*;

/**
 * QUESTION: Linear Search - Search for a target element in an array by checking 
 * each element sequentially from the beginning.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [10, 20, 30, 40]
 * - Target: 30
 * - Search process: Check 10, 20, 30 (found at index 2)
 * - Answer: Index 2 (element found)
 * - If target not found: return -1
 * 
 * APPROACH USED: Sequential Search
 * 
 * PSEUDOCODE:
 * function linearSearch(arr, key):
 *   for i = 0 to arr.length-1:
 *     if arr[i] == key:
 *       return i  // Element found, return index
 *   
 *   return -1  // Element not found
 * 
 * EXPLANATION OF STEPS:
 * 1. Start from first element (index 0)
 * 2. Check each element sequentially
 * 3. If element matches target, return current index
 * 4. If reached end without finding target, return -1
 * 5. Time complexity: O(n) in worst case, O(1) in best case
 * 
 * DRY RUN for array = [10, 20, 30, 40], target = 30:
 * 
 * Iteration 1: Check arr[0] = 10 ≠ 30
 * Iteration 2: Check arr[1] = 20 ≠ 30
 * Iteration 3: Check arr[2] = 30 = 30 → FOUND! Return index 2
 * 
 * Result: Element 30 found at index 2
 */
public class LinearSearch {
    
    public static int linearSearch(int arr[], int key) {
        // Sequential search from the beginning
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;  // Element found, return index
            }
        }
        return -1;  // Element not found, return -1
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [10, 20, 30, 40], target = 30
         * Expected result: index 2 (element 30 found)
         * 
         * Step-by-step process:
         * 
         * Iteration 1: Check arr[0] = 10 ≠ 30
         * Iteration 2: Check arr[1] = 20 ≠ 30
         * Iteration 3: Check arr[2] = 30 = 30 → FOUND!
         * 
         * Result: Element 30 found at index 2
         */
        
        // Test case 1: Element exists in array
        int arr[] = {10, 20, 30, 40};
        int target = 30;
        
        System.out.println("Linear Search Problem");
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + target);
        
        int result = linearSearch(arr, target);
        
        if (result != -1) {
            System.out.println("Element " + target + " found at index " + result);
        } else {
            System.out.println("Element " + target + " not found in array");
        }
        
        // Show step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("Array: [10, 20, 30, 40]");
        System.out.println("Target: 30");
        
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Iteration " + (i + 1) + ": Check arr[" + i + "] = " + arr[i] + 
                             "(target " + target + "? " + (arr[i] == target ? "FOUND" : "NOT FOUND") + ")");
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Element not in array
        int[] test2 = {5, 15, 25, 35};
        int target2 = 50;
        System.out.println("Test 2: " + Arrays.toString(test2));
        System.out.println("Target: " + target2);
        System.out.println("Result: " + linearSearch(test2, target2));
        
        // Test case 3: First element
        int[] test3 = {42, 15, 8, 23};
        int target3 = 42;
        System.out.println("Test 3: " + Arrays.toString(test3));
        System.out.println("Target: " + target3);
        System.out.println("Result: " + linearSearch(test3, target3));
        
        // Test case 4: Last element
        int[] test4 = {10, 20, 30, 40, 50};
        int target4 = 50;
        System.out.println("Test 4: " + Arrays.toString(test4));
        System.out.println("Target: " + target4);
        System.out.println("Result: " + linearSearch(test4, target4));
        
        // Test case 5: Empty array
        int[] test5 = {};
        int target5 = 25;
        System.out.println("Test 5: " + Arrays.toString(test5));
        System.out.println("Target: " + target5);
        System.out.println("Result: " + linearSearch(test5, target5));
    }
}
