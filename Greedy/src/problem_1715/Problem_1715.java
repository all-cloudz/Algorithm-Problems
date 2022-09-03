package problem_1715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Problem_1715 {

    private static class Deck implements Comparable<Deck> {
        private int numberOfCard;

        public Deck(int numberOfCard) {
            this.numberOfCard = numberOfCard;
        }

        @Override
        public int compareTo(Deck o) {
            return this.numberOfCard - o.numberOfCard;
        }
    }

    private static int N;
    private static PriorityQueue<Deck> decks;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        decks = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            decks.offer(new Deck(Integer.parseInt(input.readLine())));
        }

        System.out.println(countOfSort());
    }

    private static int countOfSort() {
        int cnt = 0;

        while (decks.size() > 1) {
            Deck card1 = decks.poll();
            Deck card2 = decks.poll();

            int tmp = card1.numberOfCard + card2.numberOfCard;
            cnt += tmp;
            decks.offer(new Deck(tmp));
        }

        return cnt;
    }

}
