package exactTarget;

public class BinarySearch_While {
    public static void main(String[] args) {
        /* 문제에 주어진 값을 입력 받고,
         * 입력받을 값을 인자로 이진탐색 메서드를 호출하여
         * 반환된 값을 출력하는 부분 */
    }

    private static int BinarySearch_ExactTarget_Ver1(int[] sortedNums, int target) {
        // [ left, right를 선언 및 초기화하고 mid를 선언 ]
        /* 1. left는 탐색하는 대상 중 최솟값으로 초기화
         * 2. right는 탐색하는 대상 중 최댓값으로 초기화
         * ※ 이 코드에서는 탐색하는 대상을 sortedNums의 index로 가정 */
        int left = 0;
        int right = sortedNums.length - 1;
        int mid;

        // [ 실제로 탐색을 진행하는 부분 ]
        // left == right가 되면 loop를 빠져나가고, target의 index를 반환한다.
        while (left < right) {
            mid = left + (right - left) / 2;

            // 만약 left == right가 되기 전에 index가 mid인 요소가 target과 같아지면 굳이 탐색을 더 할 필요가 없다.
            if (sortedNums[mid] == target) {
                return mid;
            }

            // [ 구간을 조정하는 부분 ]
            // 만약 index가 mid인 요소가 target보다 크다면 index가 mid 이상인 요소는 볼 필요가 없다.
            if (sortedNums[mid] > target) {
                right = mid - 1;
            // 반대로 index가 mid인 요소가 target보다 작다면 index가 mid 이하인 요소는 볼 필요가 없다.
            } else {
                left = mid + 1;
            }
        }
        // left == right가 되었다면 target을 찾은 것이므로 left 또는 right를 반환하면 된다.
        return right;
    }

    private static int BinarySearch_ExactTarget_Ver2(int[] sortedNums, int target) {
        int left = 0;
        int right = sortedNums.length - 1;
        int mid;

         /* 조금 더 생각해보자.
          * left == right이면 left == right == mid이므로 다음과 같이 수정할 수 있다. */
        while (left <= right) {
            mid = left + (right - left) / 2;

            // 이 부분이 while문의 종료 조건이자 최종적으로 탐색이 종료되는 시점
            if (sortedNums[mid] == target) {
                return mid;
            }

            // [ 구간을 조정하는 부분 ]
            if (sortedNums[mid] > target) {
                right = mid - 1;

            } else {
                left = mid + 1;
            }
        }
        // 의도와 다르게 loop를 빠져나온 경우에 -1을 반환
        return -1;
    }
}
