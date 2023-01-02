package problem_1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_1202_Sol2 {

    private static class Jewel implements Comparable<Jewel> {
        private int weight;
        private int price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Jewel o) {
            return (this.weight == o.weight) ? o.price - this.price : this.weight - o.weight;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int weight = Integer.parseInt(tokenizer.nextToken());
            int price = Integer.parseInt(tokenizer.nextToken());
            jewels[i] = new Jewel(weight, price);
        }
        Arrays.sort(jewels);

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(input.readLine());
        }
        Arrays.sort(bags);

        PriorityQueue<Jewel> stolen = new PriorityQueue<>((a, b) -> b.price - a.price);
        long answer = 0;

        int idx = 0;
        for (int bag : bags) {
            while (idx < N && jewels[idx].weight <= bag) {
                stolen.offer(jewels[idx++]);
            }

            if (!stolen.isEmpty()) {
                answer += stolen.poll().price;
            }
        }

        System.out.println(answer);
    }

}
