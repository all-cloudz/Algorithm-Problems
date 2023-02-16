package implementation.problem_2251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_2251 {

    private static final int BUCKET_COUNT = 3;

    private static int[] limits;
    private static boolean[][] visited;
    private static List<Integer> catched;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        limits = new int[BUCKET_COUNT];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < BUCKET_COUNT; i++) {
            limits[i] = Integer.parseInt(tokenizer.nextToken());
        }

        visited = new boolean[limits[0] + 1][limits[1] + 1];
        catched = new ArrayList<>();

        pourWater(0, 0, limits[2]);
        catched.stream()
              .sorted()
              .forEach(c -> answer.append(c).append(' '));
        System.out.println(answer);
    }

    private static void pourWater(int A, int B, int C) {
        if (visited[A][B]) {
            return;
        }

        if (A == 0) {
            catched.add(C);
        }

        visited[A][B] = true;

        pourWaterAToB(A, B, C);
        pourWaterBToA(A, B, C);
        pourWaterAToC(A, B, C);
        pourWaterCToA(A, B, C);
        pourWaterBToC(A, B, C);
        pourWaterCToB(A, B, C);
    }

    private static void pourWaterAToB(int A, int B, int C) {
        if (A + B <= limits[1]) {
            pourWater(0, A + B, C);
            return;
        }

        pourWater(A + B - limits[1], limits[1], C);
    }

    private static void pourWaterBToA(int A, int B, int C) {
        if (A + B <= limits[0]) {
            pourWater(A + B, 0, C);
            return;
        }

        pourWater(limits[0], A + B - limits[0], C);
    }

    private static void pourWaterAToC(int A, int B, int C) {
        if (A + C <= limits[2]) {
            pourWater(0, B, A + C);
            return;
        }

        pourWater(A + C - limits[2], B, limits[2]);
    }

    private static void pourWaterCToA(int A, int B, int C) {
        if (A + C <= limits[0]) {
            pourWater(A + C, B, 0);
            return;
        }

        pourWater(limits[0], B, A + C - limits[0]);
    }

    private static void pourWaterBToC(int A, int B, int C) {
        if (B + C <= limits[2]) {
            pourWater(A, 0, B + C);
            return;
        }

        pourWater(A, B + C - limits[2], limits[2]);
    }

    private static void pourWaterCToB(int A, int B, int C) {
        if (B + C <= limits[1]) {
            pourWater(A, B + C, 0);
            return;
        }

        pourWater(A, limits[1], B + C - limits[1]);
    }

}
