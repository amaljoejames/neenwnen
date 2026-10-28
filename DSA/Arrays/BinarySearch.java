import java.util.*;

/**
 * QUESTION: Binary Search - Search for a target element in a sorted array using 
 * divide and conquer strategy. Array must be sorted for binary search to work correctly.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Sorted Array: [10, 20, 30, 40, 50, 60, 70, 80]
 * - Target: 30
 * - Search process:
 *   1. Middle index: (0+7)/2 = 3, arr[3] = 30 → FOUND!
 * - Time complexity: O(log n) vs O(n) for linear search
 * 
 * APPROACH USED: Divide and Conquer
 * 
 * PSEUDOCODE:
 * function binarySearch(arr, key):
 *   left = 0, right = arr.length - 1
 *   
 *   while left <= right:
 *     mid = left + (right - left) / 2
 *     
 *     if arr[mid] == key:
 *       return mid  // Element found
 *     else if arr[mid] < key:
 *       left = mid + 1  // Search in right half
 *     else:
 *       right = mid - 1  // Search in left half
 *   
 *   return -1  // Element not found
 * 
 * EXPLANATION OF STEPS:
 * 1. Initialize search boundaries
 * 2. Calculate middle index
 * 3. Compare middle element with target
 * 4. If found → return index
 * 5. If not found → adjust search boundaries
 * 6. Repeat until found or boundaries cross
 * 7. Each iteration halves the search space
 * 
 * DRY RUN for array = [10, 20, 30, 40, 50, 60, 70, 80], target = 30:
 * 
 * Iteration 1:
 * - left = 0, right = 7, mid = 3, arr[3] = 30 → FOUND!
 * 
 * Result: Element 30 found at index 3
 */
public class BinarySearch {
    
    public static void binarySearch(int arr[], int key) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == key) {
                System.out.println("Element " + key + " found at index " + mid);
                return; // Element found
            } else if (arr[mid] < key) {
                left = mid + 1;  // Search in right half
            } else {
                right = mid - 1;  // Search in left half
            }
        }
        
        System.out.println("Element " + key + " not found in array");
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: sorted array = [10, 20, 30, 40, 50, 60, 70, 80], target = 30
         * Expected result: element 30 found at index 3
         * 
         * Step-by-step process:
         * 
         * Iteration 1:
         * - left = 0, right = 7, mid = 3, arr[3] = 30
         * - FOUND at index 3
         */
        
        // Test case 1: Element exists in array
        int[] sortedArray = {10, 20, 30, 40, 50, 60, 70, 80};
        int target = 30;
        
        System.out.println("Binary Search Problem");
        System.out.println("Sorted Array: " + Arrays.toString(sortedArray));
        System.out.println("Target: " + target);
        
        System.out.println("Search Process:");
        binarySearch(sortedArray, target);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Element not in array
        int[] test2 = {5, 15, 25, 35};
        int target2 = 50;
        System.out.println("Test 2: " + Arrays.toString(test2));
        System.out.println("Target: " + target2);
        binarySearch(test2, target2);
        
        // Test case 3: First element
        int[] test3 = {42, 15, 8, 23};
        int target3 = 42;
        System.out.println("Test 3: " + Arrays.toString(test3));
        System.out.println("Target: " + target3);
        binarySearch(test3, target3);
        
        // Test case 4: Last element
        int[] test4 = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        int target4 = 90;
        System.out.println("Test 4: " + Arrays.toString(test4));
        System.out.println("Target: " + target4);
        binarySearch(test4, target4);
        
        // Test case 5: Empty array
        int[] test5 = {};
        int target5 = 25;
        System.out.println("Test 5: " + Arrays.toString(test5));
        System.out.println("Target: " + target5);
        binarySearch(test5, target5);
    }
}
