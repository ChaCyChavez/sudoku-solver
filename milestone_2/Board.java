import java.util.Vector;

public class Board implements Cloneable {
  public int board[][];
  private int subGridSize;
  private int boardSize;
  private Vector<Integer> move;

  public Board(int board[][], int subGridSize) {
    this.board = board;
    this.subGridSize = subGridSize;
    this.boardSize = subGridSize * subGridSize;
    this.move = new Vector<Integer>();
  }

  

  public Board(int board[][], Vector<Integer> move, int subGridSize) {
    this.board = board;
    this.subGridSize = subGridSize;
    this.boardSize = subGridSize * subGridSize;
    this.move = move;
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

  public Vector<Integer> getMove() {
    return this.move;
  }

  public void setMove(Vector<Integer> move) {
    this.move = move;
  }

  public void findNext(int board[][], int boardSize) {
    int i, j;
    for(i = 0; i < boardSize; i++) {
      for(j = 0; j < boardSize; j++) {    
        if(board[i][j] == 0) {
          if(this.move.size() != 0) {
            this.move.set(0, i);
            this.move.set(1, j);
          } else {
            this.move.add(i);
            this.move.add(j);
          }
          return;
        }
      }
    }
  }
   
   public Board createNewState(int x) {
    int [][] newBoard = new int[this.board.length][];
    for(int i = 0; i < this.board.length; i++)
        newBoard[i] = this.board[i].clone();

    newBoard[this.move.get(0)][this.move.get(1)] = x;

    Vector<Integer> newMove = new Vector<Integer>(this.move);
    return new Board(newBoard, newMove, this.subGridSize);
   }
}