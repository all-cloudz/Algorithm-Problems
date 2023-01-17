package problem_lv4_150364;

import java.util.TreeSet;

public class Problem_Lv4_150364 {

    private static class Node {
        private int label;
        private TreeSet<Integer> children;
        private int nextChild;

        public Node(int label) {
            this.label = label;
            this.children = new TreeSet<>();
        }

        public void changeNextChild() {
            Integer newNext = children.higher(nextChild);

            if (newNext == null) {
                nextChild = children.first();
                return;
            }

            nextChild = newNext;
        }
    }

    private Node[] tree;

    public int[] solution(int[][] edges, int[] target) {
        initTree(edges);

        int[] blocks = new int[edges.length + 1];
        while (possible(blocks, target)) {

        }
    }

    private void initTree(int[][] edges) {
        int nodeCount = edges.length + 1;

        tree = new Node[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            tree[i] = new Node(i);
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            tree[from].children.add(to);
        }

        for (int i = 0; i < nodeCount; i++) {
            tree[i].changeNextChild();
        }
    }

    private boolean possible(int[] blocks, int[] target) {
        for (int i = 0; i < )
    }

}
