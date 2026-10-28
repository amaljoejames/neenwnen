public class CheckPronic {
    public static boolean isPronic(int n) {
        int x = (int) Math.sqrt(n);

        return x * (x + 1) == n || (x - 1) * x == n;
    }

    public static void main(String[] args) {

        int[] arr = { 2, 6, 12, 30, 42, 56, 100 };

        for (int n : arr) {
            System.out.println(n + " - " + isPronic(n));
        }
    }

}
