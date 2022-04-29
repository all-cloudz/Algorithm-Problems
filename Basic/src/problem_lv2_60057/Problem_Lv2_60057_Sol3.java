package problem_lv2_60057;

public class Problem_Lv2_60057_Sol3 {
    public static void main(String[] args) {
        System.out.print(solution("aabbaccc"));
    }

    public static int solution(String s) {
        int min = s.length();

        for (int zipLength = 1; zipLength < s.length() / 2 + 1; zipLength++) {
            String current = s.substring(0, zipLength);
            int repeat = 1;
            int zipStrLength = 0;

            int nextStart = zipLength;
            while (nextStart <= s.length()) {
                String next = s.substring(nextStart, Math.min(nextStart += zipLength, s.length()));

                if (current.compareTo(next) == 0) {
                    repeat++;
                    continue;
                }

                if (repeat != 1) {
                    zipStrLength += (int) Math.log10(repeat) + 1;
                }

                zipStrLength += current.length();
                current = next;
                repeat = 1;
            }

            zipStrLength += current.length();

            min = Math.min(min, zipStrLength);
        }

        return min;
    }
}
