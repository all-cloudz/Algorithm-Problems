package backtracking.problem_2580;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

public class Problem {
    private static int[] cnts;
    private static int[] answer;

    public static int[] solution(String[] id_list, String[] report, int k) {
        HashMap<String, ArrayList<String>> visited = new HashMap<>();

        final int N = id_list.length;
        cnts = new int[N];
        answer = new int[N];

        for (int i = 0; i < report.length; i++) {
            // 중복 체크
            String[] strs = report[i].split(" ");

            if (visited.containsKey(strs[1])) {
                if (visited.get(strs[1]).contains(strs[0])) {
                    break;
                }

                visited.get(strs[1]).add(strs[0]);
            } else {
                visited.put(strs[1], new ArrayList<>());
                visited.get(strs[1]).add(strs[0]);
            }

            // 신고 기록
            for (int j = 0; j < N; j++) {
                if (id_list[j].equals(strs[1])) {
                    cnts[j]++;
                    break;
                }
            }
        }

        for (String key : visited.keySet()) {
            ArrayList<String> values = visited.get(key);

            if (values.size() >= k) {
                for (String value : values) {
                    for (int i = 0; i < N; i++) {
                        if (id_list[i].equals(value)) {
                            answer[i]++;
                            break;
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] id_list = new String[] {"muzi", "frodo", "apeach", "neo"};
        String[] report = new String[] {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};

        System.out.print(Arrays.toString(solution(id_list, report, 2)));
    }
}
