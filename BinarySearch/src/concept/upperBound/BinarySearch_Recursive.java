package concept.upperBound;

public class BinarySearch_Recursive {
    public static void main(String[] args) {
        /* 문제에 주어진 값을 입력 받고
         * 입력받을 값을 인자로 이진탐색 메서드를 호출하여
         * 반환된 값을 출력하는 부분 */
    }

    // while문으로 Upper Bound 구현을 먼저 학습했다는 가정하에 차이점만 보자. 특히 Ver2만 재귀로 보자.
    private static int BinarySearch_UpperBound(int[] sortedNums, int left, int right, int target) {
        // [ mid 선언 및 초기화 ]
        // left와 right는 while문과는 달리 매개변수(인자)로 받아야 한다.
        int mid = left + (right - left) / 2;

        // [ 재귀의 종료 조건 ]
        // 종료 조건 : 성립 조건
        if (left <= right) {
            return right - 1;
        }

        // [ 재귀 호출 및 구간 조정 ]
        if (isUpperBound(sortedNums, mid, target)) {
            return BinarySearch_UpperBound(sortedNums, left, mid + 1, target);
        } else {
            return BinarySearch_UpperBound(sortedNums, mid, right, target);
        }
    }

    // [ 조건 구현 부분 ]
    private static boolean isUpperBound(int[] sortedNums, int mid, int target) {
        if (sortedNums[mid] <= target) {
            return true;
        }
        return false;
    }
}
