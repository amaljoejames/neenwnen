import java.util.*;

/**
 * QUESTION: Insertion Sort - Sort an array by building a sorted portion one element at a time.
 * Each element is inserted into its correct position in the sorted portion.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [5, 1, 4, 2, 8]
 * - Pass 1: Element 1 is sorted, compare 4 with sorted portion [1], insert before 4 → [1, 4, 5, 2, 8]
 * - Pass 2: Element 4 is sorted, compare 2 with sorted portion [1, 4], insert before 2 → [1, 2, 4, 5, 8]
 * - Pass 3: Element 5 is sorted, compare 8 with sorted portion [1, 2, 4, 5], insert after 5 → [1, 2, 4, 5, 8]
 * - Final sorted array: [1, 2, 4, 5, 8]
 * 
 * APPROACH USED: Build sorted portion incrementally
 * 
 * PSEUDOCODE:
 * function insertionSort(arr):
 *   n = arr.length
 *   
 *   for i = 1 to n-1:
 *     key = arr[i]  // Current element to insert
 *     j = i - 1     // Start comparing from previous element
 *     
 *     // Find correct position for key
 *     while j >= 0 && arr[j] > key:
 *       arr[j + 1] = arr[j]  // Shift elements right
 *       j--
 *     
 *     arr[j + 1] = key  // Insert key
 *   
 *   return arr
 * 
 * EXPLANATION OF STEPS:
 * 1. Assume first element is sorted
 * 2. For each element from position 1 to n-1:
 * 3. Compare with sorted portion and shift elements to make space
 * 4. Insert current element in correct position
 * 5. Each pass expands the sorted portion by one element
 * 
 * DRY RUN for array = [5, 1, 4, 2, 8]:
 * 
 * Pass 1 (i=1, key=1):
 * - Sorted portion: [1], unsorted: [1, 4, 2, 8]
 * - Compare 1 with 4: 1 ≤ 4, insert before 4
 * - Result: [1, 4, 1, 2, 8]
 * 
 * Pass 2 (i=2, key=4):
 * - Sorted portion: [1, 4], unsorted: [4, 2, 8]
 * - Compare 4 with 2: 4 > 2, insert after 4
 * - Result: [1, 2, 4, 8]
 * 
 * Continue until fully sorted
 */
public class InsertionSort {
    
    public static void insertionSort(int arr[]) {
        int n = arr.length;
        
        // Outer loop for each element (starting from position 1)
        for (int i = 1; i < n; i++) {
            int key = arr[i];  // Current element to insert
            int j = i - 1;    // Start comparing from previous element
            
            // Find correct position for key by shifting elements
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];  // Shift element right
                j--;
            }
            
            arr[j + 1] = key;  // Insert key in correct position
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [5, 1, 4, 2, 8]
         * Expected result: [1, 2, 4, 5, 8]
         * 
         * Step-by-step process:
         * 
         * Pass 1: Insert 1 into sorted portion [1]
         * Pass 2: Insert 4 into sorted portion [1, 4]
         * Pass 3: Insert 5 into sorted portion [1, 2, 4, 5]
         * Final sorted array
         */
        
        int arr[] = {5, 1, 4, 2, 8};
        
        System.out.println("Insertion Sort Problem");
        System.out.println("Original array: " + Arrays.toString(arr));
        
        insertionSort(arr);
        
        System.out.println("Sorted array: " + Arrays.toString(arr));
        
        // Show step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("Original: [5, 1, 4, 2, 8]");
        System.out.println("Assume first element is sorted: [5]");
        
        // Pass 1: Insert 1
        System.out.println("Pass 1: Insert element 1");
        System.out.println("  Key = 1, sorted portion = [5]");
        System.out.println("  Compare 1 with 4: 1 ≤ 4, insert before 4");
        System.out.println("  Result: [1, 4, 1, 2, 8]");
        
        // Pass 2: Insert 4
        System.out.println("Pass 2: Insert element 4");
        System.out.println("  Key = 4, sorted portion = [1, 4]");
        System.out.println("  Compare 4 with 2: 4 > 2, insert after 4");
        System.out.println("  Result: [1, 2, 4, 8]");
        
        // Pass 3: Insert 5
        System.out.println("Pass 3: Insert element 5");
        System.out.println("  Key = 5, sorted portion = [1, 2, 4]");
        System.out.println("  Compare 5 with 8: 5 ≤ 8, insert after 5");
        System.out.println("  Result: [1, 2, 4, 5, 8]");
        
        System.out.println("Final sorted array: " + Arrays.toString(arr));
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        int[] test1 = {64, 34, 25, 12, 22, 11, 90};
        insertionSort(test1);
        System.out.println("Test 1: " + Arrays.toString(test1));
        
        int[] test2 = {1, 2, 3, 4, 5};
        System.out.println("Test 2 (already sorted): " + Arrays.toString(test2));
    }
}
