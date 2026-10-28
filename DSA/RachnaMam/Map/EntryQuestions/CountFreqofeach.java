package TIT.RachnaMam.Map.EntryQuestions;

import java.util.HashMap;

public class CountFreqofeach {
    
    public static void main(String[] args) {
         String str="programming";
        // Create HashMap to store character frequencies
        HashMap<Character, Integer> freqMap = new HashMap<>();
        
        // Count frequency of each character using getOrDefault
        for (char ch : str.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        
        // Print the frequency map
        System.out.println("Character frequencies: " + freqMap);
        
        // Print individual frequencies
        System.out.println("\nIndividual frequencies:");
        for (char ch : freqMap.keySet()) {
            System.out.println(ch + ": " + freqMap.get(ch));
        }
    }
}
