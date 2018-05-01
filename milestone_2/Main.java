
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

  public static void main(String[] args) {

    String fileName = "in.txt";

    try {
      FileReader fr = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fr);


      int numOfPuzzle = Integer.parseInt(br.readLine());
      System.out.println(numOfPuzzle);

      Solver solver = new Solver();

      while(numOfPuzzle > 0) {
        int subGridSize = Integer.parseInt(br.readLine());

        System.out.println(subGridSize);
        int boardSize = subGridSize * subGridSize;

        int board[][] = new int[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++) {
          String token[] = br.readLine().split(" ");
          for(int j = 0; j < boardSize; j++) {
            board[i][j] = Integer.parseInt(token[j]);
          }
        }

        Board currBoard = new Board(board, subGridSize);
        solver.findNext(currBoard.getBoard(), currBoard.getBoardSize(), solver.getRow(), solver.getCol());
        solver.solve(currBoard.getBoard(), currBoard.getSubGridSize(), currBoard.getBoardSize(), solver.getRow(), solver.getCol());

        numOfPuzzle--;
      }


    } catch(FileNotFoundException ex) {

    } catch(IOException ex) {

    }
  }
}