package string.problem_끝말잇기2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

class UserSolution {

    private int N;
    private Cycle cycle;
    private TreeMap<Character, TreeSet<String>> words;
    private Set<String> usedWords;
    private List<String> newWords;

    public void init(int N, int M, char[][] mWords) {
        this.N = N;
        this.cycle = new Cycle(N);
        this.usedWords = new HashSet<>();
        this.newWords = new ArrayList<>();

        this.words = new TreeMap<>();
        for (int i = 0; i < M; i++) {
            char[] word = mWords[i];
            words.putIfAbsent(word[0], new TreeSet<>());

            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                if (word[j] == '\0') {
                    break;
                }

                builder.append(word[j]);
            }

            words.get(word[0]).add(builder.toString());
        }
    }

    public int playRound(int mId, char mCh) {
        cycle.start(mId);

        while (!words.getOrDefault(mCh, new TreeSet<>()).isEmpty()) {
            String cur = words.get(mCh).pollFirst();
            usedWords.add(cur);

            String reversed = new StringBuilder(cur).reverse().toString();
            newWords.add(reversed);

            mCh = reversed.charAt(0);
            cycle.pass();
        }

        for (String newWord : newWords) {
            if (usedWords.contains(newWord)) {
                continue;
            }

            words.putIfAbsent(newWord.charAt(0), new TreeSet<>());
            words.get(newWord.charAt(0)).add(newWord);
        }

        return cycle.fail();
    }

    private static class Cycle {

        private int cur;
        private Node[] nodes;

        public Cycle(int N) {
            this.nodes = new Node[N + 1];
            for (int i = 1; i <= N; i++) {
                nodes[i] = new Node(i);
            }

            for (int i = 1; i <= N; i++) {
                if (i == 1) {
                    nodes[i].prev = nodes[N];
                } else {
                    nodes[i].prev = nodes[i - 1];
                }

                if (i == N) {
                    nodes[i].next = nodes[1];
                } else {
                    nodes[i].next = nodes[i + 1];
                }
            }
        }

        private void start(int id) {
            this.cur = id;
        }

        private void pass() {
            cur = nodes[cur].next.no;
        }

        private int fail() {
            int failed = cur;
            Node next = nodes[cur].next;
            disconnect(nodes[cur].prev, nodes[cur], next);
            cur = next.no;
            return failed;
        }

        private void disconnect(Node left, Node mid, Node right) {
            left.next = right;
            right.prev = left;
            mid.prev = null;
            mid.next = null;
        }

    }

    private static class Node {

        private int no;
        private Node prev;
        private Node next;

        public Node(int no) {
            this.no = no;
        }

        public Node(int no, Node prev, Node next) {
            this.no = no;
            this.prev = prev;
            this.next = next;
        }

    }

}