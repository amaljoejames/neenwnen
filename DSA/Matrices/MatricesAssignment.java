/**
 * QUESTION: Matrices Assignment Problems - Implement assignment problems including
 * count specific numbers, row sum, matrix addition, and transpose operations.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Count Specific Numbers:
 * Count occurrences of a specific number in 2D array.
 * Simple nested loop traversal with counter increment.
 * 
 * 2. Row Sum:
 * Calculate sum of elements in a specific row.
 * Access row by index and sum all columns.
 * 
 * 3. Matrix Addition:
 * Add two matrices of same dimensions element-wise.
 * Result matrix has same dimensions as input matrices.
 * 
 * 4. Matrix Transpose:
 * Swap rows and columns of a matrix.
 * Result dimensions are swapped (m x n becomes n x m).
 * 
 * APPROACH USED: Element-wise Matrix Operations
 * 
 * PSEUDOCODE:
 * 
 * Count Specific Numbers:
 * function countNumber(matrix, target):
 *   count = 0
 *   for i = 0 to matrix.length-1:
 *     for j = 0 to matrix[i].length-1:
 *       if matrix[i][j] == target:
 *         count++
 *   return count
 * 
 * Row Sum:
 * function rowSum(matrix, rowIndex):
 *   sum = 0
 *   for j = 0 to matrix[rowIndex].length-1:
 *     sum += matrix[rowIndex][j]
 *   return sum
 * 
 * Matrix Addition:
 * function addMatrices(matrix1, matrix2):
 *   rows = matrix1.length, cols = matrix1[0].length
 *   result = new int[rows][cols]
 *   
 *   for i = 0 to rows-1:
 *     for j = 0 to cols-1:
 *       result[i][j] = matrix1[i][j] + matrix2[i][j]
 *   return result
 * 
 * Matrix Transpose:
 * function transpose(matrix):
 *   rows = matrix.length, cols = matrix[0].length
 *   result = new int[cols][rows]
 *   
 *   for i = 0 to rows-1:
 *     for j = 0 to cols-1:
 *       result[j][i] = matrix[i][j]
 *   return result
 * 
 * EXPLANATION OF STEPS:
 * 1. Count: O(n*m) time, O(1) space
 * 2. Row Sum: O(m) time, O(1) space for m columns
 * 3. Addition: O(n*m) time, O(n*m) space for result
 * 4. Transpose: O(n*m) time, O(n*m) space for result
 * 
 * DRY RUN for Count Specific Numbers:
 * 
 * Matrix: [[4, 7, 8], [8, 8, 7]], target = 7
 * 
 * Process:
 * i=0, j=0: 4 != 7
 * i=0, j=1: 7 == 7, count = 1
 * i=0, j=2: 8 != 7
 * i=1, j=0: 8 != 7
 * i=1, j=1: 8 != 7
 * i=1, j=2: 7 == 7, count = 2
 * 
 * Final count: 2
 * 
 * DRY RUN for Matrix Addition:
 * 
 * Matrix1: [[1, 2], [3, 4]], Matrix2: [[1, 2], [3, 4]]
 * 
 * Process:
 * result[0][0] = 1+1 = 2
 * result[0][1] = 2+2 = 4
 * result[1][0] = 3+3 = 6
 * result[1][1] = 4+4 = 8
 * 
 * Result: [[2, 4], [6, 8]]
 * 
 * DRY RUN for Transpose:
 * 
 * Original: [[2, 3, 7], [5, 6, 7]] (2x3)
 * 
 * Process:
 * result[0][0] = original[0][0] = 2
 * result[0][1] = original[1][0] = 5
 * result[1][0] = original[0][1] = 3
 * result[1][1] = original[1][1] = 6
 * result[2][0] = original[0][2] = 7
 * result[2][1] = original[1][2] = 7
 * 
 * Result: [[2, 5], [3, 6], [7, 7]] (3x2)
 */
public class MatricesAssignment {
    
    // Count specific numbers in matrix
    public static int countNumber(int[][] matrix, int target) {
        int count = 0;
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == target) {
                    count++;
                }
            }
        }
        
        return count;
    }

    // Calculate sum of specific row
    public static int rowSum(int[][] matrix, int rowIndex) {
        int sum = 0;
        
        for (int j = 0; j < matrix[rowIndex].length; j++) {
            sum += matrix[rowIndex][j];
        }
        
        return sum;
    }

    // Add two matrices
    public static int[][] addMatrices(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        
        return result;
    }

    // Transpose a matrix
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        
        return result;
    }

    // Print matrix
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all assignment problems
         * 
         * Count 7s: [[4,7,8],[8,8,7]], target=7
         * Expected: 2 (positions (0,1) and (1,2))
         * 
         * Row Sum: [[1,4,9],[11,4,3],[2,2,3]], row=1
         * Expected: 18 (11+4+3)
         * 
         * Matrix Addition: [[1,2],[3,4]] + [[1,2],[3,4]]
         * Expected: [[2,4],[6,8]]
         * 
         * Transpose: [[2,3,7],[5,6,7]] (2x3)
         * Expected: [[2,5],[3,6],[7,7]] (3x2)
         */
        
        // Test Count Specific Numbers
        int[][] countMatrix = {{4, 7, 8}, {8, 8, 7}};
        
        System.out.println("Matrices Assignment - Count Specific Numbers");
        System.out.println("Matrix: [[4,7,8],[8,8,7]], target=7");
        System.out.println("Expected: 2 occurrences");
        
        int count7 = countNumber(countMatrix, 7);
        System.out.println("Count of 7: " + count7);
        
        // Test Row Sum
        int[][] rowMatrix = {
            {1, 4, 9},
            {11, 4, 3},
            {2, 2, 3}
        };
        
        System.out.println("\nRow Sum:");
        System.out.println("Matrix: [[1,4,9],[11,4,3],[2,2,3]], row=1");
        System.out.println("Expected: 18 (11+4+3)");
        
        int sumRow1 = rowSum(rowMatrix, 1);
        System.out.println("Sum of row 1: " + sumRow1);
        
        // Test Matrix Addition
        int[][] matrixA = {{1, 2}, {3, 4}};
        int[][] matrixB = {{1, 2}, {3, 4}};
        
        System.out.println("\nMatrix Addition:");
        System.out.println("Matrix A: [[1,2],[3,4]]");
        System.out.println("Matrix B: [[1,2],[3,4]]");
        System.out.println("Expected: [[2,4],[6,8]]");
        
        int[][] sumMatrix = addMatrices(matrixA, matrixB);
        System.out.println("Sum matrix:");
        printMatrix(sumMatrix);
        
        // Test Matrix Transpose
        int[][] originalMatrix = {{2, 3, 7}, {5, 6, 7}};
        
        System.out.println("\nMatrix Transpose:");
        System.out.println("Original (2x3): [[2,3,7],[5,6,7]]");
        System.out.println("Expected (3x2): [[2,5],[3,6],[7,7]]");
        
        int[][] transposed = transpose(originalMatrix);
        System.out.println("Transposed matrix:");
        printMatrix(transposed);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Count zeros
        int[][] zeroMatrix = {{0, 1, 0}, {2, 0, 3}};
        System.out.println("Count zeros:");
        System.out.println("Matrix: [[0,1,0],[2,0,3]]");
        int zeroCount = countNumber(zeroMatrix, 0);
        System.out.println("Count of 0: " + zeroCount);
        
        // Test case 3: Single row sum
        int[][] singleRow = {{5, 10, 15}};
        System.out.println("Single row sum:");
        System.out.println("Matrix: [[5,10,15]], row=0");
        int singleSum = rowSum(singleRow, 0);
        System.out.println("Sum: " + singleSum);
        
        // Test case 4: Square matrix transpose
        int[][] squareMatrix = {{1, 2}, {3, 4}};
        System.out.println("Square matrix transpose:");
        System.out.println("Original: [[1,2],[3,4]]");
        int[][] squareTransposed = transpose(squareMatrix);
        System.out.println("Transposed:");
        printMatrix(squareTransposed);
    }
}
