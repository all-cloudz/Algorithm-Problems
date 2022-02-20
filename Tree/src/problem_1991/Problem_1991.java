package problem_1991;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Problem_1991 {
    private static StringBuilder answer;

    private static class Node {
        private char data;
        private Node left;
        private Node right;

        public Node(char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());

        String str = input.readLine();
        char[] chars = new char[] { str.charAt(0), str.charAt(2), str.charAt(4) };

        Node root = new Node(chars[0]);

        if (chars[1] != '.') {
            root.left = new Node(chars[1]);
        }

        if (chars[2] != '.') {
            root.right = new Node(chars[2]);
        }

        for (int i = 1; i < N; i++) {
            str = input.readLine();
            chars = new char[] { str.charAt(0), str.charAt(2), str.charAt(4) };

            Node cur = null;

            if (chars[1] != '.' || chars[2] != '.') {
                cur = search(root, chars[0]);
            }

            if (cur == null) {
                continue;
            }

            if (chars[1] != '.') {
                cur.left = new Node(chars[1]);
            }

            if (chars[2] != '.') {
                cur.right = new Node(chars[2]);
            }
        }

        answer = new StringBuilder();

        traversePreOrder(root);
        answer.append("\n");
        traversInOrder(root);
        answer.append("\n");
        traversePostOrder(root);

        System.out.println(answer);
    }

    private static Node search(Node node, char data) {
        if (node == null) {
            return null;
        }

        if (node.data == data) {
            return node;
        }

        Node left = search(node.left, data);
        Node right = search(node.right, data);

        return (left != null) ? left : right;
    }

    private static void traversePreOrder(Node node) {
        if (node == null) {
            return;
        }

        answer.append(node.data);
        traversePreOrder(node.left);
        traversePreOrder(node.right);
    }

    private static void traversInOrder(Node node) {
        if (node == null) {
            return;
        }

        traversInOrder(node.left);
        answer.append(node.data);
        traversInOrder(node.right);
    }

    private static void traversePostOrder(Node node) {
        if (node == null) {
            return;
        }

        traversePostOrder(node.left);
        traversePostOrder(node.right);
        answer.append(node.data);
    }
}
