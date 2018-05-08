import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
				solver.solve(b, b.getSubGridSize(), b.getBoardSize());
			}
		});
	}
}
