package problem_tree1_11;

import java.io.*;
import java.util.*;

public class Problem_Tree1_11_Sol1 {

    private static class IdxCharacter implements Comparable<IdxCharacter> {
        private Character character;
        private int idx;
        private int cnt;

        public IdxCharacter(Character character, int idx) {
            this.character = character;
            this.idx = idx;
        }

        public IdxCharacter(IdxCharacter prev, int cnt) {
            this.character = prev.character;
            this.idx = prev.idx;
            this.cnt = cnt;
        }

        @Override
        public int hashCode() {
            return character.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof IdxCharacter)) {
                return false;
            }

            IdxCharacter that = (IdxCharacter) o;
            return this.character.equals(that.character);
        }

        @Override
        public int compareTo(IdxCharacter o) {
            return (this.cnt == o.cnt) ? this.idx - o.idx : this.cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        Map<IdxCharacter, Integer> cntOfChar = new HashMap<>();
        String str = input.readLine();
        for (int len = str.length(), i = 0; i < len; i++) {
            IdxCharacter cur = new IdxCharacter(str.charAt(i), i);
            cntOfChar.put(cur, cntOfChar.getOrDefault(cur, 0) + 1);
        }

        PriorityQueue<IdxCharacter> keys = new PriorityQueue<>();
        for (IdxCharacter key : cntOfChar.keySet()) {
            keys.offer(new IdxCharacter(key, cntOfChar.get(key)));
        }

        IdxCharacter key = keys.poll();
        if (key.cnt != 1) {
            System.out.println("None");
            return;
        }

        System.out.println(key.character);
    }

}
