	import java.util.Stack;

public class 일일영옮기기 {
	    public String[] solution(String[] strings) {
	        String[] answer = {};
	        for(String str : strings) {
	            System.out.println( checkString(str) );
	        }
	        return answer;
	    }
	    private String checkString(String str){ 
	        Stack<String> stack = new Stack<>();
	        StringBuilder stringBuilder = new StringBuilder();
	        for(int i =0 ; i < str.length() ; i++ ){
	            String ch = String.valueOf(str.charAt(i));
	            stack.add(ch);
	            if(checkOOZ(stack)) stringBuilder.append("110");
	        }
	        int size = stack.size();
	        int lastZero = 0;
	        StringBuilder answer = new StringBuilder();
	        reverseStack(stack, answer);
	        lastZero =   findLastZero(answer.toString());
	    
	        
	        return getAnswer(answer.toString(), lastZero, stringBuilder);
	    }
	    private int findLastZero(String str) {
	    	int lastZero = 0;
	    	for(int i = 0 ; i < str.length(); i++) {
	    		if(str.charAt(i) == '0') lastZero = i+1;
	    	}
	    	return lastZero;
	    }
	    private void reverseStack(Stack<String>stack, StringBuilder str) {
	    	if(stack.size() == 1) {
	    		String st = stack.pop();
	    		str.append(st);
	    		return;
	    	}
	    	String num = stack.pop();
	    	reverseStack(stack, str);
	    	str.append(num);
	    }
	    private boolean checkOOZ(Stack<String> stack) {
	        if(stack.size() >= 3) {
	            String Z = stack.pop();
	            String O1 = stack.pop();
	            String O2 = stack.pop();
	            if(Z.equals("0") && O1.equals("1") && O2.equals("1")) {
	                return true;
	            } else {
	                stack.add(O2);
	                stack.add(O1);
	                stack.add(Z);
	                return false;
	            }
	        }
	        return false;
	    }
	    private String getAnswer(String str1 , int lastZero, StringBuilder OOZ) {
	    	if(lastZero == 0) return OOZ.toString() + str1;
	    	if(lastZero + 1 == str1.length()) return str1 + OOZ.toString();
	    	lastZero--;
	    	return str1.substring(0, lastZero + 1) + OOZ.toString() + str1.substring(lastZero + 1);
	       
	    }
	
}
