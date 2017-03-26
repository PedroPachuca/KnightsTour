import java.awt.BorderLayout;

import javax.swing.JFrame;


public class Knight_Tour_Launcher {

	int WIDTH=800, HEIGHT=600;
	
	public static void main(String[] args) {
		new Knight_Tour_Launcher().start();
	}

	private void start() {
		JFrame frame = new JFrame("Knight's Tour!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		KnightsTourPanel knightsPanel = new KnightsTourPanel(WIDTH,HEIGHT);
		frame.add(knightsPanel);
		frame.add(new KnightsTourControlPanel(WIDTH,HEIGHT/3, knightsPanel), BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

}
