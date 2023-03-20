
public class 코딩테스트공부 {
	   public int solution(int alp, int cop, int[][] problems) {
		   int goal_a = 0, goal_b = 0;
		   for(int[] problem : problems) {
			   goal_a = Math.max(goal_a, problem[0]);
			   goal_b = Math.max(goal_b, problem[1]);
		   }
		   int[][] dp = new int[goal_a+2][goal_b+2];
		   if(alp >= goal_a && cop >= goal_b) return 0;
		   if(alp > goal_a) goal_a = alp;
		   if(cop > goal_b) goal_b = cop;
		   for(int i = 0; i <= goal_a+1; i++) {
			   for(int j = 0 ; j <= goal_b+1; j++) {
				   dp[i][j] = Integer.MAX_VALUE - 100;
			   }
		   }
		   dp[alp][cop] = 0;
		   for(int i = alp; i <= goal_a; i++) {
			   dp[i+1][cop] = Math.min(dp[i+1][cop], dp[i][cop] + 1);
			   for(int j = cop ; j <= goal_b; j++) {
				   dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);
				   for(int[] problem: problems) {
					   if(problem[0] <= i && problem[1] <= j) {
						   int limitA, limitC;
						   limitA = i + problem[2] > goal_a ? goal_a : i + problem[2];
						   limitC = j + problem[3] > goal_b ? goal_b : j + problem[3];
						   dp[limitA][limitC] = Math.min(dp[limitA][limitC], dp[i][j] + problem[4]);
					   }
				   }
			   }
		   }
		   return dp[goal_a][goal_b];
	    }
}
