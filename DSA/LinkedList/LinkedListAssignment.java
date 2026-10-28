/**
 * QUESTION: LinkedList Assignment Problems - Implement interview problems including
 * intersection of two lists, delete N nodes after M, swap nodes, and odd-even segregation.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Intersection of Two Linked Lists:
 * Find the point where two linked lists merge.
 * Handle Y-shaped lists where one list's end points to another.
 * 
 * 2. Delete N Nodes After M Nodes:
 * Retain M nodes, then delete next N nodes from the list.
 * Continue until end of list.
 * 
 * 3. Swapping Nodes:
 * Swap two nodes in linked list by changing their next pointers.
 * More efficient than swapping data values.
 * 
 * 4. Odd Even Linked List:
 * Rearrange list so all even numbers appear before odd numbers.
 * Maintain relative order within even and odd groups.
 * 
 * APPROACH USED: Two-pointer Technique and Node Manipulation
 * 
 * PSEUDOCODE:
 * 
 * Intersection:
 * function getIntersectionNode(head1, head2):
 *   while head2 != null:
 *     temp = head1
 *     while temp != null:
 *       if temp == head2: return head2
 *       temp = temp.next
 *     head2 = head2.next
 *   return null
 * 
 * Delete N After M:
 * function skipMdeleteN(head, M, N):
 *   curr = head, count = 0
 *   
 *   // Skip M nodes
 *   for count = 1; count < M && curr != null; count++:
 *     curr = curr.next
 *   
 *   // Delete N nodes
 *   t = curr.next
 *   for count = 1; count <= N && t != null; count++:
 *     temp = t
 *     t = t.next
 *   
 *   curr.next = t
 *   curr = t
 * 
 * Swap Nodes:
 * function swapNodes(head, x, y):
 *   if x == y: return
 *   
 *   // Find x and its previous
 *   prevX = null, currX = head
 *   while currX != null && currX.data != x:
 *     prevX = currX
 *     currX = currX.next
 *   
 *   // Find y and Its previous
 *   prevY = null, currY = head
 *   while currY != null && currY.data != y:
 *     prevY = currY
 *     currY = currY.next
 *   
 *   // Update pointers to swap
 *   if prevX != null: prevX.next = currY
 *   else: head = currY
 *   if prevY != null: prevY.next = currX
 *   else: head = currX
 *   
 *   // Swap next pointers
 *   temp = currX.next
 *   currX.next = currY.next
 *   currY.next = temp
 * 
 * Odd Even Segregation:
 * function segregateEvenOdd():
 *   end = head, prev = null, curr = head
 *   
 *   // Find last node
 *   while end.next != null: end = end.next
 *   
 *   new_end = end
 *   
 *   // Move all odd nodes before first even node
 *   while curr.data % 2 != 0 && curr != end:
 *     new_end.next = curr
 *     curr = curr.next
 *     new_end.next.next = null
 *     new_end = new_end.next
 *   
 *   // Handle first even node
 *   if curr.data % 2 == 0: head = curr
 *   
 *   // Traverse and rearrange
 *   while curr != end:
 *     if curr.data % 2 == 0:
 *       prev = curr
 *       curr = curr.next
 *     else:
 *       prev.next = curr.next
 *       curr = curr.next
 *   
 *   // Handle last odd node case
 *   if new_end != end && end.data % 2 != 0:
 *     prev.next = end
 *     end.next = null
 *     new_end.next = end
 *   
 * EXPLANATION OF STEPS:
 * 1. Intersection: Traverse one list, check for each node in other
 * 2. Delete N After M: Skip M nodes, delete N nodes, repeat
 * 3. Swap Nodes: Find both nodes, update their next pointers
 * 4. Odd Even: Find last node, move odds before first even
 * 5. Time complexity: O(n) for all operations
 * 
 * DRY RUN for Intersection:
 * 
 * List1: 1->2->3->4, List2: 5->6->7->8
 * 
 * Process:
 * Traverse List2: 5,6,7,8
 * Check each in List1: 5(no), 6(no), 7(found at 2), return 7
 * 
 * Intersection at node with data 7
 * 
 * DRY RUN for Delete N After M:
 * 
 * List: 1->2->3->4->5->6->7->8, M=2, N=2
 * 
 * Process:
 * Skip first 2 nodes: curr at 3
 * Delete next 2 nodes: delete 3,4
 * Remaining: 1->2->5->6->7->8
 * 
 * Final result: 1->2->5->6->7->8
 */
public class LinkedListAssignment {
    
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;

    // Find intersection point of two linked lists
    public static Node getIntersectionNode(Node head1, Node head2) {
        while (head2 != null) {
            Node temp = head1;
            while (temp != null) {
                if (temp == head2) { // Intersection found
                    return head2;
                }
                temp = temp.next;
            }
            head2 = head2.next;
        }
        return null; // No intersection
    }

    // Delete N nodes after M nodes
    public static void skipMdeleteN(Node head, int M, int N) {
        Node curr = head;
        Node t;
        int count;

        // Skip M nodes
        for (count = 1; count < M && curr != null; count++) {
            curr = curr.next;
        }

        // If we've reached the end of the list, return
        if (curr == null) {
            return;
        }

        // Start deleting N nodes
        t = curr.next;
        for (count = 1; count <= N && t != null; count++) {
            t = t.next;
        }

        // Connect the remaining nodes after deletion
        curr.next = t;

        // Move to the next group
        curr = t;
    }

    // Swap nodes in linked list
    public static void swapNodes(Node head, int x, int y) {
        // If x and y are the same, no swap is needed
        if (x == y) {
            return;
        }

        // Find x and its previous node
        Node prevX = null, currX = head;
        while (currX != null && currX.data != x) {
            prevX = currX;
            currX = currX.next;
        }

        // Find y and Its previous node
        Node prevY = null, currY = head;
        while (currY != null && currY.data != y) {
            prevY = currY;
            currY = currY.next;
        }

        // If either x or y is not found, no Swap is possible
        if (currX == null || currY == null) {
            return;
        }

        // Update the previous node of x to point to y
        if (prevX != null) {
            prevX.next = currY;
        } else { // If x is the head
            head = currY;
        }

        // Update the previous node of y to point to x
        if (prevY != null) {
            prevY.next = currX;
        } else { // If y is the head
            head = currX;
        }

        // Swap next pointers of x and y
        Node temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;
    }

    // Segregate even and odd numbers
    public static void segregateEvenOdd() {
        Node end = head;
        Node prev = null;
        Node curr = head;

        // Find the last node
        while (end.next != null) {
            end = end.next;
        }

        Node new_end = end;

        // Move all odd nodes before the first even node
        while (curr.data % 2 != 0 && curr != end) {
            new_end.next = curr;
            curr = curr.next;
            new_end.next.next = null;
            new_end = new_end.next;
        }

        // If the first node is even, update head
        if (curr.data % 2 == 0) {
            head = curr;
        }

        // Traverse and rearrange nodes
        while (curr != end) {
            if (curr.data % 2 == 0) {
                prev = curr;
                curr = curr.next;
            } else {
                prev.next = curr.next;
                curr = curr.next;
            }
        }

        // If the new_end is not the original end and the last node is odd
        if (new_end != end && end.data % 2 != 0) {
            prev.next = end;
            end.next = null;
            new_end.next = end;
        }
    }

    // Print linked list
    public static void print(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all assignment problems
         * 
         * Intersection: List1: 1->2->3, List2: 2->3->4
         * Expected: Intersection at node 2
         * 
         * Delete N After M: 1->2->3->4->5, M=2, N=2
         * Expected: 1->2->5 (delete 3,4)
         * 
         * Swap Nodes: 1->2->3, x=2, y=4
         * Expected: 1->4->3->2
         * 
         * Odd Even: 8->12->10->5->4->1->6
         * Expected: 12->10->4->2->8->6->4->1
         */
        
        // Test Intersection
        Node list1 = new Node(1);
        list1.next = new Node(2);
        list1.next.next = new Node(3);
        
        Node list2 = new Node(2);
        list2.next = new Node(3);
        list2.next.next = new Node(4);
        
        System.out.println("LinkedList Assignment - Intersection");
        System.out.println("List1: 1->2->3, List2: 2->3->4");
        System.out.println("Expected: Intersection at node 2");
        
        Node intersection = getIntersectionNode(list1, list2);
        System.out.println("Result: " + (intersection != null ? "Intersection at node " + intersection.data : "No intersection"));
        
        // Test Delete N After M
        Node deleteList = new Node(1);
        deleteList.next = new Node(2);
        deleteList.next.next = new Node(3);
        deleteList.next.next.next = new Node(4);
        deleteList.next.next.next.next = new Node(5);
        deleteList.next.next.next.next.next = new Node(6);
        deleteList.next.next.next.next.next.next = new Node(7);
        deleteList.next.next.next.next.next.next.next = new Node(8);
        
        System.out.println("\nDelete N After M:");
        System.out.println("List: 1->2->3->4->5->6->7->8, M=2, N=2");
        System.out.println("Expected: 1->2->5->6->7->8");
        
        skipMdeleteN(deleteList, 2, 2);
        System.out.print("Result: ");
        print(deleteList);
        
        // Test Swap Nodes
        Node swapList = new Node(1);
        swapList.next = new Node(2);
        swapList.next.next = new Node(3);
        swapList.next.next.next = new Node(4);
        
        System.out.println("\nSwap Nodes:");
        System.out.println("List: 1->2->3->4, x=2, y=4");
        System.out.println("Expected: 1->4->3->2");
        
        swapNodes(swapList, 2, 4);
        System.out.print("Result: ");
        print(swapList);
        
        // Test Odd Even Segregation
        Node oddEvenList = new Node(8);
        oddEvenList.next = new Node(12);
        oddEvenList.next.next = new Node(10);
        oddEvenList.next.next.next = new Node(5);
        oddEvenList.next.next.next.next = new Node(4);
        oddEvenList.next.next.next.next.next = new Node(1);
        oddEvenList.next.next.next.next.next.next = new Node(6);
        
        System.out.println("\nOdd Even Segregation:");
        System.out.println("List: 8->12->10->5->4->1->6");
        System.out.println("Expected: 12->10->4->2->8->6->4->1");
        
        segregateEvenOdd();
        System.out.print("Result: ");
        print(oddEvenList);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single node intersection
        Node single1 = new Node(5);
        Node single2 = new Node(10);
        System.out.println("Single node intersection:");
        System.out.println("List1: 5, List2: 10");
        Node singleIntersection = getIntersectionNode(single1, single2);
        System.out.println("Result: " + (singleIntersection != null ? "Intersection" : "No intersection"));
        
        // Test case 3: Delete more nodes than exist
        Node smallList = new Node(1);
        smallList.next = new Node(2);
        System.out.println("Delete more nodes than exist:");
        System.out.println("List: 1->2, M=1, N=3");
        System.out.println("Expected: 1->2 (delete 2, but only 2 exists)");
        
        skipMdeleteN(smallList, 1, 3);
        System.out.print("Result: ");
        print(smallList);
    }
}
