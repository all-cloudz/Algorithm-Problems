package problem_2138;

import java.io.*;

public class Problem_2138 {
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        char[] src = input.readLine().toCharArray();
        char[] dest = input.readLine().toCharArray();

        if (isPossible(src.clone(), dest)) {
            System.out.print(answer);
            return;
        }

        answer = 0;
        press(src, 0);
        if (isPossible(src, dest)) {
            System.out.print(answer);
            return;
        }

        System.out.print(-1);
    }

    private static boolean isPossible(char[] src, char[] dest) {
        for (int i = 1; i < src.length; i++) {
            if (src[i - 1] != dest[i - 1]) {
                press(src, i);
            }
        }

        for (int i = 0; i < src.length; i++) {
            if (src[i] != dest[i]) {
                return false;
            }
        }

        return true;
    }

    private static void press(char[] src, int idx) {
        answer++;

        change(src, idx - 1);
        change(src, idx);
        change(src, idx + 1);
    }

    private static void change(char[] src, int idx) {
        if (idx < 0 || idx >= src.length) {
            return;
        }

        if (src[idx] == '0') {
            src[idx] = '1';
            return;
        }

        src[idx] = '0';
    }
}
