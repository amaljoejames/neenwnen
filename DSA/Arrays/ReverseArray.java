import java.util.*;

/**
 * QUESTION: Reverse an Array - Reverse the elements of an array in place.
 * First element becomes last, last element becomes first, and so on.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [10, 20, 30, 40, 50, 60, 70, 80]
 * - After reversal: [80, 70, 60, 50, 40, 30, 20, 10]
 * - Process:
 *   - Swap 10 and 80
 *   - Swap 20 and 70
 *   - Swap 30 and 60
 *   - Swap 40 and 50
 * 
 * APPROACH USED: Two Pointer Swap
 * 
 * PSEUDOCODE:
 * function reverse(arr):
 *   first = 0
 *   last = arr.length - 1
 *   
 *   while first < last:
 *     swap(arr, first, last)
 *     first++
 *     last--
 *   
 * function swap(arr, i, j):
 *   temp = arr[i]
 *   arr[i] = arr[j]
 *   arr[j] = temp
 * 
 * EXPLANATION OF STEPS:
 * 1. Initialize two pointers at both ends
 * 2. Swap elements at these positions
 * 3. Move pointers towards center
 * 4. Continue until pointers meet or cross
 * 5. Array is now reversed in place
 * 6. Time complexity: O(n), Space complexity: O(1)
 * 
 * DRY RUN for array = [10, 20, 30, 40, 50, 60, 70, 80]:
 * 
 * Initial: first = 0, last = 7
 * 
 * Iteration 1: first=0, last=7
 * - Swap arr[0]=10 and arr[7]=80
 * - Array: [80, 20, 30, 40, 50, 60, 70, 10]
 * - first=1, last=6
 * 
 * Iteration 2: first=1, last=6
 * - Swap arr[1]=20 and arr[6]=70
 * - Array: [80, 70, 30, 40, 50, 60, 20, 10]
 * - first=2, last=5
 * 
 * Continue until first >= last
 * Final result: [80, 70, 60, 50, 40, 30, 20, 10]
 */
public class ReverseArray {
    
    public static void reverse(int arr[]) {
        int first = 0;
        int last = arr.length - 1;
        
        while (first < last) {
            // Swap elements at first and last positions
            int temp = arr[first];
            arr[first] = arr[last];
            arr[last] = temp;
            
            // Move pointers towards center
            first++;
            last--;
        }
    }
    
    // Helper function to print array
    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [10, 20, 30, 40, 50, 60, 70, 80]
         * Expected result: [80, 70, 60, 50, 40, 30, 20, 10]
         * 
         * Step-by-step process:
         * 
         * Iteration 1: first=0, last=7 → swap 10 and 80
         * Iteration 2: first=1, last=6 → swap 20 and 70
         * Iteration 3: first=2, last=5 → swap 30 and 60
         * Iteration 4: first=3, last=4 → swap 40 and 50
         * 
         * Final result: [80, 70, 60, 50, 40, 30, 20, 10]
         */
        
        int arr[] = {10, 20, 30, 40, 50, 60, 70, 80};
        
        System.out.println("Reverse Array Problem");
        System.out.println("Original array: ");
        printArray(arr);
        
        reverse(arr);
        
        System.out.println("Reversed array: ");
        printArray(arr);
        
        // Show step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("Original: [10, 20, 30, 40, 50, 60, 70, 80]");
        
        int temp[] = Arrays.copyOf(arr, arr.length);
        int first = 0, last = temp.length - 1;
        int iteration = 1;
        
        while (first < last) {
            System.out.println("Iteration " + iteration + ": Swap arr[" + first + "]=" + temp[first] + 
                             " and arr[" + last + "]=" + temp[last]);
            
            // Perform swap
            int swapTemp = temp[first];
            temp[first] = temp[last];
            temp[last] = swapTemp;
            
            System.out.println("  Array after swap: " + Arrays.toString(temp));
            
            first++;
            last--;
            iteration++;
        }
        
        System.out.println("Final reversed array: " + Arrays.toString(temp));
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Odd length array
        int[] test2 = {5, 10, 15};
        System.out.println("Test 2 (odd length): " + Arrays.toString(test2));
        reverse(test2);
        System.out.println("Reversed: " + Arrays.toString(test2));
        
        // Test case 3: Single element
        int[] test3 = {42};
        System.out.println("Test 3 (single element): " + Arrays.toString(test3));
        reverse(test3);
        System.out.println("Reversed: " + Arrays.toString(test3));
        
        // Test case 4: Empty array
        int[] test4 = {};
        System.out.println("Test 4 (empty array): " + Arrays.toString(test4));
        reverse(test4);
        System.out.println("Reversed: " + Arrays.toString(test4));
    }
}
