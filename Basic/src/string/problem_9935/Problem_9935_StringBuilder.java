package string.problem_9935;

import java.io.*;

public class Problem_9935_StringBuilder {
    private static String str;
    private static String target;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        str = input.readLine();
        target = input.readLine();
        System.out.println(deleteTarget());
    }

    private static String deleteTarget() {
        StringBuilder answer = new StringBuilder(); // char[]를 이용해도 같은 방식으로 풀 수 있다.

        for (int i = 0; i < str.length(); i++) {
            answer.append(str.charAt(i));

            if (isSame(answer)) {
                answer.delete(answer.length() - target.length(), answer.length());
            }
        }

        if (answer.toString().equals("")) {
            return "FRULA";
        }

        return answer.toString();
    }

    private static boolean isSame(StringBuilder src) {
        if (src.length() < target.length()) {
            return false;
        }

        for (int i = 0; i < target.length(); i++) {
            int idx = src.length() - target.length() + i;

            if (src.charAt(idx) != target.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
