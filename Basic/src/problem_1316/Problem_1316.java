package problem_1316;

import java.io.*;
import java.util.*;

public class Problem_1316 {
    private static final int ALPHABET_NUM = 26;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        nextString : while (N-- > 0) {
            String str = input.readLine();
            boolean[] visited = new boolean[ALPHABET_NUM];

            int idx = str.charAt(0) - 'a';
            visited[idx] = true;

            for (int i = 1; i < str.length(); i++) {
                idx = str.charAt(i) - 'a';

                if (!visited[idx]) {
                    visited[idx] = true;
                    continue;
                }

                if (str.charAt(i - 1) != str.charAt(i)) {
                    continue nextString;
                }
            }

            answer++;
        }

        System.out.print(answer);
    }
}
