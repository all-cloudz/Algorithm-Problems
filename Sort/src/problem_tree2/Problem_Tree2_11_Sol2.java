package problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_Tree2_11_Sol2 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int q = Integer.parseInt(tokenizer.nextToken());

        TreeSet<Integer> points = new TreeSet<>();
        tokenizer = new StringTokenizer(input.readLine());
        while (n-- > 0) {
            points.add(Integer.parseInt(tokenizer.nextToken()));
        }
        points.add(Integer.MIN_VALUE);
        points.add(Integer.MAX_VALUE);

        Map<Integer, Integer> compressionOfPoint = new HashMap<>();
        Iterator<Integer> iterator = points.iterator();
        for (int cnt = 0; iterator.hasNext(); cnt++) {
            compressionOfPoint.put(iterator.next(), cnt);
        }

        while (q-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            // TreeSet의 메서드를 이용하면 LowerBound나 UpperBound를 구하는 것이 간단해진다.
            int cntOfLBD = compressionOfPoint.get(points.floor(Integer.parseInt(tokenizer.nextToken())));
            int cntOfUBD = compressionOfPoint.get(points.ceiling(Integer.parseInt(tokenizer.nextToken())));

            answer.append(cntOfUBD - cntOfLBD + 1).append('\n');
        }

        System.out.println(answer);
    }

}
