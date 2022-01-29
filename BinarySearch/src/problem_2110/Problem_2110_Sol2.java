package problem_2110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;

public class Problem_2110_Sol2 {
    private static int N;
    private static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tmp = input.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        int[] houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(input.readLine());
        }
        Arrays.sort(houses);

        int answer = binarySearch(houses);
        output.write(String.valueOf(answer));
        output.flush();

        input.close();
        output.close();
    }

    private static int binarySearch(int[] houses) {
        int left = Math.min(houses[1] - houses[0], houses[N - 1] - houses[N - 2]);
        int right = houses[N - 1] - houses[0] + 1;
         /* UpperBound를 구해야 하는 문제일 경우 답이 houses[N - 1] - houses[0]이 될 수 있기 때문에 right는 항상 + 1을 해주는 것이 좋다.
          * 그리고 필요하다면 right - 1을 반환하면 된다. 이게 싫으면 Sol1으로 풀면 된다. */
        int mid = 0;

        while (left < right) {
            mid = left + (right - left) / 2;

            if (isUpperBound(houses, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right - 1;
    }

    private static boolean isUpperBound(int[] houses, int mid) {
        int setWifi = 0;
        int cnt = 1;

        for (int i = 1; i < N; i++) {
            if (houses[i] >= houses[setWifi] + mid) {
                setWifi = i;
                cnt++;
            }
        }

        if (cnt >= C) {
            return true;
        }
        return false;
    }
}
