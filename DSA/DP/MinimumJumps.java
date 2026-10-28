import java.util.*;

/**
 * QUESTION: Minimum Jumps to Reach End - Given an array where each element represents 
 * the maximum jump length from that position, find the minimum number of jumps to reach 
 * the end of the array starting from the first position.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [2, 3, 1, 1, 4]
 * - Index 0: Can jump up to 2 positions (to index 1 or 2)
 * - Index 1: Can jump up to 3 positions (to index 2, 3, or 4)
 * - Optimal path: 0 → 1 → 4 (2 jumps)
 * - Alternative paths:
 *   - 0 → 2 → 3 → 4 (3 jumps)
 *   - 0 → 1 → 2 → 3 → 4 (4 jumps)
 * - Minimum jumps: 2
 * 
 * APPROACH USED: Dynamic Programming with Tabulation (Bottom-Up)
 * 
 * PSEUDOCODE:
 * function minJumps(nums):
 *   n = nums.length
 *   dp = int[n]
 *   fill dp with -1
 *   
 *   dp[n-1] = 0  // Last index needs 0 jumps
 *   
 *   // Fill from right to left
 *   for i = n-2 to 0:
 *     steps = nums[i]
 *     ans = ∞
 *     
 *     for j = i+1 to i+steps and j < n:
 *       if dp[j] != -1:
 *         ans = min(ans, dp[j] + 1)
 *     
 *     if ans != ∞:
 *       dp[i] = ans
 *   
 *   return dp[0]
 * 
 * EXPLANATION OF STEPS:
 * 1. DP Array: dp[i] represents minimum jumps needed to reach end from position i
 * 2. Base Case: dp[n-1] = 0 (already at end, no jumps needed)
 * 3. Fill from right to left (easier to know jumps needed from future positions)
 * 4. For each position:
 *    - Check all reachable positions from current position
 *    - Take minimum of (jumps from reachable position + 1)
 *    - If no valid path, keep as -1
 * 5. Final answer: dp[0]
 * 
 * DRY RUN for array [2, 3, 1, 1, 4]:
 * 
 * n = 5, dp = [-1, -1, -1, -1, 0]
 * 
 * i = 3 (value = 1):
 * - Can reach j = 4
 * - dp[4] = 0, so dp[3] = 0 + 1 = 1
 * 
 * i = 2 (value = 1):
 * - Can reach j = 3
 * - dp[3] = 1, so dp[2] = 1 + 1 = 2
 * 
 * i = 1 (value = 3):
 * - Can reach j = 2, 3, 4
 * - dp[2] = 2 → ans = 2 + 1 = 3
 * - dp[3] = 1 → ans = min(3, 1 + 1) = 2
 * - dp[4] = 0 → ans = min(2, 0 + 1) = 1
 * - dp[1] = 1
 * 
 * i = 0 (value = 2):
 * - Can reach j = 1, 2
 * - dp[1] = 1 → ans = 1 + 1 = 2
 * - dp[2] = 2 → ans = min(2, 2 + 1) = 2
 * - dp[0] = 2
 * 
 * Final answer: dp[0] = 2
 * Path: 0 → 1 → 4
 */
public class MinimumJumps {
    
    public static int minJumps(int nums[]) {
        int n = nums.length;
        
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        
        dp[n - 1] = 0;  // Last index → 0 jumps needed
        
        // Fill from right to left
        for (int i = n - 2; i >= 0; i--) {
            int steps = nums[i];
            int ans = Integer.MAX_VALUE;
            
            // Check all reachable positions from current position
            for (int j = i + 1; j <= i + steps && j < n; j++) {
                if (dp[j] != -1) {
                    ans = Math.min(ans, dp[j] + 1);
                }
            }
            
            if (ans != Integer.MAX_VALUE) {
                dp[i] = ans;
            }
        }
        
        return dp[0];
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case:
         * Array: [2, 3, 1, 1, 4]
         * 
         * Expected minimum jumps: 2
         * Optimal path: 0 → 1 → 4
         * 
         * Step-by-step DP table filling:
         * 
         * Initial: dp = [-1, -1, -1, -1, 0]
         * 
         * i = 3 (nums[3] = 1):
         * - Can reach position 4
         * - dp[4] = 0, so dp[3] = 0 + 1 = 1
         * 
         * i = 2 (nums[2] = 1):
         * - Can reach position 3
         * - dp[3] = 1, so dp[2] = 1 + 1 = 2
         * 
         * i = 1 (nums[1] = 3):
         * - Can reach positions 2, 3, 4
         * - dp[2] = 2 → ans = 2 + 1 = 3
         * - dp[3] = 1 → ans = min(3, 1 + 1) = 2
         * - dp[4] = 0 → ans = min(2, 0 + 1) = 1
         * - dp[1] = 1
         * 
         * i = 0 (nums[0] = 2):
         * - Can reach positions 1, 2
         * - dp[1] = 1 → ans = 1 + 1 = 2
         * - dp[2] = 2 → ans = min(2, 2 + 1) = 2
         * - dp[0] = 2
         * 
         * Final dp array: [2, 1, 2, 1, 0]
         * Answer: dp[0] = 2
         * 
         * Optimal path reconstruction:
         * - From 0, choose position 1 (dp[1] + 1 = 2)
         * - From 1, choose position 4 (dp[4] + 1 = 1)
         * - Path: 0 → 1 → 4
         */
        
        int nums[] = {2, 3, 1, 1, 4};
        
        System.out.println("Minimum Jumps to Reach End Problem");
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Index:  0  1  2  3  4");
        System.out.println("Value:  2  3  1  1  4");
        
        int result = minJumps(nums);
        System.out.println("Minimum jumps needed: " + result);
        
        // Show DP table construction
        System.out.println("\nDP Table Construction (right to left):");
        int n = nums.length;
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        dp[n - 1] = 0;
        
        System.out.println("Initial dp: " + Arrays.toString(dp));
        
        for (int i = n - 2; i >= 0; i--) {
            int steps = nums[i];
            int ans = Integer.MAX_VALUE;
            
            System.out.println("\nProcessing position " + i + " (value = " + steps + "):");
            System.out.println("Can reach positions: ");
            
            for (int j = i + 1; j <= i + steps && j < n; j++) {
                System.out.print("  Position " + j + " (dp[" + j + "] = " + dp[j] + ")");
                if (dp[j] != -1) {
                    ans = Math.min(ans, dp[j] + 1);
                    System.out.println(" → ans = " + ans);
                } else {
                    System.out.println(" → unreachable");
                }
            }
            
            if (ans != Integer.MAX_VALUE) {
                dp[i] = ans;
                System.out.println("dp[" + i + "] = " + ans);
            } else {
                System.out.println("dp[" + i + "] = -1 (no path)");
            }
            
            System.out.println("Current dp: " + Arrays.toString(dp));
        }
        
        System.out.println("\nFinal dp array: " + Arrays.toString(dp));
        System.out.println("Minimum jumps: " + dp[0]);
        
        // Show optimal path
        System.out.println("\nOptimal path reconstruction:");
        System.out.println("Path: 0 → 1 → 4");
        System.out.println("Jump 1: From position 0 (value 2) to position 1");
        System.out.println("Jump 2: From position 1 (value 3) to position 4 (end)");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 1: Already at end
        int[] test1 = {0};
        System.out.println("Array [0], jumps: " + minJumps(test1)); // 0
        
        // Test case 2: Cannot reach end
        int[] test2 = {3, 2, 1, 0, 4};
        System.out.println("Array [3,2,1,0,4], jumps: " + minJumps(test2)); // -1 or unreachable
        
        // Test case 3: Single jump
        int[] test3 = {4, 1, 1, 3, 1, 1, 1};
        System.out.println("Array [4,1,1,3,1,1,1], jumps: " + minJumps(test3)); // 2
        
        // Test case 4: Linear array
        int[] test4 = {1, 1, 1, 1, 1};
        System.out.println("Array [1,1,1,1,1], jumps: " + minJumps(test4)); // 4
    }
}
