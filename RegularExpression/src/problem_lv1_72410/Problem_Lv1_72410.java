package problem_lv1_72410;

public class Problem_Lv1_72410 {
    private static class KakaoIdBuilder {
        private static final String REMOVAL_REGEX = "[^a-z0-9-_.]";
        private static final String DEFALUT_ID = "a";
        private static final int MAX_SIZE = 15;
        private static final int MIN_SIZE = 3;

        private String id;

        public KakaoIdBuilder(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public KakaoIdBuilder toLowerCase() {
            id = id.toLowerCase();
            return this;
        }

        public KakaoIdBuilder filter() {
            /* 1. ^x : x로 시작하는 문자열
             * 2. [xy] : x 또는 y인 문자
             * 3. [^xy] : x 또는 y가 아닌 문자
             * 4. - : 범위
             * 5. \\d : [0-9]
             * 6. \\D : [^0-9]
             * 7. \\w : [a-zA-Z0-9_]
             * 8. \\W : [^a-zA-Z0-9_] */
            id = id.replaceAll(REMOVAL_REGEX, "");
            return this;
        }

        public KakaoIdBuilder toSingleDot() {
            /* 1. x{a, b} : x를 a번 이상 b번 이하 반복
             * 2. x{a,} : x를 a번 이상 반복
             * 3. x+ : x를 1번 이상 반복
             * 4. x* : x를 0번 이상 반복
             * 5. . : 임의의 문자
             * 6. '.' == [.] == \\. => 지금은 '.'를 탐색해야 하므로 .이 아니라 [.], \\.를 사용해야 한다. */
            id = id.replaceAll("[.]{2,}", ".");
            return this;
        }

        public KakaoIdBuilder deleteDot() {
            /* 1. ^x : x로 시작하는 문자열
             * 2. x$ : x로 끝나는 문자열
             * 3. | : 논리합, 또는 */
            id = id.replaceAll("^\\.|\\.$", "");
            return this;
        }

        public KakaoIdBuilder empty() {
            id = id.isEmpty() ? DEFALUT_ID : id;
            return this;
        }

        public KakaoIdBuilder longString() {
            if (id.length() > MAX_SIZE) {
                id = id.substring(0, 15);
            }

            return deleteDot();
        }

        public KakaoIdBuilder shortString() {
            StringBuilder tmp = new StringBuilder(id);

            while (tmp.length() <= MIN_SIZE) {
                tmp.append(tmp.charAt(tmp.length() - 1));
            }

            id = tmp.toString();
            return this;
        }
    }

    public static String solution(String new_id) {
        return new KakaoIdBuilder(new_id)
                .toLowerCase()
                .filter()
                .toSingleDot()
                .deleteDot()
                .empty()
                .longString()
                .shortString()
                .getId();
    }

    public static void main(String[] args) {
        System.out.print(solution("...!@BaT#*..y.abcdefghijklm"));
    }
}
