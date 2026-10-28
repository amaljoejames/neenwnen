import java.util.*;

/**
 * QUESTION: Three Sum - Find all unique triplets [nums[i], nums[j], nums[k]] 
 * such that i != j, i != k, j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Solution set must not contain duplicate triplets.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [-1, 0, 1, 2, -1, -4]
 * - Possible triplets that sum to 0:
 *   - [-1, 0, 1] → -1 + 0 + 1 = 0
 *   - [-1, -1, 2] → -1 + -1 + 2 = 0
 * - Unique triplets: [[-1, -1, 2], [-1, 0, 1]]
 * 
 * APPROACH USED: Brute Force with Hash Set for Duplicates
 * 
 * PSEUDOCODE:
 * function threeSum(nums):
 *   result = new ArrayList<>()
 *   uniqueTriplets = new HashSet<>()
 *   
 *   for i = 0 to nums.length-1:
 *     for j = i+1 to nums.length-1:
 *       for k = j+1 to nums.length-1:
 *         if nums[i] + nums[j] + nums[k] == 0:
 *           triplet = [nums[i], nums[j], nums[k]]
 *           sort(triplet)
 *           uniqueTriplets.add(triplet)
 *   
 *   result.addAll(uniqueTriplets)
 *   return result
 * 
 * EXPLANATION OF STEPS:
 * 1. Use three nested loops to generate all possible triplets
 * 2. Check if sum of triplet equals zero
 * 3. Sort each valid triplet to ensure consistent ordering
 * 4. Use HashSet to store unique triplets (handles duplicates)
 * 5. Convert set to list and return
 * 6. Time complexity: O(n³) for brute force approach
 * 
 * DRY RUN for array = [-1, 0, 1, 2, -1, -4]:
 * 
 * Triplet combinations:
 * - (-1, 0, 1): sum = 0 → valid → sort → [-1, 0, 1]
 * - (-1, 2, -1): sum = 0 → valid → sort → [-1, -1, 2]
 * - (0, 1, -1): sum = 0 → valid → sort → [-1, 0, 1] (duplicate)
 * 
 * Unique triplets: [[-1, -1, 2], [-1, 0, 1]]
 */
public class ThreeSum {
    
    public List<List<Integer>> threeSum(int[] nums) {
        // To store the final result
        List<List<Integer>> result = new ArrayList<>();
        
        // Use a set to handle duplicate triplets
        Set<List<Integer>> uniqueTriplets = new HashSet<>();
        
        // Brute force: Use three nested loops to find all triplets
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    // Check if sum of triplet is zero
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        // Create a list for the triplet
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(nums[i]);
                        triplet.add(nums[j]);
                        triplet.add(nums[k]);
                        
                        // Sort the triplet to ensure uniqueness
                        Collections.sort(triplet);
                        
                        // Add sorted triplet to the set
                        uniqueTriplets.add(triplet);
                    }
                }
            }
        }
        
        // Convert set of unique triplets into a list
        result.addAll(uniqueTriplets);
        
        return result;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [-1, 0, 1, 2, -1, -4]
         * Expected result: [[-1, -1, 2], [-1, 0, 1]]
         * 
         * Step-by-step process:
         * 
         * Check triplet combinations:
         * - (-1, 0, 1): sum = 0 → valid → [-1, 0, 1]
         * - (-1, 2, -1): sum = 0 → valid → [-1, -1, 2]
         * - (0, 1, -1): sum = 0 → valid → [-1, 0, 1] (duplicate)
         * 
         * Unique triplets: [[-1, -1, 2], [-1, 0, 1]]
         */
        
        ThreeSum solution = new ThreeSum();
        
        // Test case 1: Example from problem statement
        int[] nums = {-1, 0, 1, 2, -1, -4};
        
        System.out.println("Three Sum Problem");
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Looking for triplets that sum to 0");
        
        // Get the result
        List<List<Integer>> triplets = solution.threeSum(nums);
        
        // Print the result
        System.out.println("\nUnique triplets that sum to 0:");
        for (List<Integer> triplet : triplets) {
            System.out.println(triplet + " (sum: " + (triplet.get(0) + triplet.get(1) + triplet.get(2)) + ")");
        }
        
        // Show step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("Checking all possible triplets:");
        
        int tripletCount = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    tripletCount++;
                    System.out.println("Triplet " + tripletCount + ": (" + nums[i] + ", " + nums[j] + ", " + nums[k] + ") = " + sum);
                    
                    if (sum == 0) {
                        System.out.println("  → VALID! Sum equals 0");
                    }
                }
            }
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        int[] test2 = {0, 0, 0};
        List<List<Integer>> result2 = solution.threeSum(test2);
        System.out.println("Test 2: " + Arrays.toString(test2));
        System.out.println("Result: " + result2);
        
        int[] test3 = {1, 2, -2, -1};
        List<List<Integer>> result3 = solution.threeSum(test3);
        System.out.println("Test 3: " + Arrays.toString(test3));
        System.out.println("Result: " + result3);
        
        int[] test4 = {};
        List<List<Integer>> result4 = solution.threeSum(test4);
        System.out.println("Test 4: " + Arrays.toString(test4));
        System.out.println("Result: " + result4);
    }
}
