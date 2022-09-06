package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Problem_Tree1_11_Sol2 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        Map<Character, Integer> cntOfChar = new HashMap<>();
        int[] cache = new int['z' + 1];

        String str = input.readLine();
        for (int i = str.length() - 1; i >= 0; i--) {
            char cur = str.charAt(i);
            cntOfChar.put(cur, cntOfChar.getOrDefault(cur, 0) + 1);
            cache[cur] = i;
        }

        char answer = 0;
        for (char key : cntOfChar.keySet()) {
            if (cntOfChar.get(key) != 1) {
                continue;
            }

            if (answer == 0) {
                answer = key;
                continue;
            }

            if (cache[key] < cache[answer]) {
                answer = key;
            }
        }
        System.out.println((answer == 0) ? "None" : answer);
    }

}
