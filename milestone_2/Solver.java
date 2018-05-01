
public class Solver {

  private int row;
  private int col;
  private int numOfSolutions;


  public Solver() {
    this.row = 0;
    this.col = 0;
    this.numOfSolutions = 0;
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

  public void findNext(int board[][], int boardSize, int row, int col) {
    int i, j;
    for(i = 0; i < boardSize; i++) {
      for(j = 0; j < boardSize; j++) {
        if(board[i][j] == 0) {
          this.row = i;
          this.col = j;
          return;
        }
      }
    }
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

    // //check sudoku X
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

    // //check sudoku Y
    if(mode == 2 || mode == 3){
      if(currRow <= (boardSize/2) || (currCol == (boardSize/2) && currRow > (boardSize/2))){
        if(currRow == currCol || (currCol == (boardSize/2) && currRow > (boardSize/2))) {
          //left diagonal or bottom center
          for(i = 0; i < ((boardSize/2)+1); i++) {
            if((currRow != i && currCol != i) &&
              board[i][i] == board[currRow][currCol]) {
              return true;
            }
          }
        }
        if(currRow + currCol == (boardSize-1) || (currCol == (boardSize/2) && currRow > (boardSize/2))){
          //right diagonal or bottom center
          for(i = 0; i < ((boardSize/2)+1); i++) {
            if((currRow != i && currCol != (boardSize-i-1)) &&
              board[i][boardSize-i-1] == board[currRow][currCol]) {
              return true;
            }
          }
        }
        for(i = (boardSize/2); i<boardSize; i++){
          if((currRow != i) &&
            board[i][boardSize/2] == board[currRow][currCol]) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean solve(int board[][], int subGridSize, int boardSize, int row, int col) {
    int i, j, x;
    int currRow = row;
    int currCol = col;
    if(isSolved(board, boardSize)) {
      System.out.println("true");
      this.numOfSolutions++;

      System.out.println(this.numOfSolutions);
      printBoard(board, boardSize);
      return true;
    }

    for(i = 1; i < boardSize + 1; i++) {
      board[currRow][currCol] = i;
      if(!hasConflict(board, subGridSize, boardSize, currRow, currCol, 1)) {
        findNext(board, boardSize, this.row, this.col);
        // if(solve_board(output_file, board, subGridSize, boardSize, row, col) == 1) {
        //   return 1;
        // }
        solve(board, subGridSize, boardSize, this.row, this.col);
      }
    }
    board[currRow][currCol] = 0;
    return false;
  }


  public int getCol() {
    return this.col;
  }

  public int getRow() {
    return this.row;
  }
}
