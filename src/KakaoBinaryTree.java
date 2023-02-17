public class KakaoBinaryTree {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i = 0 ; i < answer.length ; i++) {
            answer[i] = isTree(getPerfectTree(numbers[i])) == true ? 1 : 0;
        }
        return answer;
    }
    // 포화 이진 트리 생성
    private String getPerfectTree(long number) {
        String binary = Long.toBinaryString(number);
        String nodeCount = Long.toBinaryString(binary.length());
        // 이진수의 길이가 2^n - 1 이면 포화이진트리이다
        // 이진수의 길이가 2^n - 1 이 아니라면 가장 가까이 만드는 방법은 이진수의 길이를 다시 이진수로 표현한 다음 1로 채우는 것이다
        while(nodeCount.contains("0")) {
            long notPerfectBinary = Long.parseLong(nodeCount , 2);
            notPerfectBinary++;
            nodeCount = Long.toBinaryString(notPerfectBinary);
            binary = "0" + binary;
        }
        return binary;
    }
    // 부모가 0 일때 자식이 1이면 false
    private boolean isTree(String tree) {
        if(tree.length() <= 3) {
            return compareRootElement(tree);
        }
        String leftChild = tree.substring(0 , tree.length() / 2);
        String rightChild = tree.substring(tree.length() / 2 + 1);
        return compareRootElement(tree) && isTree(leftChild) && isTree(rightChild);
    }
    // 인수로 받은 이진수 포화 이진 트리 string 에서 루트 원소 반환
    private boolean compareRootElement(String tree) {
        String leftChild = tree.substring(0 , tree.length() / 2);
        String rightChild = tree.substring(tree.length() / 2 + 1);
        if(getRootElement(tree) == '0') {
            if(getRootElement(leftChild) == '1' || getRootElement(rightChild) == '1') {
                return false;
            }
        }
        return true;
    }
    private char getRootElement(String tree) {
        return tree.charAt(tree.length() / 2);
    }
}
