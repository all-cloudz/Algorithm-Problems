package problem_d4_15231;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D4_15231 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append(" ");

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            int V = Integer.parseInt(tokenizer.nextToken());

            if (N == 1) {
                answer.append("0\n");
                continue;
            }

            if (N == 2) {
                answer.append("1\n");
                continue;
            }

            int heightOfTree = (int) (Math.log(N) / Math.log(2));
            int heightOfNode = (int) (Math.log(V) / Math.log(2));

            int infOfLeaf = (int) Math.pow(2, heightOfTree);
            int supOfLeaf = (int) Math.pow(2, heightOfTree + 1);
            int midOfLead = infOfLeaf + (supOfLeaf - infOfLeaf >> 1);

            if (V == 1) {
                answer.append(heightOfTree).append('\n');
                continue;
            }

            int parent = V;
            while (parent != 2 && parent != 3) {
                parent /= 2;
            }

            if (parent == 3) {
                answer.append(heightOfNode + heightOfTree).append('\n');
                continue;
            }

            if (N >= midOfLead) {
                answer.append(heightOfNode + heightOfTree).append('\n');
                continue;
            }

            answer.append(heightOfNode + heightOfTree - 1).append('\n');
        }

        System.out.println(answer);
    }

}
