package problem_1018;

import java.io.*;
import java.util.*;

public class Problem_1018 {
    private static int answer = 64 + 1; // 체스판 색을 바꾸는 횟수의 최댓값은 64이다.

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        final int N = Integer.parseInt(tokenizer.nextToken());
        final int M = Integer.parseInt(tokenizer.nextToken());
        char[][] chessBoard = new char[N][M];

        for (int i = 0; i < N; i++) {
            String row = input.readLine();

            for (int j = 0; j < M; j++) {
                chessBoard[i][j] = row.charAt(j);
            }
        }

        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j < M - 7; j++) {
                makeChessBoard(chessBoard, i, j);
            }
        }

        System.out.print(answer);
    }

    private static void makeChessBoard(char[][] chessBoard, int row, int col) {
        int cnt = 0;
//        char cur = chessBoard[row][col];
//
//        for (int i = row; i < row + 8; i++) {
//            for (int j = col; j < col + 8; j++) {
//                if (chessBoard[i][j] != cur) {
//                    cnt++;
//                }
//
//                cur = switchColor(cur);
//            }
//
//            cur = switchColor(cur);
//        }

        for (int i = row; i < row + 8; i++) {
            for (int j = col; j < col + 8; j++) {
                if ((i + j) % 2 == 0 && chessBoard[i][j] == 'W') {
                    cnt++;
                }

                if ((i + j) % 2 != 0 && chessBoard[i][j] == 'B') {
                    cnt++;
                }
            }
        }

        cnt = Math.min(cnt, 64 - cnt);
        answer = Math.min(cnt, answer);
    }

    private static char switchColor(char color) {
        if (color == 'W') {
            return 'B';
        }

        return 'W';
    }
}
