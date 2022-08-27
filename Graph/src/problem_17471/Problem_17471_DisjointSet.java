package problem_17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_17471_DisjointSet {

    private static class DisjointSet {
        private int[] parents;

        public DisjointSet(int size) {
            this.parents = new int[size];

            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
        }

        public int find(int cur) {
            if (parents[cur] == cur) {
                return cur;
            }

            return parents[cur] = find(parents[cur]);
        }

        public boolean union(int a, int b) {
            int rootOfA = find(a);
            int rootOfB = find(b);

            if (rootOfA == rootOfB) {
                return false;
            }

            parents[rootOfB] = rootOfA;
            return true;
        }
    }

    private static int N;
    private static int[] numOfPeople;
    private static int totalNumOfPeople;
    private static List<int[]> edgeList;
    private static int minDiff;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        numOfPeople = new int[N];

        StringTokenizer st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            numOfPeople[i] = Integer.parseInt(st.nextToken());
            totalNumOfPeople += numOfPeople[i];
        }

        edgeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            int sizeOfEdges = Integer.parseInt(st.nextToken());

            while (sizeOfEdges-- > 0) {
                int adjVertex = Integer.parseInt(st.nextToken()) - 1;
                edgeList.add(new int[] { i, adjVertex });
            }
        }

        minDiff = Integer.MAX_VALUE;
        generatePowerSet(0, 0, 0, 0);
        System.out.println((minDiff == Integer.MAX_VALUE) ? -1 : minDiff);
    }

    private static void generatePowerSet(int isSelected, int idx, int cnt, int sumOfSelected) {
        if (cnt > N >> 1) {
            return;
        }

        if (idx == N) {
            int curDiff = Math.abs(totalNumOfPeople - (sumOfSelected << 1));
            if (minDiff > curDiff && isValid(isSelected)) {
                minDiff = curDiff;
            }

            return;
        }

        generatePowerSet(isSelected | 1 << idx, idx + 1, cnt + 1, sumOfSelected + numOfPeople[idx]);
        generatePowerSet(isSelected, idx + 1, cnt, sumOfSelected);
    }

    private static boolean isValid(int isSelected) {
        if (isSelected == 0) {
            return false;
        }

        DisjointSet district = new DisjointSet(N);
        int numOfDistrict = N;

        for (int[] edge : edgeList) {
            int containsRed1 = isSelected & 1 << edge[0];
            int containsRed2 = isSelected & 1 << edge[1];

            if ((containsRed1 == 0 && containsRed2 != 0) || (containsRed1 != 0 && containsRed2 == 0)) {
                continue;
            }

            if (district.union(edge[0], edge[1])) {
                numOfDistrict--;
            }
        }

        if (numOfDistrict != 2) {
            return false;
        }

        return true;
    }

}
