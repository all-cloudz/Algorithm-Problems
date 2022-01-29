package problem_1300;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem_1300 {
    private static long N;
    private static long K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        System.out.println(binarySearch());
    }

    /* N = 3이면 6보다 작거나 같은 수의 개수, 7보다 작거나 같은 수의 개수, 8보다 작거나 같은 수의 개수가 모두 8이다.
     * 따라서 이 문제도 결국 조건을 만족시키는 수 중에서 최솟값을 찾으면 원하는 값이 배열 안에 있음을 확신할 수 있는 문제이다.
     * 참고로 조건을 만족시키는 값 중 최댓값을 찾을 때는 left == right가 일어나지 않고 무한루프를 반복하므로 right - left == 1을 종료조건으로 사용한다. */
    private static long binarySearch() {
        long left = 1;
        long right = N * N;

        while (left <= right) {
            if (left == right) {
                return left;
            }

            long mid = left + (right - left) / 2;
            long cnt = 0;

            for (int i = 1; i <= N; i++) {
                if (mid / i > N) {
                    cnt += N;
                } else {
                    cnt += mid / i;
                }
            }

            if (cnt >= K) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
