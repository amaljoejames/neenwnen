/**
 * Question: Implement string compression (e.g., "aaabb" → "a3b2").
 * 
 * Topics Included:
 * - String compression
 * - Character counting
 * - Run-length encoding
 * - String building
 * 
 * Algorithm:
 * 1. Initialize result string and count variable
 * 2. Traverse the string
 * 3. Count consecutive occurrences of each character
 * 4. When character changes, append character and count to result
 * 5. Reset count for new character
 * 6. Return compressed string
 * 
 * Pseudocode:
 * FUNCTION compressString(str):
 *     IF str is null OR str.isEmpty():
 *         RETURN ""
 *     
 *     result = ""
 *     count = 1
 *     
 *     FOR i = 1 to str.length():
 *         IF i < str.length() AND str.charAt(i) == str.charAt(i-1):
 *             count++
 *         ELSE:
 *             result += str.charAt(i-1) + count
 *             count = 1
 *     
 *     RETURN result
 * 
 * Explanation with Example:
 * Input string: "aaabb"
 * Dry run:
 * - i=1: 'a' == 'a' → count=2
 * - i=2: 'a' == 'a' → count=3
 * - i=3: 'b' != 'a' → result="a3", count=1
 * - i=4: 'b' == 'b' → count=2
 * - End: result="a3b2"
 * Final result: "a3b2"
 * 
 * Input string: "abcd"
 * Dry run:
 * - i=1: 'b' != 'a' → result="a1", count=1
 * - i=2: 'c' != 'b' → result="a1b1", count=1
 * - i=3: 'd' != 'c' → result="a1b1c1", count=1
 * - End: result="a1b1d1"
 * Final result: "a1b1c1d1"
 * 
 * Input string: "aabcccccaaa"
 * Dry run:
 * - a,a → count=2 → b different → "a2"
 * - b → count=1 → c different → "a2b1"
 * - c,c,c,c,c → count=5 → a different → "a2b1c5"
 * - a,a,a → count=3 → end → "a2b1c5a3"
 * Final result: "a2b1c5a3"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) - for result string
 */

public class p28StringCompression {
    
    /**
     * Method to compress a string using run-length encoding
     * @param str The input string
     * @return The compressed string
     */
    public static String compressString(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        int count = 1;
        
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                result.append(str.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        
        // Append last character and its count
        result.append(str.charAt(str.length() - 1)).append(count);
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        // Question: Implement string compression (e.g., "aaabb" → "a3b2")
        // Topics: String compression, character counting, run-length encoding, string building
        // Algorithm: Count consecutive characters, append char+count when character changes
        // Pseudocode: traverse → count consecutive → append char+count on change → return
        // Example: "aaabb" → "a3b2", "abcd" → "a1b1c1d1"
        // Dry run: "aaabb" → a,a(count2),a(count3),b(diff)→"a3",b,count2→end→"a3b2"
        // Time: O(n), Space: O(n)
        
        String str1 = "aaabb";
        String str2 = "abcd";
        String str3 = "aabcccccaaa";
        
        System.out.println("Original: " + str1 + ", Compressed: " + compressString(str1));
        System.out.println("Original: " + str2 + ", Compressed: " + compressString(str2));
        System.out.println("Original: " + str3 + ", Compressed: " + compressString(str3));
    }
}
