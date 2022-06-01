package problem_4803;

import java.io.*;
import java.util.*;

public class Problem_4803_DFS_Stack {
    private static class Graph {
        private List<List<Integer>> adjList;
        private int size;

        public Graph(int vertexNum) {
            this.size = vertexNum;

            adjList = new ArrayList<>();
            for (int i = 0; i <= vertexNum; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        public void addCompleteEdge(int vertex1, int vertex2) {
            adjList.get(vertex1).add(vertex2);
            adjList.get(vertex2).add(vertex1);
        }
    }

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1;;i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            final int N = Integer.parseInt(tokenizer.nextToken());

            if (N == 0) {
                break;
            }

            Graph graph = new Graph(N);

            final int M = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j < M; j++) {
                tokenizer = new StringTokenizer(input.readLine());

                int vertex1 = Integer.parseInt(tokenizer.nextToken());
                int vertex2 = Integer.parseInt(tokenizer.nextToken());

                graph.addCompleteEdge(vertex1, vertex2);
            }

            cntTrees(getRootList(graph), i);
        }

        System.out.print(answer);
    }

    private static LinkedList<Integer> getRootList(Graph graph) {
        final int N = graph.size;

        LinkedList<Integer> rootList = new LinkedList<>();
        Stack<Integer> vertices = new Stack<>();
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }

            int vertexNum = 0;
            int edgeNum = 0;

            rootList.addFirst(i);
            vertices.push(i);

            while (!vertices.empty()) {
                int cur = vertices.pop();

                if (visited[cur]) {
                    continue;
                }

                visited[cur] = true;
                vertexNum++;

                for (int next : graph.adjList.get(cur)) {
                    if (visited[next]) {
                        continue;
                    }

                    vertices.push(next);
                    edgeNum++;
                }
            }

            // 트리는 V - 1 == E를 만족시켜야 한다.
            if (vertexNum - 1 != edgeNum) {
                rootList.removeFirst();
            }
        }

        return rootList;
    }

    private static void cntTrees(List<Integer> rootList, int caseIdx) {
        int treeNum = rootList.size();

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
