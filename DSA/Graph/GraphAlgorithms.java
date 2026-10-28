import java.util.*;

/**
 * QUESTION: Advanced Graph Algorithms - Implement topological sorting,
 * all paths from source to destination, and shortest path algorithms.
 * 
 * QUESTION EXPLANATION:
 * Topological Sorting: Linear ordering of vertices where for every directed edge u→v,
 * u comes before v in the ordering. Used in task scheduling, dependency resolution.
 * 
 * Example DAG:
 *     5→4→2→3→1
 *     ↓     ↗
 *     0→1
 * 
 * Topological order: 5,4,2,3,1,0 or 4,5,0,2,3,1 (valid variations)
 * 
 * All Paths: Find all possible paths from source to destination in DAG.
 * Example: Paths from 5 to 1 in above graph
 * 
 * Shortest Path: Dijkstra's algorithm for weighted graphs.
 * Example: Find shortest distances from source to all vertices.
 * 
 * APPROACH USED: DFS for Topological Sort, Dijkstra for Shortest Paths
 * 
 * PSEUDOCODE:
 * 
 * Topological Sort (DFS):
 * function topologicalSort(graph):
 *   visited = boolean[graph.length]
 *   stack = new Stack()
 *   for i = 0 to graph.length-1:
 *     if !visited[i]:
 *       topSortUtil(graph, i, visited, stack)
 *   
 *   while !stack.isEmpty():
 *     print(stack.pop())
 * 
 * function topSortUtil(graph, curr, visited, stack):
 *   visited[curr] = true
 *   for each edge in graph[curr]:
 *     if !visited[edge.dest]:
 *       topSortUtil(graph, edge.dest, visited, stack)
 *   stack.push(curr)
 * 
 * Topological Sort (BFS - Kahn's Algorithm):
 * function topologicalSortBFS(graph):
 *   indegree = int[graph.length]
 *   calculateIndegrees(graph, indegree)
 *   queue = new Queue()
 *   
 *   for i = 0 to graph.length-1:
 *     if indegree[i] == 0:
 *       queue.add(i)
 *   
 *   while !queue.isEmpty():
 *     curr = queue.remove()
 *     print(curr)
 *     for each edge in graph[curr]:
 *       indegree[edge.dest]--
 *       if indegree[edge.dest] == 0:
 *         queue.add(edge.dest)
 * 
 * All Paths:
 * function allPaths(graph, src, dest, path):
 *   if src == dest:
 *     print(path + dest)
 *     return
 *   
 *   for each edge in graph[src]:
 *     allPaths(graph, edge.dest, dest, path + src)
 * 
 * Dijkstra's Algorithm:
 * function dijkstra(graph, src):
 *   dist = int[graph.length]
 *   visited = boolean[graph.length]
 *   fill dist with INF, dist[src] = 0
 *   
 *   pq = new PriorityQueue<Pair>()
 *   pq.add(new Pair(src, 0))
 *   
 *   while !pq.isEmpty():
 *     curr = pq.poll()
 *     if visited[curr.n]: continue
 *     visited[curr.n] = true
 *     
 *     for each edge in graph[curr.n]:
 *       if !visited[edge.dest] && dist[curr.n] + edge.wt < dist[edge.dest]:
 *         dist[edge.dest] = dist[curr.n] + edge.wt
 *         pq.add(new Pair(edge.dest, dist[edge.dest]))
 * 
 * EXPLANATION OF STEPS:
 * 1. Topological Sort: Use DFS, push vertices to stack after exploring all neighbors
 * 2. Kahn's Algorithm: Use BFS with indegree tracking
 * 3. All Paths: Use DFS with path building and backtracking
 * 4. Dijkstra: Use priority queue for greedy shortest path selection
 * 
 * DRY RUN for Topological Sort (DFS):
 * 
 * Graph: 5→4→2→3→1, 5→0→1
 * 
 * topSortUtil(graph, 5, visited, stack)
 * - visited[5] = true
 * - Edge 5-4: topSortUtil(graph, 4, visited, stack)
 *   - visited[4] = true
 *   - Edge 4-2: topSortUtil(graph, 2, visited, stack)
 *     - visited[2] = true
 *     - Edge 2-3: topSortUtil(graph, 3, visited, stack)
 *       - visited[3] = true
 *       - Edge 3-1: topSortUtil(graph, 1, visited, stack)
 *         - visited[1] = true
 *         - No outgoing edges
 *         - stack.push(1)
 *       - stack.push(3)
 *     - stack.push(2)
 *   - stack.push(4)
 * - Edge 5-0: topSortUtil(graph, 0, visited, stack)
 *   - visited[0] = true
 *   - Edge 0-1: already visited
 *   - stack.push(0)
 * - stack.push(5)
 * 
 * Stack: [1,3,2,4,0,5] (top to bottom)
 * Pop order: 5,0,4,2,3,1
 */
public class GraphAlgorithms {
    
    static class Edge {
        int src, dest, wt;
        public Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    static class Pair implements Comparable<Pair> {
        int n, path;
        
        public Pair(int n, int path) {
            this.n = n;
            this.path = path;
        }

        @Override
        public int compareTo(Pair p2) {
            return Integer.compare(this.path, p2.path);
        }
    }

    // Topological Sorting (DFS)
    public static void topologicalSort(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];
        Stack<Integer> s = new Stack<>();
        
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                topSortUtil(graph, i, vis, s);
            }
        }
        
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
    }
    
    public static void topSortUtil(ArrayList<Edge>[] graph, int curr, boolean[] vis, Stack<Integer> s) {
        vis[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                topSortUtil(graph, e.dest, vis, s);
            }
        }
        s.push(curr);
    }

    // Topological Sorting (BFS - Kahn's Algorithm)
    public static void calcIndeg(ArrayList<Edge>[] graph, int indegree[]) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                indegree[e.dest]++;
            }
        }
    }
    
    public static void topologicalSortBFS(ArrayList<Edge>[] graph) {
        int[] indegree = new int[graph.length];
        calcIndeg(graph, indegree);
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < graph.length; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        
        while (!q.isEmpty()) {
            int curr = q.remove();
            System.out.print(curr + " ");
            for (int i = 0; i < graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                indegree[e.dest]--;
                if (indegree[e.dest] == 0) {
                    q.add(e.dest);
                }
            }
        }
    }

    // All paths from source to destination
    public static void allPathsFromSourceToDestination(ArrayList<Edge> graph[], int src, int dest, String path) {
        if (src == dest) {
            System.out.println(path + dest);
            return;
        }
        for (int i = 0; i < graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            allPathsFromSourceToDestination(graph, e.dest, dest, path + src);
        }
    }

    // Dijkstra's Algorithm
    public static void dijkstra(ArrayList<Edge>[] graph, int src) {
        int dist[] = new int[graph.length];
        boolean visited[] = new boolean[graph.length];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();

            if (visited[curr.n]) continue;
            visited[curr.n] = true;

            for (Edge e : graph[curr.n]) {
                int v = e.dest, wt = e.wt;

                if (!visited[v] && dist[curr.n] + wt < dist[v]) {
                    dist[v] = dist[curr.n] + wt;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }

        // Print shortest distances
        for (int i = 0; i < dist.length; i++) {
            System.out.println("Vertex: " + i + " Distance: " + dist[i]);
        }
    }

    // Helper to create DAG for topological sort
    static void createDAG(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        // DAG: 5→4→2→3→1, 5→0→1
        graph[5].add(new Edge(5, 4, 0));
        graph[5].add(new Edge(5, 0, 0));
        graph[4].add(new Edge(4, 2, 0));
        graph[2].add(new Edge(2, 3, 0));
        graph[3].add(new Edge(3, 1, 0));
        graph[0].add(new Edge(0, 1, 0));
    }

    // Helper to create weighted graph for Dijkstra
    static void createWeightedGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));
        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 2, 1));
        graph[2].add(new Edge(2, 4, 3));
        graph[3].add(new Edge(3, 5, 1));
        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test topological sorting, all paths, and Dijkstra's algorithm
         * 
         * DAG for topological sort: 5→4→2→3→1, 5→0→1
         * Weighted graph for Dijkstra with 6 vertices
         */
        
        // Test Topological Sort
        int V = 6;
        ArrayList<Edge>[] dag = new ArrayList[V];
        createDAG(dag);
        
        System.out.println("Advanced Graph Algorithms");
        System.out.println("DAG for topological sort:");
        System.out.println("5→4→2→3→1");
        System.out.println("↓     ↗");
        System.out.println("0→1");
        
        System.out.println("\nTopological Sort (DFS):");
        topologicalSort(dag);
        
        System.out.println("\nTopological Sort (BFS - Kahn's):");
        topologicalSortBFS(dag);
        
        System.out.println("\nAll paths from 5 to 1:");
        allPathsFromSourceToDestination(dag, 5, 1, "");
        
        // Test Dijkstra's Algorithm
        ArrayList<Edge>[] weightedGraph = new ArrayList[V];
        createWeightedGraph(weightedGraph);
        
        System.out.println("\nDijkstra's Algorithm (from vertex 0):");
        dijkstra(weightedGraph, 0);
        
        // Show step-by-step for topological sort
        System.out.println("\nStep-by-step topological sort (DFS):");
        System.out.println("Processing order: 5→4→2→3→1, then 5→0→1");
        System.out.println("Stack building:");
        System.out.println("- Visit 5, push after exploring neighbors");
        System.out.println("- Visit 4, push after exploring neighbors");
        System.out.println("- Visit 2, push after exploring neighbors");
        System.out.println("- Visit 3, push after exploring neighbors");
        System.out.println("- Visit 1, push (no neighbors)");
        System.out.println("- Visit 0, push after exploring neighbors");
        System.out.println("- Finally push 5");
        System.out.println("Stack order: [1,3,2,4,0,5]");
        System.out.println("Pop order: 5,0,4,2,3,1");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Simple DAG
        ArrayList<Edge>[] simpleDAG = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            simpleDAG[i] = new ArrayList<>();
        }
        simpleDAG[0].add(new Edge(0, 1, 0));
        simpleDAG[1].add(new Edge(1, 2, 0));
        
        System.out.println("Simple DAG (0→1→2):");
        System.out.print("Topological sort: ");
        topologicalSort(simpleDAG);
        
        // Test case 3: All paths in simple graph
        System.out.println("\nAll paths from 0 to 2:");
        allPathsFromSourceToDestination(simpleDAG, 0, 2, "");
        
        // Test case 4: Single vertex
        ArrayList<Edge>[] single = new ArrayList[1];
        single[0] = new ArrayList<>();
        System.out.println("\nSingle vertex:");
        System.out.print("Topological sort: ");
        topologicalSort(single);
    }
}
