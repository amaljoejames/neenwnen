import java.util.*;

/**
 * QUESTION: Sudoku Solver - Fill a 9×9 grid with digits 1-9 such that
 * each row, column, and 3×3 subgrid contains all digits exactly once.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Partial Sudoku with some cells filled (0 represents empty)
 * - Need to fill all empty cells following Sudoku rules
 * - Rules:
 *   - Each row: digits 1-9 exactly once
 *   - Each column: digits 1-9 exactly once
 *   - Each 3×3 box: digits 1-9 exactly once
 * 
 * APPROACH USED: Backtracking with Safety Checks
 * 
 * PSEUDOCODE:
 * function solveSudoku(sudoku, row, col):
 *   if row == 9:
 *     return true  // Entire board solved
 *   
 *   // Move to next cell
 *   nextRow = row, nextCol = col + 1
 *   if col + 1 == 9:
 *     nextRow = row + 1, nextCol = 0
 *   
 *   // If cell already filled, skip
 *   if sudoku[row][col] != 0:
 *     return solveSudoku(sudoku, nextRow, nextCol)
 *   
 *   // Try digits 1-9
 *   for digit = 1 to 9:
 *     if isSafe(sudoku, row, col, digit):
 *       sudoku[row][col] = digit
 *       if solveSudoku(sudoku, nextRow, nextCol):
 *         return true
 *       sudoku[row][col] = 0  // Backtrack
 *   
 *   return false
 * 
 * function isSafe(sudoku, row, col, digit):
 *   // Check row and column
 *   for i = 0 to 8:
 *     if sudoku[row][i] == digit || sudoku[i][col] == digit:
 *       return false
 *   
 *   // Check 3×3 grid
 *   startRow = (row / 3) * 3, startCol = (col / 3) * 3
 *   for i = startRow to startRow + 2:
 *     for j = startCol to startCol + 2:
 *       if sudoku[i][j] == digit:
 *         return false
 *   
 *   return true
 * 
 * EXPLANATION OF STEPS:
 * 1. Start from (0,0), move through cells row-wise
 * 2. For empty cell, try digits 1-9
 * 3. Check if digit is safe (row, column, 3×3 box)
 * 4. If safe, place digit and recurse to next cell
 * 5. If recursion fails, backtrack and try next digit
 * 6. When all cells filled, we have solution
 * 7. Time complexity: O(9^(n²)) worst case
 * 
 * DRY RUN for simple 2×2 example:
 * 
 * sudoku = [[1,0],[0,2]]
 * solveSudoku(sudoku, 0, 0)
 * - Cell (0,0) already filled (1), move to (0,1)
 * - Cell (0,1) empty, try digit 1 → unsafe (in row)
 * - Try digit 2 → safe, place at (0,1), move to (1,0)
 * - Cell (1,0) empty, try digit 1 → unsafe (in column)
 * - Try digit 2 → unsafe (in row)
 * - Backtrack from (0,1), try digit 3 → safe, place at (0,1)
 * - Continue until solution found
 */
public class SudokuSolver {
    
    public static boolean isSafe(int sudoku[][], int row, int col, int digit) {
        // Check column and row
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == digit || sudoku[i][col] == digit) {
                return false;
            }
        }

        // Check 3x3 grid
        int startRow = (row / 3) * 3, startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (sudoku[i][j] == digit) {
                    return false;
                }
            }
        }

        return true; // Safe to place digit
    }

    // Helper function to print Sudoku board
    public static void printSudoku(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean solveSudoku(int[][] sudoku, int row, int col) {
        // Base Case: End of board
        if (row == 9) {
            return true; // Entire board is solved
        }

        // Move to next cell
        int nextRow = row, nextCol = col + 1;
        if (col + 1 == 9) { // End of current row
            nextRow = row + 1;
            nextCol = 0;
        }

        // If cell already has a value, skip it
        if (sudoku[row][col] != 0) {
            return solveSudoku(sudoku, nextRow, nextCol);
        }

        // Try placing digits from 1 to 9
        for (int digit = 1; digit <= 9; digit++) {
            if (isSafe(sudoku, row, col, digit)) { // Check if digit is safe
                sudoku[row][col] = digit; // Place digit

                // Recursively solve for the next cells
                if (solveSudoku(sudoku, nextRow, nextCol)) {
                    return true;
                }

                sudoku[row][col] = 0; // Backtrack if not solved
            }
        }

        return false; // No valid digit found for this cell
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: Simple 2×2 example for demonstration
         * sudoku = [[1,0],[0,2]]
         * Expected result: [[1,2],[2,1]]
         * 
         * Step-by-step process:
         * 
         * solveSudoku(sudoku, 0, 0)
         * - Cell (0,0) already filled (1), move to (0,1)
         * - Cell (0,1) empty, try digit 1 → unsafe (in row)
         * - Try digit 2 → safe, place at (0,1), move to (1,0)
         * - Cell (1,0) empty, try digit 1 → unsafe (in column)
         * - Try digit 2 → unsafe (in row)
         * - Backtrack and continue until solution found
         */
        
        // Example Sudoku board (0 represents empty cells)
        int[][] sudoku = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Sudoku Solver Problem");
        System.out.println("Original Sudoku:");
        printSudoku(sudoku);

        if (solveSudoku(sudoku, 0, 0)) {
            System.out.println("\nSudoku Solved Successfully:");
            printSudoku(sudoku);
        } else {
            System.out.println("\nNo Solution Exists.");
        }
        
        // Simple 2×2 example for demonstration
        System.out.println("\nSimple 2×2 Example:");
        int[][] simpleSudoku = {
            {1, 0},
            {0, 2}
        };
        
        System.out.println("Original:");
        printSudoku(simpleSudoku);
        
        if (solveSudoku(simpleSudoku, 0, 0)) {
            System.out.println("Solved:");
            printSudoku(simpleSudoku);
        }
    }
}
