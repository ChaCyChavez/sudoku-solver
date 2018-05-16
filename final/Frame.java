import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Frame extends JFrame{
	private Game game;
	private OptionsUI opts;
	private JTabbedPane tabs = new JTabbedPane();

	public Frame(ArrayList<Board> boards){						//Constructor for frame class.
		super("Sudoku");
		this.setPreferredSize(new Dimension(600,600));
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container container = this.getContentPane();
		for (int i = 0; i < boards.size(); i++) {
			String text = "Board " + (i + 1);
			this.tabs.add(text, this.getPanel(boards.get(i)));
		}

		container.add(this.tabs);
		this.pack();
	}

	public JPanel getPanel(Board board) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(600,600));
		game = new Game(board);
		opts = new OptionsUI(game);
		panel.add(game, BorderLayout.CENTER);
		panel.add(opts, BorderLayout.SOUTH);

		return panel;
	}

}
