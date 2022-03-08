package problem_9184;

import java.util.Scanner; // 성능을 올리려면 BufferedReader를 사용하면 되지만 연습삼아 Scanner 클래스 사용

public class Program_9184 {
    private static final int SIZE = 20;
    private static int[][][] cache = new int[SIZE + 1][SIZE + 1][SIZE + 1];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder answer = new StringBuilder();

        while (input.hasNext()) { // 사실 이 문제에서는 EOF가 필요하지는 않음
            int a = input.nextInt();
            int b = input.nextInt();
            int c = input.nextInt();

            if (a == -1 && b == -1 && c == -1) {
                break;
            }

            answer.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(w(a, b, c)).append("\n");
        }

        System.out.print(answer);
    }

    private static int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }

        if (a > 20 || b > 20 || c > 20) {
            return w(20, 20, 20);
        }

        if (cache[a][b][c] != 0) {
            return cache[a][b][c];
        }

        if (a < b && b < c) {
            return cache[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        }

        return cache[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }


}
