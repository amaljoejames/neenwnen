/*
 * Most Fit Trainee Problem
 *
 * Problem Statement:
 * A batch of 3 trainees undergoes a fitness running test for 3 rounds.
 * After every round, oxygen levels of each trainee are recorded.
 *
 * Task:
 * 1. Calculate average oxygen level of each trainee.
 * 2. Find trainee(s) having highest average oxygen level.
 * 3. If highest average < 70, declare all trainees unfit.
 *
 * Conditions:
 * - Oxygen level must be between 1 and 100.
 * - Invalid input → print "INVALID INPUT"
 * - Average should be rounded.
 *
 * ---------------------------------------------------
 * Approach:
 * ---------------------------------------------------
 * 1. Store oxygen values in a 2D array.
 *    Rows   -> Trainees
 *    Columns-> Rounds
 *
 * 2. Traverse each trainee row:
 *      - Calculate sum
 *      - Compute rounded average
 *
 * 3. Find maximum average oxygen level.
 *
 * 4. If maximum average < 70:
 *      Print "All trainees are unfit"
 *
 * 5. Otherwise:
 *      Print all trainee numbers whose
 *      average equals maximum average.
 *
 * ---------------------------------------------------
 * Example Input:
 * ---------------------------------------------------
 * 95 92 95
 * 92 90 92
 * 90 92 90
 *
 * ---------------------------------------------------
 * Step-by-Step Explanation:
 * ---------------------------------------------------
 * Trainee 1:
 * Oxygen Levels = 95, 92, 95
 * Sum = 282
 * Average = 282 / 3 = 94
 *
 * Trainee 2:
 * Oxygen Levels = 92, 90, 92
 * Sum = 274
 * Average = 91
 *
 * Trainee 3:
 * Oxygen Levels = 90, 92, 90
 * Sum = 272
 * Average = 91
 *
 * Highest Average = 94
 *
 * Therefore:
 * Trainee 1 is the most fit trainee.
 *
 * ---------------------------------------------------
 * Expected Output:
 * ---------------------------------------------------
 * Trainee Number : 1
 * Highest Avg Ox Lvl is: 94
 *
 * ---------------------------------------------------
 * Time Complexity:
 * O(1)
 *
 * Space Complexity:
 * O(1)
 */

import java.util.*;

public class OxygenLevel {

    public static int[] calculateAverage(int[][] oxygen) {

        int[] avg = new int[3];

        for (int i = 0; i < 3; i++) {

            int sum = 0;

            for (int j = 0; j < 3; j++) {
                sum += oxygen[i][j];
            }

            avg[i] = Math.round(sum / 3.0f);
        }

        return avg;
    }

    public static int findMaxAverage(int[] avg) {

        int maxAvg = avg[0];

        for (int i = 1; i < 3; i++) {

            if (avg[i] > maxAvg) {
                maxAvg = avg[i];
            }
        }

        return maxAvg;
    }

    public static void displayResult(int[] avg, int maxAvg) {

        if (maxAvg < 70) {
            System.out.println("All trainees are unfit");
            return;
        }

        for (int i = 0; i < 3; i++) {

            if (avg[i] == maxAvg) {
                System.out.println("Trainee Number : " + (i + 1));
            }
        }

        System.out.println("Highest Avg Ox Lvl is: " + maxAvg);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] oxygen = new int[3][3];

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                oxygen[i][j] = sc.nextInt();

                if (oxygen[i][j] < 1 || oxygen[i][j] > 100) {

                    System.out.println("INVALID INPUT");
                    sc.close();
                    return;
                }
            }
        }

        int[] avg = calculateAverage(oxygen);

        int maxAvg = findMaxAverage(avg);

        displayResult(avg, maxAvg);

        sc.close();
    }
}