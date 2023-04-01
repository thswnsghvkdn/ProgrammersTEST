import java.util.*;
class 카드짝맞추기 {
    int[][] board;
    class Card {
        int r,c,count;
        public Card(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }
    public int solution(int[][] board, int r, int c) {
        int answer = 0;
        this.board = board;
        return permutate(new Card(r,c,0));
    }
    private int permutate(Card start) {
        int answer = Integer.MAX_VALUE;
        for(int num = 1; num <= 6 ; num++ ){
            ArrayList<Card> cards = new ArrayList<>();
            for(int i = 0; i < 4 ; i++) {
                for(int j = 0 ; j < 4; j++) {
                    if(board[i][j] == num) cards.add(new Card(i,j,0));
                }
            }
            if(cards.isEmpty()) continue;
            int one = bfs(start, cards.get(0)) + bfs(cards.get(0), cards.get(1)) + 2;
            int two = bfs(start, cards.get(1)) + bfs(cards.get(1), cards.get(0)) + 2;
            for(int i = 0 ; i < 2 ;i++) 
            {
                board[cards.get(i).r][cards.get(i).c] = 0;
            }
            answer = Math.min(answer, one + permutate(cards.get(1)));
            answer = Math.min(answer, two + permutate(cards.get(0)));
            for(int i = 0 ; i < 2 ;i++) 
            {
                board[cards.get(i).r][cards.get(i).c] = num;
            }
        }
        if(answer == Integer.MAX_VALUE) return 0;
        return answer;
    }
    int[][] directions = {{-1, 0} , {1, 0} , {0, -1} , {0, 1}};
    private int bfs(Card start, Card end) {
        boolean[][] visit = new boolean[4][4];
        Queue<Card> q = new LinkedList<>();
        q.add(start);
        visit[start.r][start.c] = true;
        while(!q.isEmpty()) {
            Card current = q.poll();
            if(current.r == end.r && current.c == end.c) return current.count;
            for(int i = 0 ; i < 4 ; i++ ) {
                int nr = current.r + directions[i][0];
                int nc = current.c + directions[i][1];
                if(nr < 0 || nr > 3 || nc < 0 || nc > 3) continue;
                if(!visit[nr][nc]) {
                    visit[nr][nc] = true;
                    q.add(new Card(nr, nc, current.count+1));
                }
                for(int j = 0 ; j < 2 ; j++) {
                    if(board[nr][nc] != 0) break;
                    if(nr + directions[i][0] < 0 || nr + directions[i][0] > 3 || nc + directions[i][1] < 0 || nc + directions[i][1] > 3) break;
                    nr += directions[i][0];
                    nc += directions[i][1];
                }
                if(!visit[nr][nc]) {
                    visit[nr][nc] = true;
                    q.add(new Card(nr,nc,current.count+1));
                }
            }
        }
        return 0;
    }
}