package problem_2098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem_2098 {

    private static final int INF = 1_000_000 << 5 ;

    private static int N;
    private static int[][] W;
    private static int[][] table;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        W = new int[N][N];
        table = new int[1 << N][N];

        for (int i = 0; i < N; i++) {
            W[i] = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

//		for (int len = 1 << N, i = 0; i < len; i++) {
//			Arrays.fill(table[i], INF);
//		}

        memoize(1, 0);
        System.out.println(table[1][0]);
    }

    private static int memoize(int visited, int cur) {
        if (table[visited][cur] != 0) {
            return table[visited][cur];
        }

        if (visited == (1 << N) - 1) {
            return (W[cur][0] == 0) ? INF : W[cur][0];
        }

        int minDist = INF;

        for (int i = 0; i < N; i++) {
            if ((visited & 1 << i) == 0 && W[cur][i] != 0) {
                minDist = Math.min(minDist, W[cur][i] + memoize(visited | 1 << i, i));
            }
        }

        return table[visited][cur] = minDist;
    }

}

