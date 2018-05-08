
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

  public static void main(String[] args) {

    String fileName = "in2.txt";

    try {
      FileReader fr = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fr);


      int numOfPuzzle = Integer.parseInt(br.readLine());

      Solver solver = new Solver();

      while(numOfPuzzle > 0) {
        int subGridSize = Integer.parseInt(br.readLine());
        int boardSize = subGridSize * subGridSize;

        int board[][] = new int[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++) {
          String token[] = br.readLine().split(" ");
          for(int j = 0; j < boardSize; j++) {
            board[i][j] = Integer.parseInt(token[j]);
          }
        }

        Board currBoard = new Board(board, subGridSize);
        // currBoard.findNext(currBoard.getBoard(), boardSize);
        // solver.solve(currBoard, currBoard.getSubGridSize(), currBoard.getBoardSize());

        Frame f = new Frame(currBoard);

        numOfPuzzle--;
      }


    } catch(FileNotFoundException ex) {

    } catch(IOException ex) {

    }
  }
}
