
public class 금과은운반하기 {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = Long.MAX_VALUE;
    	long minTime = 0;
        //long maxTime = 5000000000L;
        long maxTime = Long.MAX_VALUE;
        long midTime = 1;
        while(minTime < maxTime) {
        	midTime = ( minTime + maxTime ) / 2;
        	long maxGold = 0, maxSilver = 0, maxTotal = 0;
        	for(int i = 0 ; i < g.length; i++) {
        		long moveCount = ( midTime / t[i] );
        		moveCount = moveCount / 2  + moveCount % 2;
        		maxGold += Math.min(moveCount * w[i] , g[i]);
        		maxSilver += Math.min(moveCount * w[i], s[i]);
        		maxTotal += Math.min(moveCount * w[i] , g[i] + s[i]);
            		
        	}
        	if(maxGold >= a && maxSilver >= b && maxTotal >= (a+b)) {
        		maxTime = midTime;
        		answer = Math.min(midTime, answer);
        	} else {
        		minTime = midTime + 1;
        	}
        }
        long maxGold = 0, maxSilver = 0, maxTotal = 0;
    	for(int i = 0 ; i < g.length; i++) {
    		long moveCount = ( Long.MAX_VALUE / t[i] );
    		moveCount = moveCount / 2  + moveCount % 2;
    		maxGold += Math.min(moveCount * w[i] , g[i]);
    		maxSilver += Math.min(moveCount * w[i], s[i]);
    		maxTotal += Math.min(moveCount * w[i] , g[i] + s[i]);
        		
    	}
    	if(maxGold >= a && maxSilver >= b && maxTotal >= (a+b)) {
    		maxTime = midTime;
    		answer = Math.min(midTime, answer);
    	} else {
    		minTime = midTime + 1;
    	}
    	
        return answer;
    }
}
