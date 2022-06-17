package problem_lv1_42748;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem_Lv1_42748_Collections {
    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int from = commands[i][0] - 1;
            int to = commands[i][1] - 1;
            int pos = commands[i][2] - 1;

            List<Integer> numList = Arrays.stream(array, from, to + 1).boxed().sorted().collect(Collectors.toList());
            answer[i] = numList.get(pos);
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
