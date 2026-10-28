import java.util.*;

/**
 * QUESTION: Buy and Sell Stocks - Given an array of stock prices where prices[i] is the price 
 * of a given stock on the ith day, find the maximum profit you can achieve from one transaction.
 * If you cannot achieve any profit, return 0.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Prices: [7, 1, 5, 3, 6, 4]
 * - Buy at day 1 (price = 1), sell at day 4 (price = 6)
 * - Profit = 6 - 1 = 5
 * - Other possible profits:
 *   - Buy at day 1, sell at day 2: 5 - 1 = 4
 *   - Buy at day 1, sell at day 3: 3 - 1 = 2
 *   - Buy at day 0, sell at day 2: 5 - 7 = -2 (loss)
 * - Maximum profit: 5
 * 
 * APPROACH USED: Single Pass with Minimum Tracking
 * 
 * PSEUDOCODE:
 * function buyAndSellStock(prices):
 *   buyPrice = Integer.MAX_VALUE
 *   maxProfit = 0
 *   
 *   for i = 0 to prices.length-1:
 *     if buyPrice < prices[i]:
 *       profit = prices[i] - buyPrice
 *       maxProfit = max(maxProfit, profit)
 *     else:
 *       buyPrice = prices[i]
 *   
 *   return maxProfit
 * 
 * EXPLANATION OF STEPS:
 * 1. Initialize buyPrice to maximum value, maxProfit to 0
 * 2. For each price:
 *    - If current price > buyPrice → potential profit
 *    - Calculate profit and update maxProfit if better
 *    - If current price <= buyPrice → update buyPrice
 * 3. Always track the minimum price seen so far
 * 4. Maximum profit is the best buy-sell combination
 * 5. Time complexity: O(n), Space complexity: O(1)
 * 
 * DRY RUN for prices = [7, 1, 5, 3, 6, 4]:
 * 
 * i = 0: price = 7
 * - buyPrice = 7 (7 <= MAX_VALUE)
 * - maxProfit = 0
 * 
 * i = 1: price = 1
 * - buyPrice = 1 (1 <= 7)
 * - maxProfit = 0
 * 
 * i = 2: price = 5
 * - profit = 5 - 1 = 4
 * - maxProfit = max(0, 4) = 4
 * 
 * i = 3: price = 3
 * - profit = 3 - 1 = 2
 * - maxProfit = max(4, 2) = 4
 * 
 * i = 4: price = 6
 * - profit = 6 - 1 = 5
 * - maxProfit = max(4, 5) = 5
 * 
 * i = 5: price = 4
 * - profit = 4 - 1 = 3
 * - maxProfit = max(5, 3) = 5
 * 
 * Final maxProfit = 5
 */
public class BuyAndSellStocks {
    
    public static int buyAndSellStock(int prices[]) {
        int buyPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int i = 0; i < prices.length; i++) {
            if (buyPrice < prices[i]) {
                // Calculate profit if we sell today
                int profit = prices[i] - buyPrice;
                maxProfit = Math.max(maxProfit, profit);
            } else {
                // Update buy price to current price (better buying opportunity)
                buyPrice = prices[i];
            }
        }
        
        return maxProfit;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: prices = [7, 1, 5, 3, 6, 4]
         * Expected result: 5 (buy at 1, sell at 6)
         * 
         * Step-by-step process:
         * 
         * i = 0: price = 7, buyPrice = 7, maxProfit = 0
         * i = 1: price = 1, buyPrice = 1, maxProfit = 0
         * i = 2: price = 5, profit = 4, maxProfit = 4
         * i = 3: price = 3, profit = 2, maxProfit = 4
         * i = 4: price = 6, profit = 5, maxProfit = 5
         * i = 5: price = 4, profit = 3, maxProfit = 5
         * 
         * Final maxProfit = 5
         */
        
        int[] prices = {7, 1, 5, 3, 6, 4};
        
        System.out.println("Buy and Sell Stocks Problem");
        System.out.println("Prices: " + Arrays.toString(prices));
        System.out.println("Expected result: 5 (buy at 1, sell at 6)");
        
        int result = buyAndSellStock(prices);
        System.out.println("Maximum profit: " + result);
        
        // Show detailed step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("Prices: " + Arrays.toString(prices));
        
        int buyPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int i = 0; i < prices.length; i++) {
            System.out.println("Day " + i + ": price = " + prices[i]);
            System.out.println("  Current buyPrice: " + buyPrice + ", maxProfit: " + maxProfit);
            
            if (buyPrice < prices[i]) {
                int profit = prices[i] - buyPrice;
                maxProfit = Math.max(maxProfit, profit);
                System.out.println("  → Profit if sold today: " + profit + ", maxProfit updated to " + maxProfit);
            } else {
                buyPrice = prices[i];
                System.out.println("  → Better buying price found, buyPrice updated to " + buyPrice);
            }
            System.out.println();
        }
        
        System.out.println("Final maximum profit: " + maxProfit);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        int[] test2 = {7, 6, 4, 3, 1};
        System.out.println("Test 2 (decreasing prices): " + Arrays.toString(test2));
        System.out.println("Maximum profit: " + buyAndSellStock(test2));
        
        int[] test3 = {1, 2, 3, 4, 5};
        System.out.println("Test 3 (increasing prices): " + Arrays.toString(test3));
        System.out.println("Maximum profit: " + buyAndSellStock(test3));
        
        int[] test4 = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println("Test 4: " + Arrays.toString(test4));
        System.out.println("Maximum profit: " + buyAndSellStock(test4));
        
        int[] test5 = {1};
        System.out.println("Test 5 (single day): " + Arrays.toString(test5));
        System.out.println("Maximum profit: " + buyAndSellStock(test5));
    }
}
