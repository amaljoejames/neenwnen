/**
 * QUESTION: LinkedList Basics - Implement fundamental linked list operations including
 * add, remove, search, reverse, palindrome check, and cycle detection.
 * 
 * QUESTION EXPLANATION:
 * LinkedList: Linear data structure with nodes pointing to next node.
 * Dynamic size, efficient insertions/deletions at known positions.
 * 
 * Operations covered:
 * 1. Add First/Last: Insert at beginning or end
 * 2. Remove First/Last: Delete from beginning or end
 * 3. Search: Iterative and recursive search
 * 4. Reverse: Reverse the linked list
 * 5. Palindrome: Check if list reads same forwards/backwards
 * 6. Cycle Detection: Detect and remove cycles
 * 
 * APPROACH USED: Node-based Implementation with Pointers
 * 
 * PSEUDOCODE:
 * 
 * Node Class:
 * class Node:
 *   data: int
 *   next: Node
 *   
 *   constructor Node(data):
 *     this.data = data
 *     this.next = null
 * 
 * Add First:
 * function addFirst(data):
 *   newNode = Node(data)
 *   if head == null:
 *     head = tail = newNode
 *   else:
 *     newNode.next = head
 *     head = newNode
 *   size++
 * 
 * Remove First:
 * function removeFirst():
 *   if size == 0: return MIN_VALUE
 *   data = head.data
 *   head = head.next
 *   if head == null: tail = null
 *   size--
 *   return data
 * 
 * Search:
 * function itrSearch(key):
 *   temp = head, i = 0
 *   while temp != null:
 *     if temp.data == key: return i
 *     temp = temp.next, i++
 *   return -1
 * 
 * Reverse:
 * function reverse():
 *   prev = null, curr = head
 *   while curr != null:
 *     next = curr.next
 *     curr.next = prev
 *     prev = curr
 *     curr = next
 *   head = prev
 * 
 * Palindrome:
 * function isPalindrome():
 *   if head == null || head.next == null: return true
 *   mid = findMid(head)
 *   reverseSecondHalf(mid)
 *   return compareHalves(head, mid)
 * 
 * Cycle Detection:
 * function isCycle():
 *   slow = head, fast = head
 *   while fast != null && fast.next != null:
 *     slow = slow.next
 *     fast = fast.next.next
 *     if slow == fast: return true
 *   return false
 * 
 * EXPLANATION OF STEPS:
 * 1. Use head and tail pointers for O(1) operations
 * 2. Maintain size variable for constant-time size queries
 * 3. Use slow-fast pointer for cycle detection
 * 4. Use two-pointer technique for palindrome check
 * 5. Time complexity: O(1) for add/remove, O(n) for search/reverse
 * 
 * DRY RUN for Add Operations:
 * 
 * Add: 1, 2, 3, 4, 5
 * 
 * Step 1: Add 1
 * head = 1, tail = 1, size = 1
 * 
 * Step 2: Add 2
 * newNode.next = head, head = newNode
 * head = 2, tail = 1, size = 2
 * 
 * Step 3: Add 3
 * newNode.next = head, head = newNode
 * head = 3, tail = 1, size = 3
 * 
 * Final list: 3->2->1
 * 
 * DRY RUN for Search:
 * 
 * List: 1->2->3->4, search for 3
 * 
 * Process:
 * i=0, temp=1, data≠3
 * i=1, temp=2, data≠3
 * i=2, temp=3, data=3, return 2
 * 
 * Found at index 2
 * 
 * DRY RUN for Reverse:
 * 
 * List: 1->2->3->4
 * 
 * Process:
 * prev=null, curr=1
 * next=2, curr.next=null, prev=1, curr=2
 * next=3, curr.next=null, prev=2, curr=3
 * next=4, curr.next=null, prev=3, curr=4
 * 
 * Final list: 4->3->2->1
 */
public class LinkedListBasics {
    
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    // Add First
    public static void addFirst(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    // Add Last
    public static void addLast(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    // Print Linked List
    public static void print() {
        if (head == null) {
            System.out.println("LinkedList is empty");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Add in the Middle
    public static void addInTheMiddle(int idx, int data) {
        if (idx == 0) {
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i = 0;
        while (i < idx - 1) {
            temp = temp.next;
            i++;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Remove First
    public static int removeFirst() {
        if (size == 0) {
            System.out.println("LinkedList is empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size--;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    // Remove Last
    public static int removeLast() {
        if (size == 0) {
            System.out.println("LinkedList is empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size--;
            return val;
        }
        Node prev = head;
        for (int i = 0; i < size - 2; i++) {
            prev = prev.next;
        }
        int val = prev.next.data;
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }

    // Iterative Search
    public static int itrSearch(int key) {
        Node temp = head;
        int i = 0;
        while (temp != null) {
            if (temp.data == key) {
                return i;
            }
            temp = temp.next;
            i++;
        }
        return -1;
    }

    // Recursive Search Helper
    private static int helper(Node head, int key) {
        if (head == null) {
            return -1;
        }
        if (head.data == key) {
            return 0;
        }
        int idx = helper(head.next, key);
        if (idx == -1) {
            return -1;
        }
        return idx + 1;
    }

    // Recursive Search
    public static int recSearch(int key) {
        return helper(head, key);
    }

    // Reverse Linked List
    public static void reverse() {
        Node prev = null;
        Node curr = head;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    // Find Mid (Helper)
    private static Node findMid(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Check if Palindrome
    public static boolean isPalindrome() {
        if (head == null || head.next == null) {
            return true;
        }
        // Find mid
        Node midNode = findMid(head);

        // Reverse second half
        Node prev = null;
        Node curr = midNode;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        Node right = prev;
        Node left = head;

        // Check halves
        while (right != null) {
            if (left.data != right.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }

        return true;
    }

    // Check for Cycle
    public static boolean isCycle() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all linked list operations
         * 
         * Add Operations: Add 1,2,3,4,5
         * Expected: List 5->4->3->2->1
         * 
         * Search: List 1->2->3->4, search for 3
         * Expected: Found at index 2
         * 
         * Reverse: List 1->2->3->4
         * Expected: 4->3->2->1
         * 
         * Palindrome: List 1->2->3->2->1
         * Expected: true (palindrome)
         */
        
        // Test Add Operations
        System.out.println("LinkedList Basics - Add Operations");
        System.out.println("Adding: 1, 2, 3, 4, 5");
        System.out.println("Expected: 5->4->3->2->1");
        
        addFirst(1);
        addFirst(2);
        addFirst(3);
        addFirst(4);
        addFirst(5);
        
        System.out.print("Result: ");
        print();
        
        // Test Search
        System.out.println("\nIterative Search:");
        System.out.println("List: 1->2->3->4, Search for 3");
        System.out.println("Expected: Found at index 2");
        
        int searchResult = itrSearch(3);
        System.out.println("Result: " + (searchResult == -1 ? "Not found" : "Found at index " + searchResult));
        
        // Test Reverse
        System.out.println("\nReverse:");
        System.out.println("Original: 1->2->3->4");
        System.out.println("Expected: 4->3->2->1");
        
        reverse();
        System.out.print("Reversed: ");
        print();
        
        // Test Palindrome
        System.out.println("\nPalindrome Check:");
        System.out.println("List: 1->2->3->2->1");
        System.out.println("Expected: true (palindrome)");
        
        boolean isPal = isPalindrome();
        System.out.println("Result: " + isPal);
        
        // Test Cycle Detection
        System.out.println("\nCycle Detection:");
        System.out.println("List: 1->2->3->4");
        System.out.println("Expected: false (no cycle)");
        
        boolean hasCycle = isCycle();
        System.out.println("Result: " + hasCycle);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single element
        System.out.println("Single element:");
        addFirst(42);
        System.out.print("List: ");
        print();
        System.out.println("Search for 42: " + (itrSearch(42) == 0 ? "Found" : "Not found"));
        
        // Test case 3: Empty list
        System.out.println("Empty list:");
        System.out.println("Remove first: " + (removeFirst() == Integer.MIN_VALUE ? "Empty" : "Removed " + removeFirst()));
    }
}
