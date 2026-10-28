import java.util.*;

/**
 * QUESTION: Merge Sort - Sort an array using divide and conquer approach.
 * Merge sort divides array into two halves, recursively sorts them, then merges them.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Input array: [6, 3, 9, 5, 2, 8]
 * - Divide: [6,3,9] and [5,2,8]
 * - Further divide: [6], [3,9] and [5], [2,8]
 * - Sort and merge: [3,6,9] and [2,5,8]
 * - Final merge: [2,3,5,6,8,9]
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(n) for temporary array
 * 
 * APPROACH USED: Divide and Conquer
 * 
 * PSEUDOCODE:
 * function mergeSort(arr, si, ei):
 *   if si >= ei: return
 *   mid = si + (ei - si) / 2
 *   mergeSort(arr, si, mid)
 *   mergeSort(arr, mid + 1, ei)
 *   merge(arr, si, mid, ei)
 * 
 * function merge(arr, si, mid, ei):
 *   temp = new array[ei - si + 1]
 *   i = si, j = mid + 1, k = 0
 *   
 *   while i <= mid && j <= ei:
 *     if arr[i] < arr[j]:
 *       temp[k++] = arr[i++]
 *     else:
 *       temp[k++] = arr[j++]
 *   
 *   while i <= mid:
 *     temp[k++] = arr[i++]
 *   while j <= ei:
 *     temp[k++] = arr[j++]
 *   
 *   copy temp back to arr[si...ei]
 * 
 * EXPLANATION OF STEPS:
 * 1. Base case: single element (si >= ei) is already sorted
 * 2. Divide array into two halves at middle
 * 3. Recursively sort left and right halves
 * 4. Merge two sorted halves into temporary array
 * 5. Copy merged result back to original array
 * 6. Each merge operation takes O(n) time
 * 7. Total complexity: O(n log n) due to log n levels
 * 
 * DRY RUN for array [6,3,5]:
 * 
 * mergeSort([6,3,5], 0, 2)
 * - mid = 1
 * - mergeSort([6,3,5], 0, 1)
 *   - mid = 0
 *   - mergeSort([6,3,5], 0, 0) → base case
 *   - mergeSort([6,3,5], 1, 1) → base case
 *   - merge([6,3,5], 0, 0, 1)
 *     - Compare 6 and 3 → temp = [3,6]
 * - mergeSort([6,3,5], 2, 2) → base case
 * - merge([3,6,5], 0, 1, 2)
 *   - Compare 3 and 5 → temp = [3]
 *   - Compare 6 and 5 → temp = [3,5]
 *   - Add remaining 6 → temp = [3,5,6]
 * - Final result: [3,5,6]
 */
public class MergeSort {
    
    public static void mergeSort(int arr[], int si, int ei) {
        if (si >= ei) {
            return;
        }
        int mid = si + (ei - si) / 2;
        mergeSort(arr, si, mid);
        mergeSort(arr, mid + 1, ei);
        merge(arr, si, mid, ei);
    }

    public static void merge(int arr[], int si, int mid, int ei) {
        int temp[] = new int[ei - si + 1];
        int i = si;
        int j = mid + 1;
        int k = 0;

        // Merge two sorted subarrays
        while (i <= mid && j <= ei) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // Copy remaining elements from left subarray
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Copy remaining elements from right subarray
        while (j <= ei) {
            temp[k++] = arr[j++];
        }

        // Copy sorted elements back into original array
        for (k = 0, i = si; k < temp.length; i++, k++) {
            arr[i] = temp[k];
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
         * Test case: [6, 3, 9, 5, 2, 8]
         * Expected result: [2, 3, 5, 6, 8, 9]
         * 
         * Step-by-step process:
         * 
         * Initial: [6, 3, 9, 5, 2, 8]
         * Level 1: [6,3,9] + [5,2,8]
         * Level 2: [6] + [3,9] + [5] + [2,8]
         * Level 3: [6] + [3] + [9] + [5] + [2] + [8]
         * Merge up:
         * Level 2: [6] + [3,9] + [5] + [2,8]
         * Level 1: [3,6,9] + [2,5,8]
         * Final: [2,3,5,6,8,9]
         */
        
        int[] arr = {6, 3, 9, 5, 2, 8};
        
        System.out.println("Merge Sort Algorithm");
        System.out.println("Original array:");
        printArray(arr);
        
        mergeSort(arr, 0, arr.length - 1);
        
        System.out.println("Sorted array:");
        printArray(arr);
        
        // Show step-by-step for smaller array
        System.out.println("\nStep-by-step for [6,3,5]:");
        int[] smallArr = {6, 3, 5};
        System.out.println("Original: " + Arrays.toString(smallArr));
        
        System.out.println("Divide: [6,3] and [5]");
        System.out.println("Further divide: [6], [3] and [5]");
        System.out.println("Merge [6] and [3]: [3,6]");
        System.out.println("Merge [3,6] and [5]: [3,5,6]");
        
        mergeSort(smallArr, 0, smallArr.length - 1);
        System.out.println("Final result: " + Arrays.toString(smallArr));
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Already sorted
        int[] sorted = {1, 2, 3, 4, 5};
        System.out.println("Already sorted array:");
        printArray(sorted);
        mergeSort(sorted, 0, sorted.length - 1);
        printArray(sorted);
        
        // Test case 3: Reverse sorted
        int[] reverse = {5, 4, 3, 2, 1};
        System.out.println("Reverse sorted array:");
        printArray(reverse);
        mergeSort(reverse, 0, reverse.length - 1);
        printArray(reverse);
        
        // Test case 4: Single element
        int[] single = {42};
        System.out.println("Single element array:");
        printArray(single);
        mergeSort(single, 0, single.length - 1);
        printArray(single);
        
        // Test case 5: Empty array
        int[] empty = {};
        System.out.println("Empty array:");
        printArray(empty);
        mergeSort(empty, 0, empty.length - 1);
        printArray(empty);
    }
}
