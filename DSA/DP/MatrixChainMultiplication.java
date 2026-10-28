/**
 * QUESTION: Matrix Chain Multiplication - Given dimensions of matrices in a chain, find the 
 * minimum number of multiplications needed to multiply all matrices. The order of multiplication 
 * affects the total number of operations.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Matrices: A1(1×2), A2(2×3), A3(3×4), A4(4×3)
 * - Dimensions array: [1, 2, 3, 4, 3]
 * - Possible orders:
 *   1. ((A1×A2)×A3)×A4 = (1×2×3) + (1×3×4) + (1×4×3) = 6 + 12 + 12 = 30
 *   2. (A1×(A2×A3))×A4 = (2×3×4) + (1×2×4) + (1×4×3) = 24 + 8 + 12 = 44
 *   3. A1×((A2×A3)×A4) = (2×3×4) + (2×4×3) + (1×2×3) = 24 + 24 + 6 = 54
 *   4. A1×(A2×(A3×A4)) = (3×4×3) + (2×3×3) + (1×2×3) = 36 + 18 + 6 = 60
 *   5. (A1×A2)×(A3×A4) = (1×2×3) + (3×4×3) + (1×3×3) = 6 + 36 + 9 = 51
 * - Minimum cost: 30 (order: ((A1×A2)×A3)×A4)
 * 
 * APPROACH USED: Dynamic Programming with Recursion, Memoization, and Tabulation
 * 
 * PSEUDOCODE (Tabulation):
 * function mcmTab(arr):
 *   n = arr.length
 *   dp = int[n][n]
 *   
 *   // Initialize single matrix cost = 0
 *   for i = 0 to n-1:
 *     dp[i][i] = 0
 *   
 *   // Bottom-up for chain length
 *   for len = 2 to n-1:        // Chain length
 *     for i = 1 to n-len:      // Start index
 *       j = i + len - 1        // End index
 *       dp[i][j] = ∞
 *       
 *       for k = i to j-1:      // Split point
 *         cost1 = dp[i][k]     // Cost of left part
 *         cost2 = dp[k+1][j]   // Cost of right part
 *         cost3 = arr[i-1] * arr[k] * arr[j]  // Cost of multiplying results
 *         dp[i][j] = min(dp[i][j], cost1 + cost2 + cost3)
 *   
 *   return dp[1][n-1]
 * 
 * EXPLANATION OF STEPS:
 * 1. Problem: Find optimal parenthesization to minimize multiplication cost
 * 2. Cost formula: For matrices A(p×q) and B(q×r), multiplication cost = p×q×r
 * 3. DP Table: dp[i][j] = minimum cost to multiply matrices Ai to Aj
 * 4. For each chain length, try all possible split points
 * 5. Total cost = cost of left part + cost of right part + cost of multiplying results
 * 6. Base case: dp[i][i] = 0 (single matrix needs no multiplication)
 * 
 * DRY RUN for arr = [1, 2, 3, 4, 3]:
 * 
 * Matrices: A1(1×2), A2(2×3), A3(3×4), A4(4×3)
 * 
 * Chain length 2:
 * dp[1][2] = 1×2×3 = 6
 * dp[2][3] = 2×3×4 = 24
 * dp[3][4] = 3×4×3 = 36
 * 
 * Chain length 3:
 * dp[1][3] = min(
 *   dp[1][1] + dp[2][3] + 1×2×4 = 0 + 24 + 8 = 32,
 *   dp[1][2] + dp[3][3] + 1×3×4 = 6 + 0 + 12 = 18
 * ) = 18
 * 
 * dp[2][4] = min(
 *   dp[2][2] + dp[3][4] + 2×3×3 = 0 + 36 + 18 = 54,
 *   dp[2][3] + dp[4][4] + 2×4×3 = 24 + 0 + 24 = 48
 * ) = 48
 * 
 * Chain length 4:
 * dp[1][4] = min(
 *   dp[1][1] + dp[2][4] + 1×2×3 = 0 + 48 + 6 = 54,
 *   dp[1][2] + dp[3][4] + 1×3×3 = 6 + 36 + 9 = 51,
 *   dp[1][3] + dp[4][4] + 1×4×3 = 18 + 0 + 12 = 30
 * ) = 30
 * 
 * Final answer: dp[1][4] = 30
 */
public class MatrixChainMultiplication {
    
    // Recursive approach (exponential time)
    static int mcm(int arr[], int i, int j) {
        // Base case (single matrix)
        if (i == j) {
            return 0;
        }
        
        int ans = Integer.MAX_VALUE;
        
        for (int k = i; k <= j - 1; k++) {
            int cost1 = mcm(arr, i, k);       // Ai...Ak
            int cost2 = mcm(arr, k + 1, j);   // A(k+1)...Aj
            int cost3 = arr[i - 1] * arr[k] * arr[j];  // Cost of multiplying results
            
            int finalCost = cost1 + cost2 + cost3;
            ans = Math.min(ans, finalCost);
        }
        
        return ans;
    }
    
    // Memoization approach
    public static int mcmMem(int arr[], int i, int j, int dp[][]) {
        // Base case
        if (i == j) {
            return 0;
        }
        
        // Already calculated
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        
        int ans = Integer.MAX_VALUE;
        
        for (int k = i; k <= j - 1; k++) {
            int cost1 = mcmMem(arr, i, k, dp);
            int cost2 = mcmMem(arr, k + 1, j, dp);
            int cost3 = arr[i - 1] * arr[k] * arr[j];
            
            ans = Math.min(ans, cost1 + cost2 + cost3);
        }
        
        return dp[i][j] = ans;
    }
    
    // Tabulation approach
    public static int mcmTab(int arr[]) {
        int n = arr.length;
        int dp[][] = new int[n][n];
        
        // Initialization (single matrix cost = 0)
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        
        // Bottom-up for chain length
        for (int len = 2; len <= n - 1; len++) {   // Chain length
            for (int i = 1; i <= n - len; i++) {
                int j = i + len - 1;
                
                dp[i][j] = Integer.MAX_VALUE;
                
                for (int k = i; k <= j - 1; k++) {
                    int cost1 = dp[i][k];           // Cost of left part
                    int cost2 = dp[k + 1][j];        // Cost of right part
                    int cost3 = arr[i - 1] * arr[k] * arr[j];  // Cost of multiplying results
                    
                    dp[i][j] = Math.min(dp[i][j], cost1 + cost2 + cost3);
                }
            }
        }
        
        return dp[1][n - 1];
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case:
         * Dimensions array: [1, 2, 3, 4, 3]
         * Matrices: A1(1×2), A2(2×3), A3(3×4), A4(4×3)
         * 
         * Expected minimum cost: 30
         * Optimal order: ((A1×A2)×A3)×A4
         * 
         * Step-by-step DP table filling:
         * 
         * Chain length 2 (adjacent matrices):
         * dp[1][2] = 1×2×3 = 6  (A1×A2)
         * dp[2][3] = 2×3×4 = 24 (A2×A3)
         * dp[3][4] = 3×4×3 = 36 (A3×A4)
         * 
         * Chain length 3:
         * dp[1][3] = min(
         *   (A1)×(A2×A3): 0 + 24 + 1×2×4 = 32,
         *   (A1×A2)×(A3): 6 + 0 + 1×3×4 = 18
         * ) = 18
         * 
         * Chain length 4:
         * dp[1][4] = min(
         *   (A1)×(A2×A3×A4): 0 + 48 + 1×2×3 = 54,
         *   (A1×A2)×(A3×A4): 6 + 36 + 1×3×3 = 51,
         *   (A1×A2×A3)×(A4): 18 + 0 + 1×4×3 = 30
         * ) = 30
         * 
         * Final answer: dp[1][4] = 30
         */
        
        int arr[] = {1, 2, 3, 4, 3};
        int n = arr.length;
        
        System.out.println("Matrix Chain Multiplication Problem");
        System.out.println("Dimensions array: " + java.util.Arrays.toString(arr));
        System.out.println("Matrices: A1(1×2), A2(2×3), A3(3×4), A4(4×3)");
        
        // Recursive approach
        System.out.println("\nRecursive approach:");
        System.out.println("Minimum cost = " + mcm(arr, 1, n - 1));
        
        // Memoization approach
        int dp[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }
        System.out.println("\nMemoization approach:");
        System.out.println("Minimum cost = " + mcmMem(arr, 1, n - 1, dp));
        
        // Tabulation approach
        System.out.println("\nTabulation approach:");
        System.out.println("Minimum cost = " + mcmTab(arr));
        
        // Print DP table for tabulation approach
        System.out.println("\nDP Table (Tabulation):");
        System.out.print("i\\j\t");
        for (int j = 1; j < n; j++) {
            System.out.print(j + "\t");
        }
        System.out.println();
        
        int dpTab[][] = new int[n][n];
        
        // Fill and display table
        for (int i = 1; i < n; i++) {
            System.out.print(i + "\t");
            for (int j = 1; j < n; j++) {
                if (i == j) {
                    dpTab[i][j] = 0;
                } else if (i < j) {
                    dpTab[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k <= j - 1; k++) {
                        int cost1 = dpTab[i][k];
                        int cost2 = dpTab[k + 1][j];
                        int cost3 = arr[i - 1] * arr[k] * arr[j];
                        dpTab[i][j] = Math.min(dpTab[i][j], cost1 + cost2 + cost3);
                    }
                } else {
                    dpTab[i][j] = 0; // Not used
                }
                
                if (dpTab[i][j] == Integer.MAX_VALUE) {
                    System.out.print("∞\t");
                } else {
                    System.out.print(dpTab[i][j] + "\t");
                }
            }
            System.out.println();
        }
        
        // Show optimal parenthesization
        System.out.println("\nOptimal parenthesization: ((A1×A2)×A3)×A4");
        System.out.println("Cost breakdown:");
        System.out.println("1. A1×A2: 1×2×3 = 6");
        System.out.println("2. (A1×A2)×A3: 1×3×4 = 12");
        System.out.println("3. ((A1×A2)×A3)×A4: 1×4×3 = 12");
        System.out.println("Total cost: 6 + 12 + 12 = 30");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 1: Simple case
        int[] test1 = {10, 20, 30};
        System.out.println("Dimensions [10,20,30], cost: " + mcmTab(test1)); // 6000
        
        // Test case 2: Three matrices
        int[] test2 = {40, 20, 30, 10};
        System.out.println("Dimensions [40,20,30,10], cost: " + mcmTab(test2)); // 26000
    }
}
