package string.problem_9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Problem_9935_Stack {
    private static char[] str;
    private static char[] target;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        str = input.readLine().toCharArray();
        target = input.readLine().toCharArray();
        System.out.println(deleteTarget());
    }

    private static String deleteTarget() {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length; i++) {
            stack.push(str[i]);

            if (!isSame(stack)) {
                continue;
            }

            for (int j = 0; j < target.length; j++) {
                stack.pop();
            }
        }

        if (stack.isEmpty()) {
            return "FRULA";
        }

        StringBuilder answer = new StringBuilder();
        for (char cur : stack) {
            answer.append(cur);
        }

        return answer.toString();
    }

    private static boolean isSame(Stack<Character> stack) {
        if (stack.size() < target.length) {
            return false;
        }

        if (stack.peek() != target[target.length - 1]) {
            return false;
        }

        for (int i = target.length - 1; i >= 0; i--) {
            if (stack.search(target[i]) != target.length - i) { // Stack의 메서드 get()을 사용하면 조금 더 수월할 것이다.
                return false;
            }
        }

        return true;
    }
}
