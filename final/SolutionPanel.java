import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class SolutionPanel extends JPanel{
	private JTextField[][] cells;
	private static final int PADDING = 2;
	int boardSize;

	public SolutionPanel(int[][] board){							//Constructor for game class
		this.boardSize = board.length;
		this.setLayout(new GridLayout(boardSize, boardSize, PADDING, PADDING));
		this.setBackground(Color.decode("#C9C9C9"));
		this.cells = new JTextField[boardSize][boardSize];

		for(int i=0;i<boardSize;i++){
			for(int j=0;j<boardSize;j++){
        String val = String.valueOf(board[i][j]);
				if(!val.equals("0")){
          cells[i][j] = new JTextField(val, 1);
					cells[i][j].setEnabled(false);
					cells[i][j].setDisabledTextColor(Color.BLACK);
        }else{
          cells[i][j] = new JTextField(1);
        }
        cells[i][j].setHorizontalAlignment(JTextField.CENTER);
				this.add(cells[i][j]);
			}
		}
	}

	public void setBoard(int[][] board){
		for(int i=0;i<boardSize;i++){
			for(int j=0;j<boardSize;j++){
        String val = String.valueOf(board[i][j]);
        cells[i][j].setText(val);
        cells[i][j].setEnabled(false);
        cells[i][j].setHorizontalAlignment(JTextField.CENTER);
				this.add(cells[i][j]);
			}
		}
	}

  public int getBoardSize(){
    return this.boardSize;
  }
}
