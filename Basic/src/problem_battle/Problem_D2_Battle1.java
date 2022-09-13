package problem_battle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_D2_Battle1 {


    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append(" ");

            char[] arr = input.readLine().toCharArray();
            int cnt = checkAlphabet(arr);

            answer.append(cnt).append('\n');
        }

        System.out.println(answer);
    }

    private static int checkAlphabet(char[] arr) {
        char cur = 'a';
        int cnt = 0;

        for (int len = arr.length, i = 0; i < len; cur++, i++) {
            if (arr[i] != cur) {
                return cnt;
            }

            cnt++;
        }

        return cnt;
    }

}
