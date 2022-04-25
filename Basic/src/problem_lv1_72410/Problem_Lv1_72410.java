package problem_lv1_72410;

import java.util.ArrayList;
import java.util.HashSet;

public class Problem_Lv1_72410 {
    public static void main(String[] args) {
        System.out.print(solution("123_.def"));
    }

    public static String solution(String new_id) {
        // 가능한 숫자, 특수 문자 집합
        HashSet<Character> possibleSpell = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            possibleSpell.add((char) (i + '0')); // (char) 0 != '0'이므로 조정해야 한다.
        }

        possibleSpell.add('-');
        possibleSpell.add('_');
        possibleSpell.add('.');

        // 인자로 받은 id를 ArrayList로 변경
        ArrayList<Character> chars = new ArrayList<>();

        for (int i = 0; i < new_id.length(); i++) {
            chars.add(new_id.charAt(i));
        }

        for (int i = 0; i < chars.size(); i++) {
            // 대문자라면 소문자로 변경
            if ('A' >= chars.get(i) && chars.get(i) <= 'Z') {
                chars.set(i, (char) (chars.get(i) - 'A' + 'a'));
                continue;
            }

            if ('a' >= chars.get(i) && chars.get(i) <= 'z') {
                continue;
            }

            // 가능하지 않은 문자는 삭제
            if (!possibleSpell.contains(chars.get(i))) {
                chars.remove(i--);
            }
        }

        // 연속된 점은 하나로 변경
        int dotCnt = 0;

        for (int i = 0; i < chars.size(); i++) {
            if (chars.get(i) == '.') {
                dotCnt++;
            } else {
                dotCnt = 0;
            }

            if (dotCnt >= 2) {
                chars.remove(i--);
                dotCnt--;
            }
        }

        // 점이 처음이나 끝이면 제거
        if (chars.get(0) == '.') {
            chars.remove(0);
        }

        if (chars.size() != 0 && chars.get(chars.size() - 1) == '.') {
            chars.remove(chars.size() - 1);
        }

        // 빈 문자열이면 'a' 대입
        if (chars.size() == 0) {
            chars.add('a');
        }

        // 16자 이상이면 앞에 15자 제외하고 모두 제거 후 앞뒤에 점이 있으면 제거
        while (chars.size() >= 16) {
            chars.remove(chars.size() - 1);
        }

        if (chars.get(0) == '.') {
            chars.remove(0);
        }

        if (chars.get(chars.size() - 1) == '.') {
            chars.remove(chars.size() - 1);
        }

        // 2자 이하이면 마지막 문자를 반복해서 추가하여 3자로 확장
        while (chars.size() <= 2) {
            chars.add(chars.get(chars.size() - 1));
        }

        // 결과 출력
        StringBuilder answer = new StringBuilder();
        for (Character c : chars) {
            answer.append(c);
        }

        return answer.toString();
    }
}
