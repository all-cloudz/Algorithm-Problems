package problem_chap04_2;

import java.io.*;

public class Problem_Chap04_2 {
    private static int answer = 0;
    private static final int[][] moves = {{2, -1}, {2, 1}, {-2, -1}, {-2, 1}, {1, -2}, {-1, -2}, {1, 2}, {-1, 2}};

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String pos = input.readLine();
        int row = pos.charAt(1) - '1';
        int col = pos.charAt(0) - 'a';

        for (int i = 0; i < moves.length; i++) {
            if (isPossibleMove(row, col, i)) {
                answer++;
            }
        }

        System.out.print(answer);
    }

    private static boolean isPossibleMove(int row, int col, int i) {
        row += moves[i][0];
        col += moves[i][1];

        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
            return false;
        }

        return true;
    }
}
