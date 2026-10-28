import java.util.*;

/**
 * QUESTION: Pair Sum Problem - Given an array of integers and a target sum,
 * determine if there exists a pair of numbers that add up to the target.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Array: [11, 15, 6, 8, 9, 10], target = 16
 * - Possible pairs: (11,5), (6,10), (8,8), (15,1), etc.
 * - Valid pair that sums to 16: (6, 10)
 * - Answer: true
 * 
 * APPROACH USED: Two Pointer Approach (after sorting)
 * 
 * PSEUDOCODE:
 * function pairSum1(list, target):
 *   Collections.sort(list)  // Sort the array
 *   lp = 0  // left pointer
 *   rp = list.size() - 1  // right pointer
 *   
 *   while lp != rp:
 *     sum = list.get(lp) + list.get(rp)
 *     if sum == target:
 *       return true
 *     else if sum < target:
 *       lp++  // Need larger sum, move left pointer right
 *     else:
 *       rp--  // Need smaller sum, move right pointer left
 *   
 *   return false
 * 
 * EXPLANATION OF STEPS:
 * 1. Sort the array to enable two-pointer technique
 * 2. Initialize pointers at both ends
 * 3. Calculate sum of elements at pointers
 * 4. Compare with target:
 *    - If equal → pair found
 *    - If sum < target → move left pointer right (increase sum)
 *    - If sum > target → move right pointer left (decrease sum)
 * 5. Continue until pointers meet or pair found
 * 
 * DRY RUN for array = [11, 15, 6, 8, 9, 10], target = 16:
 * 
 * After sorting: [6, 8, 9, 10, 11, 15]
 * Initial: lp = 0, rp = 5, sum = 6 + 15 = 21
 * 
 * Iteration 1:
 * - sum = 21 > target (16), so rp--
 * - New sum = 6 + 11 = 17
 * 
 * Iteration 2:
 * - sum = 17 > target (16), so rp--
 * - New sum = 6 + 10 = 16
 * 
 * Iteration 3:
 * - sum = 16 == target (16), return true
 * 
 * Pair found: (6, 10)
 */
public class PairSum {
    
    // PAIR SUM-1 2pointers approach
    public static boolean pairSum1(ArrayList<Integer> list, int target) {
        Collections.sort(list);
        int lp = 0; // left pointer
        int rp = list.size() - 1; // right pointer
        
        while (lp != rp) {
            if (list.get(lp) + list.get(rp) == target) {
                return true;
            }
            if (list.get(lp) + list.get(rp) < target) {
                lp++;
            } else {
                rp--;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: array = [11, 15, 6, 8, 9, 10], target = 16
         * Expected result: true
         * 
         * Step-by-step process:
         * 
         * After sorting: [6, 8, 9, 10, 11, 15]
         * Initial: lp=0, rp=5, sum=6+15=21
         * 
         * Iteration 1: sum=21>16, rp-- → sum=6+11=17
         * Iteration 2: sum=17>16, rp-- → sum=6+10=16
         * Iteration 3: sum=16==16 → return true
         * 
         * Pair found: (6, 10)
         */
        
        // Create an ArrayList to store numbers
        ArrayList<Integer> height = new ArrayList<>();
        height.add(11);
        height.add(15);
        height.add(6);
        height.add(8);
        height.add(9);
        height.add(10);
        
        int target = 16;
        
        System.out.println("Pair Sum Problem");
        System.out.println("Array: " + height);
        System.out.println("Target: " + target);
        System.out.println("Result: " + pairSum1(height, target));
        
        // Show step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("After sorting: " + height);
        
        ArrayList<Integer> sorted = new ArrayList<>(height);
        Collections.sort(sorted);
        
        int lp = 0, rp = sorted.size() - 1;
        int iteration = 1;
        
        while (lp != rp) {
            int sum = sorted.get(lp) + sorted.get(rp);
            System.out.println("Iteration " + iteration + ": lp=" + lp + ", rp=" + rp + 
                             ", sum=" + sum + " (elements: " + sorted.get(lp) + " + " + sorted.get(rp) + ")");
            
            if (sum == target) {
                System.out.println("  Sum equals target! Pair found: (" + sorted.get(lp) + ", " + sorted.get(rp) + ")");
                break;
            } else if (sum < target) {
                lp++;
                System.out.println("  Sum < target, moving lp++ to " + lp);
            } else {
                rp--;
                System.out.println("  Sum > target, moving rp-- to " + rp);
            }
            iteration++;
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        ArrayList<Integer> test1 = new ArrayList<>(Arrays.asList(2, 7, 11, 15));
        System.out.println("Test 1: " + test1 + ", target=9 → " + pairSum1(test1, 9));
        
        ArrayList<Integer> test2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Test 2: " + test2 + ", target=9 → " + pairSum1(test2, 9));
        
        ArrayList<Integer> test3 = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        System.out.println("Test 3: " + test3 + ", target=10 → " + pairSum1(test3, 10));
        
        ArrayList<Integer> test4 = new ArrayList<>(Arrays.asList(10, 20, 30));
        System.out.println("Test 4: " + test4 + ", target=25 → " + pairSum1(test4, 25));
    }
}
