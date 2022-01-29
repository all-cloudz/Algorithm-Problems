package problem_10816;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;

public class Problem_10816_Sol1 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(input.readLine());
        String[] tmp = input.readLine().split(" ");
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tmp[i]);
        }

        int M = Integer.parseInt(input.readLine());
        tmp = input.readLine().split(" ");
        int[] finds = new int[M];
        for (int i = 0; i < M; i++) {
            finds[i] = Integer.parseInt(tmp[i]);
        }

        StringBuilder answer = new StringBuilder();

        Arrays.sort(nums);
        for (int i = 0; i < M; i++) {
            if (!binarySearch(nums, 0, nums.length - 1, finds[i])) {
                answer.append("0 ");
                continue;
            }

            int sup = binarySearch_UpperBound(nums, 0, nums.length - 1, finds[i] + 1);
            int inf = binarySearch_LowerBound(nums, 0, nums.length - 1, finds[i] - 1);

            if (inf == sup) {
                answer.append("1 ");
                continue;
            }

            answer.append(sup - inf).append(' ');
        }

        output.write(String.valueOf(answer));
        output.flush();

        input.close();
        output.close();
    }

    // target value가 정확히 존재하는지 확인
    private static boolean binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return true;
            }

            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return false;
    }

    // LowerBound는 조건을 만족시키는 값 중에서 최소인 경우를 탐색
    private static int binarySearch_LowerBound(int[] nums, int left, int right, int target) {
        int mid = left + (right - left) / 2;

        if (left == right) {
            return mid;
        }

        if (nums[mid] > target) {
            return binarySearch_LowerBound(nums, left, mid, target);
        } else {
            return binarySearch_LowerBound(nums, mid + 1, right, target);
        }
    }

    // UpperBound는 조건을 만족시키는 값 중에서 최대인 경우를 탐색
    private static int binarySearch_UpperBound(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Sol2에서 이 부분을 개선한 방법을 사용하였다
            if (right - left <= 1) {
                if (nums[right] < target) {
                    return right;
                }
                return left;
            }

            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }
}