package problem_9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_9252_Memoization {

    private static char[] chars1;
    private static char[] chars2;
    private static int[][] memoized;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        chars1 = (" " + input.readLine()).toCharArray();
        chars2 = (" " + input.readLine()).toCharArray();

        memoized = new int[chars1.length][chars2.length];
        for (int len = chars1.length, i = 1; i < len; i++) {
            Arrays.fill(memoized[i], 1, chars2.length, -1);
        }

        int row = chars1.length - 1;
        int col = chars2.length - 1;
        fillTable(row, col);

        int lenOfLCS = memoized[row][col];
        List<Character> lcs = new LinkedList<>();
        setLCS(row, col, lcs);

        answer.append(lenOfLCS).append('\n');
        lcs.forEach(answer::append);
        System.out.println(answer);
    }

    private static int fillTable(int row, int col) {
        if (memoized[row][col] != -1) {
            return memoized[row][col];
        }

        if (chars1[row] == chars2[col]) {
            return memoized[row][col] = fillTable(row - 1, col - 1) + 1;
        }

        return memoized[row][col] = Math.max(fillTable(row - 1, col), fillTable(row, col - 1));
    }

    private static void setLCS(int row, int col, List<Character> lcs) {
        if (row == 0 || col == 0) {
            return;
        }

        if (chars1[row] == chars2[col]) {
            lcs.add(0, chars1[row]);
            setLCS(row - 1, col - 1, lcs);
            return;
        }

        if (memoized[row][col] == memoized[row - 1][col]) {
            setLCS(row - 1, col, lcs);
            return;
        }

        if (memoized[row][col] == memoized[row][col - 1]) {
            setLCS(row, col - 1, lcs);
        }
    }

}
