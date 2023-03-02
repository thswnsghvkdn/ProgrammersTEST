

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
		turnOn();
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
	private void turnOn() {
		while(!lightQueue.isEmpty()) {
			int light = lightQueue.poll();
			visit[light] = true;
			// 단말 노드의 경우 상대방 등대를 킨다.
			if(lightArray[light].size() == 1) {
				if( !turnedOn[ lightArray[light].peek()] ) {
					turnedOn[ lightArray[light].peek()] = true;
					lightCount++;
				}
			}
			for(int nextLight : lightArray[light]) {
				if(!visit[nextLight]) {
					lightQueue.add(nextLight);
				}
			}
		}
	}
}
