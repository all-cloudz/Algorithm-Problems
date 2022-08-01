package implementation.problem_1244;

import java.io.*;
import java.util.*;

public class Problem_1244_Sol1 {
    private static int N;
    private static int[] switches;

    public static void main(String[] args) {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(input.readLine());
            switches = new int[N + 1];

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int i = 1; i <= N; i++) {
                switches[i] = Integer.parseInt(tokenizer.nextToken());
            }

            int cnt = Integer.parseInt(input.readLine());
            while (cnt-- > 0) {
                tokenizer = new StringTokenizer(input.readLine());
                int sex = Integer.parseInt(tokenizer.nextToken());
                int num = Integer.parseInt(tokenizer.nextToken());

                if (sex == 1) {
                    changeManSwitches(num);
                    continue;
                }

                changeWomanSwitches(num);
            }

            printSwitch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void changeManSwitches(int num) {
        int cur = num;

        while (cur <= N) {
            switches[cur] = (switches[cur] + 1) % 2;
            cur += num;
        }
    }

    private static void changeWomanSwitches(int num) {
        int left = num - 1;
        int right = num + 1;

        while (left > 0 && right <= N && switches[left] == switches[right]) {
            left--;
            right++;
        }

        for (int i = left + 1; i < right; i++) {
            switches[i] = (switches[i] + 1) % 2;
        }
    }

    private static void printSwitch() {
        StringBuilder answer = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            answer.append(switches[i]).append(" ");

            if (i % 20 == 0) {
                answer.append('\n');
            }
        }

        System.out.println(answer);
    }
}
