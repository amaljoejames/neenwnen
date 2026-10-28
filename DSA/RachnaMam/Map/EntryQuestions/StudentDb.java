package TIT.RachnaMam.Map.EntryQuestions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StudentDb {
    
    public static void main(String[] args) {
        HashMap<Integer, String> studentDb = new HashMap<>();
        studentDb.put(101, "Akash");
        studentDb.put(102, "Navya");
        studentDb.put(103, "Ram");
        studentDb.put(104, "Sita");
        studentDb.put(105, "Siddhi");
        
        System.out.println("Initial Student Database: " + studentDb);
        System.out.println();
        
        //clear()
        System.out.println("1.clear() Method:");
        HashMap<Integer, String> tempDb = new HashMap<>(studentDb);
        System.out.println("   Before clear: " + tempDb);
        tempDb.clear();
        System.out.println("   After clear: " + tempDb);
        System.out.println("   Is empty after clear: " + tempDb.isEmpty());
        System.out.println();
        
        //containsKey()
        System.out.println("2. containsKey() Method:");
        System.out.println("   Contains roll 101: " + studentDb.containsKey(101));
        System.out.println("   Contains roll 999: " + studentDb.containsKey(999));
        System.out.println();
        
        //containsValue()
        System.out.println("3. containsValue() Method:");
        System.out.println("   Contains 'Akash': " + studentDb.containsValue("Akash"));
        System.out.println("   Contains 'Unknown': " + studentDb.containsValue("Unknown"));
        System.out.println();
        
        //entrySet()
        System.out.println("4. entrySet() Method:");
        Set<Map.Entry<Integer, String>> entries = studentDb.entrySet();
        System.out.println("   All entries:");
        for (Map.Entry<Integer, String> entry : entries) {
            System.out.println("     Roll " + entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println();
        
        //equals() 
        System.out.println("5. equals() Method:");
        HashMap<Integer, String> copyDb = new HashMap<>(studentDb);
        HashMap<Integer, String> differentDb = new HashMap<>();
        differentDb.put(101, "Akash");
        differentDb.put(102, "Different");
        
        System.out.println("   Original equals copy: " + studentDb.equals(copyDb));
        System.out.println("   Original equals different: " + studentDb.equals(differentDb));
        System.out.println();
        
        //get()
        System.out.println("6. get() Method:");
        System.out.println("   Get roll 101: " + studentDb.get(101));
        System.out.println("   Get roll 999: " + studentDb.get(999));
        System.out.println();
        
        //hashCode()
        System.out.println("7. hashCode() Method:");
        System.out.println("   HashMap hash code: " + studentDb.hashCode());
        System.out.println("   Copy hash code: " + copyDb.hashCode());
        System.out.println("   Hash codes equal: " + (studentDb.hashCode() == copyDb.hashCode()));
        System.out.println();
        
        //isEmpty()
        System.out.println("8. isEmpty() Method:");
        System.out.println("   Is studentDb empty: " + studentDb.isEmpty());
        System.out.println("   Is tempDb empty: " + tempDb.isEmpty());
        System.out.println();
        
        //more
        System.out.println("Additional Methods:");
        System.out.println("   Size: " + studentDb.size());
        System.out.println("   KeySet: " + studentDb.keySet());
        System.out.println("   Values: " + studentDb.values());
        
    }
}
