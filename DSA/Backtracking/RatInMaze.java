import java.util.*;

/**
 * QUESTION: Rat in a Maze - Find all paths for a rat to reach from 
 * source (0,0) to destination (N-1,N-1) in a maze.
 * 0 represents blocked cells, 1 represents open paths.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Maze:
 *   {1, 0, 0, 0}
 *   {1, 1, 0, 1}
 *   {0, 1, 0, 0}
 *   {1, 1, 1, 1}
 * - Start: (0,0), End: (3,3)
 * - Valid moves: Right (R), Down (D)
 * - One solution path: DDRR (Down, Down, Right, Right)
 * - Path coordinates: (0,0)→(1,0)→(2,0)→(2,1)→(3,1)→(3,2)→(3,3)
 * 
 * APPROACH USED: Backtracking with Safety Check
 * 
 * PSEUDOCODE:
 * function solveMazeUtil(maze, x, y, sol):
 *   if x == N-1 && y == N-1 && maze[x][y] == 1:
 *     sol[x][y] = 1
 *     return true
 *   
 *   if isSafe(maze, x, y) == false:
 *     return false
 *   
 *   if sol[x][y] == 1:
 *     return false
 *   
 *   sol[x][y] = 1
 *   
 *   // Try right move
 *   if solveMazeUtil(maze, x+1, y, sol):
 *     return true
 *   
 *   // Try down move
 *   if solveMazeUtil(maze, x, y+1, sol):
 *     return true
 *   
 *   sol[x][y] = 0  // Backtrack
 *   return false
 * 
 * function isSafe(maze, x, y):
 *   return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1)
 * 
 * EXPLANATION OF STEPS:
 * 1. Start from (0,0), check if it's safe
 * 2. Mark current position in solution path
 * 3. Try moving right (x+1, y)
 * 4. If right move fails, try moving down (x, y+1)
 * 5. If both fail, backtrack (unmark current position)
 * 6. When destination reached, we have a complete path
 * 7. Time complexity: O(2^(n²)) worst case
 * 
 * DRY RUN for 4×4 maze:
 * 
 * solveMazeUtil(maze, 0, 0, sol)
 * - (0,0) is safe, mark in sol
 * - Try right: solveMazeUtil(maze, 1, 0, sol)
 *   - (1,0) is safe, mark in sol
 *   - Try right: solveMazeUtil(maze, 2, 0, sol)
 *     - (2,0) is blocked (0) → return false
 *   - Try down: solveMazeUtil(maze, 1, 1, sol)
 *     - (1,1) is safe, mark in sol
 *     - Continue recursion until destination reached
 * - When all paths explored, print solution matrix
 */
public class RatInMaze {
    
    public static void printSolution(int sol[][]) {
        for (int i = 0; i < sol.length; i++) {
            for (int j = 0; j < sol.length; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Function to check if a position (x, y) is safe to move to
    public static boolean isSafe(int maze[][], int x, int y) {
        // Check if (x, y) is within maze and cell is open (1)
        return (x >= 0 && x < maze.length && y >= 0 && y < maze.length && maze[x][y] == 1);
    }

    // Function to solve maze problem using backtracking
    public static boolean solveMaze(int maze[][]) {
        int N = maze.length;
        int sol[][] = new int[N][N]; // Solution matrix initialized to 0

        // Start solving the maze from (0, 0)
        if (solveMazeUtil(maze, 0, 0, sol) == false) {
            System.out.print("Solution doesn't exist");
            return false;
        }
        
        // If solution exists, print the solution matrix
        printSolution(sol);
        return true;
    }

    // Utility function to solve the maze recursively using backtracking
    public static boolean solveMazeUtil(int maze[][], int x, int y, int sol[][]) {
        // If (x, y) is the bottom-right corner and it's open, mark it as part of solution
        if (x == maze.length - 1 && y == maze.length - 1 && maze[x][y] == 1) {
            sol[x][y] = 1;
            return true;
        }

        // Check if maze[x][y] is valid
        if (isSafe(maze, x, y) == true) {
            // If the cell is already part of the solution, return false
            if (sol[x][y] == 1) return false;

            // Mark the current cell as part of the solution
            sol[x][y] = 1;

            // Move right (x+1, y)
            if (solveMazeUtil(maze, x + 1, y, sol)) return true;

            // Move down (x, y+1)
            if (solveMazeUtil(maze, x, y + 1, sol)) return true;

            // If no movement is possible, backtrack
            sol[x][y] = 0;
            return false;
        }

        return false;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: 4×4 maze
         * Expected result: Solution path from (0,0) to (3,3)
         * 
         * Step-by-step process:
         * 
         * solveMazeUtil(maze, 0, 0, sol)
         * - (0,0) is safe, mark in sol
         * - Try right: solveMazeUtil(maze, 1, 0, sol)
         *   - (1,0) is safe, mark in sol
         *   - Try right: solveMazeUtil(maze, 2, 0, sol)
         *     - (2,0) is blocked (0) → return false
         *   - Try down: solveMazeUtil(maze, 1, 1, sol)
         *     - (1,1) is safe, mark in sol
         *     - Continue recursion until destination reached
         * - When all paths explored, print solution matrix
         */
        
        int maze[][] = { 
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {0, 1, 0, 0},
            {1, 1, 1, 1} 
        };

        System.out.println("Rat in a Maze Problem");
        System.out.println("Maze:");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("\nSolution path:");
        if (solveMaze(maze)) {
            System.out.println("Solution found!");
        } else {
            System.out.println("No solution exists.");
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Simple 2×2 maze
        int[][] maze2 = {
            {1, 1},
            {1, 1}
        };
        System.out.println("Test 2: 2×2 maze (all open)");
        solveMaze(maze2);
        
        // Test case 3: No solution
        int[][] maze3 = {
            {1, 0},
            {0, 1}
        };
        System.out.println("Test 3: 2×2 maze (no solution)");
        solveMaze(maze3);
    }
}
