/*
 * Smallest Element from Pairs Sum Problem
 * 
 * Problem Statement:
 * You're given an integer array. Task is to pair up the elements in ascending order 
 * and then compute the sum of the smallest element from each pair.
 * 
 * First sort the array in non-decreasing order, pair elements sequentially 
 * (first with second, third with fourth, and so on). For each pair, take the 
 * smallest element. If the array has an odd number of elements, the last 
 * unpaired element is also added to the sum. Return the final sum.
 * 
 * Input: Array of size N
 * Output: Sum of smallest elements from each pair
 * 
 * Example 1:
 * Input: [5, 3, 1, 8, 4, 2]
 * Output: 9
 * Explanation: 
 * - Sort: [1, 2, 3, 4, 5, 8]
 * - Pairs: (1,2), (3,4), (5,8)
 * - Smallest from pairs: 1, 3, 5
 * - Sum: 1 + 3 + 5 = 9
 * 
 * Example 2:
 * Input: [7, 1, 9, 3]
 * Output: 4
 * Explanation:
 * - Sort: [1, 3, 7, 9]
 * - Pairs: (1,3), (7,9)
 * - Smallest from pairs: 1, 7
 * - Sum: 1 + 7 = 8 (oops, correction: 1 + 7 = 8)
 * 
 * Example 3:
 * Input: [4, 2, 5]
 * Output: 6
 * Explanation:
 * - Sort: [2, 4, 5]
 * - Pairs: (2,4), (5) - unpaired
 * - Smallest from pairs: 2, 5
 * - Sum: 2 + 5 = 7 (oops, correction: 2 + 5 = 7)
 * 
 * Algorithm:
 * 1. Sort the array in non-decreasing order
 * 2. Initialize sum = 0
 * 3. Iterate through array with step of 2
 * 4. For each pair (i, i+1), add arr[i] to sum (smaller element)
 * 5. If odd length, add last element to sum
 * 6. Return sum
 * 
 * Time Complexity: O(N log N) - due to sorting
 * Space Complexity: O(1) - ignoring sort space
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.PriorityQueue;

public class SmallestSubarrSum {

    public static int sumOfSmallestFromPairs(int[] arr) {
        Arrays.sort(arr);
        int sum = 0;

        for (int i = 0; i < arr.length - 1; i += 2) {
            sum += arr[i];
        }

        if (arr.length % 2 != 0) {
            sum += arr[arr.length - 1];
        }

        return sum;
    }

    public static int sumOfSmallestFromPairsPQ(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : arr) {
            pq.offer(num);
        }
        int sum = 0;
        boolean firstInPair = true;

        while (!pq.isEmpty()) {
            int smallest = pq.poll();

            if (firstInPair) {
                sum += smallest;
            }
            firstInPair = !firstInPair;
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int result1 = sumOfSmallestFromPairs(arr);
        int result2 = sumOfSmallestFromPairsPQ(arr);

        System.out.println("SumOfSmallestFromPairs (Sort): " + result1);
        System.out.println("SumOfSmallestFromPairs (PQ): " + result2);

        sc.close();
    }
}
