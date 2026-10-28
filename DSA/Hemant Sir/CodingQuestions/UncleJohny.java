import java.util.*;

public class UncleJohny {
    /*Uncle Johny
Full Question

Vlad has a playlist containing N songs, each having a unique positive integer length. His favorite song is "Uncle Johny".

Before sorting, "Uncle Johny" is at position K (1-based indexing) in the playlist.

Vlad sorts the playlist in increasing order of song lengths.

For each test case, determine the position of "Uncle Johny" after sorting.

Input Format
T
For each test case:
N
A1 A2 A3 ... AN
K
T = Number of test cases
N = Number of songs
Ai = Length of ith song
K = Initial position of Uncle Johny
Output Format

For each test case, print the position of Uncle Johny in the sorted playlist.

Gist of the Question

Find the song at position K.

After sorting, determine where that song moves.

Approaches
Approach 1: Sort and Search
Store favorite song length = arr[K-1]
Sort array
Find favorite song's index in sorted array
Print index + 1

Time Complexity: O(N log N)

Approach 2: Count Smaller Elements (Optimal)

Observation:

Position of an element in sorted order =

(number of elements smaller than it) + 1

Steps:

Store favorite song length = arr[K-1]
Count elements smaller than favorite song
Answer = count + 1

Time Complexity: O(N)

Space Complexity: O(1)
Selected Approach

Sample Input 1
1
5
1 3 5 2 4
4
Sample Output 1
2
Dry Run
Favorite Song = arr[4-1] = 2

Array:
1 3 5 2 4

Elements smaller than 2:
1

Count = 1

Position after sorting:
1 + 1 = 2

Sorted array:

1 2 3 4 5

2 is at position 2.

Sample Input 2
1
4
10 20 30 40
3
Sample Output 2
3
Dry Run
Favorite Song = 30

Elements smaller than 30:
10, 20

Count = 2

Answer = 2 + 1 = 3

Sorted array:

10 20 30 40
Sample Input 3
1
6
8 2 12 5 20 15
5
Sample Output 3
6
Dry Run
Favorite Song = 20

Elements smaller than 20:
8, 2, 12, 5, 15

Count = 5

Answer = 5 + 1 = 6

Sorted array:

2 5 8 12 15 20 */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            int k = sc.nextInt();
            int fav = arr[k - 1];

            int ans = 1;

            for (int x : arr) {
                if (x < fav) ans++;
            }

            System.out.println(ans);
        }
    }
}