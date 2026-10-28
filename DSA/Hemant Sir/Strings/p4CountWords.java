/**
 * Question: Count the number of words in a string.
 * 
 * Topics Included:
 * - String traversal
 * - Space handling
 * - Word boundary detection
 * - Edge case handling
 * 
 * Algorithm:
 * 1. Initialize word counter to 0
 * 2. Initialize flag to track if currently in a word
 * 3. Traverse each character in the string
 * 4. If character is not a space and not in word, start new word
 * 5. If character is space, mark as not in word
 * 6. Return word counter
 * 
 * Pseudocode:
 * FUNCTION countWords(str):
 *     IF str is null or empty:
 *         RETURN 0
 *     
 *     wordCount = 0
 *     inWord = false
 *     
 *     FOR each character ch in str:
 *         IF ch is not space:
 *             IF inWord is false:
 *                 wordCount++
 *                 inWord = true
 *         ELSE:
 *             inWord = false
 *     
 *     RETURN wordCount
 * 
 * Explanation with Example:
 * Input string: "Hello World Java"
 * Dry run:
 * - 'H': not space, not in word → wordCount=1, inWord=true
 * - 'e', 'l', 'l', 'o': not space, in word → no change
 * - ' ': space → inWord=false
 * - 'W': not space, not in word → wordCount=2, inWord=true
 * - 'o', 'r', 'l', 'd': not space, in word → no change
 * - ' ': space → inWord=false
 * - 'J': not space, not in word → wordCount=3, inWord=true
 * - 'a', 'v', 'a': not space, in word → no change
 * Final result: 3 words
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1)
 */

public class p4CountWords {
    
    /**
     * Method to count the number of words in a string
     * @param str The input string
     * @return The number of words
     */
    public static int countWords(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        
        int wordCount = 0;
        boolean inWord = false;
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (ch != ' ') {
                if (!inWord) {
                    wordCount++;
                    inWord = true;
                }
            } else {
                inWord = false;
            }
        }
        
        return wordCount;
    }
    
    public static void main(String[] args) {
        // Question: Count the number of words in a string
        // Topics: String traversal, space handling, word boundary detection, edge case handling
        // Algorithm: Traverse string, detect word boundaries using space characters
        // Pseudocode: initialize flag → traverse → if not space and not in word, count++ → if space, reset flag
        // Example: "Hello World Java" → 3
        // Dry run: H(word1), e,l,l,o, (space), W(word2), o,r,l,d, (space), J(word3), a,v,a → 3
        // Time: O(n), Space: O(1)
        
        String input = "Hello World Java";
        int wordCount = countWords(input);
        
        System.out.println("String: " + input);
        System.out.println("Number of words: " + wordCount);
    }
}
