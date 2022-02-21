package problem_5639;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Problem_5639 {
    private static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

    private static class Node {
        private int data;

        private Node parent;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(input.readLine()));

        while (input.ready()) {
            Node cur = root;
            Node newNode = new Node(Integer.parseInt(input.readLine()));

            while (true) {
                if (cur.data > newNode.data) {
                    if (cur.left == null) {
                        cur.left = newNode;
                        break;
                    }
                    cur = cur.left;
                } else {
                    if (cur.right == null) {
                        cur.right = newNode;
                        break;
                    }
                    cur = cur.right;
                }
            }
        }

        traversePostOrder(root);
        output.flush();
        output.close();
    }

    private static void traversePostOrder(Node node) throws IOException {
        if (node == null) {
            return;
        }

        traversePostOrder(node.left);
        traversePostOrder(node.right);
        output.write(String.valueOf(node.data));
        output.newLine();
    }
}
