/**
 * QUESTION: Recursion Applications - Implement advanced recursion problems including
 * tiling problem, string operations, friend pairing, and Tower of Hanoi.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Tiling Problem:
 * Count ways to tile 2*n floor with 2*1 tiles.
 * Can place tile vertically (2*1) or horizontally (1*2).
 * 
 * 2. Remove Duplicates from String:
 * Remove duplicate characters from string while preserving order.
 * Use boolean array to track visited characters.
 * 
 * 3. Friend Pairing Problem:
 * Count ways to pair friends or keep them single.
 * Each person can either stay single or pair with someone else.
 * 
 * 4. Binary Strings without Consecutive Ones:
 * Generate all binary strings of length n with no consecutive ones.
 * Use recursion with last character tracking.
 * 
 * 5. Tower of Hanoi:
 * Move n disks from source to destination using auxiliary peg.
 * Rules: Only one disk at a time, larger disk cannot be placed on smaller.
 * 
 * APPROACH USED: Recursive Problem Solving with Multiple Choices
 * 
 * PSEUDOCODE:
 * 
 * Tiling Problem:
 * function tilingProblem(n):
 *   if n == 0 || n == 1: return 1
 *   
 *   // Vertical placement
 *   verticalWays = tilingProblem(n-1)
 *   
 *   // Horizontal placement
 *   horizontalWays = tilingProblem(n-2)
 *   
 *   return verticalWays + horizontalWays
 * 
 * Remove Duplicates:
 * function removeDuplicates(str, idx, newStr, map):
 *   if idx == str.length:
 *     print newStr
 *     return
 *   
 *   currChar = str[idx]
 *   if map[currChar - 'a']:
 *     removeDuplicates(str, idx+1, newStr, map)
 *   else:
 *     map[currChar - 'a'] = true
 *     removeDuplicates(str, idx+1, newStr + currChar, map)
 * 
 * Friend Pairing:
 * function friendsProblem(n):
 *   if n == 1 || n == 2: return n
 *   
 *   // Stay single
 *   single = friendsProblem(n-1)
 *   
 *   // Pair with someone
 *   pairWays = (n-1) * friendsProblem(n-2)
 *   
 *   return single + pairWays
 * 
 * Binary Strings:
 * function printBS(n, lastStr, str):
 *   if n == 0:
 *     print str
 *     return
 *   
 *   printBS(n-1, 0, str + "0")
 *   if lastStr == 0:
 *     printBS(n-1, 1, str + "1")
 * 
 * Tower of Hanoi:
 * function toh(n, source, destination, auxiliary):
 *   if n == 0: return
 *   
 *   toh(n-1, source, auxiliary, destination)
 *   print "move disk n from source to destination"
 *   toh(n-1, auxiliary, destination, source)
 * 
 * EXPLANATION OF STEPS:
 * 1. Identify base cases and recursive relations
 * 2. Handle multiple choices at each recursive step
 * 3. Ensure proper parameter passing and state management
 * 4. Use helper functions when needed for complex operations
 * 
 * DRY RUN for Tiling Problem:
 * 
 * n = 3
 * 
 * Process:
 * tilingProblem(3) = tilingProblem(2) + tilingProblem(1)
 * tilingProblem(2) = tilingProblem(1) + tilingProblem(0)
 * tilingProblem(1) = 1
 * tilingProblem(0) = 1
 * 
 * Back substitution:
 * tilingProblem(2) = 1 + 1 = 2
 * tilingProblem(3) = 2 + 1 = 3
 * 
 * Ways: VVV, VHH, HHV (V=vertical, H=horizontal)
 * 
 * DRY RUN for Friend Pairing:
 * 
 * n = 3
 * 
 * Process:
 * friendsProblem(3) = friendsProblem(2) + 2*friendsProblem(1)
 * friendsProblem(2) = 2
 * friendsProblem(1) = 1
 * 
 * Result: 2 + 2*1 = 4
 * 
 * Ways: (1)(2)(3), (1)(23), (12)(3), (13)(2)
 * 
 * DRY RUN for Tower of Hanoi:
 * 
 * n = 2, A->C using B
 * 
 * Process:
 * toh(2, A, C, B):
 *   toh(1, A, B, C): move 1 from A to B
 *   move 2 from A to C
 *   toh(1, B, C, A): move 1 from B to C
 * 
 * Moves: 1:A->B, 2:A->C, 1:B->C
 */
public class RecursionApplications {
    
    // Tiling Problem - 2*n floor
    public static int tilingProblem(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        
        // Vertical placement
        int verticalWays = tilingProblem(n - 1);
        
        // Horizontal placement
        int horizontalWays = tilingProblem(n - 2);
        
        return verticalWays + horizontalWays;
    }

    // Remove duplicates from string
    public static void removeDuplicates(String str, int idx, StringBuilder newStr, boolean map[]) {
        if (idx == str.length()) {
            System.out.println(newStr.toString());
            return;
        }
        
        char currChar = str.charAt(idx);
        if (map[currChar - 'a']) {
            removeDuplicates(str, idx + 1, newStr, map);
        } else {
            map[currChar - 'a'] = true;
            removeDuplicates(str, idx + 1, newStr.append(currChar), map);
        }
    }

    // Friend Pairing Problem
    public static int friendsProblem(int n) {
        if (n == 2 || n == 1) {
            return n;
        }
        
        return friendsProblem(n - 1) + (n - 1) * friendsProblem(n - 2);
    }

    // Print binary strings without consecutive ones
    public static void printBS(int n, int lastStr, String str) {
        if (n == 0) {
            System.out.println(str);
            return;
        }
        
        printBS(n - 1, 0, str + "0");
        if (lastStr == 0) {
            printBS(n - 1, 1, str + "1");
        }
    }

    // Tower of Hanoi
    public static void toh(int n, int A, int C, int B) {
        if (n == 0) {
            return;
        }
        
        toh(n - 1, A, B, C);
        System.out.println("Moving " + n + " th disk from " + A + " to " + C);
        toh(n - 1, B, C, A);
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all recursion applications
         * 
         * Tiling Problem: n=3
         * Expected: 3 ways
         * 
         * Remove Duplicates: "appnnaacollege"
         * Expected: "apncoleg"
         * 
         * Friend Pairing: n=4
         * Expected: 10 ways
         * 
         * Binary Strings: n=3
         * Expected: 5 strings (000,001,010,100,101)
         * 
         * Tower of Hanoi: n=2, A->C
         * Expected: 3 moves
         */
        
        // Test Tiling Problem
        System.out.println("Recursion Applications - Tiling Problem");
        System.out.println("n = 3 (2*3 floor)");
        System.out.println("Expected: 3 ways");
        int tilingWays = tilingProblem(3);
        System.out.println("Output: " + tilingWays);
        
        // Test Remove Duplicates
        System.out.println("\nRemove Duplicates:");
        String str = "appnnaacollege";
        System.out.println("Original: " + str);
        System.out.println("Expected: apncoleg");
        System.out.print("Output: ");
        removeDuplicates(str, 0, new StringBuilder(), new boolean[26]);
        
        // Test Friend Pairing
        System.out.println("\nFriend Pairing Problem:");
        System.out.println("n = 4 friends");
        System.out.println("Expected: 10 ways");
        int pairingWays = friendsProblem(4);
        System.out.println("Output: " + pairingWays);
        
        // Test Binary Strings
        System.out.println("\nBinary Strings without Consecutive Ones:");
        System.out.println("n = 3");
        System.out.println("Expected: 000,001,010,100,101");
        System.out.println("Output:");
        printBS(3, 0, "");
        
        // Test Tower of Hanoi
        System.out.println("\nTower of Hanoi:");
        System.out.println("n = 2, move from A to C");
        System.out.println("Expected: 3 moves");
        System.out.println("Output:");
        toh(2, 'A', 'C', 'B');
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Tiling with n=4
        System.out.println("Tiling n=4:");
        System.out.println("Expected: 5 ways");
        int tiling4 = tilingProblem(4);
        System.out.println("Output: " + tiling4);
        
        // Test case 3: Friend pairing with n=5
        System.out.println("\nFriend pairing n=5:");
        System.out.println("Expected: 26 ways");
        int pairing5 = friendsProblem(5);
        System.out.println("Output: " + pairing5);
        
        // Test case 4: Binary strings n=2
        System.out.println("\nBinary strings n=2:");
        System.out.println("Expected: 00,01,10");
        System.out.println("Output:");
        printBS(2, 0, "");
        
        // Test case 5: Tower of Hanoi with n=3
        System.out.println("\nTower of Hanoi n=3:");
        System.out.println("Expected: 7 moves");
        System.out.println("Output:");
        toh(3, 'A', 'C', 'B');
    }
}
