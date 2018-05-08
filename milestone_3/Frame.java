import javax.swing.*;
import java.awt.*;

class Frame extends JFrame{
	private Game game;
	private OptionsUI opts;

	public Frame(Board board){						//Constructor for frame class.
		super("XY_$uD0kU_YX");
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600,600));
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container container = this.getContentPane();
		game = new Game(board);
		opts = new OptionsUI(game);
		container.add(game, BorderLayout.CENTER);
		container.add(opts, BorderLayout.SOUTH);
		this.pack();
	}

}
