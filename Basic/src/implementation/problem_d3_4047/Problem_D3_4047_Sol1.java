package implementation.problem_d3_4047;

import java.io.*;

public class Problem_D3_4047_Sol1 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        loop : for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append(' ');
            boolean[][] discovered = new boolean[4][14]; // 카드 S, D, H, C의 소유 여부
            int[] cntOfCard = { 13, 13, 13, 13 }; // 카드 S, D, H, C의 전체 개수

            String str = input.readLine();
            for (int len = str.length(), i = 0; i < len; i += 3) {
                char card = str.charAt(i);
                int number = Integer.parseInt(str.substring(i + 1, i + 3));

                if (card == 'S' && !discovered[0][number]) {
                    cntOfCard[0]--;
                    discovered[0][number] = true;
                    continue;
                } else if (card == 'S' && discovered[0][number]) {
                    answer.append("ERROR").append('\n');
                    continue loop;
                }

                if (card == 'D' && !discovered[1][number]) {
                    cntOfCard[1]--;
                    discovered[1][number] = true;
                    continue;
                } else if (card == 'D' && discovered[1][number]) {
                    answer.append("ERROR").append('\n');
                    continue loop;
                }

                if (card == 'H' && !discovered[2][number]) {
                    cntOfCard[2]--;
                    discovered[2][number] = true;
                    continue;
                } else if (card == 'H' && discovered[2][number]) {
                    answer.append("ERROR").append('\n');
                    continue loop;
                }

                if (card == 'C' && !discovered[3][number]) {
                    cntOfCard[3]--;
                    discovered[3][number] = true;
                } else if (card == 'C' && discovered[3][number]) {
                    answer.append("ERROR").append('\n');
                    continue loop;
                }
            }

            answer.append(cntOfCard[0]).append(' ').append(cntOfCard[1]).append(' ').append(cntOfCard[2]).append(' ').append(cntOfCard[3]).append('\n');
        }

        System.out.println(answer);
    }
}
