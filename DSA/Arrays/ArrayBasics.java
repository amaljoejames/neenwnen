import java.util.*;

/**
 * QUESTION: Array Basics - Fundamental operations on arrays including initialization, 
 * traversal, and basic operations.
 * 
 * QUESTION EXPLANATION:
 * Arrays are fixed-size data structures that store elements of the same type.
 * They provide O(1) access time and are stored contiguously in memory.
 * 
 * APPROACH USED: Direct Array Operations
 * 
 * PSEUDOCODE:
 * function arrayBasics():
 *   // Initialize array
 *   arr = [1, 2, 3, 4, 5]
 *   
 *   // Print array without traversal
 *   printArray(arr)
 *   
 *   // Print array with traversal
 *   for i = 0 to arr.length-1:
 *     print(arr[i])
 *   
 *   // Update array element
 *   arr[2] = 99
 *   printArray(arr)
 *   
 *   // Calculate sum and average
 *   sum = calculateSum(arr)
 *   avg = sum / arr.length
 *   
 *   return
 * 
 * EXPLANATION OF STEPS:
 * 1. Array initialization: Create array with initial values
 * 2. Direct printing: Arrays.toString() for quick output
 * 3. Manual traversal: Use loop to access each element by index
 * 4. Element access: O(1) time complexity using index
 * 5. Element update: Direct assignment to modify value
 * 6. Array operations: Sum, average, min, max
 * 
 * DRY RUN:
 * 
 * Initial array: [1, 2, 3, 4, 5]
 * 
 * Operation 1: Print without traversal
 * Output: [1, 2, 3, 4, 5]
 * 
 * Operation 2: Print with traversal
 * Output: 1 2 3 4 5
 * 
 * Operation 3: Update element at index 2
 * Before: [1, 2, 3, 4, 5]
 * After:  [1, 2, 99, 4, 5]
 * 
 * Operation 4: Calculate sum and average
 * Sum: 1 + 2 + 99 + 4 + 5 = 111
 * Average: 111 / 5 = 22.2
 */
public class ArrayBasics {
    
    // Helper function to print array elements
    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    // Helper function to calculate sum of array elements
    public static int calculateSum(int arr[]) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
    
    // Helper function to find maximum element
    public static int findMax(int arr[]) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    
    // Helper function to find minimum element
    public static int findMin(int arr[]) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test array: [1, 2, 3, 4, 5]
         * 
         * Operations to demonstrate:
         * 1. Direct printing
         * 2. Manual traversal
         * 3. Element update
         * 4. Calculate statistics
         */
        
        // Initialize array
        int arr[] = {1, 2, 3, 4, 5};
        
        System.out.println("Array Basics Demonstration");
        System.out.println("========================");
        
        // Operation 1: Print array without traversal
        System.out.println("Operation 1: Direct array printing");
        System.out.println("Output: " + Arrays.toString(arr));
        
        // Operation 2: Print array with manual traversal
        System.out.println("\nOperation 2: Manual traversal");
        System.out.print("Elements: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        
        // Operation 3: Update element at index 2
        System.out.println("\nOperation 3: Element update");
        System.out.println("Before update: " + Arrays.toString(arr));
        arr[2] = 99;  // Update element at index 2
        System.out.println("After update:  " + Arrays.toString(arr));
        
        // Operation 4: Calculate and display statistics
        System.out.println("\nOperation 4: Array statistics");
        int sum = calculateSum(arr);
        int max = findMax(arr);
        int min = findMin(arr);
        double average = (double) sum / arr.length;
        
        System.out.println("Sum: " + sum);
        System.out.println("Maximum: " + max);
        System.out.println("Minimum: " + min);
        System.out.println("Average: " + average);
        System.out.println("Length: " + arr.length);
        
        // Demonstrate array properties
        System.out.println("\nArray Properties:");
        System.out.println("- Fixed size: " + arr.length);
        System.out.println("- Random access: O(1) time complexity");
        System.out.println("- Sequential access: O(n) time complexity");
        System.out.println("- Memory: Contiguous storage");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        int[] test1 = {10, 20, 30};
        System.out.println("Test 1: " + Arrays.toString(test1));
        System.out.println("Sum: " + calculateSum(test1) + ", Max: " + findMax(test1));
        
        int[] test2 = {5};
        System.out.println("Test 2 (single element): " + Arrays.toString(test2));
        System.out.println("Sum: " + calculateSum(test2) + ", Max: " + findMax(test2) + ", Min: " + findMin(test2));
        
        int[] test3 = {};  // Empty array
        System.out.println("Test 3 (empty array): " + Arrays.toString(test3));
        System.out.println("Sum: " + calculateSum(test3) + ", Length: " + test3.length);
    }
}
