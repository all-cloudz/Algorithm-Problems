package implementation.code_1차원_폭발_게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Code_1차원_폭발_게임_List {

    private static int N;
    private static int M;
    private static List<Integer> bombs;

    public static void main(String[] args) throws IOException {
        init();
        while (boom());

        StringBuilder answer = new StringBuilder();
        answer.append(bombs.size()).append('\n');
        bombs.forEach(bomb -> answer.append(bomb).append('\n'));
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        bombs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            bombs.add(Integer.parseInt(input.readLine()));
        }
    }

    private static boolean boom() {
        boolean exploded = false;

        int left = 0;
        while (left < bombs.size()) {
            int right = getRight(left);

            if (right - left < M) {
                left = right;
                continue;
            }

            removeBombs(left, right);
            exploded = true;
        }

        return exploded;
    }

    private static int getRight(int left) {
        int idx = left + 1;

        while (idx < bombs.size() && Objects.equals(bombs.get(left), bombs.get(idx))) {
            idx++;
        }

        return idx;
    }

    private static void removeBombs(int left, int right) {
        if (right > left) {
            bombs.subList(left, right).clear();
        }
    }

}
