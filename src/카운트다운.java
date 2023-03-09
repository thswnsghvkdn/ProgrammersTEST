
public class 카운트다운 {
    CountAndSingleBull[] DP = new CountAndSingleBull[100000];
    final int maxValue = 987654321;
	public int[] solution(int target) {
        initDart();
        throwDart(target );
        return new int[] {DP[target].count, DP[target].singleBull};
    }
	private void initDart() {
		for(int i = 0 ; i < DP.length; i++) {
			DP[i] = new CountAndSingleBull(0,0);
		}
	}
	private CountAndSingleBull throwDart(int target) {
		if(target == 0) {
			return new CountAndSingleBull(0, 0);
		}
		if(target < 0 ) {
			return new CountAndSingleBull(maxValue, maxValue);
		}
		if(DP[target].count != 0) {
			return DP[target];
		}
		CountAndSingleBull result = new CountAndSingleBull(maxValue, maxValue);
		int score;
		for(int i = 21; i > 0 ; i--) {
			// 불(50)과 싱글 포인트 점수에 대한 반복
			score = ( i == 21 ? 50 : i);
			CountAndSingleBull temp =  throwDart(target - score);
			temp.count += 1;
			temp.singleBull += 1;

			result = getLeastOne(result, temp);
		}
		for(int i = 20 ; i > 0 ; i--) {
			for(int j = 2 ; j <= 3; j++ ) {
				score = i * j;
				CountAndSingleBull temp =  throwDart(target - score);
				temp.count += 1;
				result = getLeastOne(result, temp);
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
			return new CountAndSingleBull(secondOne.count, secondOne.singleBull);
		}
		if(firstOne.count < secondOne.count) {
			return firstOne;
		}
		return new CountAndSingleBull(secondOne.count, secondOne.singleBull);	
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
