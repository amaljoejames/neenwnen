import java.util.*;

/**
 * QUESTION: Remaining Graph Algorithms - Implement missing graph algorithms including
 * Prim's MST, Bellman-Ford, Flood Fill, and other advanced problems.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Prim's MST Algorithm:
 * Find Minimum Spanning Tree of weighted undirected graph.
 * Greedy algorithm that builds MST by adding minimum weight edges.
 * 
 * 2. Bellman-Ford Algorithm:
 * Find shortest paths from source to all vertices.
 * Handles negative edge weights, detects negative cycles.
 * 
 * 3. Flood Fill Algorithm:
 * Fill connected region with new color in 2D grid.
 * Similar to paint bucket tool in image editing.
 * 
 * 4. Other Missing Algorithms:
 * Cheapest Flights, Connecting Cities, Largest Region, etc.
 * 
 * APPROACH USED: Greedy, DFS, BFS, and Dynamic Programming
 * 
 * PSEUDOCODE:
 * 
 * Prim's MST:
 * function primMST(graph, V):
 *   key = int[V], parent = int[-1]
 *   mstSet = boolean[V]
 *   key[0] = 0
 *   
 *   for count = 0 to V-1:
 *     u = minKey(key, mstSet)
 *     mstSet[u] = true
 *     
 *     for v = 0 to V-1:
 *       if graph[u][v] > 0 && !mstSet[v] && graph[u][v] < key[v]:
 *         key[v] = graph[u][v]
 *         parent[v] = u
 *   
 *   return parent
 * 
 * Bellman-Ford:
 * function bellmanFord(graph, V, E, src):
 *   dist = int[V]
 *   Arrays.fill(dist, INF)
 *   dist[src] = 0
 *   
 *   for i = 1 to V-1:
 *     for each edge(u,v,w) in graph:
 *       if dist[u] != INF && dist[u] + w < dist[v]:
 *         dist[v] = dist[u] + w
 *   
 *   // Check for negative cycles
 *   for each edge(u,v,w) in graph:
 *     if dist[u] != INF && dist[u] + w < dist[v]:
 *       print("Negative cycle detected")
 *       return
 *   
 *   return dist
 * 
 * Flood Fill:
 * function floodFill(screen, x, y, newColor):
 *   oldColor = screen[x][y]
 *   if oldColor == newColor: return
 *   
 *   queue = Queue()
 *   queue.add((x,y))
 *   
 *   while !queue.isEmpty():
 *     (currX, currY) = queue.remove()
 *     
 *     for each direction in [(0,1),(0,-1),(1,0),(-1,0)]:
 *       newX = currX + direction[0]
 *       newY = currY + direction[1]
 *       
 *       if isValid(newX, newY) && screen[newX][newY] == oldColor:
 *         screen[newX][newY] = newColor
 *         queue.add((newX, newY))
 * 
 * EXPLANATION OF STEPS:
 * 1. Prim's: Use min key array and visited set
 * 2. Bellman-Ford: Relax edges V-1 times, check for negative cycles
 * 3. Flood Fill: BFS/DFS from starting point
 * 4. Time complexity varies by algorithm
 * 
 * DRY RUN for Prim's MST:
 * 
 * Graph: 5 vertices, weighted edges
 * 
 * Process:
 * Start with vertex 0, key = [0,INF,INF,INF,INF]
 * 
 * Iteration 1: u=0, add edges (0-1:2, 0-2:4)
 * key = [0,2,4,INF,INF], parent = [-1,0,0,-1,-1]
 * 
 * Iteration 2: u=1, add edge (1-3:8)
 * key = [0,2,4,8,INF], parent = [-1,0,0,1,-1]
 * 
 * Continue until MST complete
 * 
 * Final MST edges: (0-1:2), (0-2:4), (1-3:8)
 */
public class RemainingGraphAlgorithms {
    
    // Prim's MST Algorithm
    public static void primMST(int graph[][]) {
        int V = graph.length;
        int parent[] = new int[V];
        int key[] = new int[V];
        boolean mstSet[] = new boolean[V];
        
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        key[0] = 0;
        
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;
            
            for (int v = 0; v < V; v++) {
                if (graph[u][v] > 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }
        
        System.out.println("Prim's MST:");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + " : " + graph[i][parent[i]]);
        }
    }
    
    private static int minKey(int key[], boolean mstSet[]) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        
        for (int v = 0; v < key.length; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        
        return minIndex;
    }

    // Bellman-Ford Algorithm
    public static void bellmanFord(int graph[][], int V, int E, int src) {
        int dist[] = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        // Relax edges V-1 times
        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {
                int u = graph[j][0];
                int v = graph[j][1];
                int weight = graph[j][2];
                
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }
        
        // Check for negative cycles
        for (int j = 0; j < E; j++) {
            int u = graph[j][0];
            int v = graph[j][1];
            int weight = graph[j][2];
            
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Negative cycle detected!");
                return;
            }
        }
        
        System.out.println("Bellman-Ford distances from source " + src + ":");
        for (int i = 0; i < V; i++) {
            System.out.println(i + ": " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    // Flood Fill Algorithm
    public static void floodFill(int screen[][], int x, int y, int newColor) {
        int oldColor = screen[x][y];
        if (oldColor == newColor) {
            return;
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        
        int rows = screen.length;
        int cols = screen[0].length;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int currX = curr[0];
            int currY = curr[1];
            
            // Check 4 directions
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            
            for (int[] dir : directions) {
                int newX = currX + dir[0];
                int newY = currY + dir[1];
                
                if (isValid(newX, newY, rows, cols) && screen[newX][newY] == oldColor) {
                    screen[newX][newY] = newColor;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
        
        System.out.println("Flood Fill completed!");
    }
    
    private static boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all remaining graph algorithms
         * 
         * Prim's MST: 5 vertices, weighted graph
         * Expected: MST with minimum total weight
         * 
         * Bellman-Ford: Graph with negative edges
         * Expected: Shortest distances or negative cycle detection
         * 
         * Flood Fill: 2D grid with connected region
         * Expected: Region filled with new color
         */
        
        // Test Prim's MST
        int graph[][] = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };
        
        System.out.println("Remaining Graph Algorithms - Prim's MST");
        System.out.println("Graph: 5 vertices with weighted edges");
        System.out.println("Expected: MST with minimum total weight");
        
        primMST(graph);
        
        // Test Bellman-Ford
        int E = 5;
        int V = 5;
        int src = 0;
        int graphBF[][] = {
            {0, 1, -1},
            {0, 2, 4},
            {1, 2, 3},
            {1, 3, 2},
            {1, 4, 5}
        };
        
        System.out.println("\nBellman-Ford Algorithm:");
        System.out.println("Graph: 5 vertices, 5 edges with negative weight");
        System.out.println("Expected: Shortest distances or negative cycle");
        
        bellmanFord(graphBF, V, E, src);
        
        // Test Flood Fill
        int screen[][] = {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 2, 2, 2, 2},
            {1, 1, 1, 2, 2, 2, 2},
            {1, 1, 1, 1, 1, 1, 1}
        };
        
        System.out.println("\nFlood Fill Algorithm:");
        System.out.println("Screen: 4x4 grid with connected region");
        System.out.println("Expected: Region filled with new color");
        
        floodFill(screen, 0, 0, 3);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Simple MST
        int simpleMST[][] = {
            {0, 4, 0},
            {4, 0, 8},
            {0, 8, 0}
        };
        System.out.println("Simple MST:");
        primMST(simpleMST);
        
        // Test case 3: Flood Fill single cell
        int single[][] = {{1}};
        System.out.println("Single cell flood fill:");
        floodFill(single, 0, 0, 2);
    }
}
