public class ScenarioBasedQuestion {

    /*Question 1
    Rajni wants to create a data type for the number of books in her case. her shelf can max of 75books she allocate 7 bits
    to datatype. Later another shelf added realizes she can use same dt fir storing the number of books.
    what is the max possible capacity of her new added shelf
    ops: 52, 127, 53 , 75

    // ANSWER: 127
    // EXPLANATION:
    // 1. 7 bits can store 2^7 = 128 different values (0 to 127 for unsigned)
    // 2. Rajni's original shelf holds max 75 books, which fits within 7-bit range (75 < 127)
    // 3. She allocated 7 bits for the data type
    // 4. For the new shelf, using the same 7-bit data type:
    //    - Maximum value that can be stored = 2^7 - 1 = 127
    //    - Therefore, max capacity of new shelf = 127 books
    //
    // INTUITION:
    // The question is about bit representation limits. With n bits, you can represent
    // values from 0 to 2^n - 1. Here, 7 bits → 0 to 127. Since she's using the same
    // 7-bit data type for the new shelf, its maximum capacity is 127 books.
     */

    /*
    Question 2
    Find the array representation if 12 is inserted in: 7, 11, 15, 19, 23, 27, 31

    // ANSWER: 7, 11, 12, 15, 19, 23, 27, 31
    // EXPLANATION:
    // 1. Original array is sorted in ascending order: 7, 11, 15, 19, 23, 27, 31
    // 2. To insert 12 while maintaining sorted order:
    //    - 12 > 7 (keep 7)
    //    - 12 > 11 (keep 11)
    //    - 12 < 15 (insert 12 here)
    // 3. Result: 7, 11, 12, 15, 19, 23, 27, 31
    //
    // INTUITION:
    // This is a simple insertion into a sorted array. Find the correct position
    // where 12 fits (between 11 and 15) and insert it there to maintain ascending order.
     */

    /*
    Question 3
    What algorithm paradigm is used in Kruskal's solution?

    // ANSWER: Greedy Algorithm
    // EXPLANATION:
    // 1. Kruskal's algorithm is a greedy algorithm used to find the Minimum Spanning Tree (MST)
    // 2. It works by:
    //    - Sorting all edges in increasing order of their weight
    //    - Picking the smallest edge that doesn't form a cycle
    //    - Repeating until all vertices are connected
    // 3. At each step, it makes the locally optimal choice (smallest edge) hoping to find the global optimum
    //
    // INTUITION:
    // Greedy algorithms make the best choice at each step without reconsidering.
    // Kruskal's demonstrates this by always picking the smallest available edge
    // that doesn't create a cycle, which eventually leads to the minimum spanning tree.
     */

    /*
    Question 4
    In what type of topology is a repeater connected with each node?

    // ANSWER: Ring Topology
    // EXPLANATION:
    // 1. In Ring topology, all nodes are connected in a circular formation
    // 2. Each node is connected to exactly two other nodes (forming a ring)
    // 3. Repeaters are used in Ring topology to:
    //    - Regenerate the signal as it travels around the ring
    //    - Maintain signal integrity since the signal passes through each node
    //    - Each node/repeater boosts the signal before passing it to the next node
    // 4. As signal travels in one direction, each node acts as a repeater to regenerate the signal
    //
    // INTUITION:
    // In Ring topology, the signal circulates through all nodes. Since the signal
    // passes through each node, repeaters are needed at each node to regenerate
    // and boost the signal before forwarding it to the next node in the ring.
     */
    /*
    Question 5
    Which scenario would you choose for a stack data structure?
    1) Pilling up of chairs one above another
    2) People standing in the line at service center
    3) Priority of customers queue
    4) Tatkal ticket booking

    // ANSWER: 1) Pilling up of chairs one above another
    // EXPLANATION:
    // 1. Stack follows LIFO (Last In First Out) principle
    // 2. Pilling up chairs:
    //    - You place chairs one on top of another
    //    - To remove a chair, you must remove the topmost chair first
    //    - This is exactly how a stack works (push = add on top, pop = remove from top)
    // 3. Other options:
    //    - Option 2: Queue (FIFO - First In First Out)
    //    - Option 3: Priority Queue (based on priority)
    //    - Option 4: Could be queue or priority queue
    //
    // INTUITION:
    // Stack is like a pile of objects - you add to the top and remove from the top.
    // The last item added is the first one to be removed (LIFO). Chairs piled up
    // demonstrate this perfectly - you can't remove the bottom chair without removing
    // all chairs above it first.
     */
    
}
