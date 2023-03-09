
public class 카운트다운 {
    CountAndSingleBull[] DP = new CountAndSingleBull[100000];
    int MAXVALUE = 10000000;
    
	public int[] solution(int target) {
        initDart();
        throwDart(target , 0, 0);
        return new int[] {DP[target].count, DP[target].singleBull};
    }
	private void initDart() {
		for(int i = 0 ; i < DP.length; i++) {
			DP[i] = new CountAndSingleBull(0,0);
		}
	}
	private CountAndSingleBull throwDart(int target, int singleBull, int count) {
		if(target == 0) {
			return new CountAndSingleBull(count,singleBull);
		}
		if(target < 0 ) {
			return new CountAndSingleBull(MAXVALUE, MAXVALUE);
		}
		if(DP[target].count != 0) {
			return DP[target];
		}
		CountAndSingleBull result = new CountAndSingleBull(MAXVALUE, MAXVALUE);
		int score;
		for(int i = 21; i > 0 ; i--) {
			// 불(50)과 싱글 포인트 점수에 대한 반복
			score = ( i == 21 ? 50 : i);
			result = getLeastOne(result, throwDart(target - score, singleBull + 1 , count + 1));
			
		}
		for(int i = 20 ; i > 0 ; i--) {
			for(int j = 2 ; j <= 3; j++ ) {
				score = i * j;
				result = getLeastOne(result, throwDart(target - score, singleBull, count + 1));
				
			}
		}
		return DP[target] = result;
	}
	// 인수 두개중 적게 던지거나 같을 경우 single bull 을 많이 던진 인수를 반환
	private CountAndSingleBull getLeastOne(CountAndSingleBull firstOne, CountAndSingleBull secondOne) {
		if(firstOne.count == secondOne.count) {
			if(firstOne.singleBull > secondOne.singleBull) {
				return firstOne;
			}
			return secondOne;
		}
		if(firstOne.count < secondOne.count) {
			return firstOne;
		}
		return secondOne;	
	}
    // 최소 던진 횟수와 싱글 or bull 횟수
	class CountAndSingleBull {
		int count, singleBull;
		CountAndSingleBull(int count, int singleBull) {
			this.count = count;
			this.singleBull = singleBull;
		}
	}
}
