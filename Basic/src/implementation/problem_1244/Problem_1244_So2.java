package implementation.problem_1244;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_1244_So2 {
    private static class Reader {
        private final int BUFFER_SIZE = 1 << 16;

        private InputStreamReader reader;
        private char[] buffer;

        public Reader() {
            reader = new InputStreamReader(System.in);
            buffer = new char[BUFFER_SIZE];
        }

        public String readLine() throws IOException {
            int cnt = 0;
            int read = -1;

            while ((read = reader.read()) != -1) {
                if (read == '\n') {
                    break;
                }

                buffer[cnt++] = (char) read;
            }

            return new String(buffer, 0, cnt);
        }
    }

    private static int N;
    private static char[] switches;

    public static void main(String[] args) throws IOException {
        Reader input = new Reader();
        N = Integer.parseInt(input.readLine());
        switches = new char[N + 1];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= N; i++) {
            switches[i] = tokenizer.nextToken().charAt(0);
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
    }

    private static void changeManSwitches(int num) {
        int cur = num;

        while (cur <= N) {
            switches[cur] = (switches[cur] == '0') ? '1' : '0';
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
            switches[i] = (switches[i] == '0') ? '1' : '0';
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
