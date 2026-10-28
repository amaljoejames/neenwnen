package TIT.RachnaMam.Map.Basics;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * HASHMAP CONCEPT OVERVIEW:
 * ========================
 * 
 * 1. What is HashMap?
 *    - HashMap is a part of Java Collection Framework
 *    - Implements Map interface
 *    - Stores data in key-value pairs
 *    - Uses hashing technique for storage and retrieval
 *    - Allows null values and one null key
 *    - Does not maintain insertion order
 * 
 * 2. Internal Working:
 *    - Uses an array of Node objects (buckets)
 *    - Each Node contains: key, value, hash, next (for linked list in case of collision)
 *    - Hash function determines bucket index: hash(key) % (capacity-1)
 *    - Collision handling through chaining (linked list) or tree (Java 8+)
 * 
 * 3. Time Complexity:
 *    - Average case: O(1) for get, put, remove
 *    - Worst case: O(n) due to collision (before Java 8)
 *    - Worst case: O(log n) after Java 8 (due to tree balancing)
 * 
 * 4. Load Factor & Rehashing:
 *    - Default load factor: 0.75 (75%)
 *    - When 75% of buckets are filled, HashMap rehashes (doubles capacity)
 *    - Rehashing redistributes all entries to new bucket array
 */
public class MapBasics {
    
    public static void main(String[] args) {
        System.out.println("=== HASHMAP CONCEPTS DEMONSTRATION ===\n");
        
        // Program 1: Basic HashMap Operations
        demonstrateBasicOperations();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Program 2: Map Types and Advanced Functions
        demonstrateMapTypesAndFunctions();
    }
    
    /**
     * Program 1: Basic HashMap Operations Demonstration
     * Shows all fundamental HashMap methods with detailed explanations
     */
    public static void demonstrateBasicOperations() {
        System.out.println("PROGRAM 1: Basic HashMap Operations");
        System.out.println("-----------------------------------");
        
        // Creating a HashMap
        // Syntax: HashMap<KeyType, ValueType> mapName = new HashMap<>();
        HashMap<String, Integer> studentRollNumbers = new HashMap<>();
        
        System.out.println("1. Creating empty HashMap: " + studentRollNumbers);
        
        // PUT operation - Add key-value pairs
        studentRollNumbers.put("Alice", 101);
        studentRollNumbers.put("Bob", 102);
        studentRollNumbers.put("Charlie", 103);
        studentRollNumbers.put("David", 104);
        
        System.out.println("2. After adding elements: " + studentRollNumbers);
        
        // GET operation - Retrieve value by key
        Integer aliceRoll = studentRollNumbers.get("Alice");
        System.out.println("3. Get Alice's roll number: " + aliceRoll);
        
        // GET with non-existent key (returns null)
        Integer unknownRoll = studentRollNumbers.get("Unknown");
        System.out.println("4. Get non-existent key: " + unknownRoll);
        
        // CONTAINSKEY - Check if key exists
        boolean hasAlice = studentRollNumbers.containsKey("Alice");
        boolean hasUnknown = studentRollNumbers.containsKey("Unknown");
        System.out.println("5. ContainsKey 'Alice': " + hasAlice);
        System.out.println("6. ContainsKey 'Unknown': " + hasUnknown);
        
        // CONTAINSVALUE - Check if value exists
        boolean hasRoll101 = studentRollNumbers.containsValue(101);
        boolean hasRoll999 = studentRollNumbers.containsValue(999);
        System.out.println("7. ContainsValue 101: " + hasRoll101);
        System.out.println("8. ContainsValue 999: " + hasRoll999);
        
        // REMOVE operation - Remove key-value pair
        Integer removedRoll = studentRollNumbers.remove("Charlie");
        System.out.println("9. Removed Charlie's entry: " + removedRoll);
        System.out.println("10. HashMap after removal: " + studentRollNumbers);
        
        // SIZE - Get number of elements
        int size = studentRollNumbers.size();
        System.out.println("11. Current size: " + size);
        
        // ISEMPTY - Check if HashMap is empty
        boolean isEmpty = studentRollNumbers.isEmpty();
        System.out.println("12. Is HashMap empty? " + isEmpty);
        
        // CLEAR - Remove all elements
        studentRollNumbers.clear();
        System.out.println("13. HashMap after clear(): " + studentRollNumbers);
        System.out.println("14. Is empty after clear? " + studentRollNumbers.isEmpty());
        
        // REPOPULATE for more demonstrations
        studentRollNumbers.put("Alice", 101);
        studentRollNumbers.put("Bob", 102);
        studentRollNumbers.put("David", 104);
        
        // ENTRYSET - Get all key-value pairs as Set of Map.Entry
        Set<Map.Entry<String, Integer>> entries = studentRollNumbers.entrySet();
        System.out.println("15. All entries using entrySet():");
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println("   Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        
        // KEYSET - Get all keys
        Set<String> keys = studentRollNumbers.keySet();
        System.out.println("16. All keys using keySet(): " + keys);
        
        // VALUES - Get all values
        System.out.println("17. All values: " + studentRollNumbers.values());
        
        // HASHCODE - Get hash code of HashMap
        int hashCode = studentRollNumbers.hashCode();
        System.out.println("18. HashMap hash code: " + hashCode);
        
        // EQUALS - Compare two HashMaps
        HashMap<String, Integer> anotherMap = new HashMap<>();
        anotherMap.put("Alice", 101);
        anotherMap.put("Bob", 102);
        anotherMap.put("David", 104);
        
        boolean isEqual = studentRollNumbers.equals(anotherMap);
        System.out.println("19. Are both HashMaps equal? " + isEqual);
        
        // PUTALL - Copy all elements from another map
        HashMap<String, Integer> additionalStudents = new HashMap<>();
        additionalStudents.put("Eve", 105);
        additionalStudents.put("Frank", 106);
        
        studentRollNumbers.putAll(additionalStudents);
        System.out.println("20. After putAll(): " + studentRollNumbers);
        
        // REPLACE operation - Replace value for existing key
        Integer oldValue = studentRollNumbers.replace("Alice", 111);
        System.out.println("21. Replaced Alice's old value: " + oldValue + " with new: 111");
        
        // GETORDEFAULT - Get value or return default
        Integer defaultValue = studentRollNumbers.getOrDefault("Unknown", 0);
        System.out.println("22. GetOrDefault for non-existent key: " + defaultValue);
    }
    
    /**
     * Program 2: Map Types and Advanced Functions
     * Comprehensive overview of different Map implementations and their characteristics
     */
    public static void demonstrateMapTypesAndFunctions() {
        System.out.println("PROGRAM 2: Map Types and Advanced Functions");
        System.out.println("------------------------------------------");
        
        // Overview of different Map types
        demonstrateMapTypes();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Advanced HashMap functions
        demonstrateAdvancedFunctions();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Performance comparison
        demonstratePerformanceComparison();
    }
    
    /**
     * Demonstrate different types of Map implementations
     */
    private static void demonstrateMapTypes() {
        System.out.println("=== TYPES OF MAPS ===");
        
        System.out.println("1. HASHMAP:");
        System.out.println("   - Unordered, allows null keys and values");
        System.out.println("   - Fast: O(1) average time complexity");
        System.out.println("   - Not thread-safe");
        System.out.println("   - Best for: General purpose, fast lookups");
        
        System.out.println("\n2. LINKEDHASHMAP:");
        System.out.println("   - Maintains insertion order");
        System.out.println("   - Allows null keys and values");
        System.out.println("   - Slightly slower than HashMap");
        System.out.println("   - Best for: When order matters");
        
        System.out.println("\n3. TREEMAP:");
        System.out.println("   - Sorted by natural order or comparator");
        System.out.println("   - Does not allow null keys");
        System.out.println("   - O(log n) time complexity");
        System.out.println("   - Best for: Sorted data, range queries");
        
        System.out.println("\n4. HASHTABLE:");
        System.out.println("   - Legacy, thread-safe version of HashMap");
        System.out.println("   - Does not allow null keys or values");
        System.out.println("   - Slower due to synchronization");
        System.out.println("   - Best for: Legacy code, thread safety");
        
        System.out.println("\n5. CONCURRENTHASHMAP:");
        System.out.println("   - Thread-safe, high performance");
        System.out.println("   - Allows null values but not null keys");
        System.out.println("   - Concurrent access support");
        System.out.println("   - Best for: Multi-threaded environments");
        
        System.out.println("\n6. WEAKHASHMAP:");
        System.out.println("   - Keys held with weak references");
        System.out.println("   - Entries removed when GC claims keys");
        System.out.println("   - Best for: Caching, temporary storage");
        
        System.out.println("\n7. IDENTITYHASHMAP:");
        System.out.println("   - Uses == instead of equals() for key comparison");
        System.out.println("   - Reference equality, not object equality");
        System.out.println("   - Best for: Special cases with object identity");
    }
    
    /**
     * Demonstrate advanced HashMap functions
     */
    private static void demonstrateAdvancedFunctions() {
        System.out.println("=== ADVANCED HASHMAP FUNCTIONS ===");
        
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("Math", 95);
        scores.put("Science", 88);
        scores.put("English", 92);
        scores.put("History", 85);
        
        System.out.println("Initial scores: " + scores);
        
        // 1. COMPUTE() - Compute value for key
        System.out.println("\n1. COMPUTE() Functions:");
        scores.compute("Math", (key, value) -> value + 5);
        System.out.println("   compute('Math', value+5): " + scores.get("Math"));
        
        scores.computeIfAbsent("Art", key -> 80);
        System.out.println("   computeIfAbsent('Art', 80): " + scores.get("Art"));
        
        scores.computeIfPresent("Science", (key, value) -> value - 3);
        System.out.println("   computeIfPresent('Science', value-3): " + scores.get("Science"));
        
        // 2. MERGE() - Merge values
        System.out.println("\n2. MERGE() Function:");
        scores.merge("English", 10, (oldValue, newValue) -> oldValue + newValue);
        System.out.println("   merge('English', 10): " + scores.get("English"));
        
        // 3. REPLACEALL() - Replace all values
        System.out.println("\n3. REPLACEALL() Function:");
        System.out.println("   Before replaceAll: " + scores);
        scores.replaceAll((key, value) -> value >= 90 ? value + 2 : value + 5);
        System.out.println("   After replaceAll: " + scores);
        
        // 4. GETORDEFAULT() - Safe value retrieval
        System.out.println("\n4. GETORDEFAULT() Function:");
        System.out.println("   getOrDefault('Math', 0): " + scores.getOrDefault("Math", 0));
        System.out.println("   getOrDefault('Gym', 0): " + scores.getOrDefault("Gym", 0));
        
        // 5. PUTIFABSENT() - Conditional put
        System.out.println("\n5. PUTIFABSENT() Function:");
        scores.putIfAbsent("Math", 100);
        System.out.println("   putIfAbsent('Math', 100) - no change: " + scores.get("Math"));
        scores.putIfAbsent("Music", 78);
        System.out.println("   putIfAbsent('Music', 78) - added: " + scores.get("Music"));
        
        // 6. REPLACE() variants
        System.out.println("\n6. REPLACE() Variants:");
        scores.replace("History", 87);
        System.out.println("   replace('History', 87): " + scores.get("History"));
        
        boolean replaced = scores.replace("Science", 85, 90);
        System.out.println("   replace('Science', 85, 90): " + replaced);
        
        boolean notReplaced = scores.replace("Art", 75, 80);
        System.out.println("   replace('Art', 75, 80): " + notReplaced);
        
        // 7. FOREACH() - Iterate over entries
        System.out.println("\n7. FOREACH() Function:");
        System.out.println("   Iterating with forEach:");
        scores.forEach((key, value) -> System.out.println("     " + key + ": " + value));
        
        System.out.println("\n   Final scores: " + scores);
    }
    
    /**
     * Demonstrate performance characteristics
     */
    private static void demonstratePerformanceComparison() {
        System.out.println("=== PERFORMANCE COMPARISON ===");
        
        System.out.println("Time Complexity Comparison:");
        System.out.println("Operation      | HashMap | LinkedHashMap | TreeMap");
        System.out.println("---------------|---------|---------------|--------");
        System.out.println("get/put/remove |  O(1)   |     O(1)      |  O(log n)");
        System.out.println("containsKey    |  O(1)   |     O(1)      |  O(log n)");
        System.out.println("first/last     |  N/A    |     N/A       |  O(log n)");
        System.out.println("subMap         |  N/A    |     N/A       |  O(log n)");
        
        System.out.println("\nMemory Usage:");
        System.out.println("- HashMap: Moderate memory, efficient storage");
        System.out.println("- LinkedHashMap: Higher memory (maintains links)");
        System.out.println("- TreeMap: Higher memory (tree structure)");
        
        System.out.println("\nThread Safety:");
        System.out.println("- HashMap: Not thread-safe");
        System.out.println("- LinkedHashMap: Not thread-safe");
        System.out.println("- TreeMap: Not thread-safe");
        System.out.println("- Hashtable: Thread-safe (synchronized)");
        System.out.println("- ConcurrentHashMap: Thread-safe (better performance)");
        
        System.out.println("\nNull Handling:");
        System.out.println("- HashMap: Allows 1 null key, multiple null values");
        System.out.println("- LinkedHashMap: Same as HashMap");
        System.out.println("- TreeMap: No null keys, allows null values");
        System.out.println("- Hashtable: No null keys or values");
    }
}
