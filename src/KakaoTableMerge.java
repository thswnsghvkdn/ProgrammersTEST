import java.util.ArrayList;

public class KakaoTableMerge {
    Table[][] tables;
    ArrayList<String> answerList = new ArrayList<>();
    final int TABLESIZE = 5;

    public String[] solution(String[] commands) {
        tables = new Table[TABLESIZE][TABLESIZE];
        initTables();
        for(String command : commands){
            parseCommand(command);
        }
        String[] answer = new String[answerList.size()];
        for(int i = 0 ; i < answer.length ; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    private void parseCommand(String command){
        String[] commandAndLocation = command.split(" ");
        switch (commandAndLocation[0]) {
            case "UPDATE" :
                if(commandAndLocation.length >= 4) {
                    updateTable(commandAndLocation[1],commandAndLocation[2],commandAndLocation[3]);
                }else {
                    updateTable(commandAndLocation[1],commandAndLocation[2]);
                }
                break;
            case "MERGE" :
                mergeTables(commandAndLocation[1], commandAndLocation[2] , commandAndLocation[3], commandAndLocation[4]);
                break;
            case  "UNMERGE":
                unmergeTables(commandAndLocation[1], commandAndLocation[2]);
                break;
            case "PRINT" :
                printAnswer(commandAndLocation[1], commandAndLocation[2]);
                break;
        }
    }
    private void initTables() {
        for(int i = 0 ; i < TABLESIZE; i++){
            for(int j = 0 ; j < TABLESIZE; j++) {
                tables[i][j] = new Table(i,j, "EMPTY");
            }
        }
    }
    private void updateTable(String row, String col, String value) {
        int r = tables[Integer.parseInt(row)][Integer.parseInt(col)].rootRow;
        int c = tables[Integer.parseInt(row)][Integer.parseInt(col)].rootCol;
        for(int i = 0 ; i < TABLESIZE; i++) {
            for(int j = 0 ; j < TABLESIZE; j++){
                if(tables[i][j].rootRow == r && tables[i][j].rootCol == c) {
                    tables[i][j].value = value;
                }
            }
        }
    }
    private void updateTable(String value1, String value2) {
        for(int i = 0 ; i < TABLESIZE; i++){
            for(int j = 0 ; j < TABLESIZE; j++) {
                if(tables[i][j].value.equals(value1)) {
                    updateTable(Integer.toString(i),Integer.toString(j),value2);
                }
            }
        }
    }
    private void mergeTables(String row1, String col1, String row2, String col2) {
        int r1 = Integer.parseInt(row1);
        int c1 = Integer.parseInt(col1);
        int r2 = Integer.parseInt(row2);
        int c2 = Integer.parseInt(col2);

        if(tables[r1][c1].value.equals("EMPTY") && !tables[r2][c2].value.equals("EMPTY")) {
            updateTable(Integer.toString(r1),Integer.toString(c1), tables[r2][c2].value);
        }
        tables[r2][c2].rootRow = tables[r1][c1].rootRow;
        tables[r2][c2].rootCol = tables[r1][c1].rootCol;
        updateTable(row2,col2, tables[r1][c1].value);

    }
    private void unmergeTables(String row, String col) {
        int r = Integer.parseInt(row);
        int c = Integer.parseInt(col);

        int rootRow = tables[r][c].rootRow;
        int rootCol = tables[r][c].rootCol;
        for(int i = 0 ; i < TABLESIZE ; i++){
            for(int j = 0 ; j < TABLESIZE; j++){
                if(tables[i][j].rootRow == rootRow && tables[i][j].rootCol == rootCol){
                    tables[i][j].rootRow = i;
                    tables[i][j].rootCol = j;
                    // 인수로 들어온 위치를 제외한 병합해제 셀들은 값 초기화
                    if(i != r || j != c) {
                        tables[i][j].value = "EMPTY";
                    }
                }
            }
        }
    }
    private  void printAnswer(String row, String col) {
        int r = Integer.parseInt(row);
        int c = Integer.parseInt(col);
        answerList.add(tables[r][c].value);
    }
    class Table {
        // 기본 위치와 병합시 root 위치
        int r, c, rootRow, rootCol;
        String value;
        Table(int r, int c, String value) {
            this.r = r;
            this.c = c;
            this.value = value;
            rootRow = r;
            rootCol = c;
        }
    }
}
