package implementation.problem_d6_battleForB_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 시간 초과로 Fail... cursor의 이동이 insert에서의 배열의 복사보다 더 많은 데이터를 처리할 가능성이 높기 때문이다.
// 단적인 예로, 문자의 길이가 90000이 되면 insert는 실행하지 않지만 cursor는 실행이 가능하다.
class UserSolution_Fail {

    private int W;
    private int[] backCntOfAlphabet;

    private Stack<Character> front;
    private int cursor;
    private Deque<Character> back;
    private int size;

    void init(int H, int W, char[] mStr) {
        this.W = W;
        backCntOfAlphabet = new int[26];

        front = new Stack<>();
        cursor = 0;
        back = new ArrayDeque<>();
        size = 0;

        for (char cur : mStr) {
            if (cur == '\0') {
                break;
            }

            back.add(cur);
            backCntOfAlphabet[cur - 'a']++;
            size++;
        }
    }

    void insert(char mChar) {
        front.push(mChar);
        cursor++;
        size++;
    }

    char moveCursor(int mRow, int mCol) {
        int pos = (mRow - 1) * W + (mCol - 1);

        if (cursor < pos) {
            moveBackward(pos);
        } else {
            moveForward(pos);
        }

        if (back.isEmpty()) {
            return '$';
        }

        return back.peekFirst();
    }

    void moveBackward(int pos) {
        for (;cursor < pos; cursor++) {
            if (back.isEmpty()) {
                break;
            }

            char next = back.pollFirst();
            backCntOfAlphabet[next - 'a']--;
            front.push(next);
        }
    }

    void moveForward(int pos) {
        for (;cursor > pos; cursor--) {
            if (front.isEmpty()) {
                break;
            }

            char next = front.pop();
            backCntOfAlphabet[next - 'a']++;
            back.offerFirst(next);
        }
    }

    int countCharacter(char mChar) {
        return backCntOfAlphabet[mChar - 'a'];
    }

}

class Solution_Fail
{
    private final static int CMD_INIT       = 100;
    private final static int CMD_INSERT     = 200;
    private final static int CMD_MOVECURSOR = 300;
    private final static int CMD_COUNT      = 400;

    private final static UserSolution_Fail usersolution = new UserSolution_Fail();

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