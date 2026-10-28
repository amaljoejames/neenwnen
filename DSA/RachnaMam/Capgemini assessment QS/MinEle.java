import java.util.Arrays;

public class MinEle {
    public static int findMin(int[] arr) {
        return Arrays.stream(arr).min().getAsInt();
    }

    

    public static void main(String[] args) {
        int[] arr = { 3, 7, 0, 9, 2 };
        System.out.println(findMin(arr));
    }

}
