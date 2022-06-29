package implementation.problem_15501;

import java.io.*;
import java.util.*;

public class Problem_15501_Array {
    private static int[] playerNums;
    private static int[] givenNums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        playerNums = new int[N];
        givenNums = new int[N];

        StringTokenizer tokenizer1 = new StringTokenizer(input.readLine());
        StringTokenizer tokenizer2 = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            playerNums[i] = Integer.parseInt(tokenizer1.nextToken());
            givenNums[i] = Integer.parseInt(tokenizer2.nextToken());
        }

        if (isPossible(N)) {
            System.out.print("good puzzle");
            return;
        }

        System.out.print("bad puzzle");
    }

    private static boolean isPossible(int length) {
        int index = indexOf(givenNums, playerNums[0]);
        return (isPossible_forward(index, length) || isPossible_backward(index, length));
    }

    private static int indexOf(int[] givenNums, int target) {
        for (int i = 0; i < givenNums.length; i++) {
            if (givenNums[i] == target) {
                return i;
            }
        }

        return -1;
    }

    private static boolean isPossible_forward(int index, int length) {
        for (int i = 1; i < length; i++) {
            if (playerNums[i] != givenNums[(index + i) % length]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isPossible_backward(int index, int length) {
        for (int i = 1; i < length; i++) {
            if (playerNums[i] != givenNums[(index + length - i) % length]) {
                return false;
            }
        }

        return true;
    }
}
