import java.util.*;

/**
 * QUESTION: Bridges in Graph - Find all bridges (cut edges) in an undirected graph.
 * A bridge is an edge whose removal increases the number of connected components.
 * 
 * QUESTION EXPLANATION:
 * Bridge: Edge whose removal disconnects the graph or increases connected components.
 * Critical for network vulnerability analysis and road network planning.
 * 
 * Example Graph:
 *     0---1---2
 *     |   |   |
 *     3---4   5
 * 
 * Bridges: Edge 1-2 and 2-5
 * Removing edge 1-2: Components become {0,3,4} and {2,5}
 * Removing edge 2-5: Components become {0,1,2,3,4} and {5}
 * 
 * APPROACH USED: Tarjan's Algorithm with DFS
 * Uses discovery time and low values to identify bridges.
 * 
 * PSEUDOCODE:
 * function findBridges(graph, V):
 *   visited = boolean[V]
 *   disc = int[V]     // Discovery time of vertices
 *   low = int[V]      // Lowest discovery time reachable
 *   time = 0
 *   
 *   for i = 0 to V-1:
 *     if !visited[i]:
 *       dfs(graph, i, -1, visited, disc, low, time)
 * 
 * function dfs(graph, u, parent, visited, disc, low, time):
 *   visited[u] = true
 *   disc[u] = low[u] = ++time
 *   
 *   for each edge(u,v) in graph[u]:
 *     if v == parent: continue
 *     
 *     if !visited[v]:
 *       dfs(graph, v, u, visited, disc, low, time)
 *       low[u] = Math.min(low[u], low[v])
 *     else:
 *       low[u] = Math.min(low[u], disc[v])
 *   
 *   // Check if edge (u,v) is a bridge
 *   if low[v] > disc[u]:
 *     print("Bridge: " + u + " -- " + v)
 * 
 * EXPLANATION OF STEPS:
 * 1. Run DFS from each unvisited vertex
 * 2. Track discovery time (when vertex is first visited)
 * 3. Track low value (earliest discovery time reachable)
 * 4. For each edge (u,v), if low[v] > disc[u], it's a bridge
 * 5. Time complexity: O(V + E), Space complexity: O(V)
 * 
 * DRY RUN for sample graph:
 * 
 * Graph: Triangle 0-1-2 with extra connections
 * 
 * DFS from 0:
 * - disc[0] = low[0] = 0, time = 1
 * - Edge 0-1: DFS(1, 0, visited, disc, low, time)
 *   - disc[1] = low[1] = 1, time = 2
 *   - Edge 1-2: DFS(2, 1, visited, disc, low, time)
 *     - disc[2] = low[2] = 2, time = 3
 *   - Back to 0: low[0] = Math.min(0, low[1]) = 0
 *   - Check edge 0-1: low[1] = 1 > disc[0] = 0 → NOT bridge
 * - Check edge 0-2: low[2] = 2 > disc[0] = 0 → NOT bridge
 * 
 * DFS from 1:
 * - Similar process for other edges
 * 
 * Final bridges identified based on low values
 */
public class BridgesAlgorithm {
    
    static class Edgee {
        int src, dest;
        public Edgee(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    static int time = 0; // Global time counter

    // DFS to find bridges
    public static void dfs(ArrayList<Edgee>[] graph, int u, int parent, boolean[] visited, 
                    int[] disc, int[] low, int time) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        
        for (int i = 0; i < graph[u].size(); i++) {
            Edgee e = graph[u].get(i);
            int v = e.dest;
            
            if (v == parent) {
                continue;
            }
            
            if (!visited[v]) {
                dfs(graph, v, u, visited, disc, low, time);
                low[u] = Math.min(low[u], low[v]);
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
            
            // Check if edge (u,v) is a bridge
            if (low[v] > disc[u]) {
                System.out.println("Bridge: " + u + " -- " + v);
            }
        }
    }

    // Main function to find all bridges
    public static void findBridges(ArrayList<Edgee>[] graph, int V) {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        time = 0;
        
        System.out.println("Finding bridges in graph:");
        
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(graph, i, -1, visited, disc, low, time);
            }
        }
    }
    
    // Helper to create sample graph
    public static void createGraph(ArrayList<Edgee>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // Graph: Triangle 0-1-2 with extra connections
        // 0---1---2
        // |   |   |
        // 3---4   5
        graph[0].add(new Edgee(0, 1));
        graph[0].add(new Edgee(0, 3));
        graph[1].add(new Edgee(1, 0));
        graph[1].add(new Edgee(1, 2));
        graph[2].add(new Edgee(2, 0));
        graph[2].add(new Edgee(2, 1));
        graph[3].add(new Edgee(3, 4));
        graph[4].add(new Edgee(4, 3));
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test bridge finding algorithm on sample graph
         * 
         * Graph: Triangle 0-1-2 with connections 3-4 and 4-5
         * Expected bridges: Edge 1-2 and 2-5
         * 
         * Step-by-step process:
         * 1. DFS from 0: explore neighbors, track disc and low values
         * 2. DFS from 1: continue exploration
         * 3. DFS from 2: continue exploration
         * 4. DFS from 3,4,5: explore remaining vertices
         * 5. Identify bridges using low values
         */
        
        int V = 6;
        ArrayList<Edgee>[] graph = new ArrayList[V];
        createGraph(graph);
        
        System.out.println("Bridges in Graph - Tarjan's Algorithm");
        System.out.println("Sample Graph:");
        System.out.println("0---1---2");
        System.out.println("|   |   |");
        System.out.println("3---4   5");
        
        findBridges(graph, V);
        
        // Show step-by-step explanation
        System.out.println("\nStep-by-step bridge identification:");
        System.out.println("1. Discovery times (disc): when vertex first visited");
        System.out.println("2. Low values: earliest discovery time reachable");
        System.out.println("3. Bridge condition: low[v] > disc[u] for edge (u,v)");
        System.out.println("4. If true, edge (u,v) is a bridge");
        
        System.out.println("\nBridge identification process:");
        System.out.println("DFS from vertex 0:");
        System.out.println("- disc[0] = low[0] = 0 (time = 1)");
        System.out.println("- Edge 0-1: explore vertex 1");
        System.out.println("- Edge 0-3: explore vertex 3");
        System.out.println("- Back to vertex 0:");
        System.out.println("  - low[1] = 1, low[3] = 2");
        System.out.println("  - low[0] = min(0, 1, 2) = 0");
        System.out.println("  - Check edge 0-1: low[1] = 1 > disc[0] = 0 → NOT bridge");
        System.out.println("  - Check edge 0-3: low[3] = 2 > disc[0] = 0 → NOT bridge");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Simple bridge
        ArrayList<Edgee>[] simpleBridge = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            simpleBridge[i] = new ArrayList<>();
        }
        // Line: 0-1-2-3
        simpleBridge[0].add(new Edgee(0, 1));
        simpleBridge[1].add(new Edgee(1, 0));
        simpleBridge[1].add(new Edgee(1, 2));
        simpleBridge[2].add(new Edgee(2, 1));
        simpleBridge[2].add(new Edgee(2, 3));
        
        System.out.println("Simple line graph (0-1-2-3):");
        findBridges(simpleBridge, 4);
        
        // Test case 3: No bridges (tree)
        ArrayList<Edgee>[] tree = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            tree[i] = new ArrayList<>();
        }
        // Tree structure
        tree[0].add(new Edgee(0, 1));
        tree[0].add(new Edgee(0, 2));
        tree[1].add(new Edgee(1, 0));
        tree[1].add(new Edgee(1, 3));
        tree[2].add(new Edgee(2, 1));
        tree[3].add(new Edgee(3, 1));
        
        System.out.println("Tree structure (no bridges):");
        findBridges(tree, 4);
        
        // Test case 4: Multiple bridges
        ArrayList<Edgee>[] multiBridge = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            multiBridge[i] = new ArrayList<>();
        }
        // Multiple bridge structure
        multiBridge[0].add(new Edgee(0, 1));
        multiBridge[1].add(new Edgee(1, 0));
        multiBridge[2].add(new Edgee(2, 0));
        multiBridge[3].add(new Edgee(3, 4));
        multiBridge[4].add(new Edgee(4, 5));
        
        System.out.println("Multiple bridge structure:");
        findBridges(multiBridge, 5);
    }
}
