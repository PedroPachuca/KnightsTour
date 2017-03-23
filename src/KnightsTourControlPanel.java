import java.awt.*;

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
	
	public KnightsTourControlPanel(int w, int h) {
		this.setPreferredSize(new Dimension(w, h));
		this.setBackground(Color.orange);
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
		this.add(new JButton("Move"));
		this.add(new JButton("Run"));
		
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, SPEED_MIN, SPEED_MAX, SPEED_INIT);
		ChangeListener CL = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				System.out.println(arg0.toString());
				
			}
			
		};
		speedSlider.addChangeListener(CL);

		//Turn on labels at major tick marks.
		speedSlider.setMajorTickSpacing(10);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		Font font = new Font("Serif", Font.ITALIC, 15);
		speedSlider.setFont(font);
		
		this.add(speedSlider);
	}
}
