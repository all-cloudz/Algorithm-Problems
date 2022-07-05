package implementation.problem_10163;

import java.io.*;
import java.util.*;

public class Problem_10163 {
    private static StringBuilder answer = new StringBuilder();
    private static int[][] plane = new int[1001][1001];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        for (int i = 1; i <= N; i++) {
            int[] paper = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            putPaper(i, paper);
        }

        cntPaper(N);
        System.out.print(answer);
    }

    private static void putPaper(int order, int[] paper) {
        int x = paper[0];
        int y = paper[1];

        for (int i = 0; i < paper[2]; i++) {
            for (int j = 0; j < paper[3]; j++) {
                plane[x + i][y + j] = order;
            }
        }
    }
    private static void cntPaper(int paperNum) {
        int[] cnts = new int[paperNum + 1];

        for (int i = 0; i < 1001; i++) {
            for (int j = 0; j < 1001; j++) {
                if (plane[i][j] != 0){
                    cnts[plane[i][j]]++;
                }
            }
        }

        for (int i = 1; i < cnts.length; i++) {
            answer.append(cnts[i]).append('\n');
        }
    }
}
