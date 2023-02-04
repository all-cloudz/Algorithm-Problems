package problem_d3_1230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Problem_D3_1230 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            answer.append('#').append(tc).append(' ');

            input.readLine();
            LinkedList<String> encrypted = Arrays.stream(input.readLine().split(" "))
                                                 .collect(Collectors.toCollection(LinkedList::new));

            input.readLine();
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            while (tokenizer.hasMoreTokens()) {
                String operator = tokenizer.nextToken();

                switch (operator) {
                    case "I":
                        insert(tokenizer, encrypted);
                        continue;
                    case "D":
                        delete(tokenizer, encrypted);
                        continue;
                    case "A":
                        add(tokenizer, encrypted);
                }
            }

            for (int i = 0; i < 10; i++) {
                answer.append(encrypted.pollFirst()).append(' ');
            }
            answer.append('\n');
        }

        System.out.println(answer);
    }

    private static void insert(StringTokenizer tokenizer, LinkedList<String> encrypted) {
        int x = Integer.parseInt(tokenizer.nextToken());
        int y = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < y; i++) {
            String value = tokenizer.nextToken();
            encrypted.add(x + i, value);
        }
    }

    private static void delete(StringTokenizer tokenizer, LinkedList<String> encrypted) {
        int x = Integer.parseInt(tokenizer.nextToken());
        int y = Integer.parseInt(tokenizer.nextToken());

        for (int i = y - 1; i >= 0; i--) {
            encrypted.remove(x + y);
        }
    }

    private static void add(StringTokenizer tokenizer, LinkedList<String> encrypted) {
        int y = Integer.parseInt(tokenizer.nextToken());

        LinkedList<String> adding = new LinkedList<>();
        while (y-- > 0) {
            String value = tokenizer.nextToken();
            adding.addFirst(value);
        }

        encrypted.addAll(adding);
    }

}
