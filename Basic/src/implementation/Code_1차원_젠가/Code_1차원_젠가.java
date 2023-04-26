package implementation.Code_1차원_젠가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Code_1차원_젠가 {

    private static int n;
    private static int[] blocks;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(input.readLine());
        blocks = new int[n];
        for (int i = 0; i < n; i++) {
            blocks[i] = Integer.parseInt(input.readLine());
        }

        for (int tc = 0; tc < 2; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken()) - 1;
            int to = Integer.parseInt(tokenizer.nextToken());

            int[] result = new int[blocks.length + from - to];
            int idx = 0;
            for (int i = 0; i < blocks.length; i++) {
                if (from <= i && i < to) {
                    continue;
                }

                result[idx++] = blocks[i];
            }

            blocks = result;
        }

        StringBuilder answer = new StringBuilder();
        answer.append(blocks.length).append('\n');
        for (int block : blocks) {
            answer.append(block).append('\n');
        }

        System.out.println(answer);
    }

}
