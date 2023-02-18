package hash.problem_섬_지키기;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class UserSolution {

    private static class Candidate {
        private int row;
        private int col;
        private boolean isHorizontal;
        private boolean isReverse;

        public Candidate(int row, int col, boolean isHorizontal, boolean isReverse) {
            this.row = row;
            this.col = col;
            this.isHorizontal = isHorizontal;
            this.isReverse = isReverse;
        }
    }

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private int N;
    private int[][] initMap;
    private int[][] cloneMap;
    private Map<Integer, List<Candidate>> candidates;

    public void init(int N, int[][] mMap) {
        this.N = N;
        this.initMap = new int[N + 2][N + 2];
        this.cloneMap = new int[N + 2][N + 2];
        this.candidates = new HashMap<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                initMap[i + 1][j + 1] = mMap[i][j];
                cloneMap[i + 1][j + 1] = mMap[i][j];
            }
        }

        preprocessing(N, initMap, candidates);
    }

    public int numberOfCandidate(int M, int mStructure[]) {
        if (M == 1) {
            return N * N;
        }

        int hash = getStructureHash(M, mStructure);
        return candidates.getOrDefault(hash, Collections.emptyList()).size();
    }

    public int maxArea(int M, int mStructure[], int mSeaLevel) {
        int maxArea = -1;

        if (M == 1) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    cloneMap[i][j] += mStructure[0];
                    maxArea = Math.max(maxArea, countNotSubmergedArea(cloneMap, mSeaLevel));
                    cloneMap[i][j] -= mStructure[0];
                }
            }

            return maxArea;
        }

        int hash = getStructureHash(M, mStructure);
        for (Candidate cur : candidates.getOrDefault(hash, Collections.emptyList())) {
            if (cur.isHorizontal) {
                installHorizontalStructure(M, mStructure, cur);
                maxArea = Math.max(maxArea, countNotSubmergedArea(cloneMap, mSeaLevel));
                uninstallHorizontalStructure(M, cur);
                continue;
            }

            installVerticalStructure(M, mStructure, cur);
            maxArea = Math.max(maxArea, countNotSubmergedArea(cloneMap, mSeaLevel));
            uninstallVerticalStructure(M, cur);
        }

        return maxArea;
    }

    private void installHorizontalStructure(int M, int[] mStructure, Candidate cur) {
        int height = getHeight(M, mStructure, cur);
        for (int i = 0; i < M; i++) {
            cloneMap[cur.row][cur.col + i] = height;
        }
    }

    private void uninstallHorizontalStructure(int M, Candidate cur) {
        for (int i = 0; i < M; i++) {
            cloneMap[cur.row][cur.col + i] = initMap[cur.row][cur.col + i];
        }
    }

    private void installVerticalStructure(int M, int[] mStructure, Candidate cur) {
        int height = getHeight(M, mStructure, cur);
        for (int i = 0; i < M; i++) {
            cloneMap[cur.row + i][cur.col] = height;
        }
    }

    private void uninstallVerticalStructure(int M, Candidate cur) {
        for (int i = 0; i < M; i++) {
            cloneMap[cur.row + i][cur.col] = initMap[cur.row + i][cur.col];
        }
    }

    private int getHeight(int M, int[] mStructure, Candidate cur) {
        int height = initMap[cur.row][cur.col];

        if (!cur.isReverse) {
            height += mStructure[0];
        } else {
            height += mStructure[M - 1];
        }

        return height;
    }

    private int countNotSubmergedArea(int[][] cloneMap, int mSeaLevel) {
        Queue<int[]> points = new ArrayDeque<>();
        boolean[][] discovered = new boolean[N + 2][N + 2];

        for (int i = 0; i <= N + 1; i++) {
            points.add(new int[] { i, 0 });
            discovered[i][0] = true;

            points.add(new int[] { i, N + 1 });
            discovered[i][N + 1] = true;
        }
        
        for (int i = 0; i <= N + 1; i++) {
            points.add(new int[] { 0, i });
            discovered[0][i] = true;
            
            points.add(new int[] { N + 1, i });
            discovered[N + 1][i] = true;
        }

        while (!points.isEmpty()) {
            int[] cur = points.poll();

            for (int[] dir : DIRECTIONS) {
                int nextRow = cur[0] + dir[0];
                int nextCol = cur[1] + dir[1];

                if (!isMovable(nextRow, nextCol) || discovered[nextRow][nextCol]) {
                    continue;
                }

                if (cloneMap[nextRow][nextCol] < mSeaLevel) {
                    points.add(new int[] { nextRow, nextCol });
                    discovered[nextRow][nextCol] = true;
                }
            }
        }

        int countArea = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!discovered[i][j]) {
                    countArea++;
                }
            }
        }

        return countArea;
    }

    private boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow <= N + 1 && 0 <= nextCol && nextCol <= N + 1;
    }

    private void preprocessing(int N, int[][] initMap, Map<Integer, List<Candidate>> candidates) {
        for (int length = 2; length <= 5; length++) {
            for (int row = 1; row <= N; row++) {
                for (int finish = N - length + 1, col = 1; col <= finish; col++) {
                    int hash = getColHash(initMap, length, row, col);
                    candidates.putIfAbsent(hash, new ArrayList<>());
                    candidates.get(hash).add(new Candidate(row, col, true, false));

                    int reverseHash = getReverseColHash(initMap, length, row, col);

                    if (reverseHash == hash) {
                        continue;
                    }

                    candidates.putIfAbsent(reverseHash, new ArrayList<>());
                    candidates.get(reverseHash).add(new Candidate(row, col, true, true));
                }
            }

            for (int col = 1; col <= N; col++) {
                for (int finish = N - length + 1, row = 1; row <= finish; row++) {
                    int hash = getRowHash(initMap, length, row, col);
                    candidates.putIfAbsent(hash, new ArrayList<>());
                    candidates.get(hash).add(new Candidate(row, col, false, false));

                    int reverseHash = getReverseRowHash(initMap, length, row, col);

                    if (reverseHash == hash) {
                        continue;
                    }

                    candidates.putIfAbsent(reverseHash, new ArrayList<>());
                    candidates.get(reverseHash).add(new Candidate(row, col, false, true));
                }
            }
        }
    }

    private int getColHash(int[][] initMap, int length, int row, int col) {
        int hash = 0;

        for (int k = 1; k < length; k++) {
            hash = hash * 10 + (initMap[row][col + k] - initMap[row][col + k - 1] + 5);
        }

        return hash;
    }

    private int getReverseColHash(int[][] initMap, int length, int row, int col) {
        int hash = 0;

        for (int k = length - 2; k >= 0; k--) {
            hash = hash * 10 + (initMap[row][col + k] - initMap[row][col + k + 1] + 5);
        }

        return hash;
    }

    private int getRowHash(int[][] initMap, int length, int row, int col) {
        int hash = 0;

        for (int k = 1; k < length; k++) {
            hash = hash * 10 + (initMap[row + k][col] - initMap[row + k - 1][col] + 5);
        }

        return hash;
    }

    private int getReverseRowHash(int[][] initMap, int length, int row, int col) {
        int hash = 0;

        for (int k = length - 2; k >= 0; k--) {
            hash = hash * 10 + (initMap[row + k][col] - initMap[row + k + 1][col] + 5);
        }

        return hash;
    }

    private static int getStructureHash(int M, int[] mStructure) {
        int hash = 0;

        for (int i = 1; i < M; i++) {
            hash = hash * 10 + (mStructure[i - 1] - mStructure[i] + 5);
        }

        return hash;
    }

}
