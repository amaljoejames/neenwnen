import java.util.*;

/* 
plot ke andar koi forbidden row (x[]) na aaye.
plot ke andar koi forbidden column (y[]) na aaye.
Forbidden Row Check
for (int k = 0; k < X; k++) {
    if (i <= x[k] && i + S - 1 >= x[k]) {
        c = 1;
        i1 = i - S + 1;
    }
}

Forbidden Column Check
for (int k = 0; k < Y; k++) {
    if (j <= y[k] && j + S - 1 >= y[k]) {
        c = 1;
        j1 = j - S + 1;
    }
}
Valid Plot Count
if (c == 0 && i + S - 1 <= N && j + S - 1 <= M) {
    d++;
}
*/
public class ThePromisedLand {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {

            int n = sc.nextInt();
            int m = sc.nextInt();

            int x = sc.nextInt();
            int y = sc.nextInt();

            int s = sc.nextInt();

            long sum1 = 0;
            long sum2 = 0;

            int a = 0;

            // blocked rows
            for (int i = 0; i < x; i++) {
                int b = sc.nextInt();

                sum1 += (b - a - 1) / s;
                a = b;
            }

            sum1 += (n - a) / s;

            a = 0;

            // blocked columns
            for (int i = 0; i < y; i++) {
                int b = sc.nextInt();

                sum2 += (b - a - 1) / s;
                a = b;
            }

            sum2 += (m - a) / s;

            System.out.println(sum1 * sum2);
        }

        sc.close();
    }
}
