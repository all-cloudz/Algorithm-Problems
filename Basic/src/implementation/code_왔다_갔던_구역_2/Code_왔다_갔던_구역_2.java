package implementation.code_왔다_갔던_구역_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Code_왔다_갔던_구역_2 {

    private static int cur;
    private static int[] line;

    static {
        cur = 1_000;
        line = new int[2_001];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        while (n-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            String dir = tokenizer.nextToken();
            move(x, dir);
        }

        System.out.println(countOver2Area());
    }

    private static void move(int x, String operation) {
        switch (operation) {
            case "L":
                moveLeft(x);
                return;
            case "R":
                moveRight(x);
                return;
            default:
                throw new RuntimeException("일어날 수 없는 코드");
        }
    }

    private static void moveLeft(int x) {
        for (int i = 0; i < x; i++) {
            line[cur--]++;
        }
    }

    private static void moveRight(int x) {
        for (int i = 0; i < x; i++) {
            line[++cur]++;
        }
    }

    private static int countOver2Area() {
        int cnt = 0;
        for (int dot : line) {
            if (dot >= 2) {
                cnt++;
            }
        }
        return cnt;
    }

}
