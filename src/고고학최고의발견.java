
public class 고고학최고의발견 {
    int mapRange;
    int shortestWay = Integer.MAX_VALUE;
	public int solution(int[][] clockHands) {
        int answer = 0;
        mapRange = clockHands.length;
        dfs(0,clockHands,0);
        return shortestWay;
    }
	
	private void dfs(int depth, int[][] clock, int totalTurn) {
		if(depth == mapRange) {
			makeAllElementZero(clock, totalTurn);
			return;
		}
		for(int i = 0 ; i < 4; i++) {
			int[][] copyClock = getCopy(clock);
			if(i != 0) {
				turnClock(copyClock, 0, depth, i);
			}
			dfs(depth+1, copyClock, totalTurn + i);
		}
	}
	private void makeAllElementZero(int[][] clock, int totalTurn) {
		int[][] copyClock = getCopy(clock);
		for(int i = 1; i < mapRange; i++) {
			for(int j = 0; j < mapRange; j++) {
				int turningCount = (4 - copyClock[i-1][j]) % 4;
				totalTurn += turningCount;
				turnClock(copyClock, i , j , turningCount);
			}
		}
		if(checkZero(copyClock)) {
			shortestWay = Math.min(shortestWay, totalTurn);
		}
	}
	private void turnClock(int[][] clock, int row, int col, int turningCount) {
		int[][] dir = {{0,0}, {0,1} , {0,-1}, {-1,0}, {1,0}};
		for(int i = 0 ; i < 5; i++) {
			int newRow = row + dir[i][0];
			int newCol = col + dir[i][1];
			if(newRow < 0 || newRow >= mapRange || newCol < 0 || newCol >= mapRange) {
				continue;
			}
			clock[newRow][newCol] = ( clock[newRow][newCol] + turningCount ) % 4;
		}
	}
    
    private int[][] getCopy(int[][] originalArray) {
    	int[][] newArray = new int[originalArray.length][originalArray[0].length];
    	for (int i = 0; i < originalArray.length; i++) {
    	    System.arraycopy(originalArray[i], 0, newArray[i], 0, originalArray[i].length);
    	}
    	return newArray;
    }
    
    private boolean checkZero(int[][] clock) {
    	for(int i = 0 ; i < mapRange; i++ ) {
    		for(int j = 0 ; j < mapRange; j++) {
    			if(clock[i][j] != 0) return false;
    		}
    	}
    	return true;
    }
}
