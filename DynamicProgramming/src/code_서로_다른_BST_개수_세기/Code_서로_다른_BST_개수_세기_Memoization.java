package code_서로_다른_BST_개수_세기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Code_서로_다른_BST_개수_세기_Memoization {

    private static int[] cache = new int[20];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        countBST(N);

        System.out.println(cache[N]);
    }

    private static int countBST(int cur) {
        if (cache[cur] != 0) {
            return cache[cur];
        }

        if (cur == 0 || cur == 1) {
            return cache[cur] = 1;
        }

        for (int i = 0; i < cur; i++) {
            cache[cur] += countBST(i) * countBST(cur - 1 - i);
        }

        return cache[cur];
    }

}
