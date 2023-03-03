import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 부대복귀 {
	int destination;
	int[] dp;
	boolean[] visit;
	LinkedList<Integer>[] roadArray;
	public int[] solution(int n, int[][] roads, int[] sources, int destination) {
		dp = new int[n+1];
		Arrays.fill(dp, -1);
		visit = new boolean[n+1];
		this.destination = destination;
		initRoadArray(roads, n);
		int[] answer = new int[sources.length];
		for(int i = 0 ; i < sources.length; i++) {
			answer[i] = findWay(sources[i]);
		}
		return answer;
	}
	private void initRoadArray(int[][] roads, int n) {
		visit = new boolean[n+1];
		roadArray = new LinkedList[n+1];
		for(int i = 0 ; i < n+1 ; i++) {
			roadArray[i] = new LinkedList<>();
		}
		for(int[] roadPair : roads) {
			roadArray[roadPair[0]].add(roadPair[1]);
			roadArray[roadPair[1]].add(roadPair[0]);
		}
	}
	private int findWay(int road) {
		if(road == destination) {
			return dp[road] = 0;
		}
		if(dp[road] != -1) {
			return dp[road];
		}
		int shortestWay = -1;
		for(int nextRoad : roadArray[road]) {
			if(!visit[nextRoad]) {
				visit[nextRoad] = true;
				shortestWay = updateDistance( findWay(nextRoad) , shortestWay );
				visit[nextRoad] = false;
			}
		}
		return dp[road] = shortestWay;
	}
	private int updateDistance(int newDist, int shortestWay) {
		if(newDist == -1) {
			return -1;
		}
		else {
			if(shortestWay == -1) shortestWay = Integer.MAX_VALUE;
			return Math.min(shortestWay,  newDist + 1);
		}
	}
}
