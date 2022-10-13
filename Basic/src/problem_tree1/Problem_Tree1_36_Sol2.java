package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_36_Sol2 {

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
            return o.location - this.location;
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

        TreeSet<Runner> afterRun = new TreeSet<>();
        while (!runnerSet.isEmpty()) {
            Runner cur = runnerSet.pollFirst();
            Runner next = runnerSet.higher(cur);

            while (next != null && isCatchable(next, cur, T)) {
                runnerSet.pollFirst();
                next = runnerSet.higher(cur);
            }

            afterRun.add(cur);
        }

        System.out.println(afterRun.size());
    }

    private static boolean isCatchable(Runner back, Runner front, int time) {
        if (back.velocity <= front.velocity) {
            return false;
        }

        return (double) (front.location - back.location) / (back.velocity - front.velocity) <= time;
    }

}
