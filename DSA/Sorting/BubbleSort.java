import java.util.*;

/**
 * QUESTION: Bubble Sort - Sort an array by repeatedly swapping adjacent elements 
 * if they are in wrong order. The largest elements "bubble up" to their correct positions.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [5, 1, 4, 2, 8]
 * - Pass 1: Compare 5 and 1 → swap → [1, 5, 4, 2, 8]
 * - Pass 2: Compare 5 and 4 → no swap → [1, 5, 4, 2, 8]
 * - Pass 3: Compare 4 and 2 → swap → [1, 5, 2, 4, 8]
 * - Pass 4: Compare 2 and 8 → swap → [1, 5, 2, 8, 4]
 * - Pass 5: Compare 8 and 4 → swap → [1, 5, 2, 4, 8]
 * - Sorted array: [1, 2, 4, 5, 8]
 * 
 * APPROACH USED: Repeated passes with adjacent comparisons
 * 
 * PSEUDOCODE:
 * function bubbleSort(arr):
 *   n = arr.length
 *   
 *   for i = 0 to n-1:
 *     for j = 0 to n-i-2:
 *       if arr[j] > arr[j+1]:
 *         swap(arr[j], arr[j+1])
 *   
 *   return arr
 * 
 * function swap(arr, i, j):
 *   temp = arr[i]
 *   arr[i] = arr[j]
 *   arr[j] = temp
 * 
 * EXPLANATION OF STEPS:
 * 1. Outer loop runs from first to second-last element
 * 2. Inner loop runs from start to unsorted portion
 * 3. Compare adjacent elements and swap if wrong order
 * 4. Each pass pushes largest element to correct position
 * 5. After n-1 passes, array is sorted
 * 
 * DRY RUN for array = [5, 1, 4, 2, 8]:
 * 
 * Pass 1 (i=0):
 * - j=0: compare arr[0]=5 and arr[1]=1 → swap → [1, 5, 4, 2, 8]
 * 
 * Pass 2 (i=1):
 * - j=0: compare arr[0]=1 and arr[1]=5 → no swap
 * - j=1: compare arr[1]=5 and arr[2]=4 → swap → [1, 4, 5, 2, 8]
 * 
 * Continue until fully sorted
 */
public class BubbleSort {
    
    public static void bubbleSort(int arr[]) {
        int n = arr.length;
        
        // Outer loop for number of passes
        for (int i = 0; i < n - 1; i++) {
            // Inner loop for adjacent comparisons
            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    swap(arr, j, j + 1);
                }
            }
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
         * Test case: array = [5, 1, 4, 2, 8]
         * Expected result: [1, 2, 4, 5, 8]
         * 
         * Step-by-step process:
         * 
         * Pass 1: Compare 5 and 1 → swap
         * Pass 2: Compare 5 and 4 → no swap
         * Pass 3: Compare 4 and 2 → swap
         * Continue until sorted
         */
        
        int arr[] = {5, 1, 4, 2, 8};
        
        System.out.println("Bubble Sort Problem");
        System.out.println("Original array: " + Arrays.toString(arr));
        
        bubbleSort(arr);
        
        System.out.println("Sorted array: " + Arrays.toString(arr));
        
        // Show step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("Original: [5, 1, 4, 2, 8]");
        
        int temp[] = Arrays.copyOf(arr, arr.length);
        
        // Pass 1
        System.out.println("Pass 1:");
        System.out.println("  Compare 5 and 1: 5 > 1 → swap");
        swap(temp, 0, 1);
        System.out.println("  Array: [1, 5, 4, 2, 8]");
        
        // Pass 2
        System.out.println("Pass 2:");
        System.out.println("  Compare 5 and 4: 5 > 4 → swap");
        swap(temp, 1, 2);
        System.out.println("  Array: [1, 4, 5, 2, 8]");
        
        // Pass 3
        System.out.println("Pass 3:");
        System.out.println("  Compare 5 and 2: 5 > 2 → swap");
        swap(temp, 2, 3);
        System.out.println("  Array: [1, 4, 2, 5, 8]");
        
        System.out.println("Final sorted array: " + Arrays.toString(temp));
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        int[] test1 = {64, 34, 25, 12, 22, 11, 90};
        bubbleSort(test1);
        System.out.println("Test 1: " + Arrays.toString(test1));
        
        int[] test2 = {1, 2, 3, 4, 5};
        System.out.println("Test 2 (already sorted): " + Arrays.toString(test2));
    }
}
