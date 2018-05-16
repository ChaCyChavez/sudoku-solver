import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class OptionsUI extends JPanel{
	private JButton solveButton;


	public OptionsUI(Game game){							//Constructor for game class.
		this.setLayout(new FlowLayout());
		this.setBackground(Color.decode("#C9C9C9"));
		solveButton = new JButton("SOLVE!");
		this.add(solveButton);
		solveButton.addActionListener(new ActionListener(){		//listener to solve
			@Override
			public void actionPerformed(ActionEvent e){
				Solver solver = new Solver();
				int board[][] = new int[game.getBoardSize()][game.getBoardSize()];
				game.getUpdatedBoard(board);
				Board b = new Board(board, game.getSubGridSize());
				System.out.println(b.toString());
				b.findNext(b.getBoard(), b.getBoardSize());
				ArrayList<int[][]> solutions = solver.solve(b, b.getSubGridSize(), b.getBoardSize(), 0);
				ArrayList<int[][]> solutionsX;
				ArrayList<int[][]> solutionsY;
				ArrayList<int[][]> solutionsXY;
				boolean xFlag = true;
				boolean yFlag = true;

				if(!solver.isValid(b, b.getSubGridSize(), b.getBoardSize(), 1)) xFlag = false;
				if(!solver.isValid(b, b.getSubGridSize(), b.getBoardSize(), 2)) yFlag = false;

				if(xFlag){
					solutionsX = solver.solve(b, b.getSubGridSize(), b.getBoardSize(), 1);
				}else{
					solutionsX = new ArrayList<int[][]>();
					JOptionPane.showMessageDialog(null, "Incompatible for Sudoku X!", "Sudoku Solver", JOptionPane.INFORMATION_MESSAGE);
				}

				if(yFlag){
					solutionsY = solver.solve(b, b.getSubGridSize(), b.getBoardSize(), 2);
				}else{
					solutionsY = new ArrayList<int[][]>();
					JOptionPane.showMessageDialog(null, "Incompatible for Sudoku Y!", "Sudoku Solver", JOptionPane.INFORMATION_MESSAGE);
				}

				if(xFlag && yFlag){
					solutionsXY = solver.solve(b, b.getSubGridSize(), b.getBoardSize(), 3);
				}else{
					solutionsXY = new ArrayList<int[][]>();
					JOptionPane.showMessageDialog(null, "Incompatible for Sudoku XY!", "Sudoku Solver", JOptionPane.INFORMATION_MESSAGE);
				}

				if(solutions.size() > 0){
					SolutionFrame solFrame = new SolutionFrame(solutions, "Sudoku");
				}else{
					JOptionPane.showMessageDialog(null, "No solutions found!", "Sudoku Solver", JOptionPane.INFORMATION_MESSAGE);
				}

				if(solutionsX.size() > 0){
					SolutionFrame solFrame = new SolutionFrame(solutionsX, "Sudoku X");
				}else{
					if(xFlag) JOptionPane.showMessageDialog(null, "No solutions for Sudoku X found!", "Sudoku Solver", JOptionPane.INFORMATION_MESSAGE);
				}

				if(solutionsY.size() > 0){
					SolutionFrame solFrame = new SolutionFrame(solutionsY, "Sudoku Y");
				}else{
					if(yFlag) JOptionPane.showMessageDialog(null, "No solutions for Sudoku Y found!", "Sudoku Solver", JOptionPane.INFORMATION_MESSAGE);
				}

				if(solutionsXY.size() > 0){
					SolutionFrame solFrame = new SolutionFrame(solutionsXY, "Sudoku XY");
				}else{
					if(xFlag && yFlag) JOptionPane.showMessageDialog(null, "No solutions for Sudoku XY found!", "Sudoku Solver", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
	}
}
