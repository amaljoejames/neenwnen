/**
 * QUESTION: String Applications - Implement advanced string operations including
 * case conversion, string compression, largest string comparison, and array reversal.
 * 
 * QUESTION EXPLANATION:
 * 
 * 1. Title Case Conversion:
 * Convert string to title case (first letter of each word uppercase).
 * Handle spaces and ensure proper capitalization.
 * 
 * 2. String Compression:
 * Compress string by counting consecutive characters.
 * Format: character + count (if count > 1).
 * 
 * 3. Largest String Comparison:
 * Find lexicographically largest string in array.
 * Use compareTo() method for string comparison.
 * 
 * 4. Array Reversal:
 * Reverse 2x2 array elements using command-line arguments.
 * Swap elements symmetrically from ends.
 * 
 * 5. String Builder Efficiency:
 * Demonstrate StringBuilder vs String concatenation performance.
 * 
 * APPROACH USED: String Manipulation and Array Processing
 * 
 * PSEUDOCODE:
 * 
 * Title Case:
 * function toUpperCase(str):
 *   sb = StringBuilder()
 *   sb.append(upperCase(str[0]))
 *   
 *   for i = 1 to str.length-1:
 *     if str[i] == ' ' && i < str.length-1:
 *       sb.append(str[i])
 *       i++
 *       sb.append(upperCase(str[i]))
 *     else:
 *       sb.append(str[i])
 *   
 *   return sb.toString()
 * 
 * String Compression:
 * function compress(str):
 *   newStr = ""
 *   
 *   for i = 0 to str.length-1:
 *     count = 1
 *     while i < str.length-1 && str[i] == str[i+1]:
 *       count++
 *       i++
 *     
 *     newStr += str[i]
 *     if count > 1:
 *       newStr += count.toString()
 *   
 *   return newStr
 * 
 * Largest String:
 * function findLargest(fruits):
 *   largest = fruits[0]
 *   
 *   for i = 1 to fruits.length-1:
 *     if largest.compareTo(fruits[i]) < 0:
 *       largest = fruits[i]
 *   
 *   return largest
 * 
 * Array Reversal:
 * function reverseArray(arr, n):
 *   for i = 0 to n/2-1:
 *     temp = arr[i]
 *     arr[i] = arr[n-1-i]
 *     arr[n-1-i] = temp
 * 
 * EXPLANATION OF STEPS:
 * 1. Title Case: Process characters with space detection
 * 2. Compression: Count consecutive characters efficiently
 * 3. Largest String: Use built-in string comparison
 * 4. Array Reversal: Symmetric element swapping
 * 5. StringBuilder: Use mutable string for performance
 * 
 * DRY RUN for Title Case:
 * 
 * String: "hi i'm amal joe jAMES"
 * 
 * Process:
 * sb = "H" (uppercase 'h')
 * 
 * i=1: ' ' -> add " ", i=2, uppercase 'I' -> sb = "H I"
 * i=3: '\'' -> add "'", i=4, 'm' -> sb = "H I'm"
 * i=5: ' ' -> add " ", i=6, uppercase 'A' -> sb = "H I'm A"
 * 
 * Continue for remaining characters...
 * 
 * Result: "H I'm Amal Joe JAMES"
 * 
 * DRY RUN for String Compression:
 * 
 * String: "AAABBBCCCDDDE"
 * 
 * Process:
 * i=0: 'A', count=3 (A,A,A), newStr="A3"
 * i=3: 'B', count=3 (B,B,B), newStr="A3B3"
 * i=6: 'C', count=3 (C,C,C), newStr="A3B3C3"
 * i=9: 'D', count=2 (D,D), newStr="A3B3C3D2"
 * i=11: 'E', count=1, newStr="A3B3C3D2E"
 * 
 * Result: "A3B3C3D2E"
 * 
 * DRY RUN for Largest String:
 * 
 * Fruits: ["apple","mango","banana"]
 * 
 * Process:
 * largest = "apple"
 * 
 * i=1: "apple".compareTo("mango") < 0 -> largest = "mango"
 * i=2: "mango".compareTo("banana") < 0 -> largest = "banana"
 * 
 * Result: "mango" (lexicographically largest)
 * 
 * DRY RUN for Array Reversal:
 * 
 * Input: [1,2,3,4]
 * 
 * Process:
 * i=0: swap arr[0] and arr[3] -> [4,2,3,1]
 * i=1: swap arr[1] and arr[2] -> [4,3,2,1]
 * 
 * Result: [4,3,2,1]
 */
public class StringApplications {
    
    // Convert string to title case
    public static String toUpperCase(String str) {
        StringBuilder sb = new StringBuilder("");
        
        // Handle empty string
        if (str.length() == 0) {
            return str;
        }
        
        // First character uppercase
        char ch = Character.toUpperCase(str.charAt(0));
        sb.append(ch);
        
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == ' ' && i < str.length() - 1) {
                sb.append(str.charAt(i));
                i++;
                sb.append(Character.toUpperCase(str.charAt(i)));
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    // String compression (Amazon)
    public static String compress(String str) {
        String newStr = "";
        
        for (int i = 0; i < str.length(); i++) {
            Integer count = 1;
            while (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                count++;
                i++;
            }
            newStr += str.charAt(i);
            if (count > 1) {
                newStr += count.toString();
            }
        }
        return newStr;
    }

    // String compression using StringBuilder (more efficient)
    public static String compressStrBuilder(String str) {
        StringBuilder newStr = new StringBuilder("");
        
        for (int i = 0; i < str.length(); i++) {
            Integer count = 1;
            while (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                count++;
                i++;
            }
            newStr.append(str.charAt(i));
            if (count > 1) {
                newStr.append(count);
            }
        }
        return newStr.toString();
    }

    // Find largest string in array
    public static String findLargest(String[] fruits) {
        if (fruits.length == 0) {
            return "";
        }
        
        String largest = fruits[0];
        for (int i = 1; i < fruits.length; i++) {
            if (largest.compareTo(fruits[i]) < 0) {
                largest = fruits[i];
            }
        }
        return largest;
    }

    // Reverse 2x2 array
    public static void reverse2x2Array(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
    
    public static void main(String[] args) {
        /*
         * DRY RUN IN MAIN:
         * Test all string applications
         * 
         * Title Case: "hi i'm amal joe jAMES"
         * Expected: "H I'm Amal Joe JAMES"
         * 
         * Compression: "AAABBBCCCDDDE"
         * Expected: "A3B3C3D2E"
         * 
         * Largest String: ["apple","mango","banana"]
         * Expected: "mango"
         * 
         * Array Reversal: [1,2,3,4]
         * Expected: [4,3,2,1]
         */
        
        // Test Title Case Conversion
        System.out.println("String Applications - Title Case Conversion");
        String titleStr = "hi i'm amal joe jAMES";
        System.out.println("Original: " + titleStr);
        System.out.println("Expected: H I'm Amal Joe JAMES");
        String titleResult = toUpperCase(titleStr);
        System.out.println("Result: " + titleResult);
        
        // Test String Compression
        System.out.println("\nString Compression:");
        String compressStr = "AAABBBCCCDDDE";
        System.out.println("Original: " + compressStr);
        System.out.println("Expected: A3B3C3D2E");
        String compressed = compress(compressStr);
        System.out.println("Result: " + compressed);
        
        // Test StringBuilder Compression
        System.out.println("\nStringBuilder Compression:");
        String compressStr2 = "AAABBBCCCDDDE";
        System.out.println("Original: " + compressStr2);
        System.out.println("Expected: A3B3C3D2E");
        String compressedSB = compressStrBuilder(compressStr2);
        System.out.println("Result: " + compressedSB);
        
        // Test Largest String
        System.out.println("\nLargest String:");
        String[] fruits = {"apple", "mango", "banana"};
        System.out.println("Fruits: [apple, mango, banana]");
        System.out.println("Expected: mango");
        String largest = findLargest(fruits);
        System.out.println("Result: " + largest);
        
        // Test Array Reversal
        System.out.println("\nArray Reversal:");
        int[] arr = {1, 2, 3, 4};
        System.out.println("Original: [1,2,3,4]");
        System.out.println("Expected: [4,3,2,1]");
        reverse2x2Array(arr);
        System.out.println("Result: [" + arr[0] + " " + arr[1] + "]");
        System.out.println("        [" + arr[2] + " " + arr[3] + "]");
        
        // Additional test cases
        System.out.println("\nAdditional test cases:");
        
        // Test case 2: Empty string title case
        System.out.println("Empty string title case:");
        System.out.println("Original: \"\"");
        System.out.println("Expected: \"\"");
        String emptyTitle = toUpperCase("");
        System.out.println("Result: \"" + emptyTitle + "\"");
        
        // Test case 3: Single character compression
        System.out.println("\nSingle character compression:");
        System.out.println("Original: A");
        System.out.println("Expected: A");
        String singleCompress = compress("A");
        System.out.println("Result: " + singleCompress);
        
        // Test case 4: Empty array largest string
        System.out.println("\nEmpty array:");
        String[] emptyArray = {};
        System.out.println("Fruits: []");
        System.out.println("Expected: \"\"");
        String emptyLargest = findLargest(emptyArray);
        System.out.println("Result: \"" + emptyLargest + "\"");
        
        // Test case 5: Single element array
        System.out.println("\nSingle element array:");
        String[] singleArray = {"zebra"};
        System.out.println("Fruits: [zebra]");
        System.out.println("Expected: zebra");
        String singleLargest = findLargest(singleArray);
        System.out.println("Result: " + singleLargest);
        
        // Test case 6: Already compressed string
        System.out.println("\nAlready compressed:");
        System.out.println("Original: ABC");
        System.out.println("Expected: ABC");
        String alreadyCompressed = compress("ABC");
        System.out.println("Result: " + alreadyCompressed);
    }
}
