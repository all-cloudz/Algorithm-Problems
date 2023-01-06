package problem_13913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_13913 {

    private static int N;
    private static int M;
    private static Map<Integer, Integer> routes; // key : cur, value : prev
    private static StringBuilder answer;

    static {
        routes = new HashMap<>();
        answer = new StringBuilder();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        if (N >= M) {
            answer.append(N - M).append("\n");

            for (int i = N; i >= M; i--) {
                answer.append(i).append(" ");
            }

            System.out.println(answer);
            return;
        }

        answer.append(catchTime()).append("\n");

        Stack<Integer> route = new Stack<>();
        int cur = M;
        while (cur != -1) {
            route.push(cur);
            cur = routes.get(cur);
        }

        while (!route.isEmpty()) {
            answer.append(route.pop()).append(" ");
        }

        System.out.println(answer);
    }

    private static int catchTime() {
        Queue<Integer> positions = new ArrayDeque<>();
        Map<Integer, Integer> time = new HashMap<>();

        positions.offer(N);
        routes.put(N, -1);
        time.put(N, 0);

        levelTraversal :
        while (!positions.isEmpty()) {
            for (int size = positions.size(), i = 0; i < size; i++) {
                int cur = positions.poll();

                if (cur > 200_000) {
                    continue;
                }

                if (cur == M) {
                    break levelTraversal;
                }

                if (!time.containsKey(cur - 1)) {
                    positions.offer(cur - 1);
                    routes.put(cur - 1, cur);
                    time.put(cur - 1, time.get(cur) + 1);
                }

                if (!time.containsKey(cur + 1)) {
                    positions.offer(cur + 1);
                    routes.put(cur + 1, cur);
                    time.put(cur + 1, time.get(cur) + 1);
                }

                if (!time.containsKey(2 * cur)) {
                    positions.offer(2 * cur);
                    routes.put(2 * cur, cur);
                    time.put(2 * cur, time.get(cur) + 1);
                }
            }
        }

        return time.get(M);
    }

}
