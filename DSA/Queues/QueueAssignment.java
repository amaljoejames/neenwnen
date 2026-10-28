import java.util.*;

/**
 * QUESTION: Queue Assignment Problems - Implement interview problems including
 * binary numbers generation, connect ropes, job sequencing, and sliding window maximum.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Generate Binary Numbers:
 * Generate binary numbers from 1 to N using queue.
 * Use BFS approach by appending 0 and 1 to existing numbers.
 * 
 * 2. Connect n Ropes with Minimum Cost:
 * Connect ropes with minimum cost using priority queue.
 * Always connect two smallest ropes first (greedy approach).
 * 
 * 3. Job Sequencing Problem:
 * Schedule jobs to maximize profit within deadlines.
 * Sort by profit, use slot array for scheduling.
 * 
 * 4. Sliding Window Maximum:
 * Find maximum in each sliding window of size K.
 * Use deque to maintain decreasing order of elements.
 * 
 * APPROACH USED: Queue, Priority Queue, and Deque Data Structures
 * 
 * PSEUDOCODE:
 * 
 * Generate Binary Numbers:
 * function generatePrintBinary(n):
 *   q = Queue<String>()
 *   q.add("1")
 *   
 *   while n-- > 0:
 *     s1 = q.poll()
 *     print s1
 *     q.add(s1 + "0")
 *     q.add(s1 + "1")
 * 
 * Connect Ropes:
 * function minCost(arr, n):
 *   pq = PriorityQueue<Integer>()
 *   for i = 0 to n-1: pq.add(arr[i])
 *   
 *   res = 0
 *   while pq.size() > 1:
 *     first = pq.poll()
 *     second = pq.poll()
 *     res += first + second
 *     pq.add(first + second)
 *   
 *   return res
 * 
 * Job Sequencing:
 * function printJobScheduling(jobs):
 *   sort jobs by profit descending
 *   slot = boolean[n]
 *   jobOrder = char[n]
 *   
 *   for each job:
 *     for j = min(n, job.deadline)-1 downto 0:
 *       if !slot[j]:
 *         slot[j] = true
 *         jobOrder[j] = job.job_id
 *         break
 *   
 *   print jobOrder
 * 
 * Sliding Window Maximum:
 * function printMax(arr, n, k):
 *   Qi = Deque<Integer>()
 *   
 *   for i = 0 to k-1:
 *     while !Qi.isEmpty() && arr[i] >= arr[Qi.peekLast()]:
 *       Qi.removeLast()
 *     Qi.addLast(i)
 *   
 *   for i = k to n-1:
 *     print arr[Qi.peek()]
 *     
 *     while !Qi.isEmpty() && Qi.peek() <= i-k:
 *       Qi.removeFirst()
 *     
 *     while !Qi.isEmpty() && arr[i] >= arr[Qi.peekLast()]:
 *       Qi.removeLast()
 *     Qi.addLast(i)
 *   
 *   print arr[Qi.peek()]
 * 
 * EXPLANATION OF STEPS:
 * 1. Binary Numbers: BFS generation using queue for level-order traversal
 * 2. Connect Ropes: Greedy algorithm with min heap for optimal cost
 * 3. Job Sequencing: Sort by profit, fill latest available slot
 * 4. Sliding Window: Maintain deque of potential maximums
 * 
 * DRY RUN for Generate Binary Numbers:
 * 
 * N = 3
 * 
 * Process:
 * q = ["1"]
 * 
 * Iteration 1: s1 = "1", print "1", q = ["10", "11"]
 * Iteration 2: s1 = "10", print "10", q = ["11", "100", "101"]
 * Iteration 3: s1 = "11", print "11", q = ["100", "101", "110", "111"]
 * 
 * Output: 1, 10, 11
 * 
 * DRY RUN for Connect Ropes:
 * 
 * Ropes: [4, 3, 2, 6]
 * 
 * Process:
 * pq = [2, 3, 4, 6]
 * 
 * Step 1: first=2, second=3, cost=5, pq=[4,5,6]
 * Step 2: first=4, second=5, cost=14, pq=[6,9]
 * Step 3: first=6, second=9, cost=29, pq=[15]
 * 
 * Final cost: 29
 * 
 * DRY RUN for Sliding Window Maximum:
 * 
 * arr = [1,3,-1,-3,5,3,6,7], k = 3
 * 
 * Process:
 * Window 1: [1,3,-1] -> max = 3
 * Window 2: [3,-1,-3] -> max = 3
 * Window 3: [-1,-3,5] -> max = 5
 * Window 4: [-3,5,3] -> max = 5
 * Window 5: [5,3,6] -> max = 6
 * Window 6: [3,6,7] -> max = 7
 * 
 * Output: 3, 3, 5, 5, 6, 7
 */
public class QueueAssignment {
    
    static class Job {
        char job_id;
        int deadline;
        int profit;

        Job(char job_id, int deadline, int profit) {
            this.deadline = deadline;
            this.job_id = job_id;
            this.profit = profit;
        }
    }

    // Generate Binary Numbers
    public static void generatePrintBinary(int n) {
        Queue<String> q = new ArrayDeque<>();
        q.add("1");

        while (n-- > 0) {
            String s1 = q.poll();
            System.out.print(s1 + " ");
            q.add(s1 + "0");
            q.add(s1 + "1");
        }
        System.out.println();
    }

    // Connect n ropes with minimum cost
    public static int minCost(int arr[], int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(arr[i]);
        }

        int res = 0;
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            res += first + second;
            pq.add(first + second);
        }
        return res;
    }

    // Job Sequencing Problem
    public static void printJobScheduling(ArrayList<Job> arr) {
        int n = arr.size();
        Collections.sort(arr, (a, b) -> b.profit - a.profit);

        boolean[] slot = new boolean[n];
        char[] jobOrder = new char[n];

        for (Job job : arr) {
            for (int j = Math.min(n, job.deadline) - 1; j >= 0; j--) {
                if (!slot[j]) {
                    slot[j] = true;
                    jobOrder[j] = job.job_id;
                    break;
                }
            }
        }

        for (char job : jobOrder) {
            if (job != 0) System.out.print(job + " ");
        }
        System.out.println();
    }

    // Maximum of all subarrays of size k
    public static void printMax(int arr[], int n, int k) {
        Deque<Integer> Qi = new ArrayDeque<>();

        int i;
        for (i = 0; i < k; ++i) {
            while (!Qi.isEmpty() && arr[i] >= arr[Qi.peekLast()])
                Qi.removeLast();
            Qi.addLast(i);
        }

        for (; i < n; ++i) {
            System.out.print(arr[Qi.peek()] + " ");
            
            while ((!Qi.isEmpty()) && Qi.peek() <= i - k)
                Qi.removeFirst();

            while ((!Qi.isEmpty()) && arr[i] >= arr[Qi.peekLast()])
                Qi.removeLast();

            Qi.addLast(i);
        }
        System.out.print(arr[Qi.peek()]);
        System.out.println();
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all assignment problems
         * 
         * Binary Numbers: N=3
         * Expected: 1 10 11
         * 
         * Connect Ropes: [4,3,2,6]
         * Expected: Cost 29
         * 
         * Job Sequencing: Jobs with deadlines and profits
         * Expected: Maximum profit sequence
         * 
         * Sliding Window: [1,3,-1,-3,5,3,6,7], k=3
         * Expected: 3 3 5 5 6 7
         */
        
        // Test Generate Binary Numbers
        System.out.println("Queue Assignment - Generate Binary Numbers");
        System.out.println("N = 3");
        System.out.println("Expected: 1 10 11");
        System.out.print("Output: ");
        generatePrintBinary(3);
        
        // Test Connect Ropes
        System.out.println("\nConnect Ropes with Minimum Cost:");
        int ropes[] = {4, 3, 2, 6};
        System.out.println("Ropes: [4,3,2,6]");
        System.out.println("Expected: Cost 29");
        int cost = minCost(ropes, ropes.length);
        System.out.println("Actual cost: " + cost);
        
        // Test Job Sequencing
        System.out.println("\nJob Sequencing Problem:");
        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(new Job('a', 2, 100));
        jobs.add(new Job('b', 1, 19));
        jobs.add(new Job('c', 2, 27));
        jobs.add(new Job('d', 1, 25));
        jobs.add(new Job('e', 3, 15));

        System.out.println("Jobs with deadlines and profits");
        System.out.print("Maximum profit sequence: ");
        printJobScheduling(jobs);
        
        // Test Sliding Window Maximum
        System.out.println("\nSliding Window Maximum:");
        int arr[] = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println("Array: [1,3,-1,-3,5,3,6,7], k=3");
        System.out.println("Expected: 3 3 5 5 6 7");
        System.out.print("Output: ");
        printMax(arr, arr.length, k);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Binary numbers with N=5
        System.out.println("Binary numbers N=5:");
        System.out.print("Output: ");
        generatePrintBinary(5);
        
        // Test case 3: Connect ropes with different sizes
        int ropes2[] = {1, 2, 3, 4, 5};
        System.out.println("\nConnect ropes [1,2,3,4,5]:");
        int cost2 = minCost(ropes2, ropes2.length);
        System.out.println("Cost: " + cost2);
        
        // Test case 4: Sliding window with k=2
        int arr2[] = {2, 1, 5, 3, 4};
        System.out.println("\nSliding window k=2:");
        System.out.println("Array: [2,1,5,3,4]");
        System.out.print("Output: ");
        printMax(arr2, arr2.length, 2);
    }
}
