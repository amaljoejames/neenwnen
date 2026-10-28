public class C1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        ArrayList<Integer> even = new ArrayList<>();
        ArrayList<Integer> odd = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();

            if (num % 2 == 0)
                even.add(num);
            else
                odd.add(num);
        }

        StringBuilder sb = new StringBuilder();

        for (int x : even)
            sb.append(x).append(" ");

        for (int x : odd)
            sb.append(x).append(" ");

        System.out.print(sb.toString().trim());
    }
    
}
