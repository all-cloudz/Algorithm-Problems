package code_친구의_키;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Code_친구의_키_Sol2 {

    private static int n;
    private static int m;
    private static List<List<Integer>> friends;
    private static int[] inDegrees;

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

        inDegrees = new int[n + 1];
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            friends.get(from).add(to);
            inDegrees[to]++;
        }
    }

    private static List<Integer> sortTopologically() {
        List<Integer> sorting = new ArrayList<>();
        Queue<Integer> vertices = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            if (inDegrees[i] == 0) {
                vertices.add(i);
            }
        }

        while (n-- > 0) {
            if (vertices.isEmpty()) {
                throw new RuntimeException("사이클이 있는 그래프입니다.");
            }

            int cur = vertices.poll();
            sorting.add(cur);
            friends.get(cur)
                   .forEach(next -> {
                       if (--inDegrees[next] == 0) {
                           vertices.add(next);
                       }
                   });
        }

        return sorting;
    }

    private static void printSorted(List<Integer> sorted) {
        StringBuilder answer = new StringBuilder();
        sorted.forEach(cur -> answer.append(cur).append(' '));
        System.out.println(answer);
    }

}
