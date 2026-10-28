import java.util.*;

/**
 * QUESTION: Tree Operations - Implement various binary tree operations including
 * subtree checks, kth ancestor, minimum distance, tree transformation,
 * and mirror operations.
 * 
 * QUESTION EXPLANATION:
 * Operations covered:
 * 1. Check if two trees are identical
 * 2. Check if one tree is subtree of another
 * 3. Find kth ancestor of a node
 * 4. Find minimum distance between two nodes
 * 5. Transform tree to sum tree
 * 6. Mirror a binary tree
 * 7. Delete leaves with given value
 * 8. Find maximum path sum
 * 
 * APPROACH USED: Recursive Tree Algorithms
 * 
 * PSEUDOCODE:
 * 
 * Check Identical Trees:
 * function isIdentical(root, subroot):
 *   if root == null && subroot == null: return true
 *   if root == null || subroot == null || root.data != subroot.data: return false
 *   return isIdentical(root.left, subroot.left) && isIdentical(root.right, subroot.right)
 * 
 * Check Subtree:
 * function isSubtree(root, subroot):
 *   if root == null: return false
 *   if subroot == null: return true
 *   if isIdentical(root, subroot): return true
 *   return isSubtree(root.left, subroot) || isSubtree(root.right, subroot)
 * 
 * Kth Ancestor:
 * function kthAncestor(root, n, k):
 *   if root == null: return -1
 *   if root.data == n: return 0
 *   left = kthAncestor(root.left, n, k)
 *   right = kthAncestor(root.right, n, k)
 *   if left == -1 && right == -1: return -1
 *   max = max(left, right)
 *   if max + 1 == k: print result
 *   return max + 1
 * 
 * Minimum Distance:
 * function minDist(root, n1, n2):
 *   lca = lca(root, n1, n2)
 *   return getDist(lca, n1) + getDist(lca, n2)
 * 
 * EXPLANATION OF STEPS:
 * 1. Each operation uses recursive tree traversal
 * 2. Base cases handle null nodes and target conditions
 * 3. Recursive cases process left and right subtrees
 * 4. Results combined according to operation requirements
 * 5. Time complexity varies: O(n), O(n²), O(log n) for different operations
 * 
 * DRY RUN for sample operations:
 * 
 * For identical check with trees (1,2,3) and (1,2,3):
 * - Root data matches, recursively check subtrees
 * - All nodes match → return true
 * 
 * For kth ancestor with k=2, node=5:
 * - Path: 5→3→1, 2nd ancestor from 5 is 3
 */
public class TreeOperations {
    
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Check if Two Trees are Identical
    public static boolean isIdentical(Node root, Node subroot) {
        if (root == null && subroot == null) return true;
        if (root == null || subroot == null || root.data != subroot.data) return false;
        return isIdentical(root.left, subroot.left) && isIdentical(root.right, subroot.right);
    }

    // Check if One Tree is a Subtree of Another
    public static boolean isSubtree(Node root, Node subroot) {
        if (root == null) return false;
        if (subroot == null) return true;
        if (isIdentical(root, subroot)) return true;
        return isSubtree(root.left, subroot) || isSubtree(root.right, subroot);
    }

    // Kth Level Node
    public static void kthlevel(Node root, int level, int k) {
        if (root == null) return;
        if (level == k) {
            System.out.print(root.data + " ");
            return;
        }
        kthlevel(root.left, level + 1, k);
        kthlevel(root.right, level + 1, k);
    }

    // LCA - Approach 1
    public static Node lca(Node root, int n1, int n2) {
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        if (!getpath(root, n1, path1) || !getpath(root, n2, path2)) {
            return null;
        }

        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {
            if (!path1.get(i).equals(path2.get(i))) {
                break;
            }
        }
        Node lca = path1.get(i - 1);
        return lca;
    }

    // Helper function to find path from root to target
    public static boolean getpath(Node root, int n, ArrayList<Node> path) {
        if (root == null) return false;

        path.add(root);

        if (root.data == n) return true;

        if (getpath(root.left, n, path) || getpath(root.right, n, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    // LCA - Approach 2
    public static Node lca2(Node root, int n1, int n2) {
        if (root == null) return root;
        if (root.data == n1 || root.data == n2) return root;
        Node left = lca2(root.left, n1, n2);
        Node right = lca2(root.right, n1, n2);
        if (right == null) {
            return left;
        }
        if (left == null) {
            return right;
        }
        return root;
    }

    // Minimum distance between nodes
    public static int getDist(Node root, int n) {
        if (root == null) return -1;
        if (root.data == n) return 0;
        int left = getDist(root.left, n);
        int right = getDist(root.right, n);
        return left == -1 ? right + 1 : left + 1;
    }

    // Function to find minimum distance between n1 and n2
    public static int minDist(Node root, int n1, int n2) {
        Node lcaNode = lca(root, n1, n2);
        return getDist(lcaNode, n1) + getDist(lcaNode, n2);
    }

    // Kth Ancestor
    public static int kthAncestor(Node root, int n, int k) {
        if (root == null) return -1;
        if (root.data == n) return 0;
        int left = kthAncestor(root.left, n, k);
        int right = kthAncestor(root.right, n, k);
        if (left == -1 && right == -1) return -1;
        int max = Math.max(left, right);
        if (max + 1 == k) {
            System.out.println("The " + k + "th ancestor of node " + n + " is: " + root.data);
        }
        return max + 1;
    }

    // Transform to Sum Tree
    public static int transformToSumTree(Node root) {
        if (root == null) {
            return 0;
        }
        int leftSum = transformToSumTree(root.left);
        int rightSum = transformToSumTree(root.right);
        int oldValue = root.data;
        root.data = leftSum + rightSum + root.left.data + root.right.data;
        return oldValue;
    }

    // Mirror Tree
    public static Node mirror(Node node) {
        if (node == null) return node;
        Node left = mirror(node.left);
        Node right = mirror(node.right);
        node.left = right;
        node.right = left;
        return node;
    }

    // Delete Leaves with a Given Value
    public static Node deleteLeaves(Node root, int x) {
        if (root == null) return null;
        root.left = deleteLeaves(root.left, x);
        root.right = deleteLeaves(root.right, x);
        if (root.data == x && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }

    // Maximum Path Sum
    static class Res {
        public int val;
    }

    static int findMaxUtil(Node node, Res res) {
        if (node == null) return 0;
        int l = findMaxUtil(node.left, res);
        int r = findMaxUtil(node.right, res);
        int max_single = Math.max(Math.max(l, r) + node.data, node.data);
        int max_top = Math.max(max_single, l + r + node.data);
        res.val = Math.max(res.val, max_top);
        return max_single;
    }

    static int findMaxSum(Node node) {
        Res res = new Res();
        res.val = Integer.MIN_VALUE;
        findMaxUtil(node, res);
        return res.val;
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
         * Test various operations on this tree
         */
        
        // Build sample tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        
        System.out.println("Tree Operations Problem");
        System.out.println("Tree structure:");
        System.out.println("        1");
        System.out.println("       / \\");
        System.out.println("      2   3");
        System.out.println("     / \\ / \\");
        System.out.println("    4   5 6   7");
        
        // Test identical trees
        Node subroot = new Node(1);
        subroot.left = new Node(2);
        subroot.right = new Node(3);
        subroot.left.left = new Node(4);
        subroot.left.right = new Node(5);
        
        System.out.println("\nIdentical Trees Check:");
        System.out.println("Tree 1 and Tree 2 are identical: " + isIdentical(root, subroot));
        
        // Test subtree check
        System.out.println("\nSubtree Check:");
        System.out.println("Is subroot a subtree of main tree: " + isSubtree(root, subroot));
        
        // Test kth level
        System.out.println("\nKth Level (k=2):");
        System.out.print("Nodes at level 2: ");
        kthlevel(root, 1, 2);
        
        // Test LCA
        System.out.println("\nLCA of nodes 4 and 6:");
        Node lca1 = lca(root, 4, 6);
        Node lca2 = lca2(root, 4, 6);
        System.out.println("LCA (Approach 1): " + (lca1 != null ? lca1.data : "null"));
        System.out.println("LCA (Approach 2): " + (lca2 != null ? lca2.data : "null"));
        
        // Test minimum distance
        System.out.println("\nMinimum distance between nodes 4 and 6:");
        System.out.println("Distance: " + minDist(root, 4, 6));
        
        // Test kth ancestor
        System.out.println("\nKth ancestor of node 7, k=2:");
        kthAncestor(root, 7, 2);
        
        // Test transform to sum tree
        System.out.println("\nTransform to sum tree:");
        System.out.println("Original tree sum: " + (1+2+3+4+5+6+7));
        int transformedSum = transformToSumTree(root);
        System.out.println("Transformed tree sum: " + transformedSum);
        
        // Test maximum path sum
        System.out.println("\nMaximum path sum:");
        System.out.println("Max path sum: " + findMaxSum(root));
    }
}
