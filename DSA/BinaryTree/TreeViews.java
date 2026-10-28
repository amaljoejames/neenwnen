import java.util.*;

/**
 * QUESTION: Tree Views - Implement top view and bottom view of a binary tree.
 * Top view shows nodes visible from top looking down.
 * Bottom view shows nodes visible from bottom looking up.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * Tree:
 *        1
 *       / \
 *      2   3
 *     / \ / \
 *    4   5 6   7
 * 
 * Top view: 4, 2, 1, 3, 7
 * (Nodes visible from top at horizontal distances -2, -1, 0, 1, 2)
 * 
 * Bottom view: 4, 2, 6, 7
 * (Nodes visible from bottom at horizontal distances -2, -1, 0, 1, 2)
 * 
 * APPROACH USED: Level Order Traversal with Horizontal Distance Tracking
 * 
 * PSEUDOCODE:
 * 
 * Top View:
 * function topView(root):
 *   if root == null: return
 *   queue = Queue<Info>()
 *   map = HashMap<Integer, Node>()
 *   queue.add(Info(0, root))
 *   min = 0, max = 0
 *   
 *   while !queue.isEmpty():
 *     curr = queue.remove()
 *     if curr == null:
 *       if !queue.isEmpty(): break
 *       else: queue.add(null)
 *       continue
 *     
 *     if !map.containsKey(curr.hd):
 *       map.put(curr.hd, curr.node)
 *     
 *     if curr.node.left != null:
 *       queue.add(Info(curr.hd - 1, curr.node.left))
 *       min = Math.min(curr.hd - 1, min)
 *     
 *     if curr.node.right != null:
 *       queue.add(Info(curr.hd + 1, curr.node.right))
 *       max = Math.max(curr.hd + 1, max)
 *   
 *   for i = min to max:
 *     print(map.get(i).data)
 * 
 * Bottom View:
 * function bottomViewHelper(root, curr, hd, map):
 *   if root == null: return
 *   
 *   if !map.containsKey(hd):
 *     map.put(hd, new int[]{root.data, curr})
 *   else:
 *     p = map.get(hd)
 *     if p[1] <= curr:
 *       p[1] = curr
 *       p[0] = root.data
 *     map.put(hd, p)
 *   
 *   bottomViewHelper(root.left, curr + 1, hd - 1, map)
 *   bottomViewHelper(root.right, curr + 1, hd + 1, map)
 * 
 * EXPLANATION OF STEPS:
 * 1. Use level order traversal with horizontal distance tracking
 * 2. For top view: store first node at each horizontal distance
 * 3. For bottom view: store last node at each horizontal distance
 * 4. Horizontal distance: left = parent-1, right = parent+1
 * 5. Print nodes in order of horizontal distance
 * 
 * DRY RUN for sample tree:
 * 
 * Level order with HD tracking:
 * Level 0: (1, hd=0) → map[0]=1, min=0, max=0
 * Level 1: (2,hd=-1), (3,hd=1) → map[-1]=2, map[1]=3, min=-1, max=1
 * Level 2: (4,hd=-2), (5,hd=0), (6,hd=2), (7,hd=3) → update map
 * 
 * Final map: {-2:4, -1:2, 0:1, 1:3, 2:6, 3:7}
 * Top view: 4,2,1,3,7
 * Bottom view: 4,2,6,7
 */
public class TreeViews {
    
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class Info {
        int hd; // Horizontal Distance
        Node node;

        public Info(int hd, Node node) {
            this.hd = hd;
            this.node = node;
        }
    }

    // Top View of a Tree
    public static void topView(Node root) {
        if (root == null) return;
        
        Queue<Info> q = new LinkedList<>();
        HashMap<Integer, Node> map = new HashMap<>();
        int min = 0, max = 0;
        
        q.add(new Info(0, root));
        q.add(null); // Level separator
        
        while (!q.isEmpty()) {
            Info curr = q.remove();
            if (curr == null) { // End of level
                if (!q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
                continue;
            }

            if (!map.containsKey(curr.hd)) {
                map.put(curr.hd, curr.node);
            }
            
            if (curr.node.left != null) {
                q.add(new Info(curr.hd - 1, curr.node.left));
                min = Math.min(curr.hd - 1, min);
            } 
            
            if (curr.node.right != null) {
                q.add(new Info(curr.hd + 1, curr.node.right));
                max = Math.max(curr.hd + 1, max);
            }
        }
        
        for (int i = min; i <= max; i++) {
            System.out.print(map.get(i).data + " ");
        }
    }

    // Bottom view helper function
    public static void bottomViewHelper(Node root, int curr, int hd, TreeMap<Integer, int[]> m) {
        if (root == null) return;

        // If node for a particular HD is not present, add to the map.
        if (!m.containsKey(hd)) {
            m.put(hd, new int[]{root.data, curr});
        } else {
            int[] p = m.get(hd);
            if (p[1] <= curr) {
                p[1] = curr;
                p[0] = root.data;
            }
            m.put(hd, p);
        }

        // Recursive calls for left and right subtrees
        bottomViewHelper(root.left, curr + 1, hd - 1, m);
        bottomViewHelper(root.right, curr + 1, hd + 1, m);
    }

    public static void printBottomView(Node root) {
        // Map to store Horizontal Distance, Height, and Data.
        TreeMap<Integer, int[]> m = new TreeMap<>();
        bottomViewHelper(root, 0, 0, m);

        // Print the bottom view
        for (int val[] : m.values()) {
            System.out.print(val[0] + " ");
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Build sample tree:
         *        1
         *       / \
         *      2   3
         *     / \ / \
         *    4   5 6   7
         * 
         * Expected top view: 4,2,1,3,7
         * Expected bottom View: 4,2,6,7
         * 
         * Step-by-step:
         * Level 0: (1, hd=0) → map[0]=1
         * Level 1: (2,hd=-1), (3,hd=1) → map[-1]=2, map[1]=3
         * Level 2: Update with nodes 4,5,6,7
         * 
         * Final map: {-2:4, -1:2, 0:1, 1:3, 2:6, 3:7}
         */
        
        // Build sample tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        
        System.out.println("Tree Views Problem");
        System.out.println("Tree structure:");
        System.out.println("        1");
        System.out.println("       / \\");
        System.out.println("      2   3");
        System.out.println("     / \\ / \\");
        System.out.println("    4   5 6   7");
        
        System.out.println("\nTop View:");
        System.out.print("Nodes visible from top: ");
        topView(root);
        
        System.out.println("\nBottom View:");
        System.out.print("Nodes visible from bottom: ");
        printBottomView(root);
        
        // Show step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("Level order with horizontal distance tracking:");
        System.out.println("Level 0: Node 1 at HD 0 → map[0]=1");
        System.out.println("Level 1: Node 2 at HD -1, Node 3 at HD 1");
        System.out.println("Level 2: Nodes 4(HD -2), 5(HD 0), 6(HD 2), 7(HD 3)");
        System.out.println("Final HD map: {-2:4, -1:2, 0:1, 1:3, 2:6, 3:7}");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Left skewed tree
        Node leftSkewed = new Node(1);
        leftSkewed.left = new Node(2);
        leftSkewed.left.left = new Node(3);
        
        System.out.println("Left skewed tree (1→2→3):");
        System.out.print("Top View: ");
        topView(leftSkewed);
        System.out.print("\nBottom View: ");
        printBottomView(leftSkewed);
        
        // Test case 3: Single node
        Node single = new Node(42);
        System.out.println("\nSingle node:");
        System.out.print("Top View: ");
        topView(single);
        System.out.print("\nBottom View: ");
        printBottomView(single);
    }
}
