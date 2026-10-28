import java.util.*;

/**
 * QUESTION: Merge Sort - Sort an array using divide and conquer strategy.
 * Divide array into two halves, sort each half recursively, then merge them.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [38, 27, 43, 3, 9, 82, 10]
 * - Divide: [38, 27, 43] and [3, 9, 82, 10]
 * - Sort left half: [27, 38, 43]
 * - Sort right half: [3, 9, 10, 82]
 * - Merge: [3, 9, 10, 27, 38, 43, 82]
 * - Final sorted array: [3, 9, 10, 27, 38, 43, 82]
 * 
 * APPROACH USED: Divide and Conquer with Recursive Merging
 * 
 * PSEUDOCODE:
 * function mergeSort(arr):
 *   if arr.length <= 1:
 *     return arr
 *   
 *   mid = arr.length / 2
 *   left = mergeSort(arr[0..mid])
 *   right = mergeSort(arr[mid+1..end])
 *   
 *   return merge(left, right)
 * 
 * function merge(left, right):
 *   result = []
 *   i = j = 0
 *   
 *   while i < left.length && j < right.length:
 *     if left[i] <= right[j]:
 *       result.append(left[i])
 *       i++
 *     else:
 *       result.append(right[j])
 *       j++
 *   
 *   // Add remaining elements
 *   while i < left.length:
 *     result.append(left[i++])
 *   while j < right.length:
 *     result.append(right[j++])
 *   
 *   return result
 * 
 * EXPLANATION OF STEPS:
 * 1. Base case: array with 0 or 1 element is already sorted
 * 2. Find middle index to divide array
 * 3. Recursively sort left and right halves
 * 4. Merge two sorted halves by comparing elements
 * 5. Each merge operation combines two sorted arrays
 * 6. Time complexity: O(n log n) in all cases
 * 
 * DRY RUN for array = [38, 27, 43, 3, 9, 82, 10]:
 * 
 * Initial call: mergeSort([38, 27, 43, 3, 9, 82, 10])
 * - mid = 4, left = [38, 27, 43], right = [3, 9, 82, 10]
 * 
 * Recursively sort left and right, then merge
 * Final result: [3, 9, 10, 27, 38, 43, 82]
 */
public class MergeSort {
    
    public static int[] mergeSort(int arr[]) {
        // Base case: array with 0 or 1 element
        if (arr.length <= 1) {
            return arr;
        }
        
        int mid = arr.length / 2;
        int left[] = Arrays.copyOfRange(arr, 0, mid);
        int right[] = Arrays.copyOfRange(arr, mid, arr.length);
        
        // Recursively sort both halves
        left = mergeSort(left);
        right = mergeSort(right);
        
        // Merge the sorted halves
        return merge(left, right);
    }
    
    // Merge two sorted arrays
    private static int[] merge(int left[], int right[]) {
        int result[] = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        
        // Compare and merge elements
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        
        // Add remaining elements
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [38, 27, 43, 3, 9, 82, 10]
         * Expected result: [3, 9, 10, 27, 38, 43, 82]
         * 
         * Step-by-step process:
         * 
         * Initial: mid = 4
         * Left: [38, 27, 43], Right: [3, 9, 82, 10]
         * Sort left: [27, 38, 43], Sort right: [3, 9, 10, 82]
         * Merge: [3, 9, 10, 27, 38, 43, 82]
         */
        
        int arr[] = {38, 27, 43, 3, 9, 82, 10};
        
        System.out.println("Merge Sort Problem");
        System.out.println("Original array: " + Arrays.toString(arr));
        
        int sorted[] = mergeSort(arr);
        
        System.out.println("Sorted array: " + Arrays.toString(sorted));
        
        // Show step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("Original: [38, 27, 43, 3, 9, 82, 10]");
        System.out.println("Length: 8, mid: 4");
        System.out.println("Left half: [38, 27, 43]");
        System.out.println("Right half: [3, 9, 82, 10]");
        
        System.out.println("Sorting left half: [27, 38, 43]");
        System.out.println("Sorting right half: [3, 9, 10, 82]");
        System.out.println("Merging: [3, 9, 10, 27, 38, 43, 82]");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        int[] test1 = {5, 1, 4, 2, 8};
        int[] sorted1 = mergeSort(test1);
        System.out.println("Test 1: " + Arrays.toString(sorted1));
        
        int[] test2 = {1, 2, 3, 4, 5};
        int[] sorted2 = mergeSort(test2);
        System.out.println("Test 2 (already sorted): " + Arrays.toString(sorted2));
    }
}
