import java.util.Arrays;

public class 사라지는발판 {
    boolean[][] visit;
	int[][] board;
	int[][] directions = {{-1, 0} , {1,0}, {0, -1}, {0, 1}};
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        this.board = board;
        init();
        return barkSolve(aloc,bloc);
    }
	public int barkSolve(int[] my, int[] opposite) {
		if(board[my[0]][my[1]] == 0 || visit[my[0]][my[1]]) {
			return 0;
		}
		int ans = 0;
		for(int i = 0 ; i < 4 ; i++) {
			int[] newDir = new int[2];
			newDir[0] = my[0] + directions[i][0];
			newDir[1] = my[1] + directions[i][1];
			if(newDir[0] < 0 || newDir[0] >= board.length || newDir[1] < 0 || newDir[1] >= board[0].length)
				continue;
			if(visit[newDir[0]][newDir[1]] || board[newDir[0]][newDir[1]] == 0) 
				continue;
			visit[my[0]][my[1]] = true;
			int tempAns = barkSolve(opposite, newDir) + 1;
			visit[my[0]][my[1]] = false;
			if( ans % 2 == 1 ) {
				if(tempAns % 2 == 1) {
					ans = Math.min(ans, tempAns);
				}
				
			} else {
				if(tempAns % 2 == 1) {
					ans = tempAns;
				}else {
					ans = Math.max(ans, tempAns);
				}
			}
		}
		return ans;
	}
	
	
	private void init() {
		visit = new boolean[board.length][board[0].length];
		for(int i = 0 ; i < board.length; i++) {
			for(int j = 0 ; j < board[0].length; j++) { 
				visit[i][j] = false;
			}
		}
	}
}
