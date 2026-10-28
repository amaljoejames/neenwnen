import java.util.*;

/**
 * QUESTION: N-Queens Problem - Place N queens on an N×N chessboard such that 
 * no two queens attack each other. Queens can attack horizontally, vertically, and diagonally.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - N = 4
 * - Need to place 4 queens on 4×4 board
 * - Queens attack in same row, column, or diagonal
 * - Solutions: 2 possible arrangements
 * - One solution:
 *   . Q . .
 *   . . . Q
 *   Q . . .
 *   . . Q .
 * 
 * APPROACH USED: Backtracking with Safety Check
 * 
 * PSEUDOCODE:
 * function nQueens(board, row):
 *   if row == board.length:
 *     printBoard(board)
 *     return
 *   
 *   for col = 0 to board.length-1:
 *     if isSafe(board, row, col):
 *       board[row][col] = 'Q'
 *       nQueens(board, row + 1)
 *       board[row][col] = '.'  // Backtrack
 * 
 * function isSafe(board, row, col):
 *   // Check vertically up
 *   for i = row-1 to 0:
 *     if board[i][col] == 'Q': return false
 *   
 *   // Check diagonal left up
 *   for i = row-1, j = col-1 to i>=0 && j>=0:
 *     if board[i][j] == 'Q': return false
 *   
 *   // Check diagonal right up
 *   for i = row-1, j = col+1 to i>=0 && j<board.length:
 *     if board[i][j] == 'Q': return false
 *   
 *   return true
 * 
 * EXPLANATION OF STEPS:
 * 1. Start from first row, try placing queen in each column
 * 2. For each position, check if it's safe from previous queens
 * 3. If safe, place queen and move to next row
 * 4. If not safe or if solution fails, backtrack
 * 5. When all rows processed, we have a complete solution
 * 6. Time complexity: O(n!), Space complexity: O(n²)
 * 
 * DRY RUN for N = 4:
 * 
 * Row 0: Try col 0 → place Q at (0,0)
 * Row 1: Try col 0 → unsafe, col 1 → unsafe, col 2 → place Q at (1,2)
 * Row 2: Try col 0 → unsafe, col 1 → unsafe, col 2 → unsafe, col 3 → place Q at (2,3)
 * Row 3: All columns unsafe → backtrack
 * Continue until all solutions found
 */
public class NQueens {
    
    public static boolean isSafe(char board[][], int row, int col) {
        // Check vertically up
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check diagonal left up
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check diagonal right up
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    // Recursive function to solve N-Queens
    public static void nQueens(char board[][], int row) {
        if (row == board.length) {
            printBoard(board);
            System.out.println();
            return;
        }

        // Try placing a queen in all columns of current row
        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q'; // Place queen
                nQueens(board, row + 1); // Recurse to next row
                board[row][col] = '.'; // Backtrack
            }
        }
    }

    // Function to print board
    public static void printBoard(char board[][]) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: N = 4
         * Expected result: 2 solutions
         * 
         * Step-by-step process:
         * 
         * Row 0: Try placing queen in each column
         * Row 1: For each position, check safety
         * Row 2: Continue with remaining safe positions
         * Row 3: Final row, complete solution or backtrack
         * 
         * Solutions will show all valid queen placements
         */
        
        int N = 4;
        char board[][] = new char[N][N];
        
        // Initialize board with empty cells
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = '.';
            }
        }
        
        System.out.println("N-Queens Problem");
        System.out.println("Board size: " + N + "x" + N);
        System.out.println("All solutions:");
        
        nQueens(board, 0);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: N = 1
        System.out.println("Test 2: N = 1");
        char board1[][] = new char[1][1];
        board1[0][0] = '.';
        nQueens(board1, 0);
        
        // Test case 3: N = 2 (no solution)
        System.out.println("Test 3: N = 2 (no solution expected)");
        char board2[][] = new char[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                board2[i][j] = '.';
            }
        }
        nQueens(board2, 0);
        
        System.out.println("Note: N=2 has no valid solutions");
    }
}
