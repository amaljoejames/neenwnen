package TIT.RachnaMam.Map.EntryQuestions;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Student Database using HashMap
 * Key: Roll Number (Integer)
 * Value: Student Name (String)
 * 
 * This program demonstrates all essential HashMap methods with practical examples
 */
public class EntryQuestions {
    
    public static void main(String[] args) {
        System.out.println("=== STUDENT DATABASE SYSTEM ===\n");
        
        // Create student database
        HashMap<Integer, String> studentDatabase = new HashMap<>();
        
        // Initialize the database with sample data
        initializeStudentDatabase(studentDatabase);
        
        // Demonstrate all HashMap methods
        demonstrateAllMethods(studentDatabase);
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Interactive student management system
        studentManagementSystem();
    }
    
    /**
     * Initialize student database with sample data
     */
    private static void initializeStudentDatabase(HashMap<Integer, String> database) {
        System.out.println("=== Initializing Student Database ===");
        
        // Add students using put() method
        database.put(101, "Alice Johnson");
        database.put(102, "Bob Smith");
        database.put(103, "Charlie Brown");
        database.put(104, "Diana Prince");
        database.put(105, "Edward Norton");
        database.put(106, "Fiona Apple");
        database.put(107, "George Lucas");
        
        System.out.println("Database initialized with " + database.size() + " students");
        System.out.println("Current database: " + database);
        System.out.println();
    }
    
    /**
     * Demonstrate all HashMap methods with detailed explanations
     */
    private static void demonstrateAllMethods(HashMap<Integer, String> database) {
        System.out.println("=== COMPREHENSIVE HASHMAP METHODS DEMONSTRATION ===\n");
        
        // 1. CLEAR() - Remove all entries
        System.out.println("1. CLEAR() Method:");
        HashMap<Integer, String> tempDB = new HashMap<>(database);
        System.out.println("   Before clear: " + tempDB);
        tempDB.clear();
        System.out.println("   After clear: " + tempDB);
        System.out.println("   Is empty after clear: " + tempDB.isEmpty());
        System.out.println();
        
        // 2. CONTAINSKEY() - Check if roll number exists
        System.out.println("2. CONTAINSKEY() Method:");
        System.out.println("   Contains roll 101: " + database.containsKey(101));
        System.out.println("   Contains roll 999: " + database.containsKey(999));
        System.out.println("   Contains null key: " + database.containsKey(null));
        System.out.println();
        
        // 3. CONTAINSVALUE() - Check if student name exists
        System.out.println("3. CONTAINSVALUE() Method:");
        System.out.println("   Contains 'Alice Johnson': " + database.containsValue("Alice Johnson"));
        System.out.println("   Contains 'Unknown Student': " + database.containsValue("Unknown Student"));
        System.out.println("   Contains null value: " + database.containsValue(null));
        System.out.println();
        
        // 4. ENTRYSET() - Get all key-value pairs
        System.out.println("4. ENTRYSET() Method:");
        Set<Map.Entry<Integer, String>> entries = database.entrySet();
        System.out.println("   All entries:");
        for (Map.Entry<Integer, String> entry : entries) {
            System.out.println("     Roll " + entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("   EntrySet as collection: " + entries);
        System.out.println();
        
        // 5. EQUALS() - Compare two HashMaps
        System.out.println("5. EQUALS() Method:");
        HashMap<Integer, String> copyDB = new HashMap<>(database);
        HashMap<Integer, String> differentDB = new HashMap<>();
        differentDB.put(101, "Alice Johnson");
        differentDB.put(102, "Different Name");
        
        System.out.println("   Original equals copy: " + database.equals(copyDB));
        System.out.println("   Original equals different: " + database.equals(differentDB));
        System.out.println();
        
        // 6. GET() - Retrieve student name by roll number
        System.out.println("6. GET() Method:");
        System.out.println("   Get roll 101: " + database.get(101));
        System.out.println("   Get roll 999: " + database.get(999));
        System.out.println("   Get null key: " + database.get(null));
        System.out.println();
        
        // 7. HASHCODE() - Get hash code of the HashMap
        System.out.println("7. HASHCODE() Method:");
        int hashCode = database.hashCode();
        System.out.println("   HashMap hash code: " + hashCode);
        System.out.println("   Copy hash code: " + copyDB.hashCode());
        System.out.println("   Hash codes equal: " + (hashCode == copyDB.hashCode()));
        System.out.println();
        
        // 8. ISEMPTY() - Check if database is empty
        System.out.println("8. ISEMPTY() Method:");
        System.out.println("   Is database empty: " + database.isEmpty());
        System.out.println("   Is empty database empty: " + tempDB.isEmpty());
        System.out.println();
        
        // 9. KEYSET() - Get all roll numbers
        System.out.println("9. KEYSET() Method:");
        Set<Integer> rollNumbers = database.keySet();
        System.out.println("   All roll numbers: " + rollNumbers);
        System.out.println("   Number of unique roll numbers: " + rollNumbers.size());
        System.out.println();
        
        // 10. PUT() - Add or update student
        System.out.println("10. PUT() Method:");
        System.out.println("    Before put: " + database);
        String oldName = database.put(108, "Henry Ford");
        System.out.println("    Added new student 108: " + oldName);
        String updatedName = database.put(101, "Alice Cooper");
        System.out.println("    Updated student 101: " + updatedName);
        System.out.println("    After operations: " + database);
        System.out.println();
        
        // 11. PUTALL() - Copy all entries from another map
        System.out.println("11. PUTALL() Method:");
        HashMap<Integer, String> newStudents = new HashMap<>();
        newStudents.put(109, "Ivy League");
        newStudents.put(110, "Jack Daniels");
        
        System.out.println("    Before putAll: " + database);
        database.putAll(newStudents);
        System.out.println("    After putAll: " + database);
        System.out.println();
        
        // 12. REMOVE() - Remove student by roll number
        System.out.println("12. REMOVE() Method:");
        String removedName = database.remove(108);
        System.out.println("    Removed roll 108: " + removedName);
        String notRemoved = database.remove(999);
        System.out.println("    Attempted to remove roll 999: " + notRemoved);
        System.out.println("    Current database: " + database);
        System.out.println();
        
        // 13. SIZE() - Get number of students
        System.out.println("13. SIZE() Method:");
        System.out.println("    Current database size: " + database.size());
        System.out.println();
        
        // 14. VALUES() - Get all student names
        System.out.println("14. VALUES() Method:");
        System.out.println("    All student names: " + database.values());
        System.out.println();
        
        // 15. GETORDEFAULT() - Get value or default
        System.out.println("15. GETORDEFAULT() Method:");
        System.out.println("    Get roll 101 or 'Unknown': " + database.getOrDefault(101, "Unknown"));
        System.out.println("    Get roll 999 or 'Unknown': " + database.getOrDefault(999, "Unknown"));
        System.out.println();
        
        // 16. REPLACE() - Replace value for existing key
        System.out.println("16. REPLACE() Method:");
        String replacedName = database.replace(101, "Alice Wonderland");
        System.out.println("    Replaced roll 101: " + replacedName);
        String notReplaced = database.replace(999, "Unknown");
        System.out.println("    Attempted to replace roll 999: " + notReplaced);
        System.out.println("    Current database: " + database);
        System.out.println();
        
        // 17. COMPUTE() - Compute value for key
        System.out.println("17. COMPUTE() Method:");
        database.compute(102, (key, value) -> value + " (Updated)");
        System.out.println("    Computed new value for roll 102: " + database.get(102));
        database.compute(999, (key, value) -> "New Student " + key);
        System.out.println("    Computed value for new roll 999: " + database.get(999));
        System.out.println();
        
        // 18. MERGE() - Merge values
        System.out.println("18. MERGE() Method:");
        database.merge(103, " - Senior", (oldValue, newValue) -> oldValue + newValue);
        System.out.println("    Merged value for roll 103: " + database.get(103));
        database.merge(111, "New Student", (oldValue, newValue) -> oldValue + newValue);
        System.out.println("    Merged value for new roll 111: " + database.get(111));
        System.out.println();
    }
    
    /**
     * Interactive student management system
     */
    private static void studentManagementSystem() {
        System.out.println("=== INTERACTIVE STUDENT MANAGEMENT SYSTEM ===\n");
        
        HashMap<Integer, String> studentDB = new HashMap<>();
        
        // Add initial students
        studentDB.put(1001, "John Doe");
        studentDB.put(1002, "Jane Smith");
        studentDB.put(1003, "Mike Wilson");
        
        System.out.println("Initial Database: " + studentDB);
        
        // Demonstrate practical operations
        demonstratePracticalOperations(studentDB);
    }
    
    /**
     * Demonstrate practical operations on student database
     */
    private static void demonstratePracticalOperations(HashMap<Integer, String> db) {
        System.out.println("\n=== PRACTICAL OPERATIONS ===\n");
        
        // Operation 1: Find student by roll number
        System.out.println("1. Find Student by Roll Number:");
        findStudent(db, 1002);
        findStudent(db, 9999);
        
        // Operation 2: List all students
        System.out.println("\n2. List All Students:");
        listAllStudents(db);
        
        // Operation 3: Check if student exists
        System.out.println("\n3. Check Student Existence:");
        checkStudentExists(db, 1001);
        checkStudentExists(db, 8888);
        
        // Operation 4: Update student information
        System.out.println("\n4. Update Student Information:");
        updateStudent(db, 1003, "Michael Wilson");
        updateStudent(db, 9999, "Unknown Student");
        
        // Operation 5: Remove student
        System.out.println("\n5. Remove Student:");
        removeStudent(db, 1002);
        removeStudent(db, 7777);
        
        // Operation 6: Database statistics
        System.out.println("\n6. Database Statistics:");
        showDatabaseStatistics(db);
        
        // Operation 7: Search students by name pattern
        System.out.println("\n7. Search Students by Name Pattern:");
        searchStudentsByName(db, "John");
        searchStudentsByName(db, "Smith");
        
        // Operation 8: Bulk operations
        System.out.println("\n8. Bulk Operations:");
        demonstrateBulkOperations(db);
    }
    
    // Helper methods for practical operations
    
    private static void findStudent(HashMap<Integer, String> db, int rollNumber) {
        if (db.containsKey(rollNumber)) {
            System.out.println("   Student found: Roll " + rollNumber + " -> " + db.get(rollNumber));
        } else {
            System.out.println("   Student with roll " + rollNumber + " not found");
        }
    }
    
    private static void listAllStudents(HashMap<Integer, String> db) {
        System.out.println("   Total Students: " + db.size());
        for (Map.Entry<Integer, String> entry : db.entrySet()) {
            System.out.println("   Roll " + entry.getKey() + ": " + entry.getValue());
        }
    }
    
    private static void checkStudentExists(HashMap<Integer, String> db, int rollNumber) {
        boolean exists = db.containsKey(rollNumber);
        System.out.println("   Roll " + rollNumber + " exists: " + exists);
        
        // Also check by name
        String studentName = db.get(rollNumber);
        if (studentName != null) {
            boolean nameExists = db.containsValue(studentName);
            System.out.println("   Name '" + studentName + "' exists: " + nameExists);
        }
    }
    
    private static void updateStudent(HashMap<Integer, String> db, int rollNumber, String newName) {
        if (db.containsKey(rollNumber)) {
            String oldName = db.put(rollNumber, newName);
            System.out.println("   Updated: Roll " + rollNumber + " changed from '" + oldName + "' to '" + newName + "'");
        } else {
            System.out.println("   Roll " + rollNumber + " not found. Adding new student...");
            db.put(rollNumber, newName);
            System.out.println("   Added: Roll " + rollNumber + " -> " + newName);
        }
    }
    
    private static void removeStudent(HashMap<Integer, String> db, int rollNumber) {
        String removedName = db.remove(rollNumber);
        if (removedName != null) {
            System.out.println("   Removed: Roll " + rollNumber + " (" + removedName + ")");
        } else {
            System.out.println("   Roll " + rollNumber + " not found for removal");
        }
    }
    
    private static void showDatabaseStatistics(HashMap<Integer, String> db) {
        System.out.println("   Database Statistics:");
        System.out.println("   - Total Students: " + db.size());
        System.out.println("   - Is Empty: " + db.isEmpty());
        System.out.println("   - Hash Code: " + db.hashCode());
        System.out.println("   - All Roll Numbers: " + db.keySet());
        System.out.println("   - All Student Names: " + db.values());
    }
    
    private static void searchStudentsByName(HashMap<Integer, String> db, String pattern) {
        System.out.println("   Students containing '" + pattern + "':");
        boolean found = false;
        
        for (Map.Entry<Integer, String> entry : db.entrySet()) {
            if (entry.getValue().toLowerCase().contains(pattern.toLowerCase())) {
                System.out.println("   - Roll " + entry.getKey() + ": " + entry.getValue());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("   No students found with pattern '" + pattern + "'");
        }
    }
    
    private static void demonstrateBulkOperations(HashMap<Integer, String> db) {
        // Create new students to add
        HashMap<Integer, String> newStudents = new HashMap<>();
        newStudents.put(2001, "Alice Brown");
        newStudents.put(2002, "Bob Davis");
        newStudents.put(1001, "Johnathan Doe"); // This will update existing
        
        System.out.println("   Before bulk add: " + db);
        db.putAll(newStudents);
        System.out.println("   After bulk add: " + db);
        
        // Create a copy and compare
        HashMap<Integer, String> copyDB = new HashMap<>(db);
        boolean isEqual = db.equals(copyDB);
        System.out.println("   Original equals copy: " + isEqual);
        
        // Clear demonstration
        copyDB.clear();
        System.out.println("   Cleared copy size: " + copyDB.size());
        System.out.println("   Original still intact: " + db);
    }
}
