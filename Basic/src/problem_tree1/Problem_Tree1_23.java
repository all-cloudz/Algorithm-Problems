package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_Tree1_23 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int G = Integer.parseInt(tokenizer.nextToken());

        List<Set<Integer>> groups = new ArrayList<>();
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < G; i++) {
            groups.add(new HashSet<>());
            Set<Integer> curGroup = groups.get(i);

            tokenizer = new StringTokenizer(input.readLine());
            int size = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j < size; j++) {
                int member = Integer.parseInt(tokenizer.nextToken());
                curGroup.add(member);
                adjList.get(member).add(i);
            }
        }

        Queue<Integer> invited = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        int answer = 0;
        invited.offer(1);

        while (!invited.isEmpty()) {
            int cur = invited.poll();

            if (visited[cur]) {
                continue;
            }

            visited[cur] = true;
            answer++;

            for (int groupIdx : adjList.get(cur)) {
                Set<Integer> group = groups.get(groupIdx);
                group.remove(cur);

                if (group.size() != 1) {
                    continue;
                }

                invited.addAll(group);
            }
        }

        System.out.println(answer);
    }

}
