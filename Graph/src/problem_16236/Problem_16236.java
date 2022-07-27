package problem_16236;

import java.io.*;
import java.util.*;

public class Problem_16236 {
    private static class Fish implements Comparable<Fish> {
        private int size;
        private int[] point;
        private int cntEat;

        public Fish(int size, int[] point) {
            this.size = size;
            this.point = point;
        }

        public int getDistance() {
            return Math.abs(point[0] - shark.point[0]) + Math.abs(point[1] - shark.point[1]);
        }

        public int eatFish(Fish fish) {
            int dist = fish.getDistance();

            if (shark.size == ++cntEat) {
                shark.size++;
                cntEat = 0;
            }

            shark.point = fish.point;
            fish.size = 0;

            return dist;
        }

        @Override
        public int compareTo(Fish o) {
            int distThis = this.getDistance();
            int distO = o.getDistance();

            if (distThis == distO) {
                if (this.point[0] == o.point[0]) {
                    return this.point[1] - o.point[1];
                }

                return this.point[0] - o.point[0];
            }

            return distThis - distO;
        }
    }

    private static final int[] dr = { 0, 0, 1, -1 };
    private static final int[] dc = { 1, -1, 0, 0 };

    private static int N;
    private static Fish[][] fishes;
    private static Fish shark;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        fishes = new Fish[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < N; j++) {
                int size = Integer.parseInt(tokenizer.nextToken());

                if (size == 9) {
                    shark = new Fish(2, new int[] { i, j });
                    fishes[i][j] = new Fish(0, new int[] { i, j });
                    continue;
                }

                fishes[i][j] = new Fish(size, new int[] { i, j });
            }
        }

        System.out.println(huntTime());
    }

    private static int huntTime() {
        int time = 0;
        List<PriorityQueue<Fish>> levels = new ArrayList<>();

        Queue<Fish> searchList = new LinkedList<>();
        boolean[][] discovered = new boolean[N][N];

        searchList.offer(shark);
        discovered[shark.point[0]][shark.point[1]] = true;

        while (!searchList.isEmpty()) {
            PriorityQueue<Fish> huntList = new PriorityQueue<>();

            int size = searchList.size();
            for (int i = 0; i < size; i++){
                Fish cur = searchList.poll();

                for (int j = 0; j < 4; j++) {
                    int nextRow = cur.point[0] + dr[j];
                    int nextCol = cur.point[1] + dc[j];

                    if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) {
                        continue;
                    }

                    if (discovered[nextRow][nextCol]) {
                        continue;
                    }

                    Fish next = fishes[nextRow][nextCol];
                    discovered[nextRow][nextCol] = true;

                    if (next.size > shark.size) {
                        continue;
                    }

                    searchList.offer(next);

                    if (1 <= next.size && next.size < shark.size) {
                        huntList.offer(next);
                    }
                }
            }

            if (huntList.isEmpty()) {
                levels.add(huntList);
                continue;
            }

            time += levels.size() + 1;
            shark.eatFish(huntList.poll());

            levels.clear();
            searchList.clear();
            searchList.offer(shark);
            discovered = new boolean[N][N];
        }

        return time;
    }
}
