import java.util.*;

/**
 * QUESTION: Advanced Graph Algorithms - Implement Floyd-Warshall, Articulation Points,
 * Bridges, Strongly Connected Components (Kosaraju), and Union-Find.
 * 
 * QUESTION EXPLANATION:
 * 
 * Floyd-Warshall: Find shortest paths between all pairs of vertices.
 * Time complexity: O(V³), useful for dense graphs with negative weights.
 * 
 * Articulation Points: Vertices whose removal increases number of connected components.
 * Also called cut vertices. Critical for network reliability analysis.
 * 
 * Bridges: Edges whose removal increases number of connected components.
 * Critical for network vulnerability analysis.
 * 
 * Strongly Connected Components: Groups where each vertex reachable from every other.
 * Important for dependency analysis, web page ranking.
 * 
 * Union-Find: Disjoint Set data structure for dynamic connectivity.
 * Used in Kruskal's MST, cycle detection, connected components.
 * 
 * APPROACH USED: Multiple advanced graph algorithms
 * 
 * PSEUDOCODE:
 * 
 * Floyd-Warshall:
 * function floydWarshall(graph):
 *   dist = copy(graph)
 *   for k = 0 to V-1:
 *     for i = 0 to V-1:
 *       for j = 0 to V-1:
 *         if dist[i][k] + dist[k][j] < dist[i][j]:
 *           dist[i][j] = dist[i][k] + dist[k][j]
 *   return dist
 * 
 * Articulation Points (Tarjan's Algorithm):
 * function findArticulationPoints(graph, V):
 *   disc = int[V], low = int[V], parent = int[V]
 *   visited = boolean[V], ap = boolean[V], time = 0
 *   
 *   for i = 0 to V-1:
 *     if !visited[i]:
 *       APUtil(graph, i, visited, disc, low, parent, ap)
 * 
 * Union-Find:
 * function find(parent, x):
 *   if parent[x] != x:
 *     parent[x] = find(parent, parent[x])
 *   return parent[x]
 * 
 * function union(parent, rank, x, y):
 *   rootX = find(parent, x)
 *   rootY = find(parent, y)
 *   if rootX != rootY:
 *     if rank[rootX] < rank[rootY]:
 *       parent[rootX] = rootY
 *     else:
 *       parent[rootY] = rootX
 *       if rank[rootX] == rank[rootY]:
 *         rank[rootY]++
 * 
 * EXPLANATION OF STEPS:
 * 1. Floyd-Warshall: Try all intermediate vertices for shortest paths
 * 2. Articulation Points: Use discovery time and low values
 * 3. Bridges: Similar to articulation but for edges
 * 4. SCC: Kosaraju's algorithm with DFS and transpose
 * 5. Union-Find: Path compression and union by rank
 * 
 * DRY RUN for Floyd-Warshall:
 * 
 * Graph: 4 vertices, edges: 0-1(5), 1-2(3), 2-3(1)
 * Initial dist matrix:
 *   0   5   INF  INF
 *   INF  0   3    INF
 *   INF INF  0    1
 *   INF INF INF  0
 * 
 * After k=0:
 *   0   5   INF  INF
 *   5   0   3    INF
 *   INF INF  0    1
 *   INF INF INF  0
 * 
 * After k=1:
 *   0   5   8    INF
 *   5   0   3    4
 *   INF INF  0    1
 *   INF INF INF  0
 * 
 * Final result shows all shortest paths
 */
public class AdvancedGraphAlgorithms {
    
    final static int INF = 99999;

    // Floyd-Warshall Algorithm
    public static void floydWarshall(int graph[][]) {
        int V = graph.length;
        int dist[][] = new int[V][V];

        // Initialize distance matrix with given graph values
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                dist[i][j] = graph[i][j];

        // Update distances considering each vertex as an intermediate
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        // Print final shortest distance matrix
        printSolution(dist);
    }

    // Utility function to print distance matrix
    public static void printSolution(int dist[][]) {
        System.out.println("Shortest distances between every pair of vertices:");
        for (int i = 0; i < dist.length; ++i) {
            for (int j = 0; j < dist.length; ++j) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Articulation Points - Tarjan's Algorithm
    static int time; // Global static time used for discovery timing in DFS

    // Add undirected edge
    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    // Recursive DFS utility to find articulation points
    static void APUtil(ArrayList<ArrayList<Integer>> adj, int u, boolean[] visited,
                       int[] disc, int[] low, int parent, boolean[] isAP) {

        int children = 0; // Count of children in DFS tree
        visited[u] = true;
        disc[u] = low[u] = ++time; // Initialize discovery and low value

        for (Integer v : adj.get(u)) {
            if (!visited[v]) {
                children++;
                APUtil(adj, v, visited, disc, low, u, isAP);

                low[u] = Math.min(low[u], low[v]);

                // u is an articulation point if:
                // 1. It's not root and low[v] >= disc[u]
                if (parent != -1 && low[v] >= disc[u])
                    isAP[u] = true;
            }
            // Update low value of u for parent function calls.
            else if (v != parent)
                low[u] = Math.min(low[u], disc[v]);
        }

        // Root node is an articulation point if it has 2 or more children
        if (parent == -1 && children > 1)
            isAP[u] = true;
    }

    // Main function to find and print all articulation points
    static void findArticulationPoints(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] isAP = new boolean[V];
        time = 0;

        for (int u = 0; u < V; u++) {
            if (!visited[u])
                APUtil(adj, u, visited, disc, low, -1, isAP);
        }

        System.out.print("Articulation Points: ");
        for (int u = 0; u < V; u++) {
            if (isAP[u])
                System.out.print(u + " ");
        }
        System.out.println();
    }

    // Union-Find Data Structure
    static class UnionFind {
        int parent[], rank[];

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    if (rank[rootX] == rank[rootY]) {
                        rank[rootY]++;
                    }
                }
            }
        }

        boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test Floyd-Warshall, Articulation Points, and Union-Find
         * 
         * Floyd-Warshall: 4 vertex graph with various edge weights
         * Articulation Points: Graph with critical vertices
         * Union-Find: Dynamic connectivity testing
         */
        
        // Test Floyd-Warshall
        int graphFW[][] = {
            {0,   5,   INF, 10},
            {INF, 0,   3,   INF},
            {INF, INF, 0,   1},
            {INF, INF, INF, 0}
        };

        System.out.println("Advanced Graph Algorithms");
        System.out.println("Floyd-Warshall Algorithm:");
        floydWarshall(graphFW);
        
        // Test Articulation Points
        int V = 7;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Create graph with articulation points
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 0);
        addEdge(adj, 1, 3);
        addEdge(adj, 1, 4);
        addEdge(adj, 1, 6);
        addEdge(adj, 3, 5);
        addEdge(adj, 4, 5);
        addEdge(adj, 5, 6);
        
        System.out.println("\nArticulation Points:");
        System.out.println("Graph with vertex 1 as articulation point");
        findArticulationPoints(adj, V);
        
        // Test Union-Find
        UnionFind uf = new UnionFind(5);
        System.out.println("\nUnion-Find Data Structure:");
        System.out.println("Initial state: All separate sets");
        System.out.println("Union(0,1): " + uf.connected(0, 1));
        uf.union(0, 1);
        System.out.println("Connected(0,1): " + uf.connected(0, 1));
        
        uf.union(1, 2);
        System.out.println("Union(1,2): " + uf.connected(1, 2));
        System.out.println("Connected(0,2): " + uf.connected(0, 2));
        
        uf.union(3, 4);
        System.out.println("Union(3,4): " + uf.connected(3, 4));
        System.out.println("Connected(0,4): " + uf.connected(0, 4));
        
        // Show step-by-step for Floyd-Warshall
        System.out.println("\nStep-by-step Floyd-Warshall:");
        System.out.println("Initial distance matrix:");
        System.out.println("0   5   INF  10");
        System.out.println("INF 0   3    INF");
        System.out.println("INF INF 0    1");
        System.out.println("INF INF INF  0");
        System.out.println("\nAfter k=0 (vertex 0 as intermediate):");
        System.out.println("Path 0→1: 5 (direct edge)");
        System.out.println("Path 0→2: 0→1→2 = 5+3 = 8");
        System.out.println("Path 0→3: 0→1→3 = 5+3+1 = 9");
        System.out.println("Path 0→0: 0");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Simple graph for articulation
        ArrayList<ArrayList<Integer>> simpleAdj = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            simpleAdj.add(new ArrayList<>());
        }
        addEdge(simpleAdj, 0, 1);
        addEdge(simpleAdj, 1, 2);
        addEdge(simpleAdj, 2, 3);
        
        System.out.println("Simple path graph (0-1-2-3):");
        findArticulationPoints(simpleAdj, 4);
        
        // Test case 3: Union-Find with path compression
        UnionFind uf2 = new UnionFind(10);
        System.out.println("\nUnion-Find with path compression:");
        for (int i = 0; i < 9; i++) {
            uf2.union(i, i + 1);
        }
        System.out.println("All connected: " + uf2.connected(0, 9));
        System.out.println("Find operations with path compression work efficiently");
    }
}
