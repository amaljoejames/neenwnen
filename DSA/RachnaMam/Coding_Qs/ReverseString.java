public class ReverseString {
    public static void main(String[] args) {
        String input = "i like this program very much";
        StringBuilder reversed = new StringBuilder();
        
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed.append(input.charAt(i));
        }
        
        System.out.println(reversed);//O(N)
    }
}
