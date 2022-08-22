package implementation.problem_d3_4047;

import java.io.*;
import java.util.*;

public class Problem_D3_4047_Sol2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        loop : for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append(' ');
            Map<Character, Set<Integer>> cards = new HashMap<>();
            cards.put('S', new HashSet<>());
            cards.put('D', new HashSet<>());
            cards.put('H', new HashSet<>());
            cards.put('C', new HashSet<>());

            String str = input.readLine();
            for (int len = str.length(), i = 0; i < len; i += 3) {
                char card = str.charAt(i);
                int number = Integer.parseInt(str.substring(i + 1, i + 3));

                if (cards.get(card).contains(number)) {
                    answer.append("ERROR").append('\n');
                    continue loop;
                }

                cards.get(card).add(number);
            }

            answer.append(13 - cards.get('S').size()).append(' ')
                    .append(13 - cards.get('D').size()).append(' ')
                    .append(13 - cards.get('H').size()).append(' ')
                    .append(13 - cards.get('C').size()).append('\n');
        }

        System.out.println(answer);
    }
}
