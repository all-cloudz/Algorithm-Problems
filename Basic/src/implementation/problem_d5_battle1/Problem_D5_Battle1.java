package implementation.problem_d5_battle1;

import java.io.*;
import java.util.*;

public class Problem_D5_Battle1 {
    private static int N;
    private static int[][] gates; // row : gate number, col : [ gate position, number of people ]
    private static int[] order;
    private static int[] sited;
    private static int totalMinDist;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(input.readLine());
            gates = new int[3][2];

            StringTokenizer st = new StringTokenizer(input.readLine());
            gates[0] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) };

            st = new StringTokenizer(input.readLine());
            gates[1] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) };

            st = new StringTokenizer(input.readLine());
            gates[2] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) };

            order = new int[3];
            sited = new int[N];
            totalMinDist = Integer.MAX_VALUE;
            generatePermutation(0, 0);
            answer.append(String.format("#%d %d%n", tc, totalMinDist));
        }

        System.out.println(answer);
    }

    private static void generatePermutation(int isSelected, int cnt) {
        if (cnt == 3) {
            distribute(0, gates[order[0]][0], gates[order[0]][1], 0, 0);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if ((isSelected & 1 << i) != 0) {
                continue;
            }

            order[cnt] = i;
            generatePermutation(isSelected | 1 << i, cnt + 1);
        }
    }

    private static void distribute(int idx, int start, int numOfPeople, int depth, int sumDist) {
        if (numOfPeople == 0 && idx == 2) {
            totalMinDist = Math.min(totalMinDist, sumDist);
            return;
        }

        if (numOfPeople == 0) {
            distribute(idx + 1, gates[order[idx + 1]][0], gates[order[idx + 1]][1], depth, sumDist);
            return;
        }

        int left = start - 1;
        while (left >= 0 && sited[left] != 0) {
            left--;
        }

        int right = start;
        while (right < N && sited[right] != 0) {
            right++;
        }

        if (right != N && (left == -1 || start - left > right - start)) {
            sited[right] = right - start + 1;
            distribute(idx, start, numOfPeople - 1, depth + 1, sumDist + sited[right]);
            sited[right] = 0;
            return;
        }

        if (left != -1 && (right == N || start - left < right - start)) {
            sited[left] = start - left + 1;
            distribute(idx, start, numOfPeople - 1, depth + 1, sumDist + sited[left]);
            sited[left] = 0;
            return;
        }

        if (start - left == right - start) {
            sited[right] = right - start + 1;
            distribute(idx, start, numOfPeople - 1, depth + 1, sumDist + sited[right]);
            sited[right] = 0;

            if (numOfPeople % 2 == 0) {
                return;
            }

            sited[left] = start - left + 1;
            distribute(idx, start, numOfPeople - 1, depth + 1, sumDist + sited[left]);
            sited[left] = 0;
        }
    }
}
