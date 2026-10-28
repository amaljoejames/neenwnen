/*
 * Washing Machine Fuzzy System Problem
 * 
 * A washing machine works on the principle of Fuzzy System.
 * The weight of clothes put inside it for washing is uncertain.
 * Based on weight measured by sensors, it decides time and water level.
 * 
 * Water Level and Time Estimates:
 * - Zero weight: 0 minutes
 * - Low level (1-2000g): 25 minutes
 * - Medium level (2001-4000g): 35 minutes  
 * - High level (4001-7000g): 45 minutes
 * - Over 7000g: "OVERLOADED"
 * - Invalid inputs: "INVALID INPUT"
 * 
 * Machine capacity: Maximum 7000 grams
 */

import java.util.Scanner;

public class WashingMachine {
    
    public static String getTimeEstimate(int weight) {
        if (weight < 0) return "INVALID INPUT";
        if (weight == 0) return "0 minutes";
        if (weight > 7000) return "OVERLOADED";
        if (weight <= 2000) return "25 minutes";
        if (weight <= 4000) return "35 minutes";
        return "45 minutes";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int weight = sc.nextInt();
        System.out.println(getTimeEstimate(weight));
        sc.close();
    }
}
