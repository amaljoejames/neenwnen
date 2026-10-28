/**
 * Question: Check if two strings are rotations of each other.
 * 
 * Topics Included:
 * - String concatenation
 * - Substring search
 * - Rotation detection
 * - String manipulation
 * 
 * Algorithm:
 * 1. Check if both strings have same length and are non-empty
 * 2. Concatenate first string with itself
 * 3. Check if second string is a substring of the concatenated string
 * 4. Return true if found, false otherwise
 * 
 * Pseudocode:
 * FUNCTION areRotations(str1, str2):
 *     IF str1.length() != str2.length() OR str1.isEmpty():
 *         RETURN false
 *     
 *     concatenated = str1 + str1
 *     
 *     IF concatenated.contains(str2):
 *         RETURN true
 *     ELSE:
 *         RETURN false
 * 
 * Explanation with Example:
 * Input: str1 = "ABCD", str2 = "CDAB"
 * Dry run:
 * - Length check: both have 4 characters ✓
 * - Concatenate str1: "ABCD" + "ABCD" = "ABCDABCD"
 * - Check if "CDAB" is in "ABCDABCD"
 * - "ABCDABCD" contains "CDAB" starting at index 2
 * Final result: true (rotations)
 * 
 * Input: str1 = "ABCD", str2 = "ACBD"
 * Dry run:
 * - Length check: both have 4 characters ✓
 * - Concatenate str1: "ABCD" + "ABCD" = "ABCDABCD"
 * - Check if "ACBD" is in "ABCDABCD"
 * - "ACBD" not found in "ABCDABCD"
 * Final result: false (not rotations)
 * 
 * Input: str1 = "waterbottle", str2 = "erbottlewat"
 * Dry run:
 * - Length check: both have 11 characters ✓
 * - Concatenate str1: "waterbottle" + "waterbottle" = "waterbottlewaterbottle"
 * - Check if "erbottlewat" is in concatenated string
 * - Found at index 2
 * Final result: true (rotations)
 * 
 * Time Complexity: O(n) where n is the length of the strings
 * Space Complexity: O(n) - for concatenated string
 */

public class p13CheckRotation {
    
    /**
     * Method to check if two strings are rotations of each other
     * @param str1 The first string
     * @param str2 The second string
     * @return true if rotations, false otherwise
     */
    public static boolean areRotations(String str1, String str2) {
        // Check if lengths are equal and strings are non-empty
        if (str1.length() != str2.length() || str1.isEmpty()) {
            return false;
        }
        
        // Concatenate str1 with itself
        String concatenated = str1 + str1;
        
        // Check if str2 is a substring of concatenated string
        return concatenated.contains(str2);
    }
    
    public static void main(String[] args) {
        // Question: Check if two strings are rotations of each other
        // Topics: String concatenation, substring search, rotation detection, string manipulation
        // Algorithm: Concatenate first string with itself, check if second is substring
        // Pseudocode: check length → concatenate str1+str1 → check if str2 in result
        // Example: "ABCD", "CDAB" → true, "ABCD", "ACBD" → false
        // Dry run: "ABCD"+"ABCD"="ABCDABCD" → "CDAB" found at index 2 → true
        // Time: O(n), Space: O(n)
        
        String str1 = "ABCD";
        String str2 = "CDAB";
        String str3 = "ABCD";
        String str4 = "ACBD";
        
        System.out.println("'" + str1 + "' and '" + str2 + "' are rotations: " + areRotations(str1, str2));
        System.out.println("'" + str3 + "' and '" + str4 + "' are rotations: " + areRotations(str3, str4));
    }
}
