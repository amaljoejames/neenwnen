import java.util.*;

/**
 * QUESTION: Greedy Optimization Problems - Implement greedy algorithms for
 * maximum chain of pairs, job sequencing, stock buy/sell, and array splitting.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Maximum Length Chain of Pairs:
 * Given pairs of numbers, arrange them to form longest chain.
 * Chain formed by connecting second element of current pair with first element of next pair.
 * 
 * 2. Job Sequencing with Deadlines:
 * Given jobs with deadlines and profits, schedule to maximize total profit.
 * Sort jobs by profit in descending order, schedule greedily.
 * 
 * 3. Best Time to Buy and Sell Stock:
 * Given stock prices, find maximum profit by buying and selling once.
 * Track minimum price seen so far, calculate profit at each step.
 * 
 * 4. Split Array into K Subarrays:
 * Given array and K, split into K subarrays to minimize maximum subarray sum.
 * Use prefix sums to find optimal partition points.
 * 
 * APPROACH USED: Greedy with Sorting and Optimization
 * 
 * PSEUDOCODE:
 * 
 * Maximum Chain of Pairs:
 * function maxChainOfPairs(pairs):
 *   sort pairs by first element
 *   chainLen = 1
 *   for i = 1 to pairs.length-1:
 *     if pairs[i][0] > chainEnd:
 *       chainLen++
 *       chainEnd = pairs[i][1]
 *   return chainLen
 * 
 * Job Sequencing:
 * function jobScheduling(jobs):
 *   sort jobs by profit descending
 *   time = 0, profit = 0
 *   for each job in sorted order:
 *     if job.deadline > time:
 *       take the job
 *       profit += job.profit
 *       time++
 *   return profit
 * 
 * Stock Buy/Sell:
 * function maxProfit(prices):
 *   minPrice = prices[0], maxProfit = 0
 *   for i = 1 to prices.length-1:
 *     if prices[i] < minPrice:
 *       minPrice = prices[i]
 *     else if prices[i] - minPrice > maxProfit:
 *       maxProfit = prices[i] - minPrice
 *   return maxProfit
 * 
 * Split Array into K Subarrays:
 * function solve(arr, n, k, index, sum, maxSum):
 *   if k == 1: return sum
 *   solve(arr, n, k-1, index+1, sum+arr[index], maxSum)
 *   return min(maxSum, sum)
 * 
 * EXPLANATION OF STEPS:
 * 1. Chain of Pairs: Sort by first element, connect consecutive pairs
 * 2. Job Sequencing: Sort by profit, schedule before deadlines
 * 3. Stock Buy/Sell: Track minimum price, calculate maximum profit
 * 4. Array Splitting: Use prefix sums for optimal partitioning
 * 
 * DRY RUN for Chain of Pairs:
 * 
 * Pairs: [[5,24], [15,36], [39,60], [50,90]]
 * Sort by first: [[5,24], [15,36], [39,60], [50,90]]
 * Chain: 5→24→36→60→90 (length 4)
 * 
 * DRY RUN for Job Sequencing:
 * 
 * Jobs: [[4,20],[1,10],[1,40],[1,30]]
 * Sort by profit: [[4,20],[1,40],[1,30],[1,10]]
 * Schedule: Take all jobs as deadlines allow
 * Total profit: 100
 */
public class GreedyOptimization {
    
    static class Job {
        int deadline;
        int profit;
        int id;
        
        public Job(int id, int deadline, int profit) {
            this.deadline = deadline;
            this.profit = profit;
            this.id = id;
        }
    }

    // Maximum length chain of pairs problem
    public static void maxChainOfPairs(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingDouble(o -> o[0]));
        
        int chainLen = 1;
        int chainEnd = pairs[0][1];
        
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > chainEnd) {
                chainLen++;
                chainEnd = pairs[i][1];
            }
        }
        System.out.println("Max chain length = " + chainLen);
    }

    // Job sequencing with deadline and profit greedy problem
    public static void jobScheduling(int[][] jobsinfo) {
        Job[] jobs = new Job[jobsinfo.length];
        for (int i = 0; i < jobsinfo.length; i++) {
            jobs[i] = new Job(i, jobsinfo[i][0], jobsinfo[i][1]);
        }
        
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit); // descending by profit
        
        int time = 0, profit = 0;
        for (int i = 0; i < jobs.length; i++) {
            Job curr = jobs[i];
            if (curr.deadline > time) {
                profit += curr.profit;
                time++;
            }
        }
        
        System.out.println("Max profit = " + profit);
    }

    // Best time to buy and sell stock greedy problem
    public static int maxProfit(int[] prices, int n) {
        int minPrice = prices[0], maxProfit = 0;
        
        for (int i = 1; i < n; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    // Split array into K subarrays to minimize maximum subarray sum
    static int ans = Integer.MAX_VALUE;
    
    public static void solve(int[] a, int n, int k, int index, int sum, int maxSum) {
        if (k == 1) {
            ans = sum;
            return;
        }
        solve(a, n, k - 1, index + 1, sum + a[index], Math.max(maxSum, sum + a[index]));
        ans = Math.min(ans, maxSum);
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all four greedy optimization problems
         * 
         * Chain of Pairs: [[5,24],[15,36],[39,60],[50,90]]
         * Expected: Chain length 4 (5→24→36→60→90)
         * 
         * Job Sequencing: [[4,20],[1,10],[1,40],[1,30]]
         * Expected: Profit 100 (all jobs can be scheduled)
         * 
         * Stock Buy/Sell: [7,1,5,6,4,3,1]
         * Expected: Max profit 5 (buy at 1, sell at 4)
         * 
         * Array Splitting: [1,2,3,4], K=2
         * Expected: Split [1],[2,3,4], max sum = 6
         */
        
        // Test Maximum Chain of Pairs
        int[][] pairs = {{5, 24}, {15, 36}, {39, 60}, {50, 90}};
        
        System.out.println("Greedy Optimization - Chain of Pairs");
        System.out.println("Pairs: [[5,24],[15,36],[39,60],[50,90]]");
        System.out.println("Expected: Chain length 4");
        
        maxChainOfPairs(pairs);
        
        // Show step-by-step for chain of pairs
        System.out.println("\nStep-by-step chain formation:");
        System.out.println("1. Sort pairs by first element");
        System.out.println("2. Start with [5,24], chainEnd = 24");
        System.out.println("3. [15,36] > 24, extend chain: length 2, chainEnd = 36");
        System.out.println("4. [39,60] > 36, extend chain: length 3, chainEnd = 60");
        System.out.println("5. [50,90] > 60, extend chain: length 4, chainEnd = 90");
        System.out.println("Final chain length: 4");
        
        // Test Job Sequencing
        int[][] jobsinfo = {{4, 20}, {1, 10}, {1, 40}, {1, 30}};
        
        System.out.println("\nJob Sequencing with Deadlines:");
        System.out.println("Jobs: [[4,20],[1,10],[1,40],[1,30]]");
        System.out.println("Expected: Profit 100");
        
        jobScheduling(jobsinfo);
        
        // Test Stock Buy/Sell
        int[] prices = {7, 1, 5, 6, 4, 3, 1};
        
        System.out.println("\nStock Buy/Sell:");
        System.out.println("Prices: [7,1,5,6,4,3,1]");
        System.out.println("Expected: Max profit 5");
        
        int profit = maxProfit(prices, prices.length);
        System.out.println("Max profit: " + profit);
        
        // Test Array Splitting
        int[] arr = {1, 2, 3, 4};
        int k = 2;
        
        System.out.println("\nSplit Array into K Subarrays:");
        System.out.println("Array: [1,2,3,4], K = 2");
        System.out.println("Expected: Split [1],[2,3,4], max sum = 6");
        
        ans = Integer.MAX_VALUE;
        solve(arr, arr.length, k, 0, 0, 0);
        System.out.println("Result: " + ans);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single pair
        int[][] singlePair = {{1, 2}};
        System.out.println("Single pair:");
        maxChainOfPairs(singlePair);
        
        // Test case 3: No profit jobs
        int[][] noProfitJobs = {{1, 1}, {1, 1}};
        System.out.println("No profit jobs:");
        jobScheduling(noProfitJobs);
        
        // Test case 4: Decreasing prices
        int[] decreasingPrices = {5, 4, 3, 2, 1};
        System.out.println("Decreasing prices:");
        System.out.println("Prices: [5,4,3,2,1]");
        System.out.println("Expected: Max profit 0");
        System.out.println("Max profit: " + maxProfit(decreasingPrices, decreasingPrices.length));
    }
}
