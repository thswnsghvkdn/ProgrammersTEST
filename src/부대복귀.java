import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 부대복귀 {
	int destination;
	int[] dp;
	boolean[] visit;
	LinkedList<Integer>[] roadArray;
	Queue<Integer> roadQueue = new LinkedList<>();
	
	public int[] solution(int n, int[][] roads, int[] sources, int destination) {
		dp = new int[n+1];
		visit = new boolean[n+1];
		this.destination = destination;
		initRoadArray(roads, n);
		int[] answer = new int[sources.length];
		roadQueue.add(destination);
		visit[destination] = true;
		findWay();
		for(int i = 0 ; i < sources.length; i++) {
			if(sources[i] == destination) {
				answer[i] = 0;
				continue;
			}
			answer[i] = dp[sources[i]] == 0 ? -1 : dp[sources[i]];
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
	private void findWay() {
		while(!roadQueue.isEmpty()) {
			int road = roadQueue.poll();
			for(int nextRoad : roadArray[road]) {
				if(!visit[nextRoad]) {
					dp[nextRoad] = dp[road] + 1;
					roadQueue.add(nextRoad);
					visit[nextRoad] = true;
				}
			}
		}
	}
	

}
