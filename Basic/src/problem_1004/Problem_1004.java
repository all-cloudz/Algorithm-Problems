package problem_1004;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Problem_1004 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(input.readLine());

        for (int i = 0; i < T; i++) {
            int[] department = new int[2];
            int[] destination = new int[2];

            StringTokenizer token = new StringTokenizer(input.readLine());
            department[0] = Integer.parseInt(token.nextToken());
            department[1] = Integer.parseInt(token.nextToken());
            destination[0] = Integer.parseInt(token.nextToken());
            destination[1] = Integer.parseInt(token.nextToken());

            int planetNum = Integer.parseInt(input.readLine());
            int[][] planet = new int[planetNum][3];

            for (int j = 0; j < planetNum; j++) {
                token = new StringTokenizer(input.readLine());
                planet[j][0] = Integer.parseInt(token.nextToken());
                planet[j][1] = Integer.parseInt(token.nextToken());
                planet[j][2] = Integer.parseInt(token.nextToken());
            }

            int route = 0;

            for (int k = 0; k < planetNum; k++) {
                if (isInner(department, planet[k]) != isInner(destination, planet[k])) {
                    route++;
                }
            }

            output.write(String.valueOf(route));
            output.newLine();
        }

        output.flush();
        output.close();
    }

    private static boolean isInner(int[] point, int[] circle) {
        return (Math.pow(point[0] - circle[0], 2) + Math.pow(point[1] - circle[1], 2)) < Math.pow(circle[2], 2);
    }
}