package problem_4803;

import java.io.*;
import java.util.*;

public class Problem_4803 {
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int caseIdx = 1;
        while (true) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            final int N = Integer.parseInt(tokenizer.nextToken());

            if (N == 0) {
                break;
            }

            DisjointSet set = new DisjointSet(N);

            final int M = Integer.parseInt(tokenizer.nextToken());
            for (int i = 0; i < M; i++) {
                tokenizer = new StringTokenizer(input.readLine());

                int node1 = Integer.parseInt(tokenizer.nextToken());
                int node2 = Integer.parseInt(tokenizer.nextToken());

                set.union(node1, node2);
            }

            Set<Integer> rootSet = new HashSet<>();
            for (int i = 1; i <= N; i++) {
                if (set.getParent(set.find(i)) == DisjointSet.INF) {
                    continue;
                }

                rootSet.add(set.find(i));
            }

            int treeNum = rootSet.size();
            if (treeNum == 0) {
                answer.append("Case ").append(caseIdx++).append(": No trees.\n");
                continue;
            }

            if (treeNum == 1) {
                answer.append("Case ").append(caseIdx++).append(": There is one tree.\n");
                continue;
            }

            if (treeNum > 1) {
                answer.append("Case ").append(caseIdx++).append(": A forest of ").append(treeNum).append(" trees.\n");
                continue;
            }
        }

        System.out.print(answer);
    }
}

class DisjointSet {
    private static class Node {
        private int parent;
        private int size;

        public Node(int parent) {
            this.parent = parent;
            this.size = 1;
        }

        public int getParent() {
            return this.parent;
        }
    }

    public static final int INF = Integer.MAX_VALUE;

    private Node[] trees;

    public Node[] getTrees() {
        return this.trees;
    }
    public int getParent(int idx) {
        return this.trees[idx].getParent();
    }

    public DisjointSet(int nodeNum) {
        trees = new Node[nodeNum + 1];

        for (int i = 1; i <= nodeNum; i++) {
            trees[i] = new Node(i);
        }
    }

    public int find(int node) {
        if (trees[node].parent == INF || trees[node].parent == node) {
            return node;
        }

        return find(trees[node].parent);
    }

    public void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 == root2) {
            trees[root1].parent = INF;
            return;
        }

        Node parent = trees[root1];
        Node child = trees[root2];

        if (parent.parent != INF && (child.parent == INF || parent.size < child.size)) {
            Node tmp = parent;
            parent = child;
            child = tmp;
        }

        child.parent = parent.parent;
        parent.size += child.size;
    }
}
