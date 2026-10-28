import java.util.*;

/**
 * QUESTION: Advanced Hashing Problems - Implement complex hashing algorithms including
 * largest subarray with sum 0, subarray sum equal to K, find itinerary, and sort by frequency.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Largest Subarray with Sum 0:
 * Find the longest contiguous subarray with sum equal to 0.
 * Use prefix sum technique and hashmap to track first occurrence of each sum.
 * 
 * 2. Subarray Sum Equal to K:
 * Count number of subarrays where sum equals K.
 * Use prefix sum with hashmap for O(n) time complexity.
 * 
 * 3. Find Itinerary:
 * Given list of tickets with from->to destinations, reconstruct itinerary.
 * Use hashmap and DFS to find the starting point and build path.
 * 
 * 4. Sort by Frequency:
 * Sort characters in decreasing order based on their frequency.
 * Use priority queue (max heap) for efficient sorting.
 * 
 * APPROACH USED: HashMap with Prefix Sums and DFS
 * 
 * PSEUDOCODE:
 * 
 * Largest Subarray with Sum 0:
 * function largestSubarraySumZero(arr):
 *   map = HashMap<Integer, Integer>()
 *   prefixSum = 0, maxLen = 0
 *   
 *   for i = 0 to n-1:
 *     prefixSum += arr[i]
 *     if map.containsKey(prefixSum):
 *       map.put(prefixSum, i - map.get(prefixSum))
 *     else:
 *       map.put(prefixSum, i)
 *   
 *     if prefixSum == 0:
 *       maxLen = i + 1
 *   return maxLen
 * 
 * Subarray Sum Equal to K:
 * function subarraySumEqualK(arr, K):
 *   map = HashMap<Integer, Integer>()
 *   prefixSum = 0, count = 0
 *   
 *   for i = 0 to n-1:
 *     prefixSum += arr[i]
 *     count += map.getOrDefault(prefixSum - K, 0)
 *     map.put(prefixSum, map.getOrDefault(prefixSum - K, 0) + 1)
 *   
 *   return count
 * 
 * Find Itinerary:
 * function findItinerary(tickets):
 *   map = HashMap<String, String>()
 *   for each ticket in tickets:
 *     map.put(ticket.to, ticket.from)
 *   
 *   return findItineraryHelper("JFK", map, new HashSet<>())
 * 
 * function findItineraryHelper(current, map, visited):
 *   if visited.size() == map.size(): return current
 *   
 *   for each dest in map.getOrDefault(current, new HashSet<>()):
 *     if findItineraryHelper(dest, map, visited)):
 *       return dest
 *   
 *   return null
 * 
 * Sort by Frequency:
 * function frequencySort(s):
 *   map = HashMap<Character, Integer>()
 *   for each character in s:
 *     map.put(ch, map.getOrDefault(ch, 0) + 1)
 *   
 *   pq = PriorityQueue<Map.Entry<Character, Integer>>(
 *     (a, b) -> b.getValue().equals(a.getValue()) ?
 *       Character.compare(a.getKey(), b.getKey()) :
 *       b.getValue() - a.getValue())
 *   )
 *   
 *   pq.addAll(map.entrySet())
 *   StringBuilder result = new StringBuilder()
 *   
 *   while (!pq.isEmpty()):
 *     entry = pq.poll()
 *     char ch = entry.getKey()
 *     int val = entry.getValue()
 *     while (val-- > 0):
 *       result.append(ch)
 *     result.append(ch)
 *   
 *   return result.toString()
 * 
 * EXPLANATION OF STEPS:
 * 1. Use prefix sums to track subarray sums
 * 2. Use hashmap for O(1) subarray sum queries
 * 3. Use DFS for itinerary reconstruction
 * 4. Use max heap for frequency sorting
 * 
 * DRY RUN for Largest Subarray with Sum 0:
 * 
 * Array: [1, -1, 2, -3, 1, 1, -2, 3, 4]
 * prefix sums: [1, 0, 2, 0, 1, 2, 0, 1, 2, 6]
 * sum 0 occurs at indices 0, 3, 6
 * longest subarray with sum 0: [1, -1, 2, -3] (length 3)
 * 
 * DRY RUN for Find Itinerary:
 * 
 * Tickets: [["JFK","SFO"],["SFO","LHR"],["LHR","DXB"]]
 * Expected itinerary: "JFK->SFO->LHR->DXB->JFK"
 * 
 * DRY RUN for Sort by Frequency:
 * 
 * String: "tree"
 * Character counts: t:3, r:1, e:1
 * Sorted order: "eetr" (t,e,e,r)
 */
public class HashingAdvanced {
    
    // Largest subarray with sum 0
    public static void largestSubarraySumZero(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int prefixSum = 0, maxLen = 0;
        
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            
            if (map.containsKey(prefixSum)) {
                map.put(prefixSum, i - map.get(prefixSum));
            } else {
                map.put(prefixSum, i);
            }
            
            if (prefixSum == 0) {
                maxLen = i + 1;
            }
        }
        
        System.out.println("Maximum length of subarray with sum 0: " + maxLen);
    }

    // Subarray sum equal to K
    public static int subarraySumEqualK(int[] nums, int K) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int prefixSum = 0, count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            count += map.getOrDefault(prefixSum - K, 0);
            map.put(prefixSum, map.getOrDefault(prefixSum - K, 0) + 1);
        }
        
        return count;
    }
    
    // Find itinerary helper function
    public static String findItineraryHelper(String current, HashMap<String, String> map, HashSet<String> visited) {
        if (visited.size() == map.size()) {
            return current;
        }
        
        // Get the destination for current location
        String destination = map.get(current);
        if (destination != null && !visited.contains(destination)) {
            visited.add(destination);
            String result = findItineraryHelper(destination, map, visited);
            if (result != null) {
                return current + "->" + result;
            }
            visited.remove(destination);
        }
        
        return null;
    }
    
    // Find itinerary main function
    public static String findItinerary(List<List<String>> tickets) {
        HashMap<String, String> map = new HashMap<>();
        
        for (List<String> ticket : tickets) {
            map.put(ticket.get(0), ticket.get(1));
        }
        
        // Find starting point (not a destination in any ticket)
        String start = null;
        for (String from : map.keySet()) {
            boolean isDestination = false;
            for (String to : map.values()) {
                if (from.equals(to)) {
                    isDestination = true;
                    break;
                }
            }
            if (!isDestination) {
                start = from;
                break;
            }
        }
        
        if (start == null) {
            return "No valid itinerary found";
        }
        
        HashSet<String> visited = new HashSet<>();
        visited.add(start);
        return findItineraryHelper(start, map, visited);
    }
    
    // Sort by frequency
    public static String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
            (a, b) -> b.getValue().equals(a.getValue()) ?
                Character.compare(a.getKey(), b.getKey()) :
                b.getValue() - a.getValue()
        );
        
        pq.addAll(map.entrySet());
        
        StringBuilder result = new StringBuilder();
        
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            char ch = entry.getKey();
            int val = entry.getValue();
            
            while (val-- > 0) {
                result.append(ch);
            }
            result.append(ch);
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all four advanced hashing algorithms
         * 
         * Largest Subarray Sum 0: [1,-1,2,-3,1,1,-2,3,4]
         * Expected: Length 3 (subarray [1,-1,2,-3])
         * 
         * Subarray Sum K: [1,2,3,4], K=3
         * Expected: 2 subarrays ([1,2], [3])
         * 
         * Find Itinerary: [["JFK","SFO"],["SFO","LHR"],["LHR","DXB"]]
         * Expected: "JFK->SFO->LHR->DXB->JFK"
         * 
         * Sort by Frequency: "tree"
         * Expected: "eetr" (t:3, r:1, e:1)
         */
        
        // Test Largest Subarray with Sum 0
        int[] arr1 = {1, -1, 2, -3, 1, 1, -2, 3, 4};
        
        System.out.println("Advanced Hashing - Largest Subarray with Sum 0");
        System.out.println("Array: [1,-1,2,-3,1,1,-2,3,4]");
        System.out.println("Expected: Length 3");
        
        largestSubarraySumZero(arr1);
        
        // Test Subarray Sum Equal to K
        int[] arr2 = {1, 2, 3, 4};
        int k2 = 3;
        
        System.out.println("\nSubarray Sum Equal to K:");
        System.out.println("Array: [1,2,3,4], K = 3");
        System.out.println("Expected: 2 subarrays");
        
        int result2 = subarraySumEqualK(arr2, k2);
        System.out.println("Result: " + result2);
        
        // Test Find Itinerary
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK", "SFO"));
        tickets.add(Arrays.asList("SFO", "LHR"));
        tickets.add(Arrays.asList("LHR", "DXB"));
        
        System.out.println("\nFind Itinerary:");
        System.out.println("Tickets: [[JFK,SFO],[SFO,LHR],[LHR,DXB]]");
        System.out.println("Expected: JFK->SFO->LHR->DXB->JFK");
        
        String itinerary = findItinerary(tickets);
        System.out.println("Result: " + itinerary);
        
        // Test Sort by Frequency
        String s = "tree";
        
        System.out.println("\nSort by Frequency:");
        System.out.println("String: " + s);
        System.out.println("Expected: eetr");
        
        String sorted = frequencySort(s);
        System.out.println("Result: " + sorted);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Empty array
        int[] empty = {};
        System.out.println("Empty array:");
        largestSubarraySumZero(empty);
        
        // Test case 3: All positive numbers
        int[] allPositive = {1, 2, 3, 4, 5};
        System.out.println("All positive numbers:");
        System.out.println("Array: [1,2,3,4,5]");
        largestSubarraySumZero(allPositive);
        
        // Test case 4: All zeros
        int[] allZeros = {0, 0, 0, 0, 0};
        System.out.println("All zeros:");
        System.out.println("Array: [0,0,0,0,0]");
        largestSubarraySumZero(allZeros);
    }
}
