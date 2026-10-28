import java.util.*;

/**
 * QUESTION: Find Subsets - Generate all possible subsets of a given string.
 * Each character can either be included or excluded from a subset.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - String: "abc"
 * - Possible subsets:
 *   - "" (empty set)
 *   - "a", "b", "c" (single characters)
 *   - "ab", "ac", "bc" (two characters)
 *   - "abc" (all characters)
 * - Total subsets: 2^n = 2^3 = 8
 * 
 * APPROACH USED: Recursive Backtracking
 * 
 * PSEUDOCODE:
 * function findSubsets(str, i, ans):
 *   if i == str.length():
 *     if ans.length() == 0:
 *       print("null")
 *     else:
 *       print(ans)
 *     return
 *   
 *   // Exclude current character
 *   findSubsets(str, i+1, ans)
 *   
 *   // Include current character
 *   findSubsets(str, i+1, ans + str.charAt(i))
 * 
 * EXPLANATION OF STEPS:
 * 1. Base case: when we've processed all characters
 * 2. For each character, we have two choices:
 *    - Exclude it from current subset
 *    - Include it in current subset
 * 3. Recursively process remaining characters
 * 4. Each recursive call builds partial solution
 * 5. When base case reached, print complete subset
 * 6. Time complexity: O(2^n), Space complexity: O(n)
 * 
 * DRY RUN for str = "ab":
 * 
 * Initial: findSubsets("ab", 0, "")
 * 
 * Level 0 (i=0, char='a'):
 * - Exclude 'a': findSubsets("ab", 1, "")
 * - Include 'a': findSubsets("ab", 1, "a")
 * 
 * Level 1 (i=1, char='b'):
 * From "": 
 * - Exclude 'b': findSubsets("ab", 2, "") → print "null"
 * - Include 'b': findSubsets("ab", 2, "b") → print "b"
 * From "a":
 * - Exclude 'b': findSubsets("ab", 2, "a") → print "a"
 * - Include 'b': findSubsets("ab", 2, "ab") → print "ab"
 * 
 * All subsets: ["", "b", "a", "ab"]
 */
public class FindSubsets {
    
    public static void findSubsets(String str, int i, String ans) {
        // Base case: when we've processed all characters
        if (i == str.length()) {
            if (ans.length() == 0) {
                System.out.println("null");
            } else {
                System.out.println(ans);
            }
            return;
        }
        
        // Exclude current character
        findSubsets(str, i + 1, ans);
        
        // Include current character
        findSubsets(str, i + 1, ans + str.charAt(i));
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: str = "ab"
         * Expected result: ["", "b", "a", "ab"]
         * 
         * Step-by-step process:
         * 
         * Initial: findSubsets("ab", 0, "")
         * 
         * Level 0 (i=0, char='a'):
         * - Exclude 'a': findSubsets("ab", 1, "")
         * - Include 'a': findSubsets("ab", 1, "a")
         * 
         * Level 1 (i=1, char='b'):
         * From "":
         * - Exclude 'b': findSubsets("ab", 2, "") → print "null"
         * - Include 'b': findSubsets("ab", 2, "b") → print "b"
         * From "a":
         * - Exclude 'b': findSubsets("ab", 2, "a") → print "a"
         * - Include 'b': findSubsets("ab", 2, "ab") → print "ab"
         * 
         * All subsets: ["", "b", "a", "ab"]
         */
        
        String str = "abc";
        
        System.out.println("Find Subsets Problem");
        System.out.println("String: " + str);
        System.out.println("All subsets:");
        
        findSubsets(str, 0, "");
        
        // Show expected count
        int expectedCount = (int) Math.pow(2, str.length());
        System.out.println("\nTotal subsets expected: 2^" + str.length() + " = " + expectedCount);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        System.out.println("Test 2: String = ''");
        findSubsets("", 0, "");
        
        System.out.println("\nTest 3: String = 'a'");
        findSubsets("a", 0, "");
        
        System.out.println("\nTest 4: String = 'ab'");
        findSubsets("ab", 0, "");
        
        System.out.println("\nTest 5: String = 'abcd'");
        findSubsets("abcd", 0, "");
        System.out.println("Expected: 2^4 = 16 subsets");
    }
}
