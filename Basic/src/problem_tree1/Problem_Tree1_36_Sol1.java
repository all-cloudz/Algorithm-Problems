package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_36_Sol1 {

    private static class Runner implements Comparable<Runner> {
        private int location;
        private int velocity;

        public Runner(int location, int velocity) {
            this.location = location;
            this.velocity = velocity;
        }

        public Runner run(int time) {
            return new Runner(location + velocity * time, velocity);
        }

        @Override
        public int compareTo(Runner o) {
            return this.location - o.location;
        }
    }

    private static class Catch implements Comparable<Catch> {
        private Runner back;
        private Runner front;
        private double catchTime;

        public Catch(Runner back, Runner front) {
            this.back = back;
            this.front = front;
            this.catchTime = Double.MAX_VALUE;

            if (back.velocity > front.velocity) {
                catchTime = (double) (front.location - back.location) / (back.velocity - front.velocity);
            }
        }

        @Override
        public int compareTo(Catch o) {
            return (this.catchTime - o.catchTime > 0) ? 1 : -1 ;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int T = Integer.parseInt(tokenizer.nextToken());

        TreeSet<Runner> runnerSet = new TreeSet<>();
        while (N-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            runnerSet.add(new Runner(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
        }

        PriorityQueue<Catch> minTimeDiff = new PriorityQueue<>();
        for (Runner cur : runnerSet) {
            Runner next = runnerSet.higher(cur);

            if (next != null) {
                minTimeDiff.offer(new Catch(cur, next));
            }
        }

        while (!minTimeDiff.isEmpty()) {
            Catch cur = minTimeDiff.poll();

            if (!runnerSet.contains(cur.back)) {
                continue;
            }

            if (cur.catchTime > T) {
                break;
            }

            runnerSet.remove(cur.back);
            Runner back = runnerSet.lower(cur.front);

            if (back != null) {
                minTimeDiff.offer(new Catch(back, cur.front));
            }
        }

        System.out.println(runnerSet.size());
    }

}
