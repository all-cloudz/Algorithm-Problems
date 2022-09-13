package problem_9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Problem_9252_Tabulation {

    private static char[] chars1;
    private static char[] chars2;
    private static int[][] tabulated;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        chars1 = (" " + input.readLine()).toCharArray();
        chars2 = (" " + input.readLine()).toCharArray();
        tabulated = new int[chars1.length][chars2.length];
        fillTable();

        int row = chars1.length - 1;
        int col = chars2.length - 1;
        int lenOfLCS = tabulated[row][col];
        Stack<Character> lcs = new Stack<>();

        while (lcs.size() < lenOfLCS) {
            if (chars1[row] == chars2[col]) {
                lcs.push(chars1[row]);
                row--; col--;
                continue;
            }

            if (tabulated[row][col] == tabulated[row - 1][col]) {
                row--;
                continue;
            }

            if (tabulated[row][col] == tabulated[row][col - 1]) {
                col--;
            }
        }

        answer.append(lenOfLCS).append('\n');
        while (!lcs.isEmpty()) {
            answer.append(lcs.pop());
        }
        System.out.println(answer);
    }

    private static void fillTable() {
        for (int len1 = chars1.length, i = 1; i < len1; i++) {
            for (int len2 = chars2.length, j = 1; j < len2; j++) {
                if (chars1[i] == chars2[j]) {
                    tabulated[i][j] = tabulated[i - 1][j - 1] + 1;
                    continue;
                }

                tabulated[i][j] = Math.max(tabulated[i][j - 1], tabulated[i - 1][j]);
            }
        }
    }

}
