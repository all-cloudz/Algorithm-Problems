package implementation.problem_산타의_선물_공장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Problem_산타의_선물_공장 {

    private static int n;
    private static int m;
    private static Map<Integer, Node> idToNode;
    private static Node[] heads;
    private static Node[] tails;
    private static boolean[] broken;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int queryCnt = Integer.parseInt(input.readLine());
        while (queryCnt-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int query = Integer.parseInt(tokenizer.nextToken());

            int result = 0;
            switch (query) {
                case 100:
                    init(tokenizer);
                    continue;
                case 200:
                    result = unloadOrMoveBack(Integer.parseInt(tokenizer.nextToken()));
                    break;
                case 300:
                    result = deleteById(Integer.parseInt(tokenizer.nextToken()));
                    break;
                case 400:
                    result = findByIdAndMoveFront(Integer.parseInt(tokenizer.nextToken()));
                    break;
                case 500:
                    result = breakBelt(Integer.parseInt(tokenizer.nextToken()));
                    break;
                default:
                    throw new RuntimeException("이 코드는 실행될 수 없습니다.");
            }

            answer.append(result).append('\n');
        }

        System.out.println(answer);
    }

    private static void init(StringTokenizer tokenizer) {
        idToNode = new HashMap<>();
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        heads = new Node[m + 1];
        tails = new Node[m + 1];
        for (int i = 1; i <= m; i++) {
            heads[i] = new Node(-1, i);
            tails[i] = new Node(-1, i);
            heads[i].next = tails[i];
            heads[i].next.prev = heads[i];
        }

        int giftCnt = n / m;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < giftCnt; j++) {
                int id = Integer.parseInt(tokenizer.nextToken());
                addLast(i, new Node(id, i));
            }
        }

        for (int i = 1; i <= m; i++) {
            for (Node cur = heads[i].next; cur != tails[i]; cur = cur.next) {
                cur.weight = Integer.parseInt(tokenizer.nextToken());
            }
        }

        broken = new boolean[m + 1];
    }

    private static int unloadOrMoveBack(int limit) {
        int sumWeight = 0;

        for (int i = 1; i <= m; i++) {
            Node node = removeFirst(i);
            if (node == null) {
                continue;
            }

            if (node.weight > limit) {
                addLast(i, node);
            } else {
                sumWeight += node.weight;
            }
        }

        return sumWeight;
    }

    private static int deleteById(int id) {
        Node found = idToNode.get(id);
        if (found == null) {
            return -1;
        }

        Node prev = found.prev;
        prev.next = prev.next.next;
        prev.next.prev = prev;

        idToNode.remove(found.id);
        found.prev = null;
        found.next = null;
        return id;
    }

    private static int findByIdAndMoveFront(int id) {
        Node srcFront = idToNode.get(id);
        if (srcFront == null) {
            return -1;
        }

        int belt = srcFront.belt;
        Node srcRear = tails[belt].prev;
        disconnect(srcFront.prev, srcFront, srcRear, tails[belt]);
        connect(heads[belt], srcFront, srcRear, heads[belt].next);
        return belt;
    }

    private static int breakBelt(int belt) {
        if (broken[belt]) {
            return -1;
        }

        broken[belt] = true;
        if (heads[belt].next == tails[belt]) {
            return belt;
        }

        Node srcFront = heads[belt].next;
        Node srcRear = tails[belt].prev;

        int dest = belt;
        while (true) {
            if (!broken[dest]) {
                break;
            }

            if (++dest > m) {
                dest -= m;
            }
        }

        disconnect(heads[belt], srcFront, srcRear, tails[belt]);
        connect(tails[dest].prev, srcFront, srcRear, tails[dest]);

        for (Node cur = srcFront; cur != tails[dest]; cur = cur.next) {
            Node node = idToNode.get(cur.id);
            node.belt = dest;
        }

        return belt;
    }

    private static void addLast(int belt, Node node) {
        idToNode.put(node.id, node);
        connect(tails[belt].prev, node, tails[belt]);
    }

    private static Node removeFirst(int belt) {
        if (heads[belt].next == tails[belt]) {
            return null;
        }

        Node removed = heads[belt].next;
        heads[belt].next = heads[belt].next.next;
        heads[belt].next.prev = heads[belt];

        idToNode.remove(removed.id);
        removed.prev = null;
        removed.next = null;
        return removed;
    }

    private static void connect(Node left, Node mid, Node right) {
        mid.prev = left;
        mid.next = right;
        left.next = mid;
        right.prev = mid;
    }

    private static void connect(Node destHead, Node srcFront, Node srcRear, Node destTail) {
        srcFront.prev = destHead;
        srcRear.next = destTail;
        destHead.next = srcFront;
        destTail.prev = srcRear;
    }

    private static void disconnect(Node node, Node srcFront, Node srcTail, Node tail) {
        node.next = tail;
        node.next.prev = node;
        srcFront.prev = null;
        srcTail.next = null;
    }

    private static class Node {
        private int id;
        private int weight;
        private int belt;
        private Node next;
        private Node prev;

        public Node(int id, int belt) {
            this.id = id;
            this.belt = belt;
        }
    }

    private static class NodeDto {
        private int belt;
        private Node node;

        public NodeDto(int belt, Node node) {
            this.belt = belt;
            this.node = node;
        }
    }

}
