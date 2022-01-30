package upperBound;

public class BinarySearch_While {
    public static void main(String[] args) {
        /* 문제에 주어진 값을 입력 받고
         * 입력받을 값을 인자로 이진탐색 메서드를 호출하여
         * 반환된 값을 출력하는 부분 */
    }


    /* Upper Bound는 조건을 만족시키는 값 중에서 최댓값 또는 그때의 index를 반환하는 것이 목적이다.
     * Ver1에서는 while문의 종료 조건을 이해하는 것에 집중하자. */
    private static int BinarySearch_UpperBound_Ver1(int[] sortedNums, int target) {
        // [ left, right를 선언 및 초기화하고 mid를 선언 ]
        /* 1. left는 탐색하는 대상 중 최솟값으로 초기화
         * 2. right는 탐색하는 대상 중 최댓값으로 초기화
         * ※ 이 코드에서는 탐색하는 대상을 sortedNums의 index로 가정 */
        int left = 0;
        int right = sortedNums.length - 1;
        int mid;

        // [ 실제로 탐색을 진행하는 부분 ]
        // left == right가 되면 loop에서 종료 조건으로 loop를 벗어난다. (사실은 right - left == 1도 고려해야 한다.)
        while (left <= right) {
            mid = left + (right - left) / 2;

            // [ loop의 탈출 조건 ]
            /* Upper Bound(UPB)는 left와 right가 1칸 차이일 때 mid == left가 되므로 무한 루프를 돌게 된다.
             * 따라서 종료 조건으로 right - left == 1을 포함시켜야 한다.
             * 이때, left와 right 중에서 어떤 값이 UPB인지 알 수 없기 때문에 right에서 한 번 더 확인을 해주어야 한다. */
            if (right - left <= 1) {
                if (isUpperBound(sortedNums, right, target)) {
                    return right;
                }
                return left;
            }

            // [ 구간을 조정하는 부분 ]
            // mid가 UPB일 수 있으므로 left에 mid + 1을 대입하면 원하는 값을 찾을 수 없게 되기 때문에 이를 주의해야 한다.
            if (isUpperBound(sortedNums, mid, target)) {
                left = mid;
            // mid가 UPB가 아니라면 right에 mid - 1을 대입해도 아무 문제가 없다.
            } else {
                right = mid - 1;
            }
        }
        // 의도와 다르게 loop를 빠져나온 경우에 -1을 반환
        return -1;
    }

    /* Ver2에서는 Ver1에서 right - left == 1이라는 추가적인 종료 조건을 다는 것을 피하기 위해
     * 어떤 논리로 구간을 조정하는지 주목하자. */
    private static int BinarySearch_UpperBound_Ver2(int[] sortedNums, int target) {
        // [ left, right를 선언 및 초기화하고 mid를 선언 ]
        /* 1. left는 탐색하는 대상 중 최솟값으로 초기화
         * 2. right는 탐색하는 대상 중 최댓값 + 1로 초기화 ; 이유는 뒤에서 설명
         * ※ 이 코드에서는 탐색하는 대상을 sortedNums의 index로 가정 */
        int left = 0;
        int right = sortedNums.length;
        int mid;

        // [ 실제로 탐색을 진행하는 부분 ]
        // left == right가 되면 loop를 빠져나가고 최댓값 또는 그때의 index를 반환한다.
        while (left < right) {
            mid = left + (right - left) / 2;

            // [ 구간을 조정하는 부분 ]
            /* mid == left로 무한 루프를 도는 것을 피하기 위해 left에 mid가 아니라 mid + 1을 대입한다.
             * 이렇게 하면 UPB + 1에 left가 먼저 도달했을 때, 이후의 모든 mid는 조건을 만족시키지 못하므로 left == right가 되고 loop를 빠져나간다.
             * 이때, 탐색하는 대상의 마지막 요소를 mid가 탐색하지 않을 수 있으므로 right를 최댓값 + 1로 초기화 한 것이다. */
            if (isUpperBound(sortedNums, mid, target)) {
                left = mid + 1;
            /* right = mid - 1이면 mid = UPB + 1일 때 right = UPB가 되므로 최종적으로는 UPB를 출력하게 된다.
             * 따라서 이를 피하기 위해 mid를 대입해야 한다. */
            } else {
                right = mid;
            }
        }
        // left == right가 되면 UPB + 1이나 그때의 index가 left 또는 right이므로 right - 1을 반환하면 된다.
        return right - 1;
    }

    // [ 조건 구현 부분 ]
    /* 이 코드에서는 target보다 작거나 같은 값 중에서 index가 최대인 경우를 구해보자.
     * 이때, target보다 작거나 같으면 참을, 그렇지 않으면 거짓을 반환해야 한다.
     * 이 부분이 문제의 이해도를 결정짓는 부분이다. */
    private static boolean isUpperBound(int[] sortedNums, int midOrRight, int target) {
        if (sortedNums[midOrRight] <= target) {
            return true;
        }
        return false;
    }
}
