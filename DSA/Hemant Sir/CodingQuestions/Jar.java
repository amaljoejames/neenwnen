/*
 * JAR Candy Management Problem
 * JAR has capacity N, can hold M candies (M <= N).
 * When k candies remain, JAR is refilled to full capacity.
 * Input: customer orders. Output: candies sold and JAR count.
 * Return "INVALID INPUT" if order > available candies.
 */

import java.util.Scanner;

public class Jar {
    private static int capacity, currentCandies, threshold, totalSold;

    public static String sellCandies(int order) {
        if (order > currentCandies) return "INVALID INPUT";
        currentCandies -= order;
        totalSold += order;
        if (currentCandies <= threshold) {
            currentCandies = capacity;
            return "Candies sold: " + order + ", JAR count: " + currentCandies + " (Refilled)";
        }
        return "Candies sold: " + order + ", JAR count: " + currentCandies;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        capacity = sc.nextInt();
        currentCandies = sc.nextInt();
        threshold = sc.nextInt();
        
        int Q = sc.nextInt();
        for (int i = 0; i < Q; i++) {
            int order = sc.nextInt();
            System.out.println(sellCandies(order));
        }
        System.out.println("Total candies sold: " + totalSold);
        System.out.println("Final JAR count: " + currentCandies);
        sc.close();
    }
}
