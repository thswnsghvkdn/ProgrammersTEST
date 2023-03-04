
public class 이차원동전뒤집기 {
	// 2진수로 표현한 뒤집을 순서집합 1이면 뒤집는다
	String binaryPriority = "";
	// 동전 가로 세로 길이
	int rowAndColumnSize;
	// 동전 MAP
	String[] beginMap, targetMap;
	// 최소 경우의 수
	int minFlipedCount = -1;
    public int solution(int[][] beginning, int[][] target) {
        int answer = 0;
        rowAndColumnSize = beginning.length * 2;
        initPriority();
        initCoinMap(beginning, target);
        makeAllCases();
        return minFlipedCount;
    }
    private void initPriority() {
    	// 동전 배열 가로 세로 길이만큼 0을 추가한다.
    	for(int i = 0 ; i < rowAndColumnSize; i++) {
    		binaryPriority += "0";
    	}
    }
    // 동전 map String 배열로 변환 
    private void initCoinMap(int[][] begin,int[][] target) {
    	beginMap = new String[begin.length];
    	targetMap = new String[target.length];
    	for(int i = 0 ; i < begin.length; i++) { 
    		beginMap[i] = "";
    		targetMap[i] = "";
    		for(int j = 0 ;  j < begin[0].length; j++) {
    			beginMap[i] = beginMap[i] + Integer.toString(begin[i][j]);
    			targetMap[i] = targetMap[i] + Integer.toString(target[i][j]);
    		}
    	}
    }
    // 모든 경우의 수 확인
    private void makeAllCases() {
    	//4 라면 1111(2) 는 2^(5) - 1 이다
    	int casesNumber = (int) (Math.pow(2 , rowAndColumnSize + 1) - 1);
    	for(int i = 1; i <= casesNumber; i++) {
    		String nextBinary = Integer.toBinaryString(i);
    		String nextCase = binaryPriority + nextBinary;
    		String rowCase = nextCase.substring(nextBinary.length() , nextBinary.length() + rowAndColumnSize / 2);
    		String colCase = nextCase.substring(nextBinary.length() + rowAndColumnSize / 2, nextBinary.length() + rowAndColumnSize);
    		if(rowCase.equals("01011") &&  colCase.equals("01010")) {
    			System.out.print("a");
    		}
    		checkEachCase(rowCase, colCase);
    	}
    }
    // 하나의 이진수에대해 가능한지 파악
    private void checkEachCase(String rowCase, String colCase) {
    	int flipedCount = 0;
    	String[] copyMap = deepCopy(beginMap);
    	for(int i = 0 ; i < rowCase.length(); i++) {
    		// 이진수 문자열에 대해 1이면 뒤집는다
    		if(rowCase.charAt(i) == '1') {
    			flipedCount++;
    			flipCoin(copyMap,"row", i);
    		}
    	}
    	for(int i = 0 ; i < colCase.length(); i++) {
    		// 이진수 문자열에 대해 1이면 뒤집는다
    		if(colCase.charAt(i) == '1') {
    			flipedCount++;
    			flipCoin(copyMap, "col", i);
    		}
    	}
    	if(checkMatched(copyMap) ) {
    		if(minFlipedCount == -1 || minFlipedCount > flipedCount) {
    			minFlipedCount = flipedCount;
    		}
    	}
    }
    // 동전 MAP 에서 열 또는 행을 뒤집는다.
    private void flipCoin(String[] beginMap, String type, int num) {
    	if(type.equals("col")) {
    		for(int i = 0 ; i < beginMap.length; i++ ) {
    			char fliped = beginMap[i].charAt(num) == '0' ? '1' : '0';
    			StringBuilder sb = new StringBuilder(beginMap[i]);
    			sb.setCharAt(num, fliped);
    			beginMap[i] = sb.toString();
    		}
    	} else if(type.equals("row")) {
    		String flipedString = "";
    		for(int i = 0 ; i < beginMap.length; i++) {
    			char fliped = beginMap[num].charAt(i) == '0' ? '1' : '0';
    			flipedString = flipedString + fliped;
    		}
    		beginMap[num] = flipedString;
    	}
    }
    // target map 과 일치하는지 검사
    private boolean checkMatched(String[] beginMap) {
    	for(int i = 0 ; i < beginMap.length; i++) {
    		if(!beginMap[i].equals(targetMap[i])) {
    			return false;
    		}
    	}
    	return true;
    }
    private String[] deepCopy(String[] origin) {
    	String[] copy = new String[origin.length];
    	System.arraycopy(origin, 0, copy, 0, origin.length);
    	return copy;
    }
}
