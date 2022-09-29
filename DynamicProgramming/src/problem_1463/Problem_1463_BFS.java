package problem_1463;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Problem_1463_BFS {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(input.readLine());

        Queue<Integer> nums = new ArrayDeque<>();
        int[] discovered = new int[X + 1];
        nums.offer(X);

        while (!nums.isEmpty()) {
            int cur = nums.poll();

            if (cur == 1) {
                break;
            }

            if (cur % 3 == 0 && discovered[cur / 3] == 0) {
                nums.offer(cur / 3);
                discovered[cur / 3] = discovered[cur] + 1;
            }

            if (cur % 2 == 0 && discovered[cur / 2] == 0) {
                nums.offer(cur / 2);
                discovered[cur / 2] = discovered[cur] + 1;
            }

            if (discovered[cur - 1] == 0) {
                nums.offer(cur - 1);
                discovered[cur - 1] = discovered[cur] + 1;
            }
        }

        System.out.println(discovered[1]);
    }

}
