package problem_lv2_60057;

// 재귀로 풀이
public class Problem_Lv2_60057_Sol2 {
    public static void main(String[] args) {
        System.out.print(solution("aabbaccc"));
    }

    public static int solution(String s) {
        return minZipLength(s);
    }

    private static int minZipLength(String str) {
        int min = str.length();
        StringBuilder zip = new StringBuilder();

        for (int zipLength = 1; zipLength < str.length() / 2 + 1; zipLength++) {
            min = Math.min(min, zipStr(str, zipLength, 1).length());
        }

        return min;
    }

    private static StringBuilder zipStr(String str, int zipLength, int repeat) {
        // 종료 조건
        if (str.length() < zipLength) {
            return new StringBuilder(str);
        }

        StringBuilder zip = new StringBuilder();
        String prefix = str.substring(0, zipLength);
        String next = str.substring(zipLength);

        if (next.startsWith(prefix)) {
            return zip.append(zipStr(next, zipLength, repeat + 1));
        }

        if (repeat != 1) {
            return zip.append(repeat).append(prefix).append(zipStr(next, zipLength, 1));
        }

        return zip.append(prefix).append(zipStr(next, zipLength, 1));
    }
}
