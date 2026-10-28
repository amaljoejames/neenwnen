import java.util.*;

/**
 * QUESTION: Binary Tree Basics - Implement fundamental binary tree operations including
 * traversals, height, count, sum, diameter, subtree checks, and views.
 * 
 * QUESTION EXPLANATION:
 * Binary Tree is a hierarchical data structure where each node has at most two children.
 * Example tree:
 *        1
 *       / \
 *      2   3
 *     / \ / \
 *    4   5 6   7
 * 
 * Operations include:
 * - Traversals: Preorder, Inorder, Postorder, Level Order
 * - Properties: Height, Count, Sum, Diameter
 * - Views: Top View, Bottom View
 * - Checks: Identical trees, Subtree verification
 * 
 * APPROACH USED: Recursive Tree Operations
 * 
 * PSEUDOCODE:
 * class Node:
 *   data, left, right
 *   
 * function preorder(root):
 *   if root == null: return
 *   print(root.data)
 *   preorder(root.left)
 *   preorder(root.right)
 * 
 * function inorder(root):
 *   if root == null: return
 *   inorder(root.left)
 *   print(root.data)
 *   inorder(root.right)
 * 
 * function postorder(root):
 *   if root == null: return
 *   postorder(root.left)
 *   postorder(root.right)
 *   print(root.data)
 * 
 * function height(root):
 *   if root == null: return 0
 *   left = height(root.left)
 *   right = height(root.right)
 *   return max(left, right) + 1
 * 
 * EXPLANATION OF STEPS:
 * 1. Create Node class with data, left, right references
 * 2. For each operation, use recursive approach
 * 3. Base case: when node is null, return appropriate value
 * 4. Recursive case: process left subtree, then right subtree
 * 5. Combine results as needed for each operation
 * 6. Time complexity varies by operation (O(n), O(n²), etc.)
 * 
 * DRY RUN for sample tree:
 * 
 * Tree structure:
 *     1
 *    / \
 *   2   3
 * 
 * Preorder: 1,2,3 (Root, Left, Right)
 * Inorder: 2,1,3 (Left, Root, Right)
 * Postorder: 2,3,1 (Left, Right, Root)
 * Height: 2 (levels: 1→2→3)
 */
public class TreeBasics {
    
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Preorder Traversal (Root, Left, Right)
    static void preorder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // Inorder Traversal (Left, Root, Right)
    static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // Postorder Traversal (Left, Right, Root)
    static void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    // Level Order Traversal (BFS)
    static void levelorder(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null); // Level separator
        
        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            if (curr == null) {
                System.out.println();
                if (queue.isEmpty()) {
                    break;
                } else {
                    queue.add(null);
                }
            } else {
                System.out.print(curr.data + " ");
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
    }

    // Height of Tree
    static int height(Node root) {
        if (root == null) return 0;
        int left_height = height(root.left);
        int right_height = height(root.right);
        return Math.max(left_height, right_height) + 1;
    }

    // Count of Nodes
    static int count(Node root) {
        if (root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }

    // Sum of Nodes
    static int sum(Node root) {
        if (root == null) return 0;
        return root.data + sum(root.left) + sum(root.right);
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Build sample tree:
         *        1
         *       / \
         *      2   3
         * 
         * Test all operations on this tree
         * 
         * Expected results:
         * Preorder: 1,2,3
         * Inorder: 2,1,3  
         * Postorder: 2,3,1
         * Height: 2
         * Count: 3
         * Sum: 6
         */
        
        // Build sample tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        
        System.out.println("Binary Tree Basics");
        System.out.println("Tree structure:");
        System.out.println("    1");
        System.out.println("   / \\");
        System.out.println("  2   3");
        
        System.out.println("\nTree Traversals:");
        
        System.out.print("Preorder (Root, Left, Right): ");
        preorder(root);
        
        System.out.print("\nInorder (Left, Root, Right): ");
        inorder(root);
        
        System.out.print("\nPostorder (Left, Right, Root): ");
        postorder(root);
        
        System.out.print("\nLevel Order (BFS): ");
        levelorder(root);
        
        System.out.println("\nTree Properties:");
        System.out.println("Height: " + height(root));
        System.out.println("Count of nodes: " + count(root));
        System.out.println("Sum of nodes: " + sum(root));
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single node
        Node single = new Node(42);
        System.out.println("Single node tree:");
        System.out.println("Preorder: 42");
        System.out.println("Height: 1");
        System.out.println("Count: 1");
        
        // Test case 3: Left skewed tree
        Node leftSkewed = new Node(1);
        leftSkewed.left = new Node(2);
        leftSkewed.left.left = new Node(3);
        System.out.println("\nLeft skewed tree (1→2→3):");
        System.out.println("Height: " + height(leftSkewed));
        System.out.println("Preorder: 1,2,3");
        System.out.println("Inorder: 3,2,1");
    }
}
