public class CalculateVelocity {
    /*
    QUESTION: Find Initial Velocity
    Equation: v = u + a*t
    Given: final velocity (v), acceleration (a), time interval (t)
    Find: initial velocity (u)
    
    INPUT FORMAT:
    Single line containing v, a, t respectively
    
    OUTPUT FORMAT:
    Print u (initial velocity)
    
    CONSTRAINTS:
    -1000 <= v <= 1000
    -100 <= a <= 100
    1 <= t <= 100
    
    EXAMPLE:
    Input: 50 5 8
    Output: 10
    Explanation: u = v - a*t = 50 - 5*8 = 50 - 40 = 10
     */
    
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        System.out.print("Enter final velocity (v): ");
        int v = scanner.nextInt();
        
        System.out.print("Enter acceleration (a): ");
        int a = scanner.nextInt();
        
        System.out.print("Enter time interval (t): ");
        int t = scanner.nextInt();
        int u = v - (a * t);
        
        System.out.println("Initial velocity (u): " + u);
        
        scanner.close();
    }
}

