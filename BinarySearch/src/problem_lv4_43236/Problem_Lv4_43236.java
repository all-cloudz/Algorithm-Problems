package problem_lv4_43236;

// 구하는 것 : 거리의 최솟값 중 최댓값(UBD)
// 핵심 아이디어 : 현재 바위에서 특정 거리만큼 이동했을 때 다음 바위를 넘어가면 넘어간 바위를 제거한 바위로 생각

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Problem_Lv4_43236 {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);

        ArrayList<Integer> rockList = (ArrayList<Integer>) Arrays.stream(rocks).boxed().collect(Collectors.toList());
        rockList.add(0, 0);
        rockList.add(distance);

        return binarySearch(rockList, n);
    }

    private int binarySearch(ArrayList<Integer> rockList, int n) {
        int left = 0;
        int right = rockList.get(rockList.size() - 1) + 1;

        while (left < right) {
            int mid = left + (right - left >> 1);

            if (isPossible(rockList, n, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right - 1;
    }

    private boolean isPossible(ArrayList<Integer> rockList, int n, int mid) {
        ArrayList<Integer> tmpList = new ArrayList<>(rockList);

        int cnt = 0; // 삭제한 바위의 개수

        for (int i = 1; i < tmpList.size(); i++) {
            if (tmpList.get(i) - tmpList.get(i - 1) < mid) {
                tmpList.remove(i--);
                cnt++;
            }
        }

        return cnt <= n;
    }
}
