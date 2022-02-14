package concept.lowerBound;

public class BinarySearch_While {
    public static void main(String[] args) {
        /* 문제에 주어진 값을 입력 받고
         * 입력받을 값을 인자로 이진탐색 메서드를 호출하여
         * 반환된 값을 출력하는 부분 */
    }

    /* Lower Bound(LWB)는 Upper Bound(UPB)에 비해 상대적으로 쉽다.
     * 조건을 만족시키는 값 중에서 최솟값 또는 그때의 index를 반환하기 위해 구간을 조정할 때,
     * 어떤 논리로 구간을 조정하는지 주목하자. */
    private static int BinarySearch_LowerBound(int[] sortedNums, int target) {
        // [ left, right를 선언 및 초기화하고 mid를 선언 ]
        /* 1. left는 탐색하는 대상 중 최솟값으로 초기화
         * 2. right는 탐색하는 대상 중 최댓값으로 초기화
         * ※ 이 코드에서는 탐색하는 대상을 sortedNums의 index로 가정 */
        int left = 0;
        int right = sortedNums.length - 1;
        int mid;

        // [ 실제로 탐색을 진행하는 부분 ]
        // left == right가 되면 loop를 빠져나가고 최솟값 또는 그때의 index를 반환한다.
        while (left < right) {
            mid = left + (right - left) / 2;

            // [ 구간을 조정하는 부분 ]
            // mid가 LWB일 수 있으므로 right에 mid - 1을 대입하면 원하는 값을 찾을 수 없게 되기 때문에 이를 주의해야 한다.
            if (isLowerBound(sortedNums, mid, target)) {
                right = mid;
            // mid가 LWB가 아니라면 left에 mid + 1을 대입해도 아무 문제가 없다.
            } else {
                left = mid + 1;
            }
        }
        // left == right가 되어 loop를 빠져나오면 right == LWB 또는 LWB의 index이다.
        return right;
    }

    /* 여기서 조금 더 깊이있게 생각해보면 left가 right보다 먼저 LWB에 도달해도 문제가 없는 이유는
     * mid를 계산할 때 정수형의 계산에서 소수 부분을 버림하기 때문이다.
     * 따라서 left가 LWB이면 항상 isLowerBound(mid)가 참이므로 결국 left == right가 될 것임을 알 수 있다. */


    // [ 조건 구현 부분 ]
    /* 이 코드에서는 target보다 크거나 같은 값 중에서 index가 최소인 경우를 구해보자.
     * 이때, target보다 크거나 같으면 참을, 그렇지 않으면 거짓을 반환해야 한다.
     * 이 부분이 문제의 이해도를 결정짓는 부분이다. */
    private static boolean isLowerBound(int[] sortedNums, int mid, int target) {
        if (sortedNums[mid] >= target) {
            return true;
        }
        return false;
    }
}