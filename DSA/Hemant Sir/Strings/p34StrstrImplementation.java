/**
 * Question: Implement strstr() (substring search without built-in functions).
 * 
 * Topics Included:
 * - String search
 * - Pattern matching
 * - Nested loops
 * - Index tracking
 * 
 * Algorithm:
 * 1. If either string is empty, return -1
 * 2. Use two nested loops:
 *    - Outer loop for position in main string
 *    - Inner loop to match pattern
 * 3. If pattern matches completely, return starting index
 * 4. If no match found, return -1
 * 
 * Pseudocode:
 * FUNCTION strstr(haystack, needle):
 *     IF needle is empty:
 *         RETURN 0
 *     IF haystack is empty OR needle.length() > haystack.length():
 *         RETURN -1
 *     
 *     n = haystack.length()
 *     m = needle.length()
 *     
 *     FOR i = 0 to n - m:
 *         match = true
 *         FOR j = 0 to m - 1:
 *             IF haystack.charAt(i + j) != needle.charAt(j):
 *                 match = false
 *                 BREAK
 *         
 *         IF match is true:
 *             RETURN i
 *     
 *     RETURN -1
 * 
 * Explanation with Example:
 * Input: haystack = "hello world", needle = "world"
 * Dry run:
 * - i=0: compare "hello w" with "world" → 'h' != 'w' → no match
 * - i=1: compare "ello wo" with "world" → 'e' != 'w' → no match
 * - i=2: compare "llo wor" with "world" → 'l' != 'w' → no match
 * - i=3: compare "lo worl" with "world" → 'l' != 'w' → no match
 * - i=4: compare "o world" with "world" → 'o' != 'w' → no match
 * - i=5: compare " world" with "world" → ' ' != 'w' → no match
 * - i=6: compare "world" with "world" → all match ✓
 * Final result: 6
 * 
 * Input: haystack = "hello", needle = "ll"
 * Dry run:
 * - i=0: "he" vs "ll" → no match
 * - i=1: "el" vs "ll" → no match
 * - i=2: "ll" vs "ll" → match ✓
 * Final result: 2
 * 
 * Time Complexity: O(n*m) where n is length of haystack, m is length of needle
 * Space Complexity: O(1)
 */

public class p34StrstrImplementation {
    
    /**
     * Method to implement strstr - find first occurrence of needle in haystack
     * @param haystack The main string to search in
     * @param needle The substring to search for
     * @return The index of first occurrence, or -1 if not found
     */
    public static int strstr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) {
            return 0;
        }
        if (haystack == null || haystack.isEmpty() || needle.length() > haystack.length()) {
            return -1;
        }
        
        int n = haystack.length();
        int m = needle.length();
        
        for (int i = 0; i <= n - m; i++) {
            boolean match = true;
            
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    match = false;
                    break;
                }
            }
            
            if (match) {
                return i;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        // Question: Implement strstr() (substring search without built-in functions)
        // Topics: String search, pattern matching, nested loops, index tracking
        // Algorithm: Nested loops, outer for position, inner for pattern matching
        // Pseudocode: outer loop for position → inner loop for match → return index or -1
        // Example: "hello world", "world" → 6, "hello", "ll" → 2
        // Dry run: "hello world","world" → i=6 matches → return 6
        // Time: O(n*m), Space: O(1)
        
        String haystack1 = "hello world";
        String needle1 = "world";
        String haystack2 = "hello";
        String needle2 = "ll";
        String haystack3 = "hello";
        String needle3 = "xyz";
        
        System.out.println("strstr('" + haystack1 + "', '" + needle1 + "'): " + strstr(haystack1, needle1));
        System.out.println("strstr('" + haystack2 + "', '" + needle2 + "'): " + strstr(haystack2, needle2));
        System.out.println("strstr('" + haystack3 + "', '" + needle3 + "'): " + strstr(haystack3, needle3));
    }
}
