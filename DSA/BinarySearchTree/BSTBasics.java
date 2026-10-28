import java.util.*;

/**
 * QUESTION: Binary Search Tree Basics - Implement fundamental BST operations including
 * insert, search, delete, traversal, validation, and range printing.
 * 
 * QUESTION EXPLANATION:
 * Binary Search Tree is a binary tree with ordering property:
 * - Left subtree contains nodes < current node
 * - Right subtree contains nodes > current node
 * - No duplicate values allowed
 * 
 * Example BST:
 *        8
 *       / \
 *      3   10
 *     / \ / \
 *    1   6  12  14
 * 
 * Operations covered:
 * 1. Insert nodes maintaining BST property
 * 2. Search for values efficiently
 * 3. Delete nodes while maintaining structure
 * 4. Various traversals
 * 5. Range printing
 * 6. BST validation
 * 
 * APPROACH USED: Recursive BST Operations
 * 
 * PSEUDOCODE:
 * class Node:
 *   data, left, right
 *   
 * function insert(root, val):
 *   if root == null: return new Node(val)
 *   if val < root.data:
 *     root.left = insert(root.left, val)
 *   else:
 *     root.right = insert(root.right, val)
 *   return root
 * 
 * function search(root, val):
 *   if root == null: return false
 *   if root.data == val: return true
 *   if val < root.data:
 *     return search(root.left, val)
 *   else:
 *     return search(root.right, val)
 * 
 * EXPLANATION OF STEPS:
 * 1. Create Node class with data and child references
 * 2. Insert: Compare value with current node, place in left or right subtree
 * 3. Search: Use BST property to eliminate half the tree at each step
 * 4. Delete: Find node, maintain BST property by rearranging
 * 5. Traversals: Same as binary tree but with BST ordering
 * 6. Time complexity: O(log n) average, O(n) worst case
 * 
 * DRY RUN for building BST from [8,5,10,3,6,11,12]:
 * 
 * Insert 8: root = 8
 * Insert 5: 5 < 8 → root.left = 5
 * Insert 10: 10 > 8 → root.right = 10
 * Insert 3: 3 < 8 → go left, 3 < 5 → left.left = 3
 * Insert 6: 6 > 3 → go right, 6 < 5 → left.right = 6
 * 
 * Final BST structure as shown above
 */
public class BSTBasics {
    
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Insert a value in BST
    public static Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }
        if (root.data > val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    // Search for a value in BST
    static boolean search(Node root, int val) {
        if (root == null) return false;
        if (root.data == val) return true;
        if (root.data > val) {
            return search(root.left, val);
        } else {
            return search(root.right, val);
        }
    }

    // Inorder traversal (gives sorted order)
    static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // Delete a node from BST
    static Node delete(Node root, int val) {
        if (root == null) return root;
        
        if (root.data > val) {
            root.left = delete(root.left, val);
            return root;
        } else if (root.data < val) {
            root.right = delete(root.right, val);
            return root;
        } else {
            // Node to be deleted found
            if (root.left == null && root.right == null) {
                return null; // Leaf node
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                // Find inorder successor
                Node IS = findInorderSucc(root.right);
                root.data = IS.data;
                root.right = delete(root.right, IS.data);
                return root;
            }
        }
    }

    // Find inorder successor (smallest in right subtree)
    static Node findInorderSucc(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // Print nodes in range [k1, k2]
    static void printInRange(Node root, int k1, int k2) {
        if (root == null) return;
        if (root.data >= k1 && root.data <= k2) {
            System.out.print(root.data + " ");
        }
        printInRange(root.left, k1, k2);
        printInRange(root.right, k1, k2);
    }

    // Check if a tree is valid BST
    static boolean isValidBST(Node root, Node min, Node max) {
        if (root == null) return true;
        if (min != null && root.data <= min.data || max != null && root.data >= max.data) return false;
        return isValidBST(root.left, min, max) && isValidBST(root.right, min, max);
    }

    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Build BST from array: [8,5,10,3,6,11,12]
         * Expected structure:
         *        8
         *       / \
         *      3   10
         *     / \ / \
         *    1   6  12  14
         * 
         * Test various operations on this BST
         */
        
        // Build BST from array
        int[] values = {8, 5, 10, 3, 6, 11, 12};
        Node root = null;
        for (int val : values) {
            root = insert(root, val);
        }
        
        System.out.println("Binary Search Tree Basics");
        System.out.println("Built BST from array: " + Arrays.toString(values));
        System.out.println("\nInorder traversal (sorted order):");
        inorder(root);
        
        // Test search operation
        System.out.println("\nSearch operations:");
        System.out.println("Search 6: " + search(root, 6));
        System.out.println("Search 15: " + search(root, 15));
        System.out.println("Search 9: " + search(root, 9));
        
        // Test range printing
        System.out.println("\nRange printing [5, 12]:");
        printInRange(root, 5, 12);
        
        // Test BST validation
        System.out.println("\nBST validation:");
        System.out.println("Is valid BST: " + isValidBST(root, null, null));
        
        // Test delete operation
        System.out.println("\nBefore deletion:");
        System.out.println("Inorder: ");
        inorder(root);
        
        root = delete(root, 10); // Delete node 10
        System.out.println("\nAfter deleting 10:");
        System.out.println("Inorder: ");
        inorder(root);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single node
        Node single = new Node(42);
        System.out.println("Single node BST:");
        System.out.println("Search 42: " + search(single, 42));
        System.out.println("Search 100: " + search(single, 100));
        
        // Test case 3: Skewed tree
        Node skewed = new Node(1);
        skewed.right = new Node(2);
        skewed.right.right = new Node(3);
        skewed.right.right.right = new Node(4);
        
        System.out.println("\nRight skewed BST (1→2→3→4):");
        System.out.println("Search 3: " + search(skewed, 3));
        System.out.println("Search 5: " + search(skewed, 5));
        System.out.println("Is valid BST: " + isValidBST(skewed, null, null));
    }
}
