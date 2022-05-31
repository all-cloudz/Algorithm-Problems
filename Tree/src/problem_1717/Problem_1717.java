package problem_1717;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Problem_1717 {
    private static class WeightedQuickUnionUF {
        private final int[] parent;
        private final int[] size;
        private int count; // number of components

        public WeightedQuickUnionUF(int n) {
            count = n;
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int getCount() {
            return count;
        }

        public int find(int p) {
            if (p < 0 || p >= parent.length) {
                throw new IllegalArgumentException();
            }

            while (p != parent[p]) {
                p = parent[p];
            }

            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) {
                return;
            }

            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }

            count--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tmps = input.readLine().split(" ");
        int n = Integer.parseInt(tmps[0]);
        int m = Integer.parseInt(tmps[1]);

        WeightedQuickUnionUF unionUF = new WeightedQuickUnionUF(n + 1);

        while (m-- > 0) {
            tmps = input.readLine().split(" ");

            int operator = Integer.parseInt(tmps[0]);
            int a = Integer.parseInt(tmps[1]);
            int b = Integer.parseInt(tmps[2]);

            if (operator == 0) {
                unionUF.union(a, b);
            }

            if (operator == 1) {
                output.write(unionUF.find(a) == unionUF.find(b) ? "YES\n" : "NO\n");
            }
        }

        output.flush();
        input.close();
        output.close();
    }
}
