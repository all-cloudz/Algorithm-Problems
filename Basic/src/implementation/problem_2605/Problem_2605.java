package implementation.problem_2605;

import java.io.*;
import java.util.*;

public class Problem_2605 {
    private static StringBuilder answer = new StringBuilder();

    private static int N;
    private static List<Integer> students = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        int[] picks = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            picks[i] = Integer.parseInt(tokenizer.nextToken());
        }

        order(picks);
        System.out.print(answer);
    }

    private static void order(int[] picks) {
        for (int i = 0; i < N; i++) {
            students.add(students.size() - picks[i], i + 1);
        }

        for (int i = 0; i < N; i++) {
            answer.append(students.get(i)).append(' ');
        }
    }
}
