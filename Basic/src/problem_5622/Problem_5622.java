package problem_5622;

import java.util.Scanner;

public class Problem_5622 {
    private static int answer = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String number = input.nextLine();

        for (int i = 0; i < number.length(); i++) {
            char cur = number.charAt(i);

            if (cur >= 'S') {
                cur--;
            }

            if (cur + 1 == 'Z') {
                cur--;
            }

            answer += (cur - 'A') / 3 + 3;
        }

        System.out.print(answer);
    }
}
