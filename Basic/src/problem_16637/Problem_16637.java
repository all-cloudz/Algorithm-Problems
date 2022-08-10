package problem_16637;

import java.io.*;
import java.util.*;

public class Problem_16637 {
    private static final Set<Character> OPERATIONS;
    private static final char LEFT_BRACKET_FLAG = '.';
    private static final char RIGHT_BRACKET_FLAG = '!';

    private static char[] formula;

    static {
        OPERATIONS = new HashSet<>();
        OPERATIONS.add('+');
        OPERATIONS.add('-');
        OPERATIONS.add('*');
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        formula = new char[2 * N + 1];

        String str = input.readLine();
        for (int i = 0; i < formula.length; i++) {
            if (i % 4 == 0) {
                formula[i] = LEFT_BRACKET_FLAG;
                continue;
            }

            if (i % 2 == 1) {
                formula[i] = str.charAt(i / 2);
                continue;
            }

            formula[i] = RIGHT_BRACKET_FLAG;
        }

        System.out.println(getMax());
    }

    private static int getMax() {
        return getMax(0, Integer.MIN_VALUE);
    }

    private static int getMax(int idx, int max) {
        while (idx < formula.length && formula[idx] != LEFT_BRACKET_FLAG) {
            idx++;
        }

        if (idx == formula.length) {
            String str = new String(formula);
            str = str.replaceAll("[^0-9|(|)|*|\\+|-]", "");
            max = Math.max(max, calculate(str));
            return max;
        }

        if (formula[idx + 2] == ')') {
            max = Math.max(max, getMax(idx + 3, max));
            return max;
        }
        try {
            formula[idx] = '(';
            formula[idx + 6] = ')';
            max = Math.max(max, getMax(idx + 1, max));

            formula[idx] = LEFT_BRACKET_FLAG;
            formula[idx + 6] = RIGHT_BRACKET_FLAG;
            max = Math.max(max, getMax(idx + 1, max));
        } catch (ArrayIndexOutOfBoundsException e) {
            formula[idx] = LEFT_BRACKET_FLAG;
            max = Math.max(max, getMax(idx + 1, max));
        }

        return max;
    }

    private static int calculate(String str) {
        Queue<Character> postFix = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            int curPriority = getPriority(cur);

            if (OPERATIONS.contains(cur)) {
                while (!stack.isEmpty() && getPriority(stack.peek()) >= curPriority) {
                    postFix.offer(stack.pop());
                }

                stack.push(cur);
                continue;
            }

            if (cur == '(') {
                stack.push(cur);
                continue;
            }

            if (cur == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postFix.offer(stack.pop());
                }

                stack.pop();
                continue;
            }

            postFix.offer(cur);
        }

        while (!stack.isEmpty()) {
            postFix.offer(stack.pop());
        }

        Stack<Integer> numStack = new Stack<>();

        while (!postFix.isEmpty()) {
            char cur = postFix.poll();

            if (!OPERATIONS.contains(cur)) {
                numStack.push(cur - '0');
                continue;
            }

            if (cur == '+') {
                numStack.push(numStack.pop() + numStack.pop());
                continue;
            }

            if (cur == '-') {
                numStack.push(- numStack.pop() + numStack.pop());
                continue;
            }

            if (cur == '*') {
                numStack.push(numStack.pop() * numStack.pop());
            }
        }

        return numStack.pop();
    }

    private static int getPriority(char c) {
        switch (c) {
            case '*' :
            case '+' :
            case '-' :
                return 1; // 모든 연산자의 우선 순위는 동일
            default :
                return 0;
        }
    }
}