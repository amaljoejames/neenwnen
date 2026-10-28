import java.util.*;

/**
 * QUESTION: Greedy Assignment Problems - Implement remaining greedy algorithms including
 * Indian coins problem, Chocola problem, and other optimization problems.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Indian Coins Problem:
 * Given coin denominations and amount, find minimum number of coins needed.
 * Greedy approach works because Indian coin system is canonical.
 * 
 * 2. Chocola Problem:
 * Given chocolate bar with costs for horizontal and vertical cuts, find minimum cost.
 * Always make the most expensive cut first.
 * 
 * 3. Additional optimization problems from the original code.
 * 
 * APPROACH USED: Greedy with Sorting and Mathematical Analysis
 * 
 * PSEUDOCODE:
 * 
 * Indian Coins:
 * function indianCoins(coins, amount):
 *   sort coins in descending order
 *   count = 0
 *   for each coin in coins:
 *     while amount >= coin:
 *       count++
 *       amount -= coin
 *   return count
 * 
 * Chocola Problem:
 * function chocolaProblem(costVer, costHor):
 *   sort costVer in descending order
 *   sort costHor in descending order
 *   h = v = 0, hp = vp = 1, cost = 0
 *   
 *   while h < costHor.length && v < costVer.length:
 *     if costVer[v] < costHor[h]:
 *       cost += costHor[h] * vp
 *       hp++, h++
 *     else:
 *       cost += costVer[v] * hp
 *       vp++, v++
 *   
 *   while h < costHor.length:
 *     cost += costHor[h] * vp
 *     hp++, h++
 *   
 *   while v < costVer.length:
 *     cost += costVer[v] * hp
 *     vp++, v++
 *   
 *   return cost
 * 
 * EXPLANATION OF STEPS:
 * 1. Indian Coins: Use largest denominations first
 * 2. Chocola Problem: Make most expensive cuts first
 * 3. Time complexity: O(n log n) due to sorting
 * 
 * DRY RUN for Indian Coins:
 * 
 * Coins: [1, 2, 5, 10, 20, 50, 100, 200, 500], Amount = 590
 * 
 * Process:
 * 500: 590 >= 500, count = 1, amount = 90
 * 100: 90 < 100, skip
 * 50: 90 >= 50, count = 2, amount = 40
 * 20: 40 >= 20, count = 3, amount = 20
 * 20: 20 >= 20, count = 4, amount = 0
 * 
 * Final answer: 4 coins
 * 
 * DRY RUN for Chocola Problem:
 * 
 * Vertical cuts: [2, 1, 3, 1, 4], Horizontal cuts: [4, 1, 2]
 * 
 * Sorted: Ver = [4, 3, 2, 1, 1], Hor = [4, 2, 1]
 * 
 * Process:
 * h=0,v=0,costVer[0]=4,costHor[0]=4
 * 4 < 4? No, make vertical cut: cost += 4*1 = 4, vp=1,v=1
 * 
 * h=0,v=1,costVer[1]=3,costHor[0]=4
 * 3 < 4? Yes, make horizontal cut: cost += 4*1 = 8, hp=1,h=1
 * 
 * Continue until all cuts made
 * 
 * Final answer: Total cost calculated
 */
public class GreedyAssignment {
    
    // Indian coins problem using greedy
    public static void indianCoins(Integer[] coins, int amount) {
        Arrays.sort(coins, Collections.reverseOrder());
        int count = 0;
        
        for (int i = 0; i < coins.length; i++) {
            if (amount >= coins[i]) {
                while (amount >= coins[i]) {
                    count++;
                    amount -= coins[i];
                }
            }
        }
        System.out.println("Minimum coins needed: " + count);
    }

    // Chocola problem
    public static void chocolaProblem(Integer[] costVer, Integer[] costHor) {
        Arrays.sort(costVer, Collections.reverseOrder());
        Arrays.sort(costHor, Collections.reverseOrder());
        
        int h = 0, v = 0, hp = 1, vp = 1, cost = 0;
        
        while (h < costHor.length && v < costVer.length) {
            if (costVer[v] < costHor[h]) {
                // Horizontal cut is more expensive
                cost += costHor[h] * vp;
                hp++;
                h++;
            } else {
                // Vertical cut is more expensive
                cost += costVer[v] * hp;
                vp++;
                v++;
            }
        }
        
        while (h < costHor.length) {
            cost += costHor[h] * vp;
            hp++;
            h++;
        }
        
        while (v < costVer.length) {
            cost += costVer[v] * hp;
            vp++;
            v++;
        }
        
        System.out.println("Minimum cost to cut chocolate: " + cost);
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test both greedy assignment problems
         * 
         * Indian Coins: [1,2,5,10,20,50,100,200,500], amount = 590
         * Expected: 4 coins (500 + 50 + 20 + 20)
         * 
         * Chocola Problem: vertical=[2,1,3,1,4], horizontal=[4,1,2]
         * Expected: Minimum cost calculation
         */
        
        // Test Indian Coins
        Integer[] coins = {1, 2, 5, 10, 20, 50, 100, 200, 500};
        int amount = 590;
        
        System.out.println("Greedy Assignment - Indian Coins");
        System.out.println("Coins: [1,2,5,10,20,50,100,200,500], amount = 590");
        System.out.println("Expected: 4 coins (500 + 50 + 20 + 20)");
        
        indianCoins(coins, amount);
        
        // Show step-by-step for Indian Coins
        System.out.println("\nStep-by-step coin selection:");
        System.out.println("1. 500: 590 >= 500, take 1 coin, amount = 90");
        System.out.println("2. 100: 90 < 100, skip");
        System.out.println("3. 50: 90 >= 50, take 1 coin, amount = 40");
        System.out.println("4. 20: 40 >= 20, take 1 coin, amount = 20");
        System.out.println("5. 20: 20 >= 20, take 1 coin, amount = 0");
        System.out.println("Total coins: 4");
        
        // Test Chocola Problem
        Integer[] costVer = {2, 1, 3, 1, 4};
        Integer[] costHor = {4, 1, 2};
        
        System.out.println("\nChocola Problem:");
        System.out.println("Vertical cuts: [2,1,3,1,4], Horizontal cuts: [4,1,2]");
        System.out.println("Expected: Minimum cost calculation");
        
        chocolaProblem(costVer, costHor);
        
        // Show step-by-step for Chocola Problem
        System.out.println("\nStep-by-step cutting:");
        System.out.println("1. Sort descending: Ver=[4,3,2,1,1], Hor=[4,2,1]");
        System.out.println("2. Compare costs and make most expensive cut first");
        System.out.println("3. Track horizontal and vertical pieces");
        System.out.println("4. Calculate total cost");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Small amount
        Integer[] smallCoins = {1, 2, 5};
        int smallAmount = 7;
        System.out.println("Small amount:");
        System.out.println("Coins: [1,2,5], amount = 7");
        indianCoins(smallCoins, smallAmount);
        System.out.println("Expected: 2 coins (5 + 2)");
        
        // Test case 3: Simple chocolate
        Integer[] simpleVer = {1};
        Integer[] simpleHor = {1};
        System.out.println("Simple chocolate:");
        System.out.println("Vertical cuts: [1], Horizontal cuts: [1]");
        chocolaProblem(simpleVer, simpleHor);
        System.out.println("Expected: Cost = 2 (1*1 + 1*1)");
        
        // Test case 4: Large amount
        Integer[] largeCoins = {1, 5, 10, 25};
        int largeAmount = 100;
        System.out.println("Large amount:");
        System.out.println("Coins: [1,5,10,25], amount = 100");
        indianCoins(largeCoins, largeAmount);
        System.out.println("Expected: 4 coins (25 + 25 + 25 + 25)");
    }
}
