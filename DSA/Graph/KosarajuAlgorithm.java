import java.util.*;

/**
 * QUESTION: Kosaraju's Algorithm - Find Strongly Connected Components (SCC) 
 * in a directed graph using two DFS passes.
 * 
 * QUESTION EXPLANATION:
 * Strongly Connected Component: Group of vertices where each vertex is reachable 
 * from every other vertex in the same group.
 * 
 * Example Directed Graph:
 *     0→2→1
 *     ↓     ↗
 *     3→4
 * 
 * SCCs: {0,2,1} and {3,4}
 * 
 * Kosaraju's Algorithm Steps:
 * 1. First DFS: Fill stack with vertices in order of finishing times
 * 2. Transpose Graph: Reverse all edge directions
 * 3. Second DFS: Process vertices in stack order on transposed graph
 * 4. Each DFS call on transposed graph gives one SCC
 * 
 * APPROACH USED: Two-pass DFS with Stack and Graph Transposition
 * 
 * PSEUDOCODE:
 * function kosaraju(graph, V):
 *   stack = new Stack()
 *   visited = boolean[V]
 *   
 *   // Step 1: First DFS to fill stack
 *   for i = 0 to V-1:
 *     if !visited[i]:
 *       fillOrder(graph, i, visited, stack)
 *   
 *   // Step 2: Create transpose graph
 *   transpose = createTranspose(graph, V)
 *   
 *   // Step 3: Mark all as not visited
 *   for i = 0 to V-1:
 *     visited[i] = false
 *   
 *   // Step 4: Second DFS on transposed graph
 *   while !stack.isEmpty():
 *     v = stack.pop()
 *     if !visited[v]:
 *       DFS(transpose, v, visited)
 *       print("SCC -> ")
 *       print all vertices in this SCC
 * 
 * function createTranspose(graph, V):
 *   transpose = new ArrayList[V]
 *   for i = 0 to V-1:
 *     transpose[i] = new ArrayList()
 *   for u = 0 to V-1:
 *     for each edge(u,v) in graph[u]:
 *       transpose[v].add(u)
 *   return transpose
 * 
 * EXPLANATION OF STEPS:
 * 1. First DFS: Standard DFS, push vertex to stack after exploring all neighbors
 * 2. Graph Transposition: Reverse direction of all edges
 * 3. Second DFS: Process vertices in stack order on transposed graph
 * 4. Each DFS call in step 4 gives one strongly connected component
 * 5. Time complexity: O(V + E), Space complexity: O(V)
 * 
 * DRY RUN for sample graph:
 * 
 * Graph: 0→2→1, 3→4
 * 
 * First DFS (fillOrder):
 * - fillOrder(0): visited[0]=T, explore 2, explore 1, push 0
 * - fillOrder(2): visited[2]=T, explore 1, push 2
 * - fillOrder(1): visited[1]=T, no neighbors, push 1
 * - Stack: [1,2,0] (top to bottom)
 * 
 * Transpose: 1→0, 2→0, 1→2, 3→4
 * 
 * Second DFS (process stack):
 * - pop 0: visited[0]=F, DFS(transpose, 0): print "SCC -> 0 2 1"
 * - pop 2: visited[2]=F, DFS(transpose, 2): print "SCC -> 2"
 * - pop 1: visited[1]=F, DFS(transpose, 1): print "SCC -> 1"
 * 
 * Final SCCs: {0,2,1} and {3,4}
 */
public class KosarajuAlgorithm {
    
    static class Edgee {
        int src, dest;
        public Edgee(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // DFS to fill stack in order of finishing times
    public static void fillOrder(ArrayList<Edgee>[] graph, int curr, boolean[] vis, Stack<Integer> s) {
        vis[curr] = true;
        System.out.print(curr + " ");
        for (int i = 0; i < graph[curr].size(); i++) {
            Edgee e = graph[curr].get(i);
            if (!vis[e.dest]) {
                fillOrder(graph, e.dest, vis, s);
            }
        }
        s.push(curr);
    }

    // DFS on transposed graph
    public static void dfsOnTranspose(ArrayList<Edgee>[] transpose, int curr, boolean[] vis) {
        vis[curr] = true;
        System.out.print(curr + " ");
        for (int i = 0; i < transpose[curr].size(); i++) {
            Edgee e = transpose[curr].get(i);
            if (!vis[e.dest]) {
                dfsOnTranspose(transpose, e.dest, vis);
            }
        }
    }

    // Create transpose of graph
    public static ArrayList<Edgee>[] createTranspose(ArrayList<Edgee>[] graph, int V) {
        ArrayList<Edgee>[] transpose = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            transpose[i] = new ArrayList<>();
        }
        
        for (int u = 0; u < V; u++) {
            for (int j = 0; j < graph[u].size(); j++) {
                Edgee e = graph[u].get(j);
                transpose[e.dest].add(new Edgee(e.dest, e.src));
            }
        }
        return transpose;
    }

    // Main Kosaraju's algorithm
    public static void kosaraju(ArrayList<Edgee>[] graph, int V) {
        // Step 1: First DFS to fill stack
        Stack<Integer> s = new Stack<>();
        boolean[] vis = new boolean[V];
        
        System.out.println("Step 1: First DFS to fill stack");
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                fillOrder(graph, i, vis, s);
            }
        }
        
        // Step 2: Create transpose graph
        ArrayList<Edgee>[] transpose = createTranspose(graph, V);
        
        // Step 3: Mark all as not visited
        for (int i = 0; i < V; i++) {
            vis[i] = false;
        }
        
        System.out.println("\nStep 2: Second DFS on transposed graph");
        System.out.println("Strongly Connected Components:");
        
        // Step 4: Second DFS on transposed graph
        while (!s.isEmpty()) {
            int curr = s.pop();
            if (!vis[curr]) {
                System.out.print("SCC -> ");
                dfsOnTranspose(transpose, curr, vis);
                System.out.println();
            }
        }
    }
    
    // Helper to create sample graph
    public static void createGraph(ArrayList<Edgee>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // Graph: 0→2→1, 3→4
        graph[0].add(new Edgee(0, 2));
        graph[2].add(new Edgee(2, 1));
        graph[1].add(new Edgee(1, 0));
        graph[3].add(new Edgee(3, 4));
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test Kosaraju's algorithm on sample graph
         * 
         * Graph: 0→2→1, 3→4
         * Expected SCCs: {0,2,1} and {3,4}
         * 
         * Step-by-step process:
         * 1. First DFS: Fill stack [1,2,0]
         * 2. Transpose: Reverse all edges
         * 3. Second DFS: Process stack to find SCCs
         */
        
        int V = 5;
        ArrayList<Edgee>[] graph = new ArrayList[V];
        createGraph(graph);
        
        System.out.println("Kosaraju's Algorithm - Strongly Connected Components");
        System.out.println("Sample Graph:");
        System.out.println("0→2→1");
        System.out.println("↓     ↗");
        System.out.println("3→4");
        
        kosaraju(graph, V);
        
        // Show step-by-step process
        System.out.println("\nStep-by-step explanation:");
        System.out.println("1. First DFS (fillOrder):");
        System.out.println("   - Start at 0: visited[0]=T, explore 2, explore 1, push 0");
        System.out.println("   - Start at 2: visited[2]=T, explore 1, push 2");
        System.out.println("   - Start at 1: visited[1]=T, no neighbors, push 1");
        System.out.println("   - Stack order: [1,2,0] (top to bottom)");
        System.out.println("2. Transpose: Reverse all edge directions");
        System.out.println("3. Second DFS: Process stack to find SCCs");
        System.out.println("   - Pop 0: visited[0]=F, SCC: {0,2,1}");
        System.out.println("   - Pop 2: visited[2]=F, SCC: {2}");
        System.out.println("   - Pop 1: visited[1]=F, SCC: {1}");
        System.out.println("4. Final SCCs: {0,2,1} and {3,4}");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single SCC
        ArrayList<Edgee>[] singleSCC = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            singleSCC[i] = new ArrayList<>();
        }
        // All vertices in one component
        singleSCC[0].add(new Edgee(0, 1));
        singleSCC[1].add(new Edgee(1, 2));
        singleSCC[2].add(new Edgee(2, 0));
        
        System.out.println("Single SCC (all vertices connected):");
        kosaraju(singleSCC, 3);
        
        // Test case 3: No edges
        ArrayList<Edgee>[] noEdges = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            noEdges[i] = new ArrayList<>();
        }
        
        System.out.println("No edges:");
        kosaraju(noEdges, 3);
        
        // Test case 4: Cycle in directed graph
        ArrayList<Edgee>[] cycle = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            cycle[i] = new ArrayList<>();
        }
        cycle[0].add(new Edgee(0, 1));
        cycle[1].add(new Edgee(1, 2));
        cycle[2].add(new Edgee(2, 0));
        
        System.out.println("Directed cycle:");
        kosaraju(cycle, 3);
    }
}
