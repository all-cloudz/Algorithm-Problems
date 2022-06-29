package implementation.problem_15501;

import java.io.*;
import java.util.*;

public class Problem_15501_List_Sol1 {
    private static List<Integer> playerNums;
    private static List<Integer> givenNums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        playerNums = new ArrayList<>();
        givenNums = new ArrayList<>();

        StringTokenizer tokenizer1 = new StringTokenizer(input.readLine());
        StringTokenizer tokenizer2 = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            playerNums.add(Integer.parseInt(tokenizer1.nextToken()));
            givenNums.add(Integer.parseInt(tokenizer2.nextToken()));
        }

        if (isPossible(N)) {
            System.out.print("good puzzle");
            return;
        }

        System.out.print("bad puzzle");
    }

    private static boolean isPossible(int length) {
        int index = givenNums.indexOf(playerNums.get(0));
        return (isPossible_forward(index, length) || isPossible_backward(index, length));
    }

    private static boolean isPossible_forward(int index, int length) {
        for (int i = 1; i < length; i++) {
            if (!playerNums.get(i).equals(givenNums.get((index + i) % length))) {
                return false;
            }
        }

        return true;
    }

    private static boolean isPossible_backward(int index, int length) {
        Collections.reverse(givenNums);
        index = (length - 1) - index;
        return isPossible_forward(length, index);
    }
}
