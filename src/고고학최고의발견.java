
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
        dfs(clockHands, 0, firstZero);
        return shortestWay;
    }
	
	// depth 에 대해 0,0 ~ n,n 까지 한번씩 돌려보는 방법 주변에 0이 하나라도 있으면 시계를 돌리지 않는다.
    private void dfs(int[][] clock, int depth, int zero) {
    	for(int i = 0 ; i < mapRange; i++) {
    		for(int j = 0; j < mapRange; j++) {
    			int[][] copyClock = getCopy(clock);
    			int newZero = turnClockHands(copyClock, i , j);
    			if(newZero == 0) continue;
    			if(zero + newZero == mapRange * mapRange) {
    				shortestWay = Math.min(shortestWay, depth + 1);
    				return;
    			}
    			dfs(copyClock, depth+1, zero + newZero);
    		}
    	}
    }
    private int turnClockHands(int[][] clock, int row, int col) {
    	// 0으로 변화된 수
    	int zero = 0;
    	int[][] dir = { {0 , 0} , {1, 0} , {-1, 0} , {0, 1} , {0, -1} };
    	// 주변에 0이 하나라도 있으면 pass
    	for(int i = 0 ; i < 5 ; i++) {
    		int newRow = row + dir[i][0];
    		int newCol = col + dir[i][1];
    		if(newRow < 0 || newRow >= mapRange || newCol < 0 || newCol >= mapRange ) {
    			continue;
    		} 
    		if ( clock[newRow][newCol] == 0) {
    			return zero;
    		};
    	}
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
}
