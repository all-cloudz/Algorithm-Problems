package problem_1541;

import java.io.*;

public class Problem_1541_Substring {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String formula = input.readLine();
        int answer = 0;

        int curIdx = formula.indexOf("-");

        if (curIdx == -1) {
            curIdx = formula.length();
        }

        int num = 0;
        for (int i = 0; i < curIdx; i++) {
            if (formula.charAt(i) == '+') {
                answer += num;
                num = 0;
                continue;
            }

            num = num * 10 + (formula.charAt(i) - '0');

            if (i == curIdx - 1) {
                answer += num;
                num = 0;
                continue;
            }
        }

        int prevIdx = curIdx;
        while (curIdx != formula.length()) {
            curIdx = formula.indexOf("-", prevIdx + 1);

            if (curIdx == -1) {
                curIdx = formula.length();
            }

            num = 0;
            for (int i = prevIdx + 1; i < curIdx; i++) {
                if (formula.charAt(i) == '+') {
                    answer -= num;
                    num = 0;
                    continue;
                }

                num = num * 10 + (formula.charAt(i) - '0');

                if (i == curIdx - 1) {
                    answer -= num;
                }
            }

            prevIdx = curIdx;
        }

        System.out.print(answer);
    }
}
