package problem_1504;

import java.io.*;
import java.util.*;

/* FloydWarshall vs Dijkstra
 * 1. FloydWarshall 알고리즘은 시간복잡도가 O(V^3)이다.
 * 2. 반면, Dijkstra 알고리즘은 시간복잡도가 O(ElogV)이다.
 * 3. 따라서 V가 클수록 몇 개 안되는 최단 거리를 탐색하는 것은 Floyd보다 Dijkstra가 유리하다.
 * 4. 즉, 문제에 주어진 정점의 개수 V가 최대 800이므로 3번의 최단 거리 탐색은 Dijkstra가 유리하다.
 * 5. 다만, 이 문제의 경우 FloydWarshall로도 통과할 수 있는 이유는 이 알고리즘의 상수가 낮기 때문이다. */

public class Problem_1504_FloydWarshall {
    private static final int INF = 1000 * 200000 + 1;

    private static int V;
    private static int[][] dists;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        V = Integer.parseInt(tokenizer.nextToken());
        dists = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            Arrays.fill(dists[i], INF);
            dists[i][i] = 0;
        }

        final int E = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            int vertex1 = Integer.parseInt(tokenizer.nextToken());
            int vertex2 = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());

            dists[vertex1][vertex2] = weight;
            dists[vertex2][vertex1] = weight;
        }

        floydWarshall();

        tokenizer = new StringTokenizer(input.readLine());

        int waypoint1 = Integer.parseInt(tokenizer.nextToken());
        int waypoint2 = Integer.parseInt(tokenizer.nextToken());

        int answer = Math.min(dists[1][waypoint1] + dists[waypoint2][V], dists[1][waypoint2] + dists[waypoint1][V]) + dists[waypoint1][waypoint2];

        if (answer >= INF) {
            System.out.print(-1);
            return;
        }

        System.out.print(answer);
    }

    private static void floydWarshall() {
        for (int waypoint = 1; waypoint <= V; waypoint++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    dists[i][j] = Math.min(dists[i][j], dists[i][waypoint] + dists[waypoint][j]);
                }
            }
        }
    }
}
