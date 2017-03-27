import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/* One of the tricky things you have to figure out is how to have
 * the controls in the control panel talk to the KnightsTourPanel.
 * I know you'll figure out a way.  DON'T USE STATIC METHODS!!!!!
 */

public class KnightsTourControlPanel extends JPanel {
	
	static final int SPEED_MIN = 0;
	static final int SPEED_MAX = 30;
	static final int SPEED_INIT = 15;  
	KnightsTourPanel knights;
	public KnightsTourControlPanel(int w, int h, KnightsTourPanel cur) {
		this.setPreferredSize(new Dimension(w, h));
		this.setBackground(Color.orange);
		knights = cur;
		setUpButtonsAndSliders();
	}

	/* Add all the buttons and sliders used to control this Knight's tour.
	 * It is best if you allow the placement of the components to be handled
	 * by a layout manager.  You can find out lots about layouts if you google
	 * it!  You can also bind key events to the buttons and sliders, as well
	 */
	private void setUpButtonsAndSliders() {
		FlowLayout experimentLayout = new FlowLayout();
		this.setLayout(experimentLayout);
		JButton moveButton = new JButton("Move");
		moveButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println("MOVE KNIGHT");
				knights.makeThoughtfulMove();
				//TODO find way to move knight in panel
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});;
		JButton runButton = new JButton("Run");
		runButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println("RUN KNIGHT");
				while(knights.isDone() != true) {
					knights.makeThoughtfulMove();
				}
				//TODO find way to continually move knight in panel
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});;
		this.add(moveButton);
		this.add(runButton);
		
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, SPEED_MIN, SPEED_MAX, SPEED_INIT);
		ChangeListener CL = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				System.out.println("CHANGE SPEED to " + speedSlider.getValue());
				//TODO change speed to slider val
			}
			
		};
		
		speedSlider.addChangeListener(CL);
		speedSlider.setMajorTickSpacing(10);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		Font font = new Font("Serif", Font.ITALIC, 15);
		speedSlider.setFont(font);
		
		this.add(speedSlider);
	}
}
