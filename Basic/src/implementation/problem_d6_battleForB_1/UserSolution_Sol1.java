package implementation.problem_d6_battleForB_1;

import java.util.*;

class UserSolution_Sol1 {

    static class SearchLog {
        int time;
        char[] word;

        public SearchLog(int time, char[] word) {
            this.time = time;
            this.word = word;
        }
    }

    static class Pair {
        String prevWord;
        String correctWord;

        public Pair(String prevWord, String correctWord) {
            this.prevWord = prevWord;
            this.correctWord = correctWord;
        }

        @Override
        public int hashCode() {
            return this.prevWord.hashCode() ^ this.correctWord.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Pair)) {
                return false;
            }

            Pair that = (Pair) o;
            return this.prevWord.equals(that.prevWord) && this.correctWord.equals(that.correctWord);
        }
    }

    SearchLog[] searchLogs;
    Map<Pair, Set<Integer>> candidates;
    Map<String, Set<String>> correctsMap;

    void init(int n) {
        searchLogs = new SearchLog[n + 1];
        for (int i = 1; i <= n; i++) {
            searchLogs[i] = new SearchLog(-20, null);
        }

        candidates = new HashMap<>();
        correctsMap = new HashMap<>();
    }

    int search(int mId, int searchTimestamp, char[] searchWord, char[][] correctWord) {
        SearchLog prevLog = searchLogs[mId];
        int prevTime = prevLog.time;
        char[] prevWord = prevLog.word;

        int cnt = setCorrectWord(searchWord, correctWord);

        if (searchTimestamp - prevTime <= 10 && isEditable(prevWord, searchWord)) {
            Pair key = new Pair(String.valueOf(prevWord), String.valueOf(searchWord));
            candidates.putIfAbsent(key, new HashSet<>());
            candidates.get(key).add(mId);

            if (candidates.get(key).size() == 3) {
                correctsMap.putIfAbsent(key.prevWord, new HashSet<>());
                correctsMap.get(key.prevWord).add(key.correctWord);
            }
        }

        searchLogs[mId] = new SearchLog(searchTimestamp, searchWord);
        return cnt;
    }

    int setCorrectWord(char[] searchWord, char[][] correctWord) {
        String searchStr = String.valueOf(searchWord);
        int cnt = 0;

        if (correctsMap.containsKey(searchStr)) {
            for (String cur : correctsMap.get(searchStr)) {
                correctWord[cnt++] = cur.toCharArray();
            }
        }

        return cnt;
    }

    boolean isEditable(char[] prevWord, char[] searchWord) {
        int lenOfPrevWord = len(prevWord);
        int lenOfSearchWord = len(searchWord);

        if (lenOfPrevWord == lenOfSearchWord + 1) {
            return isInsertion(prevWord, searchWord, lenOfPrevWord);
        }

        if (lenOfPrevWord == lenOfSearchWord) {
            return isUpdate(prevWord, searchWord, lenOfPrevWord);
        }

        if (lenOfPrevWord + 1 == lenOfSearchWord) {
            return isDelete(prevWord, searchWord, lenOfSearchWord);
        }

        return false;
    }

    int len(char[] word) {
        for (int i = 0; i < 11; i++) {
            if (word[i] == '\0') {
                return i;
            }
        }

        return 10;
    }

    boolean isInsertion(char[] prevWord, char[] searchWord, int maxLen) {
        int cnt = 0;

        for (int i = 0, j = 0; i < maxLen; i++) {
            if (prevWord[i] != searchWord[j]) {
                cnt++;
                continue;
            }

            j++;
        }

        return cnt == 1;
    }

    boolean isUpdate(char[] prevWord, char[] searchWord, int maxLen) {
        int cnt = 0;

        for (int i = 0; i < maxLen; i++) {
            if (prevWord[i] != searchWord[i]) {
                cnt++;
            }
        }

        return cnt == 1;
    }

    boolean isDelete(char[] prevWord, char[] searchWord, int maxLen) {
        return isInsertion(searchWord, prevWord, maxLen);
    }

}

class Solution_Sol1 {

    private static int n, m;

    private final static UserSolution_Sol1 usersolution = new UserSolution_Sol1();

    private static char[][] words = new char[4000][11];

    private static int mstrcmp(char[] a, char[] b)
    {
        int i;
        for (i = 0; a[i] != '\0'; i++)
        {
            if (a[i] != b[i])
                return a[i] - b[i];
        }
        return a[i] - b[i];
    }

    private static void String2Char(String s, char[] b) {
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            b[i] = s.charAt(i);
        }
        b[n] = '\0';
    }

    private static void inputWords(int wordCnt, Scanner sc) {

        for (int i = 0; i < wordCnt; ++i) {
            String2Char(sc.next(), words[i]);
        }
    }

    private static boolean run(int m, Scanner sc) {

        boolean accepted = true;
        char[][] correctWord = new char[5][11];
        char[][] answerWord = new char[5][11];

        while(m-- > 0) {

            int id, timestamp, correctWordN, answerWordN;
            int wordIdx;

            id = sc.nextInt();
            timestamp = sc.nextInt();
            wordIdx = sc.nextInt();

            correctWordN = usersolution.search(id, timestamp, words[wordIdx], correctWord);

            answerWordN = sc.nextInt();

            for (int i = 0; i < answerWordN; ++i) {
                String2Char(sc.next(), answerWord[i]);
            }

            if (correctWordN != answerWordN) {
                accepted = false;
            } else {
                for (int i = 0; i < answerWordN; ++i) {
                    boolean isExist = false;

                    for (int j = 0; j < correctWordN ; ++j) {
                        if (mstrcmp(answerWord[i], correctWord[j]) == 0) {
                            isExist = true;
                        }
                    }

                    if (!isExist) {
                        accepted = false;
                    }
                }
            }
        }

        return accepted;
    }

    public static void main(String[] args) throws Exception {

        int test, T;
        int wordCnt;

        // System.setIn(new java.io.FileInputStream("sample_input.txt"));

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (test = 1 ; test <= T ; ++test) {

            wordCnt = sc.nextInt();

            inputWords(wordCnt, sc);

            n = sc.nextInt();
            m = sc.nextInt();

            usersolution.init(n);

            if (run(m, sc)) {
                System.out.println("#" + test + " 100");
            } else {
                System.out.println("#" + test + " 0");
            }
        }
    }

}