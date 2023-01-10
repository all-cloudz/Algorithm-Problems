package backtracking.problem_1278;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Problem_1278 {

    private static final StringBuilder answer = new StringBuilder();

    private static int N;
    private static Set<Integer> acted = new HashSet<>();
    ;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        play(0, 0);

        System.out.println(answer);
    }

    private static boolean play(int isSelected, int cntOfAct) {
        if (cntOfAct == 1 << N) {
            answer.append(cntOfAct - 1).append('\n');
            return true;
        }

        for (int i = 0; i < N; i++) {
            int next = isSelected | 1 << i;

            if ((isSelected & 1 << i) == 0 && !acted.contains(next)) {
                acted.add(next);

                if (play(next, cntOfAct + 1)) {
                    answer.append(i + 1).append('\n');
                    return true;
                }

                acted.remove(next);
            }

            next = isSelected & ~(1 << i);
            if (next == 0 && cntOfAct != (1 << N) - 1) {
                continue;
            }

            if ((isSelected & 1 << i) != 0 && !acted.contains(next)) {
                acted.add(next);

                if (play(next, cntOfAct + 1)) {
                    answer.append(i + 1).append('\n');
                    return true;
                }

                acted.remove(next);
            }
        }

        return false;
    }

}
