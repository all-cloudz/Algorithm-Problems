package problem_lv1_81301;

public class Problem_Lv1_81301 {
    public static int solution(String s) {
        return toNum(s);
    }

    private static int toNum(String str) {
        String[] numSpell = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        for (int i = 0; i < numSpell.length; i++) {
            str = str.replaceAll(numSpell[i], String.format("%d", i));
        }

        return Integer.parseInt(str);
    }
}
