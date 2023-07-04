package code_트리의_지름;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Code_트리의_지름_Sol1 {

    private static int v;
    private static List<List<Node>> tree;

    public static void main(String[] args) throws IOException {
        init();
        int[] farthestInfo = getFarthestInfo(1, 0, new boolean[v + 1]);
        int[] diameterInfo = getFarthestInfo(farthestInfo[0], 0, new boolean[v + 1]);
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

    private static int[] getFarthestInfo(int cur, int curSum, boolean[] visited) {
        visited[cur] = true;
        int[] farthestInfo = { cur, curSum };

        for (Node next : tree.get(cur)) {
            if (!visited[next.to]) {
                int[] info = getFarthestInfo(next.to, curSum + next.weight, visited);

                if (farthestInfo[1] < info[1]) {
                    farthestInfo = info;
                }
            }
        }

        return farthestInfo;
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
