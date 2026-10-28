/**
 * Question: Check if a string is a valid shuffle of two other strings.
 * 
 * Topics Included:
 * - String validation
 * - Character counting
 * - Order preservation
 * - Length checking
 * 
 * Algorithm:
 * 1. Check if length of result equals sum of lengths of two strings
 * 2. Use three pointers: one for each string
 * 3. Traverse result string
 * 4. Match characters with either string in order
 * 5. If all characters match in order, return true
 * 6. Otherwise, return false
 * 
 * Pseudocode:
 * FUNCTION isValidShuffle(str1, str2, result):
 *     IF str1.length() + str2.length() != result.length():
 *         RETURN false
 *     
 *     i = 0  // pointer for str1
 *     j = 0  // pointer for str2
 *     k = 0  // pointer for result
 *     
 *     WHILE k < result.length():
 *         IF i < str1.length() AND str1.charAt(i) == result.charAt(k):
 *             i++
 *         ELSE IF j < str2.length() AND str2.charAt(j) == result.charAt(k):
 *             j++
 *         ELSE:
 *             RETURN false
 *         k++
 *     
 *     IF i < str1.length() OR j < str2.length():
 *         RETURN false
 *     
 *     RETURN true
 * 
 * Explanation with Example:
 * Input: str1 = "abc", str2 = "def", result = "dabecf"
 * Dry run:
 * - k=0: result[0]='d' matches str2[0]='d' → j=1, k=1
 * - k=1: result[1]='a' matches str1[0]='a' → i=1, k=2
 * - k=2: result[2]='b' matches str1[1]='b' → i=2, k=3
 * - k=3: result[3]='e' matches str2[1]='e' → j=2, k=4
 * - k=4: result[4]='c' matches str1[2]='c' → i=3, k=5
 * - k=5: result[5]='f' matches str2[2]='f' → j=3, k=6
 * - All characters matched
 * Final result: true
 * 
 * Input: str1 = "abc", str2 = "def", result = "acbdef"
 * Dry run:
 * - k=0: result[0]='a' matches str1[0]='a' → i=1, k=1
 * - k=1: result[1]='c' doesn't match str1[1]='b' or str2[0]='d' → false
 * Final result: false
 * 
 * Time Complexity: O(n) where n is the length of the result string
 * Space Complexity: O(1)
 */

public class p35ValidShuffle {
    
    /**
     * Method to check if a string is a valid shuffle of two other strings
     * @param str1 The first string
     * @param str2 The second string
     * @param result The result string to check
     * @return true if valid shuffle, false otherwise
     */
    public static boolean isValidShuffle(String str1, String str2, String result) {
        // Check if length matches
        if (str1.length() + str2.length() != result.length()) {
            return false;
        }
        
        int i = 0;  // pointer for str1
        int j = 0;  // pointer for str2
        int k = 0;  // pointer for result
        
        while (k < result.length()) {
            // Check if character matches str1
            if (i < str1.length() && str1.charAt(i) == result.charAt(k)) {
                i++;
            }
            // Check if character matches str2
            else if (j < str2.length() && str2.charAt(j) == result.charAt(k)) {
                j++;
            }
            // Character doesn't match either
            else {
                return false;
            }
            k++;
        }
        
        // Check if all characters from both strings are used
        if (i < str1.length() || j < str2.length()) {
            return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        // Question: Check if a string is a valid shuffle of two other strings
        // Topics: String validation, character counting, order preservation, length checking
        // Algorithm: Three pointers, match characters in order, validate all characters used
        // Pseudocode: check length → three pointers → match in order → validate complete → return
        // Example: "abc","def","dabecf" → true, "abc","def","acbdef" → false
        // Dry run: "abc","def","dabecf" → d(str2),a(str1),b(str1),e(str2),c(str1),f(str2) → true
        // Time: O(n), Space: O(1)
        
        String str1 = "abc";
        String str2 = "def";
        String result1 = "dabecf";
        String result2 = "acbdef";
        
        System.out.println("'" + result1 + "' is valid shuffle of '" + str1 + "' and '" + str2 + "': " + isValidShuffle(str1, str2, result1));
        System.out.println("'" + result2 + "' is valid shuffle of '" + str1 + "' and '" + str2 + "': " + isValidShuffle(str1, str2, result2));
    }
}
