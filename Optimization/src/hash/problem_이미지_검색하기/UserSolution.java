package hash.problem_이미지_검색하기;

class UserSolution {

    private static int[] countOfOne;

    private int N;
    private int M;
    private int[][] hashed;
    private int maxBit;

    static {
        countOfOne = new int[1 << 10];
        for (int size = (1 << 10), i = 0; i < size; i++) {
            countOfOne[i] = countOfOne(i);
        }
    }

    private static int countOfOne(int num) {
        int count = 0;

        int bit = 1;
        while (bit <= num) {
            if ((bit & num) != 0) {
                count++;
            }

            bit <<= 1;
        }

        return count;
    }

    void init(int N, int M, char[][][] mImageList) {
        this.N = N;
        this.M = M;
        this.hashed = new int[N][M];

        for (int i = 0; i < N; i++) {
            this.hashed[i] = hashes(mImageList[i]);
        }

        this.maxBit = (1 << M) - 1;
    }

    int findImage(char[][] mImage) {
        int[] target = hashes(mImage);

        int maxSimilarity = Integer.MIN_VALUE;
        int argOfMax = -1;
        for (int i = 0; i < N; i++) {
            int[] id = this.hashed[i];

            int similarity = 0;
            for (int j = 0; j < M; j++) {
                int key = ~(id[j] ^ target[j]) & this.maxBit;
                similarity += countOfOne[key] - 1;
            }

            if (maxSimilarity < similarity) {
                maxSimilarity = similarity;
                argOfMax = i + 1;
            }
        }

        return argOfMax;
    }

    private int[] hashes(char[][] image) {
        int[] hashes = new int[M];

        for (int i = 0; i < M; i++) {
            hashes[i] = hash(image[i]);
        }

        return hashes;
    }

    private int hash(char[] line) {
        int hash = 1 << M;

        for (int i = 0; i < M; i++) {
            if (line[i] == 1) {
                hash += 1 << i;
            }
        }

        return hash;
    }

}
