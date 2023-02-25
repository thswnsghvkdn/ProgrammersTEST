import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 억억단을외우자 {
    int[] factors;
    int[] prefixSum;
    public int[] solution(int e, int[] starts) {
        int[] res = new int[starts.length];
        putFactors(e);
        getPrefixSum(e);
        for(int i = 0 ; i < starts.length; i++)
        {
            res[i] = prefixSum[starts[i]];
        }
        return res;
    }
    private void getPrefixSum(int e) {
        prefixSum = new int[e + 1];
        prefixSum[e] = e;
        int maxFactor = factors[e];
        for(int i  = e - 1; i >= 1 ; i--){
            if(factors[i]< maxFactor) {
                prefixSum[i] = prefixSum[i + 1];
            } else {
                prefixSum[i] = i;
                maxFactor = factors[i];
            }
        }
    }
    private void putFactors(int e) {
        factors = new int[e + 1];
        Arrays.fill(factors, 0);
        for (int i = 2; i <= e; i++)
            for (int j = 1; j <= e / i; j++)
                factors[i * j ]++;
    }



}
