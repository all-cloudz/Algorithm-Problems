package problem_2751;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/* Arrays.sort()는 기본형 배열에 대해 dual-pivot quicksort를 수행하므로 최악 시간복잡도가 O(n^2)이다.
 * 따라서 반드시 O(nlogn)의 시간복잡도를 보장해야 한다면 Collections.sort()를 활용해야 한다. */
public class Problem_2751_Collections {
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        List<Integer> nums = new ArrayList<>(300000);

        for (int i = 0; i < N; i++) {
            nums.add(Integer.parseInt(input.readLine()));
        }

        Collections.sort(nums);

        for (int i = 0; i < N; i++) {
            answer.append(nums.get(i)).append('\n');
        }

        System.out.print(answer);
    }
}
