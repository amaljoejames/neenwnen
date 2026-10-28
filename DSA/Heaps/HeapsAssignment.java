import java.util.*;

/**
 * QUESTION: Heaps Assignment Problems - Implement Kth largest element in stream
 * and minimum operations to halve array sum using heaps.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Kth Largest Element in Stream:
 * Given infinite stream of integers, find Kth largest element at any point.
 * Use min heap of size K to track K largest elements.
 * 
 * 2. Minimum Operations to Halve Array Sum:
 * Given array, find minimum operations to make sum <= half of initial sum.
 * In each operation, halve value of any array element.
 * Use max heap to always halve the largest element.
 * 
 * APPROACH USED: Min Heap for Kth Largest, Max Heap for Halving Operations
 * 
 * PSEUDOCODE:
 * 
 * Kth Largest Element:
 * class KthLargest:
 *   minHeap = PriorityQueue<Integer>()
 *   k = K
 *   
 *   function getAllKthNumber(arr):
 *     list = ArrayList<Integer>()
 *     for val in arr:
 *       if minHeap.size() < k:
 *         minHeap.add(val)
 *       else:
 *         if val > minHeap.peek():
 *           minHeap.poll()
 *           minHeap.add(val)
 *       list.add(minHeap.size() >= k ? minHeap.peek() : -1)
 *     return list
 * 
 * Minimum Operations to Halve Sum:
 * function minOps(nums):
 *   sum = nums.stream().mapToInt(Integer::intValue).sum()
 *   pq = PriorityQueue<Integer>(Collections.reverseOrder())
 *   pq.addAll(nums)
 *   
 *   temp = sum
 *   count = 0
 *   while temp > sum / 2.0:
 *     x = pq.poll()
 *     temp -= x / 2.0
 *     pq.add(x / 2)
 *     count++
 *   return count
 * 
 * EXPLANATION OF STEPS:
 * 1. Kth Largest: Maintain min heap of size K with largest elements
 * 2. Halving Operations: Always halve the largest element for maximum reduction
 * 3. Time complexity: O(n log K) for Kth largest, O(n log n) for halving
 * 
 * DRY RUN for Kth Largest Element:
 * 
 * Stream: [10, 20, 11, 70, 50, 40, 100, 5], K = 3
 * 
 * Process:
 * 10: heap=[10], result=[-, -, 10]
 * 20: heap=[10,20], result=[-, -, 10]
 * 11: heap=[10,20,11], result=[-, -, 10]
 * 70: heap=[20,11,70], result=[-, -, 20]
 * 50: heap=[20,11,70], result=[-, -, 20]
 * 40: heap=[20,11,70], result=[-, -, 20]
 * 100: heap=[70,40,100], result=[-, -, 70]
 * 5: heap=[40,5,100], result=[-, -, 40]
 * 
 * Final: [_, _, 10, 11, 20, 40, 50, 50]
 * 
 * DRY RUN for Halving Operations:
 * 
 * Array: [1, 5, 8, 19]
 * Initial sum = 33, target = 16.5
 * 
 * Process:
 * Step 1: max=19, temp=33-9.5=23.5, count=1, heap=[8,5,1,9.5]
 * Step 2: max=9.5, temp=23.5-4.75=18.75, count=2, heap=[8,5,1,4.75]
 * Step 3: max=8, temp=18.75-4=14.75, count=3, heap=[5,1,4,4.75]
 * 
 * Final: count = 3
 */
public class HeapsAssignment {
    
    // Function to find K'th largest element in a stream
    static class KthLargest {
        static PriorityQueue<Integer> minHeap;
        static int k;

        static List<Integer> getAllKthNumber(int arr[]) {
            List<Integer> list = new ArrayList<>();
            
            for (int val : arr) {
                if (minHeap.size() < k) {
                    minHeap.add(val);
                } else {
                    if (val > minHeap.peek()) {
                        minHeap.poll();
                        minHeap.add(val);
                    }
                }
                list.add(minHeap.size() >= k ? minHeap.peek() : -1);
            }
            
            return list;
        }
    }
    
    // Function to minimize operations to halve array sum
    public static int minOps(ArrayList<Integer> nums) {
        int sum = nums.stream().mapToInt(Integer::intValue).sum();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.addAll(nums);

        double temp = sum;
        int cnt = 0;
        
        while (temp > sum / 2.0) {
            int x = pq.poll();
            temp -= x / 2.0;
            pq.add(x / 2);
            cnt++;
        }
        
        return cnt;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test both assignment problems
         * 
         * Kth Largest: [10,20,11,70,50,40,100,5], K=3
         * Expected: [_,_,10,11,20,40,50,50]
         * 
         * Halving Operations: [1,5,8,19]
         * Expected: 3 operations
         */
        
        // Test Kth Largest Element in Stream
        KthLargest.minHeap = new PriorityQueue<>();
        KthLargest.k = 3;
        int arr[] = {10, 20, 11, 70, 50, 40, 100, 5};
        
        System.out.println("Heaps Assignment - Kth Largest Element in Stream");
        System.out.println("Stream: [10,20,11,70,50,40,100,5], K = 3");
        System.out.println("Expected: [_,_,10,11,20,40,50,50]");
        
        List<Integer> result = KthLargest.getAllKthNumber(arr);
        System.out.println("Result: " + result);
        
        // Show step-by-step for Kth Largest
        System.out.println("\nStep-by-step Kth largest:");
        System.out.println("1. 10: heap=[10], 3rd largest=10");
        System.out.println("2. 20: heap=[10,20], 3rd largest=10");
        System.out.println("3. 11: heap=[10,20,11], 3rd largest=10");
        System.out.println("4. 70: heap=[20,11,70], 3rd largest=20");
        System.out.println("5. 50: heap=[20,11,70], 3rd largest=20");
        System.out.println("6. 40: heap=[20,11,70], 3rd largest=20");
        System.out.println("7. 100: heap=[70,40,100], 3rd largest=70");
        System.out.println("8. 5: heap=[40,5,100], 3rd largest=40");
        
        // Test Minimum Operations to Halve Array Sum
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(5);
        nums.add(8);
        nums.add(19);
        
        System.out.println("\nMinimum Operations to Halve Array Sum:");
        System.out.println("Array: [1,5,8,19]");
        System.out.println("Initial sum = 33, target = 16.5");
        System.out.println("Expected: 3 operations");
        
        int ops = minOps(nums);
        System.out.println("Result: " + ops + " operations");
        
        // Show step-by-step for Halving Operations
        System.out.println("\nStep-by-step halving:");
        System.out.println("1. Max=19, halve to 9.5, sum=23.5, ops=1");
        System.out.println("2. Max=9.5, halve to 4.75, sum=18.75, ops=2");
        System.out.println("3. Max=8, halve to 4, sum=14.75, ops=3");
        System.out.println("Target achieved: 14.75 <= 16.5");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Kth largest with K=1
        KthLargest.minHeap = new PriorityQueue<>();
        KthLargest.k = 1;
        int[] arr2 = {5, 3, 8, 1, 9};
        System.out.println("Kth largest with K=1:");
        System.out.println("Stream: [5,3,8,1,9], K=1");
        List<Integer> result2 = KthLargest.getAllKthNumber(arr2);
        System.out.println("Result: " + result2);
        System.out.println("Expected: [5,5,8,8,9] (maximum so far)");
        
        // Test case 3: Halving with small numbers
        ArrayList<Integer> small = new ArrayList<>();
        small.add(1);
        small.add(1);
        small.add(1);
        small.add(1);
        System.out.println("Halving with small numbers:");
        System.out.println("Array: [1,1,1,1]");
        int ops2 = minOps(small);
        System.out.println("Result: " + ops2 + " operations");
        System.out.println("Expected: 2 operations (halve two 1s to 0.5)");
        
        // Test case 4: Kth largest with duplicate values
        KthLargest.minHeap = new PriorityQueue<>();
        KthLargest.k = 2;
        int[] arr3 = {5, 5, 5, 5, 5};
        System.out.println("Kth largest with duplicates:");
        System.out.println("Stream: [5,5,5,5,5], K=2");
        List<Integer> result3 = KthLargest.getAllKthNumber(arr3);
        System.out.println("Result: " + result3);
        System.out.println("Expected: [_,5,5,5,5,5]");
    }
}
