import java.util.Scanner;

/**
 * QUESTION:
 * John is planning to start a business across N cities. Initially, the investments 
 * for all N cities are zero. John has a goal of finding the maximum revenue/investment 
 * value from any single city after performing K operational goals. Each operation 
 * consists of adding a specific investment amount to a range of cities from a 
 * start index to an end index (1-based indexing).
 * * Input Format:
 * - First line: Two space-separated integers N (number of cities) and K (number of operations).
 * - Next K lines: Three space-separated integers: start_city, end_city, and investment_value.
 * * Output Format:
 * - Print the maximum revenue gained across all N cities after all operations.
 * * Sample Input 1:
 * 10 4
 * 4 7 8
 * 1 8 19
 * 9 10 5
 * 1 10 7
 * * Sample Output 1:
 * 34
 */

public class CityJohn {

    /**
     * APPROACH: Difference Array (Range Update Optimization)
     * * 1. Brute Force Approach: For each of the K operations, loop from start to end 
     * and add the value. This takes O(N * K) time, which will result in a Time Limit 
     * Exceeded (TLE) error for large inputs.
     * * 2. Optimized Approach (Difference Array): 
     * Instead of updating all elements in the range, we can perform the operation 
     * in O(1) time using a difference array of size N + 2.
     * - For an operation (start, end, value):
     * diff[start] += value;
     * diff[end + 1] -= value;
     * * 3. Prefix Sum:
     * After parsing all operations, we compute the prefix sum (running sum) of the 
     * diff array from index 1 to N. The running sum at any index 'i' gives the actual 
     * final investment value of city 'i'.
     * * 4. Maximum Value:
     * While calculating the prefix sum, we continuously track the maximum value 
     * encountered. This drops the overall time complexity to O(N + K).
     */
    public static long getMaxRevenue(int n, int[][] operations) {
        long[] diff = new long[n + 2];

        for (int[] op : operations) {
            int start = op[0];
            int end = op[1];
            int value = op[2];

            diff[start] += value;
            diff[end + 1] -= value;
        }
        long maxRevenue = 0;
        long currentSum = 0;
        
        for (int i = 1; i <= n; i++) {
            currentSum += diff[i];
            if (currentSum > maxRevenue) {
                maxRevenue = currentSum;
            }
        }

        return maxRevenue;
    }
    


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] operations = new int[k][3];
        for (int i = 0; i < k; i++) {
            operations[i][0] = sc.nextInt(); 
            operations[i][1] = sc.nextInt(); 
            operations[i][2] = sc.nextInt();
        }
        sc.close();
        System.out.println("The ans is " + getMaxRevenue(n, operations));
    }
}