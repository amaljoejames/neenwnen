import java.util.*;

/**
 * QUESTION: Find Permutations - Generate all possible permutations of a given string.
 * Permutation is an arrangement of all characters in different orders.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - String: "abc"
 * - Possible permutations:
 *   - "abc", "acb", "bac", "bca", "cab", "cba"
 * - Total permutations: n! = 3! = 6
 * 
 * APPROACH USED: Recursive Backtracking with Character Selection
 * 
 * PSEUDOCODE:
 * function findPerm(str, ans):
 *   if str.length() == 0:
 *     print(ans)
 *     return
 *   
 *   for i = 0 to str.length()-1:
 *     curr = str.charAt(i)
 *     newStr = str.substring(0, i) + str.substring(i+1)
 *     findPerm(newStr, ans + curr)
 * 
 * EXPLANATION OF STEPS:
 * 1. Base case: when string becomes empty, we have complete permutation
 * 2. For each character in current string:
 *    - Select it as next character
 *    - Create new string without this character
 *    - Recursively find permutations of remaining characters
 * 3. Each recursive call builds partial permutation
 * 4. When base case reached, print complete permutation
 * 5. Time complexity: O(n!), Space complexity: O(n)
 * 
 * DRY RUN for str = "ab":
 * 
 * Initial: findPerm("ab", "")
 * 
 * Level 1: str = "ab"
 * - i=0: curr='a', newStr="b", findPerm("b", "a")
 * - i=1: curr='b', newStr="a", findPerm("a", "b")
 * 
 * Level 2: From findPerm("b", "a")
 * - str = "b", i=0: curr='b', newStr="", findPerm("", "ab") → print "ab"
 * 
 * Level 2: From findPerm("a", "b")
 * - str = "a", i=0: curr='a', newStr="", findPerm("", "ba") → print "ba"
 * 
 * All permutations: ["ab", "ba"]
 */
public class FindPermutations {
    
    public static void findPerm(String str, String ans) {
        // Base case: when string becomes empty, we have complete permutation
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        
        // Try each character as next in permutation
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            String newStr = str.substring(0, i) + str.substring(i + 1);
            findPerm(newStr, ans + curr);
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: str = "ab"
         * Expected result: ["ab", "ba"]
         * 
         * Step-by-step process:
         * 
         * Initial: findPerm("ab", "")
         * 
         * Level 1: str = "ab"
         * - i=0: curr='a', newStr="b", findPerm("b", "a")
         * - i=1: curr='b', newStr="a", findPerm("a", "b")
         * 
         * Level 2: From findPerm("b", "a")
         * - str = "b", i=0: curr='b', newStr="", findPerm("", "ab") → print "ab"
         * 
         * Level 2: From findPerm("a", "b")
         * - str = "a", i=0: curr='a', newStr="", findPerm("", "ba") → print "ba"
         * 
         * All permutations: ["ab", "ba"]
         */
        
        String str = "abc";
        
        System.out.println("Find Permutations Problem");
        System.out.println("String: " + str);
        System.out.println("All permutations:");
        
        findPerm(str, "");
        
        // Show expected count
        int expectedCount = factorial(str.length());
        System.out.println("\nTotal permutations expected: " + str.length() + "! = " + expectedCount);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        System.out.println("Test 2: String = ''");
        findPerm("", "");
        
        System.out.println("\nTest 3: String = 'a'");
        findPerm("a", "");
        
        System.out.println("\nTest 4: String = 'ab'");
        findPerm("ab", "");
        
        System.out.println("\nTest 5: String = 'abcd'");
        findPerm("abcd", "");
        System.out.println("Expected: 4! = 24 permutations");
    }
    
    // Helper function to calculate factorial
    private static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
}
