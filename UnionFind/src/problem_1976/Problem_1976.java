package problem_1976;

import java.util.Scanner;

public class Problem_1976 {
    private static int[] parent;
    private static int[] size;
    private static int city;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        city = input.nextInt();
        int travel = input.nextInt();

        input.nextLine(); // 버퍼 비우기

        parent = new int[city];
        size = new int[city];

        for (int i = 0; i < city; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int p = 0; p < city; p++) {
            String[] row = input.nextLine().split(" ");

            for (int q = p; q < city; q++) {
                if (Integer.parseInt(row[q]) == 1) {
                    union(p, q);
                }
            }
        }

        String answer = "NO";
        int depart = input.nextInt() - 1;

        for (int i = 1; i < travel; i++) {
            int arrive = input.nextInt() - 1;

            if (find(depart) == find(arrive)) {
                answer = "YES";
            } else {
                answer = "NO";
                break;
            }

            depart = arrive;
        }

        System.out.print(answer);
    }

    private static void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) {
            return;
        }

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    private static int find(int p) {
        if (p < 0 || p >= city) {
            throw new IllegalArgumentException();
        }

        while (p != parent[p]) {
            p = parent[p];
        }

        return p;
    }
}
