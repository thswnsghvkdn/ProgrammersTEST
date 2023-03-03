import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
public class 등대 {
	
	Queue<Integer> lightQueue = new LinkedList<>();
	LinkedList<Integer>[] lightArray;
	boolean[] turnedOn;
	boolean[] visit;
	int lightCount = 0;
	public int solution(int n, int[][] lighthouse) {
		initLightArray(lighthouse, n);
		lightQueue.add(1);
		visit[1] = true;
		turnOn(1);
		return lightCount;
	}
	private void initLightArray(int[][] lighthouse, int n) {
		turnedOn = new boolean[n+1];
		visit = new boolean[n+1];
		lightArray = new LinkedList[n+1];
		for(int i = 0 ; i < n+1 ; i++) {
			lightArray[i] = new LinkedList<>();
		}
		for(int[] lightPair : lighthouse) {
			lightArray[lightPair[0]].add(lightPair[1]);
			lightArray[lightPair[1]].add(lightPair[0]);
		}
	}
	private boolean turnOn(int light) {
		boolean turnOnFlag = false;
		for(int nextLight : lightArray[light]) {
			if(!visit[nextLight]) {
				visit[nextLight] = true;
				lightQueue.add(nextLight);
				turnOnFlag = (!turnOn(nextLight) || turnOnFlag  );
			}
		}
		if(turnOnFlag) lightCount++;
		return turnOnFlag;
	}
}
