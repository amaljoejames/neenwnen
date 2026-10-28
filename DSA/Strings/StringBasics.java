/**
 * QUESTION: String Basics - Implement fundamental string operations including
 * printing characters, palindrome checking, substring extraction, and string builder.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Print Letters:
 * Print each character of string individually using charAt().
 * Demonstrates string traversal and character access.
 * 
 * 2. Palindrome Check:
 * Check if string reads the same forwards and backwards.
 * Compare characters from start and end moving towards center.
 * 
 * 3. Shortest Path Calculation:
 * Calculate shortest distance from origin for directional path.
 * Process N, S, E, W directions and calculate Euclidean distance.
 * 
 * 4. Substring Extraction:
 * Extract substring from given start to end indices.
 * Manual implementation without using built-in substring method.
 * 
 * 5. String Builder:
 * Efficient string manipulation using StringBuilder class.
 * Demonstrates mutable string operations.
 * 
 * APPROACH USED: String Character Processing and Mathematical Calculations
 * 
 * PSEUDOCODE:
 * 
 * Print Letters:
 * function printLtrs(str):
 *   for i = 0 to str.length-1:
 *     print str.charAt(i)
 *   print newline
 * 
 * Palindrome Check:
 * function isPalindrome(str):
 *   n = str.length
 *   for i = 0 to n/2-1:
 *     if str.charAt(i) != str.charAt(n-i-1):
 *       return false
 *   return true
 * 
 * Shortest Path:
 * function getShortestPath(path):
 *   x = 0, y = 0
 *   for each char in path:
 *     switch char:
 *       case 's': y--
 *       case 'n': y++
 *       case 'w': x--
 *       case 'e': x++
 *   
 *   distance = sqrt(x*x + y*y)
 *   return distance
 * 
 * Substring:
 * function subString(str, si, ei):
 *   substr = ""
 *   for i = si to ei-1:
 *     substr += str.charAt(i)
 *   return substr
 * 
 * String Builder:
 * function buildAlphabet():
 *   sb = StringBuilder()
 *   for ch = 'a' to 'z':
 *     sb.append(ch)
 *   return sb.toString()
 * 
 * EXPLANATION OF STEPS:
 * 1. String Traversal: Use charAt() for character access
 * 2. Palindrome: Two-pointer technique from both ends
 * 3. Path Calculation: Track coordinates and calculate distance
 * 4. Substring: Manual character concatenation
 * 5. StringBuilder: Use mutable string for efficiency
 * 
 * DRY RUN for Palindrome Check:
 * 
 * String: "racecar"
 * 
 * Process:
 * n = 7, n/2 = 3
 * 
 * i=0: str[0]='r', str[6]='r' -> match
 * i=1: str[1]='a', str[5]='a' -> match
 * i=2: str[2]='c', str[4]='c' -> match
 * 
 * All pairs match -> palindrome
 * 
 * DRY RUN for Shortest Path:
 * 
 * Path: "WNEENESENNN"
 * 
 * Process:
 * W: x=-1, y=0
 * N: x=-1, y=1
 * E: x=0, y=1
 * N: x=0, y=2
 * E: x=1, y=2
 * S: x=1, y=1
 * E: x=2, y=1
 * N: x=2, y=2
 * N: x=2, y=3
 * N: x=2, y=4
 * 
 * Final position: (2,4)
 * Distance: sqrt(2² + 4²) = sqrt(20) = 4.47
 * 
 * DRY RUN for Substring:
 * 
 * String: "Hello World", si=0, ei=5
 * 
 * Process:
 * i=0: substr += 'H' -> "H"
 * i=1: substr += 'e' -> "He"
 * i=2: substr += 'l' -> "Hel"
 * i=3: substr += 'l' -> "Hell"
 * i=4: substr += 'o' -> "Hello"
 * 
 * Result: "Hello"
 */
public class StringBasics {
    
    // Print all characters of string
    public static void printLtrs(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i));
        }
        System.out.println();
    }

    // Check if string is palindrome
    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            int n = str.length();
            if (str.charAt(i) != str.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    // Get shortest path from directions
    public static double getShortestPath(String path) {
        int x = 0, y = 0;
        
        for (int i = 0; i < path.length(); i++) {
            char dir = path.charAt(i);
            
            switch (dir) {
                case 's' -> y--;
                case 'n' -> y++;
                case 'w' -> x--;
                default -> x++; // east
            }
        }
        
        int x2 = x * x;
        int y2 = y * y;
        return Math.sqrt(x2 + y2);
    }

    // Extract substring manually
    public static String subString(String str, int si, int ei) {
        String substr = "";
        for (int i = si; i < ei; i++) {
            substr += str.charAt(i);
        }
        return substr;
    }

    // Build alphabet string using StringBuilder
    public static String buildAlphabet() {
        StringBuilder sb = new StringBuilder("");
        for (char ch = 'a'; ch <= 'z'; ch++) {
            sb.append(ch);
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all basic string operations
         * 
         * Print Letters: "Hello"
         * Expected: H e l l o
         * 
         * Palindrome: "racecar"
         * Expected: true
         * 
         * Shortest Path: "WNEENESENNN"
         * Expected: 4.47 (distance from origin)
         * 
         * Substring: "Hello World", 0, 5
         * Expected: "Hello"
         * 
         * Build Alphabet: a-z
         * Expected: "abcdefghijklmnopqrstuvwxyz"
         */
        
        // Test Print Letters
        System.out.println("String Basics - Print Letters");
        String testStr = "Hello";
        System.out.println("String: " + testStr);
        System.out.println("Expected: H e l l o");
        System.out.print("Output: ");
        printLtrs(testStr);
        
        // Test Palindrome Check
        System.out.println("\nPalindrome Check:");
        String palindromeStr = "racecar";
        System.out.println("String: " + palindromeStr);
        System.out.println("Expected: true");
        boolean isPal = isPalindrome(palindromeStr);
        System.out.println("Output: " + isPal);
        
        // Test Shortest Path
        System.out.println("\nShortest Path:");
        String path = "WNEENESENNN";
        System.out.println("Path: " + path);
        System.out.println("Expected: ~4.47");
        double distance = getShortestPath(path);
        System.out.printf("Output: %.2f\n", distance);
        
        // Test Substring
        System.out.println("\nSubstring:");
        String helloWorld = "Hello World";
        System.out.println("String: " + helloWorld + ", indices: 0, 5");
        System.out.println("Expected: Hello");
        String substr = subString(helloWorld, 0, 5);
        System.out.println("Output: " + substr);
        
        // Test Build Alphabet
        System.out.println("\nBuild Alphabet:");
        System.out.println("Expected: a-z");
        String alphabet = buildAlphabet();
        System.out.println("Output: " + alphabet);
        System.out.println("Length: " + alphabet.length());
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Non-palindrome
        System.out.println("Non-palindrome:");
        String nonPal = "hello";
        System.out.println("String: " + nonPal);
        System.out.println("Expected: false");
        boolean notPal = isPalindrome(nonPal);
        System.out.println("Output: " + notPal);
        
        // Test case 3: Empty path
        System.out.println("\nEmpty path:");
        System.out.println("Path: \"\"");
        System.out.println("Expected: 0.0");
        double emptyPath = getShortestPath("");
        System.out.printf("Output: %.2f\n", emptyPath);
        
        // Test case 4: Single character substring
        System.out.println("\nSingle character:");
        System.out.println("String: A, indices: 0, 1");
        System.out.println("Expected: A");
        String single = subString("A", 0, 1);
        System.out.println("Output: " + single);
        
        // Test case 5: Different path directions
        System.out.println("\nDifferent path:");
        String path2 = "NS";
        System.out.println("Path: NS");
        System.out.println("Expected: 0.0");
        double path2Dist = getShortestPath(path2);
        System.out.printf("Output: %.2f\n", path2Dist);
    }
}
