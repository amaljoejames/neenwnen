import java.util.*;

/**
 * QUESTION: Stack Basics - Implement fundamental stack operations including
 * array-based stack, linked list-based stack, and basic stack applications.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Array-based Stack:
 * Stack implementation using ArrayList with LIFO (Last In First Out) operations.
 * Supports push, pop, peek, and isEmpty operations in O(1) time.
 * 
 * 2. Linked List-based Stack:
 * Stack implementation using linked list nodes with head pointer.
 * More memory efficient than array-based for dynamic sizing.
 * 
 * 3. Push at Bottom:
 * Insert element at bottom of stack using recursion.
 * Remove all elements, insert new element, then restore original order.
 * 
 * 4. String Reversal:
 * Reverse string using stack by pushing characters and popping them.
 * Demonstrates stack's reversal property.
 * 
 * 5. Stack Reversal:
 * Reverse entire stack using recursion.
 * Remove all elements, then push them back in reverse order.
 * 
 * APPROACH USED: LIFO Data Structure with Multiple Implementations
 * 
 * PSEUDOCODE:
 * 
 * Array-based Stack:
 * class StackArr:
 *   list = ArrayList<Integer>()
 *   
 *   push(data):
 *     list.add(data)
 *   
 *   pop():
 *     if isEmpty(): return -1
 *     top = list.get(size-1)
 *     list.remove(size-1)
 *     return top
 *   
 *   peek():
 *     if isEmpty(): return -1
 *     return list.get(size-1)
 * 
 * Linked List Stack:
 * class StackLL:
 *   head = null
 *   
 *   push(data):
 *     newNode = Node(data)
 *     if isEmpty(): head = newNode
 *     else: newNode.next = head, head = newNode
 *   
 *   pop():
 *     if isEmpty(): return -1
 *     top = head.data
 *     head = head.next
 *     return top
 * 
 * Push at Bottom:
 * function pushAtBottom(stack, data):
 *   if stack.isEmpty():
 *     stack.push(data)
 *     return
 *   top = stack.pop()
 *   pushAtBottom(stack, data)
 *   stack.push(top)
 * 
 * String Reversal:
 * function reverseString(str):
 *   stack = Stack<Character>()
 *   for each char in str:
 *     stack.push(char)
 *   
 *   result = ""
 *   while !stack.isEmpty():
 *     result += stack.pop()
 *   
 *   return result
 * 
 * Stack Reversal:
 * function reverseStack(stack):
 *   if stack.isEmpty(): return
 *   top = stack.pop()
 *   reverseStack(stack)
 *   stack.push(top)
 * 
 * EXPLANATION OF STEPS:
 * 1. Array Stack: Use ArrayList for dynamic sizing with O(1) operations
 * 2. Linked List Stack: Use head pointer for O(1) push/pop operations
 * 3. Push at Bottom: Recursively remove all elements, insert new, restore
 * 4. String Reversal: Push characters, then pop to reverse order
 * 5. Stack Reversal: Recursively reverse stack order
 * 
 * DRY RUN for Array-based Stack:
 * 
 * Operations: push(1), push(2), pop(), push(3), peek()
 * 
 * Process:
 * push(1): [1]
 * push(2): [1,2]
 * pop(): return 2, [1]
 * push(3): [1,3]
 * peek(): return 3
 * 
 * Final stack: [1,3]
 * 
 * DRY RUN for Push at Bottom:
 * 
 * Stack: [1,2,3], data=4
 * 
 * Process:
 * pop(3), pushAtBottom([1,2],4)
 *   pop(2), pushAtBottom([1],4)
 *     pop(1), pushAtBottom([],4)
 *       push(4), return
 *     push(1), return
 *   push(2), return
 * push(3), return
 * 
 * Final stack: [1,2,3,4]
 * 
 * DRY RUN for String Reversal:
 * 
 * String: "abc"
 * 
 * Process:
 * push('a'), push('b'), push('c')
 * stack: ['a','b','c']
 * 
 * pop(): 'c', pop(): 'b', pop(): 'a'
 * 
 * Result: "cba"
 */
public class StackBasics {
    
    // Array-based Stack Implementation
    static class StackArr {
        static ArrayList<Integer> list = new ArrayList<>();
        
        public static boolean isEmpty() {
            return list.size() == 0;
        }
        
        public static void push(int data) {
            list.add(data);
        }
        
        public static int pop() {
            if (isEmpty()) {
                return -1;
            }
            int top = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return top;
        }
        
        public static int peek() {
            if (isEmpty()) {
                return -1;
            }
            return list.get(list.size() - 1);
        }
    }

    // Linked List-based Stack Implementation
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    static class StackLL {
        static Node head = null;
        
        public static boolean isEmpty() {
            return head == null;
        }
        
        public static void push(int data) {
            Node newNode = new Node(data);
            if (isEmpty()) {
                head = newNode;
                return;
            }
            newNode.next = head;
            head = newNode;
        }
        
        public static int pop() {
            if (isEmpty()) {
                return -1;
            }
            int top = head.data;
            head = head.next;
            return top;
        }
        
        public static int peek() {
            if (isEmpty()) {
                return -1;
            }
            return head.data;
        }
    }

    // Push at bottom of stack (Amazon)
    public static void pushAtBottom(Stack<Integer> s, int data) {
        if (s.isEmpty()) {
            s.push(data);
            return;
        }
        int top = s.pop();
        pushAtBottom(s, data);
        s.push(top);
    }

    // Reverse string using stack (Amazon, Adobe, Flipkart, Microsoft, Paytm)
    public static String reverseString(String str) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        return sb.toString();
    }

    // Reverse stack (Amdocs)
    public static void reverseStack(Stack<Integer> s) {
        if (s.isEmpty()) {
            return;
        }
        int top = s.pop();
        reverseStack(s);
        s.push(top);
    }
    
    public static void printStack(Stack<Integer> s) {
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all basic stack operations
         * 
         * Array Stack: push(1,2,3,4), pop(), peek()
         * Expected: [1,2,3,4] -> [1,2,3], peek=3
         * 
         * Linked List Stack: push(1,2,3), pop()
         * Expected: [1,2,3] -> [1,2], return 3
         * 
         * Push at Bottom: [1,2,3], data=4
         * Expected: [1,2,3,4]
         * 
         * String Reversal: "abc"
         * Expected: "cba"
         * 
         * Stack Reversal: [1,2,3,4]
         * Expected: [4,3,2,1]
         */
        
        // Test Array-based Stack
        System.out.println("Stack Basics - Array-based Stack");
        StackArr stackArr = new StackArr();
        stackArr.push(1);
        stackArr.push(2);
        stackArr.push(3);
        stackArr.push(4);
        
        System.out.println("After pushing 1,2,3,4:");
        System.out.println("Peek: " + stackArr.peek());
        System.out.println("Pop: " + stackArr.pop());
        System.out.println("Peek after pop: " + stackArr.peek());
        
        // Test Linked List-based Stack
        System.out.println("\nLinked List-based Stack:");
        StackLL.push(1);
        StackLL.push(2);
        StackLL.push(3);
        
        System.out.println("Push 1,2,3:");
        System.out.println("Pop: " + StackLL.pop());
        System.out.println("Peek: " + StackLL.peek());
        
        // Test Push at Bottom
        System.out.println("\nPush at Bottom:");
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        System.out.println("Original stack: [1,2,3]");
        System.out.println("Push 4 at bottom:");
        pushAtBottom(stack, 4);
        
        System.out.print("Result: ");
        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        while (!temp.isEmpty()) {
            System.out.print(temp.pop() + " ");
        }
        System.out.println();
        
        // Test String Reversal
        System.out.println("\nString Reversal:");
        String str = "abc";
        System.out.println("Original: " + str);
        System.out.println("Reversed: " + reverseString(str));
        
        // Test Stack Reversal
        System.out.println("\nStack Reversal:");
        Stack<Integer> revStack = new Stack<>();
        revStack.push(1);
        revStack.push(2);
        revStack.push(3);
        revStack.push(4);
        
        System.out.println("Original: [1,2,3,4]");
        reverseStack(revStack);
        System.out.print("Reversed: ");
        printStack(revStack);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Empty stack operations
        System.out.println("Empty stack operations:");
        System.out.println("Array stack pop: " + stackArr.pop());
        System.out.println("Array stack peek: " + stackArr.peek());
        
        // Test case 3: Long string reversal
        String longStr = "Hello World";
        System.out.println("\nLong string reversal:");
        System.out.println("Original: " + longStr);
        System.out.println("Reversed: " + reverseString(longStr));
        
        // Test case 4: Single element stack reversal
        Stack<Integer> singleStack = new Stack<>();
        singleStack.push(42);
        System.out.println("\nSingle element stack:");
        System.out.println("Original: [42]");
        reverseStack(singleStack);
        System.out.print("Reversed: ");
        printStack(singleStack);
    }
}
