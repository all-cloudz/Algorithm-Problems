package problem_lv1_150370;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_Lv1_150370 {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public int[] solution(String today, String[] terms, String[] privacies) {
        LocalDate todayDate = LocalDate.parse(today, dateTimeFormatter);
        Map<String, Integer> termMap = makeTermMap(terms);
        return getTrash(todayDate, termMap, privacies);
    }

    private int[] getTrash(LocalDate today, Map<String, Integer> terms, String[] privacies) {
        List<Integer> trashed = new ArrayList<>();

        for (int length = privacies.length, i = 0; i < length; i++) {
            String[] privacy = privacies[i].split(" ");
            LocalDate after = afterPrivacy(privacy, terms);

            if (today.isAfter(after)) {
                trashed.add(i);
            }
        }

        return trashed.stream()
                      .mapToInt(i -> i + 1)
                      .toArray();
    }

    private Map<String, Integer> makeTermMap(String[] terms) {
        Map<String, Integer> termMap = new HashMap<>();

        for (String term : terms) {
            String[] cur = term.split(" ");
            termMap.put(cur[0], Integer.parseInt(cur[1]));
        }

        return termMap;
    }

    private LocalDate afterPrivacy(String[] privacy, Map<String, Integer> terms) {
        LocalDate startDate = LocalDate.parse(privacy[0], dateTimeFormatter);
        Integer duration = terms.get(privacy[1]);

        LocalDate endDate = startDate.plusMonths(duration).minusDays(1);
        if (endDate.getDayOfMonth() > 28) {
            endDate = endDate.withDayOfMonth(28);
        }

        return endDate;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Problem_Lv1_150370().solution("2022.05.19", new String[] { "A 6", "B 12", "C 3" },
                                                                             new String[] { "2021.05.02 A", "2021.07.01 B", "2022.02.19 C",
                                                                                            "2022.02.20 C" })));
    }

}
