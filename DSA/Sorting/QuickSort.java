import java.util.*;

/**
 * QUESTION: Quick Sort - Sort an array using partitioning strategy.
 * Choose a pivot element, partition array around it, then recursively sort subarrays.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [10, 80, 30, 90, 40, 50, 70]
 * - Choose pivot 40, partition:
 *   - Left: [10, 30] (elements < 40)
 *   - Right: [80, 90, 40, 50, 70] (elements ≥ 40)
 * - Recursively sort left and right
 * - Final result: [10, 30, 40, 50, 70, 80, 90]
 * 
 * APPROACH USED: Divide and Conquer with Partitioning
 * 
 * PSEUDOCODE:
 * function quickSort(arr, low, high):
 *   if low < high:
 *     pivotIdx = partition(arr, low, high)
 *     quickSort(arr, low, pivotIdx - 1)
 *     quickSort(arr, pivotIdx + 1, high)
 *   
 * function partition(arr, low, high):
 *   pivot = arr[high]  // Choose last element as pivot
 *   i = low - 1
 *   j = low
 *   
 *   while j <= high:
 *     if arr[j] < pivot:
 *       i++
 *       swap(arr, i, j)
 *     else:
 *       j++
 *   
 *   swap(arr, i + 1, high)  // Place pivot in correct position
 *   return i + 1
 * 
 * function swap(arr, i, j):
 *   temp = arr[i]
 *   arr[i] = arr[j]
 *   arr[j] = temp
 * 
 * EXPLANATION OF STEPS:
 * 1. Choose pivot element (last element in this implementation)
 * 2. Partition array around pivot:
 *    - Elements < pivot go to left side
 *    - Elements ≥ pivot go to right side
 * 3. Place pivot in correct position
 * 4. Recursively sort left and right partitions
 * 5. Average time complexity: O(n log n), worst case: O(n²)
 * 
 * DRY RUN for array = [10, 80, 30, 90, 40, 50, 70]:
 * 
 * Initial call: quickSort([10, 80, 30, 90, 40, 50, 70], 0, 6)
 * - Pivot = 70, partition around index 5
 * - Left: [10, 80, 30, 90, 40, 50]
 * - Right: [70]
 * - Place pivot at position 6
 * 
 * Recursively sort and merge
 * Final result: [10, 30, 40, 50, 70, 80, 90]
 */
public class QuickSort {
    
    public static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int pivotIdx = partition(arr, low, high);
            
            // Recursively sort left and right partitions
            quickSort(arr, low, pivotIdx - 1);
            quickSort(arr, pivotIdx + 1, high);
        }
    }
    
    // Partition array around pivot
    private static int partition(int arr[], int low, int high) {
        int pivot = arr[high];  // Choose last element as pivot
        int i = low - 1;
        int j = low;
        
        // Partition array around pivot
        while (j <= high) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            } else {
                j++;
            }
        }
        
        // Place pivot in correct position
        swap(arr, i + 1, high);
        return i + 1;
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
         * Test case: array = [10, 80, 30, 90, 40, 50, 70]
         * Expected result: [10, 30, 40, 50, 70, 80, 90]
         * 
         * Step-by-step process:
         * 
         * Initial call: quickSort(arr, 0, 6)
         * Pivot = 70, partition around index 5
         * Left: [10, 80, 30, 90, 40, 50]
         * Right: [70]
         * Place pivot at position 6
         * 
         * Recursively sort left: [10, 30, 90, 40, 50, 80]
         * Recursively sort right: [70]
         * 
         * Final merged result
         */
        
        int arr[] = {10, 80, 30, 90, 40, 50, 70};
        
        System.out.println("Quick Sort Problem");
        System.out.println("Original array: " + Arrays.toString(arr));
        
        quickSort(arr, 0, arr.length - 1);
        
        System.out.println("Sorted array: " + Arrays.toString(arr));
        
        // Show step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("Original: [10, 80, 30, 90, 40, 50, 70]");
        System.out.println("Pivot: 70");
        
        System.out.println("Partition around pivot 70:");
        System.out.println("  Left side: [10, 80, 30, 90, 40, 50]");
        System.out.println("  Right side: [70]");
        System.out.println("  Pivot placed at position 6");
        
        System.out.println("Recursively sorting left and right sides...");
        System.out.println("Final sorted array: " + Arrays.toString(arr));
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        int[] test1 = {5, 1, 4, 2, 8};
        quickSort(test1, 0, test1.length - 1);
        System.out.println("Test 1: " + Arrays.toString(test1));
        
        int[] test2 = {1, 2, 3, 4, 5};
        quickSort(test2, 0, test2.length - 1);
        System.out.println("Test 2 (already sorted): " + Arrays.toString(test2));
    }
}
