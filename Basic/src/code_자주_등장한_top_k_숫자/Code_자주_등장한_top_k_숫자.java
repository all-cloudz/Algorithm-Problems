package code_자주_등장한_top_k_숫자;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Code_자주_등장한_top_k_숫자 {

    private static final Comparator<Entry<Integer, Integer>> ENTRY_COMPARATOR = (e1, e2) -> {
        if (!Objects.equals(e1.getValue(), e2.getValue())) {
            return e2.getValue() - e1.getValue();
        }

        return e2.getKey() - e1.getKey();
    };

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int k = input.nextInt();

        Map<Integer, Integer> numToCnt = new HashMap<>();
        while (n-- > 0) {
            int cur = input.nextInt();
            numToCnt.merge(cur, 1, (value, newValue) -> value + 1);
        }

        List<Integer> topK = numToCnt.entrySet()
                                     .stream()
                                     .sorted(ENTRY_COMPARATOR)
                                     .map(Entry::getKey)
                                     .limit(k)
                                     .collect(Collectors.toList());

        StringBuilder answer = new StringBuilder();
        topK.forEach(cur -> answer.append(cur).append(' '));
        System.out.println(answer);
    }

}
