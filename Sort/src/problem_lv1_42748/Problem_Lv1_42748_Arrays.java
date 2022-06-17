package problem_lv1_42748;

import java.util.*;

public class Problem_Lv1_42748_Arrays {
    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int from = commands[i][0] - 1;
            int to = commands[i][1] - 1;
            int pos = commands[i][2] - 1;

            int[] copy = new int[to - from + 1];
            System.arraycopy(array, from, copy, 0, to - from + 1);
            Arrays.sort(copy);

            answer[i] = copy[pos];
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] array = new int[] {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = new int[][] {
                new int[] {2, 5, 3},
                new int[] {4, 4, 1},
                new int[] {1, 7, 3}
        };

        int[] answer = solution(array, commands);
        System.out.print(Arrays.toString(answer));
    }
}
