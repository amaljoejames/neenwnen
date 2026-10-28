/**
 * Question: Implement run-length encoding for a string.
 * 
 * Topics Included:
 * - Run-length encoding
 * - Character grouping
 * - String compression
 * - Pattern recognition
 * 
 * Algorithm:
 * 1. Initialize result string and count variable
 * 2. Traverse the string
 * 3. Count consecutive occurrences of each character
 * 4. When character changes or end of string:
 *    - Append character and count to result
 *    - Reset count for new character
 * 5. Return encoded string
 * 
 * Pseudocode:
 * FUNCTION runLengthEncode(str):
 *     IF str is null OR str.isEmpty():
 *         RETURN ""
 *     
 *     result = ""
 *     count = 1
     
 *     FOR i = 1 to str.length():
 *         IF i < str.length() AND str.charAt(i) == str.charAt(i-1):
 *             count++
 *         ELSE:
 *             result += str.charAt(i-1)
 *             IF count > 1:
 *                 result += count
 *             count = 1
     
 *     RETURN result
 * 
 * Explanation with Example:
 * Input string: "aaabbbcc"
 * Dry run:
 * - i=1: 'a' == 'a' → count=2
 * - i=2: 'a' == 'a' → count=3
 * - i=3: 'b' != 'a' → result="a3", count=1
 * - i=4: 'b' == 'b' → count=2
 * - i=5: 'b' == 'b' → count=3
 * - i=6: 'c' != 'b' → result="a3b3", count=1
 * - i=7: 'c' == 'c' → count=2
 * - End: result="a3b3c2"
 * Final result: "a3b3c2"
 * 
 * Input string: "abc"
 * Dry run:
 * - i=1: 'b' != 'a' → result="a", count=1
 * - i=2: 'c' != 'b' → result="ab", count=1
 * - End: result="abc"
 * Final result: "abc" (no count for single occurrences)
 * 
 * Input string: "wwwwaaadexxxxxx"
 * Dry run:
 * - w,w,w,w → count=4 → a different → "w4"
 * - a,a,a → count=3 → d different → "w4a3"
 * - d → count=1 → e different → "w4a3d"
 * - e → count=1 → x different → "w4a3de"
 * - x,x,x,x,x,x → count=6 → end → "w4a3dex6"
 * Final result: "w4a3dex6"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) - for result string
 */

public class p29RunLengthEncoding {
    
    /**
     * Method to implement run-length encoding
     * @param str The input string
     * @return The run-length encoded string
     */
    public static String runLengthEncode(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        int count = 1;
        
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                result.append(str.charAt(i - 1));
                if (count > 1) {
                    result.append(count);
                }
                count = 1;
            }
        }
        
        // Append last character and its count
        result.append(str.charAt(str.length() - 1));
        if (count > 1) {
            result.append(count);
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        // Question: Implement run-length encoding for a string
        // Topics: Run-length encoding, character grouping, string compression, pattern recognition
        // Algorithm: Count consecutive characters, append char and count only if count > 1
        // Pseudocode: traverse → count consecutive → append char (+count if >1) on change → return
        // Example: "aaabbbcc" → "a3b3c2", "abc" → "abc"
        // Dry run: "aaabbbcc" → a3,b3,c2 → "a3b3c2"
        // Time: O(n), Space: O(n)
        
        String str1 = "aaabbbcc";
        String str2 = "abc";
        String str3 = "wwwwaaadexxxxxx";
        
        System.out.println("Original: " + str1 + ", RLE: " + runLengthEncode(str1));
        System.out.println("Original: " + str2 + ", RLE: " + runLengthEncode(str2));
        System.out.println("Original: " + str3 + ", RLE: " + runLengthEncode(str3));
    }
}
