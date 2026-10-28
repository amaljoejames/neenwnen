package TIT.RachnaMam.Map.EntryQuestions;

import java.util.HashMap;

public class CountWordFrequency {

    public static void main(String[] args) {

        String text = "java is a language java is a powerful";

        String[] words = text.split(" ");

        HashMap<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        System.out.println(freqMap);
        /*System.out.println("Word frequencies: " + freqMap);
        for (String word : freqMap.keySet()) {
            System.out.println(word + ": " + freqMap.get(word));
        }*/
    }
}
