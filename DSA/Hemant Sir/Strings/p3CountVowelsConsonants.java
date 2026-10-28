/**
 * Question: Count the number of vowels and consonants in a string.
 * 
 * Topics Included:
 * - String traversal
 * - Character classification
 * - Conditional logic
 * - Case handling
 * 
 * Algorithm:
 * 1. Initialize counters for vowels and consonants
 * 2. Define vowels (a, e, i, o, u) both lowercase and uppercase
 * 3. Traverse each character in the string
 * 4. Check if character is a letter
 * 5. If it's a vowel, increment vowel counter
 * 6. If it's a consonant, increment consonant counter
 * 7. Return both counts
 * 
 * Pseudocode:
 * FUNCTION countVowelsConsonants(str):
 *     vowels = 0
 *     consonants = 0
 *     
 *     FOR each character ch in str:
 *         IF ch is a letter:
 *             IF ch is vowel (a, e, i, o, u, A, E, I, O, U):
 *                 vowels++
 *             ELSE:
 *                 consonants++
 *     
 *     RETURN (vowels, consonants)
 * 
 * Explanation with Example:
 * Input string: "Hello World"
 * Dry run:
 * - 'H': consonant, consonants=1
 * - 'e': vowel, vowels=1
 * - 'l': consonant, consonants=2
 * - 'l': consonant, consonants=3
 * - 'o': vowel, vowels=2
 * - ' ': skip (not a letter)
 * - 'W': consonant, consonants=4
 * - 'o': vowel, vowels=3
 * - 'r': consonant, consonants=5
 * - 'l': consonant, consonants=6
 * - 'd': consonant, consonants=7
 * Final result: Vowels=3, Consonants=7
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1)
 */

public class p3CountVowelsConsonants {
    
    /**
     * Method to count vowels and consonants in a string
     * @param str The input string
     * @return Array with [vowels, consonants]
     */
    public static int[] countVowelsConsonants(String str) {
        int vowels = 0;
        int consonants = 0;
        
        String vowelString = "aeiouAEIOU";
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // Check if character is a letter
            if (Character.isLetter(ch)) {
                if (vowelString.indexOf(ch) != -1) {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }
        
        return new int[]{vowels, consonants};
    }
    
    public static void main(String[] args) {
        // Question: Count the number of vowels and consonants in a string
        // Topics: String traversal, character classification, conditional logic, case handling
        // Algorithm: Traverse string, check each character if vowel or consonant, count them
        // Pseudocode: initialize counters → traverse string → check if vowel → increment appropriate counter
        // Example: "Hello World" → Vowels=3, Consonants=7
        // Dry run: H(con), e(vow), l(con), l(con), o(vow), W(con), o(vow), r(con), l(con), d(con) → 3,7
        // Time: O(n), Space: O(1)
        
        String input = "Hello World";
        int[] result = countVowelsConsonants(input);
        
        System.out.println("String: " + input);
        System.out.println("Vowels: " + result[0]);
        System.out.println("Consonants: " + result[1]);
    }
}
