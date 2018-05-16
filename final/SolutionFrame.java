import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SolutionFrame extends JFrame {
  private ArrayList<int[][]> solutions;
  private SolutionPanel solutionPanel;
  private int currentIndex = 0;
  JButton nextSol;
  JButton prevSol;
  JLabel solNum;


  public SolutionFrame(ArrayList<int[][]> solutions, String frameName) {
    super(frameName);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400,400));
		this.setVisible(true);
		this.setResizable(false);
		Container container = this.getContentPane();
		solutionPanel = new SolutionPanel(solutions.get(0));
		container.add(solutionPanel, BorderLayout.CENTER);
    JPanel optionsPanel = new JPanel(new FlowLayout());
    nextSol = new JButton("NEXT");
    prevSol = new JButton("PREV");
    solNum = new JLabel("Solution #1 of " + solutions.size());
    optionsPanel.add(solNum);
    optionsPanel.add(prevSol);
    optionsPanel.add(nextSol);
    container.add(optionsPanel, BorderLayout.SOUTH);

    this.solutions = solutions;
    nextSol.addActionListener(new ActionListener(){		//listener to solve
			@Override
			public void actionPerformed(ActionEvent e){
        if(solutions.size() > 0 && currentIndex != solutions.size()-1 ) currentIndex++;
        solNum.setText("Solution #" + (currentIndex+1) + " of "+ solutions.size());
        solutionPanel.setBoard(solutions.get(currentIndex));
			}
		});
    prevSol.addActionListener(new ActionListener(){		//listener to solve
			@Override
			public void actionPerformed(ActionEvent e){
        if(solutions.size() > 0 && currentIndex != 0) currentIndex--;
        solNum.setText("Solution #" + (currentIndex+1) + " of "+ solutions.size());
				solutionPanel.setBoard(solutions.get(currentIndex));
			}
		});

    this.pack();

  }
}
