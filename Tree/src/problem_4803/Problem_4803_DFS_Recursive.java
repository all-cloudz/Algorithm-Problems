package problem_4803;

import java.io.*;
import java.util.*;

public class Problem_4803_DFS_Recursive {
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

            Graph graph = new Graph(N);

            final int M = Integer.parseInt(tokenizer.nextToken());
            for (int i = 0; i < M; i++) {
                tokenizer = new StringTokenizer(input.readLine());

                int vertex1 = Integer.parseInt(tokenizer.nextToken());
                int vertex2 = Integer.parseInt(tokenizer.nextToken());

                graph.addCompleteEdge(vertex1, vertex2);
            }

            boolean[] visited = new boolean[N + 1];
            int treeNum = 0;
            for (int i = 1; i <= N; i++){
                if (isTree(graph, visited, i, -1)) {
                    treeNum++;
                }
            }

            if (treeNum == 0) {
                answer.append("Case ").append(caseIdx++).append(": No trees.\n");
                continue;
            }

            if (treeNum == 1) {
                answer.append("Case ").append(caseIdx++).append(": There is one tree.\n");
                continue;
            }

            if (treeNum > 1) {
                answer.append("Case ").append(caseIdx++).append(String.format(": A forest of %d trees.\n", treeNum));
                continue;
            }
        }

        System.out.print(answer);
    }

    private static boolean isTree(Graph graph, boolean[] visited, int cur, int parent) {
        if (visited[cur]) {
            return false;
        }

        visited[cur] = true;

        for (int next : graph.getNeighbor(cur)) {
            if (next == parent) {
                continue;
            }

            // return isTree(graph, visited, next, cur)이라 하면 true가 한 번만 나와도 true를 반환하는 문제가 생긴다.
            // 하지만, 실제로는 false는 한 번만 나와도 false를 반환해야 하고, true는 모든 경우에 true가 나와야 true를 반환해야 하므로 아래와 같이 코드를 작성해야 한다.
            if (!isTree(graph, visited, next, cur)) {
                return false;
            }
        }

        return true;
    }
}

class Graph {
    private List<List<Integer>> adjList;
    private int size;

    public Graph(int vertexNum) {
        this.size = vertexNum;
        adjList = new ArrayList<>();

        for (int i = 0; i <= vertexNum; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public List<List<Integer>> getAdjList() {
        return this.adjList;
    }

    public List<Integer> getNeighbor(int vertex) {
        return this.adjList.get(vertex);
    }

    public int size() {
        return this.size;
    }

    public void addCompleteEdge(int vertex1, int vertex2) {
        adjList.get(vertex1).add(vertex2);
        adjList.get(vertex2).add(vertex1);
    }
}