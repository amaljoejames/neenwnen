
import java.util.Scanner;

public class FindTheString {
    
    /*
     * APPROACH EXPLANATION:
     * 
     * Problem: Given N×M character matrix and string s, check if s can be generated
     * by picking characters cyclically from rows (1->2->3->1->2->3...) without reuse.
     * 
     * OPTIMIZED APPROACH: Pre-counting with Fixed Arrays
     * 
     * Key Insight: Instead of processing character by character, count requirements first!
     * 
     * Step 1: Count Required Characters per Row
     * - For each character in string, determine which row it should come from
     * - Use modulo: rowIndex = charIndex % N (cyclic row selection)
     * - Store in requiredCounts[row][charIndex] where charIndex = char - 'a'
     * 
     * Step 2: Count Available Characters per Row
     * - Scan entire matrix once
     * - Store in availableCounts[row][charIndex]
     * 
     * Step 3: Validate Requirements
     * - For each row and each character: required <= available?
     * - If any requirement fails, return false immediately
     * 
     * Why This Works:
     * - Characters are independent once row assignment is fixed
     * - No ordering constraints within same row
     * - Just need enough of each character in assigned rows
     * 
     * Example: "axbaydb" with 3 rows
     * Required: Row0: a(2), b(1) | Row1: x(1), y(1) | Row2: b(1), d(1)
     * Available: Row0: a(2), b(1) | Row1: x(1), y(1) | Row2: b(1), d(1)
     * All requirements satisfied -> Yes
     * 
     * Time Complexity: O(L + N×M) - Linear in string length + matrix size
     * Space Complexity: O(N×26) = O(N) - Fixed 26 characters per row
     * 
     * This is optimal because we must read all input anyway!
     */
    
    /*
     * Problem: You are given a matrix of characters. The matrix has N rows and M columns. 
     * Given a string s, you have to tell if it is possible to generate that string from given matrix.
     * 
     * Rules for generating string from matrix are:
     * 1. You have to pick first character of string from row 1, second character from row 2 and so on. 
     *    The character of string is to be picked from row 1, that is, you can traverse the rows in a cyclic manner (row 1 comes after row N).
     * 2. If an occurrence of a character is picked from a row, you cannot pick the same occurrence again from that row.
     * 
     * Example 1:
     * Input:
     * 3 3
     * aba
     * xyz
     * bdr
     * axbaydb
     * Output: Yes
     * 
     * Dry Run for Example 1:
     * String: axbaydb
     * Matrix:
     * Row 1: a b a
     * Row 2: x y z  
     * Row 3: b d r
     * 
     * Step 1: Pick 'a' from row 1 (index 0) -> row1: a b a (use first 'a')
     * Step 2: Pick 'x' from row 2 (index 0) -> row2: x y z (use 'x')
     * Step 3: Pick 'b' from row 3 (index 0) -> row3: b d r (use 'b')
     * Step 4: Pick 'a' from row 1 (index 2) -> row1: a b a (use second 'a')
     * Step 5: Pick 'y' from row 2 (index 1) -> row2: x y z (use 'y')
     * Step 6: Pick 'd' from row 3 (index 1) -> row3: b d r (use 'd')
     * Step 7: Pick 'b' from row 1 (index 1) -> row1: a b a (use 'b')
     * 
     * All characters found successfully -> Output: Yes
     * 
     * Time Complexity: O(L + N×M) where L is length of string, N rows, M columns
     * Space Complexity: O(N×26) = O(N) for storing character counts
     */
    
    public static boolean canGenerateString(char[][] matrix, String s) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        int[][] requiredCounts = new int[n][26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int rowIndex = i % n;
            requiredCounts[rowIndex][c - 'a']++;
        }
        
        int[][] availableCounts = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = matrix[i][j];
                availableCounts[i][c - 'a']++;
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                if (requiredCounts[i][j] > availableCounts[i][j]) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int t = 0; t < T; t++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            sc.nextLine();
            
            char[][] matrix = new char[N][M];
            for (int i = 0; i < N; i++) {
                String row = sc.nextLine();
                for (int j = 0; j < M; j++) {
                    matrix[i][j] = row.charAt(j);
                }
            }
            
            String s = sc.nextLine();
            System.out.println(canGenerateString(matrix, s) ? "Yes" : "No");
        }
        
        sc.close();
    }
}