/*
 * Longest Strictly Decreasing Contiguous Subarray Problem
 * 
 * Problem Statement:
 * You are given an integer array. Your task is to find the length of the longest 
 * contiguous subarray in which the elements are in strictly decreasing order.
 * Return the maximum length of such a subarray.
 * 
 * Input: An integer array of size N
 * Output: An integer representing maximum length of a contiguous subarray with decreasing order
 * 
 * Example 1:
 * Input: [1, 2, 3, 5, 4]
 * Output: 2
 * Explanation: The longest strictly decreasing contiguous subarray is [5, 4] with length 2.
 * 
 * Example 2:
 * Input: [9, 8, 7, 6, 5]
 * Output: 5
 * Explanation: The entire array is strictly decreasing, so length is 5.
 * 
 * Example 3:
 * Input: [1, 2, 3, 4, 5]
 * Output: 1
 * Explanation: No decreasing pairs found, so each individual element is a subarray of length 1.
 * 
 * Algorithm:
 * 1. Initialize maxLength = 1 (minimum possible length)
 * 2. Initialize currentLength = 1
 * 3. Iterate through array from index 1 to n-1
 * 4. If arr[i] < arr[i-1] (strictly decreasing):
 *    - Increment currentLength
 *    - Update maxLength if currentLength > maxLength
 * 5. Else:
 *    - Reset currentLength to 1 (start new subarray)
 * 6. Return maxLength
 * 
 * Time Complexity: O(N) - Single pass through array
 * Space Complexity: O(1) - Constant extra space
 */

import java.util.Scanner;

public class ContigiousSubArray {
    
    public static int findMaxDecSubArrLen(int[] arr) {
        if (arr.length == 0) return 0;
        
        int maxLen = 1;
        int currLen = 1;
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                currLen++;
                maxLen = Math.max(maxLen, currLen);
            } else {
                currLen = 1;
            }
        }
        
        return maxLen;
    }

    // Sliding Window 
    public static int findMaxDecSubArrLenSlidingWindow(int[] arr) {
        if (arr.length == 0) return 0;
        
        int maxLen = 1;
        int left = 0;
        
        for (int right = 1; right < arr.length; right++) {
            if (arr[right] >= arr[right - 1]) {
                left = right;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int result1 = findMaxDecSubArrLen(arr);
        int result2 = findMaxDecSubArrLenSlidingWindow(arr);
        
        System.out.println("MaxLenOfDecSubArr (Simple): " + result1);
        System.out.println("MaxLenOfDecSubArr (SlidingWindow): " + result2);
        
        sc.close();
    }
}
