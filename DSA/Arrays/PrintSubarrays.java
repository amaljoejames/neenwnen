import java.util.*;

/**
 * QUESTION: Print Subarrays - Print all possible subarrays of an array.
 * A subarray is a contiguous part of the array. For array of length n, there are n(n+1)/2 subarrays.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [10, 20, 30, 40]
 * - Subarrays:
 *   - Length 1: [10], [20], [30], [40]
 *   - Length 2: [10,20], [20,30], [30,40]
 *   - Length 3: [10,20,30], [20,30,40]
 *   - Length 4: [10,20,30,40]
 * - Total subarrays: 4×5/2 = 10
 * 
 * APPROACH USED: Triple Nested Loops
 * 
 * PSEUDOCODE:
 * function printSubarrays(arr):
 *   totalSubarrays = 0
 *   
 *   for i = 0 to arr.length-1:      // Start index
 *     for j = i to arr.length-1:      // End index
 *       for k = i to j:               // Print elements from i to j
 *         print(arr[k] + " ")
 *       totalSubarrays++
 *       print()  // New line
 *     print()  // New line after each i
 *   
 *   print("Total subarrays: " + totalSubarrays)
 * 
 * EXPLANATION OF STEPS:
 * 1. Outer loop (i): Select starting index of subarray
 * 2. Middle loop (j): Select ending index of subarray
 * 3. Inner loop (k): Print elements from start to end
 * 4. Each combination of i and j defines a unique subarray
 * 5. Total subarrays = n(n+1)/2
 * 6. Time complexity: O(n³)
 * 
 * DRY RUN for array = [10, 20, 30]:
 * 
 * i = 0:
 * - j = 0: k = 0 → [10]
 * - j = 1: k = 0,1 → [10,20]
 * - j = 2: k = 0,1,2 → [10,20,30]
 * 
 * i = 1:
 * - j = 1: k = 1 → [20]
 * - j = 2: k = 1,2 → [20,30]
 * 
 * i = 2:
 * - j = 2: k = 2 → [30]
 * 
 * Total subarrays: 6
 */
public class PrintSubarrays {
    
    public static void printSubarrays(int arr[]) {
        int totalSubarrays = 0;
        
        for (int i = 0; i < arr.length; i++) {        // Start index
            for (int j = i; j < arr.length; j++) {     // End index
                for (int k = i; k <= j; k++) {        // Print elements
                    System.out.print(arr[k] + " ");
                }
                totalSubarrays++;
                System.out.println();  // New line after each subarray
            }
            System.out.println();  // New line after each i
        }
        System.out.println("Total subarrays are: " + totalSubarrays);
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [10, 20, 30, 40]
         * Expected result: 10 subarrays total
         * 
         * Step-by-step process:
         * 
         * i = 0:
         * - j = 0: [10]
         * - j = 1: [10,20]
         * - j = 2: [10,20,30]
         * - j = 3: [10,20,30,40]
         * 
         * i = 1:
         * - j = 1: [20]
         * - j = 2: [20,30]
         * - j = 3: [20,30,40]
         * 
         * i = 2:
         * - j = 2: [30]
         * - j = 3: [30,40]
         * 
         * i = 3:
         * - j = 3: [40]
         * 
         * Total subarrays: 10
         */
        
        int marks[] = {10, 20, 30, 40};
        
        System.out.println("Print Subarrays Problem");
        System.out.println("Array: " + Arrays.toString(marks));
        System.out.println("All subarrays:");
        
        printSubarrays(marks);
        
        // Calculate expected total subarrays
        int expectedTotal = (marks.length * (marks.length + 1)) / 2;
        System.out.println("Expected total (formula n(n+1)/2): " + expectedTotal);
        
        // Show step-by-step process for smaller array
        System.out.println("\nStep-by-step process for [10, 20, 30]:");
        System.out.println("Array length: 3, Expected subarrays: 3×4/2 = 6");
        
        int[] smallArray = {10, 20, 30};
        int subarrayCount = 0;
        
        for (int i = 0; i < smallArray.length; i++) {
            System.out.println("i = " + i + " (start at index " + i + "):");
            for (int j = i; j < smallArray.length; j++) {
                System.out.print("  j = " + j + " (end at index " + j + "): [");
                for (int k = i; k <= j; k++) {
                    System.out.print(smallArray[k]);
                    if (k < j) System.out.print(",");
                }
                System.out.println("]");
                subarrayCount++;
            }
        }
        System.out.println("Total subarrays found: " + subarrayCount);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Three elements
        int[] test2 = {5, 10, 15};
        System.out.println("Test 2: " + Arrays.toString(test2));
        System.out.println("Expected subarrays: " + (test2.length * (test2.length + 1) / 2));
        printSubarrays(test2);
        
        // Test case 3: Two elements
        int[] test3 = {1, 2};
        System.out.println("Test 3: " + Arrays.toString(test3));
        System.out.println("Expected subarrays: " + (test3.length * (test3.length + 1) / 2));
        printSubarrays(test3);
        
        // Test case 4: Single element
        int[] test4 = {42};
        System.out.println("Test 4: " + Arrays.toString(test4));
        System.out.println("Expected subarrays: " + (test4.length * (test4.length + 1) / 2));
        printSubarrays(test4);
    }
}
