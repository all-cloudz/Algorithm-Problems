package problem_1956;

import java.io.*;
import java.util.*;

public class Problem_1956 {
    private static final int INF = 10000 * 400 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        final int V = Integer.parseInt(tokenizer.nextToken());
        int[][] dists = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            Arrays.fill(dists[i], INF);
        }

        final int E = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            int head = Integer.parseInt(tokenizer.nextToken());
            int tail = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());

            dists[head][tail] = weight;
        }

        floydWarshall(dists);

        int minDist = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                minDist = Math.min(minDist, dists[i][i]);
            }
        }

        minDist = (minDist == INF) ? -1 : minDist;

        System.out.print(minDist);
    }

    private static void floydWarshall(int[][] dists) {
        final int V = dists.length - 1;

        for (int waypoint = 1; waypoint <= V; waypoint++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    dists[i][j] = Math.min(dists[i][j], dists[i][waypoint] + dists[waypoint][j]);
                }
            }
        }
    }
}
