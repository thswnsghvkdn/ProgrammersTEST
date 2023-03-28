
class 공이동시뮬레이션 {
	int mapRow, mapCol;
	public long solution(int mapRow, int mapCol, int x, int y, int[][] queries) {
	  this.mapRow = mapRow;
	  this.mapCol = mapCol;
	  return expandRange(x,y,getReverseQueries(queries));
	}
	private long expandRange(int row, int col, int[][] movements) {
	  final int RIGHT = 1, LEFT = 0, UP = 2, DOWN = 3; 
		  
	  int startRow, endRow;
	  int startCol, endCol;
	  startRow = endRow = row;
	  startCol = endCol = col;
	  for(int[] move : movements) {
if(startRow < 0 || startRow >= mapRow || startCol  < 0 || startCol >= mapCol) return 0;
		if(endRow < 0 || endRow >= mapRow || endCol  < 0 || endCol >= mapCol) return 0;
		
		switch(move[0])  {
		case(LEFT) :
			if(endCol == 0) {
				startCol = Math.min(mapCol - 1, startCol + move[1]); 
			}
			else {
				endCol = endCol + move[1]; 
				startCol = Math.min(mapCol - 1, startCol + move[1]); 
			}
			break;
		case(RIGHT) :
			if(startCol == mapCol - 1) {
				endCol = Math.max(0, endCol - move[1]); 
			}
			else {
				startCol = startCol - move[1]; 
				endCol = Math.max(0, endCol - move[1]); 
			}
		break;
		case(UP) :
			if(endRow == 0) {
				startRow = Math.min(mapRow - 1, startRow + move[1]); 
			}
			else {
				endRow = endRow + move[1]; 
				startRow = Math.min(mapRow - 1, startRow + move[1]); 
			}
		break;
		case(DOWN) :
			if(startRow == mapRow - 1) {
				endRow = Math.max(0, endRow - move[1]); 
			}
			else {
				startRow = startRow - move[1]; 
				endRow = Math.max(0, endRow - move[1]); 
			}
			break;
		}
		
	  }
	  
		
	  return (long)(startRow - endRow + 1) * (long)(startCol - endCol + 1 );
	}
	private int[][] getReverseQueries(int[][] queries){ 
		int[][] reverse = new int[queries.length][2];
		int endIndex = queries.length - 1;
		for(int i = 0 ; i < queries.length; i++) {
			reverse[i][0] = queries[endIndex][0];
			reverse[i][1] = queries[endIndex--][1];
		}
		return reverse;
	}
}