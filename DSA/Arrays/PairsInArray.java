import java.util.*;

/**
 * QUESTION: Pairs in Array - Print all possible pairs of elements from an array.
 * Each pair consists of two different elements where the first element comes before the second.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [10, 20, 30, 40, 50, 60, 70, 80]
 * - Possible pairs:
 *   - (10,20), (10,30), (10,40), (10,50), (10,60), (10,70), (10,80)
 *   - (20,30), (20,40), (20,50), (20,60), (20,70), (20,80)
 *   - (30,40), (30,50), (30,60), (30,70), (30,80)
 *   - And so on...
 * - Total pairs: n(n-1)/2 = 8×7/2 = 28 pairs
 * 
 * APPROACH USED: Nested Loops
 * 
 * PSEUDOCODE:
 * function printPairs(arr):
 *   for i = 0 to arr.length-1:
 *     curr = arr[i]
 *     for j = i+1 to arr.length-1:
 *       print("(" + curr + "," + arr[j] + ")")
 *     print()  // New line after each i
 * 
 * EXPLANATION OF STEPS:
 * 1. Outer loop selects first element of pair
 * 2. Inner loop selects second element of pair
 * 3. Ensure j starts from i+1 to avoid duplicates and self-pairs
 * 4. Print each pair in format (first,second)
 * 5. Total pairs = n(n-1)/2 where n is array length
 * 6. Time complexity: O(n²)
 * 
 * DRY RUN for array = [10, 20, 30, 40]:
 * 
 * i = 0, curr = 10:
 * - j = 1: (10,20)
 * - j = 2: (10,30)
 * - j = 3: (10,40)
 * 
 * i = 1, curr = 20:
 * - j = 2: (20,30)
 * - j = 3: (20,40)
 * 
 * i = 2, curr = 30:
 * - j = 3: (30,40)
 * 
 * i = 3: No pairs (no j > i)
 * 
 * Total pairs: 6
 */
public class PairsInArray {
    
    public static void printPairs(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int curr = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                System.out.println("(" + curr + "," + arr[j] + ")");
            }
            System.out.println();  // New line after each i
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [10, 20, 30, 40]
         * Expected result: 6 pairs total
         * 
         * Step-by-step process:
         * 
         * i = 0, curr = 10:
         * - j = 1: (10,20)
         * - j = 2: (10,30)
         * - j = 3: (10,40)
         * 
         * i = 1, curr = 20:
         * - j = 2: (20,30)
         * - j = 3: (20,40)
         * 
         * i = 2, curr = 30:
         * - j = 3: (30,40)
         * 
         * Total pairs: 6
         */
        
        int marks[] = {10, 20, 30, 40, 50, 60, 70, 80};
        
        System.out.println("Pairs in Array Problem");
        System.out.println("Array: " + Arrays.toString(marks));
        System.out.println("All possible pairs:");
        
        printPairs(marks);
        
        // Calculate and show total pairs
        int totalPairs = (marks.length * (marks.length - 1)) / 2;
        System.out.println("Total pairs: " + totalPairs + " (formula: n(n-1)/2)");
        
        // Show step-by-step process for smaller array
        System.out.println("\nStep-by-step process for [10, 20, 30, 40]:");
        System.out.println("Array length: 4, Expected pairs: 4×3/2 = 6");
        
        int[] smallArray = {10, 20, 30, 40};
        for (int i = 0; i < smallArray.length; i++) {
            System.out.println("i = " + i + ", curr = " + smallArray[i] + ":");
            for (int j = i + 1; j < smallArray.length; j++) {
                System.out.println("  j = " + j + ": (" + smallArray[i] + "," + smallArray[j] + ")");
            }
            System.out.println();
        }
        
        // Additional test cases
        System.out.println("Additional test cases:");
        
        // Test case 2: Three elements
        int[] test2 = {5, 10, 15};
        System.out.println("Test 2: " + Arrays.toString(test2));
        System.out.println("Expected pairs: " + (test2.length * (test2.length - 1) / 2));
        printPairs(test2);
        
        // Test case 3: Two elements
        int[] test3 = {1, 2};
        System.out.println("Test 3: " + Arrays.toString(test3));
        System.out.println("Expected pairs: " + (test3.length * (test3.length - 1) / 2));
        printPairs(test3);
        
        // Test case 4: Single element
        int[] test4 = {42};
        System.out.println("Test 4: " + Arrays.toString(test4));
        System.out.println("Expected pairs: " + (test4.length * (test4.length - 1) / 2));
        printPairs(test4);
    }
}
