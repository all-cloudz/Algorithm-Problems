package implementation.problem_d6_battleForB_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class UserSolution {

    private int W;
    private int[][] cntOfAlphabet;

    private List<Character> memo;
    private int cursor;
    private int size;

    void init(int H, int W, char[] mStr) {
        this.W = W;
        cntOfAlphabet = new int[H][26];
        memo = new ArrayList<>();
        cursor = 0;
        size = 0;

        for (char cur : mStr) {
            if (cur == '\0') {
                break;
            }

            memo.add(cur);
            cntOfAlphabet[size / W][cur - 'a']++;
            size++;
        }
    }

    void insert(char mChar) {
        memo.add(cursor, mChar);

        int curRow = cursor / W;
        cntOfAlphabet[curRow][mChar - 'a']++;

        for (int lastRow = size / W, i = curRow + 1; i <= lastRow; i++) {
            char movedChar = memo.get(i * W);
            cntOfAlphabet[i - 1][movedChar - 'a']--;
            cntOfAlphabet[i][movedChar - 'a']++;
        }

        cursor++;
        size++;
    }

    char moveCursor(int mRow, int mCol) {
        cursor = (mRow - 1) * W + (mCol - 1);

        if (cursor < size) {
            return memo.get(cursor);
        }

        cursor = size;
        return '$';
    }

    int countCharacter(char mChar) {
        int cnt = 0;
        int nextRow = 1 + cursor / W;

        for (int len = Math.min(nextRow * W, size),  i = cursor; i < len; i++) {
            if (memo.get(i) == mChar) {
                cnt++;
            }
        }

        for (int lastRow = (size - 1) / W, i = nextRow; i <= lastRow ; i++) {
            cnt += cntOfAlphabet[i][mChar - 'a'];
        }

        return cnt;
    }

}

class Solution
{
    private final static int CMD_INIT       = 100;
    private final static int CMD_INSERT     = 200;
    private final static int CMD_MOVECURSOR = 300;
    private final static int CMD_COUNT      = 400;

    private final static UserSolution usersolution = new UserSolution();

    private static void String2Char(char[] buf, String str, int maxLen)
    {
        for (int k = 0; k < str.length(); k++)
            buf[k] = str.charAt(k);

        for (int k = str.length(); k <= maxLen; k++)
            buf[k] = '\0';
    }

    private static char[] mStr = new char[90001];

    private static boolean run(BufferedReader br) throws Exception
    {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int queryCnt = Integer.parseInt(st.nextToken());
        boolean correct = false;

        for (int q = 0; q < queryCnt; q++)
        {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd = Integer.parseInt(st.nextToken());

            if (cmd == CMD_INIT)
            {
                int H = Integer.parseInt(st.nextToken());
                int W = Integer.parseInt(st.nextToken());

                String2Char(mStr, st.nextToken(), 90000);

                usersolution.init(H, W, mStr);
                correct = true;
            }
            else if (cmd == CMD_INSERT)
            {
                char mChar = st.nextToken().charAt(0);

                usersolution.insert(mChar);
            }
            else if (cmd == CMD_MOVECURSOR)
            {
                int mRow = Integer.parseInt(st.nextToken());
                int mCol = Integer.parseInt(st.nextToken());

                char ret = usersolution.moveCursor(mRow, mCol);

                char ans = st.nextToken().charAt(0);
                if (ret != ans)
                {
                    correct = false;
                }
            }
            else if (cmd == CMD_COUNT)
            {
                char mChar = st.nextToken().charAt(0);

                int ret = usersolution.countCharacter(mChar);

                int ans = Integer.parseInt(st.nextToken());
                if (ret != ans)
                {
                    correct = false;
                }
            }
        }
        return correct;
    }

    public static void main(String[] args) throws Exception
    {
        int TC, MARK;

//        System.setIn(new java.io.FileInputStream("C:\\Users\\이다운\\IdeaProjects\\Algorithm-Problems\\Basic\\src\\implementation\\problem_d6_battleForB_2\\sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = run(br) ? MARK : 0;

            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}