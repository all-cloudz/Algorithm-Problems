package problem_16236;

import java.io.*;
import java.util.*;

public class Problem_16236_Review {
    private static class Shark extends Fish {
        private static Shark instance;

        private int countFish;

        private Shark(int size, int[] point) {
            super(size, point);
        }

        public static Shark getInstance(int size, int[] point) {
            if (instance == null) {
                instance = new Shark(size, point);
            }

            return instance;
        }

        public void eatFish(Fish fish) {
            if (size == ++countFish) {
                size++;
                countFish = 0;
            }

            point = fish.point;
            fishes[point[0]][point[1]].size = 0;
        }
    }

    private static class Fish implements Comparable<Fish> {
        protected int size;
        protected int[] point;

        public Fish(int size, int[] point) {
            this.size = size;
            this.point = point;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || !(o instanceof Fish)) {
                return false;
            }

            Fish that = (Fish) o;

            return Arrays.equals(this.point, that.point);
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = 31 * hash + point[0];
            hash = 31 * hash + point[1];

            return hash;
        }

        private int getDistance() {
            return Math.abs(point[0] - shark.point[0]) + Math.abs(point[1] - shark.point[1]);
        }
    }

    private static final int[] DR = { 0, 0, 1, -1 };
    private static final int[] DC = { 1, -1, 0, 0 };

    private static int N;
    private static Fish[][] fishes;
    private static Shark shark;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        fishes = new Fish[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < N; j++) {
                int size = Integer.parseInt(tokenizer.nextToken());

                if (size == 9) {
                    shark = new Shark(2, new int[] { i, j });
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
        int dist = 0;

        Queue<Fish> searchList = new LinkedList<>();
        HashSet<Fish> discovered = new HashSet<>();

        searchList.offer(shark);
        discovered.add(shark);

        while (!searchList.isEmpty()) {
            PriorityQueue<Fish> huntList = new PriorityQueue<>();

            int size = searchList.size();
            for (int i = 0; i < size; i++) {
                Fish cur = searchList.poll();

                for (int j = 0; j < 4; j++) {
                    int nextRow = cur.point[0] + DR[j];
                    int nextCol = cur.point[1] + DC[j];

                    if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) {
                        continue;
                    }

                    Fish next = fishes[nextRow][nextCol];

                    if (discovered.contains(next)) {
                        continue;
                    }

                    if (next.size > shark.size) {
                        continue;
                    }

                    searchList.offer(next);
                    discovered.add(next);

                    if (1 <= next.size && next.size < shark.size) {
                        huntList.offer(next);
                    }
                }
            }

            if (huntList.isEmpty()) {
                dist++;
                continue;
            }

            time += dist + 1;
            shark.eatFish(huntList.poll());
            dist = 0;

            searchList.clear();
            searchList.offer(shark);
            discovered.clear();
        }

        return time;
    }
}
