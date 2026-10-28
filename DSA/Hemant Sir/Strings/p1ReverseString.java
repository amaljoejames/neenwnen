/**
 * Question: Reverse a string without using built-in functions.
 * 
 * Topics Included:
 * - String manipulation
 * - Array operations
 * - Two-pointer technique
 * - In-place reversal
 * 
 * Algorithm:
 * 1. Convert string to character array
 * 2. Initialize two pointers (start and end)
 * 3. Swap characters from both ends
 * 4. Move pointers towards center
 * 5. Convert array back to string
 * 
 * Pseudocode:
 * FUNCTION reverseString(str):
 *     charArray = str.toCharArray()
 *     left = 0
 *     right = charArray.length - 1
 *     
 *     WHILE left < right:
 *         temp = charArray[left]
 *         charArray[left] = charArray[right]
 *         charArray[right] = temp
 *         left++
 *         right--
 *     
 *     RETURN new String(charArray)
 * 
 * Explanation with Example:
 * Input string: "hello"
 * Dry run:
 * - Convert to char array: ['h', 'e', 'l', 'l', 'o']
 * - Initial: left=0, right=4
 * - Swap 0 and 4: ['o', 'e', 'l', 'l', 'h'], left=1, right=3
 * - Swap 1 and 3: ['o', 'l', 'l', 'e', 'h'], left=2, right=2
 * - left >= right, stop
 * Final result: "olleh"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) - for character array
 */

public class p1ReverseString {
    
    /**
     * Method to reverse a string without using built-in functions
     * @param str The input string
     * @return The reversed string
     */
    public static String reverseString(String str) {
        char[] charArray = str.toCharArray();
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
    
    public static void main(String[] args) {
        // Question: Reverse a string without using built-in functions
        // Topics: String manipulation, array operations, two-pointer technique, in-place reversal
        // Algorithm: Convert to char array, swap from both ends, convert back to string
        // Pseudocode: toCharArray → two-pointer swap → convert back to String
        // Example: "hello" → "olleh"
        // Dry run: ['h','e','l','l','o'] → swap(0,4) → ['o','e','l','l','h'] → swap(1,3) → ['o','l','l','e','h'] → "olleh"
        // Time: O(n), Space: O(n)
        
        String input = "hello";
        String reversed = reverseString(input);
        
        System.out.println("Original string: " + input);
        System.out.println("Reversed string: " + reversed);
    }
}
