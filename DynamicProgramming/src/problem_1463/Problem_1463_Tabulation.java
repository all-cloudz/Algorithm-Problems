package problem_1463;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_1463_Tabulation {

    private static int X;
    private static int[] tabulated;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        X = Integer.parseInt(input.readLine());
        tabulated = new int[X + 1];

        fillTable();
        System.out.println(tabulated[X]);
    }

    private static void fillTable() {
        for (int i = 2; i <= X; i++) {
            tabulated[i] = tabulated[i - 1] + 1;

            if (i % 2 == 0) {
                tabulated[i] = Math.min(tabulated[i], tabulated[i / 2] + 1);
            }

            if (i % 3 == 0) {
                tabulated[i] = Math.min(tabulated[i], tabulated[i / 3] + 1);
            }
        }
    }

}
