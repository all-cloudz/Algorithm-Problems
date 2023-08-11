package backtracking.problem_d4_4012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_D4_4012_Sol2 {

    private static int N;
    private static int[][] synergy;
    private static int minDiff;

    public static void main(final String[] args) throws IOException {
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init(input);
            calculateMinDiff(0, 0, 0);
            answer.append(String.format("#%d %d%n", tc, minDiff));
        }

        System.out.println(answer);
    }

    private static void init(final BufferedReader input) throws IOException {
        N = Integer.parseInt(input.readLine());

        synergy = new int[N][N];
        for (int i = 0; i < N; i++) {
            synergy[i] = Arrays.stream(input.readLine().split(" "))
                               .mapToInt(Integer::parseInt)
                               .toArray();
        }

        minDiff = Integer.MAX_VALUE;
    }

    private static void calculateMinDiff(final int count, final int left, final int isSelected) {
        if (count == (N >> 1)) {
            final Food oneFood = new Food(isSelected);
            final Food anotherFood = new Food(reverseBits(isSelected));
            minDiff = Math.min(minDiff, oneFood.calculateDiffFlavor(anotherFood, synergy));
        }

        for (int i = left; i < N; i++) {
            calculateMinDiff(count + 1, i + 1, isSelected | (1 << i));
        }
    }

    private static int reverseBits(final int isSelected) {
        final int fullBits = (1 << N) - 1;
        return fullBits ^ isSelected;
    }

    private static class Food {
        private final List<Integer> ingredients;

        public Food(final int isSelected) {
            this.ingredients = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                if ((isSelected >> i & 1) == 1) {
                    addIngredient(i);
                }
            }
        }

        public void addIngredient(final int ingredient) {
            ingredients.add(ingredient);
        }

        public int calculateDiffFlavor(final Food other, final int[][] synergy) {
            return Math.abs(this.calculateFlavor(synergy) - other.calculateFlavor(synergy));
        }

        public int calculateFlavor(final int[][] synergy) {
            int sum = 0;

            for (int size = ingredients.size(), i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    final int ingredientA = ingredients.get(i);
                    final int ingredientB = ingredients.get(j);
                    sum += synergy[ingredientA][ingredientB] + synergy[ingredientB][ingredientA];
                }
            }

            return sum;
        }
    }
}
