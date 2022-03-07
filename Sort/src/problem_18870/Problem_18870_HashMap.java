package problem_18870;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Problem_18870_HashMap {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        int[] nums = new int[N];
        int[] copyNums = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            copyNums[i] = nums[i] = Integer.parseInt(tokenizer.nextToken(" "));
        }

        Arrays.sort(nums);

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(N, 1.1f);
        int index = 0;

        for (int i = 0; i < N; i++) {
            int current = nums[i];

            if (!map.containsKey(current)) {
                map.put(current, index++);
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            answer.append(map.get(copyNums[i])).append(' ');
        }

        System.out.print(answer);
    }
}
