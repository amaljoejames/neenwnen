/**
 * QUESTION: Recursion Assignment Problems - Implement interview problems including
 * all occurrences, number to words, string length, and palindromic substrings.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. All Occurrences of Key:
 * Find all indices where key appears in array using recursion.
 * Traverse array recursively and print indices when key is found.
 * 
 * 2. Number to Words:
 * Convert integer to English words using recursion.
 * Process digits from right to left recursively.
 * 
 * 3. String Length:
 * Find length of string using recursion without using length() method.
 * Base case: empty string has length 0.
 * 
 * 4. Palindromic Substrings:
 * Count substrings that start and end with same character.
 * Use recursive relation to count valid substrings.
 * 
 * APPROACH USED: Recursive String and Array Processing
 * 
 * PSEUDOCODE:
 * 
 * All Occurrences:
 * function allOccurrences(arr, key, i):
 *   if i == arr.length: return
 *   if arr[i] == key: print i
 *   allOccurrences(arr, key, i + 1)
 * 
 * Number to Words:
 * function printDigits(number):
 *   if number == 0: return
 *   lastDigit = number % 10
 *   printDigits(number / 10)
 *   print words[lastDigit]
 * 
 * String Length:
 * function length(str):
 *   if str.length == 0: return 0
 *   return length(str.substring(1)) + 1
 * 
 * Palindromic Substrings:
 * function countSubstrs(str, i, j, n):
 *   if n == 1: return 1
 *   if n <= 0: return 0
 *   
 *   res = countSubstrs(str, i+1, j, n-1) + 
 *         countSubstrs(str, i, j-1, n-1) - 
 *         countSubstrs(str, i+1, j-1, n-2)
 *   
 *   if str[i] == str[j]: res++
 *   return res
 * 
 * EXPLANATION OF STEPS:
 * 1. All Occurrences: Linear traversal with recursive calls
 * 2. Number to Words: Process digits recursively from most significant
 * 3. String Length: Reduce string size until empty
 * 4. Palindromic Substrings: Count all substrings with matching ends
 * 
 * DRY RUN for All Occurrences:
 * 
 * arr = [3,2,4,5,6,2,7,2,2], key = 2
 * 
 * Process:
 * i=0: arr[0]=3 != 2, continue
 * i=1: arr[1]=2 == 2, print 1
 * i=2: arr[2]=4 != 2, continue
 * i=3: arr[3]=5 != 2, continue
 * i=4: arr[4]=6 != 2, continue
 * i=5: arr[5]=2 == 2, print 5
 * i=6: arr[6]=7 != 2, continue
 * i=7: arr[7]=2 == 2, print 7
 * i=8: arr[8]=2 == 2, print 8
 * 
 * Output: 1 5 7 8
 * 
 * DRY RUN for Number to Words:
 * 
 * number = 1234
 * 
 * Process:
 * printDigits(1234): lastDigit=4, call printDigits(123)
 * printDigits(123): lastDigit=3, call printDigits(12)
 * printDigits(12): lastDigit=2, call printDigits(1)
 * printDigits(1): lastDigit=1, call printDigits(0)
 * printDigits(0): return
 * 
 * Print: "one two three four"
 * 
 * DRY RUN for String Length:
 * 
 * str = "abcde"
 * 
 * Process:
 * length("abcde") = 1 + length("bcde")
 * length("bcde") = 1 + length("cde")
 * length("cde") = 1 + length("de")
 * length("de") = 1 + length("e")
 * length("e") = 1 + length("")
 * length("") = 0
 * 
 * Back substitution: 1+1+1+1+1+0 = 5
 * 
 * DRY RUN for Palindromic Substrings:
 * 
 * str = "abcab", n=5
 * 
 * Process:
 * Count all substrings starting and ending with same character
 * 
 * Valid substrings: "a", "abca", "b", "bcab", "c", "a", "ab", "b"
 * 
 * Total: 7 substrings
 */
public class RecursionAssignment {
    
    static String digits[] = {
        "zero", "one", "two", "three", "four", 
        "five", "six", "seven", "eight", "nine"
    };

    // All occurrences of key in array
    public static void allOccurrences(int arr[], int key, int i) {
        if (i == arr.length) {
            return;
        }
        if (arr[i] == key) {
            System.out.print(i + " ");
        }
        allOccurrences(arr, key, i + 1);
    }

    // Print digits as words
    public static void printDigits(int number) {
        if (number == 0) {
            return;
        }
        int lastDigit = number % 10;
        printDigits(number / 10);
        System.out.print(digits[lastDigit] + " ");
    }

    // Length of string using recursion
    public static int length(String str) {
        if (str.length() == 0) {
            return 0;
        }
        return length(str.substring(1)) + 1;
    }

    // Count substrings starting and ending with same character
    public static int countSubstrs(String str, int i, int j, int n) {
        if (n == 1) {
            return 1;
        }
        if (n <= 0) {
            return 0;
        }

        int res = countSubstrs(str, i + 1, j, n - 1) + 
                  countSubstrs(str, i, j - 1, n - 1) - 
                  countSubstrs(str, i + 1, j - 1, n - 2);

        if (str.charAt(i) == str.charAt(j)) {
            res++;
        }

        return res;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all assignment problems
         * 
         * All Occurrences: [3,2,4,5,6,2,7,2,2], key=2
         * Expected: 1 5 7 8
         * 
         * Number to Words: 1234
         * Expected: one two three four
         * 
         * String Length: "abcde"
         * Expected: 5
         * 
         * Palindromic Substrings: "abcab"
         * Expected: 7
         */
        
        // Test All Occurrences
        System.out.println("Recursion Assignment - All Occurrences");
        int arr[] = {3, 2, 4, 5, 6, 2, 7, 2, 2};
        int key = 2;
        System.out.println("Array: [3,2,4,5,6,2,7,2,2], key=2");
        System.out.println("Expected: 1 5 7 8");
        System.out.print("Output: ");
        allOccurrences(arr, key, 0);
        System.out.println("\n");
        
        // Test Number to Words
        System.out.println("Number to Words:");
        int number = 1234;
        System.out.println("Number: " + number);
        System.out.println("Expected: one two three four");
        System.out.print("Output: ");
        printDigits(number);
        System.out.println("\n");
        
        // Test String Length
        System.out.println("String Length:");
        String str = "abcde";
        System.out.println("String: " + str);
        System.out.println("Expected: 5");
        int len = length(str);
        System.out.println("Output: " + len);
        
        // Test Palindromic Substrings
        System.out.println("\nPalindromic Substrings:");
        String palStr = "abcab";
        System.out.println("String: " + palStr);
        System.out.println("Expected: 7");
        int count = countSubstrs(palStr, 0, palStr.length() - 1, palStr.length());
        System.out.println("Output: " + count);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: All occurrences with no matches
        System.out.println("All occurrences - no matches:");
        int arr2[] = {1, 2, 3, 4, 5};
        System.out.println("Array: [1,2,3,4,5], key=6");
        System.out.print("Output: ");
        allOccurrences(arr2, 6, 0);
        System.out.println();
        
        // Test case 3: Number to words with single digit
        System.out.println("\nSingle digit to words:");
        System.out.println("Number: 7");
        System.out.print("Output: ");
        printDigits(7);
        System.out.println();
        
        // Test case 4: Empty string length
        System.out.println("\nEmpty string length:");
        System.out.println("String: \"\"");
        int emptyLen = length("");
        System.out.println("Output: " + emptyLen);
        
        // Test case 5: Palindromic substrings with all same characters
        System.out.println("\nAll same characters:");
        String sameStr = "aaa";
        System.out.println("String: " + sameStr);
        System.out.println("Expected: 6 (a,a,a,aa,aa,aaa)");
        int sameCount = countSubstrs(sameStr, 0, sameStr.length() - 1, sameStr.length());
        System.out.println("Output: " + sameCount);
        
        // Test case 6: Large number to words
        System.out.println("\nLarge number to words:");
        int largeNum = 2023;
        System.out.println("Number: " + largeNum);
        System.out.print("Output: ");
        printDigits(largeNum);
        System.out.println();
    }
}
