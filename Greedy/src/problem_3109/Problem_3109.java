package problem_3109;

import java.io.*;
import java.util.*;

public class Problem_3109 {
    private static int R;
    private static int C;
    public static char[][] city;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        city = new char[R][C];

        for (int i = 0; i < R; i++) {
            city[i] = input.readLine().toCharArray();
        }

        System.out.println(setPipeLines());
    }

    private static int setPipeLines() {
        int cnt = 0;

        for (int i = 0; i < R; i++) {
            cnt += installPipeLines(i, 0);
        }

        return cnt;
    }

    private static int installPipeLines(int row, int col) {
        city[row][col] = 'x';

        if (col == C - 1) {
            return 1;
        }

        if (row > 0 && city[row - 1][col + 1] == '.') {
            if (installPipeLines(row - 1, col + 1) == 1) {
                return 1;
            }
        }

        if (city[row][col + 1] == '.') {
            if (installPipeLines(row, col + 1) == 1) {
                return 1;
            }
        }

        if (row < R - 1 && city[row + 1][col + 1] == '.') {
            if (installPipeLines(row + 1, col + 1) == 1) {
                return 1;
            }
        }

        return 0;
    }
}
