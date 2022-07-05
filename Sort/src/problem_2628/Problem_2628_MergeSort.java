package problem_2628;

import java.io.*;
import java.util.*;

public class Problem_2628_MergeSort {
    private static List<Integer> cutWidth = new ArrayList<>();
    private static List<Integer> cutHeight = new ArrayList<>();
    private static int[] sorted;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        cutWidth.add(Integer.parseInt(tokenizer.nextToken()));
        cutHeight.add(Integer.parseInt(tokenizer.nextToken()));
        cutWidth.add(0);
        cutHeight.add(0);

        int num = Integer.parseInt(input.readLine());
        while (num-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());

            if (tokenizer.nextToken().equals("0")) {
                cutHeight.add(Integer.parseInt(tokenizer.nextToken()));
                continue;
            }

            cutWidth.add(Integer.parseInt(tokenizer.nextToken()));
        }

        System.out.print(maxArea());
    }

    private static int maxArea() {
        return max(cutHeight) * max(cutWidth);
    }

    private static int max(List<Integer> cut) {
        mergeSort(cut);

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < cut.size(); i++) {
            max = Math.max(max, cut.get(i) - cut.get(i - 1));
        }

        return max;
    }

    private static void mergeSort(List<Integer> list) {
        sorted = new int[list.size()];
        mergeSort(list, 0, list.size() - 1);
        sorted = null;
    }

    private static void mergeSort(List<Integer> list, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left >> 1);

        mergeSort(list, left, mid);
        mergeSort(list, mid + 1, right);

        merge(list, left, mid, right);
    }

    private static void merge(List<Integer> list, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {
            if (list.get(l) < list.get(r)) {
                sorted[idx++] = list.get(l++);
                continue;
            }

            sorted[idx++] = list.get(r++);
        }

        if (l <= mid) {
            copy(idx, list, l, mid);
        } else {
            copy(idx, list, r, right);
        }

        for (int i = left; i <= right; i++) {
            list.set(i, sorted[i]);
        }
    }

    private static void copy(int idx, List<Integer> src, int start, int end) {
        for (int i = start; i <= end; i++) {
            sorted[idx++] = src.get(i);
        }
    }
}
