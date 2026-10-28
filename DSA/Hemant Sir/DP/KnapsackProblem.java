package TIT.DP;

import java.util.Arrays;
import java.util.Comparator;

public class KnapsackProblem {
    
    public static void main(String[] args) {
        // 0/1 Knapsack Example
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;
        int n = values.length;
        
        System.out.println("=== 0/1 Knapsack ===");
        System.out.println("Values: " + Arrays.toString(values));
        System.out.println("Weights: " + Arrays.toString(weights));
        System.out.println("Capacity: " + capacity);
        System.out.println("Maximum Value: " + knapsack01(values, weights, capacity, n));
        
        System.out.println("\n=== Fractional Knapsack ===");
        // Fractional Knapsack Example
        Item[] items = {
            new Item(60, 10),
            new Item(100, 20),
            new Item(120, 30)
        };
        int fractionalCapacity = 50;
        
        System.out.println("Items:");
        for (Item item : items) {
            System.out.println("Value: " + item.value + ", Weight: " + item.weight + 
                             ", Ratio: " + item.getRatio());
        }
        System.out.println("Capacity: " + fractionalCapacity);
        System.out.println("Maximum Value: " + fractionalKnapsack(items, fractionalCapacity));
    }
    
    // 0/1 Knapsack using Dynamic Programming
    public static int knapsack01(int[] values, int[] weights, int capacity, int n) {
        int[][] dp = new int[n + 1][capacity + 1];
        
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], 
                                       dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        return dp[n][capacity];
    }
    
    // Fractional Knapsack using Greedy Approach
    public static double fractionalKnapsack(Item[] items, int capacity) {
        // Sort items by value/weight ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return Double.compare(item2.getRatio(), item1.getRatio());
            }
        });
        
        double totalValue = 0;
        int currentWeight = 0;
        
        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                // Take the whole item
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                // Take fractional part
                int remainingCapacity = capacity - currentWeight;
                totalValue += item.value * ((double) remainingCapacity / item.weight);
                break;
            }
        }
        
        return totalValue;
    }
    
    // Item class for Fractional Knapsack
    static class Item {
        int value;
        int weight;
        
        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
        
        public double getRatio() {
            return (double) value / weight;
        }
    }
}
