/**
 * QUESTION: Recursion Basics - Implement fundamental recursion concepts including
 * printing sequences, factorial, sum, Fibonacci, and array operations.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Print Decreasing/Increasing Numbers:
 * Print numbers from n to 1 (decreasing) and 1 to n (increasing) using recursion.
 * Base case when n reaches 1, recursive call with n-1.
 * 
 * 2. Factorial:
 * Calculate factorial of n using recursion.
 * Base case: 0! = 1, Recursive case: n! = n * (n-1)!
 * 
 * 3. Sum of Natural Numbers:
 * Calculate sum of first n natural numbers using recursion.
 * Base case: sum(1) = 1, Recursive case: sum(n) = n + sum(n-1)
 * 
 * 4. Fibonacci Series:
 * Generate nth Fibonacci number using recursion.
 * Base cases: fib(0) = 0, fib(1) = 1, Recursive case: fib(n) = fib(n-1) + fib(n-2)
 * 
 * 5. Array Operations:
 * Check if array is sorted, find first/last occurrences using recursion.
 * 
 * APPROACH USED: Divide and Conquer with Base Cases
 * 
 * PSEUDOCODE:
 * 
 * Print Decreasing:
 * function printDec(n):
 *   if n == 1: print 1, return
 *   print n + " "
 *   return printDec(n-1)
 * 
 * Print Increasing:
 * function printInc(n):
 *   if n == 1: print 1 + " ", return
 *   printInc(n-1)
 *   print n + " "
 *   return n
 * 
 * Factorial:
 * function fact(n):
 *   if n == 0: return 1
 *   return n * fact(n-1)
 * 
 * Sum Natural:
 * function sumNat(n):
 *   if n == 1: return 1
 *   return n + sumNat(n-1)
 * 
 * Fibonacci:
 * function fib(n):
 *   if n == 0 || n == 1: return n
 *   return fib(n-1) + fib(n-2)
 * 
 * Array Sorted Check:
 * function arrSort(arr, i):
 *   if i == arr.length-1: return true
 *   if arr[i] > arr[i+1]: return false
 *   return arrSort(arr, i+1)
 * 
 * EXPLANATION OF STEPS:
 * 1. Identify base case (smallest problem)
 * 2. Define recursive relation
 * 3. Ensure progress towards base case
 * 4. Handle edge cases properly
 * 
 * DRY RUN for Print Decreasing:
 * 
 * n = 5
 * 
 * Process:
 * printDec(5): print 5, call printDec(4)
 * printDec(4): print 4, call printDec(3)
 * printDec(3): print 3, call printDec(2)
 * printDec(2): print 2, call printDec(1)
 * printDec(1): print 1, return
 * 
 * Output: 5 4 3 2 1
 * 
 * DRY RUN for Factorial:
 * 
 * fact(4)
 * 
 * Process:
 * fact(4) = 4 * fact(3)
 * fact(3) = 3 * fact(2)
 * fact(2) = 2 * fact(1)
 * fact(1) = 1 * fact(0)
 * fact(0) = 1
 * 
 * Back substitution:
 * fact(1) = 1 * 1 = 1
 * fact(2) = 2 * 1 = 2
 * fact(3) = 3 * 2 = 6
 * fact(4) = 4 * 6 = 24
 * 
 * DRY RUN for Fibonacci:
 * 
 * fib(4)
 * 
 * Process:
 * fib(4) = fib(3) + fib(2)
 * fib(3) = fib(2) + fib(1)
 * fib(2) = fib(1) + fib(0)
 * fib(1) = 1, fib(0) = 0
 * 
 * Back substitution:
 * fib(2) = 1 + 0 = 1
 * fib(3) = 1 + 1 = 2
 * fib(4) = 2 + 1 = 3
 */
public class RecursionBasics {
    
    // Print numbers in decreasing order
    public static int printDec(int n) {
        if (n == 1) {
            System.out.print(n);
            return n;
        }
        System.out.print(n + " ");
        return printDec(n - 1);
    }

    // Print numbers in increasing order
    public static int printInc(int n) {
        if (n == 1) {
            System.out.print(1 + " ");
            return 1;
        }
        printInc(n - 1);
        System.out.print(n + " ");
        return n;
    }

    // Factorial of a number
    public static int fact(int n) {
        if (n == 0) {
            return 1;
        }
        return n * fact(n - 1);
    }

    // Sum of n natural numbers
    public static int sumNat(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sumNat(n - 1);
    }

    // Fibonacci number
    public static int fib(int n) {
        if (n == 1 || n == 0) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    // Check if array is sorted
    public static boolean arrSort(int arr[], int i) {
        if (i == arr.length - 1) {
            return true;
        }
        if (arr[i] > arr[i + 1]) {
            return false;
        }
        return arrSort(arr, i + 1);
    }

    // First occurrence of element in array
    public static int firstOccurrence(int arr[], int i, int key) {
        if (i == arr.length) {
            return -1;
        }
        if (arr[i] == key) {
            return i;
        }
        return firstOccurrence(arr, i + 1, key);
    }

    // Last occurrence of element in array
    public static int lastOccurrence(int arr[], int key, int i) {
        if (i == arr.length) {
            return -1;
        }
        int isFound = lastOccurrence(arr, key, i + 1);
        if (isFound == -1 && arr[i] == key) {
            return i;
        }
        return isFound;
    }

    // Power function x^n
    public static int power(int x, int n) {
        if (n == 0) {
            return 1;
        }
        return x * power(x, n - 1);
    }

    // Optimized power function O(log n)
    public static int optPower(int x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n % 2 != 0) {
            return x * optPower(x, n / 2) * optPower(x, n / 2);
        }
        return optPower(x, n / 2) * optPower(x, n / 2);
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all basic recursion functions
         * 
         * Print Decreasing: n=5
         * Expected: 5 4 3 2 1
         * 
         * Print Increasing: n=5
         * Expected: 1 2 3 4 5
         * 
         * Factorial: n=5
         * Expected: 120
         * 
         * Sum Natural: n=5
         * Expected: 15
         * 
         * Fibonacci: n=6
         * Expected: 8
         * 
         * Array Sorted: [1,2,3,4,5]
         * Expected: true
         */
        
        // Test Print Decreasing
        System.out.println("Recursion Basics - Print Decreasing");
        System.out.println("n = 5");
        System.out.println("Expected: 5 4 3 2 1");
        System.out.print("Output: ");
        printDec(5);
        System.out.println("\n");
        
        // Test Print Increasing
        System.out.println("Print Increasing:");
        System.out.println("n = 5");
        System.out.println("Expected: 1 2 3 4 5");
        System.out.print("Output: ");
        printInc(5);
        System.out.println("\n");
        
        // Test Factorial
        System.out.println("Factorial:");
        System.out.println("n = 5");
        System.out.println("Expected: 120");
        int factorial = fact(5);
        System.out.println("Output: " + factorial);
        
        // Test Sum Natural
        System.out.println("\nSum Natural:");
        System.out.println("n = 5");
        System.out.println("Expected: 15");
        int sum = sumNat(5);
        System.out.println("Output: " + sum);
        
        // Test Fibonacci
        System.out.println("\nFibonacci:");
        System.out.println("n = 6");
        System.out.println("Expected: 8");
        int fibonacci = fib(6);
        System.out.println("Output: " + fibonacci);
        
        // Test Array Sorted
        System.out.println("\nArray Sorted Check:");
        int[] sortedArr = {1, 2, 3, 4, 5};
        System.out.println("Array: [1,2,3,4,5]");
        System.out.println("Expected: true");
        boolean isSorted = arrSort(sortedArr, 0);
        System.out.println("Output: " + isSorted);
        
        // Test First Occurrence
        System.out.println("\nFirst Occurrence:");
        int[] arr = {1, 2, 3, 4, 5, 3, 6};
        System.out.println("Array: [1,2,3,4,5,3,6], key=3");
        System.out.println("Expected: 2");
        int first = firstOccurrence(arr, 0, 3);
        System.out.println("Output: " + first);
        
        // Test Last Occurrence
        System.out.println("\nLast Occurrence:");
        System.out.println("Array: [1,2,3,4,5,3,6], key=3");
        System.out.println("Expected: 5");
        int last = lastOccurrence(arr, 3, 0);
        System.out.println("Output: " + last);
        
        // Test Power
        System.out.println("\nPower:");
        System.out.println("2^5");
        System.out.println("Expected: 32");
        int powerResult = power(2, 5);
        System.out.println("Output: " + powerResult);
        
        // Test Optimized Power
        System.out.println("\nOptimized Power:");
        System.out.println("2^10");
        System.out.println("Expected: 1024");
        int optPowerResult = optPower(2, 10);
        System.out.println("Output: " + optPowerResult);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Fibonacci with small numbers
        System.out.println("Fibonacci sequence (first 10):");
        for (int i = 0; i < 10; i++) {
            System.out.print(fib(i) + " ");
        }
        System.out.println();
        
        // Test case 3: Unsorted array
        int[] unsortedArr = {1, 3, 2, 4, 5};
        System.out.println("\nUnsorted array check:");
        System.out.println("Array: [1,3,2,4,5]");
        System.out.println("Expected: false");
        boolean notSorted = arrSort(unsortedArr, 0);
        System.out.println("Output: " + notSorted);
    }
}
