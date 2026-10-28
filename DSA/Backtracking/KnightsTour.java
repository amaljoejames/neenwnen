import java.util.*;

/**
 * QUESTION: Knight's Tour - Given an N×N board with knight placed on first block,
 * find a sequence of moves where knight visits each square exactly once.
 * Knight moves in L-shape: 2 squares in one direction, 1 in perpendicular direction.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - N = 8 (standard chessboard)
 * - Knight starts at (0,0)
 * - Possible moves from any position: 8 directions
 * - Need to visit all 64 squares exactly once
 * - Solution shows order of visitation (0,1,2,...,63)
 * 
 * APPROACH USED: Backtracking with Safety Check
 * 
 * PSEUDOCODE:
 * function solveKT():
 *   sol[N][N] = initialize with -1
 *   
 *   xMove = [2, 1, -1, -2, -2, -1, 1, 2]
 *   yMove = [1, 2, 2, 1, -1, -2, -2, -1]
 *   
 *   sol[0][0] = 0  // Starting position
 *   
 *   if !solveKTUtil(0, 0, 1, sol, xMove, yMove):
 *     print("No solution")
 *     return false
 *   else:
 *     printSolution(sol)
 *     return true
 * 
 * function solveKTUtil(x, y, movei, sol, xMove, yMove):
 *   if movei == N*N:
 *     return true
 *   
 *   for k = 0 to 7:
 *     next_x = x + xMove[k]
 *     next_y = y + yMove[k]
 *     
 *     if isSafe(next_x, next_y, sol):
 *       sol[next_x][next_y] = movei
 *       if solveKTUtil(next_x, next_y, movei + 1, sol, xMove, yMove):
 *         return true
 *       sol[next_x][next_y] = -1  // Backtrack
 *   
 *   return false
 * 
 * function isSafe(x, y, sol):
 *   return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1)
 * 
 * EXPLANATION OF STEPS:
 * 1. Initialize solution matrix with -1 (unvisited)
 * 2. Place knight at starting position (0,0) with move number 0
 * 3. For current position, try all 8 possible knight moves
 * 4. Check if next position is safe and unvisited
 * 5. If safe, place knight and recurse
 * 6. If recursion fails, backtrack (unmark position)
 * 7. When all squares visited (movei = N*N), solution found
 * 8. Time complexity: O(8^(N²)) worst case
 * 
 * DRY RUN for N = 4 (simplified):
 * 
 * solveKTUtil(0, 0, 1, sol, xMove, yMove)
 * - Try move 0: (2,1) → safe, place move 1
 * - solveKTUtil(2, 1, 2, sol, xMove, yMove)
 *   - Try moves from (2,1), continue recursively
 * - If all moves from (2,1) fail, backtrack
 * - Try move 1: (1,2) → safe, place move 1
 * - Continue until all 16 squares visited or no solution found
 */
public class KnightsTour {
    
    static int N = 8;

    // Function to check if a position is safe to move to
    public static boolean isSafe(int x, int y, int sol[][]) {
        return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1);
    }

    // Function to print solution matrix
    public static void printSolution(int sol[][]) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                System.out.print(sol[x][y] + " ");
            }
            System.out.println();
        }
    }

    // Function to solve Knight's Tour problem
    public static boolean solveKT() {
        int sol[][] = new int[N][N];
        
        // Initialize the solution matrix with -1 (indicating unvisited cells)
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                sol[x][y] = -1;
            }
        }

        // Possible moves of knight
        int xMove[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int yMove[] = {1, 2, 2, 1, -1, -2, -2, -1};

        // Starting position of the knight
        sol[0][0] = 0;

        // Start recursive backtracking
        if (!solveKTUtil(0, 0, 1, sol, xMove, yMove)) {
            System.out.println("Solution does not exist");
            return false;
        } else {
            printSolution(sol);
            return true;
        }
    }

    // Utility function to solve Knight's Tour problem using backtracking
    public static boolean solveKTUtil(int x, int y, int movei, int sol[][], int xMove[], int yMove[]) {
        int k, next_x, next_y;

        // If all squares are visited, return true
        if (movei == N * N) {
            return true;
        }

        // Try all next moves from the current coordinate
        for (k = 0; k < 8; k++) {
            next_x = x + xMove[k];
            next_y = y + yMove[k];

            // Check if the next move is valid
            if (isSafe(next_x, next_y, sol)) {
                sol[next_x][next_y] = movei;

                // Recursively go to the next move
                if (solveKTUtil(next_x, next_y, movei + 1, sol, xMove, yMove)) {
                    return true;
                } else {
                    // Backtracking
                    sol[next_x][next_y] = -1;
                }
            }
        }

        return false;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: N = 4 (simplified for demonstration)
         * Expected result: Solution showing visitation order of all 16 squares
         * 
         * Step-by-step process:
         * 
         * solveKTUtil(0, 0, 1, sol, xMove, yMove)
         * - Try move 0: (2,1) → safe, place move 1
         * - solveKTUtil(2, 1, 2, sol, xMove, yMove)
         *   - Try moves from (2,1), continue recursively
         * - If all moves from (2,1) fail, backtrack
         * - Try move 1: (1,2) → safe, place move 1
         * - Continue until all 16 squares visited or no solution found
         * 
         * Final solution shows order 0-15 for all squares
         */
        
        // Test with smaller board for demonstration
        N = 4;
        
        System.out.println("Knight's Tour Problem");
        System.out.println("Board size: " + N + "×" + N);
        System.out.println("Starting position: (0,0)");
        System.out.println("Knight moves: 8 possible directions");
        
        if (solveKT()) {
            System.out.println("\nSolution found!");
            System.out.println("Numbers show order of visitation (0 = first, " + (N*N-1) + " = last)");
        } else {
            System.out.println("\nNo solution exists for " + N + "×" + N + " board");
        }
        
        // Show knight moves
        System.out.println("\nKnight's 8 possible moves:");
        int[] xMove = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};
        String[] moveNames = {"(2,1)", "(1,2)", "(-1,2)", "(-2,1)", "(-2,-1)", "(-1,-2)", "(1,-2)", "(2,-1)"};
        
        for (int i = 0; i < 8; i++) {
            System.out.println("Move " + i + ": " + moveNames[i]);
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        System.out.println("Test 2: N = 1 (trivial)");
        N = 1;
        solveKT();
        
        System.out.println("\nTest 3: N = 5");
        N = 5;
        solveKT();
        
        System.out.println("\nTest 4: N = 6");
        N = 6;
        solveKT();
        
        System.out.println("\nNote: N=8 (standard chessboard) is computationally intensive");
        System.out.println("For larger boards, solution may take significant time or may not exist");
    }
}
