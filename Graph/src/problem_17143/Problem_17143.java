package problem_17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_17143 {
	
	private static class Point {
		protected int row;
		protected int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static class Shark extends Point {
		private int velocity;
		private int direction;
		private int size;
		private boolean isLive;
		
		public Shark(int row, int col, int velocity, int direction, int size) {
			super(row, col);
			
			if (direction == 1 || direction == 2) {
				this.velocity = velocity % ((R - 1) << 1);
			} else {
				this.velocity = velocity % ((C - 1) << 1);
			}
			
			this.direction = direction - 1;
			this.size = size;
			this.isLive = true;
		}
		
		public void move() {
			if (!isLive) {
				return;
			}
			
			int[] move = DIRECTIONS[direction];
			
			int moveCnt = 0;
			int curRow = this.row;
			int curCol = this.col;
			
			while (moveCnt < velocity) {
				while (moveCnt < velocity && isMovable(curRow + move[0], curCol + move[1])) {
					int[] status = update(curRow, curCol, this.direction, velocity - moveCnt);
					
					curRow = status[0];
					curCol = status[1];
					moveCnt += status[2];
				}
				
				if (moveCnt < velocity) {
					swiftDirection();
				}
				
				while (moveCnt < velocity && isMovable(curRow - move[0], curCol - move[1])) {
					int[] status = update(curRow, curCol, this.direction, velocity - moveCnt);
					
					curRow = status[0];
					curCol = status[1];
					moveCnt += status[2];
				}
				
				if (moveCnt < velocity) {
					swiftDirection();
				}
			}
			
			this.row = curRow;
			this.col = curCol;
		}
		
		private boolean isMovable(int nextRow, int nextCol) {
			return 1 <= nextRow && nextRow <= R && 1 <= nextCol && nextCol <= C;
		}
		
		private static int[] update(int curRow, int curCol, int direction, int limit) {
			if (direction == 0) {
				int delta = Math.min(curRow - 1, limit);
				return new int[] { curRow - delta, curCol, delta };
			}
			
			if (direction == 1) {
				int delta = Math.min(R - curRow, limit);
				return new int[] { delta + curRow, curCol, delta };
			}
			
			if (direction == 2) {
				int delta = Math.min(C - curCol, limit);
				return new int[] { curRow, delta + curCol, delta };
			}
			
			int delta = Math.min(curCol - 1, limit);
			return new int[] { curRow, curCol - delta, delta };
		}
		
		private void swiftDirection() {
			if (this.direction % 2 == 0) {
				this.direction++;
				return;
			}
			
			this.direction--;
		}
	}
	
	private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	
	private static int R, C;
	private static Shark[][] map;
	
	private static int M;
	private static Shark[] sharks;
	private static int sumSize;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		map = new Shark[R + 1][C + 1];
		sharks = new Shark[M];
		
		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(input.readLine());
			int row = Integer.parseInt(tokenizer.nextToken());
			int col = Integer.parseInt(tokenizer.nextToken());
			int velocity = Integer.parseInt(tokenizer.nextToken());
			int direction = Integer.parseInt(tokenizer.nextToken());
			int size = Integer.parseInt(tokenizer.nextToken());
			
			sharks[i] = new Shark(row, col, velocity, direction, size);
			map[row][col] = sharks[i];
		}
		
		hunt(1);
		System.out.println(sumSize);
	}
	
	private static void hunt(int pos) {
		if (pos == C + 1) {
			return;
		}
		
		int row = 1;
		Shark hunted = map[row][pos];
		
		while (row < R && hunted == null) {
			row++;
			hunted = map[row][pos];
		}
		
		if (hunted != null) {
			sumSize += hunted.size;
			hunted.isLive = false;
		}
		
		for (Shark shark : sharks) {
			shark.move();
		}
		
		refreshMap();
		hunt(pos + 1);
	}
	
	private static void refreshMap() {
		map = new Shark[R + 1][C + 1];
		
		for (Shark shark : sharks) {
			if (!shark.isLive) {
				continue;
			}
			
			if (map[shark.row][shark.col] == null) {
				map[shark.row][shark.col] = shark;
				continue;
			}
			
			Shark other = map[shark.row][shark.col];
			
			if (other.size < shark.size) {
				other.isLive = false;
				map[shark.row][shark.col] = shark;
				continue;
			}
			
			shark.isLive = false;
		}
	}
	
}