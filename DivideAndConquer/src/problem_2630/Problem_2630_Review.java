package problem_2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_2630_Review {

    private static int N;
    private static int[][] paper;
    private static int whitePaperCount;
    private static int bluePaperCount;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(input.readLine());
        paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        cutPaper(0, 0, N);

        answer.append(whitePaperCount).append("\n")
              .append(bluePaperCount);
        System.out.print(answer);
    }

    private static void cutPaper(int row, int col, int size) {
        int blueCount = 0;
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (paper[i][j] == 1) {
                    blueCount++;
                }
            }
        }

        if (blueCount == 0) {
            whitePaperCount++;
            return;
        }

        if (blueCount == size * size) {
            bluePaperCount++;
            return;
        }

        cutPaper(row, col, size / 2);
        cutPaper(row + size / 2, col, size / 2);
        cutPaper(row, col + size / 2, size / 2);
        cutPaper(row + size / 2, col + size / 2, size / 2);
    }

}
