import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
public class 아이템줍기 {
	final int MAPSIZE = 102;
	int[][] board = new int[MAPSIZE][MAPSIZE];
	int[][] visit = new int[MAPSIZE][MAPSIZE];
	int[][] directions = {{-1, 0}, {1,0} , {0,-1}, {0,1}};
	Queue<Point> q = new LinkedList<>();
	
	int itemX, itemY;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        this.itemX = itemX * 2;
        this.itemY = itemY * 2;
        for(int[] rec : rectangle) {
        	addLocation(new int[] {rec[0] * 2, rec[1] * 2,rec[2] * 2, rec[3] * 2});
        }
        for(int[] rec : rectangle) {
        	removeLocation(new int[] {rec[0] * 2, rec[1] * 2,rec[2] * 2, rec[3] * 2});
        }
        q.add(new Point(characterY * 2, characterX * 2, 0));
        return bfs() / 2;
    }
    private int bfs() {
    	while(!q.isEmpty()) {
    		Point p = q.poll();
    		if(p.row == itemY && p.col == itemX) return p.point;
			
    		for(int i = 0 ; i < 4; i++) {
    			int row = p.row + directions[i][0];
    			int col = p.col + directions[i][1];
    			if(row < 0 || row >= MAPSIZE || col < 0 || col >= MAPSIZE) continue;
    			visit[p.row][p.col] = 1;
				
    			if(visit[row][col] == 1) continue;
    			if(board[row][col] == 1) {
    				q.add(new Point(row,col, p.point + 1));
    			}
    		}
    	
    	}
    	return 0;
    }
    
    private void addLocation(int[] rec) {
    	int startRow = rec[1];
    	int startCol = rec[0];
    	int endRow = rec[3];
    	int endCol = rec[2];
    	for(int currentRow = startRow ; currentRow <= endRow ; currentRow++) {
    		board[currentRow][startCol] = 1;
    		board[currentRow][endCol] = 1;
    		
    	}
    	for(int currentCol = startCol ; currentCol <= endCol; currentCol++) {
			board[startRow][currentCol] = 1;
			board[endRow][currentCol] = 1;
			
    	}
    }
    // 내부 점들 제거
    private void removeLocation(int[] rec) {
    	int startRow = rec[1];
    	int startCol = rec[0];
    	int endRow = rec[3];
    	int endCol = rec[2];
    	for(int i = startRow + 1 ; i < endRow ; i++) {
    		for(int j = startCol + 1 ; j < endCol ; j++) {
    			board[i][j] = 0;
    		}
    	}
    }
    
    class Point {
    	int row,col,point;
		public Point(int row, int col, int point) {
			this.row = row;
			this.col = col;
			this.point = point;
		}
    	
    }
}
