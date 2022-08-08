package problem_12891;

import java.io.*;
import java.util.*;

// 시간 초과 : S와 P가 최대 100만의 값을 가지므로 시간복잡도가 O(N^2)인 상황에서 2초안에 통과할 수 없다.
public class Problem_12891_Fail {
    private static int S;
    private static String dna;
    private static int P;
    private static int[] minCnt = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        dna = input.readLine();

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < 4; i++) {
            minCnt[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(countSubstring());
    }

    private static int countSubstring() {
        int answer = 0;

        for (int i = 0; i <= S - P; i++) {
            String subDna = dna.substring(i, i + P);
            int[] cnt = new int[4];

            checkSubDNA(subDna, cnt);

            if (validate(cnt)) {
                answer++;
            }
        }

        return answer;
    }

    private static void checkSubDNA(String subDna, int[] cnt) {
        for (int i = 0; i < P; i++){
            switch (subDna.charAt(i)) {
                case 'A':
                    cnt[0]++;
                    break;
                case 'C':
                    cnt[1]++;
                    break;
                case 'G':
                    cnt[2]++;
                    break;
                case 'T':
                    cnt[3]++;
            }
        }
    }

    private static boolean validate(int[] cnt) {
        for (int i = 0; i < 4; i++) {
            if (cnt[i] < minCnt[i]) {
                return false;
            }
        }

        return true;
    }
}
