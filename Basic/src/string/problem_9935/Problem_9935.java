package string.problem_9935;

import java.io.*;

public class Problem_9935 {
    private static StringBuilder str;
    private static String target;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        str = new StringBuilder(input.readLine());
        target = input.readLine();
        System.out.println(deleteTarget());
    }

      // 시간 초과
//    private static String deleteTarget() {
//        int idx = 0;
//        int len = target.length();
//
//        while (idx < str.length()) {
//            idx = str.indexOf(target, idx - len);
//
//            if (idx == -1) {
//                break;
//            }
//
//            str.delete(idx, idx + len);
//        }
//
//        if (str.toString().equals("")) {
//            return "FRULA";
//        }
//
//        return str.toString();
//    }

//    private static String deleteTarget() {
//        StringBuilder answer = new StringBuilder();
//
//        for (int i = 0; i < str.length(); i++) {
//            answer.append(str.charAt(i));
//
//            if (answer.length() >= target.length()) {
//                if (isSame(answer)) {
//                    answer.delete(answer.length() - target.length(), answer.length());
//                }
//            }
//        }
//
//        if (answer.toString().equals("")) {
//            return "FRULA";
//        }
//
//        return answer.toString();
//    }

//    private static boolean isSame(StringBuilder src) {
//        for (int i = 0; i < target.length(); i++) {
//            int idx = src.length() - target.length() + i;
//
//            if (src.charAt(idx) != target.charAt(i)) {
//                return false;
//            }
//        }
//
//        return true;
//    }

    private static String deleteTarget() {
        int idx = 0;
        int len = target.length();

        while (idx < str.length()) {
            if (idx + 1 >= len) {

            }

            if (str.charAt(idx) == target.charAt(len - 1) && isSame(idx)) {
                str.delete(idx, idx + target.length());
                idx = (idx < target.length()) ? 0 : idx - target.length();
                continue;
            }

            idx++;
        }

        if (str.toString().equals("")) {
            return "FRULA";
        }

        return str.toString();
    }

    private static boolean isSame(int idx) {
        for (int i = 1; i < target.length(); i++) {
            if (str.charAt(idx + i) != target.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
