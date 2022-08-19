package implementation.problem_17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_17135_Sol2 {
	private static class Unit {
		private int row;
		private int col;
		private boolean isSurvive;
		
		public Unit(int row, int col) {
			this.row = row;
			this.col = col;
			this.isSurvive = true;
		}
		
		public static int distance(Unit u1, Unit u2) {
			return Math.abs(u1.row - u2.row) + Math.abs(u1.col - u2.col);
		}
	}
	
	private static int N, M;
	private static List<Unit> enemies;
	private static int[] collocated;
	private static int C;
	private static int answer;
	
	static {
		enemies = new ArrayList<>();
		collocated = new int[3];
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
			int totalKillCnt = 0;
			int removedCnt = 0;
			
			Unit[] archers = new Unit[] { new Unit(N, collocated[0]), new Unit(N, collocated[1]), new Unit(N, collocated[2]) };

			while (removedCnt != enemies.size()) {
				// 사격 개시
				int killCnt = fire(archers);
				totalKillCnt += killCnt;
				removedCnt += killCnt;

				// 적 이동 (궁수 이동)
				removedCnt += moveEnemies(archers);
			}

			answer = Math.max(answer, totalKillCnt);
			
			if (answer >= enemies.size()) {
				System.out.println(answer);
				System.exit(0);
			}
			
			for (Unit cur : enemies) {
				cur.isSurvive = true;
			}
			
			return;
		}

		for (int i = start; i < M; i++) {
			collocated[cnt] = i;
			collocate(i + 1, cnt + 1);
		}
	}
	
	private static int fire(Unit[] archers) {
		Unit[] attackedEnemies = new Unit[] { getAttackedEnemy(archers[0]), getAttackedEnemy(archers[1]), getAttackedEnemy(archers[2]) };
		
		int cnt = 0;
		for (Unit cur : attackedEnemies) {
			if (cur != null && cur.isSurvive) {
				cur.isSurvive = false;
				cnt++;
			}
		}

		return cnt;
	}
	
	private static Unit getAttackedEnemy(Unit archer) {
		int minDist = Integer.MAX_VALUE;
		int minArg = -1;
		
		for (int size = enemies.size(), i = 0; i < size; i++) {
			Unit cur = enemies.get(i);
			
			if (!cur.isSurvive) {
				continue;
			}
			
			int dist = Unit.distance(archer, cur);
			
			if (dist > C) {
				continue;
			}
			
			if (minDist > dist) {
				minDist = dist;
				minArg = i;
				continue;
			}
			
			if (minDist == dist && enemies.get(minArg).col > cur.col) {
				minArg = i;
			}
		}
		
		if (minArg == -1) {
			return null;
		}
		
		return enemies.get(minArg);
	}
	
	private static int moveEnemies(Unit[] archers) {
		int RowOfArcher = archers[0].row--;
		for (Unit cur : archers) {
			cur.row = RowOfArcher;
		}

		int cnt = 0;
		for (Unit cur : enemies) {
			if (!cur.isSurvive) {
				continue;
			}
			
			if (cur.row == RowOfArcher) {
				cur.isSurvive = false;
				cnt++;
			}
		}
		
		return cnt;
	}
}
