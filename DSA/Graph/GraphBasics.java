import java.util.*;

/**
 * QUESTION: Graph Basics - Implement fundamental graph operations including
 * graph creation, BFS, DFS, path finding, and connected components.
 * 
 * QUESTION EXPLANATION:
 * Graph is a collection of vertices (nodes) connected by edges.
 * Types: Undirected (bidirectional edges) vs Directed (unidirectional edges)
 * 
 * Example Graph (Undirected):
 *     0
 *    / \
 *   1---2
 *   |   |
 *   3---4
 * 
 * Operations covered:
 * 1. Graph creation using adjacency list
 * 2. BFS (Breadth First Search) traversal
 * 3. DFS (Depth First Search) traversal
 * 4. Path finding between nodes
 * 5. Connected components identification
 * 
 * APPROACH USED: Adjacency List with Traversal Algorithms
 * 
 * PSEUDOCODE:
 * class Edge:
 *   src, dest, wt
 *   
 * function createGraph(graph):
 *   for i = 0 to graph.length-1:
 *     graph[i] = new ArrayList()
 *   Add edges to adjacency lists
 * 
 * function bfs(graph):
 *   queue = new Queue()
 *   visited = boolean[graph.length]
 *   queue.add(0)
 *   
 *   while !queue.isEmpty():
 *     curr = queue.remove()
 *     if !visited[curr]:
 *       print(curr)
 *       visited[curr] = true
 *       for each edge in graph[curr]:
 *         queue.add(edge.dest)
 * 
 * function dfs(graph):
 *   visited = boolean[graph.length]
 *   for i = 0 to graph.length-1:
 *     if !visited[i]:
 *       dfsUtil(graph, i, visited)
 * 
 * function dfsUtil(graph, curr, visited):
 *   print(curr)
 *   visited[curr] = true
 *   for each edge in graph[curr]:
 *     if !visited[edge.dest]:
 *       dfsUtil(graph, edge.dest, visited)
 * 
 * EXPLANATION OF STEPS:
 * 1. Create Edge class to represent graph connections
 * 2. Build adjacency list representation
 * 3. BFS: Use queue, visit level by level
 * 4. DFS: Use recursion, go deep first
 * 5. Path finding: Use DFS with backtracking
 * 6. Connected components: Run BFS/DFS from unvisited nodes
 * 
 * DRY RUN for BFS traversal:
 * 
 * Graph: 0-1-2, 0-3-4
 * Queue: [0], visited: [F,F,F,F,F]
 * 
 * Step 1: curr=0, visited[0]=T, add neighbors 1,2,3
 * Queue: [1,2,3], visited: [T,F,F,F,F]
 * 
 * Step 2: curr=1, visited[1]=T, add neighbor 0 (already visited)
 * Queue: [2,3], visited: [T,T,F,F,F]
 * 
 * Step 3: curr=2, visited[2]=T, add neighbor 0 (already visited)
 * Queue: [3], visited: [T,T,T,F,F]
 * 
 * Step 4: curr=3, visited[3]=T, add neighbor 4
 * Queue: [4], visited: [T,T,T,T,F]
 * 
 * Step 5: curr=4, visited[4]=T, add neighbor 3 (already visited)
 * Queue: [], visited: [T,T,T,T,T]
 * 
 * BFS traversal: 0,1,2,3,4
 */
public class GraphBasics {
    
    static class Edge {
        int src, dest, wt;
        public Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2, 15)); 
        graph[0].add(new Edge(0, 3, 30));
        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 3, 40));
        graph[2].add(new Edge(2, 0, 15));
        graph[2].add(new Edge(2, 3, 50));
        graph[3].add(new Edge(3, 1, 40));
        graph[3].add(new Edge(3, 2, 50));
    }

    // BFS Traversal
    public static void bfs(ArrayList<Edge>[] graph) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        q.add(0);
        
        while (!q.isEmpty()) {
            int curr = q.remove();
            if (!visited[curr]) {
                System.out.print(curr + " ");
                visited[curr] = true;
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
    }

    // DFS Traversal
    public static void dfs(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                dfsUtil(graph, i, visited);
            }
        }
    }
    
    public static void dfsUtil(ArrayList<Edge>[] graph, int curr, boolean visited[]) {
        System.out.print(curr + " ");
        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!visited[e.dest]) {
                dfsUtil(graph, e.dest, visited);
            }
        }
    }

    // Has Path - using DFS
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean visited[]) {
        if (src == dest) {
            return true;
        }
        visited[src] = true;
        for (int i = 0; i < graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            if (!visited[e.dest] && hasPath(graph, e.dest, dest, visited)) {
                return true;
            }
        }
        return false;
    }

    // Connected Components
    public static void bfscc(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                bfsutil(graph, visited);
                System.out.println(); // New line for each component
            }
        }
    }
    
    public static void bfsutil(ArrayList<Edge>[] graph, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            int curr = q.remove();
            if (!visited[curr]) {
                System.out.print(curr + " ");
                visited[curr] = true;
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Create graph with 4 vertices
         * Test BFS, DFS, path finding, and connected components
         * 
         * Graph structure:
         *     0
         *    /|\
         *   1 2 3
         *   |  |/
         *   ---4
         */
        
        int V = 4;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);
        
        System.out.println("Graph Basics");
        System.out.println("Graph with " + V + " vertices");
        System.out.println("Edges: 0-1(10), 0-2(15), 0-3(30), 1-3(40), 2-3(50)");
        
        System.out.println("\nBFS Traversal:");
        bfs(graph);
        
        System.out.println("\nDFS Traversal:");
        dfs(graph);
        
        System.out.println("\nPath Finding:");
        System.out.println("Path from 0 to 3: " + hasPath(graph, 0, 3, new boolean[V]));
        System.out.println("Path from 1 to 2: " + hasPath(graph, 1, 2, new boolean[V]));
        
        System.out.println("\nConnected Components:");
        bfscc(graph);
        
        // Show step-by-step for BFS
        System.out.println("\nStep-by-step BFS from vertex 0:");
        System.out.println("Queue: [0], visited: [F,F,F,F]");
        System.out.println("Process 0: visited[0]=T, add neighbors 1,2,3");
        System.out.println("Queue: [1,2,3], visited: [T,F,F,F]");
        System.out.println("Process 1: visited[1]=T");
        System.out.println("Queue: [2,3], visited: [T,T,F,F]");
        System.out.println("Process 2: visited[2]=T");
        System.out.println("Queue: [3], visited: [T,T,T,F]");
        System.out.println("Process 3: visited[3]=T");
        System.out.println("Queue: [], visited: [T,T,T,T]");
        System.out.println("BFS complete: 0,1,2,3");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single vertex
        ArrayList<Edge>[] singleGraph = new ArrayList[1];
        singleGraph[0] = new ArrayList<>();
        System.out.println("Single vertex graph:");
        System.out.print("BFS: ");
        bfs(singleGraph);
        
        // Test case 3: Disconnected graph
        ArrayList<Edge>[] disconnectedGraph = new ArrayList[6];
        for (int i = 0; i < 6; i++) {
            disconnectedGraph[i] = new ArrayList<>();
        }
        disconnectedGraph[0].add(new Edge(0, 1, 1));
        disconnectedGraph[1].add(new Edge(1, 0, 1));
        disconnectedGraph[2].add(new Edge(2, 3, 1));
        disconnectedGraph[3].add(new Edge(3, 2, 1));
        disconnectedGraph[4].add(new Edge(4, 5, 1));
        disconnectedGraph[5].add(new Edge(5, 4, 1));
        
        System.out.println("\nDisconnected graph (3 components):");
        System.out.println("Connected Components:");
        bfscc(disconnectedGraph);
    }
}
