package backtracking.problem_1759;

import java.io.*;
import java.util.*;

public class Problem_1759 {
    private static StringBuilder answer = new StringBuilder();
    private static int L;
    private static char[] chars;
    private static HashSet<String> vowels = new HashSet<>();

    static {
        vowels.add("a");
        vowels.add("e");
        vowels.add("i");
        vowels.add("o");
        vowels.add("u");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        L = Integer.parseInt(tokenizer.nextToken());

        chars = input.readLine().replace(" ", "").toCharArray();
        Arrays.sort(chars);

        findPassword(new String[L], 0, 0);
        System.out.print(answer);
    }

    private static void findPassword(String[] password, int depth, int idx) {
        if (depth == L) {
            if (isPossible(password)) {
                answer.append(String.join("", password)).append("\n");
            }

            return;
        }

        for (int i = idx; i < chars.length; i++){
            password[depth] = Character.toString(chars[i]);
            findPassword(password, depth + 1, i + 1);
            password[depth] = null;
        }
    }

    private static boolean isPossible(String[] password) {
        int cntVowels = 0;
        int cntConsonants = 0;

        for (String str : password) {
            if (vowels.contains(str)) {
                cntVowels++;
                continue;
            }

            cntConsonants++;
        }

        return (cntVowels >= 1) && (cntConsonants >= 2);
    }
}
