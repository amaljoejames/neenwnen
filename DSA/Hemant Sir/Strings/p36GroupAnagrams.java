/**
 * Question: Group all anagrams from an array of strings.
 * 
 * Topics Included:
 * - Hash map usage
 * - Anagram detection
 * - String sorting
 * - Grouping
 * 
 * Algorithm:
 * 1. Create a hash map with sorted string as key and list of anagrams as value
 * 2. For each string in the array:
 *    - Sort the string to get its canonical form
 *    - Use sorted string as key in the map
 *    - Add original string to the list for that key
 * 3. Return all lists from the map values
 * 
 * Pseudocode:
 * FUNCTION groupAnagrams(strs):
 *     anagramMap = new HashMap()
 *     
 *     FOR each str in strs:
 *         sortedStr = sort(str)
 *         
 *         IF anagramMap.containsKey(sortedStr):
 *             anagramMap.get(sortedStr).add(str)
 *         ELSE:
 *             list = new ArrayList()
 *             list.add(str)
 *             anagramMap.put(sortedStr, list)
 *     
 *     RETURN new ArrayList(anagramMap.values())
 * 
 * Explanation with Example:
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * Dry run:
 * - "eat": sorted = "aet" → map = {"aet": ["eat"]}
 * - "tea": sorted = "aet" → map = {"aet": ["eat", "tea"]}
 * - "tan": sorted = "ant" → map = {"aet": ["eat", "tea"], "ant": ["tan"]}
 * - "ate": sorted = "aet" → map = {"aet": ["eat", "tea", "ate"], "ant": ["tan"]}
 * - "nat": sorted = "ant" → map = {"aet": ["eat", "tea", "ate"], "ant": ["tan", "nat"]}
 * - "bat": sorted = "abt" → map = {"aet": ["eat", "tea", "ate"], "ant": ["tan", "nat"], "abt": ["bat"]}
 * Final result: [["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]
 * 
 * Time Complexity: O(n * k log k) where n is number of strings, k is max string length
 * Space Complexity: O(n * k)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class p36GroupAnagrams {
    
    /**
     * Method to group all anagrams from an array of strings
     * @param strs The array of strings
     * @return List of lists, each containing anagrams
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramMap = new HashMap<>();
        
        for (String str : strs) {
            // Sort the string to get canonical form
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);
            
            // Add to appropriate group
            if (anagramMap.containsKey(sortedStr)) {
                anagramMap.get(sortedStr).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                anagramMap.put(sortedStr, list);
            }
        }
        
        return new ArrayList<>(anagramMap.values());
    }
    
    public static void main(String[] args) {
        // Question: Group all anagrams from an array of strings
        // Topics: Hash map usage, anagram detection, string sorting, grouping
        // Algorithm: Sort each string, use as key, group anagrams together
        // Pseudocode: for each string → sort → use as map key → group → return groups
        // Example: ["eat","tea","tan","ate","nat","bat"] → [["eat","tea","ate"],["tan","nat"],["bat"]]
        // Dry run: eat→aet, tea→aet, tan→ant, ate→aet, nat→ant, bat→abt → groups
        // Time: O(n*k log k), Space: O(n*k)
        
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> groups = groupAnagrams(strs);
        
        System.out.println("Anagram groups:");
        for (List<String> group : groups) {
            System.out.println(group);
        }
    }
}
