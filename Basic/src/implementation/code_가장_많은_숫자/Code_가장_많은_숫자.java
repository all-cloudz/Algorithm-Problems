package implementation.code_가장_많은_숫자;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Code_가장_많은_숫자 {

    private static int n;
    private static int[][] pairs;

    public static void main(String[] args) throws IOException {
//        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader input = new BufferedReader(new FileReader("Basic/src/implementation/code_가장_많은_숫자/input.txt"));

        n = Integer.parseInt(input.readLine());
        pairs = new int[10][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int first = Integer.parseInt(tokenizer.nextToken());
            int second = Integer.parseInt(tokenizer.nextToken());

            pairs[first][i] = 1;
            pairs[second][i] = 1;
        }

        int maxCount = -1;
        int num = 0;
        for (int i = 1; i < 10; i++) {
            int[] used = pairs[i];
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (used[j] == 1) {
                    count++;
                    continue;
                }


                if (count == 6) {
                    System.out.println(i + " " + j);
                }

                if (maxCount < count) {
                    maxCount = count;
                    num = i;
                }

                count = 0;
            }

            if (maxCount < count) {
                maxCount = count;
                num = i;
            }
        }

        System.out.println(maxCount + " " + num);
    }

}
