package TIT.DP;

import java.util.Arrays;

public class CoinChangeProblem {
    
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        
        System.out.println("=== Coin Change Problem ===");
        System.out.println("Coins: " + Arrays.toString(coins));
        System.out.println("Amount: " + amount);
        
        System.out.println("\n--- Minimum Number of Coins ---");
        int minCoins = minCoins(coins, amount);
        if (minCoins == Integer.MAX_VALUE) {
            System.out.println("Not possible to make amount " + amount);
        } else {
            System.out.println("Minimum coins needed: " + minCoins);
        }
        
        System.out.println("\n--- Number of Ways to Make Change ---");
        int ways = countWays(coins, amount);
        System.out.println("Number of ways: " + ways);
        
        System.out.println("\n--- Coin Change with Combinations ---");
        printCoinCombinations(coins, amount);
    }
    
    // Minimum number of coins needed to make the amount
    public static int minCoins(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount];
    }
    
    // Number of ways to make the amount
    public static int countWays(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        
        return dp[amount];
    }
    
    // Print one combination of coins that makes the amount
    public static void printCoinCombinations(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int[] parent = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    if (dp[i - coin] + 1 < dp[i]) {
                        dp[i] = dp[i - coin] + 1;
                        parent[i] = coin;
                    }
                }
            }
        }
        
        if (dp[amount] == Integer.MAX_VALUE) {
            System.out.println("No combination possible");
            return;
        }
        
        System.out.print("One optimal combination: ");
        int current = amount;
        while (current > 0 && parent[current] != -1) {
            System.out.print(parent[current] + " ");
            current -= parent[current];
        }
        System.out.println();
    }
    
    // Recursive solution with memoization (for understanding)
    public static int minCoinsRecursive(int[] coins, int amount, int[] memo) {
        if (amount == 0) return 0;
        if (amount < 0) return Integer.MAX_VALUE;
        if (memo[amount] != -1) return memo[amount];
        
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = minCoinsRecursive(coins, amount - coin, memo);
            if (result != Integer.MAX_VALUE) {
                min = Math.min(min, result + 1);
            }
        }
        
        memo[amount] = min;
        return min;
    }
}
