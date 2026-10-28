import java.util.*;

/**
 * QUESTION: Heaps Applications - Implement practical heap algorithms including
 * nearby cars, connect ropes, weakest soldier, and sliding window maximum.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Nearby Cars Problem:
 * Given coordinates of cars, find K nearest cars to origin (0,0).
 * Use min heap based on distance squared from origin.
 * 
 * 2. Connect Ropes Problem:
 * Given rope lengths, connect them with minimum cost.
 * Cost = sum of two ropes being connected, use min heap.
 * 
 * 3. Weakest Soldier Problem:
 * Given matrix with soldiers (1) and civilians (0), find K weakest rows.
 * Count soldiers per row, use min heap based on count then index.
 * 
 * 4. Sliding Window Maximum:
 * Given array and window size K, find maximum in each window.
 * Use max heap to track maximum elements efficiently.
 * 
 * APPROACH USED: Priority Queue with Custom Comparable Classes
 * 
 * PSEUDOCODE:
 * 
 * Nearby Cars:
 * function nearbyCars(dist, k):
 *   pq = PriorityQueue<Point>()
 *   for i = 0 to dist.length-1:
 *     distSq = dist[i][0]^2 + dist[i][1]^2
 *     pq.add(Point(dist[i][0], dist[i][1], distSq, i))
 *   
 *   for i = 0 to k-1:
 *     print pq.remove().idx
 * 
 * Connect Ropes:
 * function connectRopes(ropes):
 *   pq = PriorityQueue<Integer>()
 *   for rope in ropes:
 *     pq.add(rope)
 *   
 *   cost = 0
 *   while pq.size() > 1:
 *     min1 = pq.remove()
 *     min2 = pq.remove()
 *     cost += min1 + min2
 *     pq.add(min1 + min2)
 *   
 *   return cost
 * 
 * Weakest Soldier:
 * function weakestSoldier(army, k):
 *   pq = PriorityQueue<Soldiers>()
 *   for i = 0 to army.length-1:
 *     count = 0
 *     for j = 0 to army[0].length-1:
 *       count += army[i][j] == 1 ? 1 : 0
 *     pq.add(Soldiers(count, i))
 *   
 *   for i = 0 to k-1:
 *     print pq.remove().idx
 * 
 * Sliding Window Maximum:
 * function slidingMaxWindow(arr, k):
 *   res = new int[arr.length - k + 1]
 *   pq = PriorityQueue<Pair>()
 *   
 *   for i = 0 to k-1:
 *     pq.add(Pair(arr[i], i))
 *   
 *   res[0] = pq.peek().val
 *   
 *   for i = k to arr.length-1:
 *     while !pq.isEmpty() && pq.peek().idx <= i-k:
 *       pq.remove()
 *     pq.add(Pair(arr[i], i))
 *     res[i-k+1] = pq.peek().val
 *   
 *   return res
 * 
 * EXPLANATION OF STEPS:
 * 1. Use priority queue for efficient min/max operations
 * 2. Custom comparable classes for complex data structures
 * 3. Time complexity: O(n log k) for most operations
 * 
 * DRY RUN for Nearby Cars:
 * 
 * Cars: [[3,3], [5,-1], [-2,4]], K = 2
 * 
 * Distance calculations:
 * Car 0: 3^2 + 3^2 = 9 + 9 = 18
 * Car 1: 5^2 + (-1)^2 = 25 + 1 = 26
 * Car 2: (-2)^2 + 4^2 = 4 + 16 = 20
 * 
 * Min heap order: Car 0(18), Car 2(20), Car 1(26)
 * 
 * K = 2: Car 0, Car 2
 * 
 * DRY RUN for Connect Ropes:
 * 
 * Ropes: [2,3,3,4,6]
 * 
 * Process:
 * pq = [2,3,3,4,6]
 * 
 * Step 1: min1=2, min2=3, cost=5, pq=[3,4,5,6]
 * Step 2: min1=3, min2=4, cost=12, pq=[5,6,7]
 * Step 3: min1=5, min2=6, cost=23, pq=[7,12]
 * Step 4: min1=7, min2=12, cost=42, pq=[19]
 * 
 * Final cost: 42
 */
public class HeapsApplications {
    
    static class Point implements Comparable<Point> {
        int x, y;
        int distSq;
        int idx;
        
        public Point(int x, int y, int distSq, int idx) {
            this.x = x;
            this.y = y;
            this.distSq = distSq;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Point o) {
            return this.distSq - o.distSq;
        }
    }
    
    static class Soldiers implements Comparable<Soldiers> {
        int soldier;
        int idx;
        
        public Soldiers(int soldier, int idx) {
            this.soldier = soldier;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Soldiers o) {
            if (this.soldier == o.soldier) {
                return this.idx - o.idx;
            } else {
                return this.soldier - o.soldier;
            }
        }
    }
    
    static class Pair implements Comparable<Pair> {
        int val, idx;
        
        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Pair o) {
            // descending order for max heap
            return o.val - this.val;
        }
    }
    
    // Nearby cars problem
    public static void nearbyCars(int dist[][], int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        
        for (int i = 0; i < dist.length; i++) {
            int distSq = (dist[i][0]) * (dist[i][0]) + (dist[i][1]) * (dist[i][1]);
            pq.add(new Point(dist[i][0], dist[i][1], distSq, i));
        }
        
        // nearest k cars
        System.out.println("Nearest " + k + " cars:");
        for (int i = 0; i < k; i++) {
            System.out.println("Car " + pq.remove().idx);
        }
    }
    
    // Connect n ropes problem
    public static void connectRopes(int ropes[]) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < ropes.length; i++) {
            pq.add(ropes[i]);
        }
        
        int cost = 0;
        while (pq.size() > 1) {
            int min1 = pq.remove();
            int min2 = pq.remove();
            cost += min1 + min2;
            pq.add(min1 + min2);
        }
        
        System.out.println("Minimum cost to connect ropes: " + cost);
    }
    
    // Weakest soldier
    public static void weakestSoldier(int army[][], int k) {
        PriorityQueue<Soldiers> pq = new PriorityQueue<>();
        
        for (int i = 0; i < army.length; i++) {
            int count = 0;
            for (int j = 0; j < army[0].length; j++) {
                count += army[i][j] == 1 ? 1 : 0;
            }
            pq.add(new Soldiers(count, i));
        }
        
        System.out.println("Weakest " + k + " soldiers:");
        for (int i = 0; i < k; i++) {
            System.out.println("Row " + pq.remove().idx);
        }
    }
    
    // Sliding window maximum problem
    public static void slidingMaxWindow(int arr[], int k) {
        int res[] = new int[arr.length - k + 1];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        
        for (int i = 0; i < k; i++) {
            pq.add(new Pair(arr[i], i));
        }
        
        res[0] = pq.peek().val;
        
        for (int i = k; i < arr.length; i++) {
            while (!pq.isEmpty() && pq.peek().idx <= i - k) {
                pq.remove();
            }
            pq.add(new Pair(arr[i], i));
            res[i - k + 1] = pq.peek().val;
        }
        
        System.out.println("Sliding window maximum:");
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all heap applications
         * 
         * Nearby Cars: [[3,3],[5,-1],[-2,4]], K=2
         * Expected: Cars 0 and 2 (distances 18, 20)
         * 
         * Connect Ropes: [2,3,3,4,6]
         * Expected: Cost 42
         * 
         * Weakest Soldier: [[1,0,0,0],[1,1,1,1],[1,0,0,0],[1,0,0,0]], K=2
         * Expected: Rows 2 and 3 (1 soldier each)
         * 
         * Sliding Window: [1,3,-1,-3,5,3,6,7], K=3
         * Expected: [3,3,5,5,6,7]
         */
        
        // Test Nearby Cars
        int dist[][] = {{3, 3}, {5, -1}, {-2, 4}};
        int k = 2;
        
        System.out.println("Heaps Applications - Nearby Cars");
        System.out.println("Cars: [3,3], [5,-1], [-2,4]");
        System.out.println("Expected: Cars 0 and 2 (nearest)");
        
        nearbyCars(dist, k);
        
        // Test Connect Ropes
        int ropes[] = {2, 3, 3, 4, 6};
        
        System.out.println("\nConnect Ropes:");
        System.out.println("Ropes: [2,3,3,4,6]");
        System.out.println("Expected: Minimum cost 42");
        
        connectRopes(ropes);
        
        // Test Weakest Soldier
        int army[][] = {{1, 0, 0, 0}, {1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        int rows = 2;
        
        System.out.println("\nWeakest Soldier:");
        System.out.println("Army: [[1,0,0,0],[1,1,1,1],[1,0,0,0],[1,0,0,0]]");
        System.out.println("Expected: Rows 2 and 3 (1 soldier each)");
        
        weakestSoldier(army, rows);
        
        // Test Sliding Window Maximum
        int window[] = {1, 3, -1, -3, 5, 3, 6, 7};
        int para = 3;
        
        System.out.println("\nSliding Window Maximum:");
        System.out.println("Array: [1,3,-1,-3,5,3,6,7], K=3");
        System.out.println("Expected: [3,3,5,5,6,7]");
        
        slidingMaxWindow(window, para);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single car
        int[][] singleCar = {{0, 0}};
        System.out.println("Single car:");
        nearbyCars(singleCar, 1);
        
        // Test case 3: Two ropes
        int[] twoRopes = {1, 2};
        System.out.println("Two ropes:");
        connectRopes(twoRopes);
        
        // Test case 4: Single window
        int[] singleWindow = {5};
        System.out.println("Single window:");
        slidingMaxWindow(singleWindow, 1);
    }
}
