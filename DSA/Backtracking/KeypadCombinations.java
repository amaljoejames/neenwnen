import java.util.*;

/**
 * QUESTION: Keypad Combinations - Given a string of digits (2-9), print all 
 * possible letter combinations that the number could represent like telephone keypad.
 * 1 does not map to any letters.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Digits: "23"
 * - Mapping: 2→"abc", 3→"def"
 * - Combinations: "ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"
 * - Total: 3×3 = 9 combinations
 * 
 * APPROACH USED: BFS/DFS with Recursive Backtracking
 * 
 * PSEUDOCODE:
 * function letterCombinations(D):
 *   len = D.length()
 *   if len == 0:
 *     print("")
 *     return
 *   
 *   bfs(0, len, new StringBuilder(), D)
 * 
 * function bfs(pos, len, sb, D):
 *   if pos == len:
 *     print(sb.toString())
 *     return
 *   
 *   letters = L[Character.getNumericValue(D.charAt(pos))]
 *   for i = 0 to letters.length-1:
 *     bfs(pos + 1, len, new StringBuilder(sb).append(letters[i]), D)
 * 
 * EXPLANATION OF STEPS:
 * 1. Handle empty string case
 * 2. For each digit position, get corresponding letters
 * 3. Try each letter for current position
 * 4. Recursively process remaining positions
 * 5. Build combination using StringBuilder
 * 6. When all positions processed, print complete combination
 * 7. Time complexity: O(4^n) worst case (digit 7,9 have 4 letters)
 * 
 * DRY RUN for digits = "23":
 * 
 * letterCombinations("23")
 * - bfs(0, 2, "", "23")
 *   - letters = ['a','b','c'] (for digit '2')
 *   - i=0: bfs(1, 2, "a", "23")
 *     - letters = ['d','e','f'] (for digit '3')
 *     - i=0: bfs(2, 2, "ad", "23") → print "ad"
 *     - i=1: bfs(2, 2, "ae", "23") → print "ae"
 *     - i=2: bfs(2, 2, "af", "23") → print "af"
 *   - i=1: bfs(1, 2, "b", "23") → similar process for "bd","be","bf"
 *   - i=2: bfs(1, 2, "c", "23") → similar process for "cd","ce","cf"
 * 
 * All combinations: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 */
public class KeypadCombinations {
    
    // Mapping of digits to letters (like a telephone keypad)
    final static char[][] L = {
        {},                    // 0
        {},                    // 1
        {'a', 'b', 'c'},       // 2
        {'d', 'e', 'f'},       // 3
        {'g', 'h', 'i'},       // 4
        {'j', 'k', 'l'},       // 5
        {'m', 'n', 'o'},       // 6
        {'p', 'q', 'r', 's'}, // 7
        {'t', 'u', 'v'},       // 8
        {'w', 'x', 'y', 'z'}  // 9
    };

    // Function to print all letter combinations for a given digit string
    public static void letterCombinations(String D) {
        int len = D.length();
        if (len == 0) {
            System.out.println("");
            return;
        }
        bfs(0, len, new StringBuilder(), D);
    }

    // Helper function for breadth-first search (bfs) to find combinations
    public static void bfs(int pos, int len, StringBuilder sb, String D) {
        if (pos == len) {
            System.out.println(sb.toString());
        } else {
            char[] letters = L[Character.getNumericValue(D.charAt(pos))];
            for (int i = 0; i < letters.length; i++) {
                bfs(pos + 1, len, new StringBuilder(sb).append(letters[i]), D);
            }
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: digits = "23"
         * Expected result: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
         * 
         * Step-by-step process:
         * 
         * letterCombinations("23")
         * - bfs(0, 2, "", "23")
         *   - letters = ['a','b','c'] (for digit '2')
         *   - i=0: bfs(1, 2, "a", "23")
         *     - letters = ['d','e','f'] (for digit '3')
         *     - i=0: bfs(2, 2, "ad", "23") → print "ad"
         *     - i=1: bfs(2, 2, "ae", "23") → print "ae"
         *     - i=2: bfs(2, 2, "af", "23") → print "af"
         *   - Continue for 'b' and 'c'...
         * 
         * All combinations printed
         */
        
        String digits = "23";
        
        System.out.println("Keypad Combinations Problem");
        System.out.println("Digits: " + digits);
        System.out.println("All letter combinations:");
        
        letterCombinations(digits);
        
        // Show expected count
        System.out.println("\nExpected combinations:");
        for (int i = 0; i < digits.length(); i++) {
            int digit = Character.getNumericValue(digits.charAt(i));
            int letterCount = L[digit].length;
            System.out.println("Digit " + digit + " has " + letterCount + " letters: " + Arrays.toString(L[digit]));
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        System.out.println("Test 2: Digits = ''");
        letterCombinations("");
        
        System.out.println("\nTest 3: Digits = '2'");
        letterCombinations("2");
        
        System.out.println("\nTest 4: Digits = '7'");
        letterCombinations("7");
        System.out.println("Expected: 4 letters (p,q,r,s) → 4 combinations");
        
        System.out.println("\nTest 5: Digits = '79'");
        letterCombinations("79");
        System.out.println("Expected: 4×4 = 16 combinations");
    }
}
