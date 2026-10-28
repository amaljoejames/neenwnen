import java.util.*;

/**
 * QUESTION: Queue Basics - Implement fundamental queue operations including
 * array-based queue, linked list-based queue, and queue using two stacks.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Array-based Queue:
 * Circular queue implementation using array with front and rear pointers.
 * Handles wrap-around using modulo arithmetic for efficient space usage.
 * 
 * 2. Linked List-based Queue:
 * Dynamic queue implementation using linked list nodes.
 * No size limitation, efficient enqueue/dequeue operations.
 * 
 * 3. Queue using Two Stacks:
 * Implement queue operations using two stacks.
 * Enqueue by moving elements to second stack, adding new element, then moving back.
 * 
 * APPROACH USED: FIFO (First In First Out) Data Structure
 * 
 * PSEUDOCODE:
 * 
 * Array-based Queue:
 * class QueueA:
 *   arr[], size, front, rear
 *   
 *   enqueue(value):
 *     if isFull(): return
 *     if isEmpty(): front = 0
 *     rear = (rear + 1) % size
 *     arr[rear] = value
 *   
 *   dequeue():
 *     if isEmpty(): return -1
 *     result = arr[front]
 *     if front == rear: front = rear = -1
 *     else: front = (front + 1) % size
 *     return result
 * 
 * Linked List Queue:
 * class QueueB:
 *   head, tail
 *   
 *   enqueue(data):
 *     newNode = Node(data)
 *     if isEmpty(): head = tail = newNode
 *     else: tail.next = newNode, tail = newNode
 *   
 *   dequeue():
 *     if isEmpty(): return -1
 *     result = head.data
 *     head = head.next
 *     if head == null: tail = null
 *     return result
 * 
 * Queue using Two Stacks:
 * class Queue2Stacks:
 *   s1, s2
 *   
 *   enqueue(data):
 *     while !s1.isEmpty(): s2.push(s1.pop())
 *     s1.push(data)
 *     while !s2.isEmpty(): s1.push(s2.pop())
 *   
 *   dequeue():
 *     if isEmpty(): return -1
 *     return s1.pop()
 * 
 * EXPLANATION OF STEPS:
 * 1. Array Queue: Use circular buffer for efficient space utilization
 * 2. Linked List Queue: Dynamic allocation, no size constraints
 * 3. Two Stacks Queue: Trade-off between enqueue/dequeue efficiency
 * 4. Time complexity: O(1) for most operations
 * 
 * DRY RUN for Array-based Queue:
 * 
 * Queue size = 3, operations: enqueue(1), enqueue(2), dequeue(), enqueue(3)
 * 
 * Process:
 * enqueue(1): front=0, rear=0, arr[0]=1
 * enqueue(2): rear=1, arr[1]=2
 * dequeue(): result=1, front=1
 * enqueue(3): rear=2, arr[2]=3
 * 
 * Final state: front=1, rear=2, arr=[1,2,3]
 * 
 * DRY RUN for Queue using Two Stacks:
 * 
 * Operations: enqueue(1), enqueue(2), dequeue(), enqueue(3)
 * 
 * Process:
 * enqueue(1): s1=[1], s2=[]
 * enqueue(2): s1=[1,2], s2=[]
 * dequeue(): result=1, s1=[2], s2=[]
 * enqueue(3): move 2 to s2, push 3 to s1, move back: s1=[2,3], s2=[]
 */
public class QueueBasics {
    
    // Array-based Queue Implementation
    static class QueueA {
        static int arr[];
        static int size;
        static int rear;
        static int front;

        QueueA(int n) {
            arr = new int[n];
            size = n;
            rear = -1;
            front = -1;
        }

        public static boolean isEmpty() {
            return rear == -1 && front == -1;
        }

        public static boolean isFull() {
            return (rear + 1) % size == front;
        }

        public static void enqueue(int value) {
            if (isFull()) {
                System.out.println("Queue is full. Cannot enqueue " + value);
                return;
            }
            if (isEmpty()) {
                front = 0;
            }
            rear = (rear + 1) % size;
            arr[rear] = value;
        }

        public static int dequeue() {
            if (isEmpty()) {
                System.out.println("Queue is empty. Cannot dequeue.");
                return -1;
            }
            int result = arr[front];
            if (front == rear) {
                front = rear = -1;
            } else {
                front = (front + 1) % size;
            }
            return result;
        }

        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty. No elements to peek.");
                return -1;
            }
            return arr[front];
        }

        public static void display() {
            if (isEmpty()) {
                System.out.println("Queue is empty.");
                return;
            }
            System.out.print("Queue elements: ");
            int i = front;
            while (true) {
                System.out.print(arr[i] + " ");
                if (i == rear) break;
                i = (i + 1) % size;
            }
            System.out.println();
        }
    }

    // Linked List-based Queue Implementation
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class QueueB {
        static Node head = null;
        static Node tail = null;

        public static boolean isEmpty() {
            return head == null && tail == null;
        }

        public static void add(int data) {
            Node newNode = new Node(data);
            if (isEmpty()) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        public static int remove() {
            if (isEmpty()) {
                System.out.println("Queue is empty. Cannot remove.");
                return -1;
            }
            int frontData = head.data;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return frontData;
        }

        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty. No elements to peek.");
                return -1;
            }
            return head.data;
        }

        public static void display() {
            if (isEmpty()) {
                System.out.println("Queue is empty.");
                return;
            }
            System.out.print("Queue elements: ");
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    // Queue using Two Stacks
    static class Queue2Stacks {
        static Stack<Integer> s1 = new Stack<>();
        static Stack<Integer> s2 = new Stack<>();

        public static boolean isEmpty() {
            return s1.isEmpty();
        }

        public static void add(int data) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s1.push(data);
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        public static int remove() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            return s1.pop();
        }

        public static int peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            return s1.peek();
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all queue implementations
         * 
         * Array Queue: size=3, enqueue(1,2), dequeue(), enqueue(3)
         * Expected: [1,2] -> [2] -> [2,3]
         * 
         * Linked List Queue: add(100,200,300), remove()
         * Expected: [100,200,300] -> [200,300]
         * 
         * Two Stacks Queue: add(1,2,3), remove()
         * Expected: [1,2,3] -> [2,3]
         */
        
        // Test Array-based Queue
        System.out.println("Queue Basics - Array-based Queue");
        System.out.println("Size: 3, operations: enqueue(1,2), dequeue(), enqueue(3)");
        System.out.println("Expected: [1,2] -> [2] -> [2,3]");
        
        QueueA queue = new QueueA(3);
        queue.enqueue(1);
        queue.enqueue(2);
        System.out.print("After enqueuing 1,2: ");
        queue.display();
        
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.print("After dequeue: ");
        queue.display();
        
        queue.enqueue(3);
        System.out.print("After enqueuing 3: ");
        queue.display();
        
        // Test Linked List-based Queue
        System.out.println("\nLinked List-based Queue:");
        System.out.println("Operations: add(100,200,300), remove()");
        System.out.println("Expected: [100,200,300] -> [200,300]");
        
        QueueB.add(100);
        QueueB.add(200);
        QueueB.add(300);
        System.out.print("After adding 100,200,300: ");
        QueueB.display();
        
        System.out.println("Removed: " + QueueB.remove());
        System.out.print("After removal: ");
        QueueB.display();
        
        // Test Queue using Two Stacks
        System.out.println("\nQueue using Two Stacks:");
        System.out.println("Operations: add(1,2,3), remove()");
        System.out.println("Expected: [1,2,3] -> [2,3]");
        
        Queue2Stacks stackQueue = new Queue2Stacks();
        stackQueue.add(1);
        stackQueue.add(2);
        stackQueue.add(3);
        
        System.out.print("Queue elements: ");
        while (!stackQueue.isEmpty()) {
            System.out.print(stackQueue.peek() + " ");
            stackQueue.remove();
        }
        System.out.println();
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Circular behavior
        QueueA circularQueue = new QueueA(3);
        circularQueue.enqueue(10);
        circularQueue.enqueue(20);
        circularQueue.enqueue(30);
        circularQueue.dequeue();
        circularQueue.enqueue(40);
        System.out.print("Circular queue test: ");
        circularQueue.display();
        
        // Test case 3: Empty operations
        QueueB emptyQueue = new QueueB();
        System.out.println("Empty queue test: " + emptyQueue.remove());
    }
}
