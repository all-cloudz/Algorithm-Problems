package string.problem_9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 시간 초과가 발생한 이유를 정확하게는 모르겠지만 추정하자면 메서드 delete()를 StringBuilder 객체의 middle에 사용할 경우 shift가 발생하면서 시간복잡도를 높이는 것으로 보인다.
public class Problem_9935_Fail {
    private static StringBuilder str;
    private static String target;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        str = new StringBuilder(input.readLine());
        target = input.readLine();
        System.out.println(deleteTarget());
    }

    // 시간 초과
    private static String deleteTarget() {
        for (int idx = target.length() - 1; idx < str.length(); idx++) {
            if (isSame(idx + 1)) {
                str.delete(idx - target.length() + 1, idx + 1);
                idx -= target.length();
            }
        }

        if (str.toString().equals("")) {
            return "FRULA";
        }

        return str.toString();
    }

    private static boolean isSame(int curLength) {
        if (curLength < target.length()) {
            return false;
        }

        for (int i = 0; i < target.length(); i++) {
            int idx = curLength - target.length() + i;

            if (str.charAt(idx) != target.charAt(i)) {
                return false;
            }
        }

        return true;
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
}
