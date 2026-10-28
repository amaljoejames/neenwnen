/**
 * Question: Find the length of a string without using built-in functions.
 * 
 * Topics Included:
 * - String traversal
 * - Character counting
 * - Loop iteration
 * - Manual length calculation
 * 
 * Algorithm:
 * 1. Initialize counter to 0
 * 2. Traverse the string character by character
 * 3. Increment counter for each character
 * 4. Stop when end of string is reached
 * 5. Return the counter value
 * 
 * Pseudocode:
 * FUNCTION stringLength(str):
 *     IF str is null:
 *         RETURN 0
 *     
 *     count = 0
 *     
 *     FOR each character ch in str:
 *         count++
 *     
 *     RETURN count
 * 
 * Explanation with Example:
 * Input string: "hello"
 * Dry run:
 * - 'h': count = 1
 * - 'e': count = 2
 * - 'l': count = 3
 * - 'l': count = 4
 * - 'o': count = 5
 * Final result: 5
 * 
 * Input string: "Hello World"
 * Dry run:
 * - H:1, e:2, l:3, l:4, o:5, (space):6, W:7, o:8, r:9, l:10, d:11
 * Final result: 11
 * 
 * Input string: ""
 * Dry run:
 * - No characters to traverse
 * Final result: 0
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1)
 */

public class p16StringLength {
    
    /**
     * Method to find the length of a string without using built-in functions
     * @param str The input string
     * @return The length of the string
     */
    public static int stringLength(String str) {
        if (str == null) {
            return 0;
        }
        
        int count = 0;
        
        for (int i = 0; i < str.length(); i++) {
            count++;
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        // Question: Find the length of a string without using built-in functions
        // Topics: String traversal, character counting, loop iteration, manual length calculation
        // Algorithm: Traverse string and count each character
        // Pseudocode: initialize counter → traverse string → increment counter → return count
        // Example: "hello" → 5, "Hello World" → 11
        // Dry run: "hello" → h:1, e:2, l:3, l:4, o:5 → 5
        // Time: O(n), Space: O(1)
        
        String str1 = "hello";
        String str2 = "Hello World";
        String str3 = "";
        
        System.out.println("Length of '" + str1 + "': " + stringLength(str1));
        System.out.println("Length of '" + str2 + "': " + stringLength(str2));
        System.out.println("Length of '" + str3 + "': " + stringLength(str3));
    }
}
