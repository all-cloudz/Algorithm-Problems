package problem_2352;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/* 가장 긴 증가하는 부분 수열(LIS)의 길이를 구하는 문제
 * 1. 두 선분이 만나는 점이 없어야 하므로 사실상 증가 혹은 감소만 해야 하는 상황 => 이분 탐색 중 사용
 * 2. 연결할 수 있는 최대 길이를 구해야 하므로 LIS 사용 => Lower Bound 사용
 * 3. 만약 최대 길이 뿐만 아니라 연결의 결과까지 도출해야 한다면 역추적을 위한 배열을 추가로 생성해야 함 */

public class Problem_2352 {
    private static int[] tabulate;
    private static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int NUM = Integer.parseInt(input.readLine());
        tabulate = new int[NUM];

        int[] link = new int[NUM];
        String[] strings = input.readLine().split(" ");
        for (int i = 0; i < NUM; i++) {
            link[i] = Integer.parseInt(strings[i]);
        }

        tabulate[0] = link[0];
        size++;

        for (int i = 0; i < NUM ; i++) {
            if (tabulate[size - 1] < link[i]) {
                tabulate[size++] = link[i];
            }

            tabulate[binarySearchLowerBound(link[i])] = link[i];
        }

        System.out.print(size);
    }

    private static int binarySearchLowerBound(int target) {
        int left = 0;
        int right = size;

        while (left < right) {
            int mid = left + (right - left >> 1);

            if (tabulate[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
