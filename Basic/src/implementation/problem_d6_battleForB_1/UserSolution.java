package implementation.problem_d6_battleForB_1;

import java.util.*;

import java.util.Scanner;

class UserSolution {

    static class SearchLog {
        int time;
        char[] word;

        public SearchLog(int time, char[] word) {
            this.time = time;
            this.word = word;
        }
    }

    SearchLog[] searchLogs;
    Map<String, Map<String, Set<Integer>>> databaseForPair;

    void init(int n) {
        searchLogs = new SearchLog[n + 1];
        for (int i = 1; i <= n; i++) {
            searchLogs[i] = new SearchLog(-20, null);
        }

        databaseForPair = new HashMap<>();
    }

    int search(int mId, int searchTimestamp, char[] searchWord, char[][] correctWord) {
        SearchLog prevLog = searchLogs[mId];
        int prevTime = prevLog.time;
        char[] prevWord = prevLog.word;

        int cnt = setCorrectWord(searchWord, correctWord);

        if (searchTimestamp - prevTime <= 10 && isEditable(prevWord, searchWord)) {
            String prevStr = String.valueOf(prevWord);
            String searchStr = String.valueOf(searchWord);

            databaseForPair.putIfAbsent(prevStr, new HashMap<>());
            Map<String, Set<Integer>> correctWordsMap = databaseForPair.get(prevStr);

            correctWordsMap.putIfAbsent(searchStr, new HashSet<>());
            correctWordsMap.get(searchStr).add(mId);
        }

        searchLogs[mId] = new SearchLog(searchTimestamp, searchWord);
        return cnt;
    }

    int setCorrectWord(char[] searchWord, char[][] correctWord) {
        int cnt = 0;

        String searchStr = String.valueOf(searchWord);
        if (databaseForPair.containsKey(searchStr)) {
            Map<String, Set<Integer>> correctWordsMap = databaseForPair.get(searchStr);

            for (String correctStr : correctWordsMap.keySet()) {
                if (correctWordsMap.get(correctStr).size() >= 3) {
                    correctWord[cnt++] = correctStr.toCharArray();
                }
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

class Solution {

    private static int n, m;

    private final static UserSolution usersolution = new UserSolution();

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