package problem_19940;

import java.io.*;
import java.util.*;

public class Problem_19940_BFS {
    private static class Button {
        private int time;
        private int cntADDH;
        private int cntADDT;
        private int cntMINT;
        private int cntADDO;
        private int cntMINO;

        public Button(int time, int cntADDH, int cntADDT, int cntMINT, int cntADDO, int cntMINO) {
            this.time = time;
            this.cntADDH = cntADDH;
            this.cntADDT = cntADDT;
            this.cntMINT = cntMINT;
            this.cntADDO = cntADDO;
            this.cntMINO = cntMINO;
        }
    }

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int T = Integer.parseInt(input.readLine());

        for (int i = 0; i < T; i++) {
            int time = Integer.parseInt(input.readLine());
            pressButton(time);
        }

        System.out.println(answer);
    }

    private static void pressButton(int time) {
        HashMap<Integer, Button> visited = new HashMap<>();
        Queue<Button> buttonQueue = new LinkedList<>();

        Button start = new Button(0, 0, 0, 0, 0, 0);
        visited.put(start.time, start);
        buttonQueue.add(start);

        while (!buttonQueue.isEmpty()) {
            Button cur = buttonQueue.poll();

            if (!visited.containsKey(cur.time - 1) && 0 <= cur.time - 1 && cur.time - 1 <= 60) {
                Button next = new Button(cur.time - 1, cur.cntADDH, cur.cntADDT, cur.cntMINT, cur.cntADDO, cur.cntMINO + 1);
                visited.put(next.time, next);
                buttonQueue.add(next);
            }

            if (!visited.containsKey(cur.time + 1) && 0 <= cur.time + 1 && cur.time + 1 <= 60) {
                Button next = new Button(cur.time + 1, cur.cntADDH, cur.cntADDT, cur.cntMINT, cur.cntADDO + 1, cur.cntMINO);
                visited.put(next.time, next);
                buttonQueue.add(next);
            }

            if (!visited.containsKey(cur.time - 10) && 0 <= cur.time - 10 && cur.time - 10 <= 60) {
                Button next = new Button(cur.time - 10, cur.cntADDH, cur.cntADDT, cur.cntMINT + 1, cur.cntADDO, cur.cntMINO);
                visited.put(next.time, next);
                buttonQueue.add(next);
            }

            if (!visited.containsKey(cur.time + 10) && 0 <= cur.time + 10 && cur.time + 10 <= 60) {
                Button next = new Button(cur.time + 10, cur.cntADDH, cur.cntADDT + 1, cur.cntMINT, cur.cntADDO, cur.cntMINO);
                visited.put(next.time, next);
                buttonQueue.add(next);
            }

            if (!visited.containsKey(cur.time + 60) && 0 <= cur.time + 60 && cur.time + 60 <= 60) {
                Button next = new Button(cur.time + 60, cur.cntADDH + 1, cur.cntADDT, cur.cntMINT, cur.cntADDO, cur.cntMINO);
                visited.put(next.time, next);
                buttonQueue.add(next);
            }
        }

        answer.append(time / 60 + visited.get(time % 60).cntADDH).append(' ')
                .append(visited.get(time % 60).cntADDT).append(' ')
                .append(visited.get(time % 60).cntMINT).append(' ')
                .append(visited.get(time % 60).cntADDO).append(' ')
                .append(visited.get(time % 60).cntMINO).append('\n');
    }
}