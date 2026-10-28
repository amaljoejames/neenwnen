public class ChangeCase {
    public static char changeCase(char ch) {
        return Character.isUpperCase(ch) ? Character.toLowerCase(ch) : Character.toUpperCase(ch);
    }

    public static void main(String[] args) {

        System.out.println(changeCase('a'));
        System.out.println(changeCase('A'));
    }

}
