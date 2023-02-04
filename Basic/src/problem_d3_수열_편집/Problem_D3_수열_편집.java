package problem_d3_수열_편집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Problem_D3_수열_편집 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            answer.append('#').append(tc).append(' ');
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            tokenizer.nextToken();
            int M = Integer.parseInt(tokenizer.nextToken());
            int L = Integer.parseInt(tokenizer.nextToken());

            LinkedList<String> sequence = Arrays.stream(input.readLine().split(" "))
                                                .collect(Collectors.toCollection(LinkedList::new));

            while (M-- > 0) {
                tokenizer = new StringTokenizer(input.readLine());
                String operator = tokenizer.nextToken();

                switch (operator) {
                    case "I":
                        add(tokenizer, sequence);
                        continue;
                    case "D":
                        delete(tokenizer, sequence);
                        continue;
                    case "C":
                        insert(tokenizer, sequence);
                }
            }

            if (sequence.size() <= L) {
                answer.append(-1).append('\n');
                continue;
            }

            answer.append(sequence.get(L)).append('\n');
        }

        System.out.println(answer);
    }

    private static void add(StringTokenizer tokenizer, LinkedList<String> sequence) {
        int index = Integer.parseInt(tokenizer.nextToken());
        String value = tokenizer.nextToken();
        sequence.add(index, value);
    }

    private static void delete(StringTokenizer tokenizer, LinkedList<String> sequence) {
        int index = Integer.parseInt(tokenizer.nextToken());
        sequence.remove(index);
    }

    private static void insert(StringTokenizer tokenizer, LinkedList<String> sequence) {
        int index = Integer.parseInt(tokenizer.nextToken());
        String value = tokenizer.nextToken();
        sequence.set(index, value);
    }

}
