

public class Board {
  private int board[][];
  private int subGridSize;
  private int boardSize;

  public Board(int board[][], int subGridSize) {
    this.board = board;
    this.subGridSize = subGridSize;
    this.boardSize = subGridSize * subGridSize;
  }

  @Override
  public String toString() {

    String boardString = "";

    for(int i = 0; i < boardSize; i++) {
      for(int j = 0; j < boardSize; j++) {
        boardString += board[i][j] + " ";
      }
      boardString += "\n";
    }

    return boardString;
  }

  public int[][] getBoard() {
    return this.board;
  }

  public int getSubGridSize() {
    return this.subGridSize;
  }

  public int getBoardSize() {
    return this.boardSize;
  }
}