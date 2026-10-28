/**
 * QUESTION: LinkedList Advanced - Implement advanced linked list operations including
 * merge sort, zigzag pattern, and cycle removal.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Merge Sort for Linked List:
 * Sort linked list using divide and conquer approach.
 * Find middle, recursively sort halves, then merge.
 * 
 * 2. Zigzag Pattern:
 * Rearrange linked list in zigzag pattern.
 * Alternate nodes should be greater than their neighbors.
 * 
 * 3. Cycle Removal:
 * Detect and remove cycle from linked list.
 * Use slow-fast pointers to find cycle start.
 * 
 * APPROACH USED: Two-pointer Technique and Recursive Merging
 * 
 * PSEUDOCODE:
 * 
 * Merge Sort:
 * function mergeSort(head):
 *   if head == null || head.next == null: return head
 *   mid = findMid(head)
 *   right = mid.next
 *   mid.next = null
 *   
 *   left = mergeSort(head)
 *   right = mergeSort(right)
 *   return merge(left, right)
 * 
 * function merge(left, right):
 *   result = dummy = Node(-1)
 *   while left != null && right != null:
 *     if left.data <= right.data:
 *       result.next = left
 *       left = left.next
 *     else:
 *       result.next = right
 *       right = right.next
 *     result = result.next
 *   
 *   while left != null:
 *     result.next = left
 *     left = left.next
 *     result = result.next
 *   
 *   while right != null:
 *     result.next = right
 *     right = right.next
 *     result = result.next
 *   
 *   return dummy.next
 * 
 * Zigzag:
 * function zigzag():
 *   if head == null || head.next == null: return
 *   
 *   mid = findMid(head)
 *   reverseSecondHalf(mid)
 *   alternateMerge(head, mid)
 * 
 * Cycle Removal:
 * function removeCycle():
 *   slow = head, fast = head
 *   cycle = false
 *   
 *   while fast != null && fast.next != null:
 *     slow = slow.next
 *     fast = fast.next.next
 *     if slow == fast:
 *       cycle = true
 *       break
 *   
 *   if !cycle: return
 *   
 *   slow = head
 *   prev = null
 *   while slow != fast:
 *     prev = fast
 *     fast = fast.next
 *   
 *   prev.next = null
 *   return
 * 
 * EXPLANATION OF STEPS:
 * 1. Merge Sort: O(n log n) time, O(log n) space
 * 2. Zigzag: O(n) time, O(1) space
 * 3. Cycle Removal: O(n) time, O(1) space
 * 
 * DRY RUN for Merge Sort:
 * 
 * List: 4->2->3->1
 * 
 * Process:
 * mid = 3, left = 4->2, right = 3->1
 * left sorted = 2->4, right sorted = 1->3
 * merge = 2->3->4->1
 * 
 * Final sorted: 1->2->3->4
 * 
 * DRY RUN for Zigzag:
 * 
 * List: 1->2->3->4->5
 * 
 * Process:
 * mid = 3, reverse after mid = 5->4
 * alternate merge: 1->4->2->3->5
 * 
 * Final zigzag: 1->4->2->3->5
 */
public class LinkedListAdvanced {
    
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;

    // Find Mid (Helper)
    private static Node findMid(Node head) {
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Merge two sorted lists
    private static Node merge(Node head1, Node head2) {
        Node mergedLL = new Node(-1);
        Node temp = mergedLL;
        
        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }
        
        while (head1 != null) {
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }
        
        while (head2 != null) {
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;
        }
        
        return mergedLL.next;
    }

    // Merge Sort
    public static Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        Node mid = findMid(head);
        Node right = mid.next;
        mid.next = null;
        
        Node newLeft = mergeSort(head);
        Node newRight = mergeSort(right);
        
        return merge(newLeft, newRight);
    }

    // Zigzag Pattern
    public static void zigzag() {
        if (head == null || head.next == null) {
            return;
        }
        
        // Find mid
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow;
        
        // Reverse second half
        Node prev = null;
        Node curr = mid.next;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        Node right = prev;
        Node left = head;
        
        // Alternate merge - zigzag pattern
        while (left != null && right != null) {
            Node nextL = left.next;
            Node nextR = right.next;
            
            left.next = right;
            right.next = nextL;
            
            left = nextL;
            right = nextR;
        }
    }

    // Remove Cycle
    public static void removeCycle() {
        Node slow = head;
        Node fast = head;
        boolean cycle = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                cycle = true;
                break;
            }
        }

        if (!cycle) {
            return;
        }

        slow = head;
        Node prev = null;
        while (slow != fast) {
            prev = fast;
            fast = fast.next;
        }
        
        prev.next = null;
    }
    
    // Print Linked List
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
         * Test all advanced linked list operations
         * 
         * Merge Sort: 4->2->3->1
         * Expected: 1->2->3->4
         * 
         * Zigzag: 1->2->3->4->5
         * Expected: 1->4->2->3->5
         * 
         * Cycle Removal: 1->2->3->4->2 (cycle)
         * Expected: 1->2->3->4 (cycle removed)
         */
        
        // Test Merge Sort
        Node list1 = new Node(4);
        list1.next = new Node(2);
        list1.next.next = new Node(3);
        list1.next.next.next = new Node(1);
        
        System.out.println("LinkedList Advanced - Merge Sort");
        System.out.println("Input: 4->2->3->1");
        System.out.println("Expected: 1->2->3->4");
        
        Node sorted = mergeSort(list1);
        System.out.print("Sorted: ");
        print(sorted);
        
        // Test Zigzag
        Node zigzagList = new Node(1);
        zigzagList.next = new Node(2);
        zigzagList.next.next = new Node(3);
        zigzagList.next.next.next = new Node(4);
        zigzagList.next.next.next.next = new Node(5);
        
        System.out.println("\nZigzag Pattern:");
        System.out.println("Input: 1->2->3->4->5");
        System.out.println("Expected: 1->4->2->3->5");
        
        zigzag();
        System.out.print("Zigzag: ");
        print(zigzagList);
        
        // Test Cycle Removal
        Node cycleList = new Node(1);
        cycleList.next = new Node(2);
        cycleList.next.next = new Node(3);
        cycleList.next.next.next = new Node(4);
        cycleList.next.next.next.next = cycleList; // Create cycle
        
        System.out.println("\nCycle Removal:");
        System.out.println("Input: 1->2->3->4->2 (cycle to 4)");
        System.out.println("Expected: 1->2->3->4 (cycle removed)");
        
        removeCycle();
        System.out.print("After cycle removal: ");
        print(cycleList);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single element merge sort
        Node single = new Node(42);
        System.out.println("Single element merge sort:");
        System.out.println("Input: 42");
        System.out.println("Expected: 42");
        
        Node singleSorted = mergeSort(single);
        System.out.print("Sorted: ");
        print(singleSorted);
        
        // Test case 3: Two elements zigzag
        Node two = new Node(1);
        two.next = new Node(2);
        
        System.out.println("Two elements zigzag:");
        System.out.println("Input: 1->2");
        System.out.println("Expected: 2->1 or 1->2");
        
        zigzag();
        System.out.print("Zigzag: ");
        print(two);
    }
}
