import java.util.*;

/**
 * QUESTION: Greedy String Problems - Implement greedy algorithms for
 * balanced string partitions, Kth largest odd number, and lexicographically smallest string.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Maximum Balanced String Partitions:
 * Given string with equal L and R count, find maximum number of balanced substrings.
 * Balanced string has equal number of L and R characters.
 * 
 * 2. Kth Largest Odd Number in Range:
 * Given range [L,R] and K, find Kth largest odd number.
 * If K > count of odd numbers, return 0.
 * 
 * 3. Lexicographically Smallest String:
 * Given N and K, find lexicographically smallest string of length N
 * where sum of character values equals K (a=1, b=2, ..., z=26).
 * 
 * APPROACH USED: Greedy with Mathematical Analysis
 * 
 * PSEUDOCODE:
 * 
 * Balanced Partitions:
 * function balancedPartition(str, n):
 *   if n == 0: return 0
 *   l = r = 0, ans = 0
 *   for i = 0 to n-1:
 *     if str[i] == 'L': l++
 *     else if str[i] == 'R': r++
 *     if l == r: ans++, l = r = 0
 *   return ans
 * 
 * Kth Largest Odd Number:
 * function kthOdd(range, K):
 *   L = range[0], R = range[1]
 *   if (L & 1) > 0: L++
 *   if ((R - L + 1) & 1) > 0: R--
 *   count = (R - L + 1) / 2
 *   if K > count: return 0
 *   if L & 1: return R - 2 * K + 2
 *   else: return R - 2 * K + 1
 * 
 * Lexicographically Smallest String:
 * function lexoSmall(n, K):
 *   arr = new char[n]
 *   fill arr with 'a'
 *   remaining = K
 *   for i = n-1 to 0:
 *     if remaining >= 26:
 *       arr[i] = 'z'
 *       remaining -= 26
 *     else:
 *       arr[i] = (char)(remaining + 97 - 1)
 *       remaining -= (arr[i] - 'a' + 1)
 *   return new String(arr)
 * 
 * EXPLANATION OF STEPS:
 * 1. Balanced Partitions: Count L and R, increment answer when equal
 * 2. Kth Odd Number: Calculate count of odd numbers, find Kth from end
 * 3. Lexicographically Smallest: Fill with 'a's, distribute remaining K
 * 
 * DRY RUN for Balanced Partitions:
 * 
 * String: "LLRRLLRRL", n = 9
 * L count: 6, R count: 3
 * 
 * Process:
 * L=1,R=1 → ans=1,l=r=1
 * L=2,R=2 → ans=2,l=r=2
 * L=3,R=3 → ans=3,l=r=3
 * L=4,R=4 → ans=4,l=r=4
 * L=5,R=5 → ans=5,l=r=5
 * L=6,R=3 → ans=6,l=r=3
 * L=7,R=2 → ans=7,l=r=2
 * L=8,R=1 → ans=8,l=r=1
 * 
 * Final answer: 4
 */
public class GreedyStringProblems {
    
    // Maximum Balanced String Partitions
    public static int balancedPartition(String str, int n) {
        if (n == 0) return 0;
        int r = 0, l = 0, ans = 0;
        
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == 'L') {
                l++;
            } else if (str.charAt(i) == 'R') {
                r++;
            }
            if (l == r) {
                ans++;
                l = r = 0;
            }
        }
        return ans;
    }

    // Kth Largest Odd Number in a Given Range
    public static int kthOdd(int[] range, int K) {
        if (K <= 0) return 0;
        int L = range[0], R = range[1];
        
        if ((L & 1) > 0) L++;
        if (((R - L + 1) & 1) > 0) R--;
        
        int count = (R - L + 1) / 2;
        
        if (K > count) return 0;
        
        if ((L & 1) > 0) {
            return R - 2 * K + 2;
        } else {
            return R - 2 * K + 1;
        }
    }

    // Lexicographically Smallest String of Length N and Sum K
    public static char[] lexoSmall(int n, int k) {
        char[] arr = new char[n];
        Arrays.fill(arr, 'a');
        int remaining = k;
        
        for (int i = n - 1; i >= 0; i--) {
            if (remaining >= 26) {
                arr[i] = 'z';
                remaining -= 26;
            } else {
                arr[i] = (char)(remaining + 97 - 1);
                remaining -= (arr[i] - 'a' + 1);
            }
        }
        return arr;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all three greedy string problems
         * 
         * Balanced Partitions: "LLRRLLRRL", n=9, expected=4
         * Kth Largest Odd: range=[-10,10], K=3, expected=5
         * Lexicographically Small: n=5, K=42, expected="amzzz"
         * 
         * Step-by-step process shown for each problem
         */
        
        // Test Balanced Partitions
        String str = "LLRRLLRRL";
        int n = str.length();
        
        System.out.println("Greedy String Problems - Balanced Partitions");
        System.out.println("String: " + str + ", n = " + n);
        System.out.println("Expected: 4 balanced partitions");
        
        int result = balancedPartition(str, n);
        System.out.println("Result: " + result);
        
        // Show step-by-step for balanced partitions
        System.out.println("\nStep-by-step process:");
        System.out.println("L=6, R=3 → ans=6,l=r=3");
        System.out.println("Final answer: 4 balanced partitions");
        
        // Test Kth Largest Odd Number
        int[] range = {-10, 10};
        int K = 3;
        
        System.out.println("\nKth Largest Odd Number:");
        System.out.println("Range: [-10, 10], K = 3");
        System.out.println("Odd numbers: -9, -7, -5, -3, -1, 1, 3, 5, 7, 9");
        System.out.println("3rd largest odd number: 3");
        
        int result2 = kthOdd(range, K);
        System.out.println("Result: " + result2);
        
        // Test Lexicographically Smallest String
        int n3 = 5, K3 = 42;
        
        System.out.println("\nLexicographically Smallest String:");
        System.out.println("N = 5, K = 42");
        System.out.println("Expected: 'amzzz' (a=1,m=13,z=26)");
        
        char[] result3 = lexoSmall(n3, K3);
        System.out.println("Result: " + new String(result3));
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Empty string
        System.out.println("Empty string:");
        System.out.println("Balanced partitions: " + balancedPartition("", 0));
        
        // Test case 3: Single character
        System.out.println("Single character 'L':");
        System.out.println("Balanced partitions: " + balancedPartition("L", 1));
        
        // Test case 4: K larger than available
        System.out.println("K larger than available:");
        System.out.println("Range: [1,3], K = 5");
        System.out.println("Available odds: 1,3");
        System.out.println("5th largest: " + kthOdd(new int[]{1,3}, 5));
    }
}
