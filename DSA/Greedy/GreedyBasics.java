import java.util.*;

/**
 * QUESTION: Greedy Algorithms Basics - Implement fundamental greedy algorithms including
 * activity selection, knapsack, and difference pairs.
 * 
 * QUESTION EXPLANATION:
 * Greedy Algorithm: Makes locally optimal choice at each step with hope of finding
 * global optimum. Works best when local optimal choices lead to global optimum.
 * 
 * Algorithms covered:
 * 1. Activity Selection: Select maximum non-overlapping activities
 * 2. Fractional Knapsack: Maximize value with weight constraint
 * 3. Minimum Absolute Difference Pairs: Minimize sum of absolute differences
 * 
 * APPROACH USED: Greedy with Sorting
 * 
 * PSEUDOCODE:
 * 
 * Activity Selection:
 * function maxActivities(start[], end[]):
 *   activities = array of [start, end, id]
 *   sort activities by end time
 *   select activities greedily
 *   return count of selected activities
 * 
 * Fractional Knapsack:
 * function fractionalKnapsack(value[], weight[], capacity):
 *   items = array of [value, weight, ratio]
 *   sort items by value/weight ratio
 *   select items greedily
 *   return total value
 * 
 * Min Absolute Difference Pairs:
 * function minAbsoluteDifferencePairs(arr1[], arr2[]):
 *   sort both arrays
 *   pair elements to minimize sum of absolute differences
 *   return minimum sum
 * 
 * EXPLANATION OF STEPS:
 * 1. Activity Selection: Sort by end time, select non-overlapping
 * 2. Fractional Knapsack: Sort by value/weight ratio, take fractions
 * 3. Min Difference Pairs: Sort both arrays, pair optimally
 * 4. Time complexity varies by algorithm
 * 
 * DRY RUN for Activity Selection:
 * 
 * Activities: [1,2], [3,4], [0,6], [5,7], [8,9]
 * Sort by end time: [1,2], [3,4], [0,6], [5,7], [8,9]
 * Selection:
 * - Select [1,2], end at 2
 * - Select [3,4], end at 4 (overlaps with [1,2])
 * - Select [0,6], end at 6
 * - Select [5,7], end at 7
 * - Select [8,9], end at 9
 * Total: 4 activities
 */
public class GreedyBasics {
    
    // Maximum activities to be performed given start and end time
    public static void maxActivities(int[] start, int[] end) {
        // Sort activities by finish time
        int activities[][] = new int[start.length][3];
        for (int i = 0; i < start.length; i++) {
            activities[i][0] = i;
            activities[i][1] = start[i];
            activities[i][2] = end[i];
        }
        Arrays.sort(activities, Comparator.comparingDouble(o -> o[2]));
        
        ArrayList<Integer> ans = new ArrayList<>();
        int maxActivities = 1;
        ans.add(activities[0][0]);
        int lastEnd = activities[0][2];
        
        for (int i = 1; i < end.length; i++) {
            if (activities[i][1] >= lastEnd) {
                // Select activities
                maxActivities++;
                ans.add(activities[i][0]);
                lastEnd = activities[i][2];
            }
        }
        
        System.out.println("Max activities = " + maxActivities);
        for (int i = 0; i < ans.size(); i++) {
            System.out.print("a" + ans.get(i) + " ");
        }
        System.out.println();
    }

    // Fractional knapsack greedy problem
    public static void fractionalKnapsack(int[] value, int[] weight, int capacity) {
        // Sort items by value/weight ratio
        double ratio[][] = new double[value.length][2];
        for (int i = 0; i < value.length; i++) {
            ratio[i][0] = i;
            ratio[i][1] = (double) value[i] / (double) weight[i];
        }
        Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));
        
        int ans = 0;
        double remainingCapacity = capacity;
        
        for (int i = ratio.length - 1; i >= 0; i--) {
            int idx = (int) ratio[i][0];
            if (weight[idx] <= remainingCapacity) {
                // Select items
                ans += value[idx];
                remainingCapacity -= weight[idx];
            } else {
                // Select fraction of items
                ans += ratio[i][1] * remainingCapacity;
            }
            System.out.println("Total value = " + ans);
        }
    }

    // Minimum absolute difference pairs problem
    public static void minAbsoluteDifferencePairs(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        int minDiff = 0;
        for (int i = 0; i < arr1.length; i++) {
            minDiff += Math.abs(arr1[i] - arr2[i]);
        }
        System.out.println("Min absolute difference = " + minDiff);
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all three greedy algorithms
         * 
         * Activity Selection: [1,2], [3,4], [0,6], [5,7], [8,9]
         * Expected: 4 activities selected
         * 
         * Fractional Knapsack: values=[60,100,120], weights=[10,20,30], capacity=50
         * Expected: optimal value calculation
         * 
         * Min Difference Pairs: arr1=[1,2,3], arr2=[2,1,3]
         * Expected: minimize sum of absolute differences
         */
        
        // Test Activity Selection
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        
        System.out.println("Greedy Algorithms - Activity Selection");
        System.out.println("Activities: [1,2], [3,4], [0,6], [5,7], [8,9]");
        System.out.println("Expected: Select 4 activities");
        
        maxActivities(start, end);
        
        // Show step-by-step for activity selection
        System.out.println("\nStep-by-step activity selection:");
        System.out.println("1. Sort by end time: [1,2], [3,4], [0,6], [5,7], [8,9]");
        System.out.println("2. Select [1,2] (ends at 2)");
        System.out.println("3. Skip [3,4] (conflicts with [1,2])");
        System.out.println("4. Select [0,6] (ends at 6)");
        System.out.println("5. Select [5,7] (ends at 7)");
        System.out.println("6. Select [8,9] (ends at 9)");
        System.out.println("Total selected: 4 activities");
        
        // Test Fractional Knapsack
        int[] value = {60, 100, 120};
        int[] weight = {10, 20, 30};
        int capacity = 50;
        
        System.out.println("\nFractional Knapsack:");
        System.out.println("Values: [60,100,120], Weights: [10,20,30], Capacity: 50");
        
        fractionalKnapsack(value, weight, capacity);
        
        // Test Minimum Absolute Difference Pairs
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {2, 1, 3};
        
        System.out.println("\nMinimum Absolute Difference Pairs:");
        System.out.println("Array1: [1,2,3], Array2: [2,1,3]");
        System.out.println("Expected: Pair to minimize differences");
        
        minAbsoluteDifferencePairs(arr1, arr2);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single activity
        int[] singleStart = {1};
        int[] singleEnd = {2};
        System.out.println("Single activity:");
        maxActivities(singleStart, singleEnd);
        
        // Test case 3: Empty arrays
        int[] empty1 = {};
        int[] empty2 = {};
        System.out.println("Empty arrays:");
        minAbsoluteDifferencePairs(empty1, empty2);
    }
}
