import java.util.*;

/**
 * QUESTION: Contains Duplicate - Given an integer array nums, return true if any value 
 * appears at least twice in the array, and return false if every element is distinct.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [1, 2, 3, 4, 2, 4, 1]
 * - Check for duplicates:
 *   - 1 appears at positions 0 and 6 → DUPLICATE
 *   - 2 appears at positions 1 and 4 → DUPLICATE
 *   - 3 appears once → UNIQUE
 *   - 4 appears at positions 3 and 5 → DUPLICATE
 * - Since duplicates exist, return true
 * 
 * APPROACH USED: Brute Force Comparison
 * 
 * PSEUDOCODE:
 * function containsDuplicate(nums):
 *   for i = 0 to nums.length-2:
 *     for j = i+1 to nums.length-1:
 *       if nums[i] == nums[j]:
 *         return true
 *   
 *   return false
 * 
 * EXPLANATION OF STEPS:
 * 1. Use nested loops to compare each pair of elements
 * 2. Outer loop selects first element
 * 3. Inner loop compares with all subsequent elements
 * 4. If any match found, return true immediately
 * 5. If no matches found after all comparisons, return false
 * 6. Time complexity: O(n²), Space complexity: O(1)
 * 
 * DRY RUN for array = [1, 2, 3, 4, 2, 4, 1]:
 * 
 * i = 0 (value = 1):
 * - j = 1: 1 ≠ 2
 * - j = 2: 1 ≠ 3
 * - j = 3: 1 ≠ 4
 * - j = 4: 1 ≠ 2
 * - j = 5: 1 ≠ 4
 * - j = 6: 1 = 1 → DUPLICATE FOUND! Return true
 * 
 * Result: true (duplicates exist)
 */
public class ContainsDuplicate {
    
    public static boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true; // Duplicate found
                }
            }
        }
        return false; // No duplicates found
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [1, 2, 3, 4, 2, 4, 1]
         * Expected result: true (duplicates exist)
         * 
         * Step-by-step process:
         * 
         * i = 0 (value = 1):
         * - j = 1: 1 ≠ 2
         * - j = 2: 1 ≠ 3
         * - j = 3: 1 ≠ 4
         * - j = 4: 1 ≠ 2
         * - j = 5: 1 ≠ 4
         * - j = 6: 1 = 1 → DUPLICATE FOUND! Return true
         * 
         * Result: true
         */
        
        int[] arr = {1, 2, 3, 4, 2, 4, 1};
        
        System.out.println("Contains Duplicate Problem");
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Expected result: true (duplicates exist)");
        
        boolean result = containsDuplicate(arr);
        System.out.println("Contains duplicate: " + result);
        
        // Show step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Checking for duplicates:");
        
        boolean found = false;
        for (int i = 0; i < arr.length - 1 && !found; i++) {
            System.out.println("i = " + i + " (value = " + arr[i] + "):");
            for (int j = i + 1; j < arr.length; j++) {
                System.out.print("  Compare with arr[" + j + "] = " + arr[j]);
                if (arr[i] == arr[j]) {
                    System.out.println(" → DUPLICATE FOUND!");
                    found = true;
                    break;
                } else {
                    System.out.println(" → different");
                }
            }
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        int[] test2 = {1, 2, 3, 4, 5};
        System.out.println("Test 2: " + Arrays.toString(test2));
        System.out.println("Contains duplicate: " + containsDuplicate(test2));
        
        int[] test3 = {5, 5, 5, 5};
        System.out.println("Test 3: " + Arrays.toString(test3));
        System.out.println("Contains duplicate: " + containsDuplicate(test3));
        
        int[] test4 = {42};
        System.out.println("Test 4: " + Arrays.toString(test4));
        System.out.println("Contains duplicate: " + containsDuplicate(test4));
        
        int[] test5 = {};
        System.out.println("Test 5: " + Arrays.toString(test5));
        System.out.println("Contains duplicate: " + containsDuplicate(test5));
        
        int[] test6 = {1, 2, 1, 3, 2, 4, 3};
        System.out.println("Test 6: " + Arrays.toString(test6));
        System.out.println("Contains duplicate: " + containsDuplicate(test6));
    }
}
