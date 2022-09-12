package problem_tree1;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.HashSet;
        import java.util.Set;
        import java.util.StringTokenizer;

public class Problem_Tree1_19 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        Set<Long> numSet = new HashSet<>();
        int n = Integer.parseInt(input.readLine());
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        while (n-- > 0) {
            numSet.add(Long.parseLong(tokenizer.nextToken()));
        }

        int m = Integer.parseInt(input.readLine());
        long[] nums = new long[m];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < m; i++) {
            nums[i] = Long.parseLong(tokenizer.nextToken());
        }

        for (int i = 0; i < m; i++) {
            if (numSet.contains(nums[i])) {
                answer.append("1\n");
                continue;
            }

            answer.append("0\n");
        }

        System.out.println(answer);
    }

}
