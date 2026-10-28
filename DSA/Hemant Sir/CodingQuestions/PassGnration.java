public class PassGnration {

    public static String generatePassword(String input) {

        StringBuilder password = new StringBuilder();

        String[] pairs = input.split(",");

        for (String pair : pairs) {

            String[] parts = pair.split(":");

            String name = parts[0];
            String code = parts[1];

            int maxDigit = -1;

            for (char ch : code.toCharArray()) {
                int digit = ch - '0';

                if (digit <= name.length()) {
                    maxDigit = Math.max(maxDigit, digit);
                }
            }

            if (maxDigit == -1) {
                password.append('X');
            } else {
                password.append(name.charAt(maxDigit - 1));
            }
        }

        return password.toString();
    }

    public static void main(String[] args) {

        String input =
            "Abhishek:34848,Mayur:4739,Friends:2949,Yeah:9889";

        System.out.println(generatePassword(input));
    }

    
}
