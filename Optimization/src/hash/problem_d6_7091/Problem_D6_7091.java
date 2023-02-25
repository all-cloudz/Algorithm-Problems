package hash.problem_d6_7091;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_D6_7091 {

    private static final int[] BASES = { 31, 37, 41 };

    private static int H;
    private static int W;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            H = Integer.parseInt(tokenizer.nextToken());
            W = Integer.parseInt(tokenizer.nextToken());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());

            char[][] dream = new char[H][W];
            for (int i = 0; i < H; i++) {
                dream[i] = input.readLine().toCharArray();
            }

            char[][] drawing = new char[N][M];
            for (int i = 0; i < N; i++) {
                drawing[i] = input.readLine().toCharArray();
            }

            answer.append(String.format("#%d %d%n", tc, countCommonPart(drawing, dream)));
        }

        System.out.println(answer);
    }

    private static int countCommonPart(char[][] target, char[][] pattern) {
        if (H * W > N * M) {
            return 0;
        }

        int[] targetHashes = new int[3];
        int[] patternHashes = hashCode(pattern);

        int[][] powers = new int[3][H * W + 1];
        powers[0][0] = powers[1][0] = powers[2][0] = 1;
        for (int i = 1; i <= H * W; i++) {
            powers[0][i] = powers[0][i - 1] * BASES[0];
            powers[1][i] = powers[1][i - 1] * BASES[1];
            powers[2][i] = powers[2][i - 1] * BASES[2];
        }

        int count = 0;
        for (int rowLen = N - H, row = 0; row <= rowLen; row++) {
            for (int colLen = M - W, col = 0; col <= colLen; col++) {
                if (col == 0) {
                    targetHashes = hashCode(subdrawing(target, row, col));
                } else {
                    for (int k = 0; k < 3; k++) {
                        int minus = 0;
                        for (int i = 0; i < H; i++) {
                            minus += target[i][col - 1] * powers[k][(H - i) * W];
                        }

                        int plus = 0;
                        for (int i = 0; i < H; i++) {
                            plus += target[i][col + W - 1] * powers[k][(H - i - 1) * W];
                        }

                        targetHashes[k] = targetHashes[k] * BASES[k] - minus + plus;
                    }
                }

                if (Arrays.equals(targetHashes, patternHashes)) {
                    count++;
                }
            }
        }

        return count;
    }

    private static char[][] subdrawing(char[][] drawing, int rowBegin, int colBegin) {
        char[][] subdrawing = new char[H][W];

        for (int i = 0; i < H; i++) {
            System.arraycopy(drawing[rowBegin + i], colBegin, subdrawing[i], 0, W);
        }

        return subdrawing;
    }

    private static int[] hashCode(char[][] chars) {
        int[] hashes = new int[3];

        for (int i = 0; i < 3; i++) {
            hashes[i] = hashCode(chars, BASES[i]);
        }

        return hashes;
    }

    private static int hashCode(char[][] chars, int base) {
        int hash = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                hash = hash * base + chars[i][j];
            }
        }

        return hash;
    }


}
