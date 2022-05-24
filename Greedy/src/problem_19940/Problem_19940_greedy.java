package problem_19940;

import java.io.*;

public class Problem_19940_greedy {
    private static StringBuilder answer = new StringBuilder();

    private static final int TIME_ADDH = 60;
    private static final int TIME_ADDT = 10;
    private static final int TIME_MINT = -10;
    private static final int TIME_ADDO = 1;
    private static final int TIME_MINO = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int T = Integer.parseInt(input.readLine());

        for (int i = 0; i < T; i++) {
            int time = Integer.parseInt(input.readLine());
            pressButton(time);
        }

        System.out.println(answer);
    }

    private static void pressButton(int time) {
        int sum = 0;

        sum = pressADDH(time, sum);
        sum = pressADDT(time, sum);
        sum = pressMINT(time, sum);
        sum = pressADDO(time, sum);
        sum = pressMINO(time, sum);

        answer.append('\n');
    }

    private static int pressADDH(int time, int sum) {
        return sum + TIME_ADDH * setPress(time, TIME_ADDH);
    }

    private static int pressADDT(int time, int sum) {
        return sum + TIME_ADDT * setPress(time - sum, TIME_ADDT);
    }

    private static int pressMINT(int time, int sum) {
        return sum + TIME_MINT * setPress(time - sum, TIME_MINT);
    }

    private static int pressADDO(int time, int sum) {
        return sum + TIME_ADDO * setPress(time - sum, TIME_ADDO);
    }

    private static int pressMINO(int time, int sum) {
        return sum + TIME_MINO * setPress(time - sum, TIME_MINO);
    }

    private static int setPress(int time, int num) {
        if (time * num <= 0) {
            answer.append(0).append(' ');
            return 0;
        }

        if (num < 0) {
            time *= -1;
            num *= -1;
        }

        int cnt = time / num;

        if (num == TIME_ADDH && time % num > num / 2 + 5) {
            cnt++;
        }

        if (num == TIME_ADDT && time % num > num / 2) {
            cnt++;
        }

        answer.append(cnt).append(' ');

        return cnt;
    }
}
