import java.util.*;

/**
 * QUESTION: Grid Ways - Find number of ways to reach bottom-right corner of a grid
 * from top-left corner, moving only right and down.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Grid size: 3×3 (rows=3, cols=3)
 * - Start: (0,0), End: (2,2)
 * - Possible moves: Right (i, j+1) or Down (i+1, j)
 * - Total ways: 6 paths
 * - Some paths: RRDD, RDRD, RDDR, DRRD, DRDR, DDRR
 * 
 * APPROACH USED: Recursive Backtracking
 * 
 * PSEUDOCODE:
 * function gridWays(i, j, n, m):
 *   if i == n-1 && j == m-1:
 *     return 1  // Reached destination
 *   
 *   if i == n || j == m:
 *     return 0  // Out of bounds
 *   
 *   // Recursion: move right + move down
 *   return gridWays(i+1, j, n, m) + gridWays(i, j+1, n, m)
 * 
 * EXPLANATION OF STEPS:
 * 1. Start from (0,0)
 * 2. At each cell, two choices: move right or down
 * 3. Base case: reached destination (n-1, m-1) → count 1 way
 * 4. Out of bounds → count 0 ways
 * 5. Sum of paths from right and down moves
 * 6. Time complexity: O(2^(n+m)), Space complexity: O(n+m)
 * 
 * DRY RUN for 2×2 grid:
 * 
 * gridWays(0,0,2,2)
 * - Not at destination, not out of bounds
 * - Return gridWays(1,0,2,2) + gridWays(0,1,2,2)
 * 
 * gridWays(1,0,2,2):
 * - Return gridWays(2,0,2,2) + gridWays(1,1,2,2)
 * - gridWays(2,0,2,2) = 0 (out of bounds)
 * - gridWays(1,1,2,2) = gridWays(2,1,2,2) + gridWays(1,2,2,2) = 0 + 0 = 0
 * 
 * gridWays(0,1,2,2):
 * - Return gridWays(1,1,2,2) + gridWays(0,2,2,2)
 * - gridWays(1,1,2,2) = 0 (from above)
 * - gridWays(0,2,2,2) = 0 (out of bounds)
 * 
 * Wait, there's an error in logic. Let me trace correctly:
 * 
 * Correct trace for 2×2:
 * gridWays(0,0,2,2) = gridWays(1,0,2,2) + gridWays(0,1,2,2)
 * gridWays(1,0,2,2) = gridWays(2,0,2,2) + gridWays(1,1,2,2) = 0 + 1 = 1
 * gridWays(0,1,2,2) = gridWays(1,1,2,2) + gridWays(0,2,2,2) = 1 + 0 = 1
 * Total: 1 + 1 = 2 ways
 */
public class GridWays {
    
    public static int gridWays(int i, int j, int n, int m) {
        // Base case: reached destination
        if (i == n - 1 && j == m - 1) {
            return 1;
        }
        
        // Out of bounds
        if (i == n || j == m) {
            return 0;
        }
        
        // Recursion: move down + move right
        return gridWays(i + 1, j, n, m) + gridWays(i, j + 1, n, m);
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: 2×2 grid
         * Expected result: 2 ways
         * 
         * Step-by-step process:
         * 
         * gridWays(0,0,2,2)
         * = gridWays(1,0,2,2) + gridWays(0,1,2,2)
         * 
         * gridWays(1,0,2,2) = gridWays(2,0,2,2) + gridWays(1,1,2,2) = 0 + 1 = 1
         * gridWays(0,1,2,2) = gridWays(1,1,2,2) + gridWays(0,2,2,2) = 1 + 0 = 1
         * 
         * Total: 1 + 1 = 2 ways
         */
        
        int n = 3, m = 3;
        
        System.out.println("Grid Ways Problem");
        System.out.println("Grid size: " + n + "×" + m);
        System.out.println("Number of ways: " + gridWays(0, 0, n, m));
        
        // Show step-by-step for 2×2 grid
        System.out.println("\nStep-by-step for 2×2 grid:");
        System.out.println("gridWays(0,0,2,2)");
        System.out.println("= gridWays(1,0,2,2) + gridWays(0,1,2,2)");
        System.out.println("gridWays(1,0,2,2) = gridWays(2,0,2,2) + gridWays(1,1,2,2) = 0 + 1 = 1");
        System.out.println("gridWays(0,1,2,2) = gridWays(1,1,2,2) + gridWays(0,2,2,2) = 1 + 0 = 1");
        System.out.println("Total: 1 + 1 = 2 ways");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        System.out.println("Test 2: 1×1 grid");
        System.out.println("Ways: " + gridWays(0, 0, 1, 1));
        
        System.out.println("\nTest 3: 2×3 grid");
        System.out.println("Ways: " + gridWays(0, 0, 2, 3));
        
        System.out.println("\nTest 4: 3×3 grid");
        System.out.println("Ways: " + gridWays(0, 0, 3, 3));
        
        System.out.println("\nTest 5: 4×4 grid");
        System.out.println("Ways: " + gridWays(0, 0, 4, 4));
        
        // Show formula for verification
        System.out.println("\nFormula verification:");
        System.out.println("For n×m grid, ways = C(n+m-2, n-1) where C is combination");
        System.out.println("For 3×3: C(4,2) = 6 (should match recursive result)");
    }
}
