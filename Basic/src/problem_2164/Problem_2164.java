package problem_2164;

import java.util.*;

public class Problem_2164 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();

        Deque<Integer> cards = new ArrayDeque<>();
        for (int i = N; i > 0; i--) {
            cards.push(i);
        }

        int cnt = 0;
        while (cards.size() != 1) {
            if (cnt++ % 2 == 0) {
                cards.pop();
                continue;
            }

            cards.offerLast(cards.pop());
        }

        System.out.println(cards.pop());
    }
}
