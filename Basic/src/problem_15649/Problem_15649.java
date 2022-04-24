package problem_15649;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 재귀를 이용하여 백트래킹을 구현하는 대표적인 문항
public class Problem_15649 {
    private static final StringBuilder answer = new StringBuilder();

    private static int N;
    private static boolean[] visited;
    private static int M;
    private static int[] nums;


    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[N];

        M = Integer.parseInt(tokenizer.nextToken());
        nums = new int[M];

        backtracking(0);

        System.out.print(answer);
    }

    private static void backtracking(int depth) {
        if (depth == M) {
            for (int val : nums) {
                answer.append(val).append(' ');
            }

            // answer.append(String.format("%n", System.lineSeparator()));
            answer.append('\n');
            return; // 리턴을 하면 현재 깊이의 재귀를 종료하며 백트래킹
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                nums[depth] = i + 1;
                visited[i] = true; // 중복을 허락하지 않으므로 방문했던 요소는 건너뛸 수 있도록 해야 함
                
                backtracking(depth + 1); // 깊이를 하나 더 깊게 하여 필요한 요소 탐색
                visited[i] = false; // 백트래킹 과정에서 방문했던 요소를 다시 방문해야 하므로 false로 초기화
            }
        }
    }
}
