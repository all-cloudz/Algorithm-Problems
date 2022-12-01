package math.problem_lv2_92335;

import java.math.BigInteger;

public class Problem_Lv2_92335 {

    public int solution(int n, int k) {
        String converted = Integer.toString(n, k);
        return countPrimeNumber(converted);
    }

    private int countPrimeNumber(String number) {
        int count = 0;

        number = number.replaceAll("0+", "/");
        String[] targets = number.split("/");

        for (String cur : targets) {
            BigInteger target = new BigInteger(cur);

            if (target.isProbablePrime(10)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Problem_Lv2_92335().solution(1, 10));
    }

}
