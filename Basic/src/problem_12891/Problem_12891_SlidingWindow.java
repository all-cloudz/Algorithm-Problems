package problem_12891;

import java.io.*;
import java.util.*;

public class Problem_12891_SlidingWindow {
    private static final Map<Character, Integer> INDICES;

    private static int S;
    private static String DNA;
    private static int P;
    private static int[] minCnt = new int[4];

    static {
        INDICES = new HashMap<>();
        INDICES.put('A', 0);
        INDICES.put('C', 1);
        INDICES.put('G', 2);
        INDICES.put('T', 3);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        DNA = input.readLine();

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < 4; i++) {
            minCnt[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(countSubstring());
    }

    private static int countSubstring() {
        int totalCnt = 0;
        int left = 0;
        int right = 0;

        while (right < S) {
            if (right - left + 1 < P) {
                minCnt[INDICES.get(DNA.charAt(right++))]--;
            } else if (right - left + 1 > P) {
                minCnt[INDICES.get(DNA.charAt(left++))]++;
            }

            if (right - left + 1 != P) {
                continue;
            }

            minCnt[INDICES.get(DNA.charAt(right++))]--;

            if (validate()) {
                totalCnt++;
            }
        }

        return totalCnt;
    }

    private static boolean validate() {
        for (int i = 0; i < 4; i++) {
            if (minCnt[i] > 0) {
                return false;
            }
        }

        return true;
    }
}
