/**
 * Question: Reverse each word of a string individually.
 * 
 * Topics Included:
 * - String splitting
 * - Word reversal
 * - String building
 * - Word-level manipulation
 * 
 * Algorithm:
 * 1. Split the string into words based on spaces
 * 2. Initialize empty result string
 * 3. For each word:
 *    a. Reverse the word using two-pointer technique
 *    b. Append reversed word to result
 *    c. Add space between words
 * 4. Return the result string
 * 
 * Pseudocode:
 * FUNCTION reverseEachWord(str):
 *     words = split str by spaces
 *     result = ""
 *     
 *     FOR each word in words:
 *         reversedWord = reverse(word)
 *         result += reversedWord + " "
 *     
 *     RETURN result.trim()
 * 
 * FUNCTION reverse(word):
 *     charArray = word.toCharArray()
 *     left = 0
 *     right = charArray.length - 1
 *     
 *     WHILE left < right:
 *         swap charArray[left] and charArray[right]
 *         left++
 *         right--
 *     
 *     RETURN new String(charArray)
 * 
 * Explanation with Example:
 * Input string: "Hello World"
 * Dry run:
 * - Split into words: ["Hello", "World"]
 * - Reverse "Hello": "olleH"
 * - Reverse "World": "dlroW"
 * - Combine: "olleH dlroW"
 * Final result: "olleH dlroW"
 * 
 * Input string: "Java Programming"
 * Dry run:
 * - Split into words: ["Java", "Programming"]
 * - Reverse "Java": "avaJ"
 * - Reverse "Programming": "gnimmargorP"
 * - Combine: "avaJ gnimmargorP"
 * Final result: "avaJ gnimmargorP"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) - for result string
 */

public class p22ReverseEachWord {
    
    /**
     * Method to reverse a single word
     * @param word The word to reverse
     * @return The reversed word
     */
    public static String reverseWord(String word) {
        char[] charArray = word.toCharArray();
        int left = 0;
        int right = charArray.length - 1;
        
        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }
        
        return new String(charArray);
    }
    
    /**
     * Method to reverse each word in a string
     * @param str The input string
     * @return The string with each word reversed
     */
    public static String reverseEachWord(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        
        String[] words = str.split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (String word : words) {
            result.append(reverseWord(word)).append(" ");
        }
        
        return result.toString().trim();
    }
    
    public static void main(String[] args) {
        // Question: Reverse each word of a string individually
        // Topics: String splitting, word reversal, string building, word-level manipulation
        // Algorithm: Split string, reverse each word, combine back
        // Pseudocode: split → reverse each word → combine with spaces → return
        // Example: "Hello World" → "olleH dlroW", "Java Programming" → "avaJ gnimmargorP"
        // Dry run: "Hello World" → Hello→olleH, World→dlroW → "olleH dlroW"
        // Time: O(n), Space: O(n)
        
        String str1 = "Hello World";
        String str2 = "Java Programming";
        
        System.out.println("Original: " + str1 + ", Reversed words: " + reverseEachWord(str1));
        System.out.println("Original: " + str2 + ", Reversed words: " + reverseEachWord(str2));
    }
}
