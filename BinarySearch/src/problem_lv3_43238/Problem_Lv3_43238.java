package problem_lv3_43238;

import java.util.Arrays;

// 구하려는 것 : 총 심사 시간의 최솟값(LBD) ; 과정은 관심 없음
// 핵심 아이디어 : 예상 총 심사 시간 / 심사관의 심사 시간 == 해당 심사관의 총 심사 인원
public class Problem_Lv3_43238 {
    public static long solution(int n, int[] times) {
        Arrays.sort(times);
        return binarySearch(n, times);
    }

    private static long binarySearch(int n, int[] times) {
        long left = 0;
        long right = (long) n * times[times.length - 1] + 1;

        while (left < right) {
            long mid = left + (right - left >> 1);

            if (isPossible(n, times, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean isPossible(int n, int[] times, long mid) {
        long cnt = 0;

        for (int time : times) {
            cnt += mid / time;
        }

        return cnt >= n;
    }
}