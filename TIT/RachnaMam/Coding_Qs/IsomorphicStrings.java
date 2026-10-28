import java.util.*;

public class IsomorphicStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("1st: ");
        String str1 = sc.nextLine();
        System.out.print("2nd: ");
        String str2 = sc.nextLine();
        boolean isIso = true;
        if (str1.length() != str2.length()) {
            isIso = false;
        } else {
            int[] map1 = new int[256];
            int[] map2 = new int[256];
            Arrays.fill(map1, -1);
            Arrays.fill(map2, -1);
            
            for (int i = 0; i < str1.length(); i++) {
                char c1 = str1.charAt(i);
                char c2 = str2.charAt(i);
                
                if (map1[c1] != map2[c2]) {
                    isIso = false;
                    break;
                }
                
                map1[c1] = i;
                map2[c2] = i;
            }
        }
        
        System.out.println("isomorphic? :" + isIso);
    }
}