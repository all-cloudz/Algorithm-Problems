package problem_2110;

import java.util.Scanner;
import java.util.Arrays;

public class Problem_2110 {
    private static int N;
    private static int C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        C = sc.nextInt();

        int[] houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = sc.nextInt();
        }

        Arrays.sort(houses);

        System.out.println(binarySearch(houses));
    }

    private static int binarySearch(int[] houses) {
        int minMatric = Math.min(houses[1] - houses[0], houses[N - 1] - houses[N - 2]);
        int maxMatric = houses[N - 1] - houses[0];
        int midMatric = 0;

        while (minMatric <= maxMatric) {
            midMatric = minMatric + (maxMatric - minMatric) / 2;

            if (maxMatric == minMatric) {
                return minMatric;
            }

            // 조건을 만족시키는 값 중에서 최댓값을 찾을 때 사용하는 종료 조건
            if (maxMatric - minMatric == 1) {
                if (isMatric(houses, maxMatric)) {
                    return maxMatric;
                }
                return minMatric;
            }

            if (isMatric(houses, midMatric)) {
                minMatric = midMatric;
            } else {
                maxMatric = midMatric - 1;
            }
        }

        return -1;
    }

    // 이 문제를 풀면서 깨달은 부분은 적어도 최대, 최소의 상황을 구할 때는 이진탐색을 boolean으로 구현하는 게 쉽다는 것!!
    private static boolean isMatric(int[] houses, int Matric) {
        int setWifi = 0;
        int cnt = 1;

        for (int i = 1; i < N; i++) {
            if (houses[i] >= houses[setWifi] + Matric) {
                cnt++;
                setWifi = i;
            }
        }

        if (cnt >= C) {
            return true;
        }

        return false;
    }
}
