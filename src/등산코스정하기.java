import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
public class 등산코스정하기 {
	ArrayList<Integer>[] courses;
	boolean[] visit;
	Map<Integer, Boolean> isGate;
	Map<Integer, Boolean> isSummit;
	int[][] climbIntense;
	
	// 목적지까지 최소 INTENSE
	GoalAndIntense[] DP;
	final int MAXVALUE = 10000000;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {0,0};
        initCourses(n + 1, paths, gates, summits);
        GoalAndIntense result = new GoalAndIntense(MAXVALUE, MAXVALUE);
        for(int gate : gates) {
        	Arrays.fill(visit, false);
        	updateResult(result, dfs(gate));
        }
        answer[0] = result.goal;
        answer[1] = result.intense;
        return answer;
    }
    private void initCourses(int n, int[][] paths,int[] gates, int[] summits) {
    	courses = new ArrayList[n];
    	visit = new boolean[n];
    	DP = new GoalAndIntense[n];
    	isGate = new HashMap<Integer, Boolean>();
    	isSummit = new HashMap<Integer, Boolean>();
    	climbIntense = new int[n][n];
    	for(int i = 0 ; i < n ; i++) {
    		courses[i] = new ArrayList<>();
    		visit[i] = false;
    		DP[i] = new GoalAndIntense ( MAXVALUE , MAXVALUE );
    		isGate.put(i, false);
    		isSummit.put(i, false);
    		climbIntense[i] = new int[n];
    	}
    	for(int[] path : paths) {
    		courses[path[0]].add(path[1]);
    		courses[path[1]].add(path[0]);
    		climbIntense[path[1]][path[0]] = path[2];
    		climbIntense[path[0]][path[1]] = path[2];
    	}
    	for(int gate : gates) {
    		isGate.put(gate, true);
    	}
    	for(int summit : summits) {
    		isSummit.put(summit, true);
    	}
    }
    private GoalAndIntense dfs(int path) {
    	if(isSummit.get(path)) {
    		return new GoalAndIntense(path, 0);
    	}
    	if(DP[path].goal != MAXVALUE) {
    		return DP[path];
    	}
    	GoalAndIntense result = new GoalAndIntense(MAXVALUE, MAXVALUE);
    	for(int nextPath : courses[path]) {
    		if(isGate.get(nextPath) || visit[nextPath]) {
    			continue;
    		} 
    		visit[nextPath] = true;
    		GoalAndIntense temp = dfs(nextPath);
            visit[nextPath] = false;
    		temp.intense = Math.max(temp.intense, climbIntense[path][nextPath]);
    		updateResult(result, temp);
    	}
    	return DP[path] = result;
     }
    // 강도가 낮은 목적지로 변경
    // 강도가 같다면 번호가 낮은 목적지로 변경
    private void updateResult(GoalAndIntense result, GoalAndIntense instance) {
    	if(result.intense > instance.intense) {
    		result.intense = instance.intense;
    		result.goal = instance.goal;
    	} else if(result.intense == instance.intense && result.goal > instance.goal) {
    		result.goal = instance.goal;
    	}
    	
    }
    // 목적지와 강도
    class GoalAndIntense {
    	int goal, intense;
    	GoalAndIntense(int goal, int intense){
    		this.goal = goal;
    		this.intense = intense;
    	}
    }
}
