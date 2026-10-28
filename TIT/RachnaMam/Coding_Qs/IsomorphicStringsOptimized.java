import java.util.*;

public class IsomorphicStringsOptimized {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("1st: ");
        String str1 = sc.nextLine();
        System.out.print("2nd: ");
        String str2 = sc.nextLine();
        
        boolean isIso = isIsomorphic(str1, str2);
        System.out.println("isomorphic? :" + isIso);
    }
    
    public static boolean isIsomorphic(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        
        Map<Character, Character> map = new HashMap<>();
        
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            
            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) return false;
            } else {
                if (map.containsValue(c2)) return false;
                map.put(c1, c2);
            }
        }
        return true;
    }
    
    // Most optimized - Using arrays for ASCII
    public static boolean isIsomorphicOptimized(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        
        int[] map = new int[512]; // 256 for c1, 256 for c2
        Arrays.fill(map, -1);
        
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            
            int c1Index = c1;
            int c2Index = 256 + c2;
            
            if (map[c1Index] != map[c2Index]) {
                if (map[c1Index] == -1 && map[c2Index] == -1) {
                    map[c1Index] = map[c2Index] = i;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Space optimized - Using only one array
    public static boolean isIsomorphicSpaceOptimized(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        
        int[] map = new int[256];
        boolean[] visited = new boolean[256];
        
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            
            if (map[c1] == 0) {
                if (visited[c2]) return false;
                map[c1] = c2;
                visited[c2] = true;
            } else if (map[c1] != c2) {
                return false;
            }
        }
        return true;
    }
}
