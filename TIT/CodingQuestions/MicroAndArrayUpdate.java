package TIT.CodingQuestions;

/*
 * Micro and Array Update Problem
 * 
 * Problem Statement:
 * Micro purchased an array A having N integer values. After playing it for a while, 
 * he got bored of it and decided to update value of its element. In one second 
 * he can increase value of each array element by 1. He wants each array element's 
 * value to become greater than or equal to K. Please help Micro to find out the 
 * minimum amount of time it will take, for him to do so.
 * 
 * Input:
 * First line consists of a single integer, T, denoting the number of test cases.
 * First line of each test case consists of two space separated integers denoting N and K.
 * Second line of each test case consists of N space separated integers denoting the array A.
 * 
 * Output:
 * For each test case, print the minimum time in which all array elements will become 
 * greater than or equal to K. Print a new line after each test case.
 * 
 * Sample Input:
 * 2
 * 3 4
 * 1 2 5
 * 3 2
 * 2 5 5
 * 
 * Sample Output:
 * 3
 * 0
 * 
 * Explanation:
 * For first test case,
 * After 1 second, array will be [2,3,6]
 * After 2 second, array will be [3,4,7] 
 * After 3 second, array will be [4,5,8]
 * So it will take 3 second for all array elements to become greater than or equal to 4.
 * 
 * Algorithm:
 * Since all elements increase by 1 simultaneously each second, we need to find the
 * maximum difference between K and any element that is less than K.
 * For each element A[i] < K, time needed = K - A[i]
 * Answer = maximum of all these time requirements
 */

import java.util.Scanner;

public class MicroAndArrayUpdate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int T = scanner.nextInt();
        
        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();
            int K = scanner.nextInt();
            
            int[] A = new int[N];
            int minTime = 0;
            
            for (int i = 0; i < N; i++) {
                A[i] = scanner.nextInt();
            }
            
            for (int i = 0; i < N; i++) {
                if (A[i] < K) {
                    int tNeed = K - A[i];
                    if (tNeed > minTime) {
                        minTime = tNeed;
                    }
                }
            }
            
            System.out.println(minTime);
        }
        
        scanner.close();
    }
}

