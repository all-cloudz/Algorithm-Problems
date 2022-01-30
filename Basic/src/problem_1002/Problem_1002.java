package problem_1002;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Problem_1002 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(input.readLine());

        int[][] circles = new int[T][6];


        for (int i = 0; i < T; i++) {
            StringTokenizer strToken = new StringTokenizer(input.readLine());
            for (int j = 0; j < 6; j++) {
                circles[i][j] = Integer.parseInt(strToken.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            int distanceSquare = (circles[i][0] - circles[i][3]) * (circles[i][0] - circles[i][3]) + (circles[i][1] - circles[i][4]) * (circles[i][1] - circles[i][4]);
            int outRadiusSquare = (circles[i][2] + circles[i][5]) * (circles[i][2] + circles[i][5]);
            int inRadiusSquare = (circles[i][2] - circles[i][5]) * (circles[i][2] - circles[i][5]);

            if (distanceSquare == 0 && inRadiusSquare == 0) {
                output.write("-1\n");
            } else if ((distanceSquare > outRadiusSquare) || (distanceSquare < inRadiusSquare)) {
                output.write("0\n");
            } else if ((distanceSquare == outRadiusSquare) || (distanceSquare == inRadiusSquare)) {
                output.write("1\n");
            } else {
                output.write("2\n");
            }
        }

        output.flush();

        input.close();
        output.close();
    }
}