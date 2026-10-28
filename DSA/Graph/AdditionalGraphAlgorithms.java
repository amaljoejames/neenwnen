import java.util.*;

/**
 * QUESTION: Additional Graph Algorithms - Implement missing advanced graph algorithms
 * including Cheapest Flights, Connecting Cities, Rotten Oranges, Largest Region, etc.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Cheapest Flights Within K Stops:
 * Find cheapest flight from source to destination with at most K stops.
 * Use modified Dijkstra's algorithm with stops constraint.
 * 
 * 2. Connecting Cities with Minimum Cost:
 * Connect all cities with minimum cost using MST approach.
 * Kruskal's algorithm with Union-Find data structure.
 * 
 * 3. Minimum Time to Rot All Oranges:
 * BFS from rotten oranges to spread rot to fresh oranges.
 * Calculate time for all oranges to become rotten.
 * 
 * 4. Largest Region in Boolean Matrix:
 * Find largest connected region of 1s in 2D boolean matrix.
 * Use DFS/BFS to explore connected components.
 * 
 * 5. Word Ladder (Shortest Chain Length):
 * Find shortest transformation sequence from beginWord to endWord.
 * Each step changes exactly one character.
 * 
 * APPROACH USED: BFS, Union-Find, and DFS Algorithms
 * 
 * PSEUDOCODE:
 * 
 * Cheapest Flights:
 * function findCheapestPrice(n, flights, src, dst, k):
 *   adj = List[List[Pair]]()
 *   for flight in flights:
 *     adj[flight[0]].add([flight[1], flight[2]])
 *   
 *   pq = PriorityQueue<[cost, node, stops]>()
 *   pq.add([0, src, 0])
 *   
 *   while !pq.isEmpty():
 *     [cost, node, stops] = pq.poll()
 *     if node == dst: return cost
 *     if stops > k: continue
 *     
 *     for [next, price] in adj[node]:
 *       pq.add([cost + price, next, stops + 1])
 *   
 *   return -1
 * 
 * Connecting Cities:
 * function connectCities(n, connections):
 *   edges = List<[cost, u, v]]()
 *   for [u, v, cost] in connections:
 *     edges.add([cost, u, v])
 *   
 *   sort edges by cost
 *   uf = UnionFind(n)
 *   totalCost = 0
 *   
 *   for [cost, u, v] in edges:
 *     if uf.find(u) != uf.find(v):
 *       uf.union(u, v)
 *       totalCost += cost
 *   
 *   return totalCost
 * 
 * Rotten Oranges:
 * function rotOranges(grid):
 *   queue = Queue<[x, y, time]>()
 *   fresh = 0
 *   
 *   for i in 0 to n-1:
 *     for j in 0 to m-1:
 *       if grid[i][j] == 2: queue.add([i, j, 0])
 *       if grid[i][j] == 1: fresh++
 *   
 *   time = 0
 *   while !queue.isEmpty():
 *     [x, y, t] = queue.poll()
 *     time = t
 *     
 *     for [dx, dy] in directions:
 *       nx = x + dx, ny = y + dy
 *       if valid(nx, ny) and grid[nx][ny] == 1:
 *         grid[nx][ny] = 2
 *         fresh--
 *         queue.add([nx, ny, t + 1])
 *   
 *   return fresh == 0 ? time : -1
 * 
 * EXPLANATION OF STEPS:
 * 1. Cheapest Flights: Modified Dijkstra with stops tracking
 * 2. Connecting Cities: Kruskal's MST with Union-Find
 * 3. Rotten Oranges: Multi-source BFS for rotting process
 * 4. Largest Region: DFS/BFS for connected components
 * 5. Word Ladder: BFS with word transformation
 */
public class AdditionalGraphAlgorithms {
    
    // Union-Find for Kruskal's algorithm
    static class UnionFind {
        int[] parent;
        int[] rank;
        
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    // Cheapest Flights Within K Stops
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, src, 0});
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int cost = current[0];
            int node = current[1];
            int stops = current[2];
            
            if (node == dst) {
                return cost;
            }
            
            if (stops > k) {
                continue;
            }
            
            for (int[] neighbor : adj.get(node)) {
                pq.offer(new int[]{cost + neighbor[1], neighbor[0], stops + 1});
            }
        }
        
        return -1;
    }

    // Connecting Cities with Minimum Cost (Kruskal's MST)
    public static int connectCities(int n, int[][] connections) {
        List<int[]> edges = new ArrayList<>();
        for (int[] conn : connections) {
            edges.add(new int[]{conn[2], conn[0], conn[1]});
        }
        
        edges.sort((a, b) -> a[0] - b[0]);
        
        UnionFind uf = new UnionFind(n);
        int totalCost = 0;
        
        for (int[] edge : edges) {
            int cost = edge[0];
            int u = edge[1];
            int v = edge[2];
            
            if (uf.find(u) != uf.find(v)) {
                uf.union(u, v);
                totalCost += cost;
            }
        }
        
        return totalCost;
    }

    // Minimum Time to Rot All Oranges
    public static int rotOranges(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j, 0});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        
        int time = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int t = current[2];
            time = t;
            
            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 1) {
                    grid[nx][ny] = 2;
                    fresh--;
                    queue.offer(new int[]{nx, ny, t + 1});
                }
            }
        }
        
        return fresh == 0 ? time : -1;
    }

    // Largest Region in Boolean Matrix
    public static int largestRegion(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int maxSize = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int size = dfs(grid, i, j, visited);
                    maxSize = Math.max(maxSize, size);
                }
            }
        }
        
        return maxSize;
    }
    
    private static int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || 
            grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }
        
        visited[i][j] = true;
        int size = 1;
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1},
                             {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        
        for (int[] dir : directions) {
            size += dfs(grid, i + dir[0], j + dir[1], visited);
        }
        
        return size;
    }

    // Word Ladder - Shortest Chain Length
    public static int shortestChainLen(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                
                if (current.equals(endWord)) {
                    return level;
                }
                
                char[] chars = current.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        
                        chars[j] = c;
                        String transformed = new String(chars);
                        
                        if (wordSet.contains(transformed)) {
                            wordSet.remove(transformed);
                            queue.offer(transformed);
                        }
                    }
                    
                    chars[j] = original;
                }
            }
            level++;
        }
        
        return 0;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all additional graph algorithms
         * 
         * Cheapest Flights: n=3, flights=[[0,1,100],[1,2,100],[0,2,500]], src=0, dst=2, k=1
         * Expected: 200 (0->1->2)
         * 
         * Connecting Cities: n=3, connections=[[0,1,5],[1,2,3],[0,2,6]]
         * Expected: 8 (0->1->2)
         * 
         * Rotten Oranges: [[2,1,1],[1,1,0],[0,1,1]]
         * Expected: 2
         */
        
        // Test Cheapest Flights
        int n = 3;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        System.out.println("Cheapest Flights:");
        System.out.println("n=3, flights=[[0,1,100],[1,2,100],[0,2,500]], src=0, dst=2, k=1");
        System.out.println("Expected: 200 (0->1->2)");
        int cheapest = findCheapestPrice(n, flights, 0, 2, 1);
        System.out.println("Result: " + cheapest);
        
        // Test Connecting Cities
        int[][] connections = {{0, 1, 5}, {1, 2, 3}, {0, 2, 6}};
        System.out.println("\nConnecting Cities:");
        System.out.println("n=3, connections=[[0,1,5],[1,2,3],[0,2,6]]");
        System.out.println("Expected: 8 (0->1->2)");
        int minCost = connectCities(3, connections);
        System.out.println("Result: " + minCost);
        
        // Test Rotten Oranges
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println("\nRotten Oranges:");
        System.out.println("Grid: [[2,1,1],[1,1,0],[0,1,1]]");
        System.out.println("Expected: 2");
        int time = rotOranges(grid);
        System.out.println("Result: " + time);
        
        // Test Largest Region
        int[][] regionGrid = {{1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 1, 0}, {1, 0, 0, 0}};
        System.out.println("\nLargest Region:");
        System.out.println("Grid: [[1,1,0,0],[0,1,1,0],[0,0,1,0],[1,0,0,0]]");
        System.out.println("Expected: 4 (largest connected region)");
        int largest = largestRegion(regionGrid);
        System.out.println("Result: " + largest);
        
        // Test Word Ladder
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println("\nWord Ladder:");
        System.out.println("beginWord=hit, endWord=cog, wordList=[hot,dot,dog,lot,log,cog]");
        System.out.println("Expected: 5 (hit->hot->dot->dog->cog)");
        int chainLength = shortestChainLen("hit", "cog", wordList);
        System.out.println("Result: " + chainLength);
    }
}
