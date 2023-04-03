package implementation.problem_산타의_선물_공장_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_산타의_선물_공장_2 {

    private static Node[] heads;
    private static Node[] tails;
    private static int[] counts;
    private static Node[] gifts;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader input = new BufferedReader(new FileReader("Basic/src/implementation/problem_산타의_선물_공장_2/input.txt"));
        StringBuilder answer = new StringBuilder();

        int queryCnt = Integer.parseInt(input.readLine());
        while (queryCnt-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int query = Integer.parseInt(tokenizer.nextToken());

            switch (query) {
                case 100:
                    init(tokenizer);
                    break;
                case 200:
                    answer.append(moveAll(tokenizer)).append('\n');
                    break;
                case 300:
                    answer.append(changeFront(tokenizer)).append('\n');
                    break;
                case 400:
                    answer.append(divideHalf(tokenizer)).append('\n');
                    break;
                case 500:
                    answer.append(getGiftInfo(tokenizer)).append('\n');
                    break;
                case 600:
                    answer.append(getBeltInfo(tokenizer)).append('\n');
                    break;
                default:
                    throw new RuntimeException("실행될 수 없는 코드입니다.");
            }
        }

        System.out.println(answer);
    }

    private static void init(StringTokenizer tokenizer) {
        int beltCnt = Integer.parseInt(tokenizer.nextToken());
        heads = new Node[beltCnt + 1];
        tails = new Node[beltCnt + 1];
        counts = new int[beltCnt + 1];
        for (int i = 1; i <= beltCnt; i++) {
            heads[i] = new Node(-1);
            tails[i] = new Node(-1);
            heads[i].next = tails[i];
            heads[i].next.prev = heads[i];
        }

        int giftCnt = Integer.parseInt(tokenizer.nextToken());
        gifts = new Node[giftCnt + 1];
        for (int i = 1; i <= giftCnt; i++) {
            int idx = Integer.parseInt(tokenizer.nextToken());
            gifts[i] = new Node(i);
            connect(tails[idx].prev, gifts[i], tails[idx]);
            counts[idx]++;
        }
    }

    private static int moveAll(StringTokenizer tokenizer) {
        int from = Integer.parseInt(tokenizer.nextToken());
        int to = Integer.parseInt(tokenizer.nextToken());

        if (heads[from].next == tails[from]) {
            return counts[to];
        }

        merge(heads[to], heads[from].next, tails[from].prev, heads[to].next);
        heads[from].next = tails[from];
        heads[from].next.prev = heads[from];

        counts[to] += counts[from];
        counts[from] = 0;
        return counts[to];
    }

    private static int changeFront(StringTokenizer tokenizer) {
        int from = Integer.parseInt(tokenizer.nextToken());
        int to = Integer.parseInt(tokenizer.nextToken());

        if (heads[to].next == tails[to]) {
            moveOne(from, to);
            return counts[to];
        }

        if (heads[from].next == tails[from]) {
            moveOne(to, from);
            return counts[to];
        }

        Node fromFirst = heads[from].next;
        Node fromSecond = heads[from].next.next;
        Node toFirst = heads[to].next;
        Node toSecond = heads[to].next.next;

        connect(heads[from], toFirst, fromSecond);
        connect(heads[to], fromFirst, toSecond);
        return counts[to];
    }

    private static void moveOne(int src, int dest) {
        if (heads[src].next == tails[src]) {
            return;
        }

        Node srcFirst = heads[src].next;
        Node srcSecond = heads[src].next.next;

        connect(heads[dest], srcFirst, tails[dest]);
        heads[src].next = srcSecond;
        heads[src].next.prev = heads[src];

        counts[src]--;
        counts[dest]++;
    }

    private static int divideHalf(StringTokenizer tokenizer) {
        int from = Integer.parseInt(tokenizer.nextToken());
        int to = Integer.parseInt(tokenizer.nextToken());

        int half = counts[from] / 2;
        if (half == 0) {
            return counts[to];
        }

        Node fromFirst = heads[from].next;
        Node fromHalf = heads[from];
        for (int i = 0; i < half; i++) {
            fromHalf = fromHalf.next;
        }

        heads[from].next = fromHalf.next;
        heads[from].next.prev = heads[from];
        merge(heads[to], fromFirst, fromHalf, heads[to].next);

        counts[from] -= half;
        counts[to] += half;
        return counts[to];
    }

    private static int getGiftInfo(StringTokenizer tokenizer) {
        int idx = Integer.parseInt(tokenizer.nextToken());
        Node gift = gifts[idx];
        return gift.prev.data + (gift.next.data << 1);
    }

    private static int getBeltInfo(StringTokenizer tokenizer) {
        int idx = Integer.parseInt(tokenizer.nextToken());
        return heads[idx].next.data + (tails[idx].prev.data << 1) + (3 * counts[idx]);
    }

    private static void connect(Node left, Node mid, Node right) {
        mid.prev = left;
        mid.next = right;
        left.next = mid;
        right.prev = mid;
    }

    private static void merge(Node toHead, Node fromFirst, Node fromLast, Node toFirst) {
        fromFirst.prev = toHead;
        fromLast.next = toFirst;
        toHead.next = fromFirst;
        toFirst.prev = fromLast;
    }

    private static class Node {
        private final int data;
        private Node next;
        private Node prev;

        public Node(int data) {
            this.data = data;
        }
    }

}
