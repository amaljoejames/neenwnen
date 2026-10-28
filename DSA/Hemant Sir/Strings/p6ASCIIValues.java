/**
 * Question: Find the ASCII value of each character in a string.
 * 
 * Topics Included:
 * - Character to ASCII conversion
 * - String traversal
 * - Type casting
 * - Character representation
 * 
 * Algorithm:
 * 1. Traverse each character in the string
 * 2. Convert character to its ASCII value using type casting
 * 3. Store or print character with its ASCII value
 * 4. Continue for all characters in the string
 * 
 * Pseudocode:
 * FUNCTION printASCIIValues(str):
 *     FOR each character ch in str:
 *         asciiValue = (int)ch
 *         PRINT "Character: " + ch + ", ASCII: " + asciiValue
 * 
 * Explanation with Example:
 * Input string: "ABC"
 * Dry run:
 * - 'A': ASCII value = 65
 * - 'B': ASCII value = 66
 * - 'C': ASCII value = 67
 * Output:
 *   Character: A, ASCII: 65
 *   Character: B, ASCII: 66
 *   Character: C, ASCII: 67
 * 
 * Input string: "a1@"
 * Dry run:
 * - 'a': ASCII value = 97
 * - '1': ASCII value = 49
 * - '@': ASCII value = 64
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1)
 */

public class p6ASCIIValues {
    
    /**
     * Method to print ASCII values of each character in a string
     * @param str The input string
     */
    public static void printASCIIValues(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int asciiValue = (int)ch;
            System.out.println("Character: '" + ch + "', ASCII: " + asciiValue);
        }
    }
    
    public static void main(String[] args) {
        // Question: Find the ASCII value of each character in a string
        // Topics: Character to ASCII conversion, string traversal, type casting, character representation
        // Algorithm: Traverse string, cast each char to int to get ASCII value
        // Pseudocode: for each char → cast to int → print char and ASCII value
        // Example: "ABC" → A=65, B=66, C=67
        // Dry run: 'A'→65, 'B'→66, 'C'→67
        // Time: O(n), Space: O(1)
        
        String input = "ABC";
        System.out.println("String: " + input);
        printASCIIValues(input);
        
        System.out.println("\nString: a1@");
        printASCIIValues("a1@");
    }
}
