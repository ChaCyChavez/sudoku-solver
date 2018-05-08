import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Game extends JPanel{
	private JTextField[][] cells;
	private static final int PADDING = 2;
  private Board board;
  private int boardSize;
  private int subGridSize;

	public Game(Board board){							//Constructor for game class.
    this.board = board;
    this.boardSize = board.getBoardSize();
    this.subGridSize = board.getSubGridSize();
		this.setLayout(new GridLayout(boardSize, boardSize, PADDING, PADDING));
		this.setBackground(Color.decode("#C9C9C9"));
		this.cells = new JTextField[boardSize][boardSize];

    int[][] b = board.getBoard();
		for(int i=0;i<boardSize;i++){
			for(int j=0;j<boardSize;j++){
        String val = String.valueOf(b[i][j]);
				if(!val.equals("0")){
          cells[i][j] = new JTextField(val, 1);
          cells[i][j].setEnabled(false);
        }else{
          cells[i][j] = new JTextField(1);
        }
        cells[i][j].setHorizontalAlignment(JTextField.CENTER);
				this.add(cells[i][j]);
			}
		}
	}

  public void getUpdatedBoard(int b[][]){
    for(int i=0;i<this.boardSize;i++){
			for(int j=0;j<this.boardSize;j++){
        String val = cells[i][j].getText();
        if(val.equals("") || val.equals(null)) val = "0";
        b[i][j] = Integer.parseInt(val);
			}
		}
  }

  public int getBoardSize(){
    return this.boardSize;
  }

  public int getSubGridSize(){
    return this.subGridSize;
  }
}
