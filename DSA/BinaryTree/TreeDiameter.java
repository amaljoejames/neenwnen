import java.util.*;

/**
 * QUESTION: Tree Diameter - Find the diameter of a binary tree.
 * Diameter is the longest path between any two nodes in the tree.
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
 * Longest path: 4→2→1→3→7 (length = 4 edges)
 * Diameter = 4
 * 
 * APPROACH USED: Two approaches - O(n²) and O(n)
 * 
 * PSEUDOCODE:
 * 
 * Approach 1 - O(n²):
 * function diameter1(root):
 *   if root == null: return 0
 *   left_height = height(root.left)
 *   right_height = height(root.right)
 *   left_diameter = diameter1(root.left)
 *   right_diameter = diameter1(root.right)
 *   return max(left_height + right_height + 1, max(left_diameter, right_diameter))
 * 
 * Approach 2 - O(n):
 * function diameter2(root):
 *   if root == null: return new Info(0, 0)
 *   leftInfo = diameter2(root.left)
 *   rightInfo = diameter2(root.right)
 *   current_diameter = max(leftInfo.diameter, rightInfo.diameter)
 *   height_diameter = leftInfo.height + rightInfo.height + 1
 *   return new Info(max(leftInfo.height, rightInfo.height) + 1, max(current_diameter, height_diameter))
 * 
 * EXPLANATION OF STEPS:
 * 1. For each node, calculate height of left and right subtrees
 * 2. Calculate diameter passing through current node
 * 3. Recursively find diameters of left and right subtrees
 * 4. Return maximum of: current diameter, left diameter, right diameter
 * 5. Approach 2 optimizes by avoiding repeated height calculations
 * 
 * DRY RUN for sample tree:
 * 
 * At root (1):
 * - left_height = 2, right_height = 2
 * - left_diameter = 2, right_diameter = 2
 * - current_diameter = max(2, 2) = 2
 * - height_diameter = 2 + 2 + 1 = 5
 * - Return max(2, 5) = 5
 * 
 * Final diameter = 5
 */
public class TreeDiameter {
    
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Helper function to calculate height
    static int height(Node root) {
        if (root == null) return 0;
        int left_height = height(root.left);
        int right_height = height(root.right);
        return Math.max(left_height, right_height) + 1;
    }

    // Diameter of a Tree - Approach 1 (O(n²))
    static int diameter1(Node root) {
        if (root == null) return 0;
        int left_height = height(root.left);
        int right_height = height(root.right);
        int left_diameter = diameter1(root.left);
        int right_diameter = diameter1(root.right);
        return Math.max(left_height + right_height + 1, Math.max(left_diameter, right_diameter));
    }

    static class Info {
        int height;
        int diameter;

        public Info(int height, int diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }

    // Diameter of a Tree - Approach 2 (O(n))
    static Info diameter2(Node root) {
        if (root == null) return new Info(0, 0);
        Info leftInfo = diameter2(root.left);
        Info rightInfo = diameter2(root.right);
        int diam = Math.max(Math.max(leftInfo.diameter, rightInfo.diameter), leftInfo.height + rightInfo.height + 1);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(height, diam);
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
         * Expected diameter: 4 (path 4→2→1→3→7)
         * 
         * Step-by-step for Approach 2:
         * At root (1):
         * - leftInfo: height=2, diameter=2
         * - rightInfo: height=2, diameter=2
         * - current_diameter = max(2, 2) = 2
         * - height_diameter = 2 + 2 + 1 = 5
         * - Return Info(height=3, diameter=5)
         * 
         * Final diameter = 5
         */
        
        // Build sample tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        
        System.out.println("Tree Diameter Problem");
        System.out.println("Tree structure:");
        System.out.println("        1");
        System.out.println("       / \\");
        System.out.println("      2   3");
        System.out.println("     / \\ / \\");
        System.out.println("    4   5 6   7");
        
        System.out.println("\nApproach 1 - O(n²) Method:");
        int result1 = diameter1(root);
        System.out.println("Diameter: " + result1);
        
        System.out.println("\nApproach 2 - O(n) Method:");
        Info result2 = diameter2(root);
        System.out.println("Diameter: " + result2.diameter);
        System.out.println("Tree height: " + result2.height);
        
        // Show step-by-step for Approach 2
        System.out.println("\nStep-by-step for Approach 2:");
        System.out.println("At root (1):");
        System.out.println("- Left subtree: height=" + (result2.height - 3) + ", diameter=" + "2");
        System.out.println("- Right subtree: height=" + (result2.height - 3) + ", diameter=" + "2");
        System.out.println("- Current node diameter: max(2, 2) = 2");
        System.out.println("- Height through current node: 2 + 2 + 1 = 5");
        System.out.println("- Final diameter: max(2, 5) = 5");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Skewed tree
        Node skewed = new Node(1);
        skewed.left = new Node(2);
        skewed.left.left = new Node(3);
        skewed.left.left.left = new Node(4);
        
        System.out.println("Skewed tree (1→2→3→4):");
        System.out.println("Diameter: " + diameter1(skewed) + " (should be 3 edges)");
        
        // Test case 3: Single node
        Node single = new Node(42);
        System.out.println("Single node:");
        System.out.println("Diameter: " + diameter1(single) + " (should be 0)");
        
        // Test case 4: Empty tree
        System.out.println("Empty tree:");
        System.out.println("Diameter: " + diameter1(null) + " (should be 0)");
    }
}
