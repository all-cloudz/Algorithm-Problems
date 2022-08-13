package problem_d4_14695;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class Problem_D4_14695_Sol2 {
    private static class Vector {
        private long x;
        private long y;
        private long z;

        public Vector(long x, long y, long z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public static Vector outerProduct(Vector v1, Vector v2) {
            long newX = v1.y * v2.z - v1.z * v2.y;
            long newY = v1.z * v2.x - v1.x * v2.z;
            long newZ = v1.x * v2.y - v1.y * v2.x;

            return new Vector(newX, newY, newZ);
        }

        public static long innerProduct(Vector v1, Vector v2) {
            return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
        }

        public static boolean isParallel(Vector v1, Vector v2) {
            Vector outerProduct = outerProduct(v1, v2);
            return norm(outerProduct) == 0;
        }

        public static long norm(Vector v) {
            return Math.abs(v.x) + Math.abs(v.y) + Math.abs(v.z);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        loop : for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append(' ');

            final int N = Integer.parseInt(input.readLine());

            if (N <= 3) {
                for (int i = 0; i < N; i++) {
                    input.readLine();
                }

                answer.append("TAK\n");
                continue;
            }

            StringTokenizer st = new StringTokenizer(input.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long z = Long.parseLong(st.nextToken());
            Vector move = new Vector(x, y, z);

            List<Vector> vectors = new ArrayList<>();
            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(input.readLine());
                x = Long.parseLong(st.nextToken());
                y = Long.parseLong(st.nextToken());
                z = Long.parseLong(st.nextToken());

                vectors.add(new Vector(x - move.x, y - move.y, z - move.z));
            }

            Vector v1 = vectors.remove(vectors.size() - 1);
            Vector normal = getNormalVector(vectors, v1);

            if (normal == null) {
                answer.append("TAK\n");
                continue;
            }

            for (Vector cur : vectors) {
                if (Vector.innerProduct(normal, cur) != 0) {
                    answer.append("NIE\n");
                    continue loop;
                }
            }

            answer.append("TAK\n");
        }

        System.out.println(answer);
    }

    private static Vector getNormalVector(List<Vector> vectors, Vector v) {
        for (int i = vectors.size() - 1; i >= 0; i--) {
            Vector cur = vectors.get(i);

            if (Vector.isParallel(v, cur)) {
                vectors.remove(i);
                continue;
            }

            return Vector.outerProduct(v, cur);
        }

        return null;
    }
}
