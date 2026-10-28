import java.util.*;

/**
 * QUESTION: Search in Rotated Sorted Array - Find target element in a sorted array 
 * that has been rotated at some pivot point unknown to us.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Original sorted: [0, 1, 2, 3, 4, 5, 6, 7]
 * - Rotated at pivot 4: [4, 5, 6, 7, 0, 1, 2, 3]
 * - Search for target = 0 → should return index 4
 * - Search for target = 5 → should return index 1
 * - Search for target = 8 → should return -1 (not found)
 * 
 * Time Complexity: O(log n) using modified binary search
 * Space Complexity: O(1)
 * 
 * APPROACH USED: Modified Binary Search
 * 
 * PSEUDOCODE:
 * function search(arr, target, si, ei):
 *   if si > ei: return -1
 *   mid = si + (ei - si) / 2
 *   
 *   if arr[mid] == target: return mid
 *   
 *   // Check if left part is sorted
 *   if arr[si] <= arr[mid]:
 *     // Target in left sorted part
 *     if arr[si] <= target && target < arr[mid]:
 *       return search(arr, target, si, mid - 1)
 *     else:
 *       return search(arr, target, mid + 1, ei)
 *   // Right part is sorted
 *   else:
 *     // Target in right sorted part
 *     if arr[mid] < target && target <= arr[ei]:
 *       return search(arr, target, mid + 1, ei)
 *     else:
 *       return search(arr, target, si, mid - 1)
 * 
 * EXPLANATION OF STEPS:
 * 1. Base case: if search space is empty, return -1
 * 2. Find middle element
 * 3. If middle is target, return index
 * 4. Check which half is sorted (left or right)
 * 5. If left half is sorted:
 *    - Check if target lies in left sorted range
 *    - If yes, search in left half
 *    - Else search in right half
 * 6. If right half is sorted:
 *    - Check if target lies in right sorted range
 *    - If yes, search in right half
 *    - Else search in left half
 * 7. Each recursive call eliminates half the search space
 * 
 * DRY RUN for arr = [4,5,6,7,0,1,2,3], target = 0:
 * 
 * search([4,5,6,7,0,1,2,3], 0, 6, 0)
 * - mid = 3, arr[3] = 7
 * - arr[0] = 4 ≤ arr[3] = 7 → left part is sorted
 * - Check if target (0) is in [4,7]: 4 ≤ 0 < 7 → false
 * - Search in right part: search(arr, 0, 4, 6)
 * 
 * search([4,5,6,7,0,1,2,3], 4, 6, 0)
 * - mid = 5, arr[5] = 1
 * - arr[4] = 0 ≤ arr[5] = 1 → left part is sorted
 * - Check if target (0) is in [0,1]: 0 ≤ 0 < 1 → true
 * - Search in left part: search(arr, 0, 4, 4)
 * 
 * search([4,5,6,7,0,1,2,3], 4, 4, 0)
 * - mid = 4, arr[4] = 0
 * - arr[4] == target → return 4
 * 
 * Final result: 0 found at index 4
 */
public class SearchInRotatedArray {
    
    public static int search(int arr[], int tar, int si, int ei) {
        // Base case
        if (si > ei) {
            return -1;
        }
        
        // Work
        int mid = si + (ei - si) / 2;
    
        if (arr[mid] == tar) {
            return mid; // Found target
        }
    
        // Check if left part is sorted
        if (arr[si] <= arr[mid]) { // L1
            // Target in left sorted part
            if (arr[si] <= tar && tar < arr[mid]) {
                return search(arr, tar, si, mid - 1);
            } else {
                return search(arr, tar, mid + 1, ei);
            }
        }
        // Right part is sorted
        else { // L2
            // Target in right sorted part
            if (arr[mid] < tar && tar <= arr[ei]) {
                return search(arr, tar, mid + 1, ei);
            } else {
                return search(arr, tar, si, mid - 1);
            }
        }
    }
    
    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: [4, 5, 6, 7, 0, 1, 2], target = 0
         * Expected result: index 4
         * 
         * Step-by-step process:
         * 
         * search([4,5,6,7,0,1,2], 0, 5, 0)
         * - mid = 2, arr[2] = 6
         * - arr[0] = 4 ≤ arr[2] = 6 → left part sorted
         * - target 0 not in [4,6] → search right part
         * 
         * search([4,5,6,7,0,1,2], 3, 5, 0)
         * - mid = 4, arr[4] = 0
         * - Found target at index 4
         */
        
        int arr[] = {4, 5, 6, 7, 0, 1, 2}; // Rotated sorted array
        int target = 0; 
        
        System.out.println("Search in Rotated Sorted Array");
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + target);
        
        int result = search(arr, target, 0, arr.length - 1);
        System.out.println("Target " + target + " found at index: " + result);
        
        // Show step-by-step for the main example
        System.out.println("\nStep-by-step search process:");
        System.out.println("Initial: search([4,5,6,7,0,1,2], 0, 6, 0)");
        System.out.println("mid = 3, arr[3] = 7");
        System.out.println("arr[0] = 4 ≤ arr[3] = 7 → left part is sorted");
        System.out.println("target 0 not in range [4,7] → search right part");
        System.out.println("search([4,5,6,7,0,1,2], 4, 6, 0)");
        System.out.println("mid = 5, arr[5] = 1");
        System.out.println("arr[4] = 0 ≤ arr[5] = 1 → left part is sorted");
        System.out.println("target 0 in range [0,1] → search left part");
        System.out.println("search([4,5,6,7,0,1,2], 4, 4, 0)");
        System.out.println("mid = 4, arr[4] = 0 → FOUND!");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Target at beginning
        int target2 = 4;
        int result2 = search(arr, target2, 0, arr.length - 1);
        System.out.println("Target " + target2 + " found at index: " + result2);
        
        // Test case 3: Target at end
        int target3 = 2;
        int result3 = search(arr, target3, 0, arr.length - 1);
        System.out.println("Target " + target3 + " found at index: " + result3);
        
        // Test case 4: Target not found
        int target4 = 8;
        int result4 = search(arr, target4, 0, arr.length - 1);
        System.out.println("Target " + target4 + " found at index: " + result4);
        
        // Test case 5: Single element array
        int[] single = {5};
        int result5 = search(single, 5, 0, single.length - 1);
        System.out.println("Single element array, target 5: " + result5);
        
        // Test case 6: Not rotated (normal sorted array)
        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        int result6 = search(sorted, 4, 0, sorted.length - 1);
        System.out.println("Normal sorted array, target 4: " + result6);
        
        // Test case 7: Different rotation point
        int[] rotated2 = {6, 7, 0, 1, 2, 3, 4, 5};
        int result7 = search(rotated2, 3, 0, rotated2.length - 1);
        System.out.println("Different rotation, target 3: " + result7);
    }
}
