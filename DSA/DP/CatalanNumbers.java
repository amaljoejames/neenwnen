import java.util.*;

/**
 * QUESTION: Catalan Numbers - Find the nth Catalan number. Catalan numbers appear in 
 * various combinatorial problems like counting BSTs, valid parentheses, mountain ranges, etc.
 * 
 * QUESTION EXPLANATION:
 * Catalan sequence: C0, C1, C2, C3, C4, C5, C6, ...
 * Values: 1, 1, 2, 5, 14, 42, 132, ...
 * 
 * For n = 4, Catalan number is 14 because:
 * C4 = C0*C3 + C1*C2 + C2*C1 + C3*C0
 * C4 = 1*5 + 1*2 + 2*1 + 5*1 = 5 + 2 + 2 + 5 = 14
 * 
 * APPROACH USED: Dynamic Programming with Recursion, Memoization, and Tabulation
 * 
 * PSEUDOCODE (Tabulation):
 * function catalanTab(n):
 *   dp = int[n+1]
 *   dp[0] = 1
 *   dp[1] = 1
 *   
 *   for i = 2 to n:        // Calculate Ci
 *     dp[i] = 0
 *     for j = 0 to i-1:    // Ci = Σ(Cj * C(i-j-1))
 *       dp[i] += dp[j] * dp[i-j-1]
 *   
 *   return dp[n]
 * 
 * EXPLANATION OF STEPS:
 * 1. Catalan Number Formula: Cn = Σ(i=0 to n-1) Ci * C(n-i-1)
 * 2. Base Cases: C0 = 1, C1 = 1
 * 3. For each n, we consider all possible splits
 * 4. Each split represents left and right subtrees/partitions
 * 5. Applications:
 *    - Number of BSTs with n nodes
 *    - Number of valid parentheses with n pairs
 *    - Number of mountain ranges with n up/down pairs
 * 
 * DRY RUN for n = 4:
 * 
 * C0 = 1 (base case)
 * C1 = 1 (base case)
 * 
 * C2 = C0*C1 + C1*C0 = 1*1 + 1*1 = 2
 * 
 * C3 = C0*C2 + C1*C1 + C2*C0 = 1*2 + 1*1 + 2*1 = 5
 * 
 * C4 = C0*C3 + C1*C2 + C2*C1 + C3*C0 
 *    = 1*5 + 1*2 + 2*1 + 5*1 
 *    = 5 + 2 + 2 + 5 = 14
 */
public class CatalanNumbers {
    
    // Recursive approach (exponential time)
    public static int catalanRec(int n) {
        // Base case
        if (n == 0 || n == 1) {
            return 1;
        }
        
        int ans = 0;
        
        for (int i = 0; i <= n - 1; i++) {
            ans += catalanRec(i) * catalanRec(n - i - 1);
        }
        
        return ans;
    }
    
    // Memoization approach
    public static int catalanMem(int n, int dp[]) {
        // Base case
        if (n == 0 || n == 1) {
            return 1;
        }
        
        // Already calculated
        if (dp[n] != -1) {
            return dp[n];
        }
        
        int ans = 0;
        
        for (int i = 0; i < n; i++) {
            ans += catalanMem(i, dp) * catalanMem(n - i - 1, dp);
        }
        
        return dp[n] = ans;
    }
    
    // Tabulation approach
    public static int catalanTab(int n) {
        int dp[] = new int[n + 1];
        
        dp[0] = 1;  // Base case
        dp[1] = 1;  // Base case
        
        // Fill dp array
        for (int i = 2; i <= n; i++) {   // Calculate Ci
            dp[i] = 0; // Important initialization
            
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        
        return dp[n];
    }
    
    // Count BSTs with n nodes (application of Catalan numbers)
    public static int countBST(int n) {
        int dp[] = new int[n + 1];
        
        dp[0] = 1;  // Empty tree
        dp[1] = 1;  // Single node tree
        
        for (int i = 2; i < n + 1; i++) {
            // Ci → number of BSTs with i nodes
            for (int j = 0; j < i; j++) {
                int left = dp[j];           // Left subtree nodes
                int right = dp[i - j - 1];  // Right subtree nodes
                
                dp[i] += left * right;
            }
        }
        
        return dp[n];
    }
    
    // Mountain range problem (application of Catalan numbers)
    public static int mountainRanges(int n) {
        int dp[] = new int[n + 1];
        
        dp[0] = 1;  // No pairs
        dp[1] = 1;  // One pair: /\ or \/
        
        for (int i = 2; i < n + 1; i++) {
            // i pairs → mountain ranges = Ci
            for (int j = 0; j < i; j++) {
                int inside = dp[j];        // Inside current peak
                int outside = dp[i - j - 1]; // Outside current peak
                
                dp[i] += inside * outside;
            }
        }
        
        return dp[n];
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: n = 4
         * Expected Catalan number: 14
         * 
         * Step-by-step calculation:
         * 
         * C0 = 1 (base case)
         * C1 = 1 (base case)
         * 
         * C2 = C0*C1 + C1*C0 = 1*1 + 1*1 = 2
         * 
         * C3 = C0*C2 + C1*C1 + C2*C0 
         *    = 1*2 + 1*1 + 2*1 = 5
         * 
         * C4 = C0*C3 + C1*C2 + C2*C1 + C3*C0 
         *    = 1*5 + 1*2 + 2*1 + 5*1 
         *    = 5 + 2 + 2 + 5 = 14
         * 
         * Applications:
         * - 14 BSTs with 4 nodes
         * - 14 valid parentheses with 4 pairs
         * - 14 mountain ranges with 4 up/down pairs
         */
        
        int n = 4;
        
        System.out.println("Catalan Numbers Problem");
        System.out.println("n = " + n);
        
        // Recursive approach
        System.out.println("\nRecursive approach:");
        System.out.println("Catalan(" + n + ") = " + catalanRec(n));
        
        // Memoization approach
        int dp[] = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println("\nMemoization approach:");
        System.out.println("Catalan(" + n + ") = " + catalanMem(n, dp));
        
        // Tabulation approach
        System.out.println("\nTabulation approach:");
        System.out.println("Catalan(" + n + ") = " + catalanTab(n));
        
        // Show DP table for tabulation
        System.out.println("\nDP Table (Tabulation):");
        int dpTab[] = new int[n + 1];
        dpTab[0] = 1;
        dpTab[1] = 1;
        
        System.out.println("C0 = " + dpTab[0]);
        System.out.println("C1 = " + dpTab[1]);
        
        for (int i = 2; i <= n; i++) {
            dpTab[i] = 0;
            System.out.print("C" + i + " = ");
            for (int j = 0; j < i; j++) {
                dpTab[i] += dpTab[j] * dpTab[i - j - 1];
                if (j > 0) System.out.print(" + ");
                System.out.print("C" + j + "*C" + (i-j-1));
            }
            System.out.println(" = " + dpTab[i]);
        }
        
        // Applications
        System.out.println("\nApplications of Catalan Numbers:");
        
        // Count BSTs
        System.out.println("Number of BSTs with " + n + " nodes = " + countBST(n));
        
        // Mountain ranges
        System.out.println("Number of mountain ranges with " + n + " pairs = " + mountainRanges(n));
        
        // Additional test cases
        System.out.println("\nFirst few Catalan numbers:");
        for (int i = 0; i <= 6; i++) {
            System.out.println("C" + i + " = " + catalanTab(i));
        }
        
        // Show applications for different n
        System.out.println("\nApplications for different n:");
        System.out.println("n\tCatalan\tBSTs\tMountain Ranges");
        for (int i = 0; i <= 5; i++) {
            int cat = catalanTab(i);
            System.out.println(i + "\t" + cat + "\t" + countBST(i) + "\t" + mountainRanges(i));
        }
    }
}
