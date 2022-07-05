package implementation.problem_2669;

import java.io.*;
import java.util.*;

public class Problem_2669_Sol2 {
    private static int[][] plane = new int[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            int x1 = Integer.parseInt(tokenizer.nextToken());
            int y1 = Integer.parseInt(tokenizer.nextToken());
            int x2 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());

            rectangle(x1, y1, x2, y2);
        }

        System.out.print(area());
    }

    private static void rectangle(int x1, int y1, int x2, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                plane[i][j]++;
            }
        }
    }

    private static int area() {
        int area = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (plane[i][j] > 0) {
                    area++;
                }
            }
        }

        return area;
    }
}
