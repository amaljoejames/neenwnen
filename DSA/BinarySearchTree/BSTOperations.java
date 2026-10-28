import java.util.*;

/**
 * QUESTION: Advanced BST Operations - Implement complex BST operations including
 * mirror, sorted array to balanced BST, BST to balanced BST, 
 * largest BST in binary tree, merging BSTs, and size calculations.
 * 
 * QUESTION EXPLANATION:
 * Advanced operations covered:
 * 1. Mirror a BST (swap left and right subtrees)
 * 2. Convert sorted array to balanced BST
 * 3. Convert BST to balanced BST
 * 4. Find largest BST in binary tree
 * 5. Merge two BSTs into one
 * 6. Calculate size of largest BST
 * 
 * APPROACH USED: Recursive BST Algorithms with Optimization
 * 
 * PSEUDOCODE:
 * 
 * Mirror BST:
 * function mirror(root):
 *   if root == null: return null
 *   left = mirror(root.left)
 *   right = mirror(root.right)
 *   root.left = right
 *   root.right = left
 *   return root
 * 
 * Sorted Array to Balanced BST:
 * function sortedArrayToBalancedBST(arr, si, ei):
 *   if si > ei: return null
 *   mid = (si + ei) / 2
 *   root = new Node(arr[mid])
 *   root.left = sortedArrayToBalancedBST(arr, si, mid-1)
 *   root.right = sortedArrayToBalancedBST(arr, mid+1, ei)
 *   return root
 * 
 * Largest BST in Binary Tree:
 * function largestBst(root):
 *   if root == null: return new Info(false, 0, MAX, MIN)
 *   leftInfo = largestBst(root.left)
 *   rightInfo = largestBst(root.right)
 *   size = leftInfo.size + rightInfo.size + 1
 *   min = Math.min(root.data, Math.min(leftInfo.min, rightInfo.min))
 *   max = Math.max(root.data, Math.max(leftInfo.max, rightInfo.max))
 *   
 *   // Check if current node maintains BST property
 *   if (!leftInfo.isBst || !rightInfo.isBst || 
 *       root.data <= leftInfo.max || root.data >= rightInfo.min):
 *     return new Info(false, 0, MAX, MIN)
 *   
 *   maxBST = Math.max(maxBST, size)
 *   return new Info(true, size, min, max)
 * 
 * EXPLANATION OF STEPS:
 * 1. Mirror: Recursively swap left and right subtrees
 * 2. Array to BST: Use divide and conquer for balanced construction
 * 3. BST to Balanced: Convert to array, then back to BST
 * 4. Largest BST: For each node, check if subtree is BST and track maximum size
 * 5. Merge BSTs: Get inorder traversals, merge, convert back to BST
 * 
 * DRY RUN for sample operations:
 * 
 * Mirror BST with tree (1,2,3):
 * - Original: left(2), right(3)
 * - After mirror: left(3), right(2)
 * 
 * Largest BST from binary tree:
 * - Check each node if its subtree is valid BST
 * - Track maximum size found
 * 
 * Merge two BSTs:
 * - Get inorder of both: [1,2,3] and [4,5,6]
 * - Merge: [1,2,3,4,5,6]
 * - Convert back to balanced BST
 */
public class BSTOperations {
    
    static int maxBST = 0;
    
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
        boolean isBst;
        int size;
        int min;
        int max;

        public Info(boolean isBst, int size, int min, int max) {
            this.isBst = isBst;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    // Mirror a BST
    static Node mirror(Node root) {
        if (root == null) return null;
        Node left = mirror(root.left);
        Node right = mirror(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // Convert sorted array to balanced BST
    static Node sortedArrayToBalancedBST(int[] arr, int si, int ei) {
        if (si > ei) {
            return null;
        }
        int mid = (si + ei) / 2;
        Node root = new Node(arr[mid]);
        root.left = sortedArrayToBalancedBST(arr, si, mid - 1);
        root.right = sortedArrayToBalancedBST(arr, mid + 1, ei);
        return root;
    }

    // Get inorder traversal of BST
    static void getInorder(Node root, ArrayList<Integer> inorder) {
        if (root == null) return;
        getInorder(root.left, inorder);
        inorder.add(root.data);
        getInorder(root.right, inorder);
    }

    // Convert BST to balanced BST
    static Node convertToBalancedBST(Node root) {
        ArrayList<Integer> inorder = new ArrayList<>();
        getInorder(root, inorder);
        return createBst(inorder, 0, inorder.size() - 1);
    }

    // Create BST from inorder array
    static Node createBst(ArrayList<Integer> inorder, int si, int ei) {
        if (si > ei) {
            return null;
        }
        int mid = (si + ei) / 2;
        Node root = new Node(inorder.get(mid));
        root.left = createBst(inorder, si, mid - 1);
        root.right = createBst(inorder, mid + 1, ei);
        return root;
    }

    // Find largest BST in binary tree
    static Info largestBst(Node root) {
        maxBST = 0;
        return largestBstHelper(root);
    }
    
    // Helper method for recursive largest BST calculation
    static Info largestBstHelper(Node root) {
        if (root == null) {
            return new Info(false, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Info leftInfo = largestBstHelper(root.left);
        Info rightInfo = largestBstHelper(root.right);

        int size = leftInfo.size + rightInfo.size + 1;
        int min = Math.min(root.data, Math.min(leftInfo.min, rightInfo.min));
        int max = Math.max(root.data, Math.max(leftInfo.max, rightInfo.max));

        // Check if current node maintains BST property
        if (!leftInfo.isBst || !rightInfo.isBst || 
            root.data <= leftInfo.max || root.data >= rightInfo.min) {
            return new Info(false, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        maxBST = Math.max(maxBST, size);
        return new Info(true, size, min, max);
    }

    // Merge two BSTs
    static Node merge(Node root1, Node root2) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        getInorder(root1, arr1);
        getInorder(root2, arr2);

        // Merge sorted arrays
        int i = 0, j = 0;
        ArrayList<Integer> merged = new ArrayList<>();
        while (i < arr1.size() && j < arr2.size()) {
            if (arr1.get(i) <= arr2.get(j)) {
                merged.add(arr1.get(i));
                i++;
            } else {
                merged.add(arr2.get(j));
                j++;
            }
        }

        // Add remaining elements
        while (i < arr1.size()) {
            merged.add(arr1.get(i));
            i++;
        }
        while (j < arr2.size()) {
            merged.add(arr2.get(j));
            j++;
        }

        return createBst(merged, 0, merged.size() - 1);
    }

    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test various BST operations
         * 
         * Mirror test: Tree (1,2,3) → Mirror (1,3,2)
         * Largest BST test: Find maximum BST subtree in binary tree
         * Merge test: Combine two BSTs into one
         * 
         * Expected results shown in step-by-step
         */
        
        // Test mirror operation
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        
        System.out.println("Advanced BST Operations");
        System.out.println("Original tree:");
        System.out.println("    1");
        System.out.println("   / \\");
        System.out.println("  2   3");
        
        Node mirrored = mirror(root);
        System.out.println("\nMirrored tree:");
        System.out.println("    1");
        System.out.println("   / \\");
        System.out.println("  3   2");
        
        // Test sorted array to balanced BST
        int[] sorted = {3, 5, 6, 8, 10, 11, 12};
        Node balancedBST = sortedArrayToBalancedBST(sorted, 0, sorted.length - 1);
        System.out.println("\nBalanced BST from sorted array:");
        System.out.println("Inorder should be: " + Arrays.toString(sorted));
        
        // Test largest BST in binary tree
        Node binaryTree = new Node(10);
        binaryTree.left = new Node(5);
        binaryTree.right = new Node(15);
        binaryTree.left.left = new Node(1);
        binaryTree.left.right = new Node(8);
        binaryTree.right.left = new Node(12);
        binaryTree.right.right = new Node(20);
        
        Info largestInfo = largestBst(binaryTree);
        System.out.println("\nLargest BST in binary tree:");
        System.out.println("Size: " + largestInfo.size);
        System.out.println("Min: " + largestInfo.min);
        System.out.println("Max: " + largestInfo.max);
        System.out.println("Is BST: " + largestInfo.isBst);
        
        // Test merge BSTs
        Node bst1 = new Node(2);
        bst1.left = new Node(1);
        bst1.right = new Node(4);
        
        Node bst2 = new Node(9);
        bst2.left = new Node(3);
        bst2.right = new Node(12);
        
        Node merged = merge(bst1, bst2);
        System.out.println("\nMerge two BSTs:");
        System.out.println("BST1: [1,2,4], BST2: [3,9,12]");
        System.out.println("Merged BST created successfully");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single node mirror
        Node single = new Node(42);
        Node mirroredSingle = mirror(single);
        System.out.println("Single node mirror: " + (mirroredSingle != null && mirroredSingle.data == 42));
        
        // Test case 3: Empty operations
        System.out.println("Empty tree mirror: " + (mirror(null) == null));
        System.out.println("Empty array to BST: " + (sortedArrayToBalancedBST(new int[0], 0, -1) == null));
    }
}
