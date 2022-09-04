package problem_18869;

import java.io.*;
import java.util.*;

public class Problem_18869_Compression_Sol2 {

    private static int N, M;
    private static int[][] universes;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        universes = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                universes[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        compress(universes);

        Arrays.sort(universes, (a, b) -> {
           for (int i = 0; i < M; i++) {
               if (a[i] == b[i]) {
                   continue;
               }

               return a[i] - b[i];
           }

           return 0;
        });

        int answer = 0;
        int cnt = 1;
        for (int i = 1; i <= N; i++) {
            if (i < N && isSame(universes[i], universes[i - 1])) {
                cnt++;
                continue;
            }

            answer += cnt * (cnt - 1) >> 1;
            cnt = 1;
        }
        System.out.println(answer);
    }

    private static void compress(int[][] universes) {
        int[][] copied = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(universes[i], 0, copied[i], 0, M);
        }

        for (int i = 0; i < N; i++) {
            int[] cur = universes[i];
            int[] sorted = copied[i];
            Arrays.sort(sorted);

            Map<Integer, Integer> rankOfNum = new HashMap<>();

            int rank = 0;
            for (int element : sorted) {
                if (!rankOfNum.containsKey(element)) {
                    rankOfNum.put(element, rank++);
                }
            }

            for (int j = 0; j < M; j++) {
                cur[j] = rankOfNum.get(cur[j]);
            }
        }
    }

    private static boolean isSame(int[] arr1, int[] arr2) {
        for (int i = 0; i < M; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }

}
