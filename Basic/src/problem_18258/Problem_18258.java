package problem_18258;

import java.io.*;
import java.util.*;

public class Problem_18258 {
    private static StringBuilder answer = new StringBuilder();
    private static int[] queue;
    private static int front = 0;
    private static int rear = 0;
    private static int size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        queue = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            String method = tokenizer.nextToken();
            int value = 0;

            if (method.equals("push")) {
                value = Integer.parseInt(tokenizer.nextToken());
            }

            runMethod(method, value);
        }

        System.out.println(answer);
    }

    private static void runMethod(String method, int value) {
        switch (method) {
            case "empty" :
                isEmpty();
                break;
            case "size" :
                size();
                break;
            case "push" :
                push(value);
                break;
            case "pop" :
                pop();
                break;
            case "front" :
                peek();
                break;
            case "back" :
                peekLast();
        }
    }

    private static boolean isEmpty() {
        if (front == rear) {
            answer.append(1).append('\n');
            return true;
        }

        answer.append(0).append('\n');
        return false;
    }

    private static void size() {
        answer.append(size).append('\n');
    }

    private static void push(int value) {
        rear = (++rear) % queue.length;
        queue[rear] = value;
        size++;
    }

    private static void pop() {
        if (front == rear) {
            answer.append(-1).append('\n');
            return;
        }

        answer.append(queue[++front]).append('\n');
        front %= queue.length;
        size--;
    }

    private static void peek() {
        if (front == rear) {
            answer.append(-1).append('\n');
            return;
        }

        answer.append(queue[front + 1]).append('\n');
    }

    private static void peekLast() {
        if (front == rear) {
            answer.append(-1).append('\n');
            return;
        }

        answer.append(queue[rear]).append('\n');
    }
}
