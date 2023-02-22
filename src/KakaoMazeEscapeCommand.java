import java.util.LinkedList;
import java.util.Queue;

public class KakaoMazeEscapeCommand {
    Queue<CommandAndLocation> commands = new LinkedList<>();
    String[] commandPriority = { "d", "l", "r" , "u"};
    int[][] nextDirection = { {1, 0} , {0, -1}, {0, 1}, {-1, 0}};
    int rowSize, colSize, endRow, endCol, answerSize;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        rowSize = n;
        colSize = m;
        endRow = r;
        endCol = c;
        answerSize = k;
        commands.add(new CommandAndLocation(x,y, ""));
        return bfs();
    }
    private String bfs() {
        while(!commands.isEmpty()) {

            CommandAndLocation currentLocation = commands.poll();

            if(currentLocation.command.length() == answerSize && currentLocation.row == endRow && currentLocation.col == endCol) {
                return currentLocation.command;
            }
            for(int i = 0 ; i < 4 ; i++ ){
                int nextRow = currentLocation.row + nextDirection[i][0];
                int nextCol = currentLocation.col + nextDirection[i][1];
                String nextCommand = currentLocation.command + commandPriority[i];
                if(nextRow <= 0 || nextRow > rowSize || nextCol <= 0 || nextCol > colSize || nextCommand.length() > answerSize) {
                    continue;
                } else {
                    commands.add(new CommandAndLocation(nextRow, nextCol, nextCommand));
                }
            }
        }
        return "impossible";
    }
    class CommandAndLocation {
        int row, col;
        String command;
        CommandAndLocation(int row, int col, String command){
            this.row = row;
            this.col = col;
            this.command = command;
        }
    }
}
