package problem_lv2_60057;

public class Problem_Lv2_60057 {
    public static void main(String[] args) {
        System.out.print(solution("aabbaccc"));
    }

    public static int solution(String s) {
        return minZipLength(s);
    }

    private static int minZipLength(String str) {
        int min = str.length();

        for (int i = 1; i < str.length(); i++) {
            min = Math.min(min, zipStr(str, i).length());
        }

        return min;
    }

    private static String zipStr(String str, int zipLength) {
        StringBuilder zip = new StringBuilder();

        String current = str.substring(0, zipLength);
        zip.append(current);

        int zipCnt = 1;
        int nextStart = zipLength;
        while (nextStart <= str.length()) {
            String next = str.substring(nextStart, Math.min(nextStart + zipLength, str.length()));

            if (current.equals(next)) {
                zipCnt++;
            } else {
                zip.append(zipCnt != 1 ? zipCnt : "");
                zip.append(next);
                zipCnt = 1;
            }

            current = next;
            nextStart += zipLength;
        }

        return zip.toString();
    }
}