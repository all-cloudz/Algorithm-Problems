package problem_2370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_2370 {

    private static int N;
    private static int[][] intervals;
    private static TreeSet<Integer> coordinates;
    private static Map<Integer, Integer> compressed;
    private static int[] posters;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        intervals = new int[N + 1][2];
        coordinates = new TreeSet<>();
        for (int i = 1; i <= N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int min = Integer.parseInt(tokenizer.nextToken());
            int max = Integer.parseInt(tokenizer.nextToken());

            intervals[i] = new int[] { min, max };
            coordinates.add(min);
            coordinates.add(max);
        }

        compress();
        setPosters();
        System.out.println(countPoster());
    }

    private static void compress() {
        compressed = new HashMap<>();

        int cnt = 1;
        for (int coordinate : coordinates) {
            compressed.put(coordinate, cnt++);
        }
    }

    private static void setPosters() {
        posters = new int[coordinates.size() + 1];

        for (int i = 1; i <= N; i++) {
            int[] interval = intervals[i];
            int left = compressed.get(interval[0]);
            int right = compressed.get(interval[1]);

            while (left <= right) {
                posters[left++] = i;
            }
        }
    }

    private static int countPoster() {
        Set<Integer> posterSet = new HashSet<>();

        for (int poster : posters) {
            if (poster != 0) {
                posterSet.add(poster);
            }
        }

        return posterSet.size();
    }

}
