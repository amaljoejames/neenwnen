/**
 * Question: Check if two strings are anagrams.
 * 
 * Topics Included:
 * - Character counting
 * - String comparison
 * - Array manipulation
 * - Anagram detection
 * 
 * Algorithm:
 * 1. Check if both strings have same length
 * 2. Create an array of size 26 for character counts
 * 3. Traverse first string and increment count for each character
 * 4. Traverse second string and decrement count for each character
 * 5. Check if all counts are zero
 * 6. Return true if all zero, false otherwise
 * 
 * Pseudocode:
 * FUNCTION areAnagrams(str1, str2):
 *     IF str1.length() != str2.length():
 *         RETURN false
 *     
 *     countArray = new int[26]
 *     
 *     // Count characters in first string
 *     FOR each character ch in str1:
 *         countArray[ch - 'a']++
 *     
 *     // Decrement count for second string
 *     FOR each character ch in str2:
 *         countArray[ch - 'a']--
 *     
 *     // Check if all counts are zero
 *     FOR each count in countArray:
 *         IF count != 0:
 *             RETURN false
 *     
 *     RETURN true
 * 
 * Explanation with Example:
 * Input: str1 = "listen", str2 = "silent"
 * Dry run:
 * - Length check: both have 6 characters ✓
 * - Count str1: l:1, i:1, s:1, t:1, e:1, n:1
 * - Decrement str2: s:1→0, i:1→0, l:1→0, e:1→0, n:1→0, t:1→0
 * - All counts are zero
 * Final result: true (anagrams)
 * 
 * Input: str1 = "hello", str2 = "world"
 * Dry run:
 * - Length check: both have 5 characters ✓
 * - Count str1: h:1, e:1, l:2, o:1
 * - Decrement str2: w:0→-1, o:1→0, r:0→-1, l:2→1, d:0→-1
 * - Counts are not all zero
 * Final result: false (not anagrams)
 * 
 * Time Complexity: O(n) where n is the length of the strings
 * Space Complexity: O(1) - fixed size array of 26
 */

public class p12CheckAnagram {
    
    /**
     * Method to check if two strings are anagrams
     * @param str1 The first string
     * @param str2 The second string
     * @return true if anagrams, false otherwise
     */
    public static boolean areAnagrams(String str1, String str2) {
        // Check if lengths are equal
        if (str1.length() != str2.length()) {
            return false;
        }
        
        int[] countArray = new int[26];
        
        // Convert to lowercase for case-insensitive comparison
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        // Count characters in first string
        for (int i = 0; i < str1.length(); i++) {
            char ch = str1.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                countArray[ch - 'a']++;
            }
        }
        
        // Decrement count for second string
        for (int i = 0; i < str2.length(); i++) {
            char ch = str2.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                countArray[ch - 'a']--;
            }
        }
        
        // Check if all counts are zero
        for (int count : countArray) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        // Question: Check if two strings are anagrams
        // Topics: Character counting, string comparison, array manipulation, anagram detection
        // Algorithm: Count characters in both strings, check if counts match
        // Pseudocode: check length → count str1 → decrement str2 → verify all zero
        // Example: "listen", "silent" → true, "hello", "world" → false
        // Dry run: "listen","silent" → l:1,i:1,s:1,t:1,e:1,n:1 → all zero → true
        // Time: O(n), Space: O(1)
        
        String str1 = "listen";
        String str2 = "silent";
        String str3 = "hello";
        String str4 = "world";
        
        System.out.println("'" + str1 + "' and '" + str2 + "' are anagrams: " + areAnagrams(str1, str2));
        System.out.println("'" + str3 + "' and '" + str4 + "' are anagrams: " + areAnagrams(str3, str4));
    }
}
