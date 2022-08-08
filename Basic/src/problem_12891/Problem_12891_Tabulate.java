package problem_12891;

import java.io.*;
import java.util.*;

public class Problem_12891_Tabulate {
    private static final Map<Character, Integer> INDICES;

    private static int S;
    private static String DNA;
    private static int P;
    private static int[] minCnt = new int[4];

    static {
        INDICES = new HashMap<>();
        INDICES.put('A', 0);
        INDICES.put('C', 1);
        INDICES.put('G', 2);
        INDICES.put('T', 3);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        DNA = input.readLine();

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < 4; i++) {
            minCnt[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(countSubstring());
    }

    private static int countSubstring() {
        int totalCnt = 0;

        int[][] tabulate = new int[S - P + 1][4];
        fillTable(tabulate);

        for (int i = 0; i <= S - P; i++) {
            if (validate(tabulate[i])) {
                totalCnt++;
            }
        }

        return totalCnt;
    }

    private static void fillTable(int[][] tabulate) {
        int left = 0;
        int right = 0;

        while (right < S) {
            if (right - left < P) {
                update(tabulate, left, right++, false);
            } else {
                System.arraycopy(tabulate[left], 0, tabulate[left + 1], 0, 4);
                update(tabulate, left, left++, true);
            }
        }
    }

    private static void update(int[][] tabulate, int start, int pos, boolean isLeft) {
        int index = INDICES.get(DNA.charAt(pos));

        if (isLeft) {
            tabulate[start + 1][index] = tabulate[start][index] - 1;
            return;
        }

        tabulate[start][index]++;
    }

    private static boolean validate(int[] cnt) {
        for (int i = 0; i < 4; i++) {
            if (cnt[i] < minCnt[i]) {
                return false;
            }
        }

        return true;
    }
}
