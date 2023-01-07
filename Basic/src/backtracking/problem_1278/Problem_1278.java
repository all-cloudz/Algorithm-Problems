package backtracking.problem_1278;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Problem_1278 {

    private static final StringBuilder answer = new StringBuilder();

    private static int N;
    private static Set<Integer> acted;
    private static Stack<Integer> order;

    static {
        acted = new HashSet<>();
        order = new Stack<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        play(0, -1, 0);

        while (!order.isEmpty()) {
            answer.append(order.pop()).append("\n");
        }

        System.out.println(answer);
    }

    private static boolean play(int isSelected, int cur, int cntOfAct) {
        if (isSelected == 0 && cntOfAct == 1 << N) {
            answer.append(cntOfAct - 1).append("\n");
            return true;
        }

        for (int i = 0; i < N; i++) {
            int next = isSelected | 1 << i;

            if ((isSelected & 1 << i) == 0 && !acted.contains(next)) {
                acted.add(next);

                if (play(next, i,cntOfAct + 1)) {
                    order.push(i + 1);
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

                if (play(next, i, cntOfAct + 1)) {
                    order.push(i + 1);
                    return true;
                }

                acted.remove(next);
            }
        }

        return false;
    }

}
