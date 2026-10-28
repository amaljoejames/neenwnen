import java.util.*;

/**
 * QUESTION: Trie Assignment Problems - Implement advanced Trie applications including
 * group anagrams and longest word in dictionary problems.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Group Anagrams Together:
 * Group strings that are anagrams of each other.
 * Sort each string's characters, then use Trie to group.
 * 
 * 2. Longest Word in Dictionary:
 * Find longest word that can be built character by character.
 * All prefixes of the word must exist in the dictionary.
 * Return lexicographically smallest if multiple answers.
 * 
 * APPROACH USED: Trie Data Structure with Custom Node Classes
 * 
 * PSEUDOCODE:
 * 
 * Group Anagrams:
 * class Solution:
 *   root = TrieNode()
 *   ans = List<List<String>>()
 *   
 *   for word in strs:
 *     build(word)
 *   
 *   dfs(root)
 *   
 *   function build(word):
 *     temp = root
 *     chars = word.toCharArray()
 *     sort(chars)
 *     
 *     for char in chars:
 *       idx = char - 'a'
 *       if temp.children[idx] == null:
 *         temp.children[idx] = new TrieNode()
 *       temp = temp.children[idx]
 *     
 *     temp.isEnd = true
 *     temp.data.add(word)
 *   
 *   function dfs(rt):
 *     if rt.isEnd:
 *       ans.add(rt.data)
 *     
 *     for child in rt.children:
 *       if child != null:
 *         dfs(child)
 *   
 * Longest Word:
 * function longestWord(words):
 *   for word in words:
 *     insert(word)
 *   
 *   ans = ""
 *   dfs(root)
 *   return ans
 * 
 * function dfs(node):
 *   if node == null: return
 *   
 *   if node.word != null:
 *     if node.word.length() > ans.length():
 *       ans = node.word
 *     else if node.word.length() == ans.length() && node.word.compareTo(ans) < 0:
 *       ans = node.word
 *   
 *   for child in node.children:
 *     if child != null and child.word != null:
 *       dfs(child)
 * 
 * EXPLANATION OF STEPS:
 * 1. Group Anagrams: Sort characters, build Trie, DFS to collect groups
 * 2. Longest Word: Insert all words, DFS to find longest with valid prefixes
 * 3. Trie Node: Enhanced with data field for word storage
 * 4. DFS Traversal: Systematic exploration of Trie structure
 * 5. Lexicographic Comparison: Use built-in compareTo for tie-breaking
 * 
 * DRY RUN for Group Anagrams:
 * 
 * strs = ["eat","tea","tan","ate","nat","bat"]
 * 
 * Process:
 * Sort each word:
 * "eat" -> "aet"
 * "tea" -> "aet"
 * "tan" -> "ant"
 * "ate" -> "aet"
 * "nat" -> "ant"
 * "bat" -> "abt"
 * 
 * Build Trie:
 * "aet": a->e->t (word="eat")
 * "ant": a->n->t (word="tan")
 * "abt": a->b->t (word="bat")
 * 
 * DFS Collection:
 * Group 1: ["eat", "tea", "ate"] (all map to "aet")
 * Group 2: ["tan", "nat"] (all map to "ant")
 * Group 3: ["bat"] (maps to "abt")
 * 
 * Result: [["eat","tea","ate"],["tan","nat"],["bat"]]
 * 
 * DRY RUN for Longest Word:
 * 
 * words = ["w","wo","wor","worl","world"]
 * 
 * Process:
 * Insert all words into Trie
 * 
 * DFS traversal:
 * "w" -> freq=1, eow=true, word="w"
 * "wo" -> freq=1, eow=true, word="wo"
 * "wor" -> freq=1, eow=true, word="wor"
 * "worl" -> freq=1, eow=true, word="worl"
 * "world" -> freq=1, eow=true, word="world"
 * 
 * All words have valid prefixes, longest is "world"
 * 
 * DRY RUN for Longest Word with Prefixes:
 * 
 * words = ["a","banana","app","appl","ap","apply","apple"]
 * 
 * Process:
 * Insert all words
 * 
 * DFS traversal with prefix checking:
 * "a" -> eow=true, ans="a"
 * "a" -> "b" -> eow=true, ans="ab"
 * "a" -> "n" -> eow=true, ans="ab"
 * "a" -> "a" -> eow=true, ans="aba"
 * "a" -> "p" -> eow=true, ans="abap"
 * "a" -> "p" -> eow=true, ans="abapp"
 * "a" -> "l" -> eow=true, ans="appl"
 * "a" -> "y" -> eow=true, ans="apply"
 * "a" -> "p" -> eow=true, ans="apply" (longest)
 * 
 * Result: "apply"
 */
public class TrieAssignment {
    
    // Trie Node for Group Anagrams
    static class TrieNode {
        List<String> data;
        TrieNode[] children;
        boolean isEnd;
        
        TrieNode() {
            data = new ArrayList<>();
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    // Group Anagrams Together (Medium)
    static class Solution {
        static TrieNode root;
        List<List<String>> ans;

        public List<List<String>> groupAnagrams(String[] strs) {
            ans = new ArrayList<>();
            root = new TrieNode();
            
            for (String word : strs) {
                build(word);
            }
            
            dfs(root);
            return ans;
        }

        private void build(String s) {
            TrieNode temp = root;
            char[] word = s.toCharArray();
            Arrays.sort(word);
            
            for (char c : word) {
                int index = c - 'a';
                if (temp.children[index] == null) {
                    temp.children[index] = new TrieNode();
                }
                temp = temp.children[index];
            }
            
            temp.isEnd = true;
            temp.data.add(s);
        }

        private void dfs(TrieNode rt) {
            if (rt.isEnd) {
                ans.add(rt.data);
            }
            
            for (int i = 0; i < 26; i++) {
                if (rt.children[i] != null) {
                    dfs(rt.children[i]);
                }
            }
        }
    }

    // Longest Word in Dictionary (Medium)
    static class LongestWordSolution {
        static Node root;
        static String ans = "";

        public String longestWord(String[] words) {
            for (String word : words) {
                insert(word);
            }
            dfs(root);
            return ans;
        }

        // Enhanced Node for longest word problem
        static class Node {
            Node[] children = new Node[26];
            String word;
            
            Node() {
                for (int i = 0; i < 26; i++) {
                    children[i] = null;
                }
            }
        }

        private void insert(String word) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new Node();
                }
                curr = curr.children[idx];
            }
            curr.word = word;
        }

        private void dfs(Node node) {
            if (node == null) {
                return;
            }
            
            if (node.word != null) {
                if (node.word.length() > ans.length()) {
                    ans = node.word;
                } else if (node.word.length() == ans.length() && node.word.compareTo(ans) < 0) {
                    ans = node.word;
                }
            }
            
            for (Node child : node.children) {
                if (child != null) {
                    dfs(child);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test both assignment problems
         * 
         * Group Anagrams: ["eat","tea","tan","ate","nat","bat"]
         * Expected: [["eat","tea","ate"],["tan","nat"],["bat"]]
         * 
         * Longest Word: ["w","wo","wor","worl","world"]
         * Expected: "world"
         * 
         * Longest Word with Prefixes: ["a","banana","app","appl","ap","apply","apple"]
         * Expected: "apply"
         */
        
        // Test Group Anagrams
        System.out.println("Trie Assignment - Group Anagrams");
        String[] anagrams = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Input: [eat, tea, tan, ate, nat, bat]");
        System.out.println("Expected: [[eat,tea,ate],[tan,nat],[bat]]");
        
        Solution solution = new Solution();
        List<List<String>> groups = solution.groupAnagrams(anagrams);
        
        System.out.println("Output:");
        for (List<String> group : groups) {
            System.out.println(group);
        }
        
        // Test Longest Word
        System.out.println("\nLongest Word in Dictionary:");
        String[] words = {"w", "wo", "wor", "worl", "world"};
        System.out.println("Input: [w, wo, wor, worl, world]");
        System.out.println("Expected: world");
        
        LongestWordSolution longestWord = new LongestWordSolution();
        String longest = longestWord.longestWord(words);
        System.out.println("Output: " + longest);
        
        // Test Longest Word with All Prefixes
        System.out.println("\nLongest Word with All Prefixes:");
        String[] prefixWords = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        System.out.println("Input: [a, banana, app, appl, ap, apply, apple]");
        System.out.println("Expected: apply");
        
        // Create TrieBasics instance for testing
        TrieBasics trie = new TrieBasics();
        for (String prefixWord : prefixWords) {
            trie.insert(prefixWord);
        }
        
        trie.ans = "";
        trie.longestWordWithAllPrefixes(trie.root, new StringBuilder(""));
        System.out.println("Output: " + trie.ans);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Empty array
        System.out.println("Empty array group anagrams:");
        String[] emptyArray = {};
        Solution emptySolution = new Solution();
        List<List<String>> emptyGroups = emptySolution.groupAnagrams(emptyArray);
        System.out.println("Input: []");
        System.out.println("Output: " + emptyGroups);
        
        // Test case 3: Single word longest
        System.out.println("Single word longest:");
        String[] singleWord = {"xyz"};
        LongestWordSolution singleLongest = new LongestWordSolution();
        String singleResult = singleLongest.longestWord(singleWord);
        System.out.println("Input: [xyz]");
        System.out.println("Output: " + singleResult);
        
        // Test case 4: All same prefix length
        System.out.println("All same prefix length:");
        String[] samePrefix = {"a", "ab", "abc", "abcd"};
        TrieBasics samePrefixTrie = new TrieBasics();
        for (String samePrefixWord : samePrefix) {
            samePrefixTrie.insert(samePrefixWord);
        }
        
        samePrefixTrie.ans = "";
        samePrefixTrie.longestWordWithAllPrefixes(samePrefixTrie.root, new StringBuilder(""));
        System.out.println("Input: [a, ab, abc, abcd]");
        System.out.println("Output: " + samePrefixTrie.ans);
        
        // Test case 5: Multiple longest words with same length
        System.out.println("Multiple longest words same length:");
        String[] sameLength = {"cat", "dog", "pig", "fox"};
        LongestWordSolution sameLengthLongest = new LongestWordSolution();
        for (String sameLengthWord : sameLength) {
            sameLengthLongest.insert(sameLengthWord);
        }
        sameLengthLongest.ans = "";
        sameLengthLongest.dfs(sameLengthLongest.root);
        System.out.println("Input: [cat, dog, pig, fox]");
        System.out.println("Expected: cat (lexicographically smallest)");
        System.out.println("Output: " + sameLengthLongest.ans);
    }
}
