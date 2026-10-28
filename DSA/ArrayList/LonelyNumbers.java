import java.util.*;

/**
 * QUESTION: Lonely Numbers in ArrayList - A number x is lonely when it appears only once, 
 * and no adjacent numbers (i.e., x + 1 and x - 1) appear in arraylist.
 * Return all lonely numbers in nums.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [10, 6, 5, 8]
 * - Check each number:
 *   - 10: appears once, check 9 and 11 → neither present → lonely
 *   - 6: appears once, check 5 and 7 → 5 present → not lonely
 *   - 5: appears once, check 4 and 6 → 6 present → not lonely
 *   - 8: appears once, check 7 and 9 → neither present → lonely
 * - Lonely numbers: [10, 8]
 * 
 * APPROACH USED: Sort and Check Adjacent Numbers
 * 
 * PSEUDOCODE:
 * function findLonely(nums):
 *   Collections.sort(nums)  // Sort to easily check adjacent numbers
 *   list = new ArrayList<>()  // Store lonely numbers
 *   
 *   // Check for lonely numbers in middle of array
 *   for i = 1 to nums.size() - 1:
 *     if nums.get(i - 1) + 1 < nums.get(i) && nums.get(i) + 1 < nums.get(i + 1):
 *       list.add(nums.get(i))
 *   
 *   // Handle edge cases
 *   if nums.size() == 1:
 *     list.add(nums.get(0))
 *   
 *   if nums.size() > 1:
 *     // Check first element
 *     if nums.get(0) + 1 < nums.get(1):
 *       list.add(nums.get(0))
 *     // Check last element
 *     if nums.get(nums.size() - 2) + 1 < nums.get(nums.size() - 1):
 *       list.add(nums.get(nums.size() - 1))
 *   
 *   return list
 * 
 * EXPLANATION OF STEPS:
 * 1. Sort the array to group same numbers together
 * 2. Check middle elements for loneliness (no adjacent numbers)
 * 3. Handle edge cases (single element, first and last elements)
 * 4. A number is lonely if:
 *    - It appears only once
 *    - Neither (number-1) nor (number+1) exist in array
 * 5. Collect and return all lonely numbers
 * 
 * DRY RUN for array = [10, 6, 5, 8]:
 * 
 * After sorting: [5, 6, 8, 10]
 * 
 * Check middle elements:
 * - i=1 (value 6): check 5 and 8 → 5 exists → not lonely
 * - i=2 (value 8): check 6 and 10 → neither exists → lonely
 * 
 * Check edges:
 * - First element (5): check if 4 exists → no, add 5
 * - Last element (10): check if 9 exists → no, add 10
 * 
 * Lonely numbers: [5, 8, 10]
 * But wait, 5 is not lonely because 6 exists!
 * Correct lonely numbers: [8, 10]
 */
public class LonelyNumbers {
    
    public static ArrayList<Integer> findLonely(ArrayList<Integer> nums) {
        // Sort input list to easily check adjacent numbers
        Collections.sort(nums);
        
        // Create a new list to store lonely numbers
        ArrayList<Integer> list = new ArrayList<>();
        
        // Check for lonely numbers in middle of array
        for (int i = 1; i < nums.size() - 1; i++) {
            // A number is lonely if it is not adjacent to nums[i-1] or nums[i+1]
            if (nums.get(i - 1) + 1 < nums.get(i) && nums.get(i) + 1 < nums.get(i + 1)) {
                list.add(nums.get(i));
            }
        }

        // If list size is 1, only element is lonely
        if (nums.size() == 1) {
            list.add(nums.get(0));
        }

        // If list size is greater than 1, check first and last elements
        if (nums.size() > 1) {
            // First element is lonely if it doesn't have adjacent numbers
            if (nums.get(0) + 1 < nums.get(1)) {
                list.add(nums.get(0));
            }
            // Last element is lonely if it doesn't have adjacent numbers
            if (nums.get(nums.size() - 2) + 1 < nums.get(nums.size() - 1)) {
                list.add(nums.get(nums.size() - 1));
            }
        }

        return list;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [10, 6, 5, 8]
         * Expected result: [8, 10]
         * 
         * Step-by-step process:
         * 
         * After sorting: [5, 6, 8, 10]
         * 
         * Check middle elements:
         * - i=1 (value 6): check 5 and 8 → 5 exists → not lonely
         * - i=2 (value 8): check 6 and 10 → neither exists → lonely
         * 
         * Check edges:
         * - First element (5): check if 4 exists → no → lonely
         * - Last element (10): check if 9 exists → no → lonely
         * 
         * Lonely numbers found: [5, 8, 10]
         * But wait, 5 is not lonely because 6 exists!
         * Correction: [8, 10]
         */
        
        // Test case 1: Example from problem statement
        ArrayList<Integer> test1 = new ArrayList<>(Arrays.asList(10, 6, 5, 8));
        System.out.println("Test 1: " + test1);
        System.out.println("Lonely numbers: " + findLonely(test1));
        System.out.println("Expected: [8, 10]");
        
        // Show step-by-step process for test 1
        System.out.println("\nStep-by-step process for Test 1:");
        ArrayList<Integer> sorted = new ArrayList<>(test1);
        Collections.sort(sorted);
        System.out.println("After sorting: " + sorted);
        
        System.out.println("Checking middle elements:");
        for (int i = 1; i < sorted.size() - 1; i++) {
            int current = sorted.get(i);
            int prev = sorted.get(i - 1);
            int next = sorted.get(i + 1);
            boolean isLonely = prev + 1 < current && current + 1 < next;
            System.out.println("  Element " + current + ": prev=" + prev + ", next=" + next + 
                             ", lonely=" + isLonely);
        }
        
        System.out.println("Checking edges:");
        if (sorted.size() > 1) {
            int first = sorted.get(0);
            int second = sorted.get(1);
            boolean firstLonely = first + 1 < second;
            System.out.println("  First element " + first + ": next=" + second + ", lonely=" + firstLonely);
            
            int last = sorted.get(sorted.size() - 1);
            int secondLast = sorted.get(sorted.size() - 2);
            boolean lastLonely = secondLast + 1 < last;
            System.out.println("  Last element " + last + ": prev=" + secondLast + ", lonely=" + lastLonely);
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        ArrayList<Integer> test2 = new ArrayList<>(Arrays.asList(1, 3, 5, 3));
        System.out.println("Test 2: " + test2 + " → " + findLonely(test2));
        
        ArrayList<Integer> test3 = new ArrayList<>(Arrays.asList(1));
        System.out.println("Test 3: " + test3 + " → " + findLonely(test3));
        
        ArrayList<Integer> test4 = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
        System.out.println("Test 4: " + test4 + " → " + findLonely(test4));
    }
}
