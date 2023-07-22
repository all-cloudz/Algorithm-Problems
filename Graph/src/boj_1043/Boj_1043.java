package boj_1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.StringTokenizer;

public class Boj_1043 {

    private static int N;
    private static int M;
    private static int src;
    private static DisjointSet disjointSet;
    private static int[][] parties;

    public static void main(final String[] args) {
        init();
        shareTruth();
        final int answer = countMsgTalk();
        System.out.println(answer);
    }

    private static void shareTruth() {
        for (final int[] party : parties) {
            if (!containsKnowing(party)) {
                continue;
            }

            disjointSet.union(src, party[0]);
        }
    }

    private static int countMsgTalk() {
        int count = 0;

        for (final int[] party : parties) {
            if (containsKnowing(party)) {
                continue;
            }

            count++;
        }

        return count;
    }

    private static boolean containsKnowing(final int[] party) {
        final int root = disjointSet.find(src);

        for (final int participant : party) {
            final int parent = disjointSet.find(participant);

            if (parent == root) {
                return true;
            }
        }

        return false;
    }

    private static void init() {
        try (final BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());

            disjointSet = new DisjointSet(N);
            tokenizer = new StringTokenizer(input.readLine());
            final int size = Integer.parseInt(tokenizer.nextToken());

            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    src = Integer.parseInt(tokenizer.nextToken());
                    continue;
                }

                final int desc = Integer.parseInt(tokenizer.nextToken());
                disjointSet.union(src, desc);
            }

            parties = new int[M][];
            for (int i = 0; i < M; i++) {
                tokenizer = new StringTokenizer(input.readLine());
                final int participantCount = Integer.parseInt(tokenizer.nextToken());
                parties[i] = new int[participantCount];

                for (int j = 0; j < participantCount; j++) {
                    parties[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }

            for (final int[] party : parties) {
                for (int i = 1; i < party.length; i++) {
                    disjointSet.union(party[i - 1], party[i]);
                }
            }
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static final class DisjointSet {
        private final int[] parents;

        public DisjointSet(final int size) {
            this.parents = new int[size + 1];
            for (int i = 1; i <= size; i++) {
                this.parents[i] = i;
            }
        }

        public int find(final int cur) {
            if (parents[cur] == cur) {
                return cur;
            }

            final int root = find(parents[cur]);
            return parents[cur] = root;
        }

        public boolean union(final int src, final int desc) {
            final int srcParent = find(src);
            final int descParent = find(desc);

            if (srcParent == descParent) {
                return false;
            }

            parents[descParent] = srcParent;
            return true;
        }
    }
}
