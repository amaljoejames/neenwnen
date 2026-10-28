public class Recursion {
    public static void solve(int x, int y) {
        if (x < 5) {
            solve(x + 2, y);
        }

        System.out.println(y);
    }

    public static int function1(int a, int b) {
        if (a < 3 & b < 4) {
            return function1(a + 1, b + 1);
        } else {
            return a + b;
        }
    }

    public static int function2(int a, int b) {
        if (a > 0) {
            if (b > 0) {
                return a + b + function2(a + 1, 0) + function2(a + 2, 0);
            }
        }
        return a + b;
    }

    public static int function5(int a, int b) {
        if (a < b) {
            return function5(b, a);
        } else if (b != 0) {
            return a + function5(a, b - 1);
        } else {
            return 0;
        }
    }

    public static int function6(int n) {
    
        if (n <= 1) {
            return 1;
        }
        if (n % 2 == 0) {
            return function6(n / 2);
        }
      
        return function6(n / 2) + function6(n / 2 + 1);
    }
    
    
    // public static void fun7(int n){
    //     if(n>0){
    //         fun7(n-1);
    //         System.out.print(n-1);
    //     }
    // }

     public static void fun8(int n){
        /*
         * Recursion tree for fun8(3):
         * fun8(3)
         * ├─ fun8(2)
         * │  ├─ fun8(1)
         * │  │  ├─ fun8(0) → returns
         * │  │  ├─ print 1
         * │  │  └─ fun8(0) → returns
         * │  ├─ print 2
         * │  └─ fun8(1)
         * │     ├─ fun8(0) → returns
         * │     ├─ print 1
         * │     └─ fun8(0) → returns
         * ├─ print 3
         * └─ fun8(2)
         *    ├─ fun8(1)
         *    │  ├─ fun8(0) → returns
         *    │  ├─ print 1
         *    │  └─ fun8(0) → returns
         *    ├─ print 2
         *    └─ fun8(1)
         *       ├─ fun8(0) → returns
         *       ├─ print 1
         *       └─ fun8(0) → returns
         *
         * Output: 1212123
         */
        if(n>0){
            fun8(n-1);

        System.out.print(n);
        fun8(n-1);
        }

    }

    public static int fun9(int n){
        if(n<=0){
            return 0;
        }
        return n*n+fun9(n-2);
    }
     public static int fun10(int n){
        /*fun10(5)
├─ n=5, odd → return fun10(4)
│
├─ fun10(4)
│  ├─ n=4, even → return 4 + fun10(2)
│  │
│  └─ fun10(2)
│     ├─ n=2, even → return 2 + fun10(0)
│     │
│     └─ fun10(0)
│        └─ n=0, ≤1 → return 1
│
└─ fun10(2) = 2 + 1 = 3
└─ fun10(4) = 4 + 3 = 7 */
        if(n<=1){
            return 1;
        }
        if(n %2==0){
            return n+ fun10(n-2);
        }
        return fun10(n-1);
     }

     public static int fun11(int n){
        /*fun11(6)
├─ return fun11(5) + fun11(3)
│
├─ fun11(5)
│  └─ return fun11(4) + fun11(2)
│     ├─ fun11(4)
│     │  └─ return fun11(3) + fun11(1)
│     │     ├─ fun11(3) = fun11(2) + fun11(0) = 1 + 1 = 2
│     │     └─ fun11(1) = 1
│     │  → 2 + 1 = 3
│     └─ fun11(2) = 1
│  → 3 + 1 = 4
│
├─ fun11(3)
│  └─ return fun11(2) + fun11(0) = 1 + 1 = 2
│
└─ fun11(6) = 4 + 2 = 6 */
        if(n<=2){
            return 1;
        }
        
        return fun11(n-1)+fun11(n-3);
    }
        public static int fun12(int n){
            if(n<=0){
                return 0;
            }
            
            
            return n+fun12(n-2);
        }
     public static int fun13(int n){
        if(n<=1){
            return 1;
        }
        
        
        return fun13(n-1) + fun13(n-2);
    }
     public static int fun14(int n){
        if(n<=1){
            return 1;
        }
        if(n%2==0){
            return fun14(n/2);
        }else{
        
        
        return fun14(3*n+1);}
    }
  public static int fun15(int x, int y){
        int result=0;
        while(x>0){
            if(y%2==1){
                result=result+x;
            }
            x=x-1;
            y=y-1;
        }
        return result;
    }
    public static void main(String[] args) {
        solve(3, 5);
        System.out.println(function1(2, 6));
        System.out.println(function2(2, 6));
        System.out.println(function5(8, 9));
        System.out.println(function6(11));
        // fun7(3);
        fun8(3);
        System.out.println();
        System.out.println(fun9(7));
        System.out.println(fun10(5));
        System.out.println(fun11(6));
        System.out.println(fun12(5));
        System.out.println(fun13(8));
        System.out.println(fun14(16));
        System.out.println(fun15(5,3));

    }

}
