import java.util.LinkedList;
import java.util.Vector;
import java.util.ArrayList;

public class Solver {

  private int row;
  private int col;
  private int numOfSolutions;
  private LinkedList<Board> boards;
  private boolean[] explored;
  private Board newBoard;
  private Board currBoard;


  public Solver() {
    this.row = 0;
    this.col = 0;
    this.numOfSolutions = 0;
    this.boards = new LinkedList<Board>();
  }

  private boolean isSolved(int board[][], int boardSize) {
    int i, j;
    for(i = 0; i < boardSize; i++) {
      for(j = 0; j < boardSize; j++) {
        if(board[i][j] == 0) {
          return false;
        }
      }
    }

    return true;
  }

  public void printBoard(int board[][], int boardSize) {
    for(int i = 0; i < boardSize; i++) {
      for(int j = 0; j < boardSize; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println("");
    }
  }

  private boolean hasConflict(int board[][], int subGridSize, int boardSize, int currRow, int currCol, int mode) {
    /**MODES:
    0: Sudoku
    1: Sudoku X
    2: Sudoku Y
    3: Sudoku XY
    **/
    int i, j, rowMin, rowMax, colMin, colMax;

    //check row
    for(i = 0; i < boardSize; i++) {
      if(currCol != i && board[currRow][i] == board[currRow][currCol]) {
        return true;
      }
    }

    //check column
    for(i = 0; i < boardSize; i++) {
      if(currRow != i && board[i][currCol] == board[currRow][currCol]) {
        return true;
      }
    }

    //check box or quadrant
    if((currRow + 1) == 1) {
      rowMin = 0;
      rowMax = subGridSize;
    } else if((currRow + 1) % subGridSize == 0) {
      rowMin = (currRow + 1) - subGridSize;
      rowMax = currRow + 1;
    } else {
      rowMax = currRow + 1;
      while(true) {
        rowMax++;
        if(rowMax % subGridSize == 0) break;
      }
      rowMin = rowMax - subGridSize;
    }

    if((currCol + 1) == 1) {
      colMin = 0;
      colMax = subGridSize;
    } else if((currCol + 1) % subGridSize == 0) {
      colMin = (currCol + 1) - subGridSize;
      colMax = currCol + 1;
    } else {
      colMax = currCol + 1;
      while(true) {
        colMax++;
        if(colMax % subGridSize == 0) break;
      }
      colMin = colMax - subGridSize;
    }

    for(i = rowMin; i < rowMax; i++) {
      for(j = colMin; j < colMax; j++) {
        if((currRow != i && currCol != j) &&
          board[i][j] == board[currRow][currCol]) {
          return true;
        }
      }
    }

    //check sudoku X
    if(mode == 1 || mode == 3){
      if(currRow == currCol) {
        //left diagonal
        for(i = 0; i < boardSize; i++) {
          // System.out.println(currRow+" "+currCol);
          if((currRow != i && currCol != i) &&
            board[i][i] == board[currRow][currCol]) {
            return true;
          }
        }
      }
      if((currRow + currCol) == (boardSize-1)){
        //right diagonal
        for(i = 0; i < boardSize; i++) {
          if((currRow != i && currCol != Math.abs(boardSize-1-i)) &&
            board[i][Math.abs(boardSize-1-i)] == board[currRow][currCol]) {
            return true;
          }
        }
      }
    }

    //check sudoku Y
    if(mode == 2 || mode == 3){
      if(currRow <= (boardSize/2) || (currCol == (boardSize/2) && currRow > (boardSize/2))){
        if(currRow == currCol || (currCol == (boardSize/2) && currRow > (boardSize/2))) {
          //left diagonal or bottom center
          for(i = 0; i < ((boardSize/2)+1); i++) {
            if((currRow != i && currCol != i) &&
              board[i][i] == board[currRow][currCol]) {
                System.out.println("CONFLICT ON0: " );
                System.out.println(i + " " + i);
                System.out.println(currRow + " " + currCol);
              this.printBoard(board, boardSize);
              System.out.println("");
              return true;
            }
          }
          for(i = (boardSize/2); i<boardSize; i++){
            if((currRow != i) &&
              board[i][boardSize/2] == board[currRow][currCol]) {
                System.out.println("CONFLICT ON2: " );
                System.out.println(i + " " + (boardSize/2));
                System.out.println(currRow + " " + currCol);
                this.printBoard(board, boardSize);
                System.out.println("");
              return true;
            }
          }
        }
        if(currRow + currCol == (boardSize-1) || (currCol == (boardSize/2) && currRow > (boardSize/2))){
          //right diagonal or bottom center
          for(i = 0; i < ((boardSize/2)+1); i++) {
            if((currRow != i && currCol != (boardSize-i-1)) &&
              board[i][boardSize-i-1] == board[currRow][currCol]) {
                System.out.println("CONFLICT ON1: " );
                System.out.println(i + " " + (boardSize-i-1));
                System.out.println(currRow + " " + currCol);
                this.printBoard(board, boardSize);
                System.out.println("");
              return true;
            }
          }
          for(i = (boardSize/2); i<boardSize; i++){
            if((currRow != i) &&
              board[i][boardSize/2] == board[currRow][currCol]) {
                System.out.println("CONFLICT ON2: " );
                System.out.println(i + " " + (boardSize/2));
                System.out.println(currRow + " " + currCol);
                this.printBoard(board, boardSize);
                System.out.println("");
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  ArrayList<int[][]> solve(Board board, int subGridSize, int boardSize, int mode) {
    ArrayList<int[][]> sols = new ArrayList<int[][]>();
    this.boards.add(board);
    int counter = 0;

    while(this.boards.size() != 0) {
      currBoard = boards.pop();

      if(isSolved(currBoard.getBoard(), boardSize)) {
        counter += 1;
        sols.add(currBoard.getBoard());
      } else {
        for(int i = 1; i < boardSize + 1; i++) {

          newBoard = currBoard.createNewState(i);
          if(!hasConflict(newBoard.getBoard(), subGridSize, boardSize, newBoard.getMove().get(0), newBoard.getMove().get(1), mode)) {

            newBoard.findNext(newBoard.getBoard(), boardSize);
            boards.push(newBoard);
          }
        }
      }
    }

    System.out.println("Number of Solutions: " + counter);
    return sols;
  }

  public boolean isValid(Board board, int subGridSize, int boardSize, int mode){
    int b[][] = board.getBoard();
    if(mode == 1 || mode ==3){
      for(int i=0; i<boardSize; i++){
        for(int j=i+1; j<boardSize; j++){
          if(b[i][i] != 0 && b[i][Math.abs(boardSize-1-i)] !=0){
            if((b[i][i] == b[j][j]) ||
            (b[i][Math.abs(boardSize-1-i)] == b[j][Math.abs(boardSize-1-j)])){
              return false;
            }
          }
        }
      }
    }

    if(mode == 2 || mode == 3){
      for(int j=0; j < ((boardSize/2)+1); j++){
        for(int i = j+1; i < ((boardSize/2)+1); i++) {
          if(b[j][j] != 0 && b[j][(boardSize-j-1)] !=0){
            if(b[i][i] == b[j][j]) return false;
            if(b[i][boardSize-i-1] == b[j][(boardSize-j-1)]) return false;
          }
        }
        for(int i = (boardSize/2)+1; i<boardSize; i++){
          if(b[j][j] != 0 && b[j][(boardSize-j-1)] !=0){
            if(b[i][boardSize/2] == b[j][j]) return false;
            if(b[i][boardSize/2] == b[j][(boardSize-j-1)]) return false;
          }
        }
      }

      for(int j = (boardSize/2); j<boardSize; j++){
        for(int i = 0; i < (boardSize/2); i++) {
          if(b[i][i] == b[j][boardSize/2]) return false;
          if(b[i][(boardSize-i-1)] == b[j][boardSize/2]) return false;
        }
        for(int i = j+1; i<boardSize; i++){
          if(b[i][boardSize/2] == b[j][boardSize/2]) return false;
        }
      }
    }
    return true;
  }


  public int getCol() {
    return this.col;
  }

  public int getRow() {
    return this.row;
  }
}
