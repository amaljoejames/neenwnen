import java.util.Stack;

public class ParenthesisMatching {
    //  Parenthesis Matching (Google, Microsoft, Amazon)
    public static int parenthesisMatching(String str) {
    Stack<Character> st = new Stack<>();

    for(char ch : str.toCharArray()) {

        if(ch=='(' || ch=='[' || ch=='{')
            st.push(ch);

        else {
            if(st.isEmpty())
                return 1;

            char top = st.pop();

            if((ch==')' && top!='(') ||
               (ch==']' && top!='[') ||
               (ch=='}' && top!='{'))
                return 1;
        }
    }

    return st.isEmpty() ? 0 : 1;
}
    public static void main(String[] args) {

    String[] tests = {
        "{[()()]}",
        "}[()]",
        "{([])}{",
        "{[()]}"
    };

    for (String str : tests) {
        System.out.println("Input  : " + str);
        System.out.println("Output : " + parenthesisMatching(str));
        System.out.println();
    }
}
    
}
