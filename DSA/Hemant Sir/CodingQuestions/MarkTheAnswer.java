
/*
 * Mark The Answer Problem
 * 
 * Problem Statement:
 * Our friend Monk has an exam that has quite weird rules. Each question has a 
 * difficulty level in the form of an Integer. Now, Monk can only solve the 
 * problems that have difficulty level less than X. Now the rules are-
 * 
 * 1. Score of the student is equal to the maximum number of answers he/she 
 *    has attempted without skipping a question.
 * 2. Student is allowed to skip just "one" question that will not be counted 
 *    in the continuity of the questions.
 * 3. Assume the student knows the solution to the problem he/she attempts 
 *    and always starts the paper from first question.
 * 
 * Given the number of Questions, N, the maximum difficulty level of the 
 * problem Monk can solve, X, and the difficulty level of each question, can 
 * you help him determine his maximum score?
 * 
 * Input Format:
 * First Line contains Integer N, the number of questions and the maximum 
 * difficulty X Monk can solve.
 * Next line contains N integers, denoting the difficulty level of each question.
 * 
 * Output Format:
 * Maximum score Monk can achieve in the exam.
 * 
 * Sample Input:
 * 7 6
 * 4 3 7 6 7 2 2
 * 
 * Sample Output:
 * 3
 * 
 * Explanation:
 * In this example, maximum difficulty = 6, Monk solves question 0 and 1, 
 * but skips the question 2 as A[2]>6. Monk then solves the question 3, 
 * but stops at 4 because A[4]>6 and question 2 was already skipped. 
 * As 3 questions (0,1 and 3) were solved and 2 questions (2 and 4) have 
 * been skipped, therefore we print "3".
 * 
 * Algorithm/Logic:
 * 1. Iterate through the array from start
 * 2. Count questions that can be solved (difficulty <= X)
 * 3. Keep track of whether we have used our one skip or not
 * 4. If we encounter a question with difficulty > X:
 *    - If we haven't skipped yet, skip it and continue
 *    - If we already skipped, stop counting
 * 5. Return the total count of solved questions
 */

import java.util.Scanner;

public class MarkTheAnswer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int X = scanner.nextInt();

        int[] difficulties = new int[N];
        for (int i = 0; i < N; i++) {
            difficulties[i] = scanner.nextInt();
        }

        int score = 0;
        boolean skipUsed = false;

        for (int i = 0; i < N; i++) {
            if (difficulties[i] <= X) {
                score++;
            } else {

                if (!skipUsed) {

                    skipUsed = true;
                } else {

                    break;
                }
            }
        }

        System.out.println(score);
        scanner.close();
    }
}
