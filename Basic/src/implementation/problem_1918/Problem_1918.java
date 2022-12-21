package implementation.problem_1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Problem_1918 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        String notation = input.readLine();
        Stack<Character> operations = new Stack<>();
        for (char cur : notation.toCharArray()) {
            if (Character.isUpperCase(cur)) {
                answer.append(cur);
                continue;
            }

            if (cur == '(') {
                operations.push(cur);
                continue;
            }

            if (cur == ')') {
                while (operations.peek() != '(') {
                    answer.append(operations.pop());
                }

                operations.pop();
                continue;
            }

            while (!operations.isEmpty() && compareTo(cur, operations.peek()) <= 0) {
                answer.append(operations.pop());
            }

            operations.push(cur);
        }

        while (!operations.isEmpty()) {
            answer.append(operations.pop());
        }

        System.out.println(answer);
    }

    private static int compareTo(char char1, char char2) {
        return getPriority(char1) - getPriority(char2);
    }

    private static int getPriority(char operationOrNum) {
        switch (operationOrNum) {
            case '*':
            case '/':
                return 3;
            case '+':
            case '-':
                return 2;
            default:
                return 1;
        }
    }

}
