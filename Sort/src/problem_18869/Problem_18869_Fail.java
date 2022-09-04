package problem_18869;

import java.io.*;
import java.util.*;

public class Problem_18869_Fail {

    private static class Planet implements Comparable<Planet> {
        private int size;
        private int idx;

        public Planet(int size, int idx) {
            this.size = size;
            this.idx = idx;
        }

        @Override
        public int compareTo(Planet o) {
            return this.size - o.size;
        }

    }

    private static int N, M;
    private static Planet[][] universes;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        universes = new Planet[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                universes[i][j] = new Planet(Integer.parseInt(tokenizer.nextToken()), j);
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.sort(universes[i]);
        }

        int answer = 0;
        for (int i = 1; i < N; i++) {
            loop : for (int j = 0; j < i; j++) {
                for (int k = 1; k < M; k++) {
                    if (universes[i][k].idx != universes[j][k].idx) {
                        continue loop;
                    }

                    if ((universes[i][k - 1].size < universes[i][k].size) != (universes[j][k - 1].size < universes[j][k].size)) {
                        continue loop;
                    }
                }

                answer++;
            }
        }

        System.out.println(answer);
    }

}
