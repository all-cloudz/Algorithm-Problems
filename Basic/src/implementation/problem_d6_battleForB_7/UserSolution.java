package implementation.problem_d6_battleForB_7;

import java.util.*;

public class UserSolution {

    static class Piece implements Comparable<Piece> {
        private int row;
        private int col;
        private int adjCnt;
        private boolean isPutted;
        private int[] piece;

        public Piece(int row, int col, boolean isPutted) {
            this.row = row;
            this.col = col;
            this.adjCnt = 0;
            this.isPutted = isPutted;
            this.piece = new int[4];
        }

        @Override
        public int compareTo(Piece o) {
            if (this.adjCnt != o.adjCnt) {
                return o.adjCnt - this.adjCnt;
            }

            if (this.row != o.row) {
                return this.row - o.row;
            }

            return this.col - o.col;
        }

        @Override
        public int hashCode() {
            return 31 * row ^ 101 * col;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Piece)) {
                return false;
            }

            Piece that = (Piece) obj;
            return this.row == that.row && this.col == that.col;
        }
    }

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static final int[][] MATCH_IDX = { { 0, 2 }, { 2, 0 }, { 3, 1 }, { 1, 3 } };

    private int M;
    private Piece[][] puzzle;
    private Set<Piece> candidateSet;

    void init(int N, int M, int[][] mU, int[][] mR, int[][] mB, int[][] mL) {
        this.M = M;
        puzzle = new Piece[N + 2][N + 2];
        candidateSet = new TreeSet<>();

        for (int i = 0; i < N + 2; i++) {
            for (int j = 0; j < N + 2; j++) {
                puzzle[i][j] = new Piece(i, j, false);
            }
        }

        for (int i = 1; i <= N; i++) {
            puzzle[i][0].isPutted = true;
            puzzle[i][0].piece = mL[i];
            puzzle[i][1].adjCnt++;
            candidateSet.add(puzzle[i][1]);

            puzzle[i][N + 1].isPutted = true;
            puzzle[i][N + 1].piece = mR[i];
            puzzle[i][N].adjCnt++;
            candidateSet.add(puzzle[i][N + 1]);
        }

        for (int j = 1; j <= N; j++) {
            puzzle[0][j].isPutted = true;
            puzzle[0][j].piece = mU[j];
            puzzle[1][j].adjCnt++;
            candidateSet.add(puzzle[1][j]);

            puzzle[N + 1][j].isPutted = true;
            puzzle[N + 1][j].piece = mB[j];
            puzzle[N + 1][j].adjCnt++;
            candidateSet.add(puzzle[N + 1][j]);
        }
    }

    int put(int[] mPiece) {
        Iterator<Piece> iterator = candidateSet.iterator();

        while (iterator.hasNext()) {
            Piece cur = iterator.next();

            for (int rotate = 0; rotate < M; rotate++) {
                int[] rotated = new int[4];
                System.arraycopy(mPiece, rotate, rotated, 0, M - rotate);
                System.arraycopy(mPiece, 0, rotated, M - rotate, rotate);

                if (isPlaceable(cur.row, cur.col, rotated)) {
                    cur.piece = rotated;
                    cur.isPutted = true;
                    update(cur.row, cur.col);
                    iterator.remove();
                    return cur.row + cur.col;
                }
            }
        }

        return -1;
    }

    boolean isPlaceable(int row, int col, int[] rotated) {
        for (int i = 0; i < 4; i++) {
            int nextRow = row + DIRECTIONS[i][0];
            int nextCol = col + DIRECTIONS[i][1];

            Piece neighbor = puzzle[nextRow][nextCol];

            if (neighbor.isPutted && !isMatchable(rotated[MATCH_IDX[i][0]], neighbor.piece[MATCH_IDX[i][1]])) {
                return false;
            }
        }

        return true;
    }

    boolean isMatchable(int num1, int num2) {
        for (int i = 0; i < M; i++) {
            int pos1 = (num1 % ((int) Math.pow(10, i + 1))) / ((int) Math.pow(10, i));
            int pos2 = (num2 % ((int) Math.pow(10, M - i))) / ((int) Math.pow(10, M - i - 1));

            if (pos1 + pos2 != 10) {
                return false;
            }
        }

        return true;
    }

    void update(int row, int col) {
        for (int[] move : DIRECTIONS) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];

            Piece neighbor = puzzle[nextRow][nextCol];

            if (neighbor.isPutted) {
                continue;
            }

            neighbor.adjCnt++;
            candidateSet.add(neighbor);
        }
    }

}
