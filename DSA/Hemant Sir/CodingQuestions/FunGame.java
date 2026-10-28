
/*
Fun Game - HackerEarth Question

PROBLEM STATEMENT:
A and B are playing a game. In this game, both of them are initially provided with a list of n numbers.
(Both have the same list but their own copy).

Now, they both have a different strategy to play the game.
A picks the element from start of his list.
B picks from the end of his list.

You need to generate the result in form of an output list.

Method to be followed at each step to build the output list is:

If the number picked by A is bigger than B then this step's output is 1.
A removes the number that was picked from their list.
If the number picked by A is smaller than B then this step's output is 2.
B removes the number that was picked from their list.
If both have the same number then this step's output is 0.
Both A and B remove the number that was picked from their list.

This game ends when at least one of them has no more elements to be picked i.e. when the list gets empty.

Output the built output list.

INPUT FORMAT:
- First line: integer n (size of the list)
- Second line: n space-separated integers

OUTPUT FORMAT:
- Output the required output list separated by spaces

SAMPLE INPUT:
3
1 2 3

SAMPLE OUTPUT:
2 2 0

CONSTRAINTS:
1 ≤ n ≤ 10^5
1 ≤ elements ≤ 10^9
*/

import java.util.*;

public class FunGame {

    public static List<Integer> playGame(int[] arr) {
        List<Integer> result = new ArrayList<>();
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        for (int num : arr) {
            listA.add(num);
        }

        for (int num : arr) {
            listB.add(num);
        }

        int leftA = 0, rightB = arr.length - 1;

        while (leftA < listA.size() && rightB >= 0) {
            int pickA = listA.get(leftA);
            int pickB = listB.get(rightB);

            if (pickA > pickB) {
                result.add(1);
                leftA++; // A remove, B keep
            } else if (pickA < pickB) {
                result.add(2);
                rightB--;// B keeps, A removes
            } else {
                result.add(0);
                leftA++; // A removes
                rightB--; // B removes
            }
        }

        return result;
    }

    public static void printResult(List<Integer> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        List<Integer> result = playGame(arr);
        printResult(result);

        scanner.close();
    }
}
