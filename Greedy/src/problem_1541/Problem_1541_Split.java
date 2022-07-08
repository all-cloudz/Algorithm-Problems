package problem_1541;

import java.io.*;

public class Problem_1541_Split {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;
        String[] strs = input.readLine().split("[-]");
        answer += sum(strs[0]);

        for (int i = 1; i < strs.length; i++) {
            answer -= sum(strs[i]);
        }

        System.out.print(answer);
    }

    private static int sum(String str) {
        String[] nums = str.split("[+]");

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += Integer.parseInt(nums[i]);
        }

        return sum;

        // 스트림을 사용하면 return Arrays.stream(str.split("[+]")).mapToInt(Integer::parseInt).sum(); 이라고 간결하게 표현할 수 있지만 성능이 약 4배정도 느리다.
        // 간단한 연산을 하거나 기본형 배열을 다룰 때는 스트림에서 성능이 느려지는 것을 감안하면 이 문제는 for-loop로 푸는 것이 합리적!!
    }
}