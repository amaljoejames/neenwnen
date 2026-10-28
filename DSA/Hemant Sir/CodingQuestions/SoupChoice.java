import java.util.*;
public class SoupChoice {
    /*
     * ## Q.7 Cafeteria Menu Program
     * 
     * ### Question
     * 
     * Write a Java program to take input for **main menu** and **sub menu** and
     * display the selected item name.
     * 
     * ### Menu Details
     * 
     * #### Soups
     * 
     * 1. Hot and Sour Soup
     * 2. Veg Corn Soup
     * 3. Tomato Soup
     * 4. Spicy Tomato Soup
     * 
     * #### Beverages
     * 
     * 1. Hot Chocolate Drink
     * 2. Badam Drink
     * 3. Badam-Pista Drink
     * 
     * ### Input Format
     * 
     * Enter first letter for main menu:
     * 
     * `S` → Soups
     * `B` → Beverages
     * Enter submenu number.
     * 
     * ---
     * 
     * ### Output Format
     * 
     * Display:
     * 
     * ```text
     * Welcome to CCD
     * Enjoy your <selected item>
     * ```
     * 
     * ---
     * 
     * # Approach
     * 
     * 1. Take main menu input (`S` or `B`)
     * 2. Take submenu number
     * 3. Use outer `switch` for main menu
     * 4. Use inner `switch` for submenu
     * 5. Print selected item name
     * 
     * Example 1
     * 
     * ### Input
     * 
     * ```text
     * S
     * 2
     * ```
     * 
     * ### Output
     * 
     *
     * Welcome to CCD
     * Enjoy your Veg Corn Soup
     * ```
     * 
     * ---
     * 
     * # Example 2
     * 
     * ### Input
     * 
     * ```text
     * B
     * 3
     * ```
     * 
     * ### Output
     * 
     * ```text
     * Welcome to CCD
     * Enjoy your Badam-Pista Drink
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char menu = sc.next().charAt(0);
        int choice = sc.nextInt();

        System.out.println("Welcome to CCD");
        switch (menu) {
            case 'S':

                switch (choice) {

                    case 1:
                        System.out.println("Enjoy your Hot and Sour Soup");
                        break;

                    case 2:
                        System.out.println("Enjoy your Veg Corn Soup");
                        break;

                    case 3:
                        System.out.println("Enjoy your Tomato Soup");
                        break;

                    case 4:
                        System.out.println("Enjoy your Spicy Tomato Soup");
                        break;

                    default:
                        System.out.println("Invalid Soup Choice");
                }

                break;
            case 'B':

                switch (choice) {

                    case 1:
                        System.out.println("Enjoy your Hot Chocolate Drink");
                        break;

                    case 2:
                        System.out.println("Enjoy your Badam Drink");
                        break;

                    case 3:
                        System.out.println("Enjoy your Badam-Pista Drink");
                        break;

                    default:
                        System.out.println("Invalid Beverage Choice");
                }

                break;

            default:
                System.out.println("Invalid Main Menu Choice");
        }

        sc.close();
    }

}
