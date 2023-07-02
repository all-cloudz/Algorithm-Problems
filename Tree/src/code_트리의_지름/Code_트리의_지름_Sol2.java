package code_트리의_지름;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Code_트리의_지름_Sol2 {

    private static int v;
    private static List<List<Node>> tree;

    public static void main(String[] args) throws IOException {
        init();
        int[] farthestInfo = getFarthestInfo(1);
        int[] diameterInfo = getFarthestInfo(farthestInfo[0]);
        System.out.println(diameterInfo[1]);
    }

    private static void init() throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            v = Integer.parseInt(input.readLine());
            tree = new ArrayList<>();
            for (int i = 0; i <= v; i++) {
                tree.add(new ArrayList<>());
            }

            for (int i = 1; i < v; i++) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());
                int node1 = Integer.parseInt(tokenizer.nextToken());
                int node2 = Integer.parseInt(tokenizer.nextToken());
                int weight = Integer.parseInt(tokenizer.nextToken());
                tree.get(node1).add(new Node(node2, weight));
                tree.get(node2).add(new Node(node1, weight));
            }
        }
    }

    private static int[] getFarthestInfo(int begin) {
        Stack<Integer> nodes = new Stack<>();
        int[] dists = new int[v + 1];
        Arrays.fill(dists, -1);

        nodes.add(begin);
        dists[begin] = 0;

        while (!nodes.isEmpty()) {
            int cur = nodes.pop();

            for (Node next : tree.get(cur)) {
                if (dists[next.to] == -1) {
                    nodes.add(next.to);
                    dists[next.to] = dists[cur] + next.weight;
                }
            }
        }

        return getMaxInfo(dists);
    }

    private static int[] getMaxInfo(int[] dists) {
        int maxArg = -1;
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= v; i++) {
            if (max < dists[i]) {
                maxArg = i;
                max = dists[i];
            }
        }

        return new int[] { maxArg, max };
    }

    private static class Node {
        private int to;
        private int weight;

        public Node(int to) {
            this(to, 0);
        }

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

}
