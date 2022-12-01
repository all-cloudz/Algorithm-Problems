package implementation.problem_lv2_92341;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Problem_Lv2_92341 {

    private static final String FINISH_TIME = "23:59";

    private Map<Integer, String> timeOfCar;
    private Map<Integer, String> stateOfCar;
    private TreeMap<Integer, Integer> totalMinutesOfCar;

    public Problem_Lv2_92341() {
        this.timeOfCar = new HashMap<>();
        this.stateOfCar = new HashMap<>();
        this.totalMinutesOfCar = new TreeMap<>();
    }

    public int[] solution(int[] fees, String[] records) {
        insertRecords(records);

        List<Integer> answer = new ArrayList<>();
        totalMinutesOfCar.forEach((key, value) -> {
            int fee = getFee(fees, value);
            answer.add(fee);
        });

        return answer.stream()
                     .mapToInt(i -> i)
                     .toArray();
    }

    private int getFee(int[] fees, int minutes) {
        int fee = fees[1];

        if (minutes > fees[0]) {
            double excessMinutes = minutes - fees[0];
            fee += Math.ceil(excessMinutes / fees[2]) * fees[3];
        }

        return fee;
    }

    private void insertRecords(String[] records) {
        for (String record : records) {
            String time = record.substring(0, 5);
            int carNum = Integer.parseInt(record.substring(6, 10));
            String state = record.substring(11);

            if (stateOfCar.containsKey(carNum) && stateOfCar.get(carNum).equals("IN")) {
                updateTotalMinutes(carNum, timeOfCar.get(carNum), time);
            }

            timeOfCar.put(carNum, time);
            stateOfCar.put(carNum, state);
        }

        for (int carNum : stateOfCar.keySet()) {
            String state = stateOfCar.get(carNum);

            if ("IN".equals(state)) {
                updateTotalMinutes(carNum, timeOfCar.get(carNum), FINISH_TIME);
            }
        }
    }

    private void updateTotalMinutes(int carNum, String from, String to) {
        int totalMinutes = totalMinutesOfCar.getOrDefault(carNum, 0) + getMinuteDiff(from, to);
        totalMinutesOfCar.put(carNum, totalMinutes);
    }

    private int getMinuteDiff(String from, String to) {
        return getMinutes(to) - getMinutes(from);
    }

    private int getMinutes(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
    }

    public static void main(String[] args) {
        Arrays.stream(new Problem_Lv2_92341().solution(
                new int[] { 180, 5000, 10, 600 },
                new String[] { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN",
                        "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" }
        )).forEach(System.out::println);
    }

}