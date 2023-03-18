package parametric_search.problem_d5_10507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D5_10507_TwoPointer {

    private static int n;
    private static int p;
    private static int[] studied;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            p = Integer.parseInt(tokenizer.nextToken());

            studied = new int[n];
            tokenizer = new StringTokenizer(input.readLine());
            for (int i = 0; i < n; i++) {
                studied[i] = Integer.parseInt(tokenizer.nextToken());
            }

            answer.append(String.format("#%d %d%n", tc, maxLength()));
        }

        System.out.println(answer);
    }

    private static int maxLength() {
        Pointer left = new Pointer(0, studied[0]);
        Pointer right = initRight(left);
        int maxLength = right.value - left.value;

        while (left.value <= studied[n - 1]) {
            moveAll(left);
            left.value++;

            right.value++;
            moveAll(right);

            maxLength = Math.max(maxLength, right.value - left.value);
        }

        return maxLength;
    }

    private static Pointer initRight(Pointer left) {
        Pointer right = new Pointer(0, studied[0]);
        int count = right.value - left.value;

        while (count <= p) {
            moveAll(right);
            if (count == p) {
                break;
            }

            right.value++;
            count++;
        }

        return right;
    }

    private static void moveAll(Pointer pointer) {
        while (pointer.index < n && pointer.value == studied[pointer.index]) {
            pointer.move();
        }
    }

    private static class Pointer {
        private int index;
        private int value;

        public Pointer(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public void move() {
            this.index++;
            this.value++;
        }
    }

}
