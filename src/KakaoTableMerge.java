import java.util.ArrayList;
import java.util.List;

public class KakaoTableMerge {
    final int TABLESIZE = 5;
    Table[][] tables = new Table[TABLESIZE][TABLESIZE];
    List<String> answerList= new ArrayList<>();

    public String[] solution(String[] commands) {
        initTables();
        for(String command : commands) {
            parseCommand(command);
        }
        String[] answer = new String[answerList.size()];
        for(int i = 0 ; i < answerList.size() ; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    private void parseCommand(String command){
        String[] commandAndLocation = command.split(" ");
        switch (commandAndLocation[0]) {
            case "UPDATE" :
                if(commandAndLocation.length >= 4) {
                    updateTable(Integer.parseInt(commandAndLocation[1]),Integer.parseInt(commandAndLocation[2]),commandAndLocation[3]);
                }else {
                    updateTable(commandAndLocation[1],commandAndLocation[2]);
                }
                break;
            case "MERGE" :
                mergeTable(Integer.parseInt(commandAndLocation[1]), Integer.parseInt(commandAndLocation[2]) , Integer.parseInt(commandAndLocation[3]), Integer.parseInt(commandAndLocation[4]));
                break;
            case  "UNMERGE":
                unmergeTable(Integer.parseInt(commandAndLocation[1]), Integer.parseInt(commandAndLocation[2]));
                break;
            case "PRINT" :
                print(Integer.parseInt(commandAndLocation[1]), Integer.parseInt(commandAndLocation[2]));
                break;
        }
    }
    private void initTables() {
        for(int i = 0 ; i< TABLESIZE ; i++) {
            for(int j = 0 ; j < TABLESIZE ; j++) {
                tables[i][j] = new Table(i,j,"EMPTY");
            }
        }
    }
    private void updateTable(int row, int col, String value){
        int rootRow = tables[row][col].rootRow;
        int rootCol = tables[row][col].rootCol;
        for(int i = 0 ; i < TABLESIZE ; i++) {
            for(int j = 0; j < TABLESIZE; j++) {
                if(tables[i][j].rootRow == rootRow && tables[i][j].rootCol == rootCol) {
                    tables[i][j].value = value;
                }
            }
        }
    }
    private void updateTable( String value1, String value2){
        for(int i = 0 ; i < TABLESIZE ; i++) {
            for(int j = 0; j < TABLESIZE; j++) {
                if(tables[i][j].value.equals(value1) ) {
                    tables[i][j].value = value2;
                }
            }
        }
    }
    private void mergeTable(int row1, int col1, int row2, int col2) {
        if(tables[row1][col1].value.equals("EMPTY")) {
            updateTable(row1, col1, tables[row2][col2].value);
        }
        updateRootRow(row2, col2, row1, col1);
        updateTable(row2, col2, tables[row1][col1].value);
    }
    private void updateRootRow(int row, int col, int changeRow, int changeCol) {
        int rootRow = tables[row][col].rootRow;
        int rootCol = tables[row][col].rootCol;
        for(int i = 0; i < TABLESIZE ; i++) {
            for(int j = 0; j< TABLESIZE ; j++) {
                if(tables[i][j].rootRow == rootRow && tables[i][j].rootCol == rootCol) {
                    tables[i][j].rootRow = tables[changeRow][changeCol].rootRow;
                    tables[i][j].rootCol = tables[changeRow][changeCol].rootCol;
                }
            }
        }
    }
    private void unmergeTable(int row, int col) {
        String originValue = tables[row][col].value;
        int rootRow = tables[row][col].rootRow;
        int rootCol = tables[row][col].rootCol;

        for(int i = 0 ; i < TABLESIZE; i++) {
            for(int j = 0 ; j < TABLESIZE; j++) {
                if(tables[i][j].rootRow == rootRow && tables[i][j].rootCol == rootCol) {
                    tables[i][j].rootRow = i;
                    tables[i][j].rootCol = j;
                    if(i == row && j == col) {

                    }else {
                        tables[i][j].value = "EMPTY";
                    }
                }
            }
        }
    }
    private void print(int row, int col) {
        answerList.add(tables[row][col].value);
    }
    class Table {
        int rootRow, rootCol;
        String value;
        Table(int rootRow, int rootCol, String value){
            this.rootRow = rootRow;
            this.rootCol = rootCol;
            this.value = value;
        }
    }
}
