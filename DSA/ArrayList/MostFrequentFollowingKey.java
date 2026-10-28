import java.util.*;

/**
 * QUESTION: Most Frequent Number following Key - Given an integer Arraylist nums and an integer key 
 * which is present in nums. For every unique integer target in nums, count the number of times 
 * target immediately follows an occurrence of key in nums. Return the target with the maximum count.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [1, 100, 200, 1, 100], key = 1
 * - Check occurrences of key = 1:
 *   - Position 0: key (no previous element)
 *   - Position 1: 100 (follows key)
 *   - Position 3: 1 (follows key)
 * - Count for target = 100: appears 2 times after key
 * - Count for target = 200: appears 1 time after key
 * - Count for target = 1: appears 0 times after key (but it IS the key)
 * - Maximum count: 2 (for target = 100)
 * - Answer: 100
 * 
 * APPROACH USED: Single Pass with Frequency Map
 * 
 * PSEUDOCODE:
 * function findMostFrequent(nums, key):
 *   freqMap = HashMap<Integer, Integer>()  // Count frequencies
 *   
 *   // Count frequencies of all numbers
 *   for num in nums:
 *     freqMap.put(num, freqMap.getOrDefault(num, 0) + 1)
 *   
 *   maxCount = 0
 *   result = -1
 *   
 *   // Check each unique target
 *   for target in freqMap.keySet():
 *     if target == key: continue  // Skip the key itself
 *     
 *     // Count how many times target follows key
 *     count = 0
 *     for i = 0 to nums.size() - 1:
 *       if nums.get(i) == key && i + 1 < nums.size():
 *         if nums.get(i + 1) == target:
 *           count++
 *     
 *     if count > maxCount:
 *       maxCount = count
 *       result = target
 *   
 *   return result
 * 
 * EXPLANATION OF STEPS:
 * 1. Create frequency map to count occurrences of each number
 * 2. For each unique target (excluding the key itself):
 * 3. Count how many times target appears immediately after key
 * 4. Track the target with maximum such count
 * 5. Return the result
 * 
 * DRY RUN for array = [1, 100, 200, 1, 100], key = 1:
 * 
 * Frequency map: {1=2, 100=2, 200=1}
 * 
 * Check targets:
 * - target = 100 (skip, it's the key)
 * - target = 200: check positions 2 and 4
 *   - Position 2: nums[2] = 200, previous nums[1] = 100 (key) → count++
 *   - Position 4: nums[4] = 100, previous nums[3] = 1 → no count
 *   - Total count for 200: 1
 * 
 * - target = 1: skip (it's the key)
 * 
 * Maximum count: 2 (for target = 100)
 * Result: 100
 */
public class MostFrequentFollowingKey {
    
    public static int findMostFrequent(ArrayList<Integer> nums, int key) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        
        // Count frequencies of all numbers
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        int maxCount = 0;
        int result = -1;
        
        // Check each unique target
        for (int target : freqMap.keySet()) {
            if (target == key) {
                continue; // Skip the key itself
            }
            
            // Count how many times target follows key
            int count = 0;
            for (int i = 0; i < nums.size() - 1; i++) {
                if (nums.get(i) == key && i + 1 < nums.size()) {
                    if (nums.get(i + 1) == target) {
                        count++;
                    }
                }
            }
            
            if (count > maxCount) {
                maxCount = count;
                result = target;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [1, 100, 200, 1, 100], key = 1
         * Expected result: 100
         * 
         * Step-by-step process:
         * 
         * Frequency map: {1=2, 100=2, 200=1}
         * 
         * Check targets:
         * - target = 100: count occurrences after key
         *   Position 1: 100 follows 1 (position 0) → count++
         *   Position 3: 100 follows 1 (position 2) → count++
         *   Total count: 2
         * 
         * - target = 200: count occurrences after key
         *   Position 2: 200 follows 100 (position 1 is key) → count++
         *   Position 4: 100 follows 1 (position 3) → no count
         *   Total count: 1
         * 
         * - target = 1: skip (it's the key)
         * 
         * Maximum count: 2 (for target = 100)
         * Result: 100
         */
        
        // Test case 1: Example from problem statement
        ArrayList<Integer> test1 = new ArrayList<>(Arrays.asList(1, 100, 200, 1, 100));
        System.out.println("Test 1: " + test1);
        System.out.println("Key: 1");
        System.out.println("Most frequent target following key: " + findMostFrequent(test1, 1));
        System.out.println("Expected: 100");
        
        // Show step-by-step process for test 1
        System.out.println("\nStep-by-step process:");
        System.out.println("Array: " + test1);
        
        // Build frequency map
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : test1) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        System.out.println("Frequency map: " + freqMap);
        
        // Check each target
        for (int target : freqMap.keySet()) {
            if (target == 1) {
                System.out.println("Skipping target " + target + " (it's the key)");
                continue;
            }
            
            int count = 0;
            System.out.print("Target " + target + ": follows key at positions ");
            for (int i = 0; i < test1.size() - 1; i++) {
                if (test1.get(i) == 1 && i + 1 < test1.size()) {
                    if (test1.get(i + 1) == target) {
                        count++;
                        System.out.print(i + " ");
                    }
                }
            }
            System.out.println("(count: " + count + ")");
            
            if (count > 0) {
                System.out.println("  → Target " + target + " appears " + count + " times after key");
            }
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        ArrayList<Integer> test2 = new ArrayList<>(Arrays.asList(2, 2, 2, 3, 3));
        System.out.println("Test 2: " + test2 + ", key=2 → " + findMostFrequent(test2, 2));
        
        ArrayList<Integer> test3 = new ArrayList<>(Arrays.asList(5, 5, 5, 1, 1, 1));
        System.out.println("Test 3: " + test3 + ", key=5 → " + findMostFrequent(test3, 5));
        
        ArrayList<Integer> test4 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Test 4: " + test4 + ", key=3 → " + findMostFrequent(test4, 3));
    }
}
