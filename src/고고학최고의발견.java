
public class 고고학최고의발견 {
    int mapRange;
    int shortestWay = Integer.MAX_VALUE;
	public int solution(int[][] clockHands) {
        int answer = 0;
        mapRange = clockHands.length;
        // 처음 0 개수
        int firstZero = 0;
        for(int i = 0 ; i < mapRange; i ++) {
        	for(int j = 0 ; j < mapRange; j++) {
        		if(clockHands[i][j] == 0) {
        			firstZero++;
        		}
        	}
        }
        dfs(clockHands, 0, firstZero,0);
        return shortestWay;
    }
	
	// 0행에 대해 모든 경우의 수로 시계를 돌리고 나머지 행들을 이전 행을 0으로 변경시키기 위한 작업을 한다.
    private void dfs(int[][] clock, int column, int zero, int turnedCount) {
    	if(column == mapRange) return;
    	for(int i = 0 ; i < 4; i++) {
			int newZero = 0; 
			int[][] copyClock = getCopy(clock);
			if(i != 0) {  
				newZero = turnClockHands(copyClock, 0 , column);
			}
			makeAllElementZero(copyClock, zero + newZero, turnedCount + i);
			dfs(copyClock, column+1, zero + newZero, turnedCount + i);
		}
    }
    private void makeAllElementZero(int[][] clock, int zero, int turnedCount) {
    	int[][] copyClock = getCopy(clock);
    	for(int i = 1; i < mapRange; i++ ) {
    		for(int j = 0 ; j < mapRange; j++) {
    			int turningCount = ( 4 - copyClock[i-1][j] ) % 4; // 0이 될만큼 회전해야 하는 수
    			turnedCount += turningCount;
    			for(int count = 0 ; count < turningCount; count++) { 
    				zero += turnClockHands(copyClock, i, j);
    			}
    		}
    	}
    	if(checkZero(copyClock)) {
    		shortestWay = Math.min(shortestWay, turnedCount);
    	}
    }
    private int turnClockHands(int[][] clock, int row, int col) {
    	// 0으로 변화된 수
    	int zero = 0;
    	int[][] dir = { {0 , 0} , {1, 0} , {-1, 0} , {0, 1} , {0, -1} };
    	
    	for(int i = 0 ; i < 5 ; i++) {
    		int newRow = row + dir[i][0];
    		int newCol = col + dir[i][1];
    		if(newRow < 0 || newRow >= mapRange || newCol < 0 || newCol >= mapRange) {
    			continue;
    		} 
    		clock[newRow][newCol] = ( clock[newRow][newCol] + 1 ) % 4;
    		if(clock[newRow][newCol] == 0) {
    			zero++;
    		}
    	}
    	return zero;
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
