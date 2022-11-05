package problem_2141;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Problem_2141 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        TreeMap<Long, Long> countOfLocation = new TreeMap<>();

        long totalCount = 0;
        while (N-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            long location = Long.parseLong(tokenizer.nextToken());
            long count = Long.parseLong(tokenizer.nextToken());

            countOfLocation.put(location, count);
            totalCount += count;
        }

        long pointCut = (totalCount + 1) >> 1;
        long curCount = 0;
        for (Long location : countOfLocation.keySet()) {
            curCount += countOfLocation.get(location);

            if (curCount >= pointCut) {
                System.out.println(location);
                return;
            }
        }
    }

}
