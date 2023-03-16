
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileInputStream;

public class 핀볼게임 {
	public static void main(String args[]) throws Exception
	{
	
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		Pinball pinball = new Pinball();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			System.out.print("#" + Integer.toString(test_case) + " ");
			System.out.println(pinball.solution(sc));
		}
	}	
}

class Pinball {
	int[][] pinballMap;
	int mapSize;
	// 아래, 위, 왼쪽, 오른쪽 방향
	int[][] directions = {{1, 0}, {-1, 0}, {0, -1} , {0, 1}}; 
	// 각 블록에대한 방향변화
	int[][] blocks = {
			{3,0,1,2}, // 1번 블록에 대한 방향변화
			{1,3,0,2}, // 2번 블록
			{1,2,3,0}, // 3번 블록
			{2,0,3,1}, // 4번 블록
			{1,0,3,2}// 5번 블록
		};
	// 2차원 인덱스를 1차원 인덱스로 변경한 값과 다음 위치를 사상
	Map<Integer, NextHole> wormholes = new HashMap<>();
	// 다음 장소에 대한 정보를 담는 배열
	NextHole[] wormhole = new NextHole[11];
	public int solution(Scanner sc) {
		int answer = 0;
		initMap(sc);
		for(int i = 0 ; i < mapSize ; i++ ) {
			for(int j = 0 ; j < mapSize; j++) {
				NextHole start = new NextHole(i,j);
				NextHole current = new NextHole(i,j);
				if(pinballMap[current.row][current.col] != 0) continue;
				for(int k = 0 ; k < 4 ; k++) {
					int dir = k;
					int wallCount = 0; 
					do {
						current.row = current.row + directions[dir][0];
						current.col = current.col + directions[dir][1];
						// 벽에 부딪힌 경우 
						if(current.row < 0 || current.row >= mapSize || current.col < 0 || current.col >= mapSize) {
							dir = reverseDirection(dir);
							wallCount++;
						}
						// 장애물과 만난 경우 
						else if(pinballMap[current.row][current.col] > 0 && pinballMap[current.row][current.col] <= 5 )
						{
							int blockType = pinballMap[current.row][current.col] - 1;
							dir = blocks[blockType][dir];
							wallCount++;
						} 
						// 웜홀을 만난 경우  
						else if(pinballMap[current.row][current.col] >= 6 && pinballMap[current.row][current.col] <= 10) {
							NextHole nextWormHole = wormholes.get(current.row * mapSize + current.col);
							current.row = nextWormHole.row;
							current.col = nextWormHole.col;
						}
						else if(pinballMap[current.row][current.col] == -1) {
							break;
						}
					} while(!current.equals(start));
					answer = Math.max(answer, wallCount);
				}
			}
		}
		return answer;
	}
	// 반대방향으로 전환 0 <-> 1 , 2 <-> 3
	private int reverseDirection(int dir) {
		if(dir >= 2) {
			return dir == 3 ? 2 : 3;
		}
		return dir == 0 ? 1 : 0;
	}
	private void initMap(Scanner sc) {
		mapSize = sc.nextInt();
		for( int i = 0 ; i < wormhole.length ; i++) {
			wormhole[i] = new NextHole(-1,-1);
		}
		pinballMap = new int[mapSize][mapSize];
		for(int i = 0 ; i < mapSize; i++) {
			for(int j = 0 ; j < mapSize; j++) {
				int spaceState = sc.nextInt();
				pinballMap[i][j] = spaceState;
				if(spaceState >= 6 ) {	
					linkWormhole(i, j, spaceState);
				}
			}
		}
	}
	private void linkWormhole(int i, int j, int spaceState) {
		// 해당 웜홀이 한번도 방문되지 않았다면
		if(wormhole[spaceState].row == -1) {
			wormhole[spaceState].row = i;
			wormhole[spaceState].col = j;
		} else {
			// 이전에 방문한 웜홀과 현재 웜홀 연결
			NextHole next = wormhole[spaceState];
			wormholes.put(i * mapSize + j, next);
			wormholes.put(next.row * mapSize + next.col , new NextHole(i,j));
		}
	}
	class NextHole {
		int row, col;
		NextHole(int row, int col) { 
			this.row = row;
			this.col = col;
		}
		public boolean equals(NextHole hole) {
			if(hole.row == row && hole.col == col)
				return true;
			return false;
		}
	}
}

