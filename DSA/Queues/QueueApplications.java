import java.util.*;

/**
 * QUESTION: Queue Applications - Implement practical queue algorithms including
 * first non-repeating character, queue interleaving, and queue reversal.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. First Non-Repeating Character:
 * Find first non-repeating character in stream of characters.
 * Use frequency array and queue to track characters.
 * 
 * 2. Queue Interleaving:
 * Interleave first half of queue with second half.
 * Split queue into two halves and merge alternately.
 * 
 * 3. Queue Reversal:
 * Reverse all elements in queue using stack.
 * Push all elements to stack, then pop back to queue.
 * 
 * 4. Stack using Two Queues:
 * Implement stack operations using two queues.
 * Use queue operations to simulate LIFO behavior.
 * 
 * APPROACH USED: Queue and Stack Data Structures
 * 
 * PSEUDOCODE:
 * 
 * First Non-Repeating Character:
 * function firstNonRepeatingChar(str):
 *   freq = int[26]
 *   q = Queue<Character>()
 *   
 *   for each character ch in str:
 *     q.add(ch)
 *     freq[ch-'a']++
 *     
 *     while !q.isEmpty() && freq[q.peek()-'a'] > 1:
 *       q.remove()
 *     
 *     if q.isEmpty(): print -1
 *     else: print q.peek()
 * 
 * Queue Interleaving:
 * function interleaveQueue(q):
 *   firstHalf = Queue()
 *   size = q.size()
 *   
 *   for i = 0 to size/2-1:
 *     firstHalf.add(q.remove())
 *   
 *   while !firstHalf.isEmpty():
 *     q.add(firstHalf.remove())
 *     q.add(q.remove())
 * 
 * Queue Reversal:
 * function reverseQueue(q):
 *   s = Stack()
 *   
 *   while !q.isEmpty():
 *     s.push(q.remove())
 *   
 *   while !s.isEmpty():
 *     q.add(s.pop())
 * 
 * Stack using Two Queues:
 * class Stack2Queues:
 *   q1, q2
 *   
 *   push(data):
 *     if !q1.isEmpty(): q1.add(data)
 *     else: q2.add(data)
 *   
 *   pop():
 *     if !q1.isEmpty():
 *       while !q1.isEmpty():
 *         top = q1.remove()
 *         if !q1.isEmpty(): q2.add(top)
 *     else:
 *       while !q2.isEmpty():
 *         top = q2.remove()
 *         if !q2.isEmpty(): q1.add(top)
 *     return top
 * 
 * EXPLANATION OF STEPS:
 * 1. First Non-Repeating: Track frequency and maintain queue of candidates
 * 2. Interleaving: Split and merge alternately for pattern creation
 * 3. Reversal: Use stack LIFO property to reverse queue order
 * 4. Stack Simulation: Move all elements except last to other queue
 * 
 * DRY RUN for First Non-Repeating Character:
 * 
 * String: "aabccxb"
 * 
 * Process:
 * 'a': freq[a]=1, q=[a], output=a
 * 'a': freq[a]=2, q=[], output=-1
 * 'b': freq[b]=1, q=[b], output=b
 * 'c': freq[c]=1, q=[b,c], output=b
 * 'c': freq[c]=2, q=[b], output=b
 * 'x': freq[x]=1, q=[b,x], output=b
 * 'b': freq[b]=2, q=[x], output=x
 * 
 * Output: a,-1,b,b,b,x
 * 
 * DRY RUN for Queue Interleaving:
 * 
 * Queue: [1,2,3,4,5,6]
 * 
 * Process:
 * firstHalf = [1,2,3], q = [4,5,6]
 * 
 * Interleave:
 * q.add(1), q.add(4) -> [4,5,6,1,4]
 * q.add(2), q.add(5) -> [5,6,1,4,2,5]
 * q.add(3), q.add(6) -> [6,1,4,2,5,3,6]
 * 
 * Final: [1,4,2,5,3,6]
 */
public class QueueApplications {
    
    // First non-repeating character in stream
    public static void firstNonRepeatingChar(String str) {
        int freq[] = new int[26];
        Queue<Character> q = new ArrayDeque<>();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            q.add(ch);
            freq[ch - 'a']++;
            
            while (!q.isEmpty() && freq[q.peek() - 'a'] > 1) {
                q.remove();
            }
            
            if (q.isEmpty()) {
                System.out.print("-1 ");
            } else {
                System.out.print(q.peek() + " ");
            }
        }
        System.out.println();
    }

    // Interleave 2 halves of a queue
    public static void interleaveQueue(Queue<Integer> q) {
        Queue<Integer> firstHalf = new ArrayDeque<>();
        int size = q.size();
        
        for (int i = 0; i < size / 2; i++) {
            firstHalf.add(q.remove());
        }
        
        while (!firstHalf.isEmpty()) {
            q.add(firstHalf.remove());
            q.add(q.remove());
        }
    }

    // Queue reversal
    public static void reverseQueue(Queue<Integer> q) {
        Stack<Integer> s = new Stack<>();
        
        while (!q.isEmpty()) {
            s.push(q.remove());
        }
        
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
    }

    // Stack using Two Queues
    static class Stack2Queues {
        static Queue<Integer> q1 = new LinkedList<>();
        static Queue<Integer> q2 = new LinkedList<>();

        public static boolean isEmpty() {
            return q1.isEmpty() && q2.isEmpty();
        }

        public static void push(int data) {
            if (!q1.isEmpty()) {
                q1.add(data);
            } else {
                q2.add(data);
            }
        }

        public static int pop() {
            if (isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            
            int top = -1;
            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    if (!q1.isEmpty()) {
                        q2.add(top);
                    }
                }
            } else {
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    if (!q2.isEmpty()) {
                        q1.add(top);
                    }
                }
            }
            return top;
        }

        public static int peek() {
            if (isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            
            int top = -1;
            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    q2.add(top);
                }
            } else {
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    q1.add(top);
                }
            }
            return top;
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all queue applications
         * 
         * First Non-Repeating: "aabccxb"
         * Expected: a -1 b b b x
         * 
         * Interleaving: [1,2,3,4,5,6]
         * Expected: [1,4,2,5,3,6]
         * 
         * Reversal: [1,2,3,4,5,6]
         * Expected: [6,5,4,3,2,1]
         * 
         * Stack2Queues: push(1,2,3), pop()
         * Expected: 3,2,1
         */
        
        // Test First Non-Repeating Character
        System.out.println("Queue Applications - First Non-Repeating Character");
        System.out.println("String: aabccxb");
        System.out.println("Expected: a -1 b b b x");
        System.out.print("Output: ");
        firstNonRepeatingChar("aabccxb");
        
        // Test Queue Interleaving
        System.out.println("\nQueue Interleaving:");
        Queue<Integer> interleaveQueue = new ArrayDeque<>();
        interleaveQueue.add(1);
        interleaveQueue.add(2);
        interleaveQueue.add(3);
        interleaveQueue.add(4);
        interleaveQueue.add(5);
        interleaveQueue.add(6);
        
        System.out.println("Original: [1,2,3,4,5,6]");
        System.out.println("Expected: [1,4,2,5,3,6]");
        
        interleaveQueue(interleaveQueue);
        System.out.print("Interleaved: ");
        while (!interleaveQueue.isEmpty()) {
            System.out.print(interleaveQueue.remove() + " ");
        }
        System.out.println();
        
        // Test Queue Reversal
        System.out.println("\nQueue Reversal:");
        Queue<Integer> reverseQueue = new ArrayDeque<>();
        reverseQueue.add(1);
        reverseQueue.add(2);
        reverseQueue.add(3);
        reverseQueue.add(4);
        reverseQueue.add(5);
        reverseQueue.add(6);
        
        System.out.println("Original: [1,2,3,4,5,6]");
        System.out.println("Expected: [6,5,4,3,2,1]");
        
        reverseQueue(reverseQueue);
        System.out.print("Reversed: ");
        while (!reverseQueue.isEmpty()) {
            System.out.print(reverseQueue.remove() + " ");
        }
        System.out.println();
        
        // Test Stack using Two Queues
        System.out.println("\nStack using Two Queues:");
        Stack2Queues stack = new Stack2Queues();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        System.out.println("Pushed: 1,2,3");
        System.out.println("Expected pop order: 3,2,1");
        System.out.print("Popped: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: First non-repeating with all same characters
        System.out.println("All same characters:");
        System.out.print("String: aaaa: ");
        firstNonRepeatingChar("aaaa");
        
        // Test case 3: Odd-sized queue interleaving
        Queue<Integer> oddQueue = new ArrayDeque<>();
        oddQueue.add(1);
        oddQueue.add(2);
        oddQueue.add(3);
        oddQueue.add(4);
        oddQueue.add(5);
        
        System.out.println("\nOdd-sized interleaving:");
        System.out.println("Original: [1,2,3,4,5]");
        interleaveQueue(oddQueue);
        System.out.print("Interleaved: ");
        while (!oddQueue.isEmpty()) {
            System.out.print(oddQueue.remove() + " ");
        }
        System.out.println();
    }
}
