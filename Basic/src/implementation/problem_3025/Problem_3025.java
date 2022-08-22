package implementation.problem_3025;

import java.io.*;
import java.util.*;

public class Problem_3025 {
    private static int R, C;
    private static char[][] map;
    private static Map<Integer, LinkedList<int[]>> routes;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer st = new StringTokenizer(input.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        routes = new HashMap<>();

        for (int i = 0; i < R; i++) {
            map[i] = input.readLine().toCharArray();
        }

        for (int i = 0; i < C; i++) {
            routes.put(i, new LinkedList<>());
        }

        int N = Integer.parseInt(input.readLine());
        while (N-- > 0) {
            int dropIdx = Integer.parseInt(input.readLine()) - 1;
            drop(dropIdx);
        }

        for (int i = 0; i < R; i++) {
            answer.append(String.valueOf(map[i])).append('\n');
        }

        System.out.println(answer);
    }

    private static void drop(int dropIdx) {
        if (routes.get(dropIdx).isEmpty()) {
            dropTopDown(0, dropIdx, dropIdx);
            return;
        }

        dropBottomUp(dropIdx);
    }

    private static void dropTopDown(int row, int col, int dropIdx) {
        while (row + 1 < R && map[row + 1][col] != 'X') {
            routes.get(dropIdx).addLast(new int[] {row, col});

            if (map[row + 1][col] != 'O') {
                row++;
                continue;
            }

            if (col - 1 >= 0 && map[row][col - 1] == '.' && map[row + 1][col - 1] == '.') {
                col--;
                row++;
                continue;
            }

            if (col + 1 < C && map[row][col + 1] == '.' && map[row + 1][col + 1] == '.') {
                col++;
                row++;
                continue;
            }

            break;
        }

        map[row][col] = 'O';
    }

    private static void dropBottomUp(int dropIdx) {
        LinkedList<int[]> route = routes.get(dropIdx);
        int[] point = route.removeLast();

        while (map[point[0]][point[1]] != '.') {
            point = route.removeLast();
        }

        dropTopDown(point[0], point[1], dropIdx);
    }
}
