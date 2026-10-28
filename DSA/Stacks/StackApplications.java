import java.util.*;

/**
 * QUESTION: Stack Applications - Implement advanced stack algorithms including
 * stock span problem, next greater element, valid parentheses, and duplicate parentheses.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Stock Span Problem:
 * For each day's stock price, find how many consecutive days price was <= current.
 * Use stack to track previous higher prices and calculate spans efficiently.
 * 
 * 2. Next Greater Element:
 * For each element, find next element that is greater than current.
 * Process array from right to left using stack for O(n) solution.
 * 
 * 3. Valid Parentheses:
 * Check if string of brackets is properly balanced.
 * Use stack to match opening and closing brackets.
 * 
 * 4. Duplicate Parentheses:
 * Check if there are unnecessary parentheses in expression.
 * Use stack to detect if any parentheses are empty.
 * 
 * 5. Maximum Area in Histogram:
 * Find largest rectangular area in histogram using stack.
 * Calculate area using nearest smaller to left and right for each bar.
 * 
 * APPROACH USED: Stack-based Algorithms for Efficient Solutions
 * 
 * PSEUDOCODE:
 * 
 * Stock Span:
 * function stockSpan(stocks, span):
 *   s = Stack()
 *   span[0] = 1
 *   s.push(0)
 *   
 *   for i = 1 to n-1:
 *     currPrice = stocks[i]
 *     while !s.isEmpty() && stocks[s.peek()] < currPrice:
 *       s.pop()
 *     
 *     if s.isEmpty: span[i] = i + 1
 *     else: span[i] = i - s.peek()
 *     s.push(i)
 * 
 * Next Greater Element:
 * function nextGreater(arr, nextGreater):
 *   s = Stack()
 *   
 *   for i = n-1 to 0:
 *     while !s.isEmpty() && arr[s.peek()] <= arr[i]:
 *       s.pop()
 *     
 *     if s.isEmpty: nextGreater[i] = -1
 *     else: nextGreater[i] = arr[s.peek()]
 *     s.push(i)
 * 
 * Valid Parentheses:
 * function isValid(str):
 *   s = Stack()
 *   
 *   for each char in str:
 *     if char is opening: s.push(char)
 *     else:
 *       if s.isEmpty: return false
 *       if !isMatching(s.peek(), char): return false
 *       s.pop()
 *   
 *   return s.isEmpty()
 * 
 * Duplicate Parentheses:
 * function duplicateParentheses(str):
 *   s = Stack()
 *   
 *   for each char in str:
 *     if char != ')': s.push(char)
 *     else:
 *       count = 0
 *       while s.peek() != '(':
 *         s.pop()
 *         count++
 *       if count < 1: return true
 *       s.pop()
 *   
 *   return false
 * 
 * Max Area Histogram:
 * function maxArea(arr):
 *   nsr = nextSmallerRight(arr)
 *   nsl = nextSmallerLeft(arr)
 *   maxArea = 0
 *   
 *   for i = 0 to n-1:
 *     height = arr[i]
 *     width = nsr[i] - nsl[i] - 1
 *     currArea = height * width
 *     maxArea = max(maxArea, currArea)
 *   
 *   return maxArea
 * 
 * EXPLANATION OF STEPS:
 * 1. Stock Span: Track indices of higher prices for span calculation
 * 2. Next Greater: Process from right to left, maintain decreasing stack
 * 3. Valid Parentheses: Match pairs using stack LIFO property
 * 4. Duplicate Parentheses: Check for empty parentheses
 * 5. Max Area: Find boundaries for each bar's maximum rectangle
 * 
 * DRY RUN for Stock Span:
 * 
 * Stocks: [100,80,60,70,60,85,100]
 * 
 * Process:
 * span[0] = 1, s = [0]
 * i=1: 80 < 100, span[1] = 1, s = [0,1]
 * i=2: 60 < 80, span[2] = 1, s = [0,1,2]
 * i=3: 70 > 60, pop 2, 70 < 80, span[3] = 1, s = [0,1,3]
 * i=4: 60 < 70, span[4] = 1, s = [0,1,3,4]
 * i=5: 85 > 60, pop 4,3,1, 85 < 100, span[5] = 5, s = [0,5]
 * i=6: 100 > 85, pop 5, 100 >= 100, span[6] = 2, s = [0,6]
 * 
 * Output: [1,1,1,2,1,5,2]
 * 
 * DRY RUN for Next Greater Element:
 * 
 * Array: [2,1,5,6,2,3]
 * 
 * Process (right to left):
 * i=5: s = [], nextGreater[5] = -1, s = [5]
 * i=4: 2 < 3, nextGreater[4] = 3, s = [5,4]
 * i=3: 6 > 3, pop 4, pop 5, nextGreater[3] = -1, s = [3]
 * i=2: 5 < 6, nextGreater[2] = 6, s = [3,2]
 * i=1: 1 < 5, nextGreater[1] = 5, s = [3,2,1]
 * i=0: 2 < 5, nextGreater[0] = 5, s = [3,2,1,0]
 * 
 * Output: [5,5,6,-1,3,-1]
 * 
 * DRY RUN for Valid Parentheses:
 * 
 * String: "({[]})"
 * 
 * Process:
 * '(', '{', '[', ']', '}', ')'
 * Stack: ['(', '{', '['], then match ']', then '}', then ')'
 * 
 * Final stack: empty -> valid
 * 
 * DRY RUN for Max Area Histogram:
 * 
 * Heights: [2,1,5,6,2,3]
 * 
 * Process:
 * For each bar, find left and right boundaries
 * Area calculations using width * height
 * 
 * Maximum area: 10 (bars 5 and 6)
 */
public class StackApplications {
    
    // Stock Span Problem (Amazon, Adobe, Flipkart, Microsoft, Samsung)
    public static void stockSpan(int[] stocks, int[] span) {
        Stack<Integer> s = new Stack<>();
        span[0] = 1;
        s.push(0);
        
        for (int i = 1; i < stocks.length; i++) {
            int currPrice = stocks[i];
            while (!s.isEmpty() && stocks[s.peek()] < currPrice) {
                s.pop();
            }
            
            if (s.isEmpty()) {
                span[i] = i + 1;
            } else {
                int prevHigh = s.peek();
                span[i] = i - prevHigh;
            }
            s.push(i);
        }
    }

    // Next Greater Element Approach
    public static void nextGreaterElement(int[] arr, int[] nextGreater, Stack<Integer> s) {
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && arr[s.peek()] <= arr[i]) {
                s.pop();
            }
            
            if (s.isEmpty()) {
                nextGreater[i] = -1;
            } else {
                nextGreater[i] = arr[s.peek()];
            }
            s.push(i);
        }
        
        for (int i = 0; i < nextGreater.length; i++) {
            System.out.print(nextGreater[i] + " ");
        }
        System.out.println();
    }

    // Valid Parentheses
    public static boolean isValid(String str) {
        Stack<Character> s = new Stack<>();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (ch == '(' || ch == '{' || ch == '[') {
                s.push(ch);
            } else {
                if (s.isEmpty()) {
                    return false;
                }
                if ((s.peek() == '(' && ch == ')') ||
                    (s.peek() == '{' && ch == '}') ||
                    (s.peek() == '[' && ch == ']')) {
                    s.pop();
                } else {
                    return false;
                }
            }
        }
        
        return s.isEmpty();
    }

    // Duplicate Parentheses (Google, Microsoft, Amazon)
    public static boolean duplicateParentheses(String str) {
        Stack<Character> s = new Stack<>();
        
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (ch == ')') {
                int count = 0;
                while (s.peek() != '(') {
                    s.pop();
                    count++;
                }
                if (count < 1) {
                    return true;
                } else {
                    s.pop();
                }
            } else {
                s.push(ch);
            }
        }
        return false;
    }

    // Maximum Area in Histogram (Microsoft, Paytm, Facebook)
    public static void maxArea(int[] arr) {
        int maxArea = 0;
        int[] nsr = new int[arr.length];
        int[] nsl = new int[arr.length];
        
        // Next Smaller Right
        Stack<Integer> s = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && arr[s.peek()] >= arr[i]) {
                s.pop();
            }
            
            if (s.isEmpty()) {
                nsr[i] = arr.length;
            } else {
                nsr[i] = s.peek();
            }
            s.push(i);
        }
        
        // Next Smaller Left
        s = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!s.isEmpty() && arr[s.peek()] >= arr[i]) {
                s.pop();
            }
            
            if (s.isEmpty()) {
                nsl[i] = -1;
            } else {
                nsl[i] = s.peek();
            }
            s.push(i);
        }
        
        // Calculate maximum area
        for (int i = 0; i < arr.length; i++) {
            int height = arr[i];
            int width = nsr[i] - nsl[i] - 1;
            int currArea = height * width;
            maxArea = Math.max(maxArea, currArea);
        }
        
        System.out.println(maxArea);
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all stack applications
         * 
         * Stock Span: [100,80,60,70,60,85,100]
         * Expected: [1,1,1,2,1,5,2]
         * 
         * Next Greater: [2,1,5,6,2,3]
         * Expected: [5,5,6,-1,3,-1]
         * 
         * Valid Parentheses: "({[]})"
         * Expected: true
         * 
         * Duplicate Parentheses: "((a+b))"
         * Expected: true
         * 
         * Max Area: [2,1,5,6,2,3]
         * Expected: 10
         */
        
        // Test Stock Span
        System.out.println("Stack Applications - Stock Span Problem");
        int[] stocks = {100, 80, 60, 70, 60, 85, 100};
        int[] span = new int[stocks.length];
        System.out.println("Stocks: [100,80,60,70,60,85,100]");
        System.out.println("Expected span: [1,1,1,2,1,5,2]");
        stockSpan(stocks, span);
        System.out.print("Actual span: ");
        for (int i = 0; i < span.length; i++) {
            System.out.print(span[i] + " ");
        }
        System.out.println("\n");
        
        // Test Next Greater Element
        System.out.println("Next Greater Element:");
        int[] arr = {2, 1, 5, 6, 2, 3};
        int[] nextGreater = new int[arr.length];
        System.out.println("Array: [2,1,5,6,2,3]");
        System.out.println("Expected: [5,5,6,-1,3,-1]");
        System.out.print("Actual: ");
        nextGreaterElement(arr, nextGreater, new Stack<>());
        
        // Test Valid Parentheses
        System.out.println("\nValid Parentheses:");
        String validStr = "({[]})";
        System.out.println("String: " + validStr);
        System.out.println("Expected: true");
        boolean isValidResult = isValid(validStr);
        System.out.println("Actual: " + isValidResult);
        
        // Test Duplicate Parentheses
        System.out.println("\nDuplicate Parentheses:");
        String dupStr = "((a+b))";
        System.out.println("String: " + dupStr);
        System.out.println("Expected: true");
        boolean isDuplicate = duplicateParentheses(dupStr);
        System.out.println("Actual: " + isDuplicate);
        
        // Test Max Area Histogram
        System.out.println("\nMaximum Area in Histogram:");
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Heights: [2,1,5,6,2,3]");
        System.out.println("Expected: 10");
        System.out.print("Actual: ");
        maxArea(heights);
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Invalid parentheses
        System.out.println("\nInvalid parentheses:");
        String invalidStr = "([)]";
        System.out.println("String: " + invalidStr);
        System.out.println("Expected: false");
        boolean invalidResult = isValid(invalidStr);
        System.out.println("Actual: " + invalidResult);
        
        // Test case 3: No duplicate parentheses
        System.out.println("\nNo duplicate parentheses:");
        String noDupStr = "(a+b)*(c+d)";
        System.out.println("String: " + noDupStr);
        System.out.println("Expected: false");
        boolean noDupResult = duplicateParentheses(noDupStr);
        System.out.println("Actual: " + noDupResult);
        
        // Test case 4: Single bar histogram
        System.out.println("\nSingle bar histogram:");
        int[] singleBar = {5};
        System.out.println("Heights: [5]");
        System.out.println("Expected: 5");
        System.out.print("Actual: ");
        maxArea(singleBar);
        
        // Test case 5: All decreasing stock prices
        System.out.println("\nAll decreasing stock prices:");
        int[] decreasingStocks = {100, 90, 80, 70};
        int[] decreasingSpan = new int[decreasingStocks.length];
        System.out.println("Stocks: [100,90,80,70]");
        System.out.println("Expected: [1,1,1,1]");
        stockSpan(decreasingStocks, decreasingSpan);
        System.out.print("Actual: ");
        for (int i = 0; i < decreasingSpan.length; i++) {
            System.out.print(decreasingSpan[i] + " ");
        }
        System.out.println();
    }
}
