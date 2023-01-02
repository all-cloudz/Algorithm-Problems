package problem_1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_1202 {

    private static class Jewel implements Comparable<Jewel> {

        private int weight;
        private int price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Jewel o) {
            return (this.weight == o.weight) ? o.price - this.price : o.weight - this.weight;
        }

    }

    private static class Bag {

        private int limit;
        private Jewel jewel;

        public Bag(int limit) {
            this.limit = limit;
        }

        public int value() {
            return (jewel == null) ? 0 : jewel.price;
        }

    }

    private static PriorityQueue<Jewel> jewels;
    private static PriorityQueue<Bag> emptyBags;
    private static PriorityQueue<Bag> filledBags;

    static {
        jewels = new PriorityQueue<>();
        emptyBags = new PriorityQueue<>((a, b) -> b.limit - a.limit);
        filledBags = new PriorityQueue<>((a, b) -> (a.value() == b.value()) ? b.limit - a.limit : a.value() - b.value());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        while (N-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            int weight = Integer.parseInt(tokenizer.nextToken());
            int price = Integer.parseInt(tokenizer.nextToken());
            jewels.offer(new Jewel(weight, price));
        }

        while (K-- > 0) {
            int limit = Integer.parseInt(input.readLine());
            emptyBags.offer(new Bag(limit));
        }

        while (!jewels.isEmpty()) {
            Jewel heavyJewel = jewels.poll();

            if (!emptyBags.isEmpty()) {
                Bag strongBag = emptyBags.peek();

                if (strongBag.limit >= heavyJewel.weight) {
                    emptyBags.peek().jewel = heavyJewel;
                    filledBags.offer(emptyBags.poll());
                    continue;
                }
            }

            if (!filledBags.isEmpty()) {
                Bag valuelessBag = filledBags.peek();

                if (valuelessBag.value() < heavyJewel.price && valuelessBag.limit >= heavyJewel.weight) {
                    Bag polled = filledBags.poll();
                    jewels.offer(polled.jewel);

                    polled.jewel = heavyJewel;
                    filledBags.offer(polled);
                }
            }
        }

        System.out.println(filledBags.stream()
                                     .mapToLong(a -> (long) a.value())
                                     .sum());
    }

}
