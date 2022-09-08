package problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_Tree2_11_Sol1 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int q = Integer.parseInt(tokenizer.nextToken());

        Set<Integer> points = new TreeSet<>();
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            points.add(Integer.parseInt(tokenizer.nextToken()));
        }

        int[] compressionOfPoints = new int[n + 1];
        compressionOfPoints[0] = Integer.MIN_VALUE;

        int cnt = 1;
        for (int key : points) {
            compressionOfPoints[cnt++] = key;
        }

        for (int i = 0; i < q; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int left = Integer.parseInt(tokenizer.nextToken());
            int right = Integer.parseInt(tokenizer.nextToken());

            int ubdOfLeft = binarySearch_LBD(compressionOfPoints, 0, n + 1, left);
            int ubdOfRight = binarySearch_UBD(compressionOfPoints, 0, n + 1, right);
            answer.append(ubdOfRight - ubdOfLeft + 1).append('\n');
        }

        System.out.println(answer);
    }

    private static int binarySearch_UBD(int[] arr, int left, int right, int target) {
        if (left == right) {
            return right - 1;
        }

        int mid = left + (right - left >> 1);

        if (arr[mid] <= target) {
            return binarySearch_UBD(arr, mid + 1, right, target);
        }

        return binarySearch_UBD(arr, left, mid, target);
    }

    private static int binarySearch_LBD(int[] arr, int left, int right, int target) {
        if (left == right) {
            return left;
        }

        int mid = left + (right - left >> 1);

        if (arr[mid] >= target) {
            return binarySearch_LBD(arr, left, mid, target);
        }

        return binarySearch_LBD(arr, mid + 1, right, target);
    }

}
