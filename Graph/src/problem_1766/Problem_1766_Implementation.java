package problem_1766;

import java.io.*;
import java.util.*;

public class Problem_1766_Implementation {
    private static class PriorityQueue {
        private int[] vertices;
        private int size;

        public PriorityQueue() {
            this.vertices = new int[32_001];
            this.size = 0;
        }

        public void offer(int value) {
            vertices[++size] = value;
            shiftUp();
        }

        private void shiftUp() {
            int childIdx = size;
            int target = vertices[childIdx];

            while (childIdx > 1) {
                int parentIdx = childIdx / 2;
                int parent = vertices[parentIdx];

                if (parent <= target) {
                    break;
                }

                vertices[childIdx] = parent;
                childIdx = parentIdx;
            }

            vertices[childIdx] = target;
        }

        public int poll() {
            if (size == 0) {
                throw new NullPointerException();
            }

            int polled = vertices[1];
            vertices[1] = vertices[size];
            vertices[size--] = 0;

            shiftDown();

            return polled;
        }

        private void shiftDown() {
            int parentIdx = 1;
            int target = vertices[parentIdx];

            int childIdx;
            while ((childIdx = parentIdx * 2) <= size) {
                int child = vertices[childIdx];

                int rightChildIdx = parentIdx * 2 + 1;
                int rightChild = vertices[rightChildIdx];

                if (rightChildIdx <= size && child > rightChild) {
                    childIdx = rightChildIdx;
                    child = rightChild;
                }

                if (target <= child) {
                    break;
                }

                vertices[parentIdx] = child;
                parentIdx = childIdx;
            }

            vertices[parentIdx] = target;
        }
    }

    private static class Graph {
        private HashMap<Integer, List<Integer>> adjList;
        private int[] indegrees;
        private int size;

        public Graph(int N) {
            this.adjList = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                adjList.put(i, new ArrayList<>());
            }

            this.indegrees = new int[N + 1];
            this.size = N;
        }

        public void addDirectedEdge(int head, int tail) {
            adjList.get(head).add(tail);
            indegrees[tail]++;
        }
    }

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        final int N = Integer.parseInt(tokenizer.nextToken());
        Graph problems = new Graph(N);

        int M = Integer.parseInt(tokenizer.nextToken());
        while (M-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            int head = Integer.parseInt(tokenizer.nextToken());
            int tail = Integer.parseInt(tokenizer.nextToken());

            problems.addDirectedEdge(head, tail);
        }

        sortTopologically(problems);
        System.out.print(answer);
    }

    private static void sortTopologically(Graph problems) {
        PriorityQueue vertices = new PriorityQueue();

        for (int i = 1; i <= problems.size; i++) {
            if (problems.indegrees[i] == 0) {
                vertices.offer(i);
            }
        }

        for (int i = 1; i <= problems.size; i++) {
//            if (vertices.isEmpty()) {
//                System.out.print("Has Cycle");
//                System.exit(-1);
//            }

            int cur = vertices.poll();
            answer.append(cur).append(' ');

            for (int nxt : problems.adjList.get(cur)) {
                if (--problems.indegrees[nxt] == 0) {
                    vertices.offer(nxt);
                }
            }
        }
    }
}
