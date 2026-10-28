
import java.util.Scanner;
import java.util.Stack;

/*
 * Book Exercises Problem
 * 
 * Problem Statement:
 * Harry keeps a pile of books on the floor, each with a specific number of remaining exercises.
 * He adds books to the pile one by one. When he wants to solve exercises, he must pick the book
 * with the minimum remaining exercises. If multiple books have the same minimum, he picks the one
 * requiring the least number of books to remove from the top of the pile.
 * 
 * Input:
 * First line: N (number of actions)
 * Next N lines: Either "-1" (do book exercise) or "exercise_count book_name" (add book)
 * 
 * Output:
 * For each "-1" action, output: "books_removed book_name"
 * 
 * Algorithm:
 * Use two stacks:
 * 1. Main Stack: Stores all books (exercise count and name)
 * 2. Min Stack: Keeps track of minimum exercise book at each level
 * 
 * When adding a book:
 * - Push to main stack
 * - If exercise <= current min, push book to min stack
 * - Else, push current min again to min stack
 * 
 * When -1 encountered:
 * - Find minimum exercise from min stack top
 * - Pop books until we find the minimum book
 * - Count pops = books removed
 * - Output count and book name
 */

public class BookExercises {
    
    static class Book {
        int exercises;
        String name;
        
        Book(int exercises, String name) {
            this.exercises = exercises;
            this.name = name;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        
        int N = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Stack<Book> mainStack = new Stack<>();
        
        for (int i = 0; i < N; i++) {
            if (!scanner.hasNextLine()) break;
            
            String line = scanner.nextLine().trim();
            
            if (line.equals("-1")) {
                // Handle book exercise request
                if (mainStack.isEmpty()) {
                    continue;
                }
                
                // Find the minimum exercise book
                Book minBook = findMinimumBook(mainStack);
                int booksRemoved = 0;
                Stack<Book> tempStack = new Stack<>();
                
                // Pop books until we find the minimum book
                while (!mainStack.isEmpty()) {
                    Book current = mainStack.pop();
                    booksRemoved++;
                    
                    if (current.exercises == minBook.exercises && 
                        current.name.equals(minBook.name)) {
                        // Found the target book
                        System.out.println((booksRemoved - 1) + " " + current.name);
                        break;
                    } else {
                        // Store temporarily to push back later
                        tempStack.push(current);
                    }
                }
                
                // Push back the remaining books
                while (!tempStack.isEmpty()) {
                    mainStack.push(tempStack.pop());
                }
                
            } else {
                // Add new book to pile
                String[] parts = line.split(" ", 2);
                if (parts.length >= 2) {
                    int exercises = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    
                    Book newBook = new Book(exercises, name);
                    mainStack.push(newBook);
                }
            }
        }
        
        scanner.close();
    }
    
    // Helper method to find the minimum exercise book in the stack
    private static Book findMinimumBook(Stack<Book> stack) {
        Book minBook = null;
        int minExercises = Integer.MAX_VALUE;
        
        // Create a temporary stack to preserve order
        Stack<Book> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            Book book = stack.pop();
            tempStack.push(book);
            
            if (book.exercises < minExercises) {
                minExercises = book.exercises;
                minBook = book;
            } else if (book.exercises == minExercises) {
                // If same exercises, prefer the one that appears first (from bottom)
                // Since we're popping from top, the first encountered is actually from top
                // So we keep the current minBook as it represents the lower position
            }
        }
        
        // Restore the original stack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
        
        return minBook;
    }
}
