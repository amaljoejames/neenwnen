/**
 * Question: Find the smallest word in a string.
 * 
 * Topics Included:
 * - String splitting
 * - Word extraction
 * - Length comparison
 * - String manipulation
 * 
 * Algorithm:
 * 1. Split the string into words based on spaces
 * 2. Initialize smallest word as first word (or empty string)
 * 3. Traverse through all words
 * 4. Compare each word's length with smallest word's length
 * 5. If current word is smaller, update smallest word
 * 6. Return the smallest word
 * 
 * Pseudocode:
 * FUNCTION findSmallestWord(str):
 *     IF str is null OR str.isEmpty():
 *         RETURN ""
 *     
 *     words = split str by spaces
 *     
 *     IF words is empty:
 *         RETURN ""
 *     
 *     smallestWord = words[0]
 *     
 *     FOR each word in words:
 *         IF word.length() < smallestWord.length():
 *             smallestWord = word
 *     
 *     RETURN smallestWord
 * 
 * Explanation with Example:
 * Input string: "The quick brown fox jumps"
 * Dry run:
 * - Split into words: ["The", "quick", "brown", "fox", "jumps"]
 * - Initialize: smallestWord = "The" (length 3)
 * - "quick": length 5 > 3, smallestWord = "The" (no change)
 * - "brown": length 5 > 3, smallestWord = "The" (no change)
 * - "fox": length 3 = 3, smallestWord = "The" (no change)
 * - "jumps": length 5 > 3, smallestWord = "The" (no change)
 * Final result: "The"
 * 
 * Input string: "I love programming in Java"
 * Dry run:
 * - Words: ["I", "love", "programming", "in", "Java"]
 * - Initialize: smallestWord = "I" (length 1)
 * - "love": 4 > 1, smallestWord = "I"
 * - "programming": 11 > 1, smallestWord = "I"
 * - "in": 2 > 1, smallestWord = "I"
 * - "Java": 4 > 1, smallestWord = "I"
 * Final result: "I"
 * 
 * Input string: "Hello world from Java"
 * Dry run:
 * - Words: ["Hello", "world", "from", "Java"]
 * - Initialize: smallestWord = "Hello" (length 5)
 * - "world": 5 = 5, smallestWord = "Hello"
 * - "from": 4 < 5, smallestWord = "from"
 * - "Java": 4 = 4, smallestWord = "from"
 * Final result: "from"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(k) where k is the number of words
 */

public class p20SmallestWord {
    
    /**
     * Method to find the smallest word in a string
     * @param str The input string
     * @return The smallest word
     */
    public static String findSmallestWord(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        
        String[] words = str.split("\\s+");
        
        if (words.length == 0) {
            return "";
        }
        
        String smallestWord = words[0];
        
        for (String word : words) {
            if (word.length() < smallestWord.length()) {
                smallestWord = word;
            }
        }
        
        return smallestWord;
    }
    
    public static void main(String[] args) {
        // Question: Find the smallest word in a string
        // Topics: String splitting, word extraction, length comparison, string manipulation
        // Algorithm: Split string into words, compare lengths, return shortest
        // Pseudocode: split string → initialize with first word → traverse → update if shorter → return
        // Example: "The quick brown fox" → "The", "I love programming" → "I"
        // Dry run: "The quick brown fox" → The(3), quick(5), brown(5), fox(3) → "The"
        // Time: O(n), Space: O(k)
        
        String str1 = "The quick brown fox jumps";
        String str2 = "I love programming in Java";
        String str3 = "Hello world from Java";
        
        System.out.println("String: " + str1 + ", Smallest word: " + findSmallestWord(str1));
        System.out.println("String: " + str2 + ", Smallest word: " + findSmallestWord(str2));
        System.out.println("String: " + str3 + ", Smallest word: " + findSmallestWord(str3));
    }
}
