package problem_1725;

import java.io.*;
import java.util.*;

public class Problem_1725_Stack {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        int[] arr = new int[N + 2];
        arr[0] = Integer.MIN_VALUE;
        arr[N + 1] = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(input.readLine());
        }

        Stack<Integer> indices = new Stack<>();
        indices.push(0);

        int maxArea = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            // 현재 스택에 새로 들어온 높이보다 높은 게 있다면 남겨두지 않는다.
            // 현재 스택에 새로 들어온 높이보다 낮거나 같은 게 있다면 그냥 지나간다.
            while (!indices.isEmpty() && arr[i] < arr[indices.peek()]) {
                int top = indices.pop();
                int curArea = arr[top] * ((i - 1) - indices.peek());

                maxArea = Math.max(maxArea, curArea);
            }

            indices.push(i);
        }

        System.out.println(maxArea);
    }
}
