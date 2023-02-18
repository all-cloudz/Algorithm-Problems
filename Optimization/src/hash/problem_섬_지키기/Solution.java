package hash.problem_섬_지키기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    private final static int CMD_INIT = 1;
    private final static int CMD_NUMBER_OF_CANDIDATE = 2;
    private final static int CMD_MAX_AREA = 3;

    private final static UserSolution usersolution = new UserSolution();

    private static int[][] mMap = new int[20][20];
    private static int[] mStructure = new int[5];

    private static boolean run(BufferedReader br) throws Exception {
        StringTokenizer st;

        int numQuery;
        int N, M, mSeaLevel;
        int userAns, ans;

        boolean isCorrect = false;

        numQuery = Integer.parseInt(br.readLine());

        for (int q = 0; q < numQuery; ++q) {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd;
            cmd = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case CMD_INIT:
                    N = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            mMap[i][j] = Integer.parseInt(st.nextToken());
                        }
                    }
                    usersolution.init(N, mMap);
                    isCorrect = true;
                    break;
                case CMD_NUMBER_OF_CANDIDATE:
                    M = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < M; i++) {
                        mStructure[i] = Integer.parseInt(st.nextToken());
                    }
                    userAns = usersolution.numberOfCandidate(M, mStructure);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans) {
                        isCorrect = false;
                    }
                    break;
                case CMD_MAX_AREA:
                    M = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < M; i++) {
                        mStructure[i] = Integer.parseInt(st.nextToken());
                    }
                    mSeaLevel = Integer.parseInt(st.nextToken());
                    userAns = usersolution.maxArea(M, mStructure, mSeaLevel);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans) {
                        isCorrect = false;
                    }
                    break;
                default:
                    isCorrect = false;
                    break;
            }
        }
        return isCorrect;
    }

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream("Optimization/src/hash/problem_섬_지키기/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }

}
