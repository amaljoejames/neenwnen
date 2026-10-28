import java.util.*;

/**
 * QUESTION: Trapped Rainwater - Given an array of heights representing elevation map,
 * calculate how much water can be trapped after raining.
 * 
 * QUESTION EXPLANATION:
 * Example: 
 * - Heights: [5, 2, 4, 5, 6, 7]
 * - At each position, water trapped = min(leftMax, rightMax) - height[i]
 * - Position 0: min(5,7) - 5 = 0
 * - Position 1: min(5,7) - 2 = 3
 * - Position 2: min(5,7) - 4 = 1
 * - Position 3: min(5,7) - 5 = 0
 * - Position 4: min(6,7) - 6 = 0
 * - Position 5: min(7,7) - 7 = 0
 * - Total trapped water: 3 + 1 = 4 units
 * 
 * APPROACH USED: Precompute Left Max and Right Max Arrays
 * 
 * PSEUDOCODE:
 * function trapRainwater(height):
 *   n = height.length
 *   leftMax = new int[n]
 *   rightMax = new int[n]
 *   
 *   // Calculate left max boundary array
 *   leftMax[0] = height[0]
 *   for i = 1 to n-1:
 *     leftMax[i] = max(height[i], leftMax[i-1])
 *   
 *   // Calculate right max boundary array
 *   rightMax[n-1] = height[n-1]
 *   for i = n-2 to 0:
 *     rightMax[i] = max(height[i], rightMax[i+1])
 *   
 *   // Calculate trapped water
 *   trappedWater = 0
 *   for i = 0 to n-1:
 *     waterLevel = min(leftMax[i], rightMax[i])
 *     trappedWater += waterLevel - height[i]
 *   
 *   return trappedWater
 * 
 * EXPLANATION OF STEPS:
 * 1. Create leftMax array: maximum height to left of each position
 * 2. Create rightMax array: maximum height to right of each position
 * 3. For each position, water level = min(leftMax[i], rightMax[i])
 * 4. Water trapped at position = water level - height[i]
 * 5. Sum all trapped water
 * 6. Time complexity: O(n), Space complexity: O(n)
 * 
 * DRY RUN for heights = [5, 2, 4, 5, 6, 7]:
 * 
 * leftMax: [5, 5, 5, 5, 6, 7]
 * rightMax: [7, 7, 7, 7, 7, 7]
 * 
 * Position 0: min(5,7) - 5 = 0
 * Position 1: min(5,7) - 2 = 3
 * Position 2: min(5,7) - 4 = 1
 * Position 3: min(5,7) - 5 = 0
 * Position 4: min(6,7) - 6 = 0
 * Position 5: min(7,7) - 7 = 0
 * 
 * Total trapped water: 4 units
 */
public class TrappedRainwater {
    
    public static int trapRainwater(int height[]) {
        int n = height.length;
        int leftMax[] = new int[n];
        int rightMax[] = new int[n];
        
        // Calculate left max boundary array
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        
        // Calculate right max boundary array
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        
        int trappedWater = 0;
        
        // Calculate trapped water at each position
        for (int i = 0; i < n; i++) {
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            trappedWater += waterLevel - height[i];
        }
        
        return trappedWater;
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test case: heights = [5, 2, 4, 5, 6, 7]
         * Expected result: 4 units of water
         * 
         * Step-by-step process:
         * 
         * leftMax: [5, 5, 5, 5, 6, 7]
         * rightMax: [7, 7, 7, 7, 7, 7]
         * 
         * Position 0: min(5,7) - 5 = 0
         * Position 1: min(5,7) - 2 = 3
         * Position 2: min(5,7) - 4 = 1
         * Position 3: min(5,7) - 5 = 0
         * Position 4: min(6,7) - 6 = 0
         * Position 5: min(7,7) - 7 = 0
         * 
         * Total trapped water: 4 units
         */
        
        int[] arr = {5, 2, 4, 5, 6, 7};
        
        System.out.println("Trapped Rainwater Problem");
        System.out.println("Heights: " + Arrays.toString(arr));
        System.out.println("Expected result: 4 units");
        
        int result = trapRainwater(arr);
        System.out.println("Trapped water: " + result + " units");
        
        // Show detailed step-by-step process
        System.out.println("\nStep-by-step process:");
        System.out.println("Heights: " + Arrays.toString(arr));
        
        int n = arr.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        
        // Calculate left max
        leftMax[0] = arr[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(arr[i], leftMax[i - 1]);
        }
        System.out.println("Left max: " + Arrays.toString(leftMax));
        
        // Calculate right max
        rightMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(arr[i], rightMax[i + 1]);
        }
        System.out.println("Right max: " + Arrays.toString(rightMax));
        
        // Calculate trapped water at each position
        System.out.println("\nWater calculation at each position:");
        int totalWater = 0;
        
        for (int i = 0; i < n; i++) {
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            int waterAtPosition = waterLevel - arr[i];
            totalWater += waterAtPosition;
            
            System.out.println("Position " + i + ": min(" + leftMax[i] + "," + rightMax[i] + ") - " + arr[i] + " = " + waterAtPosition);
        }
        
        System.out.println("Total trapped water: " + totalWater + " units");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        int[] test2 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Test 2: " + Arrays.toString(test2));
        System.out.println("Trapped water: " + trapRainwater(test2) + " units");
        
        int[] test3 = {4, 2, 0, 3, 2, 5};
        System.out.println("Test 3: " + Arrays.toString(test3));
        System.out.println("Trapped water: " + trapRainwater(test3) + " units");
        
        int[] test4 = {1, 2, 3, 4, 5};
        System.out.println("Test 4: " + Arrays.toString(test4));
        System.out.println("Trapped water: " + trapRainwater(test4) + " units");
    }
}
