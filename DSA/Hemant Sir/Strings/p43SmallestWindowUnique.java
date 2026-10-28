/**
 * Question: Find the smallest substring which contains all unique characters of the string.
 * 
 * Topics Included:
 * - Sliding window
 * - Hash set usage
 * - Character tracking
 * - Window manipulation
 * 
 * Algorithm:
 * 1. Find the count of unique characters in the string
 * 2. Use sliding window with two pointers
 * 3. Expand window to include all unique characters
 * 4. Shrink window from left while maintaining all unique characters
 * 5. Track minimum window containing all unique characters
 * 6. Return the minimum window substring
 * 
 * Pseudocode:
 * FUNCTION smallestWindowWithAllUnique(str):
 *     uniqueChars = countUniqueCharacters(str)
 *     
 *     IF uniqueChars == 0:
 *         RETURN ""
 *     
 *     charCount = new HashMap()
 *     left = 0
 *     uniqueInWindow = 0
 *     minLen = infinity
 *     minWindow = ""
 *     
 *     FOR right = 0 to str.length() - 1:
 *         char = str.charAt(right)
 *         charCount[char]++
 *         IF charCount[char] == 1:
 *             uniqueInWindow++
 *         
 *         WHILE uniqueInWindow == uniqueChars:
 *             IF right - left + 1 < minLen:
 *                 minLen = right - left + 1
 *                 minWindow = str.substring(left, right + 1)
 *             
 *             leftChar = str.charAt(left)
 *             charCount[leftChar]--
 *             IF charCount[leftChar] == 0:
 *                 uniqueInWindow--
 *             left++
 *     
 *     RETURN minWindow
 * 
 * Explanation with Example:
 * Input string: "aabcbcdbca"
 * Dry run:
 * - Unique characters: a, b, c, d (4 unique)
 * - Expand window: a(2), a(2), b(1), c(1), b(2), c(2), d(1) → uniqueInWindow=4
 * - Shrink: "aabcbcdbca" → valid, length=10
 * - Continue: "abcbcdbc" → valid, length=8
 * - Continue: "bcbcdbca" → valid, length=8
 * - Continue: "cbcdbca" → valid, length=7
 * - Continue: "bcdbca" → valid, length=6
 * - Continue: "cdbca" → valid, length=5
 * - Continue: "dbca" → valid, length=4
 * Final result: "dbca"
 * 
 * Input string: "aaaa"
 * Dry run:
 * - Unique characters: a (1 unique)
 * - Minimum window containing 'a': "a"
 * Final result: "a"
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(k) where k is the number of unique characters
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class p43SmallestWindowUnique {
    
    /**
     * Method to find smallest substring containing all unique characters
     * @param str The input string
     * @return The smallest window substring
     */
    public static String smallestWindowWithAllUnique(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        
        // Count unique characters in the string
        Set<Character> uniqueSet = new HashSet<>();
        for (char c : str.toCharArray()) {
            uniqueSet.add(c);
        }
        int uniqueChars = uniqueSet.size();
        
        if (uniqueChars == 1) {
            return String.valueOf(str.charAt(0));
        }
        
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0;
        int uniqueInWindow = 0;
        int minLen = Integer.MAX_VALUE;
        String minWindow = "";
        
        for (int right = 0; right < str.length(); right++) {
            char c = str.charAt(right);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            
            if (charCount.get(c) == 1) {
                uniqueInWindow++;
            }
            
            // Shrink window while maintaining all unique characters
            while (uniqueInWindow == uniqueChars) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minWindow = str.substring(left, right + 1);
                }
                
                char leftChar = str.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                
                if (charCount.get(leftChar) == 0) {
                    uniqueInWindow--;
                }
                left++;
            }
        }
        
        return minWindow;
    }
    
    public static void main(String[] args) {
        // Question: Find the smallest substring which contains all unique characters of the string
        // Topics: Sliding window, hash set usage, character tracking, window manipulation
        // Algorithm: Count unique chars, sliding window, expand to include all, shrink, track min
        // Pseudocode: count unique → sliding window → expand → shrink while valid → track min → return
        // Example: "aabcbcdbca" → "dbca", "aaaa" → "a"
        // Dry run: "aabcbcdbca" → unique=4 → window shrinks to "dbca"
        // Time: O(n), Space: O(k)
        
        String str1 = "aabcbcdbca";
        String str2 = "aaaa";
        String str3 = "abc";
        
        System.out.println("Smallest window in '" + str1 + "': " + smallestWindowWithAllUnique(str1));
        System.out.println("Smallest window in '" + str2 + "': " + smallestWindowWithAllUnique(str2));
        System.out.println("Smallest window in '" + str3 + "': " + smallestWindowWithAllUnique(str3));
    }
}
