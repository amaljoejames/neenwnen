import java.util.*;

/**
 * QUESTION: Heaps Basics - Implement fundamental heap operations including
 * heap data structure, heap sort, and priority queue operations.
 * 
 * QUESTION EXPLANATION:
 * Heap: Complete binary tree with heap property (parent >= children for max heap).
 * Used for priority queues, heap sort, and efficient O(log n) operations.
 * 
 * Algorithms covered:
 * 1. Heap Implementation: Basic heap operations (add, remove, heapify)
 * 2. Heap Sort: Sort array using heap data structure
 * 3. Priority Queue: Custom objects with comparable interface
 * 
 * APPROACH USED: Array-based Heap Implementation with Comparable Interface
 * 
 * PSEUDOCODE:
 * 
 * Heap Class:
 * class Heap:
 *   arr = ArrayList<Integer>()
 *   
 *   function add(data):
 *     arr.add(data)
 *     x = arr.size() - 1
 *     parent = (x - 1) / 2
 *     while arr[x] < arr[parent]:
 *       swap(arr[x], arr[parent])
 *       x = parent
 *       parent = (x - 1) / 2
 *   
 *   function remove():
 *     data = arr[0]
 *     swap(arr[0], arr[last])
 *     remove last element
 *     heapify(0)
 *     return data
 *   
 *   function heapify(idx):
 *     left = 2 * idx + 1
 *     right = 2 * idx + 2
 *     minIdx = idx
 *     
 *     if left < size && arr[left] < arr[minIdx]:
 *       minIdx = left
 *     if right < size && arr[right] < arr[minIdx]:
 *       minIdx = right
 *     
 *     if minIdx != idx:
 *       swap(arr[idx], arr[minIdx])
 *       heapify(minIdx)
 * 
 * Heap Sort:
 * function heapSort(arr):
 *   build max heap
 *   for i = n/2 to 0:
 *     heapify(i, n, arr)
 *   
 *   for i = n-1 to 1:
 *     swap(arr[0], arr[i])
 *     heapify(0, i, arr)
 * 
 * EXPLANATION OF STEPS:
 * 1. Heap uses array representation of complete binary tree
 * 2. Parent at index i: children at 2i+1 and 2i+2
 * 3. Heapify maintains heap property from given index
 * 4. Heap sort uses heap property for O(n log n) sorting
 * 
 * DRY RUN for Heap Operations:
 * 
 * Insert: 3, 4, 1, 5
 * 
 * Step 1: Insert 3
 * arr = [3]
 * 
 * Step 2: Insert 4
 * arr = [3, 4]
 * 4 > 3, swap: arr = [4, 3]
 * 
 * Step 3: Insert 1
 * arr = [4, 3, 1]
 * 1 < 4, swap: arr = [1, 3, 4]
 * 
 * Step 4: Insert 5
 * arr = [1, 3, 4, 5]
 * 5 > 1, swap: arr = [5, 3, 4, 1]
 * 
 * Final heap: [5, 3, 4, 1]
 * 
 * DRY RUN for Heap Sort:
 * 
 * Array: [1, 2, 5, 4, 3]
 * 
 * Build max heap: [5, 4, 3, 1, 2]
 * 
 * Sort steps:
 * 1. swap(5,2): [2, 4, 3, 1, 5], heapify(0,4): [4, 2, 3, 1, 5]
 * 2. swap(4,1): [1, 2, 3, 4, 5], heapify(0,3): [3, 2, 1, 4, 5]
 * 3. swap(3,2): [2, 3, 1, 4, 5], heapify(0,2): [2, 3, 1, 4, 5]
 * 
 * Final sorted: [1, 2, 3, 4, 5]
 */
public class HeapsBasics {
    
    static class Student implements Comparable<Student> {
        String name;
        int rank;
        
        public Student(String name, int rank) {
            this.name = name;
            this.rank = rank;
        }
        
        @Override
        public int compareTo(Student s2) {
            return this.rank - s2.rank;
        }
    }

    // Implementing heaps
    static class Heap {
        ArrayList<Integer> arr = new ArrayList<>();
        
        public void add(int data) {
            arr.add(data);
            int x = arr.size() - 1; // x->child index
            int par = (x - 1) / 2;
            
            while (arr.get(x) < arr.get(par)) { // > for max heap
                int temp = arr.get(x);
                arr.set(x, arr.get(par));
                arr.set(par, temp);
                x = par;
                par = (x - 1) / 2;
            }
        }
        
        public int peek() {
            return arr.get(0);
        }
        
        public void heapify(int idx) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int minIdx = idx;
            
            if (left < arr.size() && arr.get(left) < arr.get(minIdx)) { // > for max heap
                minIdx = left;
            }
            if (right < arr.size() && arr.get(right) < arr.get(minIdx)) { // > for max heap
                minIdx = right;
            }
            
            if (minIdx != idx) {
                int temp = arr.get(idx);
                arr.set(idx, arr.get(minIdx));
                arr.set(minIdx, temp);
                heapify(minIdx);
            }
        }
        
        public int remove() {
            int data = arr.get(0);
            // swap first and last
            int temp = arr.get(0);
            arr.set(0, arr.get(arr.size() - 1));
            arr.set(arr.size() - 1, temp);
            // delete last
            arr.remove(arr.size() - 1);
            // step 3 - heapify
            heapify(0);
            return data;
        }
        
        public boolean isEmpty() {
            return arr.size() == 0;
        }
    }

    // Heap sort algorithm
    public static void heapify(int idx, int size, int arr[]) {
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;
        int maxIdx = idx;
        
        if (left < size && arr[left] > arr[maxIdx]) { // > for max heap
            maxIdx = left;
        }
        if (right < size && arr[right] > arr[maxIdx]) { // > for max heap
            maxIdx = right;
        }
        
        if (maxIdx != idx) {
            int temp = arr[idx];
            arr[idx] = arr[maxIdx];
            arr[maxIdx] = temp;
            heapify(maxIdx, size, arr);
        }
    }
    
    public static void heapSort(int arr[]) {
        // build max heap
        int n = arr.length;
        for (int i = n / 2; i >= 0; i--) {
            heapify(i, n, arr);
        }
        
        // push largest at end
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(0, i, arr);
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all heap operations
         * 
         * Priority Queue: Students with ranks
         * Expected: Sorted by rank (C:1, A:2, D:3, B:12)
         * 
         * Heap Operations: Insert 3,4,1,5
         * Expected: Max heap [5,4,1,3]
         * 
         * Heap Sort: [1,2,5,4,3]
         * Expected: Sorted [1,2,3,4,5]
         */
        
        // Test Priority Queue with custom objects
        PriorityQueue<Student> pq = new PriorityQueue<>();
        pq.add(new Student("A", 2));
        pq.add(new Student("B", 12));
        pq.add(new Student("C", 1));
        pq.add(new Student("D", 3));
        
        System.out.println("Heaps Basics - Priority Queue");
        System.out.println("Students: A(2), B(12), C(1), D(3)");
        System.out.println("Expected order: C(1), A(2), D(3), B(12)");
        
        while (!pq.isEmpty()) {
            System.out.println(pq.peek().name + ": " + pq.peek().rank);
            pq.remove();
        }
        
        // Test Heap Operations
        System.out.println("\nHeap Operations:");
        System.out.println("Inserting: 3, 4, 1, 5");
        
        Heap x = new Heap();
        x.add(3);
        x.add(4);
        x.add(1);
        x.add(5);
        
        System.out.println("Expected max heap order: 5, 4, 3, 1");
        System.out.println("Actual removal order:");
        
        while (!x.isEmpty()) {
            System.out.println(x.peek());
            x.remove();
        }
        
        // Test Heap Sort
        System.out.println("\nHeap Sort:");
        int arr[] = {1, 2, 5, 4, 3};
        System.out.println("Original: [1,2,5,4,3]");
        System.out.println("Expected sorted: [1,2,3,4,5]");
        
        heapSort(arr);
        System.out.print("Sorted: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single element
        int[] single = {5};
        System.out.println("Single element:");
        heapSort(single);
        System.out.println("Result: " + Arrays.toString(single));
        
        // Test case 3: Already sorted
        int[] sorted = {1, 2, 3, 4, 5};
        System.out.println("Already sorted:");
        heapSort(sorted);
        System.out.println("Result: " + Arrays.toString(sorted));
        
        // Test case 4: Reverse sorted
        int[] reverse = {5, 4, 3, 2, 1};
        System.out.println("Reverse sorted:");
        heapSort(reverse);
        System.out.println("Result: " + Arrays.toString(reverse));
    }
}
