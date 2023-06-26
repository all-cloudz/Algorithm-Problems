package code_친구의_키;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Code_친구의_키_Sol1 {

    private static int n;
    private static int m;
    private static List<List<Integer>> friends;

    public static void main(String[] args) throws IOException {
        init();
        List<Integer> sorted = sortTopologically();
        printSorted(sorted);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        friends = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            friends.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            friends.get(from).add(to);
        }
    }

    private static List<Integer> sortTopologically() {
        LinkedList<Integer> sorting = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            sortTopologically(i, sorting, visited);
        }

        return sorting;
    }

    private static void sortTopologically(int cur, LinkedList<Integer> sorting, boolean[] visited) {
        if (visited[cur]) {
            return;
        }

        visited[cur] = true;
        friends.get(cur)
               .forEach(next -> sortTopologically(next, sorting, visited));
        sorting.addFirst(cur);
    }

    private static void printSorted(List<Integer> sorted) {
        StringBuilder answer = new StringBuilder();
        sorted.forEach(cur -> answer.append(cur).append(' '));
        System.out.println(answer);
    }

}
