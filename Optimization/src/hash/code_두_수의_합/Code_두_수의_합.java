package hash.code_두_수의_합;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Code_두_수의_합 {

    private static Map<Integer, Integer> numToCnt = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int k = input.nextInt();

        int answer = 0;
        while (n-- > 0) {
            int cur = input.nextInt();
            answer += numToCnt.getOrDefault(k - cur, 0);
            numToCnt.merge(cur, 1, (value, newValue) -> value + 1);
        }

        System.out.print(answer);
    }

}
