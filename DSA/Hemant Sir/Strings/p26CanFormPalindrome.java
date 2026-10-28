/**
 * Question: Check if a string can be rearranged to form a palindrome.
 * 
 * Topics Included:
 * - Hash map usage
 * - Character frequency analysis
 * - Palindrome property
 * - Odd count checking
 * 
 * Algorithm:
 * 1. Count frequency of each character in the string
 * 2. For a string to form a palindrome:
 *    - If length is even: all characters must have even frequency
 *    - If length is odd: exactly one character can have odd frequency
 * 3. Count how many characters have odd frequency
 * 4. Return true if odd count ≤ 1, false otherwise
 * 
 * Pseudocode:
 * FUNCTION canFormPalindrome(str):
 *     frequencyMap = new HashMap()
 *     
 *     FOR each character ch in str:
 *         IF frequencyMap.containsKey(ch):
 *             frequencyMap.put(ch, frequencyMap.get(ch) + 1)
 *         ELSE:
 *             frequencyMap.put(ch, 1)
 *     
 *     oddCount = 0
 *     
 *     FOR each frequency in frequencyMap.values():
 *         IF frequency is odd:
 *             oddCount++
 *     
 *     IF oddCount <= 1:
 *         RETURN true
 *     ELSE:
 *         RETURN false
 * 
 * Explanation with Example:
 * Input string: "aab"
 * Dry run:
 * - Count frequencies: a=2, b=1
 * - Check odd counts: a=2 (even), b=1 (odd)
 * - oddCount = 1
 * - oddCount ≤ 1 → true
 * Final result: true (can form "aba")
 * 
 * Input string: "aabb"
 * Dry run:
 * - Count frequencies: a=2, b=2
 * - Check odd counts: a=2 (even), b=2 (even)
 * - oddCount = 0
 * - oddCount ≤ 1 → true
 * Final result: true (can form "abba" or "baab")
 * 
 * Input string: "abc"
 * Dry run:
 * - Count frequencies: a=1, b=1, c=1
 * - Check odd counts: a=1 (odd), b=1 (odd), c=1 (odd)
 * - oddCount = 3
 * - oddCount > 1 → false
 * Final result: false (cannot form palindrome)
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(k) where k is the number of unique characters
 */

import java.util.HashMap;
import java.util.Map;

public class p26CanFormPalindrome {
    
    /**
     * Method to check if a string can be rearranged to form a palindrome
     * @param str The input string
     * @return true if can form palindrome, false otherwise
     */
    public static boolean canFormPalindrome(String str) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        
        // Count frequencies
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (frequencyMap.containsKey(ch)) {
                frequencyMap.put(ch, frequencyMap.get(ch) + 1);
            } else {
                frequencyMap.put(ch, 1);
            }
        }
        
        // Count characters with odd frequency
        int oddCount = 0;
        for (int frequency : frequencyMap.values()) {
            if (frequency % 2 != 0) {
                oddCount++;
            }
        }
        
        // Can form palindrome if at most one character has odd frequency
        return oddCount <= 1;
    }
    
    public static void main(String[] args) {
        // Question: Check if a string can be rearranged to form a palindrome
        // Topics: Hash map usage, character frequency analysis, palindrome property, odd count checking
        // Algorithm: Count frequencies, check if at most one character has odd frequency
        // Pseudocode: count frequencies → count odd frequencies → return oddCount <= 1
        // Example: "aab" → true, "abc" → false
        // Dry run: "aab" → a:2(even), b:1(odd) → oddCount=1 → true
        // Time: O(n), Space: O(k)
        
        String str1 = "aab";
        String str2 = "aabb";
        String str3 = "abc";
        
        System.out.println("'" + str1 + "' can form palindrome: " + canFormPalindrome(str1));
        System.out.println("'" + str2 + "' can form palindrome: " + canFormPalindrome(str2));
        System.out.println("'" + str3 + "' can form palindrome: " + canFormPalindrome(str3));
    }
}
