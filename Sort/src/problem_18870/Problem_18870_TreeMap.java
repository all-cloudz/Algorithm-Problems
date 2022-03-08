package problem_18870;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeMap;

/* HashMap과 달리 TreeMap은 Red-Black Tree로 구현되어 있으므로 Node를 put하면 알아서 정렬된다.
 * 즉, key에 대한 순차적 접근이 필요할 때는 TreeMap이 유리하다.
 * 이 문제는 key에 대한 순차적 접근이 필요하지는 않지만 TreeMap으로 해결하면 다음과 같다. */

public class Problem_18870_TreeMap {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        int[] nums = new int[N];
        int[] copyNums = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            copyNums[i] = nums[i] = Integer.parseInt(tokenizer.nextToken(" "));
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            int current = nums[i];

            if (!map.containsKey(current)) {
                map.put(current, 0);
            }
        }

        int index = 0;
        for (Integer i : map.keySet()) {
            map.put(i, map.get(i) + index);
            index++;
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            answer.append(map.get(copyNums[i])).append(' ');
        }

        System.out.print(answer);
    }
}
