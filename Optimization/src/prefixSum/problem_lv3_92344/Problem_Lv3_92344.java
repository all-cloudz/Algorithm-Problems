package prefixSum.problem_lv3_92344;

public class Problem_Lv3_92344 {

    private int rowSize, colSize;
    private int[][] prefixSum;

    public int solution(int[][] board, int[][] skill) {
        rowSize = board.length;
        colSize = board[0].length;
        prefixSum = new int[rowSize + 1][colSize + 1];
        useSkills(skill);

        int answer = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (board[i][j] + prefixSum[i][j] >= 1) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private void useSkills(int[][] skills) {
        for (int len = skills.length, i = 0; i < len; i++) {
            useSkill(skills[i]);
        }

        for (int i = rowSize - 1; i >= 0 ; i--) {
            for (int j = colSize - 1; j >= 0; j--) {
                prefixSum[i][j] += prefixSum[i + 1][j] + prefixSum[i][j + 1] - prefixSum[i + 1][j + 1];
            }
        }
    }

    private void useSkill(int[] skill) {
        int type = skill[0];
        int r1 = skill[1];
        int c1 = skill[2];
        int r2 = skill[3];
        int c2 = skill[4];
        int degree = skill[5];

        if (type == 1) {
            degree *= -1;
        }

        if (r1 != 0 && c1 != 0) {
            prefixSum[r1 - 1][c1 - 1] += degree;
        }

        if (c1 != 0) {
            prefixSum[r2][c1 - 1] -= degree;
        }

        if (r1 != 0) {
            prefixSum[r1 - 1][c2] -= degree;
        }

        prefixSum[r2][c2] += degree;
    }

    public static void main(String[] args) {
        int result1 = new Problem_Lv3_92344().solution(
                new int[][] {
                        { 5, 5, 5, 5, 5 },
                        { 5, 5, 5, 5, 5 },
                        { 5, 5, 5, 5, 5 },
                        { 5, 5, 5, 5, 5 }
                },
                new int[][] {
                        { 1, 0, 0, 3, 4, 4 },
                        { 1, 2, 0, 2, 3, 2 },
                        { 2, 1, 0, 3, 1, 2 },
                        { 1, 0, 1, 3, 3, 1 },
                }
        );

        System.out.println(result1);

        int result2 = new Problem_Lv3_92344().solution(
                new int[][] {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 }
                },
                new int[][] {
                        { 1, 1, 1, 2, 2, 4 },
                        { 1, 0, 0, 1, 1, 2 },
                        { 2, 2, 0, 2, 0, 100 }
                }
        );
        System.out.println(result2);
    }

}
