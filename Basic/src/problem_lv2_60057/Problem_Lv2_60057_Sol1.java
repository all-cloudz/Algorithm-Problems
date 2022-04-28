package problem_lv2_60057;

// 반복문으로 풀이
public class Problem_Lv2_60057_Sol1 {
    public static void main(String[] args) {
        System.out.print(solution("aabbaccc"));
    }

    public static int solution(String s) {
        return minZipLength(s);
    }

    private static int minZipLength(String str) {
        int min = str.length();

        for (int i = 1; i < str.length() / 2 + 1; i++) {
            min = Math.min(min, zipStr(str, i).length());
        }

        return min;
    }

    private static String zipStr(String str, int zipLength) {
        StringBuilder zip = new StringBuilder();

        String current = str.substring(0, zipLength);
        int repeat = 1;
        int nextStart = zipLength;

        while (nextStart <= str.length()) {
            String next = str.substring(nextStart, Math.min(nextStart += zipLength, str.length()));

            if (current.equals(next)) {
                repeat++;
            } else {
                zip.append(repeat != 1 ? repeat : "");
                zip.append(current);
                repeat = 1;
            }

            current = next;
        }

        zip.append(current);

        return zip.toString();
    }
}