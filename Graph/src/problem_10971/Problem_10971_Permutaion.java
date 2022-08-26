package problem_10971;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_10971_Permutaion {
    private static int N;
    private static int[][] graph;
    private static int minDist;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        graph = new int[N][N];
        minDist = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());

            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        permutate(1, 1, 0, 0);

        System.out.println(minDist);
    }

    private static void permutate(int visited, int depth, int cur, int sumDist) {
        if (minDist < sumDist) {
            return;
        }

        if (depth == N) {
            if (graph[cur][0] != 0) {
                minDist = Math.min(minDist, sumDist + graph[cur][0]);
            }

            return;
        }

        for (int i = 0; i < N; i++) {
            if ((visited & 1 << i) != 0 || graph[cur][i] == 0) {
                continue;
            }

            permutate(visited | 1 << i, depth + 1, i, sumDist + graph[cur][i]);
        }
    }
}
