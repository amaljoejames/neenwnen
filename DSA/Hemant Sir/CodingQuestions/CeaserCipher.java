/*
The CC is :

A Caesar Cipher is a type of substitution cipher in which
each alphabet or digit in the plaintext is shifted by a
fixed number of positions called KEY.

Brute Force:
(ch - 'a' + key) % 26 + 'a'
String mapping:
String alpha = "abcdefghijklmnopqrstuvwxyz";
Example
int index = alpha.indexOf(ch);
char newChar = alpha.charAt((index + key) % 26);

Rules:
1. Alphabets shift circularly
   Example:
   z with key 2 becomes b

2. Digits also shift circularly
   Example:
   8 with key 4 becomes 2

3. Special characters remain unchanged.

4. If key < 0 print "INVALID INPUT"

APPROACH USED :
ASCII Direct Transformation (Optimized)

We directly manipulate ASCII values of:
- lowercase letters
- uppercase letters
- digits

FORMULAS:

Lowercase:
(ch - 'a' + key) % 26 + 'a'

Uppercase:
(ch - 'A' + key) % 26 + 'A'

Digits:
(ch - '0' + key) % 10 + '0'

TIME COMPLEXITY : O(n)
SPACE COMPLEXITY : O(1)

SAMPLE INPUT:
All the best 5
2

SAMPLE OUTPUT:
Cnn vjg dguv 7
*/

import java.util.Scanner;

public class CeaserCipher {

    static String encrypt(String s, int key) {

        if (key < 0)
            return "INVALID INPUT";

        String ans = "";

        for (char ch : s.toCharArray()) {
            if (ch >= 'a' && ch <= 'z')
                ans += (char)((ch - 'a' + key) % 26 + 'a');//uc
            else if (ch >= 'A' && ch <= 'Z')
                ans += (char)((ch - 'A' + key) % 26 + 'A');//lc

            else if (ch >= '0' && ch <= '9')
                ans += (char)((ch - '0' + key) % 10 + '0');//dig
            else
                ans += ch;//spc
        }

        return ans;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        int key = sc.nextInt();

        System.out.println(encrypt(s, key));
    }
}