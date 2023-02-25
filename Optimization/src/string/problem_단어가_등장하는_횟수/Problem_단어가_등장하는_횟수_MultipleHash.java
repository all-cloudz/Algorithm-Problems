package string.problem_단어가_등장하는_횟수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem_단어가_등장하는_횟수_MultipleHash {

    private static final int[] BASES = new int[] { 31, 37, 41 };

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            String B = input.readLine();
            int targetLen = B.length();
            int[] targetHashes = new int[3];

            String S = input.readLine();
            int patternLen = S.length();
            int[] patternHashes = new int[3];

            int[] powers = new int[] { 1, 1, 1 };
            for (int i = 0; i < patternLen; i++) {
                powers[0] *= BASES[0];
                powers[1] *= BASES[1];
                powers[2] *= BASES[2];
            }

            int count = 0;

            for (int i = 0; i <= targetLen - patternLen; i++) {
                if (i == 0) {
                    targetHashes = hashCode(B.substring(i, i + patternLen));
                    patternHashes = hashCode(S);
                } else {
                    for (int j = 0; j < 3; j++) {
                        targetHashes[j] = targetHashes[j] * BASES[j] - B.charAt(i - 1) * powers[j] + B.charAt(i + patternLen - 1);
                    }
                }

                if (Arrays.equals(targetHashes, patternHashes)) {
                    count++;
                }
            }

            answer.append(String.format("#%d %d%n", tc, count));
        }

        System.out.println(answer);
    }

    private static int[] hashCode(String str) {
        int[] hashes = new int[3];

        for (int i = 0; i < 3; i++) {
            hashes[i] = hashCode(str, BASES[i]);
        }

        return hashes;
    }

    private static int hashCode(String str, int base) {
        int hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = hash * base + str.charAt(i);
        }

        return hash;
    }

}
