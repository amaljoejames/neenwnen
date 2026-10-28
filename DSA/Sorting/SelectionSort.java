import java.util.*;

/**
 * QUESTION: Selection Sort - Sort an array by repeatedly finding the minimum element 
 * and swapping it with the element at the current position.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [64, 34, 25, 12, 22, 11, 90]
 * - Pass 1: Find minimum (11) at position 4, swap with position 0 → [11, 34, 25, 12, 64, 22, 90]
 * - Pass 2: Find minimum (12) at position 2, swap with position 1 → [11, 12, 25, 34, 64, 22, 90]
 * - Pass 3: Find minimum (22) at position 4, swap with position 2 → [11, 12, 22, 25, 34, 64, 90]
 * - Continue until sorted
 * 
 * APPROACH USED: Find minimum and swap with current position
 * 
 * PSEUDOCODE:
 * function selectionSort(arr):
 *   n = arr.length
 *   
 *   for i = 0 to n-1:
 *     minIdx = i  // Assume current position has minimum
 *     
 *     // Find minimum in unsorted portion
 *     for j = i+1 to n-1:
 *       if arr[j] < arr[minIdx]:
 *         minIdx = j
 *   
 *     // Swap minimum with current position
 *     swap(arr, i, minIdx)
 *   
 *   return arr
 * 
 * function swap(arr, i, j):
 *   temp = arr[i]
 *   arr[i] = arr[j]
 *   arr[j] = temp
 * 
 * EXPLANATION OF STEPS:
 * 1. For each position from left to right
 * 2. Find minimum element in unsorted portion
 * 3. Swap minimum element with current position
 * 4. Each pass places one element in correct position
 * 5. After n-1 passes, array is sorted
 * 
 * DRY RUN for array = [64, 34, 25, 12, 22, 11, 90]:
 * 
 * Pass 1 (i=0):
 * - Current position: 0, unsorted: positions 1-5
 * - Find minimum: 11 at position 4
 * - Swap positions 0 and 4 → [11, 34, 25, 12, 64, 22, 90]
 * 
 * Pass 2 (i=1):
 * - Current position: 1, unsorted: positions 2-5
 * - Find minimum: 12 at position 4
 * - Swap positions 1 and 4 → [11, 12, 25, 34, 64, 22, 90]
 * 
 * Continue until fully sorted
 */
public class SelectionSort {
    
    public static void selectionSort(int arr[]) {
        int n = arr.length;
        
        // Outer loop for each position
        for (int i = 0; i < n - 1; i++) {
            // Assume current position has minimum
            int minIdx = i;
            
            // Find minimum in unsorted portion
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            
            // Swap minimum with current position
            swap(arr, i, minIdx);
        }
    }
    
    // Helper function to swap two elements
    private static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [64, 34, 25, 12, 22, 11, 90]
         * Expected result: [11, 12, 22, 25, 34, 64, 90]
         * 
         * Step-by-step process:
         * 
         * Pass 1: Find minimum (11) at position 4, swap with position 0
         * Pass 2: Find minimum (12) at position 2, swap with position 1
         * Continue until sorted
         */
        
        int arr[] = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("Selection Sort Problem");
        System.out.println("Original array: " + Arrays.toString(arr));
        
        selectionSort(arr);
        
        System.out.println("Sorted array: " + Arrays.toString(arr));
        
        // Show step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("Original: [64, 34, 25, 12, 22, 11, 90]");
        
        // Pass 1
        System.out.println("Pass 1 (i=0):");
        System.out.println("  Current position: 0, unsorted: positions 1-5");
        System.out.println("  Find minimum: 11 at position 4");
        System.out.println("  Swap positions 0 and 4");
        System.out.println("  Array: [11, 34, 25, 12, 64, 22, 90]");
        
        // Pass 2
        System.out.println("Pass 2 (i=1):");
        System.out.println("  Current position: 1, unsorted: positions 2-5");
        System.out.println("  Find minimum: 12 at position 4");
        System.out.println("  Swap positions 1 and 4");
        System.out.println("  Array: [11, 12, 25, 34, 64, 22, 90]");
        
        System.out.println("Final sorted array: " + Arrays.toString(arr));
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        int[] test1 = {5, 1, 4, 2, 8};
        selectionSort(test1);
        System.out.println("Test 1: " + Arrays.toString(test1));
        
        int[] test2 = {1, 2, 3, 4, 5};
        System.out.println("Test 2 (already sorted): " + Arrays.toString(test2));
    }
}
