/**
 * Question: Find the minimum number of flips to make a binary string alternate (0101 or 1010).
 * 
 * Topics Included:
 * - String manipulation
 * - Pattern matching
 * - Counting
 * - Binary string operations
 * 
 * Algorithm:
 * 1. Count flips needed to make pattern "010101..." (starting with 0)
 * 2. Count flips needed to make pattern "101010..." (starting with 1)
 * 3. Return the minimum of the two counts
 * 
 * Pseudocode:
 * FUNCTION minFlipsToAlternate(str):
 *     flips1 = 0  // For pattern starting with 0
 *     flips2 = 0  // For pattern starting with 1
     
 *     FOR i = 0 to str.length() - 1:
 *         expected1 = i % 2  // 0,1,0,1,...
 *         expected2 = 1 - i % 2  // 1,0,1,0,...
 *         actual = str.charAt(i) - '0'
 *         
 *         IF actual != expected1:
 *             flips1++
 *         
 *         IF actual != expected2:
 *             flips2++
     
 *     RETURN min(flips1, flips2)
 * 
 * Explanation with Example:
 * Input string: "001"
 * Dry run:
 * Pattern "0101" (starting with 0):
 * - i=0: expected=0, actual=0 → match, flips1=0
 * - i=1: expected=1, actual=0 → mismatch, flips1=1
 * - i=2: expected=0, actual=1 → mismatch, flips1=2
 * Total flips1 = 2
 * 
 * Pattern "1010" (starting with 1):
 * - i=0: expected=1, actual=0 → mismatch, flips2=1
 * - i=1: expected=0, actual=0 → match, flips2=1
 * - i=2: expected=1, actual=1 → match, flips2=1
 * Total flips2 = 1
 * 
 * Final result: min(2, 1) = 1
 * 
 * Input string: "000"
 * Dry run:
 * Pattern "0101": 0✓,1✗,0✗ → flips1=2
 * Pattern "1010": 1✗,0✗,1✗ → flips2=3
 * Final result: min(2, 3) = 2
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1)
 */

public class p38MinFlipsAlternate {
    
    /**
     * Method to find minimum flips to make binary string alternate
     * @param str The binary string
     * @return Minimum number of flips required
     */
    public static int minFlipsToAlternate(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        
        int flips1 = 0;  // For pattern starting with 0 (0101...)
        int flips2 = 0;  // For pattern starting with 1 (1010...)
        
        for (int i = 0; i < str.length(); i++) {
            int expected1 = i % 2;           // 0,1,0,1,...
            int expected2 = 1 - (i % 2);     // 1,0,1,0,...
            int actual = str.charAt(i) - '0';
            
            if (actual != expected1) {
                flips1++;
            }
            
            if (actual != expected2) {
                flips2++;
            }
        }
        
        return Math.min(flips1, flips2);
    }
    
    public static void main(String[] args) {
        // Question: Find the minimum number of flips to make a binary string alternate (0101 or 1010)
        // Topics: String manipulation, pattern matching, counting, binary string operations
        // Algorithm: Count flips for both patterns (0101 and 1010), return minimum
        // Pseudocode: count flips for pattern1 → count flips for pattern2 → return min
        // Example: "001" → 1, "000" → 2
        // Dry run: "001" → pattern0101:2flips, pattern1010:1flip → min=1
        // Time: O(n), Space: O(1)
        
        String str1 = "001";
        String str2 = "000";
        String str3 = "010";
        
        System.out.println("String: " + str1 + ", Min flips: " + minFlipsToAlternate(str1));
        System.out.println("String: " + str2 + ", Min flips: " + minFlipsToAlternate(str2));
        System.out.println("String: " + str3 + ", Min flips: " + minFlipsToAlternate(str3));
    }
}
