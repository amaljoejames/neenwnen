/**
 * Question: Replace all spaces in a string with '-'.
 * 
 * Topics Included:
 * - String manipulation
 * - Character replacement
 * - String building
 * - Pattern replacement
 * 
 * Algorithm:
 * 1. Initialize empty result string
 * 2. Traverse each character in the input string
 * 3. Check if character is a space
 * 4. If space, append '-' to result
 * 5. If not space, append the character itself
 * 6. Return the result string
 * 
 * Pseudocode:
 * FUNCTION replaceSpaces(str):
 *     result = ""
 *     
 *     FOR each character ch in str:
 *         IF ch == ' ':
 *             result += '-'
 *         ELSE:
 *             result += ch
 *     
 *     RETURN result
 * 
 * Explanation with Example:
 * Input string: "Hello World"
 * Dry run:
 * - 'H': not space → result = "H"
 * - 'e': not space → result = "He"
 * - 'l': not space → result = "Hel"
 * - 'l': not space → result = "Hell"
 * - 'o': not space → result = "Hello"
 * - ' ': space → result = "Hello-"
 * - 'W': not space → result = "Hello-W"
 * - 'o': not space → result = "Hello-Wo"
 * - 'r': not space → result = "Hello-Wor"
 * - 'l': not space → result = "Hello-Worl"
 * - 'd': not space → result = "Hello-World"
 * Final result: "Hello-World"
 * 
 * Input string: "Java Programming"
 * Dry run:
 * - J,a,v,a, (space)→-, P,r,o,g,r,a,m,m,i,n,g
 * Final result: "Java-Programming"
 * 
 * Input string: "  Multiple  Spaces  "
 * Dry run:
 * - (space)→-, (space)→-, M,u,l,t,i,p,l,e, (space)→-, (space)→-, S,p,a,c,e,s, (space)→-, (space)→-
 * Final result: "--Multiple--Spaces--"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) - for result string
 */

public class p18ReplaceSpaces {
    
    /**
     * Method to replace all spaces in a string with '-'
     * @param str The input string
     * @return The string with spaces replaced
     */
    public static String replaceSpaces(String str) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (ch == ' ') {
                result.append('-');
            } else {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        // Question: Replace all spaces in a string with '-'
        // Topics: String manipulation, character replacement, string building, pattern replacement
        // Algorithm: Traverse string, replace space with '-', keep other characters
        // Pseudocode: traverse → if space append '-' → else append char → return result
        // Example: "Hello World" → "Hello-World", "Java Programming" → "Java-Programming"
        // Dry run: "Hello World" → H,e,l,l,o,-,W,o,r,l,d → "Hello-World"
        // Time: O(n), Space: O(n)
        
        String str1 = "Hello World";
        String str2 = "Java Programming";
        String str3 = "  Multiple  Spaces  ";
        
        System.out.println("Original: " + str1 + ", Replaced: " + replaceSpaces(str1));
        System.out.println("Original: " + str2 + ", Replaced: " + replaceSpaces(str2));
        System.out.println("Original: " + str3 + ", Replaced: " + replaceSpaces(str3));
        
    }
}
