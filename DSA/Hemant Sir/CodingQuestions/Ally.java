/*
 * Aly's Buffet Lucky Draw Problem (2.1)
 *
 * Aly is planning a buffet for the inauguration party, and everyone is invited.
 *
 * On their way in, each guest picks up a sheet of paper containing a random number
 * (this number may be repeated).
 *
 * The guests then sit down on a round table with their friends.
 *
 * He now decides that he would like to play a game.
 *
 * He asks you to pick a random person from your table and have them read
 * their number out loud.
 *
 * Then, moving clockwise around the table, each person will read out their number.
 *
 * The goal is to find that set of numbers which forms an increasing subsequence.
 *
 * All people owning these numbers will be eligible for a lucky draw!
 *
 * One of the software developers is very excited about this prospect,
 * and wants to maximize the number of people who are eligible for the lucky draw.
 *
 * So, he decides to write a program that decides who should read their number first
 * so as to maximize the number of people that are eligible for the lucky draw.
 *
 * Can you beat him to it?
 *
 * Input Format:
 * - First line: Integer N (number of guests)
 * - Second line: N space-separated integers (numbers on guests' papers)
 *
 * Output Format:
 * - Single integer: Maximum length of increasing subsequence possible
 *   by choosing optimal starting position
 *
 * Sample Input 1:
 * 5
 * 1 2 3 4 5
 *
 * Sample Output 1:
 * 5
 *
 * Explanation 1:
 * Already sorted, so starting at position 0 gives LIS = 5
 *
 * Sample Input 2:
 * 5
 * 5 4 3 2 1
 *
 * Sample Output 2:
 * 1
 *
 * Explanation 2:
 * Reverse sorted, maximum LIS from any starting point is 1
 *
 * Sample Input 3:
 * 6
 * 1 3 5 2 4 6
 *
 * Sample Output 3:
 * 4
 *
 * Explanation 3:
 * Starting at position 3 (number 2): sequence 2,4,6 gives LIS = 3
 * Actually: 2,4,6 is length 3, but 2,4,6 and wrapping... let me check
 * Starting at 3: 2, 4, 6, then wrap to 1, 3, 5
 * LIS: 2, 4, 6 or we can do 2, 4, 6 (3 elements) or starting from 0: 1,3,5 (3)
 * Or starting at 2: 5, then 2, 4, 6 -> LIS: 2, 4, 6 (3 elements)
 * Actually optimal might be 1,2,4,6 or 1,3,4,6 = 4
 * Starting at 0: 1,3,5,2,4,6 -> LIS: 1,2,4,6 or 1,3,4,6 = 4
 *
 * Approach:
 * Since table is circular, we need to consider array as circular.
 * For each starting position, compute LIS in the circular sequence.
 * Return maximum LIS found.
 *
 * Time Complexity: O(N^2) for each starting point, total O(N^3)
 * Can be optimized to O(N^2) using dynamic programming
 */

import java.util.Scanner;

public class Ally {

    public static int computeLIS(int[] arr, int start) {
        int n = arr.length;
        int[] dp = new int[n];
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            int currIdx = (start + i) % n;
            for (int j = 0; j < i; j++) {
                int prevIdx = (start + j) % n;
                if (arr[prevIdx] < arr[currIdx]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static int findMaxLIS(int[] arr) {
        int n = arr.length;
        int maxLIS = 0;

        for (int start = 0; start < n; start++) {
            int lis = computeLIS(arr, start);
            maxLIS = Math.max(maxLIS, lis);
        }

        return maxLIS;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(findMaxLIS(arr));

        sc.close();
    }
}
