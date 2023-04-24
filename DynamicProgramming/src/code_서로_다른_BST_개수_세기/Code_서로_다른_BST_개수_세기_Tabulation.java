package code_서로_다른_BST_개수_세기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Code_서로_다른_BST_개수_세기_Tabulation {

    private static int[] cache = new int[20];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        cache[0] = 1;
        cache[1] = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                cache[i] += cache[j] * cache[i - 1 - j];
            }
        }

        System.out.println(cache[N]);
    }

}
