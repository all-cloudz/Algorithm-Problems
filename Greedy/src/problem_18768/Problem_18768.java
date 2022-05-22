package problem_18768;

import java.io.*;
import java.util.*;

public class Problem_18768 {
    private static class Player implements Comparable<Player> {
        private final int attack;
        private final int defence;

        public Player(int attack, int defence) {
            this.attack = attack;
            this.defence = defence;
        }

        public int getDiff() {
            return this.attack - this.defence;
        }

        @Override
        public int compareTo(Player o) {
            return Math.abs(o.getDiff()) - Math.abs(this.getDiff());
        }
    }

    private static final StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int T = Integer.parseInt(input.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            int n = Integer.parseInt(tokenizer.nextToken());
            int k = Integer.parseInt(tokenizer.nextToken());

            PriorityQueue<Player> players = new PriorityQueue<>();

            StringTokenizer attackTokenizer = new StringTokenizer(input.readLine());
            StringTokenizer defenceTokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < n; j++) {
                Player player = new Player(Integer.parseInt(attackTokenizer.nextToken()), Integer.parseInt(defenceTokenizer.nextToken()));
                players.add(player);
            }

            findMaxSumStats(players, k);
        }

        System.out.println(answer);
    }

    private static void findMaxSumStats(PriorityQueue<Player> players, int k) {
        List<Player> attackers = new ArrayList<>();
        List<Player> defenders = new ArrayList<>();

        distributePlayers(players, attackers, defenders);
        balanceSize(attackers, defenders, k);

        long maxSumStats = calculateSum(attackers, defenders);

        answer.append(maxSumStats).append("\n");
    }

    private static void distributePlayers(PriorityQueue<Player> players, List<Player> attackers, List<Player> defenders) {
        while (!players.isEmpty()) {
            Player player = players.poll();

            if (player.getDiff() > 0) {
                attackers.add(player);
                continue;
            }

            defenders.add(player);
        }
    }

    private static void balanceSize(List<Player> attackers, List<Player> defenders, int k) {
        while (attackers.size() - defenders.size() > k) {
            defenders.add(attackers.remove(attackers.size() - 1));
        }

        while (defenders.size() - attackers.size() > k) {
            attackers.add(defenders.remove(defenders.size() - 1));
        }
    }

    private static long calculateSum(List<Player> attackers, List<Player> defenders) {
        long sum = 0;

        for (int i = 0; i < attackers.size(); i++) {
            sum += attackers.get(i).attack;
        }

        for (int i = 0; i < defenders.size(); i++) {
            sum += defenders.get(i).defence;
        }

        return sum;
    }
}
