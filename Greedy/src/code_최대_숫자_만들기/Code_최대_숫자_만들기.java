package code_최대_숫자_만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Code_최대_숫자_만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int n = Integer.parseInt(input.readLine());
        List<String> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(input.readLine());
        }

        nums.stream()
            .sorted((a, b) -> {
                String ab = a + b;
                String ba = b + a;

                if (ab.equals(ba)) {
                    return 0;
                }

                return ba.compareTo(ab);
            })
            .forEach(answer::append);

        System.out.println(answer);
    }

}
