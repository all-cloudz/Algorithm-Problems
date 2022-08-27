package implementation.problem_d4_battle3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_D4_Battle3 {

    private static int N;
    private static int[] cards;
    private static int[] buttons;
    private static int minCount;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append(' ');

            N = Integer.parseInt(input.readLine());
            cards = new int[N];
            buttons = new int[Math.min(N, 5)];

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int i = 0; i < N; i++) {
                cards[i] = Integer.parseInt(tokenizer.nextToken());
            }

            if (isValid(cards)) {
                answer.append(0).append('\n');
                continue;
            }

            minCount = Integer.MAX_VALUE;
            permutate(0);
            answer.append((minCount == Integer.MAX_VALUE) ? -1 : minCount).append('\n');
        }

        System.out.println(answer);
    }

    private static void permutate(int cnt) {
        if (cnt == Math.min(N, 5)) {
            pressButtons();
            return;
        }

        for (int i = 1; i < N; i++) {
            buttons[cnt] = i;
            permutate(cnt + 1);
        }
    }

    private static void pressButtons() {
        int[] cloned = new int[N];
        System.arraycopy(cards, 0, cloned, 0, N);

        for (int size = Math.min(N, 5), i = 0; i < size; i++) {
            if (i + 1 >= minCount){
                return;
            }

            pressButton(cloned, buttons[i]);

            if (isValid(cloned)) {
                minCount = i + 1;
                return;
            }
        }
    }

    private static void pressButton(int[] cards, int button) {
        if (button == 0) {
            return;
        }

        int halfSize = N >> 1;

        int[] halfLeft = new int[halfSize];
        System.arraycopy(cards, 0, halfLeft, 0, halfSize);

        int[] halfRight = new int[halfSize];
        System.arraycopy(cards, halfSize, halfRight, 0, halfSize);

        if (button < halfSize) {
            merge(cards, halfLeft, halfRight, button);
            return;
        }

        merge(cards, halfRight, halfLeft, N - button - 1);
    }

    private static void merge(int[] cards, int[] halfLeft, int[] halfRight, int button) {
        int halfSize = N >> 1;
        int idx = halfSize - button;
        int left = halfSize - button;
        int right = 0;

        System.arraycopy(halfLeft, 0, cards, 0, halfSize - button);

        for (int i = 0; i < button; i++) {
            cards[idx++] = halfRight[right++];
            cards[idx++] = halfLeft[left++];
        }

        System.arraycopy(halfRight, right, cards, idx, halfSize - button);
    }

    private static boolean isValid(int[] cards) {
        // 오름차순 확인
        int i = N - 1;
        while (i > 0 && cards[i] > cards[i - 1]) {
            i--;
        }

        if (i == 0) {
            return true;
        }

        // 내림차순 확인
        i = N - 1;
        while (i > 0 && cards[i] < cards[i - 1]) {
            i--;
        }

        if (i == 0) {
            return true;
        }

        return false;
    }

}
