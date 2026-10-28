package TIT.RachnaMam.Map.EntryQuestions;

import java.util.HashMap;

public class FirstNonRepeatingChar {
    public static void main(String[] args) {
        String name = "rahulsharma";
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : name.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : name.toCharArray()) {
            if (map.get(c) == 1) {
                System.out.println("First nnc : " + c);
                return;
            }
        }
        System.out.println("Not found ");
    }
}
