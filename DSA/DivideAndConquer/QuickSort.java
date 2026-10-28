import java.util.*;

/**
 * QUESTION: Quick Sort - Sort an array using divide and conquer approach.
 * Quick sort selects a pivot element, partitions array around it, then recursively sorts subarrays.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Input array: [6, 3, 9, 5, 2, 8]
 * - Choose pivot: 8 (last element)
 * - Partition: [6, 3, 5, 2] + [8] + [9]
 * - Recursively sort left and right parts
 * - Final result: [2, 3, 5, 6, 8, 9]
 * 
 * Time Complexity: O(n log n) average, O(n²) worst case
 * Space Complexity: O(log n) for recursion stack
 * 
 * APPROACH USED: Divide and Conquer with Partitioning
 * 
 * PSEUDOCODE:
 * function quickSort(arr, si, ei):
 *   if si >= ei: return
 *   pidx = partition(arr, si, ei)
 *   quickSort(arr, si, pidx - 1)
 *   quickSort(arr, pidx + 1, ei)
 * 
 * function partition(arr, si, ei):
 *   pivot = arr[ei]
 *   i = si - 1
 *   for j = si to ei-1:
 *     if arr[j] <= pivot:
 *       i++
 *       swap(arr[i], arr[j])
 *   swap(arr[i+1], arr[ei])
 *   return i + 1
 * 
 * EXPLANATION OF STEPS:
 * 1. Base case: single element (si >= ei) is already sorted
 * 2. Choose pivot element (last element in this implementation)
 * 3. Partition array: elements ≤ pivot on left, > pivot on right
 * 4. Place pivot in correct position
 * 5. Recursively sort left and right partitions
 * 6. Each partition takes O(n) time
 * 7. Average complexity: O(n log n), worst case: O(n²)
 * 
 * DRY RUN for array [6,3,5]:
 * 
 * quickSort([6,3,5], 0, 2)
 * - pivot = 5, i = -1
 * - j=0: arr[0]=6 > 5 → no swap
 * - j=1: arr[1]=3 ≤ 5 → i=0, swap(6,3) → [3,6,5]
 * - After loop: i=0, swap(arr[1], arr[2]) → [3,5,6]
 * - pivot index = 1
 * - Recursively sort [3] and [6] (base cases)
 * - Final result: [3,5,6]
 */
public class QuickSort {
    
    public static void quickSort(int arr[], int si, int ei) {
        if (si >= ei) { // Correct base condition
            return;
        }
        int pidx = partition(arr, si, ei); // Partition array
        quickSort(arr, si, pidx - 1); // Recursively sort left part
        quickSort(arr, pidx + 1, ei); // Recursively sort right part
    }
    
    public static int partition(int arr[], int si, int ei) {
        int pivot = arr[ei]; // Choose last element as pivot
        int i = si - 1; // Index of smaller element
        
        for (int j = si; j < ei; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        
        // Place pivot in its correct position
        i++;
        int temp = arr[ei];
        arr[ei] = arr[i];
        arr[i] = temp;
    
        return i; // Return pivot index
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
         * Test case: [6, 3, 9, 5, 2, 8]
         * Expected result: [2, 3, 5, 6, 8, 9]
         * 
         * Step-by-step process:
         * 
         * Initial: [6, 3, 9, 5, 2, 8]
         * First partition with pivot=8:
         * - Elements ≤8: [6,3,5,2], >8: [9]
         * - After partition: [6,3,5,2,8,9]
         * - Pivot position: 4
         * 
         * Recursively sort left [6,3,5,2] and right [9]
         * Continue until all subarrays are sorted
         */
        
        int[] arr = {6, 3, 9, 5, 2, 8};
        
        System.out.println("Quick Sort Algorithm");
        System.out.println("Original array:");
        printArray(arr);
        
        quickSort(arr, 0, arr.length - 1);
        
        System.out.println("Sorted array:");
        printArray(arr);
        
        // Show step-by-step for smaller array
        System.out.println("\nStep-by-step for [6,3,5]:");
        int[] smallArr = {6, 3, 5};
        System.out.println("Original: " + Arrays.toString(smallArr));
        System.out.println("Pivot: 5");
        
        System.out.println("Partition process:");
        System.out.println("j=0: arr[0]=6 > 5, no swap");
        System.out.println("j=1: arr[1]=3 ≤ 5, i=0, swap(6,3) → [3,6,5]");
        System.out.println("After loop: i=0, swap(arr[1],arr[2]) → [3,5,6]");
        System.out.println("Pivot position: 1");
        
        quickSort(smallArr, 0, smallArr.length - 1);
        System.out.println("Final result: " + Arrays.toString(smallArr));
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Already sorted
        int[] sorted = {1, 2, 3, 4, 5};
        System.out.println("Already sorted array:");
        printArray(sorted);
        quickSort(sorted, 0, sorted.length - 1);
        printArray(sorted);
        
        // Test case 3: Reverse sorted
        int[] reverse = {5, 4, 3, 2, 1};
        System.out.println("Reverse sorted array:");
        printArray(reverse);
        quickSort(reverse, 0, reverse.length - 1);
        printArray(reverse);
        
        // Test case 4: Single element
        int[] single = {42};
        System.out.println("Single element array:");
        printArray(single);
        quickSort(single, 0, single.length - 1);
        printArray(single);
        
        // Test case 5: Empty array
        int[] empty = {};
        System.out.println("Empty array:");
        printArray(empty);
        quickSort(empty, 0, empty.length - 1);
        printArray(empty);
        
        // Test case 6: Duplicate elements
        int[] duplicates = {3, 6, 3, 6, 3, 6};
        System.out.println("Array with duplicates:");
        printArray(duplicates);
        quickSort(duplicates, 0, duplicates.length - 1);
        printArray(duplicates);
    }
}
