package problem_lv1_67256;

public class Problem_Lv1_67256 {
    private static final StringBuilder answer = new StringBuilder();

    private static int[] leftPos = {3, 0};
    private static int[] rightPos = {3, 2};

    public static String solution(int[] numbers, String hand) {
        for (int i = 0; i < numbers.length; i++) {
            if (isLeft(numbers[i], hand)) {
                answer.append('L');
            } else {
                answer.append('R');
            }
        }

        return answer.toString();
    }

    public static boolean isLeft(int press, String hand) {
        // 누르려는 버튼 위치 저장
        int[] pressPos = new int[] {(press + 2) / 3 - 1, (press + 2) % 3};

        if (press == 0) {
            pressPos = new int[] {3, 1};
        }

        // 1, 4, 7 이면 왼손, 3, 6, 9이면 오른손
        if (pressPos[1] == 0) {
            leftPos = pressPos;
            return true;
        } else if (pressPos[1] == 2) {
            rightPos = pressPos;
            return false;
        }

        // 2, 5, 8, 0 이면 엄지 위치에 따른 거리 계산
        int leftDistance = Math.abs(leftPos[0] - pressPos[0]) + Math.abs(leftPos[1] - pressPos[1]);
        int rightDistance = Math.abs(rightPos[0] - pressPos[0]) + Math.abs(rightPos[1] - pressPos[1]);

        // 거리 대소 관계에 따라 손 선택
        if (leftDistance < rightDistance) {
            leftPos = pressPos;
            return true;
        } else if (leftDistance > rightDistance) {
            rightPos = pressPos;
            return false;
        }

        if (hand.equals("left")) {
            leftPos = pressPos;
            return true;
        }

        rightPos = pressPos;
        return false;
    }

    public static void main(String[] args) {
        System.out.print(solution(new int[] {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right"));
    }
}
