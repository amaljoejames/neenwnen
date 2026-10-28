/**
 * QUESTION: Matrices Basics - Implement fundamental matrix operations including
 * search, spiral traversal, diagonal sum, and staircase search.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Matrix Search:
 * Linear search through 2D array to find target element.
 * Time complexity: O(n*m) for n x m matrix.
 * 
 * 2. Spiral Matrix Traversal:
 * Print matrix elements in spiral order starting from top-left.
 * Move right, down, left, up in layers until all elements visited.
 * 
 * 3. Diagonal Sum:
 * Calculate sum of both primary and secondary diagonals.
 * Avoid double-counting center element for odd-sized matrices.
 * 
 * 4. Staircase Search:
 * Search in sorted matrix using O(n+m) time complexity.
 * Start from top-right corner and move based on comparisons.
 * 
 * APPROACH USED: Two-dimensional Array Traversal Patterns
 * 
 * PSEUDOCODE:
 * 
 * Matrix Search:
 * function search(matrix, key):
 *   for i = 0 to n-1:
 *     for j = 0 to m-1:
 *       if matrix[i][j] == key:
 *         return (i, j)
 *   return (-1, -1)
 * 
 * Spiral Traversal:
 * function printSpiral(matrix):
 *   startRow = 0, startCol = 0
 *   endRow = n-1, endCol = m-1
 *   
 *   while startRow <= endRow && startCol <= endCol:
 *     // Print top row
 *     for j = startCol to endCol:
 *       print matrix[startRow][j]
 *     
 *     // Print right column
 *     for i = startRow+1 to endRow:
 *       print matrix[i][endCol]
 *     
 *     // Print bottom row
 *     for j = endCol-1 downto startCol:
 *       if startRow == endRow: break
 *       print matrix[endRow][j]
 *     
 *     // Print left column
 *     for i = endRow-1 downto startRow+1:
 *       if startCol == endCol: break
 *       print matrix[i][startCol]
 *     
 *     startRow++, startCol++, endRow--, endCol--
 * 
 * Diagonal Sum:
 * function diagonalSum(matrix):
 *   sum = 0
 *   for i = 0 to n-1:
 *     sum += matrix[i][i]  // Primary diagonal
 *     if i != n-1-i:
 *       sum += matrix[i][n-1-i]  // Secondary diagonal
 *   return sum
 * 
 * Staircase Search:
 * function staircaseSearch(matrix, key):
 *   row = 0, col = m-1
 *   while row < n && col >= 0:
 *     if matrix[row][col] == key:
 *       return (row, col)
 *     else if key < matrix[row][col]:
 *       col--
 *     else:
 *       row++
 *   return (-1, -1)
 * 
 * EXPLANATION OF STEPS:
 * 1. Matrix Search: Simple nested loop traversal
 * 2. Spiral: Four-direction traversal with boundary updates
 * 3. Diagonal: Single loop for both diagonals with duplicate check
 * 4. Staircase: Exploit sorted property for O(n+m) search
 * 
 * DRY RUN for Spiral Traversal:
 * 
 * Matrix: [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]
 * 
 * Process:
 * Layer 1: 1,2,3,4,8,12,16,15,14,13,9,5
 * Layer 2: 6,7,11,10
 * 
 * Output: 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
 * 
 * DRY RUN for Diagonal Sum:
 * 
 * Matrix: [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]
 * 
 * Process:
 * Primary diagonal: 1+6+11+16 = 34
 * Secondary diagonal: 4+7+10+13 = 34
 * Total sum = 34+34 = 68
 * 
 * No overlap (even size matrix)
 */
public class MatricesBasics {
    
    // Matrix Search
    public static boolean search(int matrix[][], int key) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == key) {
                    System.out.println(key + " found at index (" + i + ", " + j + ")");
                    return true;
                }
            }
        }
        
        System.out.println(key + " not found in matrix");
        return false;
    }

    // Spiral Matrix Traversal
    public static void printSpiral(int matrix[][]) {
        int startRow = 0;
        int startCol = 0;
        int endRow = matrix.length - 1;
        int endCol = matrix[0].length - 1;
        
        while (startRow <= endRow && startCol <= endCol) {
            // Top boundary
            for (int j = startCol; j <= endCol; j++) {
                System.out.print(matrix[startRow][j] + " ");
            }
            
            // Right boundary
            for (int i = startRow + 1; i <= endRow; i++) {
                System.out.print(matrix[i][endCol] + " ");
            }
            
            // Bottom boundary
            for (int j = endCol - 1; j >= startCol; j--) {
                if (startRow == endRow) {
                    break;
                }
                System.out.print(matrix[endRow][j] + " ");
            }
            
            // Left boundary
            for (int i = endRow - 1; i >= startRow + 1; i--) {
                if (startCol == endCol) {
                    break;
                }
                System.out.print(matrix[i][startCol] + " ");
            }
            
            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }
        System.out.println();
    }

    // Diagonal Sum
    public static void diagonalSum(int matrix[][]) {
        int sum = 0;
        int n = matrix.length;
        
        for (int i = 0; i < n; i++) {
            // Primary diagonal
            sum += matrix[i][i];
            
            // Secondary diagonal
            if (i != n - 1 - i) {
                sum += matrix[i][n - 1 - i];
            }
        }
        
        System.out.println("Diagonal sum: " + sum);
    }

    // Staircase Search
    public static boolean staircaseSearch(int matrix[][], int key) {
        int row = 0;
        int col = matrix[0].length - 1;
        
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == key) {
                System.out.println("Found key " + key + " at (" + row + ", " + col + ")");
                return true;
            } else if (key < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }
        
        System.out.println("Key " + key + " not found");
        return false;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all matrix operations
         * 
         * Matrix Search: [[1,2,4],[1,4,3],[2,1,6]], key=4
         * Expected: Found at (0,2) and (1,1)
         * 
         * Spiral: 4x4 matrix 1-16
         * Expected: 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
         * 
         * Diagonal Sum: 4x4 matrix 1-16
         * Expected: 68 (34+34)
         * 
         * Staircase: [[10,20,30,40],[50,60,70,80],[90,100,110,120],[130,140,150,160]], key=50
         * Expected: Found at (1,0)
         */
        
        // Test Matrix Search
        int[][] searchMatrix = {{1, 2, 4}, {1, 4, 3}, {2, 1, 6}};
        
        System.out.println("Matrices Basics - Matrix Search");
        System.out.println("Matrix: [[1,2,4],[1,4,3],[2,1,6]], key=4");
        System.out.println("Expected: Found at (0,2) and (1,1)");
        
        search(searchMatrix, 4);
        
        // Test Spiral Traversal
        int[][] spiralMatrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        
        System.out.println("\nSpiral Traversal:");
        System.out.println("4x4 matrix with numbers 1-16");
        System.out.println("Expected: 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10");
        System.out.print("Spiral order: ");
        printSpiral(spiralMatrix);
        
        // Test Diagonal Sum
        System.out.println("\nDiagonal Sum:");
        System.out.println("Same 4x4 matrix");
        System.out.println("Expected: 68 (primary: 1+6+11+16=34, secondary: 4+7+10+13=34)");
        
        diagonalSum(spiralMatrix);
        
        // Test Staircase Search
        int[][] staircaseMatrix = {
            {10, 20, 30, 40},
            {50, 60, 70, 80},
            {90, 100, 110, 120},
            {130, 140, 150, 160}
        };
        
        System.out.println("\nStaircase Search:");
        System.out.println("4x4 sorted matrix, key=50");
        System.out.println("Expected: Found at (1,0)");
        
        staircaseSearch(staircaseMatrix, 50);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single element matrix
        int[][] single = {{42}};
        System.out.println("Single element matrix:");
        System.out.println("Matrix: [[42]], key=42");
        search(single, 42);
        
        // Test case 3: Odd-sized diagonal sum
        int[][] oddMatrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Odd-sized diagonal sum:");
        System.out.println("3x3 matrix 1-9");
        System.out.println("Expected: 25 (1+5+9+3+7, center 5 counted once)");
        diagonalSum(oddMatrix);
    }
}
