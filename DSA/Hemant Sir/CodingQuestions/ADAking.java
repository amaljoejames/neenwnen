import java.util.*;

public class ADAking {

    /* ADAKING

Full Question

Chef Ada wants to place a king and some obstacles on an 8×8 chessboard
such that the king can reach exactly K cells.

A king can move to any adjacent cell (horizontal, vertical, diagonal)
that does not contain an obstacle.

For each test case, print any valid chessboard configuration where the
king can reach exactly K cells.

Symbols:

'O' = King's starting position
'.' = Empty cell
'X' = Obstacle

Input Format

T
K1
K2
...
KT

T = Number of test cases
K = Number of cells the king should be able to reach

Output Format

For each test case print an 8×8 board.

Gist of the Question

Create an 8×8 board where exactly K cells are reachable.

Observation

If we keep the first K cells open and all remaining cells blocked,
the king can reach exactly K cells.

Since the board is filled row-wise and all open cells remain connected,
the king can move to every open cell.

Approach

1. Create an 8×8 board.
2. Place king 'O' at (0,0).
3. Remaining K-1 reachable cells become '.'.
4. All other cells become 'X'.
5. Print the board.

Time Complexity

O(64) ≈ O(1)

Space Complexity

O(64) ≈ O(1)

Selected Approach

Construct the board directly.

Sample Input

4
1
5
9
64

Sample Output

For K = 1

OXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX

For K = 5

O....
XXXXXXX
XXXXXXX
XXXXXXX
XXXXXXX
XXXXXXX
XXXXXXX
XXXXXXX

For K = 9

O.......
.
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX

For K = 64

O.......
........
........
........
........
........
........
........

Dry Run (K = 5)

Initially:

OXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX

Need 5 reachable cells.

Cell 1 = O
Next 4 cells = .

Result:

O....
XXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX
XXXXXXXX

Reachable cells = 5

*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {

            int k = sc.nextInt();

            char[][] board = new char[8][8];

            for (int i = 0; i < 8; i++) {
                Arrays.fill(board[i], 'X');
            }

            board[0][0] = 'O';
            k--;

            for (int i = 0; i < 8 && k > 0; i++) {
                for (int j = 0; j < 8 && k > 0; j++) {

                    if (i == 0 && j == 0)
                        continue;

                    board[i][j] = '.';
                    k--;
                }
            }

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }

            System.out.println();
        }
    }
}