package TIT.AmarNayakSir;

/*
 * ===============================================================================
 * HOMEWORK 1 - JAVA PROGRAMMING FUNDAMENTALS
 * ============================================================================= 
 * This file contains comprehensive notes, pseudocode, and Java programs covering:
 * 1. Logical Operators
 * 2. Bitwise Operators  
 * 3. Loop Control Statements
 * 4. Mathematical Operations (Factorial, Sum of Digits, Reverse, etc.)
 * 5. Functions and Procedures
 * ===============================================================================
 */

public class HW1 {
    
    /*
     * ===============================================================================
     * SECTION 1: LOGICAL OPERATORS
     * ===============================================================================
     * Logical operators are used to combine multiple conditions.
     * 
     * Types:
     * 1. AND ( && ) - Condition is true only if both conditions are true
     * 2. OR ( || ) - Condition is true if any one condition is true
     * 3. NOT ( ! ) - It reverses the result
     * ===============================================================================
     */
    
    /*
     * AND Operator Truth Table:
     * A | B | A && B
     * T | T |   T
     * T | F |   F
     * F | T |   F
     * F | F |   F
     * 
     * OR Operator Truth Table:
     * A | B | A || B
     * T | T |   T
     * T | F |   T
     * F | T |   T
     * F | F |   F
     * 
     * NOT Operator Truth Table:
     * A | !A
     * T |  F
     * F |  T
     */
    
    /*
     * ===============================================================================
     * SECTION 2: BITWISE OPERATORS
     * ===============================================================================
     * Bitwise operators work directly on binary values.
     * 
     * Types:
     * 1. AND (&)
     * 2. OR (|)
     * 3. XOR (^)
     * 4. Complement (~)
     * 5. Left Shift (<<)
     * 6. Right Shift (>>)
     * ===============================================================================
     */
    
    /**
     * Pseudocode for Bitwise Operators Demo:
     * 
     * START
     * DECLARE x, y
     * SET x = 17
     * SET y = 19
     * 
     * PRINT "AND = " + (x & y)
     * PRINT "OR = " + (x | y)
     * PRINT "XOR = " + (x ^ y)
     * PRINT "Complement of x = " + (~x)
     * PRINT "Left Shift = " + (x << 1)
     * PRINT "Right Shift = " + (x >> 1)
     * STOP
     */
    
    public static void bitwiseExample() {
        int x = 17;  // Binary: 10001
        int y = 19;  // Binary: 10011
        
        System.out.println("=== BITWISE OPERATORS DEMO ===");
        System.out.println("x = " + x + " (Binary: 10001)");
        System.out.println("y = " + y + " (Binary: 10011)");
        System.out.println();
        
        // Bitwise AND: 10001 & 10011 = 10001 (17)
        System.out.println("AND = " + (x & y));
        
        // Bitwise OR: 10001 | 10011 = 10011 (19)
        System.out.println("OR = " + (x | y));
        
        // Bitwise XOR: 10001 ^ 10011 = 00010 (2)
        System.out.println("XOR = " + (x ^ y));
        
        // Bitwise Complement
        System.out.println("Complement of x = " + (~x));
        
        // Left Shift (multiplies by 2): 17 << 1 = 34
        System.out.println("Left Shift = " + (x << 1));
        
        // Right Shift (divides by 2): 17 >> 1 = 8
        System.out.println("Right Shift = " + (x >> 1));
        System.out.println();
    }
    
    /*
     * ===============================================================================
     * SECTION 3: LOOP CONTROL STATEMENTS
     * ===============================================================================
     * Loop statements are used to execute a block of code repeatedly.
     * 
     * Types:
     * 1. while loop
     * 2. do while loop
     * 3. for loop
     * ===============================================================================
     */
    
    /**
     * Pseudocode for While Loop (Print 1 to 10):
     * 
     * START
     * DECLARE i
     * SET i = 1
     * 
     * WHILE i <= 10
     *     PRINT i
     *     i = i + 1
     * END WHILE
     * STOP
     */
    
    public static void whileExample() {
        System.out.println("=== WHILE LOOP - PRINT 1 TO 10 ===");
        int i = 1;
        
        while(i <= 10) {
            System.out.println(i);
            i++;
        }
        System.out.println();
    }
    
    /**
     * Pseudocode for While Loop (Print Even Numbers):
     * 
     * START
     * DECLARE i
     * SET i = 2
     * 
     * WHILE i <= 20
     *     PRINT i
     *     i = i + 2
     * END WHILE
     * STOP
     */
    
    public static void evenNumbersWhile() {
        System.out.println("=== WHILE LOOP - PRINT EVEN NUMBERS ===");
        int i = 2;
        
        while(i <= 20) {
            System.out.println(i);
            i = i + 2;
        }
        System.out.println();
    }
    
    /**
     * Pseudocode for Sum of First 10 Numbers:
     * 
     * START
     * DECLARE i, sum
     * SET i = 1
     * SET sum = 0
     * 
     * WHILE i <= 10
     *     sum = sum + i
     *     i = i + 1
     * END WHILE
     * 
     * PRINT "Sum = " + sum
     * STOP
     */
    
    public static void sumFirst10Numbers() {
        System.out.println("=== SUM OF FIRST 10 NUMBERS ===");
        int i = 1;
        int sum = 0;
        
        while(i <= 10) {
            sum = sum + i;
            i++;
        }
        
        System.out.println("Sum = " + sum);
        System.out.println();
    }
    
    /*
     * ===============================================================================
     * SECTION 4: FOR LOOP
     * ===============================================================================
     * For loop is used when the number of iterations is known.
     * 
     * Syntax:
     * for(initialization; condition; increment/decrement)
     * {
     *     statement
     * }
     * ===============================================================================
     */
    
    /**
     * Pseudocode for For Loop (Print 1 to 10):
     * 
     * START
     * FOR i = 1 to 10
     *     PRINT i
     * END FOR
     * STOP
     */
    
    public static void forExample() {
        System.out.println("=== FOR LOOP - PRINT 1 TO 10 ===");
        for(int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
        System.out.println();
    }
    
    /**
     * Pseudocode for For Loop (Print Even Numbers):
     * 
     * START
     * FOR i = 2 to 20 step 2
     *     PRINT i
     * END FOR
     * STOP
     */
    
    public static void evenNumbersFor() {
        System.out.println("=== FOR LOOP - PRINT EVEN NUMBERS ===");
        for(int i = 2; i <= 20; i = i + 2) {
            System.out.println(i);
        }
        System.out.println();
    }
    
    /**
     * Pseudocode for For Loop (Print Odd Numbers):
     * 
     * START
     * FOR i = 1 to 20 step 2
     *     PRINT i
     * END FOR
     * STOP
     */
    
    public static void oddNumbersFor() {
        System.out.println("=== FOR LOOP - PRINT ODD NUMBERS ===");
        for(int i = 1; i <= 20; i = i + 2) {
            System.out.println(i);
        }
        System.out.println();
    }
    
    /*
     * ===============================================================================
     * SECTION 5: MATHEMATICAL OPERATIONS
     * ===============================================================================
     */
    
    /**
     * Pseudocode for Factorial:
     * 
     * START
     * DECLARE n, fact, i
     * PRINT "Enter number: "
     * INPUT n
     * SET fact = 1
     * 
     * FOR i = 1 to n
     *     fact = fact * i
     * END FOR
     * 
     * PRINT "Factorial = " + fact
     * STOP
     */
    
    public static void factorial() {
        System.out.println("=== FACTORIAL PROGRAM ===");
        java.util.Scanner sc = new java.util.Scanner(System.in);
        
        int n;
        int fact = 1;
        
        System.out.print("Enter number: ");
        n = sc.nextInt();
        
        for(int i = 1; i <= n; i++) {
            fact = fact * i;
        }
        
        System.out.println("Factorial = " + fact);
        System.out.println();
    }
    
    /**
     * Pseudocode for Sum of Digits:
     * 
     * START
     * DECLARE num, sum, digit
     * PRINT "Enter number: "
     * INPUT num
     * SET sum = 0
     * 
     * WHILE num > 0
     *     digit = num % 10
     *     sum = sum + digit
     *     num = num / 10
     * END WHILE
     * 
     * PRINT "Sum of digits = " + sum
     * STOP
     */
    
    public static void sumOfDigits() {
        System.out.println("=== SUM OF DIGITS PROGRAM ===");
        java.util.Scanner sc = new java.util.Scanner(System.in);
        
        int num;
        int sum = 0;
        
        System.out.print("Enter number: ");
        num = sc.nextInt();
        
        while(num > 0) {
            int digit = num % 10;
            sum = sum + digit;
            num = num / 10;
        }
        
        System.out.println("Sum of digits = " + sum);
        System.out.println();
    }
    
    /**
     * Pseudocode for Reverse Number:
     * 
     * START
     * DECLARE num, reverse, digit
     * PRINT "Enter number: "
     * INPUT num
     * SET reverse = 0
     * 
     * WHILE num > 0
     *     digit = num % 10
     *     reverse = reverse * 10 + digit
     *     num = num / 10
     * END WHILE
     * 
     * PRINT "Reverse = " + reverse
     * STOP
     */
    
    public static void reverseNumber() {
        System.out.println("=== REVERSE NUMBER PROGRAM ===");
        java.util.Scanner sc = new java.util.Scanner(System.in);
        
        int num;
        int reverse = 0;
        
        System.out.print("Enter number: ");
        num = sc.nextInt();
        
        while(num > 0) {
            int digit = num % 10;
            reverse = reverse * 10 + digit;
            num = num / 10;
        }
        
        System.out.println("Reverse = " + reverse);
        System.out.println();
    }
    
    /**
     * Pseudocode for Palindrome Number:
     * 
     * START
     * DECLARE num, temp, reverse, digit
     * PRINT "Enter number: "
     * INPUT num
     * SET temp = num
     * SET reverse = 0
     * 
     * WHILE num > 0
     *     digit = num % 10
     *     reverse = reverse * 10 + digit
     *     num = num / 10
     * END WHILE
     * 
     * IF temp == reverse
     *     PRINT "Palindrome"
     * ELSE
     *     PRINT "Not Palindrome"
     * END IF
     * STOP
     */
    
    public static void palindrome() {
        System.out.println("=== PALINDROME NUMBER PROGRAM ===");
        java.util.Scanner sc = new java.util.Scanner(System.in);
        
        int num, temp, reverse = 0;
        
        System.out.print("Enter number: ");
        num = sc.nextInt();
        
        temp = num;
        
        while(num > 0) {
            int digit = num % 10;
            reverse = reverse * 10 + digit;
            num = num / 10;
        }
        
        if(temp == reverse) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
        System.out.println();
    }
    
    /**
     * Pseudocode for Armstrong Number:
     * 
     * START
     * DECLARE num, temp, sum, digit
     * PRINT "Enter number: "
     * INPUT num
     * SET temp = num
     * SET sum = 0
     * 
     * WHILE num > 0
     *     digit = num % 10
     *     sum = sum + (digit * digit * digit)
     *     num = num / 10
     * END WHILE
     * 
     * IF sum == temp
     *     PRINT "Armstrong Number"
     * ELSE
     *     PRINT "Not Armstrong Number"
     * END IF
     * STOP
     */
    
    public static void armstrong() {
        System.out.println("=== ARMSTRONG NUMBER PROGRAM ===");
        java.util.Scanner sc = new java.util.Scanner(System.in);
        
        int num, temp, sum = 0;
        
        System.out.print("Enter number: ");
        num = sc.nextInt();
        
        temp = num;
        
        while(num > 0) {
            int digit = num % 10;
            sum = sum + (digit * digit * digit);
            num = num / 10;
        }
        
        if(sum == temp) {
            System.out.println("Armstrong Number");
        } else {
            System.out.println("Not Armstrong Number");
        }
        System.out.println();
    }
    
    /*
     * ===============================================================================
     * SECTION 6: SAMPLE PROGRAMS FROM NOTEBOOK
     * ===============================================================================
     */
    
    /**
     * Pseudocode for Example 1:
     * 
     * START
     * DECLARE p,q,r
     * SET p = 5
     * SET q = 10 + p
     * 
     * IF (5 + q < 11) THEN
     *      r = p - (q + 1)
     * ELSE
     *      r = (q - p) + p
     * END IF
     * 
     * PRINT p + q + r
     * STOP
     */
    
    public static void example1() {
        System.out.println("=== EXAMPLE 1 ===");
        int p, q, r;
        
        p = 5;
        q = 10 + p;
        
        if(5 + q < 11)
            r = p - (q + 1);
        else
            r = (q - p) + p;
        
        System.out.println(p + q + r);
        System.out.println();
    }
    
    /**
     * Pseudocode for Example 2:
     * 
     * START
     * DECLARE x,y,z
     * SET x = 10
     * SET y = 16
     * SET z = 3
     * 
     * IF x > y
     *       z = x + y
     * ELSE
     *       y = x / 2
     * END IF
     * 
     * IF z > y
     *       x = 2 * y
     * ELSE
     *       z = x / 2
     * END IF
     * 
     * PRINT x + y + z
     * STOP
     */
    
    public static void example2() {
        System.out.println("=== EXAMPLE 2 ===");
        int x, y, z;
        
        x = 10;
        y = 16;
        z = 3;
        
        if(x > y)
            z = x + y;
        else
            y = x / 2;
        
        if(z > y)
            x = 2 * y;
        else
            z = x / 2;
        
        System.out.println(x + y + z);
        System.out.println();
    }
    
    /**
     * Pseudocode for Sum Program:
     * 
     * START
     * DECLARE p,q,r,sum
     * SET p = 36
     * SET q = 4
     * SET r = 40
     * SET sum = p + q + r
     * PRINT sum
     * STOP
     */
    
    public static void sumProgram() {
        System.out.println("=== SUM PROGRAM ===");
        int p, q, r, sum;
        
        p = 36;
        q = 4;
        r = 40;
        
        sum = p + q + r;
        
        System.out.println(sum);
        System.out.println();
    }
    
    /**
     * Pseudocode for While Loop Countdown:
     * 
     * START
     * DECLARE n
     * SET n = 10
     * 
     * WHILE n > 0
     *       PRINT n
     *       n = n - 1
     * END WHILE
     * STOP
     */
    
    public static void whileLoopCountdown() {
        System.out.println("=== WHILE LOOP COUNTDOWN ===");
        int n = 10;
        
        while(n > 0) {
            System.out.println(n);
            n = n - 1;
        }
        System.out.println();
    }
    
    /*
     * ===============================================================================
     * SECTION 7: FUNCTIONS AND PROCEDURES
     * ===============================================================================
     * 
     * Procedure: A block of code that performs a task but does not return value
     * Function: Performs a task and returns a value
     * ===============================================================================
     */
    
    /**
     * Pseudocode for Add Function:
     * 
     * FUNCTION add(x,y)
     * RETURN x + y
     * END FUNCTION
     */
    
    static int add(int x, int y) {
        return x + y;
    }
    
    public static void functionExample() {
        System.out.println("=== FUNCTION EXAMPLE ===");
        int result = add(5, 3);
        System.out.println(result);
        System.out.println();
    }
    
    /**
     * Pseudocode for Factorial Function:
     * 
     * FUNCTION factorial(n)
     * DECLARE fact, i
     * SET fact = 1
     * 
     * FOR i = 1 to n
     *      fact = fact * i
     * END FOR
     * 
     * RETURN fact
     * END FUNCTION
     */
    
    static int factorialFunction(int n) {
        int f = 1;
        
        for(int i = 1; i <= n; i++)
            f = f * i;
        
        return f;
    }
    
    public static void factorialFunctionExample() {
        System.out.println("=== FACTORIAL FUNCTION EXAMPLE ===");
        System.out.println(factorialFunction(5));
        System.out.println();
    }
    
    /*
     * ===============================================================================
     * OPERATOR PRECEDENCE TABLE
     * ===============================================================================
     * 
     * Priority (Highest to Lowest):
     * 1. Unary      : ++  --  !
     * 2. Multiplicative : *  /  %
     * 3. Additive   : +  -
     * 4. Relational : <  >  <=  >=
     * 5. Equality   : ==  !=
     * 6. Logical    : &&  ||
     * 
     * Associativity: Left to Right
     * Example: a + b * c
     * b * c is evaluated first, then addition
     * ===============================================================================
     */
    
    /*
     * ===============================================================================
     * SECTION 8: ADDITIONAL FUNCTION QUESTIONS
     * ===============================================================================
     */
    
    /**
     * Pseudocode for Function fun(a,b):
     * 
     * START
     * 
     * FUNCTION fun(a,b)
     * c = a + a + b + b
     * b = c + b
     * c = b - a
     * RETURN a + b
     * END FUNCTION
     * 
     * CALL fun(5,5)
     * STOP
     */
    
    static int fun(int a, int b) {
        int c;
        
        c = a + a + b + b;
        b = c + b;
        c = b - a;
        
        return a + b;
    }
    
    public static void exampleFun() {
        System.out.println("=== FUNCTION fun(a,b) EXAMPLE ===");
        System.out.println("Given: a = 5, b = 5");
        System.out.println("Calculation:");
        System.out.println("c = 5 + 5 + 5 + 5 = 20");
        System.out.println("b = 20 + 5 = 25");
        System.out.println("c = 25 - 5 = 20");
        System.out.println("return a + b = 5 + 25 = 30");
        System.out.println("Result: " + fun(5,5));
        System.out.println();
    }
    
    /**
     * Pseudocode for Reverse Procedure:
     * 
     * PROCEDURE reverse(n)
     * DECLARE a,b=0
     * WHILE n > 0
     *     a = n mod 10
     *     b = b * 10 + a
     *     n = n / 10
     * END WHILE
     * PRINT b
     * END PROCEDURE
     * 
     * CALL reverse(1234)
     */
    
    static void reverseProcedure(int n) {
        int a;
        int b = 0;
        
        while(n > 0) {
            a = n % 10;
            b = b * 10 + a;
            n = n / 10;
        }
        
        System.out.println(b);
    }
    
    public static void reverseProcedureExample() {
        System.out.println("=== REVERSE PROCEDURE EXAMPLE ===");
        System.out.println("Input: 1234");
        System.out.print("Output: ");
        reverseProcedure(1234);
        System.out.println();
    }
    
    /**
     * Pseudocode for Palindrome Function:
     * 
     * FUNCTION palindrome(num)
     * DECLARE a,b=0
     * n = num
     * WHILE num > 0
     *     a = num mod 10
     *     b = b * 10 + a
     *     num = num / 10
     * END WHILE
     * IF b = n
     *     PRINT "Palindrome"
     * ELSE
     *     PRINT "Not Palindrome"
     * END IF
     * END FUNCTION
     * 
     * CALL palindrome(121)
     */
    
    static void palindromeFunction(int num) {
        int a;
        int b = 0;
        int n = num;
        
        while(num > 0) {
            a = num % 10;
            b = b * 10 + a;
            num = num / 10;
        }
        
        if(b == n)
            System.out.println("Palindrome");
        else
            System.out.println("Not Palindrome");
    }
    
    public static void palindromeFunctionExample() {
        System.out.println("=== PALINDROME FUNCTION EXAMPLE ===");
        System.out.println("Input: 121");
        System.out.print("Output: ");
        palindromeFunction(121);
        System.out.println();
    }
    
    /**
     * Pseudocode for Function test(a,b,c):
     * 
     * FUNCTION test(a,b,c)
     * FOR each c from 3 to 5
     *     a = (a + c) ^ b
     *     IF (a + c) < (c + a)
     *         b = (b + 11) + c
     *     ELSE
     *         c = b + b
     *         a = 3 + b
     *         CONTINUE
     *     END IF
     * END FOR
     * RETURN a + b
     * END FUNCTION
     */
    
    static int test(int a, int b, int c) {
        for(c = 3; c <= 5; c++) {
            a = (int)Math.pow(a + c, b);
            
            if((a + c) < (c + a))
                b = (b + 11) + c;
            else {
                c = b + b;
                a = 3 + b;
                continue;
            }
        }
        
        return a + b;
    }
    
    public static void functionTestExample() {
        System.out.println("=== FUNCTION test(a,b,c) EXAMPLE ===");
        System.out.println("Given: a = 4, b = 4, c = 7");
        System.out.println("Result: " + test(4, 4, 7));
        System.out.println();
    }
    
    /*
     * ===============================================================================
     * MAIN METHOD - DEMONSTRATE ALL PROGRAMS
     * ===============================================================================
     */
    
    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println("     HOMEWORK 1 - JAVA PROGRAMMING FUNDAMENTALS");
        System.out.println("==============================================================");
        System.out.println();
        
        // Demonstrate all programs
        bitwiseExample();
        whileExample();
        evenNumbersWhile();
        sumFirst10Numbers();
        forExample();
        evenNumbersFor();
        oddNumbersFor();
        factorial();
        sumOfDigits();
        reverseNumber();
        palindrome();
        armstrong();
        example1();
        example2();
        sumProgram();
        whileLoopCountdown();
        functionExample();
        factorialFunctionExample();
        
        // Additional function questions
        exampleFun();
        reverseProcedureExample();
        palindromeFunctionExample();
        functionTestExample();
        
        System.out.println("==============================================================");
        System.out.println("                    PROGRAM COMPLETED");
        System.out.println("==============================================================");
    }
}
