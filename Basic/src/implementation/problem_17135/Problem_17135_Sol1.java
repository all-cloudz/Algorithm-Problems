package implementation.problem_17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem_17135_Sol1 {
	private static class Unit {
		private int row;
		private int col;
		private boolean isSurvive;

		public Unit(int row, int col) {
			this.row = row;
			this.col = col;
			this.isSurvive = true;
		}

		public static int distance(Unit p1, Unit p2) {
			return Math.abs(p1.row - p2.row) + Math.abs(p1.col - p2.col);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}

			if (o == null || !(o instanceof Unit)) {
				return false;
			}

			Unit that = (Unit) o;

			return this.row == that.row && this.col == that.col;
		}

		@Override
		public int hashCode() {
			int hash = 31;
			hash = hash * 17 + row;
			hash = hash * 17 + col;
			return hash;
		}
	}

	private static int N, M;
	private static Set<Unit> enemies;
	private static int[] located;
	private static int C;
	private static int answer;

	static {
		enemies = new HashSet<>();
		located = new int[3];
		answer = Integer.MIN_VALUE;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());

			for (int j = 0; j < M; j++) {
				if (st.nextToken().equals("1")) {
					enemies.add(new Unit(i, j));
				}
			}
		}

		collocate(0, 0);
		System.out.println(answer);
	}

	private static void collocate(int start, int cnt) {
		if (cnt == 3) {
			defence();
			return;
		}

		for (int i = start; i < M; i++) {
			located[cnt] = i;
			collocate(i + 1, cnt + 1);
		}
	}

	private static void defence() {
		int totalKillCnt = 0;
		int removedCnt = 0;

		Unit[] archers = new Unit[] { new Unit(N, located[0]), new Unit(N, located[1]), new Unit(N, located[2]) };

		while (removedCnt != enemies.size()) {
			// 사격 개시
			int killCnt = fire(archers);
			totalKillCnt += killCnt;
			removedCnt += killCnt;

			// 적 이동
			removedCnt += moveEnemies(archers);
		}

		answer = Math.max(answer, totalKillCnt);

		if (answer == enemies.size()) {
			System.out.println(answer);
			System.exit(0);
		}

		for (Unit cur : enemies) {
			cur.isSurvive = true;
		}
	}

	private static int fire(Unit[] archers) {
		int cnt = 0;

		Unit[] attackedEnemies = new Unit[] { getAttackedEnemy(archers[0]), getAttackedEnemy(archers[1]), getAttackedEnemy(archers[2]) };

		for (Unit cur : attackedEnemies) {
			if (cur == null || !cur.isSurvive) {
				continue;
			}
			
			if (enemies.contains(cur)) {
				cur.isSurvive = false;
				cnt++;
			}
		}

		return cnt;
	}

	private static Unit getAttackedEnemy(Unit archer) {
		PriorityQueue<Unit> candidates = new PriorityQueue<>((a, b) -> {
			int diff = Unit.distance(archer, a) - Unit.distance(archer, b);

			if (diff == 0) {
				return a.col - b.col;
			}

			return diff;
		});

		for (Unit cur : enemies) {
			if (!cur.isSurvive) {
				continue;
			}
			
			if (Unit.distance(archer, cur) <= C) {
				candidates.offer(cur);
			}
		}

		if (candidates.isEmpty()) {
			return null;
		}

		return candidates.poll();
	}

	private static int moveEnemies(Unit[] archers) {
		int rowOfArcher = archers[0].row--;
		for (Unit cur : archers) {
			cur.row = rowOfArcher;
		}
		
		int cnt = 0;
		for (Unit cur : enemies) {
			if (!cur.isSurvive) {
				continue;
			}
			
			if (cur.row == rowOfArcher) {
				cur.isSurvive = false;
				cnt++;
			}
		}
		
		return cnt;
	}
}
