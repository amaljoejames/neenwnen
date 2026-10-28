/**
 * Question: Find the lexicographically smallest and largest substring of length k.
 * 
 * Topics Included:
 * - Substring extraction
 * - String comparison
 * - Sliding window
 * - Lexicographic ordering
 * 
 * Algorithm:
 * 1. Initialize smallest and largest substrings
 * 2. Slide a window of size k through the string
 * 3. For each window, extract the substring
 * 4. Compare with current smallest and largest
 * 5. Update if necessary
 * 6. Return smallest and largest
 * 
 * Pseudocode:
 * FUNCTION findLexicographicSubstrings(str, k):
 *     IF str.length() < k:
 *         RETURN ("", "")
 *     
 *     smallest = str.substring(0, k)
 *     largest = str.substring(0, k)
 *     
 *     FOR i = 0 to str.length() - k:
 *         substring = str.substring(i, i + k)
 *         
 *         IF substring.compareTo(smallest) < 0:
 *             smallest = substring
 *         
 *         IF substring.compareTo(largest) > 0:
 *             largest = substring
 *     
 *     RETURN (smallest, largest)
 * 
 * Explanation with Example:
 * Input: str = "banana", k = 2
 * Dry run:
 * - i=0: substring = "ba", smallest = "ba", largest = "ba"
 * - i=1: substring = "an", "an" < "ba" → smallest = "an"
 * - i=2: substring = "na", "na" > "an", "na" < "ba" → smallest = "an", largest = "ba"
 * - i=3: substring = "an", "an" = "an", "an" < "ba" → no change
 * - i=4: substring = "na", "na" > "an", "na" < "ba" → no change
 * Final result: smallest = "an", largest = "ba"
 * 
 * Input: str = "geeksforgeeks", k = 3
 * Dry run:
 * - i=0: "gee", smallest="gee", largest="gee"
 * - i=1: "eek", "eek" < "gee" → smallest="eek"
 * - i=2: "eks", "eks" < "eek" → smallest="eks"
 * - i=3: "ksf", "ksf" > "eks", "ksf" > "gee" → largest="ksf"
 * - i=4: "sfo", "sfo" > "ksf" → largest="sfo"
 * - i=5: "for", "for" > "sfo" → largest="sfo" (no change)
 * - ... continue
 * Final result: smallest = "eks", largest = "sfo"
 * 
 * Time Complexity: O(n*k) where n is string length, k is substring length
 * Space Complexity: O(k) for storing substrings
 */

public class p37LexicographicSubstrings {
    
    /**
     * Method to find lexicographically smallest and largest substring of length k
     * @param str The input string
     * @param k The length of substring
     * @return Array with [smallest, largest]
     */
    public static String[] findLexicographicSubstrings(String str, int k) {
        if (str == null || str.length() < k) {
            return new String[]{"", ""};
        }
        
        String smallest = str.substring(0, k);
        String largest = str.substring(0, k);
        
        for (int i = 0; i <= str.length() - k; i++) {
            String substring = str.substring(i, i + k);
            
            if (substring.compareTo(smallest) < 0) {
                smallest = substring;
            }
            
            if (substring.compareTo(largest) > 0) {
                largest = substring;
            }
        }
        
        return new String[]{smallest, largest};
    }
    
    public static void main(String[] args) {
        // Question: Find the lexicographically smallest and largest substring of length k
        // Topics: Substring extraction, string comparison, sliding window, lexicographic ordering
        // Algorithm: Sliding window of size k, compare each substring, track min and max
        // Pseudocode: sliding window → extract substring → compare → update min/max → return
        // Example: "banana", k=2 → smallest="an", largest="ba"
        // Dry run: "banana",k=2 → ba,an,na,an,na → min=an, max=ba
        // Time: O(n*k), Space: O(k)
        
        String str1 = "banana";
        int k1 = 2;
        String[] result1 = findLexicographicSubstrings(str1, k1);
        
        System.out.println("String: " + str1 + ", k: " + k1);
        System.out.println("Smallest: " + result1[0] + ", Largest: " + result1[1]);
        
        String str2 = "geeksforgeeks";
        int k2 = 3;
        String[] result2 = findLexicographicSubstrings(str2, k2);
        
        System.out.println("\nString: " + str2 + ", k: " + k2);
        System.out.println("Smallest: " + result2[0] + ", Largest: " + result2[1]);
    }
}
