/**
 * QUESTION: LinkedList Sorting - Implement merge sort for K sorted linked lists
 * and merge K sorted lists using divide and conquer.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Merge Two Sorted Lists:
 * Merge two already sorted linked lists into one sorted list.
 * Compare node values and link in sorted order.
 * 
 * 2. Merge K Sorted Lists:
 * Merge K sorted linked lists into one sorted list.
 * Use divide and conquer approach for efficiency.
 * 
 * APPROACH USED: Recursive Merging with Divide and Conquer
 * 
 * PSEUDOCODE:
 * 
 * Merge Two Sorted Lists:
 * function SortedMerge(a, b):
 *   result = dummy = Node(-1)
 *   while a != null && b != null:
 *     if a.data <= b.data:
 *       result.next = a
 *       a = a.next
 *     else:
 *       result.next = b
 *       b = b.next
 *     result = result.next
 *   
 *   while a != null:
 *     result.next = a
 *     a = a.next
 *     result = result.next
 *   
 *   while b != null:
 *     result.next = b
 *     b = b.next
 *     result = result.next
 *   
 *   return dummy.next
 * 
 * Merge K Sorted Lists:
 * function mergeKLists(arr, last):
 *   while last != 0:
 *     i = 0, j = last
 *     
 *     // Merge lists in pairs
 *     while i < j:
 *       arr[i] = SortedMerge(arr[i], arr[j])
 *       i++
 *       j--
 *     
 *     // Update last index
 *     if i >= j:
 *       last = j
 *   
 *   return arr[0]
 * 
 * EXPLANATION OF STEPS:
 * 1. Merge Two: O(n+m) time, O(1) space
 * 2. Merge K: O(N log K) time, O(1) space
 * 3. Use dummy node to simplify edge cases
 * 4. Recursive approach for clean code
 * 
 * DRY RUN for Merge Two Sorted Lists:
 * 
 * List1: 1->3->5, List2: 2->4->6
 * 
 * Process:
 * 1 <= 2: result=1, list1=3->5, List2=2->4->6
 * 3 <= 4: result=1->3, List1=5, List2=2->4->6
 * 5 <= 6: result=1->3->5, List1=5, List2=2->4->6
 * 
 * Continue with remaining nodes
 * 
 * Final: 1->3->5->6
 * 
 * DRY RUN for Merge K Sorted Lists:
 * 
 * K=3, Lists: [1->3], [6->8], [9->10]
 * 
 * Process:
 * Merge [1->3] and [6->8]: [1->3->6->8]
 * Merge result with [9->10]: [1->3->6->8->9->10]
 * 
 * Final merged: 1->3->6->8->9->10
 */
public class LinkedListSorting {
    
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Merges two sorted linked lists
    public static Node SortedMerge(Node a, Node b) {
        Node result = new Node(-1); // Dummy node
        Node temp = result;

        // Base cases
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        }

        // Pick either 'a' or 'b', and recursively merge the rest
        if (a.data <= b.data) {
            result.next = a;
            a = a.next;
        } else {
            result.next = b;
            b = b.next;
        }
        temp = result.next;

        while (a != null && b != null) {
            if (a.data <= b.data) {
                temp.next = a;
                a = a.next;
            } else {
                temp.next = b;
                b = b.next;
            }
            temp = temp.next;
        }

        while (a != null) {
            temp.next = a;
            a = a.next;
            temp = temp.next;
        }

        while (b != null) {
            temp.next = b;
            b = b.next;
            temp = temp.next;
        }

        return result.next;
    }

    // Merges 'k' sorted linked lists using divide and conquer
    public static Node mergeKLists(Node arr[], int last) {
        // Repeat until only one list remains
        while (last != 0) {
            int i = 0, j = last;

            // Merge lists in pairs
            while (i < j) {
                arr[i] = SortedMerge(arr[i], arr[j]);
                i++;
                j--;
            }

            // Update the last index
            if (i >= j) {
                last = j;
            }
        }

        return arr[0]; // The merged list is in arr[0]
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
         * Test all sorting algorithms
         * 
         * Merge Two Sorted: [1->3->5], [2->4->6]
         * Expected: [1->2->3->4->5->6]
         * 
         * Merge K Sorted: K=3, [[1->3],[6->8],[9->10]]
         * Expected: [1->3->6->8->9->10]
         */
        
        // Test Merge Two Sorted Lists
        Node list1 = new Node(1);
        list1.next = new Node(3);
        list1.next.next = new Node(5);
        
        Node list2 = new Node(2);
        list2.next = new Node(4);
        list2.next.next = new Node(6);
        
        System.out.println("LinkedList Sorting - Merge Two Sorted Lists");
        System.out.println("List1: 1->3->5, List2: 2->4->6");
        System.out.println("Expected: 1->2->3->4->5->6");
        
        Node merged = SortedMerge(list1, list2);
        System.out.print("Merged: ");
        print(merged);
        
        // Test Merge K Sorted Lists
        System.out.println("\nMerge K Sorted Lists:");
        System.out.println("K=3, Lists: [1->3], [6->8], [9->10]");
        System.out.println("Expected: 1->3->6->8->9->10");
        
        Node[] lists = new Node[3];
        lists[0] = new Node(1);
        lists[0].next = new Node(3);
        
        lists[1] = new Node(6);
        lists[1].next = new Node(8);
        
        lists[2] = new Node(9);
        lists[2].next = new Node(10);
        
        Node kMerged = mergeKLists(lists, 3);
        System.out.print("K Merged: ");
        print(kMerged);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single list merge
        Node single = new Node(42);
        System.out.println("Single list merge:");
        System.out.println("List1: 42, List2: null");
        Node singleMerged = SortedMerge(single, null);
        System.out.print("Merged: ");
        print(singleMerged);
        
        // Test case 3: K=2 merge
        Node[] twoLists = new Node[2];
        twoLists[0] = new Node(1);
        twoLists[0].next = new Node(5);
        
        twoLists[1] = new Node(2);
        twoLists[1].next = new Node(8);
        
        System.out.println("K=2 merge:");
        System.out.println("Lists: [1->5], [2->8]");
        Node twoMerged = mergeKLists(twoLists, 2);
        System.out.print("Merged: ");
        print(twoMerged);
    }
}
