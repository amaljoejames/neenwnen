/**
 * Question: Print all substrings of a string.
 * 
 * Topics Included:
 * - Nested loops
 * - Substring extraction
 * - String manipulation
 * - Pattern generation
 * 
 * Algorithm:
 * 1. Use outer loop for starting index (i)
 * 2. Use inner loop for ending index (j)
 * 3. Extract substring from i to j+1
 * 4. Print the substring
 * 5. Continue for all possible combinations
 * 
 * Pseudocode:
 * FUNCTION printAllSubstrings(str):
 *     n = str.length()
 *     
 *     FOR i = 0 to n-1:
 *         FOR j = i to n-1:
 *             substring = str.substring(i, j+1)
 *             PRINT substring
 * 
 * Explanation with Example:
 * Input string: "abc"
 * Dry run:
 * - i=0:
 *   - j=0: substring = "a"
 *   - j=1: substring = "ab"
 *   - j=2: substring = "abc"
 * - i=1:
 *   - j=1: substring = "b"
 *   - j=2: substring = "bc"
 * - i=2:
 *   - j=2: substring = "c"
 * Output: a, ab, abc, b, bc, c
 * 
 * Input string: "ab"
 * Dry run:
 * - i=0:
 *   - j=0: substring = "a"
 *   - j=1: substring = "ab"
 * - i=1:
 *   - j=1: substring = "b"
 * Output: a, ab, b
 * 
 * Total substrings for string of length n = n*(n+1)/2
 * For "abc": 3*4/2 = 6 substrings
 * 
 * Time Complexity: O(n³) where n is the length of the string (O(n²) substrings, each takes O(n) to print)
 * Space Complexity: O(1)
 */

public class p24PrintSubstrings {
    
    /**
     * Method to print all substrings of a string
     * @param str The input string
     */
    public static void printAllSubstrings(String str) {
        int n = str.length();
        int count = 0;
        
        System.out.println("All substrings of '" + str + "':");
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String substring = str.substring(i, j + 1);
                System.out.println(substring);
                count++;
            }
        }
        
        System.out.println("Total substrings: " + count);
    }
    
    public static void main(String[] args) {
        // Question: Print all substrings of a string
        // Topics: Nested loops, substring extraction, string manipulation, pattern generation
        // Algorithm: Use nested loops, outer for start index, inner for end index
        // Pseudocode: outer loop i → inner loop j → substring(i, j+1) → print
        // Example: "abc" → a, ab, abc, b, bc, c (6 substrings)
        // Dry run: "abc" → i=0: a,ab,abc; i=1: b,bc; i=2: c → 6 substrings
        // Time: O(n³), Space: O(1)
        
        String str1 = "abc";
        String str2 = "ab";
        
        printAllSubstrings(str1);
        System.out.println();
        printAllSubstrings(str2);
    }
}
