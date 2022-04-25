package problem_lv1_92334;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class Problem_Lv1_92334_Sol1 {
    public int[] solution(String[] id_list, String[] reports, int k) {
        //  각 유저가 받을 메일의 수를 저장할 변수 cnts 생성
        HashMap<String, Integer> cnts = new HashMap<>();
        for (String str : id_list) {
            cnts.put(str, 0);
        }

        // 신고를 DAU로 표현
        Graph graph = new Graph(id_list);
        for (String str : reports) {
            String[] report = str.split(" ");
            graph.addDirectEdge(report);
        }

        // 그래프에서 인접한 정점의 개수가 k 이상인 String[] 출력
        ArrayList<String> ban_list = graph.ban(k);

        // ban_list에 속한 사람을 신고한 사람의 받을 메일 수를 추가
        for (String reported : ban_list) {
            HashSet<String> neighbors = graph.getNeighbors(reported);

            for (String reporting : neighbors) {
                cnts.put(reporting, cnts.get(reporting) + 1);
            }
        }

        // 결과 출력
        int[] answer = new int[id_list.length];

        for (int i = 0; i < id_list.length; i++) {
            answer[i] = cnts.get(id_list[i]);
        }

        return answer;
    }
}

class Graph {
    private HashMap<String, HashSet<String>> adjList = new HashMap<>();

    public HashSet<String> getNeighbors(String name) {
        return this.adjList.get(name);
    }

    public Graph(String[] id_list) {
        for (String str : id_list) {
            this.adjList.put(str, new HashSet<>());
        }
    }

    public void addDirectEdge(String[] report) {
        HashSet<String> neighbors = this.adjList.get(report[1]);

        // 중복 리포트 제거
        if (!neighbors.contains(report[0])) {
            neighbors.add(report[0]);
        }
    }

    public ArrayList<String> ban(int k) {
        ArrayList<String> ban_list = new ArrayList<>();

        for (String key : this.adjList.keySet()) {
            if (this.adjList.get(key).size() >= k) {
                ban_list.add(key);
            }
        }

        return ban_list;
    }
}
