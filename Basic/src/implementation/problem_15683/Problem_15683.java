package implementation.problem_15683;

import java.io.*;
import java.util.*;

public class Problem_15683 {
	private static class CCTV {
		private int no;
		private int row;
		private int col;

		public CCTV(int no, int row, int col) {
			this.no = no;
			this.row = row;
			this.col = col;
		}
	}

	private static final int[][] DIRECTIONS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static int N;
	private static int M;
	private static int[][] office;
	private static List<CCTV> cctvList;
	private static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		office = new int[N + 2][M + 2];
		Arrays.fill(office[0], 6);
		Arrays.fill(office[N + 1], 6);

		for (int i = 0; i < N + 2; i++) {
			office[i][0] = 6;
			office[i][M + 1] = 6;
		}

		cctvList = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(input.readLine());

			for (int j = 1; j <= M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());

				if (0 < office[i][j] && office[i][j] < 6) {
					cctvList.add(new CCTV(office[i][j], i, j));
				}
			}
		}

		min = Integer.MAX_VALUE;
		setCCTV(0, 0);
		System.out.println(min);
	}

	private static void setCCTV(int start, int cnt) {
		if (cnt == cctvList.size()) {
			min = Math.min(min, countZero());
			return;
		}

		for (int i = start; i < cctvList.size(); i++) {
			CCTV cur = cctvList.get(i);

			if (cur.no == 5) {
				watch(cur);
				setCCTV(i + 1, cnt + 1);
				continue;
			}

			for (int j = 0; j < 4; j++) {
				watch(cur, j);
				setCCTV(i + 1, cnt + 1);

				unwatch(cur, j);
			}
		}
	}
	
	
	private static int countZero() {
		int cnt = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (office[i][j] == 0) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	private static void watch(CCTV cctv) {
		for (int i = 0; i < 4; i++) {
			watchStraight(cctv, i);
		}
	}

	private static void watch(CCTV cctv, int moveIdx) {
		switch (cctv.no) {
		case 1 :
			watchStraight(cctv, moveIdx);
			return;
		case 2 :
			watchHorizon(cctv, moveIdx);
			return;
		case 3 :
			watchOrthogonal(cctv, moveIdx);
			return;
		case 4 :
			watchTriple(cctv, moveIdx);
		}
	}

	private static void watchStraight(CCTV cctv, int moveIdx) {
		int row = cctv.row;
		int col = cctv.col;

		while (office[row][col] != 6) {
			if (office[row][col] <= 0) {
				office[row][col]--;
			}

			row += DIRECTIONS[moveIdx][0];
			col += DIRECTIONS[moveIdx][1];
		}
	}

	private static void watchHorizon(CCTV cctv, int moveIdx) {
		watchStraight(cctv, moveIdx);
		watchStraight(cctv, (moveIdx + 2) % 4);
	}

	private static void watchOrthogonal(CCTV cctv, int moveIdx) {
		watchStraight(cctv, moveIdx);
		watchStraight(cctv, (moveIdx + 1) % 4);
	}

	private static void watchTriple(CCTV cctv, int moveIdx) {
		watchStraight(cctv, moveIdx);
		watchStraight(cctv, (moveIdx + 1) % 4);
		watchStraight(cctv, (moveIdx + 2) % 4);
	}

	private static void unwatch(CCTV cctv, int moveIdx) {
		switch (cctv.no) {
		case 1 :
			unwatchStraight(cctv, moveIdx);
			return;
		case 2 :
			unwatchHorizon(cctv, moveIdx);
			return;
		case 3 :
			unwatchOrthogonal(cctv, moveIdx);
			return;
		case 4:
			unwatchTriple(cctv, moveIdx);
		}
	}
	
	private static void unwatchStraight(CCTV cctv, int moveIdx) {
		int row = cctv.row;
		int col = cctv.col;
		
		while (office[row][col] != 6) {
			if (office[row][col] < 0) {
				office[row][col]++;
			}
			
			row += DIRECTIONS[moveIdx][0];
			col += DIRECTIONS[moveIdx][1];
		}
	}
	
	private static void unwatchHorizon(CCTV cctv, int moveIdx) {
		unwatchStraight(cctv, moveIdx);
		unwatchStraight(cctv, (moveIdx + 2) % 4);
	}

	private static void unwatchOrthogonal(CCTV cctv, int moveIdx) {
		unwatchStraight(cctv, moveIdx);
		unwatchStraight(cctv, (moveIdx + 1) % 4);
	}

	private static void unwatchTriple(CCTV cctv, int moveIdx) {
		unwatchStraight(cctv, moveIdx);
		unwatchStraight(cctv, (moveIdx + 1) % 4);
		unwatchStraight(cctv, (moveIdx + 2) % 4);
	}
}
