import java.util.*;

/**
 * QUESTION: Monotonic ArrayList - An ArrayList is monotonic if it is either monotone increasing 
 * or monotone decreasing. An ArrayList nums is monotone increasing if for all i <= j, 
 * nums.get(i) <= nums.get(j). An ArrayList nums is monotone decreasing if for all i <= j, 
 * nums.get(i) >= nums.get(j).
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [1, 2, 2, 3]
 * - Check monotonic increasing: All elements are non-decreasing (1 ≤ 2 ≤ 2 ≤ 3)
 * - Answer: true
 * 
 * - Array: [6, 5, 4, 4]
 * - Check monotonic decreasing: All elements are non-increasing (6 ≥ 5 ≥ 4 ≥ 4)
 * - Answer: true
 * 
 * - Array: [1, 3, 2]
 * - Not monotonic: 1 < 3 but 3 > 2 (violates both conditions)
 * - Answer: false
 * 
 * APPROACH USED: Single Pass with Two Flags
 * 
 * PSEUDOCODE:
 * function isMonotonic(A):
 *   inc = true  // Flag to check if array is increasing
 *   dec = true  // Flag to check if array is decreasing
 *   
 *   for i = 0 to A.size() - 1:
 *     if A.get(i) > A.get(i + 1):
 *       inc = false  // Found decreasing pair
 *     if A.get(i) < A.get(i + 1):
 *       dec = false  // Found increasing pair
 *   
 *   return inc || dec  // Return true if either flag remains true
 * 
 * EXPLANATION OF STEPS:
 * 1. Initialize two boolean flags: inc (increasing) and dec (decreasing)
 * 2. Iterate through adjacent elements
 * 3. For each pair:
 *    - If current > next → array cannot be increasing
 *    - If current < next → array cannot be decreasing
 * 4. If either flag remains true, array is monotonic
 * 5. Return result
 * 
 * DRY RUN for array = [1, 2, 2, 3]:
 * 
 * Initial: inc = true, dec = true
 * 
 * Iteration 1: i=0, compare 1 and 2
 * - 1 ≤ 2 → dec = false (no change to inc)
 * 
 * Iteration 2: i=1, compare 2 and 2
 * - 2 = 2 → no change to either flag
 * 
 * Iteration 3: i=2, compare 2 and 3
 * - 2 ≤ 3 → dec = false (no change to inc)
 * 
 * Final state: inc = true, dec = false
 * Result: inc || dec = true
 */
public class MonotonicArrayList {
    
    public static boolean isMonotonic(ArrayList<Integer> A) {
        boolean inc = true; // Flag to check if array is increasing
        boolean dec = true; // Flag to check if array is decreasing

        // Iterate through array and compare adjacent elements
        for (int i = 0; i < A.size() - 1; i++) {
            if (A.get(i) > A.get(i + 1)) {
                inc = false; // If any element is greater than next, it's not increasing
            }
            if (A.get(i) < A.get(i + 1)) {
                dec = false; // If any element is less than next, it's not decreasing
            }
        }

        // Return true if either inc or dec is true
        return inc || dec;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [1, 2, 2, 3]
         * Expected result: true
         * 
         * Step-by-step process:
         * 
         * Initial: inc = true, dec = true
         * 
         * Iteration 1: i=0, compare A[0]=1 and A[1]=2
         * - 1 ≤ 2 → dec = false (inc remains true)
         * 
         * Iteration 2: i=1, compare A[1]=2 and A[2]=2
         * - 2 = 2 → no change to either flag
         * 
         * Iteration 3: i=2, compare A[2]=2 and A[3]=3
         * - 2 ≤ 3 → dec = false (inc remains true)
         * 
         * Final: inc = true, dec = false
         * Result: true || false = true
         */
        
        // Test case 1: Monotonic increasing
        ArrayList<Integer> test1 = new ArrayList<>(Arrays.asList(1, 2, 2, 3));
        System.out.println("Test 1: " + test1);
        System.out.println("Is monotonic: " + isMonotonic(test1));
        System.out.println("Reason: All elements are non-decreasing");
        
        // Test case 2: Monotonic decreasing
        ArrayList<Integer> test2 = new ArrayList<>(Arrays.asList(6, 5, 4, 4));
        System.out.println("\nTest 2: " + test2);
        System.out.println("Is monotonic: " + isMonotonic(test2));
        System.out.println("Reason: All elements are non-increasing");
        
        // Test case 3: Not monotonic
        ArrayList<Integer> test3 = new ArrayList<>(Arrays.asList(1, 3, 2));
        System.out.println("\nTest 3: " + test3);
        System.out.println("Is monotonic: " + isMonotonic(test3));
        System.out.println("Reason: 1 < 3 but 3 > 2 (violates both conditions)");
        
        // Test case 4: Single element
        ArrayList<Integer> test4 = new ArrayList<>(Arrays.asList(5));
        System.out.println("\nTest 4: " + test4);
        System.out.println("Is monotonic: " + isMonotonic(test4));
        System.out.println("Reason: Single element is always monotonic");
        
        // Test case 5: Empty array
        ArrayList<Integer> test5 = new ArrayList<>();
        System.out.println("\nTest 5: " + test5);
        System.out.println("Is monotonic: " + isMonotonic(test5));
        System.out.println("Reason: Empty array is considered monotonic");
        
        // Test case 6: Two elements
        ArrayList<Integer> test6 = new ArrayList<>(Arrays.asList(2, 2));
        System.out.println("\nTest 6: " + test6);
        System.out.println("Is monotonic: " + isMonotonic(test6));
        System.out.println("Reason: Equal elements are both non-decreasing and non-increasing");
    }
}
