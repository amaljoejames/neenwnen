import java.util.*;

/**
 * QUESTION: Container With Most Water - Given an array of heights representing vertical lines,
 * find two lines that together with the x-axis form a container that holds the most water.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Heights: [11, 15, 6, 8, 9, 10]
 * - Lines at positions 0 and 5 (heights 11 and 10) form container
 * - Width = 5 - 0 = 5
 * - Height = min(11, 10) = 10
 * - Area = 5 × 10 = 50
 * - Try other combinations:
 *   - Lines 1 and 4: width 3, height min(15, 9) = 9, area = 27
 *   - Lines 2 and 5: width 3, height min(6, 10) = 6, area = 18
 * - Maximum area = 50
 * 
 * APPROACH USED: Two Pointer Approach
 * 
 * PSEUDOCODE:
 * function maxWater(height):
 *   lp = 0  // left pointer
 *   rp = height.size() - 1  // right pointer
 *   maxWater = 0
 *   
 *   while lp < rp:
 *     ht = min(height.get(lp), height.get(rp))  // Current height
 *     width = rp - lp  // Current width
 *     currWater = ht * width  // Current area
 *     maxWater = max(maxWater, currWater)  // Update maximum
 *     
 *     if height.get(lp) < height.get(rp):
 *       lp++  // Move left pointer
 *     else:
 *       rp--  // Move right pointer
 *   
 *   return maxWater
 * 
 * EXPLANATION OF STEPS:
 * 1. Initialize two pointers at both ends of the array
 * 2. Calculate current water area using min height and width
 * 3. Update maximum area found so far
 * 4. Move the pointer pointing to shorter line inward
 * 5. Continue until pointers meet
 * 6. Return maximum area
 * 
 * DRY RUN for heights = [11, 15, 6, 8, 9, 10]:
 * 
 * Initial: lp = 0, rp = 5, maxWater = 0
 * 
 * Iteration 1:
 * - lp = 0, rp = 5
 * - ht = min(11, 10) = 10
 * - width = 5 - 0 = 5
 * - currWater = 10 × 5 = 50
 * - maxWater = max(0, 50) = 50
 * - height[0] (11) < height[5] (10), so lp++
 * 
 * Iteration 2:
 * - lp = 1, rp = 5
 * - ht = min(15, 10) = 10
 * - width = 5 - 1 = 4
 * - currWater = 10 × 4 = 40
 * - maxWater = max(50, 40) = 50
 * - height[1] (15) > height[5] (10), so rp--
 * 
 * Iteration 3:
 * - lp = 1, rp = 4
 * - ht = min(15, 9) = 9
 * - width = 4 - 1 = 3
 * - currWater = 9 × 3 = 27
 * - maxWater = max(50, 27) = 50
 * - height[1] (15) > height[4] (9), so rp--
 * 
 * Continue until lp >= rp
 * Final answer: maxWater = 50
 */
public class MaxWaterContainer {
    
    public static int maxWater(ArrayList<Integer> height) {
        int lp = 0; // left pointer
        int rp = height.size() - 1; // right pointer
        int maxWater = 0;

        while (lp < rp) {
            // Calculate water area
            int ht = Math.min(height.get(lp), height.get(rp));
            int width = rp - lp;
            int currWater = ht * width;

            // Update maxWater with maximum area found so far
            maxWater = Math.max(maxWater, currWater);

            // Update pointers
            if (height.get(lp) < height.get(rp)) {
                lp++;
            } else {
                rp--;
            }
        }

        return maxWater; // Return maximum water area
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: heights = [11, 15, 6, 8, 9, 10]
         * Expected maximum water: 50
         * 
         * Container formed by lines at positions 0 and 5:
         * - Width = 5 - 0 = 5
         * - Height = min(11, 10) = 10
         * - Area = 5 × 10 = 50
         * 
         * Step-by-step pointer movement:
         * 1. lp=0, rp=5 → area=50, maxWater=50, lp++
         * 2. lp=1, rp=5 → area=40, maxWater=50, rp--
         * 3. lp=1, rp=4 → area=27, maxWater=50, rp--
         * Continue until pointers meet
         * Final result: 50
         */
        
        // Create an ArrayList to store heights
        ArrayList<Integer> height = new ArrayList<>();
        height.add(11);
        height.add(15);
        height.add(6);
        height.add(8);
        height.add(9);
        height.add(10);
        
        System.out.println("Container With Most Water Problem");
        System.out.println("Heights: " + height);
        System.out.println("Maximum water that can be stored: " + maxWater(height));
        
        // Show step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("Initial: lp=0, rp=" + (height.size()-1) + ", maxWater=0");
        
        int lp = 0, rp = height.size() - 1, maxWater = 0;
        int iteration = 1;
        
        while (lp < rp) {
            int ht = Math.min(height.get(lp), height.get(rp));
            int width = rp - lp;
            int currWater = ht * width;
            maxWater = Math.max(maxWater, currWater);
            
            System.out.println("Iteration " + iteration + ": lp=" + lp + ", rp=" + rp + 
                             ", ht=" + ht + ", width=" + width + 
                             ", currWater=" + currWater + ", maxWater=" + maxWater);
            
            if (height.get(lp) < height.get(rp)) {
                lp++;
                System.out.println("  height[" + lp + "](" + height.get(lp) + ") < height[" + rp + "](" + height.get(rp) + "), so lp++");
            } else {
                rp--;
                System.out.println("  height[" + lp + "](" + height.get(lp) + ") > height[" + rp + "](" + height.get(rp) + "), so rp--");
            }
            iteration++;
        }
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        ArrayList<Integer> test1 = new ArrayList<>(Arrays.asList(1, 8, 6, 2, 5, 4, 8, 3, 7));
        System.out.println("Test 1: " + test1 + " → Max water: " + maxWater(test1));
        
        ArrayList<Integer> test2 = new ArrayList<>(Arrays.asList(1, 1));
        System.out.println("Test 2: " + test2 + " → Max water: " + maxWater(test2));
        
        ArrayList<Integer> test3 = new ArrayList<>(Arrays.asList(4, 3, 2, 1, 4));
        System.out.println("Test 3: " + test3 + " → Max water: " + maxWater(test3));
    }
}
