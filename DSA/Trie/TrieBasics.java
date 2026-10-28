/**
 * QUESTION: Trie Data Structure - Implement fundamental Trie operations including
 * insertion, search, word break, prefix problems, and unique substring counting.
 * 
 * QUESTION EXPLANATION:
 * 
 * Trie (Prefix Tree): Tree-like data structure for efficient string operations.
 * Each node has 26 children (one for each alphabet letter) and end-of-word marker.
 * Used for dictionary operations, autocomplete, and pattern matching.
 * 
 * 1. Insert Operation:
 * Insert word into trie character by character.
 * Increment frequency for existing nodes, mark end-of-word.
 * 
 * 2. Search Operation:
 * Search for exact word match in trie.
 * Traverse character by character, check end-of-word marker.
 * 
 * 3. Word Break Problem:
 * Check if string can be segmented into dictionary words.
 * Use recursion to try all possible partitions.
 * 
 * 4. Prefix Problem:
 * Print all unique prefixes with frequency 1.
 * DFS traversal with frequency checking.
 * 
 * 5. Starts With Problem:
 * Check if any word starts with given prefix.
 * Traverse prefix characters, return true if all found.
 * 
 * 6. Unique Substrings:
 * Count all unique substrings in trie.
 * Count nodes representing end-of-words.
 * 
 * 7. Longest Word with All Prefixes:
 * Find longest word where all prefixes are valid words.
 * DFS traversal checking end-of-word markers.
 * 
 * APPROACH USED: Trie Node with 26 Children Array
 * 
 * PSEUDOCODE:
 * 
 * Trie Node:
 * class Node:
 *   children = Node[26]
 *   eow = false
 *   freq = 1
 *   word = null
 * 
 * Insert:
 * function insert(word):
 *   curr = root
 *   for each character in word:
 *     idx = char - 'a'
 *     if curr.children[idx] == null:
 *       curr.children[idx] = new Node()
 *     else:
 *       curr.children[idx].freq++
 *     curr = curr.children[idx]
 *   curr.eow = true
 * 
 * Search:
 * function search(key):
 *   curr = root
 *   for each character in key:
 *     idx = char - 'a'
 *     if curr.children[idx] == null:
 *       return false
 *     curr = curr.children[idx]
 *   return curr.eow
 * 
 * Word Break:
 * function wordBreak(key):
 *   if key.length == 0: return true
 *   
 *   for i = 1 to key.length:
 *     if search(key.substring(0, i)) and wordBreak(key.substring(i)):
 *       return true
 *   
 *   return false
 * 
 * Prefix:
 * function prefix(root, ans):
 *   if root == null: return
 *   if root.freq == 1:
 *     print ans
 *     return
 *   
 *   for i = 0 to 25:
 *     if root.children[i] != null:
 *       prefix(root.children[i], ans + (char)(i + 'a'))
 * 
 * Starts With:
 * function startsWith(key):
 *   curr = root
 *   for each character in key:
 *     idx = char - 'a'
 *     if curr.children[idx] == null:
 *       return false
 *     curr = curr.children[idx]
 *   return true
 * 
 * Unique Substrings:
 * function countUniqueSubstrings(root):
 *   if root == null: return 0
 *   count = 0
 *   
 *   for i = 0 to 25:
 *     if root.children[i] != null:
 *       count += countUniqueSubstrings(root.children[i])
 *   
 *   return count + 1
 * 
 * Longest Word with All Prefixes:
 * function longestWordWithAllPrefixes(root, temp):
 *   if root == null: return
 *   
 *   for i = 0 to 25:
 *     if root.children[i] != null and root.children[i].eow:
 *       temp.append((char)(i + 'a'))
 *       if temp.length() > ans.length():
 *         ans = temp.toString()
 *       longestWordWithAllPrefixes(root.children[i], temp)
 *       temp.deleteCharAt(temp.length() - 1)
 * 
 * EXPLANATION OF STEPS:
 * 1. Trie Structure: Each node represents a character in the path
 * 2. Insertion: Create nodes as needed, mark end-of-word
 * 3. Search: Follow character path to verify word existence
 * 4. Word Break: Recursive partitioning with dictionary lookup
 * 5. Prefix: DFS with frequency tracking for uniqueness
 * 6. Unique Substrings: Count nodes representing complete words
 * 7. Longest Word: Track longest word with all valid prefixes
 * 
 * DRY RUN for Insert Operation:
 * 
 * Insert "apple":
 * 
 * Process:
 * 'a': children[0] = new Node(), freq=1, move to children[0]
 * 'p': children[15] = new Node(), freq=1, move to children[15]
 * 'p': children[15].freq = 2, move to children[15]
 * 'l': children[11] = new Node(), freq=1, move to children[11]
 * 'e': children[4] = new Node(), freq=1, move to children[4]
 * 
 * Mark children[4].eow = true
 * 
 * DRY RUN for Word Break:
 * 
 * String: "applepenapple"
 * 
 * Process:
 * wordBreak("applepenapple"):
 *   i=1: search("a") = true, wordBreak("pplepenapple")
 *     i=1: search("p") = false
 *   i=2: search("ap") = false
 *   i=3: search("app") = true, wordBreak("lepenapple")
 *     i=1: search("l") = false
 *   i=4: search("appl") = true, wordBreak("epenapple")
 *     i=1: search("e") = false
 *   i=5: search("apple") = true, wordBreak("penapple")
 *     i=1: search("p") = false
 *   ... continue
 * 
 * Returns true (apple + pen + apple)
 * 
 * DRY RUN for Prefix Problem:
 * 
 * Words: ["apple", "app", "apply"]
 * 
 * Process:
 * Insert all words, then call prefix(root, "")
 * 
 * DFS traversal:
 * root -> 'a' (freq=3) -> print "a"
 * root -> 'a' -> 'p' (freq=3) -> print "ap"
 * root -> 'a' -> 'p' -> 'p' (freq=1) -> print "app"
 * root -> 'a' -> 'p' -> 'l' (freq=1) -> print "appl"
 * root -> 'a' -> 'p' -> 'l' -> 'e' (freq=1) -> print "apple"
 * 
 * Output: "a ap app appl apple"
 */
public class TrieBasics {
    
    static class Node {
        Node[] children = new Node[26];
        boolean eow = false;
        int freq;
        String word;
        
        public Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            freq = 1;
        }
    }
    
    public static Node root = new Node();

    // Insert word into trie
    public static void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            } else {
                curr.children[idx].freq++;
            }
            curr = curr.children[idx];
        }
        curr.eow = true;
    }

    // Search word in trie
    public static boolean search(String key) {
        Node curr = root;
        for (int level = 0; level < key.length(); level++) {
            int idx = key.charAt(level) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }
        return curr.eow == true;
    }

    // Word break problem (Google, Microsoft, +3)
    public static boolean wordBreak(String key) {
        if (key.length() == 0) {
            return true;
        }
        
        for (int i = 1; i <= key.length(); i++) {
            if (search(key.substring(0, i)) && wordBreak(key.substring(i))) {
                return true;
            }
        }
        return false;
    }

    // Prefix problem (Hard - Google, Microsoft, Amazon, Flipkart, etc. +4)
    public static void prefix(Node root, String ans) {
        if (root == null) {
            return;
        }
        if (root.freq == 1) {
            System.out.print(ans + " ");
        }
        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null) {
                prefix(root.children[i], ans + (char) (i + 'a'));
            }
        }
    }

    // Starts with problem
    public static boolean startsWith(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            int idx = key.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }
        return true;
    }

    // Count unique substrings (Important - Google, Microsoft, Amazon +3)
    public static int countUniqueSubstrings(Node root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                count += countUniqueSubstrings(root.children[i]);
            }
        }
        return count + 1;
    }

    // Longest word with all prefixes
    public static String ans = "";
    
    public static void longestWordWithAllPrefixes(Node root, StringBuilder temp) {
        if (root == null) {
            return;
        }
        
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].eow == true) {
                temp.append((char) (i + 'a'));
                if (temp.length() > ans.length()) {
                    ans = temp.toString();
                }
                longestWordWithAllPrefixes(root.children[i], temp);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all trie operations
         * 
         * Insert/Search: ["the","a","there","their","any","the"]
         * Expected: search("the") = true, search("true") = false
         * 
         * Word Break: "applepenapple"
         * Expected: true (apple + pen + apple)
         * 
         * Prefix: ["apple","app","apply"]
         * Expected: a ap app appl apple
         * 
         * Starts With: "ap", "moon"
         * Expected: true, false
         * 
         * Unique Substrings: "apple"
         * Expected: 10 (a, ap, app, appl, apple, p, pp, ppl, ple, le)
         * 
         * Longest Word with Prefixes: ["a","banana","app","appl","ap","apply","apple"]
         * Expected: apply
         */
        
        // Test Insert and Search
        System.out.println("Trie Basics - Insert and Search");
        String[] words1 = {"the", "a", "there", "their", "any", "the"};
        for (int i = 0; i < words1.length; i++) {
            insert(words1[i]);
        }
        
        System.out.println("Words inserted: [the, a, there, their, any, the]");
        System.out.println("Search 'the': " + search("the"));
        System.out.println("Search 'true': " + search("true"));
        
        // Test Word Break
        System.out.println("\nWord Break Problem:");
        String wordBreakStr = "applepenapple";
        System.out.println("String: " + wordBreakStr);
        System.out.println("Expected: true (apple + pen + apple)");
        boolean canBreak = wordBreak(wordBreakStr);
        System.out.println("Result: " + canBreak);
        
        // Test Prefix Problem
        System.out.println("\nPrefix Problem:");
        String[] prefixWords = {"apple", "app", "apply"};
        for (int i = 0; i < prefixWords.length; i++) {
            insert(prefixWords[i]);
        }
        System.out.println("Words: [apple, app, apply]");
        System.out.println("Expected prefixes: a ap app appl apple");
        System.out.print("Actual prefixes: ");
        prefix(root, "");
        System.out.println();
        
        // Test Starts With
        System.out.println("\nStarts With Problem:");
        System.out.println("Prefix 'ap': " + startsWith("ap"));
        System.out.println("Prefix 'moon': " + startsWith("moon"));
        
        // Test Unique Substrings
        System.out.println("\nUnique Substrings:");
        String uniqueStr = "apple";
        for (int i = 0; i < uniqueStr.length(); i++) {
            insert(uniqueStr.substring(i));
        }
        System.out.println("String: " + uniqueStr);
        System.out.println("Expected: 10 unique substrings");
        int uniqueCount = countUniqueSubstrings(root);
        System.out.println("Result: " + uniqueCount);
        
        // Test Longest Word with All Prefixes
        System.out.println("\nLongest Word with All Prefixes:");
        String[] longestWords = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        for (int i = 0; i < longestWords.length; i++) {
            insert(longestWords[i]);
        }
        System.out.println("Words: [a, banana, app, appl, ap, apply, apple]");
        System.out.println("Expected: apply");
        longestWordWithAllPrefixes(root, new StringBuilder(""));
        System.out.println("Result: " + ans);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Empty string
        System.out.println("Empty string operations:");
        System.out.println("Search empty string: " + search(""));
        System.out.println("Word break empty string: " + wordBreak(""));
        
        // Test case 3: Single character
        System.out.println("\nSingle character operations:");
        insert("z");
        System.out.println("Search 'z': " + search("z"));
        System.out.println("Starts with 'z': " + startsWith("z"));
        
        // Test case 4: No prefixes
        System.out.println("\nNo prefixes:");
        System.out.println("Starts with 'xyz': " + startsWith("xyz"));
        
        // Test case 5: Complex word
        System.out.println("\nComplex word:");
        String complexWord = "character";
        insert(complexWord);
        System.out.println("Search 'character': " + search("character"));
        System.out.println("Prefix count: " + countUniqueSubstrings(root));
    }
}
