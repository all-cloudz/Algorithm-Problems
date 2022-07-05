package backtracking.problem_2309;

import java.io.*;
import java.util.*;

public class Problem_2309 {
    private static int[] dwarfs = new int[9];
    private static boolean[] visited = new boolean[9];
    private static List<Integer> realDwarfs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            dwarfs[i] = Integer.parseInt(input.readLine());
        }

        backtracking(0, 0);
    }

    private static void backtracking(int idx, int cnt) {
        if (cnt == 7) {
            if (sum() == 100) {
                print();
                System.exit(0);
            }

            return;
        }

        if (idx >= 9) {
            return;
        }

        if (!visited[idx]) {
            realDwarfs.add(dwarfs[idx]);
            visited[idx] = true;
            backtracking(idx + 1, cnt + 1);

            realDwarfs.remove(realDwarfs.size() - 1);
            visited[idx] = false;
        }

        backtracking(idx + 1, cnt);
    }

    private static int sum() {
        int sum = 0;

        for (int num : realDwarfs) {
            sum += num;
        }

        return sum;
        // 스트림과 람다를 이용하면 return list.stream().mapToInt(Integer::intValue).sum(); 로 메서드를 간단히 만들 수 있다.
        // 하지만 순차 스트림의 경우 기본형이거나 함수 계산 비용이 작다면 for-loop에 비해 성능 저하가 있으므로 이 문제에서는 사용할 필요가 없다.
    }

    private static void print() {
        StringBuilder answer = new StringBuilder();

        Collections.sort(realDwarfs);
        for (int num : realDwarfs) {
            answer.append(num).append('\n');
        }

        System.out.print(answer);
    }
}
