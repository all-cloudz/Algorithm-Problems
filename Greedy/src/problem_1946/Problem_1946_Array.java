package problem_1946;

import java.io.*;
import java.util.*;

public class Problem_1946_Array {
    private static StringBuilder answer = new StringBuilder();
    private static int[] interviews; // index는 서류심사 결과를 활용 = 자동 정렬과 같은 기능

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(input.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(input.readLine());
            interviews = new int[N + 1];

            while (N-- > 0) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());
                interviews[Integer.parseInt(tokenizer.nextToken())] = Integer.parseInt(tokenizer.nextToken());
            }

            answer.append(select()).append('\n');
        }

        System.out.print(answer);
    }

    private static int select() {
        int num = 1;
        int idx = 1;

        for (int i = 2; i < interviews.length; i++) {
            if (interviews[idx] < interviews[i]) {
                continue;
            }

            num++;
            idx = i;
        }

        return num;
    }
}
