/**
 * Question: Find the largest word in a string.
 * 
 * Topics Included:
 * - String splitting
 * - Word extraction
 * - Length comparison
 * - String manipulation
 * 
 * Algorithm:
 * 1. Split the string into words based on spaces
 * 2. Initialize largest word as empty string
 * 3. Traverse through all words
 * 4. Compare each word's length with largest word's length
 * 5. If current word is longer, update largest word
 * 6. Return the largest word
 * 
 * Pseudocode:
 * FUNCTION findLargestWord(str):
 *     IF str is null OR str.isEmpty():
 *         RETURN ""
 *     
 *     words = split str by spaces
 *     largestWord = ""
 *     
 *     FOR each word in words:
 *         IF word.length() > largestWord.length():
 *             largestWord = word
 *     
 *     RETURN largestWord
 * 
 * Explanation with Example:
 * Input string: "The quick brown fox jumps"
 * Dry run:
 * - Split into words: ["The", "quick", "brown", "fox", "jumps"]
 * - "The": length 3, largestWord = "The"
 * - "quick": length 5 > 3, largestWord = "quick"
 * - "brown": length 5 = 5, largestWord = "quick" (no change)
 * - "fox": length 3 < 5, largestWord = "quick" (no change)
 * - "jumps": length 5 = 5, largestWord = "quick" (no change)
 * Final result: "quick"
 * 
 * Input string: "I love programming in Java"
 * Dry run:
 * - Words: ["I", "love", "programming", "in", "Java"]
 * - "I": 1, largest = "I"
 * - "love": 4 > 1, largest = "love"
 * - "programming": 11 > 4, largest = "programming"
 * - "in": 2 < 11, largest = "programming"
 * - "Java": 4 < 11, largest = "programming"
 * Final result: "programming"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(k) where k is the number of words
 */

public class p19LargestWord {
    
    /**
     * Method to find the largest word in a string
     * @param str The input string
     * @return The largest word
     */
    public static String findLargestWord(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        
        String[] words = str.split("\\s+");
        String largestWord = "";
        
        for (String word : words) {
            if (word.length() > largestWord.length()) {
                largestWord = word;
            }
        }
        
        return largestWord;
    }
    
    public static void main(String[] args) {
        // Question: Find the largest word in a string
        // Topics: String splitting, word extraction, length comparison, string manipulation
        // Algorithm: Split string into words, compare lengths, return longest
        // Pseudocode: split string → initialize largest → traverse words → update if longer → return
        // Example: "The quick brown fox" → "quick", "I love programming" → "programming"
        // Dry run: "The quick brown fox" → The(3), quick(5), brown(5), fox(3) → "quick"
        // Time: O(n), Space: O(k)
        
        String str1 = "The quick brown fox jumps";
        String str2 = "I love programming in Java";
        
        System.out.println("String: " + str1 + ", Largest word: " + findLargestWord(str1));
        System.out.println("String: " + str2 + ", Largest word: " + findLargestWord(str2));
    }
}
