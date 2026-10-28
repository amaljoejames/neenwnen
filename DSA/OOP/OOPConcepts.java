/**
 * QUESTION: OOP Concepts in Java - Implement fundamental Object-Oriented Programming
 * concepts including Encapsulation, Constructors, Copy Constructors, and Polymorphism.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Encapsulation:
 * Data hiding using private fields with public getter/setter methods.
 * Protects data from unauthorized access and modification.
 * 
 * 2. Constructors:
 * Special methods for object initialization.
 * Types: Default, Parameterized, Copy constructors.
 * 
 * 3. Copy Constructor:
 * Creates new object by copying existing object's properties.
 * Can be shallow copy (references) or deep copy (new objects).
 * 
 * 4. Polymorphism:
 * Method overloading (compile-time) and method overriding (runtime).
 * Same method name with different parameters or behaviors.
 * 
 * APPROACH USED: Object-Oriented Programming Principles
 * 
 * PSEUDOCODE:
 * 
 * Encapsulation:
 * class Pen:
 *   private color, tip
 *   public getColor(), getTip(), setColor(), setTip()
 * 
 * Constructors:
 * class Student:
 *   Student()                    // Default
 *   Student(int age)             // Parameterized
 *   Student(String name)         // Parameterized
 *   Student(Student s)            // Copy constructor
 * 
 * Polymorphism:
 * class Calculator:
 *   int add(int a, int b)         // Method overloading
 *   double add(double a, double b)// Same name, different types
 * 
 * EXPLANATION OF STEPS:
 * 1. Encapsulation: Private fields + public methods for controlled access
 * 2. Constructors: this keyword for field initialization
 * 3. Copy Constructor: Copy properties from source object
 * 4. Polymorphism: Compile-time method overloading with type-specific implementations
 * 
 * DRY RUN for Constructors:
 * 
 * Student s1 = new Student()      // Default constructor called
 * Student s2 = new Student(13)     // Parameterized with age
 * Student s3 = new Student("Amal") // Parameterized with name
 * Student s4 = new Student(s3)     // Copy constructor
 * 
 * Process:
 * s1: name=null, age=0 (default values)
 * s2: name=null, age=13
 * s3: name="Amal", age=0
 * s4: name="Amal", age=0 (copied from s3)
 * 
 * DRY RUN for Polymorphism:
 * 
 * Calculator calc = new Calculator()
 * calc.add(5, 10)        // Calls int version, returns 15
 * calc.add(5.5, 2.3)     // Calls double version, returns 7.8
 * 
 * Method selection based on parameter types at compile time
 */
public class OOPConcepts {
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all OOP concepts
         * 
         * Encapsulation: Pen with color and tip
         * Expected: Blue color, 3 tip
         * 
         * Constructors: Student with different initialization
         * Expected: Default, age=13, name="Amal", copied name="Amal"
         * 
         * Polymorphism: Calculator with overloaded methods
         * Expected: 15 (int), 7.8 (double)
         */
        
        System.out.println("OOP Concepts Demonstration");
        System.out.println("==========================");
        
        // Encapsulation: Using Getters and Setters
        System.out.println("\n1. Encapsulation - Pen Class:");
        Pen p1 = new Pen();
        p1.setColor("Blue");
        p1.setTip(3);
        System.out.println("Expected: Color=Blue, Tip=3");
        System.out.println("Actual - Pen Color: " + p1.getColor());
        System.out.println("Actual - Pen Tip: " + p1.getTip());

        // BankAccount Example
        System.out.println("\n2. Encapsulation - BankAccount Class:");
        BankAccount b1 = new BankAccount();
        b1.username = "Amal";
        b1.setPassword("amal22"); // Private data modified using setter
        System.out.println("Expected: Username=Amal, Password set privately");
        System.out.println("Actual - Username: " + b1.username);
        System.out.println("Actual - Password: [Hidden for security]");

        // Constructor Demonstration
        System.out.println("\n3. Constructors - Student Class:");
        Student s1 = new Student(); // Default Constructor
        Student s2 = new Student(13); // Parameterized Constructor
        System.out.println("Expected: Age=13");
        System.out.println("Actual - Student Age: " + s2.age);
        
        Student s3 = new Student("Amal"); // Parameterized Constructor
        System.out.println("Expected: Name=Amal");
        System.out.println("Actual - Student Name: " + s3.name);

        // Copy Constructor Example
        System.out.println("\n4. Copy Constructor:");
        Student s4 = new Student(s3);
        System.out.println("Expected: Copied Name=Amal");
        System.out.println("Actual - Copied Student Name: " + s4.name);

        // Demonstrating Polymorphism
        System.out.println("\n5. Polymorphism - Calculator Class:");
        Calculator calc = new Calculator();
        System.out.println("Expected: int 5+10=15");
        System.out.println("Actual - Sum (int): " + calc.add(5, 10));
        System.out.println("Expected: double 5.5+2.3=7.8");
        System.out.println("Actual - Sum (double): " + calc.add(5.5, 2.3));
        
        // Additional demonstrations
        System.out.println("\n6. Additional OOP Demonstrations:");
        
        // Test encapsulation with validation
        Pen p2 = new Pen();
        p2.setColor("Red");
        p2.setTip(5);
        System.out.println("Pen 2 - Color: " + p2.getColor() + ", Tip: " + p2.getTip());
        
        // Test multiple constructors
        Student s5 = new Student("John", 25);
        System.out.println("Student with name and age: " + s5.name + ", " + s5.age);
        
        // Test polymorphism with more types
        System.out.println("Calculator string addition: " + calc.add("Hello", "World"));
    }
}

// Encapsulation: Pen Class
class Pen {
    private String color;
    private int tip;

    public String getColor() {
        return this.color;
    }

    public int getTip() {
        return this.tip;
    }

    public void setColor(String newColor) {
        this.color = newColor;
    }

    public void setTip(int newTip) {
        this.tip = newTip;
    }
}

// Encapsulation: BankAccount Class
class BankAccount {
    public String username;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }
}

// Constructor and Copy Constructor: Student Class
class Student {
    String name;
    int age;

    // Default Constructor
    Student() {
        System.out.println("Default Constructor is called");
    }

    // Parameterized Constructor
    Student(int age) {
        this.age = age;
    }

    Student(String name) {
        this.name = name;
    }
    
    // Additional parameterized constructor
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Copy Constructor (Shallow Copy)
    Student(Student s) {
        this.name = s.name;
        this.age = s.age;
    }
}

// Polymorphism: Method Overloading in Calculator
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
    
    String add(String a, String b) {
        return a + b;
    }
    
    // Additional overloaded methods
    int add(int a, int b, int c) {
        return a + b + c;
    }
    
    double add(double a, double b, double c) {
        return a + b + c;
    }
}
