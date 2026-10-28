import java.util.*;
public class UnionSorArrays {
    // /*
    //  Question: Find union of two sorted arrays

    //  Approach:
    //  - Use two pointers i and j
    //  - Compare elements of both arrays
    //  - Add smaller element to result
    //  - Skip duplicates while adding
    //  - If equal, add once and move both pointers

    //  Example:
    //  a = [1,2,2,3,4]
    //  b = [2,3,5]

    //  Result = [1,2,3,4,5]

    //  Time  : O(n + m)
    //  Space : O(n + m)
    // */

    // static ArrayList<Integer> union(int[] a, int[] b) {
    //     ArrayList<Integer> res = new ArrayList<>();
    //     int i = 0, j = 0;

    //     while (i < a.length && j < b.length) {
    //         int x = a[i] < b[j] ? a[i++] : b[j] < a[i] ? b[j++] : a[i++];

    //         if (a[i - 1] == b[j < b.length ? j : j - 1]) j++;

    //         if (res.isEmpty() || res.get(res.size() - 1) != x)
    //             res.add(x);
    //     }

    //     while (i < a.length)
    //         if (res.isEmpty() || res.get(res.size() - 1) != a[i])
    //             res.add(a[i++]);
    //         else i++;

    //     while (j < b.length)
    //         if (res.isEmpty() || res.get(res.size() - 1) != b[j])
    //             res.add(b[j++]);
    //         else j++;

    //     return res;
    // }

    // public static void main(String[] args) {
    //     int[] a = {1, 2, 2, 3, 4};
    //     int[] b = {2, 3, 5};

    //     System.out.println(union(a, b));
    // }


    /**
 * Question: Find the union of two arrays.
 * 
 * Topics Included:
 * - Array manipulation
 * - Set operations
 * - Union finding
 * - Duplicate elimination
 * 
 * Algorithm:
 * 1. Create a set to store all unique elements
 * 2. Add all elements from the first array to the set
 * 3. Add all elements from the second array to the set
 * 4. Convert the set to an array and return
 * 
 * Pseudocode:
 * FUNCTION findUnion(array1, array2):
 *     unionSet = new HashSet()
 *     
 *     FOR each element in array1:
 *         ADD element to unionSet
 *     
 *     FOR each element in array2:
 *         ADD element to unionSet
 *     
 *     RETURN unionSet.toArray()
 * 
 * Explanation with Example:
 * Input arrays: [1, 2, 3, 4] and [3, 4, 5, 6]
 * Dry run:
 * - Create unionSet = {}
 * - Add elements from array1:
 *   - Add 1 → unionSet = {1}
 *   - Add 2 → unionSet = {1, 2}
 *   - Add 3 → unionSet = {1, 2, 3}
 *   - Add 4 → unionSet = {1, 2, 3, 4}
 * - Add elements from array2:
 *   - Add 3 → already exists (no change)
 *   - Add 4 → already exists (no change)
 *   - Add 5 → unionSet = {1, 2, 3, 4, 5}
 *   - Add 6 → unionSet = {1, 2, 3, 4, 5, 6}
 * - Convert to array: [1, 2, 3, 4, 5, 6]
 * Final result: [1, 2, 3, 4, 5, 6]
 * 
 * Time Complexity: O(n + m) where n and m are lengths of the two arrays
 * Space Complexity: O(n + m) - requires space for the union set
 */




    
    /**
     * Method to find the union of two arrays
     * @param arr1 The first array
     * @param arr2 The second array
     * @return An array containing the union elements (unique)
     */
    public static int[] findUnion(int[] arr1, int[] arr2) {
        Set<Integer> unionSet = new HashSet<>();
        
        // Add all elements from first array
        for (int num : arr1) {
            unionSet.add(num);
        }
        
        // Add all elements from second array
        for (int num : arr2) {
            unionSet.add(num);
        }
        
        // Convert set to array
        int[] result = new int[unionSet.size()];
        int index = 0;
        for (int num : unionSet) {
            result[index++] = num;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Question: Find the union of two arrays
        // Topics: Array manipulation, set operations, union finding, duplicate elimination
        // Algorithm: Use hash set to combine all unique elements from both arrays
        // Pseudocode: unionSet = new HashSet(); add all elements from both arrays; return unionSet.toArray()
        // Example: [1, 2, 3, 4] and [3, 4, 5, 6] → [1, 2, 3, 4, 5, 6]
        // Dry run: unionSet={} → add 1,2,3,4 → {1,2,3,4} → add 3,4,5,6 → {1,2,3,4,5,6}
        // Time: O(n+m), Space: O(n+m)
        
        int[] arr1 = {1, 2, 3, 2, 3,6,1};
        int[] arr2 = {3, 4, 5, 6,2,1};
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]);
            if (i < arr1.length - 1) System.out.print(", ");
        }
        System.out.println();
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i]);
            if (i < arr2.length - 1) System.out.print(", ");
        }
        System.out.println();

        int[] result = findUnion(arr1, arr2);
        
        System.out.println("Array 1: [1, 2, 3, 4]");
        System.out.println("Array 2: [3, 4, 5, 6]");
        System.out.print("Union: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}

