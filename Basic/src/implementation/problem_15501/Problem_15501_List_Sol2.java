package implementation.problem_15501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_15501_List_Sol2 {
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
        boolean check = true;
        boolean checkReverse = true;

        for (int i = 1; i < length; i++) {
            if (!playerNums.get(i).equals(givenNums.get((index + i) % length))) {
                check = false;
                break;
            }
        }

        for (int i = 1; i < length; i++) {
            if (!playerNums.get(i).equals(givenNums.get((index + length - i) % length))) {
                checkReverse = false;
                break;
            }
        }

        return (check || checkReverse);
    }
}
