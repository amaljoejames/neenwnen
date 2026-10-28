import java.util.*;

public class Lis {
    /*Problem Statement

You are given an array A of size N.

Your task is to reorder the array in any way and then find the length of the Longest Increasing Subsequence (LIS) in the reordered array.

Print the maximum possible LIS length.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the relative order of the remaining elements.

Key Observation

Since reordering is allowed, we can simply:

Sort the array.
After sorting, the array becomes non-decreasing.
The LIS will contain only distinct increasing elements.

Therefore:

Answer = Number of unique elements in the array

Input Format
First line contains an integer N — size of the array.
Second line contains N space-separated integers.
Output Format
Print a single integer representing the maximum possible length of the Longest Increasing Subsequence.
Example
Input
5
5 3 3 3 1
Sorted Array
1 3 3 3 5
Increasing Subsequence
1 3 5
Output
3
Approach

Since reordering is allowed:

Sort the array.
Count distinct elements.
Distinct elements themselves form the longest strictly increasing subsequence.
Algorithm
Read N
Read array elements
Insert all elements into a set
Print size of the set
Time Complexity
Using Set:
Insertion: O(log N)
Total: O(N log N)
Space Complexity
O(N) */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        HashSet<Integer> s = new HashSet<>();

        while(n-- > 0) {
            s.add(sc.nextInt());
        }

        System.out.println(s.size());
    }
}