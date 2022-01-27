package problem_2630;

// Olympiad > 한국정보올림피아드 > KOI 2001 > 중등부 1번
// 난이도 하
// 난 이것도 어렵네?

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_2630 {
    private static int[][] paper;
    private static int numWhite = 0;
    private static int numBlue = 0;

    public static void main(String[] args) throws IOException {
        // BufferedReader와 Scanner는 속도의 차이가 핵심, 하지만 BufferedReader는 LinebyLine으로만 읽고 Scanner는 원하는대로 읽을 수 있다는 편의성의 차이도 있다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        paper = new int[N][N];

        // StringTokenizer의 객체에 한 줄 읽어들일 때마다 저장하고, nextToken 메서드로 각 줄에서 띄워쓰기 단위로 분리하여 저장장
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checkPaper(0, 0, N);

        System.out.println(numWhite);
        System.out.println(numBlue);
    }

    // 이진탐색처럼 Divide and Conquer를 재귀로 구현하여 해결결
   private static void checkPaper(int i, int j, int checkTimes) {
        int sum = 0;

        for (int x = i; x < i + checkTimes; x++){
            for (int y = j; y < j + checkTimes; y++) {
                sum += paper[x][y];
            }
        }

        if (sum == Math.pow(checkTimes, 2)) {
            numBlue++;
        } else if (sum == 0) {
            numWhite++;
        } else {
            checkPaper(i, j, checkTimes / 2);
            checkPaper(i + checkTimes / 2, j, checkTimes / 2);
            checkPaper(i, j + checkTimes / 2, checkTimes / 2);
            checkPaper(i + checkTimes / 2, j + checkTimes / 2, checkTimes / 2);
        }
    }
}