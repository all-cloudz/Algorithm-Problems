package problem_4803;

import java.io.*;
import java.util.*;

public class Problem_4803_Union_Find {
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

            DisjointSet disjointSet = new DisjointSet(N);

            final int M = Integer.parseInt(tokenizer.nextToken());
            for (int i = 0; i < M; i++) {
                tokenizer = new StringTokenizer(input.readLine());

                int node1 = Integer.parseInt(tokenizer.nextToken());
                int node2 = Integer.parseInt(tokenizer.nextToken());

                disjointSet.union(node1, node2);
            }

            cntTrees(getRootSet(disjointSet), caseIdx++);
        }

        System.out.print(answer);
    }

    private static Set<Integer> getRootSet(DisjointSet disjointSet) {
        int N = disjointSet.getSize();

        Set<Integer> rootSet = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            if (disjointSet.getParent(disjointSet.find(i)) == DisjointSet.INF) {
                continue;
            }

            rootSet.add(disjointSet.find(i));
        }

        return rootSet;
    }

    private static void cntTrees(Set<Integer> treeSet, int caseIdx) {
        int treeNum = treeSet.size();
        if (treeNum == 0) {
            answer.append("Case ").append(caseIdx).append(": No trees.\n");
            return;
        }

        if (treeNum == 1) {
            answer.append("Case ").append(caseIdx).append(": There is one tree.\n");
            return;
        }

        if (treeNum > 1) {
            answer.append("Case ").append(caseIdx).append(": A forest of ").append(treeNum).append(" trees.\n");
            return;
        }
    }
}

class DisjointSet {
    private static class Node {
        private int parent;
        private int size; // 자신과 자식 노드의 총합

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
    private int size;

    public DisjointSet(int nodeNum) {
        this.size = nodeNum;
        trees = new Node[nodeNum + 1];

        for (int i = 1; i <= nodeNum; i++) {
            trees[i] = new Node(i);
        }
    }

    public Node[] getTrees() {
        return this.trees;
    }

    public int getSize() {
        return this.size;
    }

    public int getParent(int idx) {
        return this.trees[idx].getParent();
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
