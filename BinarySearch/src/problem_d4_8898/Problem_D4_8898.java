package problem_d4_8898;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_D4_8898 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());

            tokenizer = new StringTokenizer(input.readLine());
            int x1 = Integer.parseInt(tokenizer.nextToken());
            int x2 = Integer.parseInt(tokenizer.nextToken());

            TreeSet<Integer> cows = new TreeSet<>();
            tokenizer = new StringTokenizer(input.readLine());
            while (N-- > 0) {
                cows.add(Integer.parseInt(tokenizer.nextToken()));
            }

            TreeSet<Integer> horses = new TreeSet<>();
            tokenizer = new StringTokenizer(input.readLine());
            while (M-- > 0) {
                horses.add(Integer.parseInt(tokenizer.nextToken()));
            }

            int[] minAndCount = minAndCount(cows, horses);

            answer.append(String.format("#%d %d %d%n", tc, minAndCount[0] + Math.abs(x1 - x2), minAndCount[1]));
        }

        System.out.println(answer);
    }

    private static int[] minAndCount(TreeSet<Integer> cows, TreeSet<Integer> horses) {
        int min = Integer.MAX_VALUE;
        Set<Pair> pairs = new HashSet<>();

        for (Integer cow : cows) {
            Integer closeHorse = horses.ceiling(cow);

            if (closeHorse == null) {
                break;
            }

            int newMin = closeHorse - cow;
            if (min > newMin) {
                min = newMin;
                pairs.clear();
            }

            if (min >= newMin) {
                pairs.add(new Pair(cow, closeHorse));
            }
        }

        for (Integer horse : horses) {
            Integer closeCow = cows.ceiling(horse);

            if (closeCow == null) {
                break;
            }

            int newMin = closeCow - horse;
            if (min > newMin) {
                min = newMin;
                pairs.clear();
            }

            if (min >= newMin) {
                pairs.add(new Pair(closeCow, horse));
            }
        }

        return new int[] { min, pairs.size() };
    }

    private static class Pair {
        private int cow;
        private int horse;

        public Pair(int cow, int horse) {
            this.cow = cow;
            this.horse = horse;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Pair)) {
                return false;
            }

            Pair that = (Pair) o;
            return (this.cow == that.cow) && (this.horse == that.horse);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cow, horse);
        }
    }

}
