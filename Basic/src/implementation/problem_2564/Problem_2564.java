package implementation.problem_2564;

import java.io.*;
import java.util.*;

public class Problem_2564 {
    private static boolean[][] block;

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int width = Integer.parseInt(tokenizer.nextToken());
        int height = Integer.parseInt(tokenizer.nextToken());
        block = new boolean[height + 1][width + 1];

        final int N = Integer.parseInt(input.readLine());
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int direction = Integer.parseInt(tokenizer.nextToken());
            int position = Integer.parseInt(tokenizer.nextToken());
            record(direction, position);
        }


        tokenizer = new StringTokenizer(input.readLine());
        int direction = Integer.parseInt(tokenizer.nextToken());
        int position = Integer.parseInt(tokenizer.nextToken());
        int[] person = record(direction, position);

        System.out.print(minSum(person, N));
    }

    private static int[] record(int direction, int position) {
        int row = 0;
        int col = 0;

        switch (direction) {
            case 1 :
                col = position;
                break;
            case 2 :
                row = block.length - 1;
                col = position;
                break;
            case 3 :
                row = position;
                break;
            case 4 :
                col = block[0].length - 1;
                row = position;
                break;
        }

        block[row][col] = true;
        return new int[] {row, col};
    }

    private static int minSum(int[] person, int merchantNum) {
        int totalLength = 2 * (block.length + block[0].length - 2);

        int cnt = 0;
        int sum = 0;

        while (cnt < merchantNum) {
            int dist = traverseClockwise(person);
            sum += Math.min(dist, totalLength - dist);
            cnt++;
        }

        return sum;
    }

    private static int traverseClockwise(int[] person) {
        int row = person[0];
        int col = person[1];
        int dist = 0;

        block[row][col] = false;
        while (!block[row][col]) {
            int[] moved = move(row, col);
            row = moved[0];
            col = moved[1];
            dist++;
        }

        block[row][col] = false;
        return dist;
    }

    private static int[] move(int row, int col) {
        if (row == 0 && col < block[0].length - 1) {
            col++;
        } else if (col == block[0].length - 1 && row < block.length - 1) {
            row++;
        } else if (row == block.length - 1 && 0 < col) {
            col--;
        } else {
            row--;
        }

        return new int[] {row, col};
    }
}
