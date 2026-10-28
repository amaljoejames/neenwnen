import java.util.*;

/**
 * QUESTION: Graph Cycle Detection - Implement cycle detection algorithms for
 * both undirected and directed graphs using different approaches.
 * 
 * QUESTION EXPLANATION:
 * Cycle detection determines if there's a path that starts and ends at the same vertex.
 * 
 * Undirected Graph Cycle Example:
 *     0
 *    / \
 *   1---2  ← Cycle: 0-1-2-0
 *   |   |
 *   3---4
 * 
 * Directed Graph Cycle Example:
 *     0→1→2→0  ← Cycle: 0→1→2→0
 *     ↓
 *     3
 * 
 * Approaches:
 * 1. Undirected: DFS with parent tracking
 * 2. Directed: DFS with recursion stack tracking
 * 3. Undirected: BFS with parent tracking
 * 4. Bipartite check using BFS coloring
 * 
 * APPROACH USED: DFS with Tracking Mechanisms
 * 
 * PSEUDOCODE:
 * 
 * Undirected Graph Cycle Detection:
 * function detectCycle(graph):
 *   visited = boolean[graph.length]
 *   for i = 0 to graph.length-1:
 *     if !visited[i]:
 *       if detectUtil(graph, i, visited, -1):
 *         return true
 *   return false
 * 
 * function detectUtil(graph, src, visited, parent):
 *   visited[src] = true
 *   for each edge in graph[src]:
 *     if !visited[edge.dest]:
 *       if detectUtil(graph, edge.dest, visited, src):
 *         return true
 *     else if edge.dest != parent:
 *       return true
 *   return false
 * 
 * Directed Graph Cycle Detection:
 * function detectCycle2(graph):
 *   visited = boolean[graph.length]
 *   stack = boolean[graph.length]
 *   for i = 0 to graph.length-1:
 *     if !visited[i]:
 *       if isCyclicUtil2(graph, i, visited, stack):
 *         return true
 *   return false
 * 
 * function isCyclicUtil2(graph, curr, visited, stack):
 *   visited[curr] = true
 *   stack[curr] = true
 *   for each edge in graph[curr]:
 *     if stack[edge.dest]: return true
 *     if !visited[edge.dest] && isCyclicUtil2(graph, edge.dest, visited, stack):
 *       return true
 *   stack[curr] = false
 *   return false
 * 
 * EXPLANATION OF STEPS:
 * 1. Undirected: Track parent to avoid false positives
 * 2. Directed: Track recursion stack to detect back edges
 * 3. Bipartite: Use BFS with 2-coloring
 * 4. Time complexity: O(V + E) for all approaches
 * 
 * DRY RUN for Undirected Graph with Cycle:
 * 
 * Graph: 0-1-2-0 (triangle)
 * 
 * detectUtil(graph, 0, visited, -1)
 * - visited[0] = true
 * - Edge 0-1: !visited[1] → detectUtil(graph, 1, visited, 0)
 *   - visited[1] = true
 *   - Edge 1-0: visited[0] && 0 == parent → continue
 *   - Edge 1-2: !visited[2] → detectUtil(graph, 2, visited, 1)
 *     - visited[2] = true
 *     - Edge 2-1: visited[1] && 1 == parent → continue
 *     - Edge 2-0: visited[0] && 0 != parent → CYCLE DETECTED!
 *     - return true
 *   - return true
 * - return true
 * 
 * Final result: Cycle exists
 */
public class GraphCycleDetection {
    
    static class Edge {
        int src, dest;
        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // Cycle detection in undirected graph
    public static boolean detectCycle(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                if (detectUtil(graph, i, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean detectUtil(ArrayList<Edge>[] graph, int src, boolean[] visited, int parent) {
        visited[src] = true;
        for (int i = 0; i < graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            if (!visited[e.dest]) {
                if (detectUtil(graph, e.dest, visited, src)) {
                    return true;
                }
            } else if (visited[e.dest] && e.dest != parent) {
                return true;
            }
        }
        return false;
    }

    // Check for bipartite graph
    public static boolean isBipartite(ArrayList<Edge>[] graph) {
        int col[] = new int[graph.length];
        for (int i = 0; i < col.length; i++) {
            col[i] = -1; // no color
        }
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < col.length; i++) {
            if (col[i] == -1) { // BFS
                q.add(i);
                col[i] = 0; // yellow color
                while (!q.isEmpty()) {
                    int curr = q.poll();
                    for (int j = 0; j < graph[curr].size(); j++) {
                        Edge e = graph[curr].get(j);
                        if (col[e.dest] == -1) {
                            q.add(e.dest);
                            int nextcol = col[curr] == 0 ? 1 : 0; // opposite color
                            col[e.dest] = nextcol;
                        } else if (col[e.dest] == col[curr]) {
                            return false; // not bipartite
                        }
                    }
                }
            }
        }
        return true;
    }

    // Cycle detection for directed graph
    public static boolean isCycle2(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        boolean stack[] = new boolean[graph.length];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                if (isCyclicUtil2(graph, i, visited, stack)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isCyclicUtil2(ArrayList<Edge>[] graph, int curr, boolean[] vis, boolean[] stack) {
        vis[curr] = true;
        stack[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (stack[e.dest]) { // cycle
                return true;
            }
            if (!vis[e.dest] && isCyclicUtil2(graph, e.dest, vis, stack)) { // not visited
                return true;
            }
        }
        stack[curr] = false; // no cycle
        return false;
    }

    // Helper to create undirected graph
    static void createUndirectedGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        // Graph with cycle: 0-1-2-0
        graph[0].add(new Edge(0, 1));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));
        graph[2].add(new Edge(2, 1));
        graph[2].add(new Edge(2, 0));
        graph[0].add(new Edge(0, 2));
    }

    // Helper to create directed graph
    static void createDirectedGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        // Graph with cycle: 0→1→2→0
        graph[0].add(new Edge(0, 1));
        graph[1].add(new Edge(1, 2));
        graph[2].add(new Edge(2, 0));
    }

    // Helper to create acyclic graph
    static void createAcyclicGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        // Tree structure: 0-1-2-3
        graph[0].add(new Edge(0, 1));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));
        graph[2].add(new Edge(2, 1));
        graph[2].add(new Edge(2, 3));
        graph[3].add(new Edge(3, 2));
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test cycle detection on different graph types
         * 
         * Test 1: Undirected graph with cycle (triangle 0-1-2-0)
         * Test 2: Undirected graph without cycle (tree structure)
         * Test 3: Directed graph with cycle
         * Test 4: Bipartite check
         */
        
        int V = 3;
        
        // Test 1: Undirected graph with cycle
        ArrayList<Edge>[] cyclicGraph = new ArrayList[V];
        createUndirectedGraph(cyclicGraph);
        
        System.out.println("Graph Cycle Detection");
        System.out.println("Test 1: Undirected graph with cycle (0-1-2-0)");
        System.out.println("Has cycle: " + detectCycle(cyclicGraph));
        System.out.println("Is bipartite: " + isBipartite(cyclicGraph));
        
        // Test 2: Undirected graph without cycle
        ArrayList<Edge>[] acyclicGraph = new ArrayList[V + 1];
        createAcyclicGraph(acyclicGraph);
        
        System.out.println("\nTest 2: Undirected graph without cycle (tree 0-1-2-3)");
        System.out.println("Has cycle: " + detectCycle(acyclicGraph));
        System.out.println("Is bipartite: " + isBipartite(acyclicGraph));
        
        // Test 3: Directed graph with cycle
        ArrayList<Edge>[] directedCyclic = new ArrayList[V];
        createDirectedGraph(directedCyclic);
        
        System.out.println("\nTest 3: Directed graph with cycle (0→1→2→0)");
        System.out.println("Has cycle: " + isCycle2(directedCyclic));
        
        // Show step-by-step for undirected cycle detection
        System.out.println("\nStep-by-step cycle detection for triangle 0-1-2-0:");
        System.out.println("detectUtil(graph, 0, visited, -1)");
        System.out.println("- visited[0] = true");
        System.out.println("- Edge 0-1: !visited[1] → detectUtil(graph, 1, visited, 0)");
        System.out.println("  - visited[1] = true");
        System.out.println("  - Edge 1-0: visited[0] && 0 == parent → continue");
        System.out.println("  - Edge 1-2: !visited[2] → detectUtil(graph, 2, visited, 1)");
        System.out.println("    - visited[2] = true");
        System.out.println("    - Edge 2-1: visited[1] && 1 == parent → continue");
        System.out.println("    - Edge 2-0: visited[0] && 0 != parent → CYCLE DETECTED!");
        System.out.println("    - return true");
        System.out.println("  - return true");
        System.out.println("- return true");
        System.out.println("Final result: Cycle exists");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test 4: Single vertex (no cycle)
        ArrayList<Edge>[] single = new ArrayList[1];
        single[0] = new ArrayList<>();
        System.out.println("Single vertex:");
        System.out.println("Has cycle: " + detectCycle(single));
        System.out.println("Is bipartite: " + isBipartite(single));
        
        // Test 5: Complete bipartite graph
        ArrayList<Edge>[] bipartite = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            bipartite[i] = new ArrayList<>();
        }
        // K_{2,2}: vertices 0,1 connected to 2,3
        bipartite[0].add(new Edge(0, 2));
        bipartite[2].add(new Edge(2, 0));
        bipartite[0].add(new Edge(0, 3));
        bipartite[3].add(new Edge(3, 0));
        bipartite[1].add(new Edge(1, 2));
        bipartite[2].add(new Edge(2, 1));
        bipartite[1].add(new Edge(1, 3));
        bipartite[3].add(new Edge(3, 1));
        
        System.out.println("Complete bipartite graph K_{2,2}:");
        System.out.println("Has cycle: " + detectCycle(bipartite));
        System.out.println("Is bipartite: " + isBipartite(bipartite));
    }
}
