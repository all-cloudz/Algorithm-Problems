package problem_lv1_92334;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

public class Problem_Lv1_92334_Sol2 {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        // 중복 리포트 제거
        /* 1. Arrays.stream(String[]).distinct() 또는 list.stream().distinct()는 스트림 내의 중복을 제거하여 스트림을 반환한다.
         * 2. collect(Collectors.toList())는 스트림을 List로 변환한다. */
        List<String> reports = Arrays.stream(report).distinct().collect(Collectors.toList());

        // id_list와 report 정보를 User 개체에 저장
        HashMap<String, User> users = new HashMap<>();


        for (String name : id_list) {
            users.put(name, new User(name));
        }

        for (String rep : reports) {
            String[] strs = rep.split(" ");

            // 사실 위에서 중복 리포트를 제거하지 않아도 여기서 중복 리포트는 알아서 제거되지만 미리 제거하면 반복 횟수를 줄일 수 있다.
            users.get(strs[0]).getReporting().add(strs[1]);
            users.get(strs[1]).getReported().add(strs[0]);
        }

        // User 개체를 순회하며 reported 개수가 k 이상인 개체 탐색
        Collection<User> values = users.values(); // values()는 HashMap의 values를 Collection으로 반환한다.
        HashMap<String, Integer> mailCnts = new HashMap<>(); // 나중에 결과 출력을 위해 메일 받는 횟수를 저장

        for (User user : values) {
            HashSet<String> reported = user.getReported();

            if (reported.size() < k) {
                continue;
            }

            for (String name : reported) {
                mailCnts.put(name, mailCnts.getOrDefault(name, 0) + 1);
            }
        }

        // 결과 출력
        for (int i = 0; i < id_list.length; i++) {
            answer[i] = mailCnts.getOrDefault(id_list[i], 0);
        }

        return answer;
    }
}

class User {
    private String name;
    private HashSet<String> reporting; // user가 리포트 한 사람
    private HashSet<String> reported; // user를 리포트 한 사람

    public User(String name) {
        this.name = name;
        this.reporting = new HashSet<>();
        this.reported = new HashSet<>();
    }

    public HashSet<String> getReporting() {
        return this.reporting;
    }

    public HashSet<String> getReported() {
        return this.reported;
    }
}
