package implementation.problem_14696;

import java.io.*;
import java.util.*;

public class Problem_14696 {
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        while (N-- > 0) {
            int[] paperA = makePaper(input);
            int[] paperB = makePaper(input);

            match(paperA, paperB);
        }

        System.out.print(answer);
    }

    private static int[] makePaper(BufferedReader input) throws IOException {
        int[] paper = new int[5];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int figureNum = Integer.parseInt(tokenizer.nextToken());
        while (figureNum-- > 0) {
            paper[Integer.parseInt(tokenizer.nextToken())]++;
        }

        return paper;
    }

    private static void match(int[] paperA, int[] paperB) {
        for (int i = 4; i >= 1; i--) {
            if (paperA[i] > paperB[i]) {
                answer.append("A\n");
                return;
            }

            if (paperA[i] < paperB[i]) {
                answer.append("B\n");
                return;
            }
        }

        answer.append("D\n");
    }
}
