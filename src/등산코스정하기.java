import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
public class 등산코스정하기 {
	
	PriorityQueue<Edge> edges = new PriorityQueue<>();
	Map<Integer, Edge> gates = new HashMap<>();
	HashSet<Integer> isSummit = new HashSet<>();
	HashSet<Integer> visit = new HashSet<>();
	Map<Integer, ArrayList<Edge>> eachEdge = new HashMap<>();
	int[] maxIntense;
	Edge answer = new Edge(0,0, 10000001);
	
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
    	init(n , paths, summits);
    	for(int gate : gates) {
    		edges.addAll(eachEdge.get(gate));
    		visit.add(gate);
    		maxIntense[gate] = 0;
    	}
    	while(!edges.isEmpty()) {
    		Edge edge = edges.poll();
    		if(visit.contains(edge.to)) {
    			continue;
    		}
    		int intensity = Math.max(maxIntense[edge.from], edge.intense);
    		if(maxIntense[edge.to] > intensity) {
    			maxIntense[edge.to] = intensity;
    		}
    		if(isSummit.contains(edge.to)) {
    			answer.update(new Edge(0, edge.to, intensity));
    			continue;
    		}
    		visit.add(edge.to);
    		edges.addAll(eachEdge.get(edge.to));
    	}
    	return new int[] { answer.to, answer.intense};
    }
    
    
    private void init(int n, int[][] paths, int[] summits) {

    	maxIntense = new int[n+1];
    	Arrays.fill(maxIntense, Integer.MAX_VALUE);
    	for(int[] path: paths) {
    		if(!eachEdge.containsKey(path[0])) {
    			eachEdge.put(path[0], new ArrayList<>());		
    		}
    		eachEdge.get(path[0]).add(new Edge(path[0], path[1], path[2]));
    		if(!eachEdge.containsKey(path[1])) {
    			eachEdge.put(path[1], new ArrayList<>());		
    		}
    		eachEdge.get(path[1]).add(new Edge(path[1], path[0], path[2]));

    	}
    	for(int summit : summits) {
    		isSummit.add(summit);
    	}
    	
    }
    class Edge implements Comparable<Edge> {
    	int from,to,intense;
    	Edge(int from, int to, int intense) {
    		this.from = from;
    		this.to = to;
    		this.intense = intense;
    	}
    	public void update(Edge edge) {
    		if(edge.intense < intense) {
    			intense = edge.intense;
    			to = edge.to;
    			from = edge.from;
    		} 
    		if(edge.intense == intense && edge.to < to) {
    			intense = edge.intense;
    			to = edge.to;
    			from = edge.from;	
    		}
    	}
    	@Override 
    	public int compareTo(Edge edge) {
    		if(this.intense == edge.intense)
    			return Integer.compare(this.to, edge.to);
    		return Integer.compare(this.intense, edge.intense);
    	}
    }
}
