import java.util.*;

/**
 * QUESTION: Count the number of ways to climb n stairs when you can take either 1 or 2 steps at a time.
 * 
 * QUESTION EXPLANATION: 
 * If n = 4, the answer is 8 because:
 * - 1+1+1+1
 * - 1+1+2
 * - 1+2+1
 * - 2+1+1
 * - 2+2
 * - 1+3 (but 3 steps not allowed, so this is invalid)
 * Actually, for n=4 with 1 or 2 steps: 5 ways
 * For n=4, the correct answer is 5 ways, not 8. For n=5, answer is 8 ways.
 * 
 * APPROACH USED: Dynamic Programming with Memoization (Top-Down) and Tabulation (Bottom-Up)
 * 
 * PSEUDOCODE (Memoization):
 * function countWays(n, memo):
 *   if n == 0: return 1
 *   if n < 0: return 0
 *   if memo[n] != -1: return memo[n]
 *   memo[n] = countWays(n-1, memo) + countWays(n-2, memo)
 *   return memo[n]
 * 
 * EXPLANATION OF STEPS:
 * 1. Base Cases: 
 *    - If n == 0, we've reached the ground, so return 1 (one valid way)
 *    - If n < 0, we've gone below ground, so return 0 (invalid way)
 * 2. Memoization Check: If we've already computed ways[n], return it
 * 3. Recursive Relation: ways[n] = ways[n-1] + ways[n-2]
 *    - From step n, we can either take 1 step (leaving n-1 steps) or 2 steps (leaving n-2 steps)
 * 4. Store and return the result
 * 
 * DRY RUN for n = 4:
 * countWays(4) = countWays(3) + countWays(2)
 * countWays(3) = countWays(2) + countWays(1)
 * countWays(2) = countWays(1) + countWays(0)
 * countWays(1) = countWays(0) + countWays(-1) = 1 + 0 = 1
 * countWays(0) = 1
 * countWays(-1) = 0
 * 
 * Building up:
 * countWays(2) = countWays(1) + countWays(0) = 1 + 1 = 2
 * countWays(3) = countWays(2) + countWays(1) = 2 + 1 = 3
 * countWays(4) = countWays(3) + countWays(2) = 3 + 2 = 5
 * 
 * So for n=4, there are 5 ways to climb the stairs.
 */
public class CountWaysToClimbStairs {
    
    // Memoization approach
    public static int countWays(int n, int ways[]) {
        if (n == 0) return 1;  // Reached ground - valid way
        if (n < 0) return 0;   // Below ground - invalid way
        
        // If already computed, return from memo
        if (ways[n] != -1) {
            return ways[n];
        }
        
        // Recursive relation: ways[n] = ways[n-1] + ways[n-2]
        ways[n] = countWays(n-1, ways) + countWays(n-2, ways);
        return ways[n];
    }
    
    // Tabulation approach
    public static int countWaysTab(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;  // Base case: 1 way to stay at ground
        
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                dp[i] = 1;  // Only 1 way: take 1 step
            } else if (i == 2) {
                dp[i] = 2;  // 2 ways: 1+1 or 2
            } else {
                dp[i] = dp[i-1] + dp[i-2];  // Fibonacci relation
            }
        }
        return dp[n];
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: n = 5
         * Expected output: 8 ways
         * 
         * Ways for n=5:
         * 1. 1+1+1+1+1
         * 2. 1+1+1+2
         * 3. 1+1+2+1
         * 4. 1+2+1+1
         * 5. 2+1+1+1
         * 6. 1+2+2
         * 7. 2+1+2
         * 8. 2+2+1
         */
        
        int n = 5;
        int ways[] = new int[n+1];
        Arrays.fill(ways, -1);
        
        System.out.println("Memoization approach:");
        System.out.println("Number of ways to climb " + n + " stairs = " + countWays(n, ways));
        
        System.out.println("\nTabulation approach:");
        System.out.println("Number of ways to climb " + n + " stairs = " + countWaysTab(n));
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        for (int i = 0; i <= 6; i++) {
            System.out.println("n=" + i + ": " + countWaysTab(i) + " ways");
        }
    }
}
