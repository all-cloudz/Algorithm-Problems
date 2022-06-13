package backtracking.problem_14889;

import java.io.*;
import java.util.*;

public class Problem_14889 {
    private static int N;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        int[][] status = new int[N][N];
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < N; j++) {
                status[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        makeTeam(status, visited, 0,0);
        System.out.print(answer);
    }

    // idx는 다음 팀원을 선택할 때 중복된 탐색을 피하기 위해 필요
    // cnt는 재귀 깊이(depth)의 역할로서 팀원을 모두 선택한 후에 재귀를 멈추기 위해 필요
    private static void makeTeam(int[][] status, boolean[] visited, int idx, int cnt) {
        if (cnt == N / 2) {
            difference(status, visited);
            return;
        }

        // i = cnt를 사용할 경우 중복 방문이 많이 발생한다.
        // 이를 피하기 위해 i = idx를 사용하여 가장 최근에 팀에 편입한 사람 이후를 탐색해야 한다.
        for (int i = idx; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                makeTeam(status, visited, i + 1, cnt + 1);

                visited[i] = false;
            }
        }
    }

    private static void difference(int[][] status, boolean[] visited) {
        int startTeamStatus = 0;
        int linkTeamStatus = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (visited[i] && visited[j]) {
                    startTeamStatus += status[i][j] + status[j][i];
                    continue;
                }

                if (!visited[i] && !visited[j]) {
                    linkTeamStatus += status[i][j] + status[j][i];
                }
            }
        }

        int diff = Math.abs(startTeamStatus - linkTeamStatus);
        answer = Math.min(answer, diff);
    }
}
