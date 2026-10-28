/**
 * Question: Find the minimum window substring containing all characters of another string.
 * 
 * Topics Included:
 * - Sliding window
 * - Hash map usage
 * - Character counting
 * - Window manipulation
 * 
 * Algorithm:
 * 1. Create frequency map of characters in target string
 * 2. Use sliding window with two pointers
 * 3. Expand window to include all required characters
 * 4. Shrink window from left while maintaining validity
 * 5. Track minimum valid window
 * 6. Return the minimum window substring
 * 
 * Pseudocode:
 * FUNCTION minWindow(s, t):
 *     IF s.length() < t.length():
 *         RETURN ""
 *     
 *     targetCount = createFrequencyMap(t)
 *     windowCount = new HashMap()
 *     have = 0
 *     need = targetCount.size()
 *     
 *     left = 0
 *     minLen = infinity
 *     minWindow = ""
 *     
 *     FOR right = 0 to s.length() - 1:
 *         char = s.charAt(right)
 *         windowCount[char]++
 *         IF char in targetCount AND windowCount[char] == targetCount[char]:
 *             have++
 *         
 *         WHILE have == need:
 *             IF right - left + 1 < minLen:
 *                 minLen = right - left + 1
 *                 minWindow = s.substring(left, right + 1)
 *             
 *             leftChar = s.charAt(left)
 *             windowCount[leftChar]--
 *             IF leftChar in targetCount AND windowCount[leftChar] < targetCount[leftChar]:
 *                 have--
 *             left++
 *     
 *     RETURN minWindow
 * 
 * Explanation with Example:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Dry run:
 * - targetCount = {A:1, B:1, C:1}
 * - Expand window: A(1), D(1), O(1), B(1), E(1), C(1) → have=3 (all chars)
 * - Shrink: "ADOBEC" → valid, length=6, minWindow="ADOBEC"
 * - Continue expanding and shrinking...
 * - Final minimum window: "BANC"
 * Final result: "BANC"
 * 
 * Time Complexity: O(m + n) where m and n are lengths of the two strings
 * Space Complexity: O(k) where k is the number of unique characters
 */

import java.util.HashMap;
import java.util.Map;

public class p42MinWindowSubstring {
    
    /**
     * Method to find minimum window substring containing all characters of t
     * @param s The source string
     * @param t The target string
     * @return The minimum window substring
     */
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        
        Map<Character, Integer> targetCount = new HashMap<>();
        Map<Character, Integer> windowCount = new HashMap<>();
        
        // Build frequency map for target string
        for (char c : t.toCharArray()) {
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
        }
        
        int have = 0;
        int need = targetCount.size();
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        String minWindow = "";
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowCount.put(c, windowCount.getOrDefault(c, 0) + 1);
            
            // Check if current character count matches target
            if (targetCount.containsKey(c) && windowCount.get(c).equals(targetCount.get(c))) {
                have++;
            }
            
            // Try to shrink window while maintaining validity
            while (have == need) {
                // Update minimum window
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minWindow = s.substring(left, right + 1);
                }
                
                // Remove left character from window
                char leftChar = s.charAt(left);
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                
                if (targetCount.containsKey(leftChar) && windowCount.get(leftChar) < targetCount.get(leftChar)) {
                    have--;
                }
                left++;
            }
        }
        
        return minWindow;
    }
    
    public static void main(String[] args) {
        // Question: Find the minimum window substring containing all characters of another string
        // Topics: Sliding window, hash map usage, character counting, window manipulation
        // Algorithm: Sliding window, expand to include all chars, shrink while valid, track min
        // Pseudocode: create target map → expand window → shrink when valid → track min → return
        // Example: "ADOBECODEBANC", "ABC" → "BANC"
        // Dry run: "ADOBECODEBANC","ABC" → expand to include A,B,C → shrink → "BANC"
        // Time: O(m+n), Space: O(k)
        
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        String s2 = "a";
        String t2 = "a";
        String s3 = "a";
        String t3 = "aa";
        
        System.out.println("Min window in '" + s1 + "' containing '" + t1 + "': " + minWindow(s1, t1));
        System.out.println("Min window in '" + s2 + "' containing '" + t2 + "': " + minWindow(s2, t2));
        System.out.println("Min window in '" + s3 + "' containing '" + t3 + "': " + minWindow(s3, t3));
    }
}
