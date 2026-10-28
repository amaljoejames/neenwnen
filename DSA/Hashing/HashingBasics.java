import java.util.*;

/**
 * QUESTION: Hashing Basics - Implement fundamental hashing operations including
 * majority element, valid anagram, distinct elements count, union/intersection.
 * 
 * QUESTION EXPLANATION:
 * Hashing: Technique to map keys to values for O(1) average time complexity.
 * Used for frequency counting, caching, and fast lookups.
 * 
 * Algorithms covered:
 * 1. Majority Element: Find element appearing > n/2 times
 * 2. Valid Anagram: Check if two strings are anagrams
 * 3. Count Distinct Elements: Count unique elements in array
 * 4. Union and Intersection: Find common and unique elements between arrays
 * 
 * APPROACH USED: HashMap for Frequency Counting and Lookups
 * 
 * PSEUDOCODE:
 * 
 * Majority Element:
 * function majorityElement(arr, n):
 *   map = HashMap<Integer, Integer>()
 *   for each element in arr:
 *     map[element] = map.getOrDefault(element, 0) + 1
 *   
 *   for each key, value in map:
 *     if value > n/2: return key
 *   return -1
 * 
 * Valid Anagram:
 * function isAnagram(s, t):
 *   if s.length != t.length: return false
 *   map = HashMap<Character, Integer>()
 *   count characters in s
 *   count characters in t
 *   compare maps
 *   return maps equal
 * 
 * Count Distinct Elements:
 * function countDistinct(arr):
 *   set = HashSet<Integer>()
 *   for each element in arr:
 *     set.add(element)
 *   return set.size()
 * 
 * Union and Intersection:
 * function unionIntersection(arr1, arr2):
 *   set1 = HashSet<Integer>()
 *   set2 = HashSet<Integer>()
 *   for each element in arr1: set1.add(element)
 *   for each element in arr2: set2.add(element)
 *   
 *   union = set1 ∪ set2
 *   intersection = set1 ∩ set2
 *   return sizes
 * 
 * EXPLANATION OF STEPS:
 * 1. Use HashMap for O(1) frequency counting
 * 2. Use HashSet for O(1) distinct counting
 * 3. Set operations for union/intersection
 * 4. Time complexity: O(n) for most operations
 * 
 * DRY RUN for Majority Element:
 * 
 * Array: [1, 2, 2, 2, 3, 3, 3, 3]
 * n = 7, n/2 = 3.5
 * 
 * Frequency map: {1:1, 2:3, 3:3}
 * Elements with count > 3.5: Only 3 (appears 3 times)
 * No majority element exists
 * 
 * DRY RUN for Valid Anagram:
 * 
 * s = "listen", t = "silent"
 * Character counts should be equal
 * 
 * DRY RUN for Union and Intersection:
 * 
 * arr1 = [1, 2, 3], arr2 = [3, 4, 5]
 * union = [1, 2, 3, 4, 5]
 * intersection = [3]
 * 
 * Final answer: union size = 5, intersection size = 1
 */
public class HashingBasics {
    
    // Majority element in array
    public static void majorityElement(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            // if (map.containsKey(arr[i])) {
            //     map.put(arr[i], map.get(arr[i]) + 1);
            // } else {
            //     map.put(arr[i], 1);
            // }
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        
        for (Integer key : map.keySet()) {
            if (map.get(key) > arr.length / k) {
                System.out.println(key);
            }
        }
    }

    // Valid anagram
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        HashMap<Character, Integer> map = new HashMap<>();
        
        // Count characters in s
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        // Count characters in t
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (map.containsKey(ch)) {
                if (map.get(ch) == 1) {
                    map.remove(ch);
                } else {
                    map.put(ch, map.get(ch) - 1);
                }
            } else {
                map.put(ch, 1);
            }
        }
        
        return map.isEmpty();
    }

    // Count distinct elements
    public static int countDistinct(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        
        return set.size();
    }

    // Union and intersection of two arrays
    public static void unionIntersection(int[] arr1, int[] arr2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        
        for (int i = 0; i < arr1.length; i++) {
            set1.add(arr1[i]);
        }
        
        for (int i = 0; i < arr2.length; i++) {
            set2.add(arr2[i]);
        }
        
        // Union
        HashSet<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union size: " + union.size());
        
        // Intersection
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (set2.contains(arr1[i])) {
                count++;
            }
        }
        System.out.println("Intersection size: " + count);
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all four hashing algorithms
         * 
         * Majority Element: [1,2,2,2,3,3,3,3], n=7
         * Expected: No majority element (appears 3 times, need >3.5)
         * 
         * Valid Anagram: "listen", "silent"
         * Expected: true (same character counts)
         * 
         * Count Distinct: [1,2,3,2,4,5]
         * Expected: 4 distinct elements
         * 
         * Union/Intersection: [1,2,3], [3,4,5]
         * Expected: Union size 5, Intersection size 1
         */
        
        // Test Majority Element
        int[] arr = {1, 2, 2, 3, 3, 3, 3};
        int k = 7;
        
        System.out.println("Hashing Basics - Majority Element");
        System.out.println("Array: [1,2,2,3,3,3,3,3]");
        System.out.println("n = 7, threshold = " + (k / 2));
        System.out.println("Expected: No majority element (max count = 3)");
        
        majorityElement(arr, k);
        
        // Test Valid Anagram
        String s = "listen";
        String t = "silent";
        
        System.out.println("\nValid Anagram:");
        System.out.println("s = " + s + ", t = " + t);
        System.out.println("Expected: true (both have same character counts)");
        
        boolean result = isAnagram(s, t);
        System.out.println("Result: " + result);
        
        // Test Count Distinct Elements
        int[] arr2 = {1, 2, 3, 2, 4, 5};
        
        System.out.println("\nCount Distinct Elements:");
        System.out.println("Array: [1,2,3,2,4,5]");
        System.out.println("Expected: 4 distinct elements");
        
        int distinct = countDistinct(arr2);
        System.out.println("Result: " + distinct);
        
        // Test Union and Intersection
        int[] arr3 = {1, 2, 3};
        int[] arr4 = {3, 4, 5};
        
        System.out.println("\nUnion and Intersection:");
        System.out.println("Array1: [1,2,3], Array2: [3,4,5]");
        System.out.println("Expected: Union size 5, Intersection size 1");
        
        unionIntersection(arr3, arr4);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Single element array
        int[] single = {5};
        System.out.println("Single element array:");
        System.out.println("Array: [5]");
        System.out.println("Distinct elements: " + countDistinct(single));
        
        // Test case 3: Empty arrays
        int[] empty1 = {};
        int[] empty2 = {};
        System.out.println("Empty arrays:");
        System.out.print("Union size: ");
        unionIntersection(empty1, empty2);
        
        // Test case 4: Arrays with duplicates
        int[] dup1 = {1, 1, 2, 2};
        int[] dup2 = {2, 2, 3, 3};
        System.out.println("Arrays with duplicates:");
        System.out.print("Union size: ");
        unionIntersection(dup1, dup2);
        System.out.println("Expected: Union size 3 (1,2,3)");
    }
}
