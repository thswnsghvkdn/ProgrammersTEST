import java.util.Arrays;

public class 숫자타자대회 {
	int[][] shortcut =  {
	        { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
	        { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
	        { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
	        { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
	        { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
	        { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
	        { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
	        { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
	        { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
	        { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
	    };
	int[][][] dp;
	String numbers;
	int numberLength;
	
    public int solution(String numbers) {
        int answer = 0;
        this.numbers = numbers;
        this.numberLength = numbers.length();
        initDP();
        return getMin(4,6,0);
    }
    
    private void initDP() {
    	dp = new int[numberLength ][10][10];
        for (int i = 0; i < numberLength ; i++) {
            for (int j = 0; j < 10; j++)
            	for(int k = 0 ; k < 10; k++)
            		dp[i][j][k] = -1;
        }
    }
    
    private int getMin(int left, int right, int depth) {
    	if(depth == numberLength) {
    		return 0;
    	}
    	if(dp[depth][left][right] != -1) {
    		return dp[depth][left][right];
    	}
    	int nextNumber = numbers.charAt(depth) - '0';
    	// 두 value 중 해당 depth에서 처리가 되지 않을경우가 있으므로 max value 로 초기화
    	int leftValue = Integer.MAX_VALUE, rightValue = Integer.MAX_VALUE;
    	// 왼손 -> 다음 수 
    	if(nextNumber != right) {
    		leftValue = getMin(nextNumber, right, depth + 1) + shortcut[left][nextNumber];
    	}
    	// 오른손 -> 다음 수
    	if(nextNumber != left) {
    		rightValue = getMin(left, nextNumber , depth + 1) + shortcut[right][nextNumber]; 
    	}
    	
    	return dp[depth][left][right] = Math.min(rightValue, leftValue);
    }
    
}
