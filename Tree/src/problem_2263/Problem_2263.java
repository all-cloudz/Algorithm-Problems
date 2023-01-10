package problem_2263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_2263 {

    private static int N;
    private static int[] inOrder;
    private static int[] indexCache;
    private static int[] postOrder;
    private static List<Integer> preOrder;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(input.readLine());
        inOrder = new int[N];
        indexCache = new int[N + 1];
        postOrder = new int[N];
        preOrder = new ArrayList<>();

        StringTokenizer tokenizer1 = new StringTokenizer(input.readLine());
        StringTokenizer tokenizer2 = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            inOrder[i] = Integer.parseInt(tokenizer1.nextToken());
            indexCache[inOrder[i]] = i;
            postOrder[i] = Integer.parseInt(tokenizer2.nextToken());
        }

        setPreOrder(0, N - 1, 0, N - 1);
        for (int node : preOrder) {
            answer.append(node).append(' ');
        }
        System.out.println(answer);
    }

    private static void setPreOrder(int inLeft, int inRight, int postLeft, int postRight)  {
        if (inLeft > inRight || postLeft > postRight) {
            return;
        }

        int root = postOrder[postRight];
        preOrder.add(root);

        int pivotPos = indexCache[root];
        int leftCount = pivotPos - inLeft;
        setPreOrder(inLeft, pivotPos - 1, postLeft, postLeft + leftCount - 1);
        setPreOrder(pivotPos + 1, inRight, postLeft + leftCount, postRight - 1);
    }

}
