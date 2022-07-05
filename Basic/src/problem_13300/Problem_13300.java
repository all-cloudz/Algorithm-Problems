package problem_13300;

import java.io.*;
import java.util.*;

public class Problem_13300 {
    private static int[][] studentNum = new int[6][2];

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        final int K = input.nextInt();

        while (N-- > 0) {
            int sex = input.nextInt();
            int grade = input.nextInt();

            studentNum[grade - 1][sex]++;
        }

        System.out.print(assignMinRoom(K));
    }

    private static int assignMinRoom(int maxStudentNum) {
        int cnt = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                int cur = studentNum[i][j];

                if (cur % maxStudentNum == 0) {
                    cnt += cur / maxStudentNum;
                    continue;
                }

                cnt += cur / maxStudentNum + 1;
            }
        }

        return cnt;
    }
}
