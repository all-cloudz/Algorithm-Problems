package problem_lv1_159994;

public class Problem_Lv1_159994 {

    public String solution(String[] cards1, String[] cards2, String[] goal) {
        return selectCards(0, cards1, 0, cards2, 0, goal);
    }

    private String selectCards(int count, String[] cards1, int index1, String[] cards2, int index2, String[] goal) {
        if (count == goal.length) {
            return "Yes";
        }

        String answer = "No";

        if (index1 < cards1.length && goal[count].equals(cards1[index1])) {
            answer = selectCards(count + 1, cards1, index1 + 1, cards2, index2, goal);
        }

        if (index2 < cards2.length && goal[count].equals(cards2[index2])) {
            answer = ("Yes".equals(answer)) ? "Yes" : selectCards(count + 1, cards1, index1, cards2, index2 + 1, goal);
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Problem_Lv1_159994().solution(new String[] { "i", "drink", "water" }, new String[] { "want", "to" },
                                                             new String[] { "i", "want", "to", "drink", "water" }));

        System.out.println(new Problem_Lv1_159994().solution(new String[] { "i", "water", "drink" }, new String[] { "want", "to" },
                                                             new String[] { "i", "want", "to", "drink", "water" }));
    }

}
