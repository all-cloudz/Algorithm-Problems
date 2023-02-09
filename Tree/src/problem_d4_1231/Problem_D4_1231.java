package problem_d4_1231;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_D4_1231 {

    private static StringBuilder answer = new StringBuilder();
    private static int N;
    private static char[] completeTree;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            answer.append('#').append(tc).append(' ');

            N = Integer.parseInt(input.readLine());
            completeTree = new char[N + 1];
            for (int i = 1; i <= N; i++) {
                char target = input.readLine().split(" ")[1].charAt(0);
                completeTree[i] = target;
            }

            printNode(1);
            answer.append('\n');
        }

        System.out.println(answer);
    }

    private static void printNode(int node) {
        if (node > N) {
            return;
        }

        printNode(2 * node);
        answer.append(completeTree[node]);
        printNode(2 * node + 1);
    }

}
