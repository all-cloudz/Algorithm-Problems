package backtracking.problem_14888;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Problem_14888 {
    private static int MAX = Integer.MIN_VALUE;
    private static int MIN = Integer.MAX_VALUE;

    private static int N;
    private static int[] nums;
    private static int[] operators = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        nums = new int[N];

        String[] tmp = input.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tmp[i]);
        }

        tmp = input.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(tmp[i]);
        }

        backtracking(nums[0], 1);

        System.out.println(MAX);
        System.out.println(MIN);
    }

    private static void backtracking(int calculated, int idx){
        if (idx == N) {
            MAX = Math.max(MAX, calculated);
            MIN = Math.min(MIN, calculated);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--;

                switch (i) {
                    case 0 :
                        backtracking(calculated + nums[idx], idx + 1);
                        break;
                    case 1 :
                        backtracking(calculated - nums[idx], idx + 1);
                        break;
                    case 2 :
                        backtracking(calculated * nums[idx], idx + 1);
                        break;
                    case 3 :
                        backtracking(calculated / nums[idx], idx + 1);
                        break;
                }

                operators[i]++; // 백트래킹을 위해 다시 원래의 연산자 개수로 복구해야 한다.
            }
        }
    }
}
