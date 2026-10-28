import java.util.*;

/**
 * QUESTION: Hashing Assignment Problems - Implement Two Sum and Frequency Sort algorithms
 * from the assignment section.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Two Sum:
 * Given array of integers and target, return indices of two numbers that add up to target.
 * Each input has exactly one solution, cannot use same element twice.
 * 
 * 2. Sort by Frequency:
 * Given string s, sort it in decreasing order based on frequency of characters.
 * If multiple answers exist, return any of them.
 * Same characters must be together.
 * 
 * APPROACH USED: HashMap for Frequency Counting and Two-pointer for Two Sum
 * 
 * PSEUDOCODE:
 * 
 * Two Sum:
 * function twoSum(arr, target):
 *   map = HashMap<Integer, Integer>()
 *   for i = 0 to n-1:
 *     diff = target - arr[i]
 *     if map.containsKey(diff):
 *       return [map.get(diff), i]
 *     map.put(arr[i], i)
 *   return [-1, -1]
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
 *       b.getValue() - a.getValue()
 *   )
 *   
 *   pq.addAll(map.entrySet())
 *   result = StringBuilder()
 *   
 *   while (!pq.isEmpty()):
 *     entry = pq.poll()
 *     ch = entry.getKey()
 *     val = entry.getValue()
 *     while (val-- > 0):
 *       result.append(ch)
 *   
 *   return result.toString()
 * 
 * EXPLANATION OF STEPS:
 * 1. Two Sum: Use hashmap to store complement values
 * 2. Frequency Sort: Count frequencies, use max heap for sorting
 * 3. Time complexity: O(n) for Two Sum, O(n log n) for Frequency Sort
 * 
 * DRY RUN for Two Sum:
 * 
 * Array: [2, 7, 11, 15], target = 9
 * 
 * Process:
 * i = 0, arr[0] = 2, diff = 9 - 2 = 7
 *   map = {}, diff not found
 *   map.put(2, 0)
 * 
 * i = 1, arr[1] = 7, diff = 9 - 7 = 2
 *   map.containsKey(2) = true
 *   return [map.get(2), 1] = [0, 1]
 * 
 * DRY RUN for Frequency Sort:
 * 
 * String: "tree"
 * Character counts: t:2, r:1, e:1
 * 
 * Priority queue order: (t,2), (r,1), (e,1)
 * Result: "eetr" or "eert"
 * 
 * Final answer: "eetr"
 */
public class HashingAssignment {
    
    // Two Sum problem
    public int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> visited = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            int diff = target - arr[i]; // Compute difference
            
            // Check if difference is already in map
            if (visited.containsKey(diff)) {
                return new int[]{visited.get(diff), i};
            }
            
            // Add current element to map
            visited.put(arr[i], i);
        }
        
        return new int[]{-1, -1}; // No valid pair found
    }

    // Sort by frequency problem
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        
        // Count frequency of each character
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Max heap (Priority Queue) sorted by frequency
        PriorityQueue<Map.Entry<Character, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue().equals(a.getValue()) ?
                        Character.compare(a.getKey(), b.getKey()) :
                        b.getValue() - a.getValue());

        pq.addAll(map.entrySet());

        StringBuilder res = new StringBuilder();

        // Build sorted string
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            char ch = entry.getKey();
            int val = entry.getValue();
            
            while (val-- > 0) {
                res.append(ch);
            }
        }

        return res.toString();
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test both assignment problems
         * 
         * Two Sum: [2,7,11,15], target = 9
         * Expected: [0,1] (2 + 7 = 9)
         * 
         * Frequency Sort: "tree"
         * Expected: "eetr" (t:2, r:1, e:1)
         */
        
        HashingAssignment ha = new HashingAssignment();
        
        // Test Two Sum
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        
        System.out.println("Hashing Assignment - Two Sum");
        System.out.println("Array: [2,7,11,15], target = 9");
        System.out.println("Expected: [0,1] (2 + 7 = 9)");
        
        int[] result = ha.twoSum(arr, target);
        System.out.println("Result: [" + result[0] + ", " + result[1] + "]");
        
        // Show step-by-step for Two Sum
        System.out.println("\nStep-by-step Two Sum:");
        System.out.println("1. i=0, arr[0]=2, diff=9-2=7, map={2:0}");
        System.out.println("2. i=1, arr[1]=7, diff=9-7=2, map contains 2");
        System.out.println("3. Return [map.get(2), 1] = [0, 1]");
        
        // Test Frequency Sort
        String s = "tree";
        
        System.out.println("\nSort by Frequency:");
        System.out.println("String: " + s);
        System.out.println("Expected: eetr (t:2, r:1, e:1)");
        
        String sorted = ha.frequencySort(s);
        System.out.println("Result: " + sorted);
        
        // Show step-by-step for Frequency Sort
        System.out.println("\nStep-by-step Frequency Sort:");
        System.out.println("1. Count characters: t:2, r:1, e:1");
        System.out.println("2. Create max heap: (t,2), (r,1), (e,1)");
        System.out.println("3. Extract from heap: t(2), r(1), e(1)");
        System.out.println("4. Build result: eetr");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Two Sum with negative numbers
        int[] arr2 = {-1, -2, -3, -4, -5};
        int target2 = -8;
        System.out.println("Two Sum with negative numbers:");
        System.out.println("Array: [-1,-2,-3,-4,-5], target = -8");
        int[] result2 = ha.twoSum(arr2, target2);
        System.out.println("Result: [" + result2[0] + ", " + result2[1] + "]");
        System.out.println("Expected: [2,4] (-3 + -5 = -8)");
        
        // Test case 3: Frequency Sort with repeated characters
        String s2 = "aaabbbccc";
        System.out.println("Frequency Sort with repeated characters:");
        System.out.println("String: " + s2);
        String sorted2 = ha.frequencySort(s2);
        System.out.println("Result: " + sorted2);
        System.out.println("Expected: aaabbbccc (already sorted)");
        
        // Test case 4: Two Sum no solution
        int[] arr3 = {1, 2, 3, 4};
        int target3 = 10;
        System.out.println("Two Sum no solution:");
        System.out.println("Array: [1,2,3,4], target = 10");
        int[] result3 = ha.twoSum(arr3, target3);
        System.out.println("Result: [" + result3[0] + ", " + result3[1] + "]");
        System.out.println("Expected: [-1, -1] (no solution)");
    }
}
