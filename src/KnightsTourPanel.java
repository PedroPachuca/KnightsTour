import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/*
 * This class will be the display and will get the starting position
 * of the knight using a mousePress.  It should also have the data
 * like the 2D array and the current location of the knight.  The 
 * paintComponent method should redraw the view from the beginning, as it
 * always should.
 */

public class KnightsTourPanel extends JPanel {

	// what private data is needed?
	private boolean firstClick = true;
	private Image knight;
	private Vector start;
	private Vector loc;
	private int[][] grid;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private static final String COLS = "ABCDEFGH";
	
	

	public KnightsTourPanel(int w, int h) {
		this.setLayout(new GridLayout(0, 9));
		this.setPreferredSize(new Dimension(w,h));
		this.setBackground(Color.green);
		grid = new int[w][h];
		addMouseListener();
		setKnight();
		makeBoard();
	}
	private void setKnight() {
		try {
			URL url = getClass().getResource("knight.png");
			Image image = ImageIO.read(url);
			knight = image;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private Image getKnight() {
		return knight;
	}
	public void makeBoard() {
		this.setBorder(new LineBorder(Color.black));
		for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon();
                icon.setImage(getKnight());
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)|| (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                chessBoardSquares[jj][ii] = b;
            }
        }
		
		fillBoard();
	}
	private void fillBoard() {
		 this.add(new JLabel(""));
	        // fill the top row
	        for (int ii = 0; ii < 8; ii++) {
	            this.add(
	                    new JLabel(COLS.substring(ii, ii + 1),
	                    SwingConstants.CENTER));
	        }
	        // fill the black non-pawn piece row
	        for (int ii = 0; ii < 8; ii++) {
	            for (int jj = 0; jj < 8; jj++) {
	                switch (jj) {
	                    case 0:
	                        this.add(new JLabel("" + (ii + 1),
	                                SwingConstants.CENTER));
	                    default:
	                       this.add(chessBoardSquares[jj][ii]);
	                }
	            }
	        }
		
	}
	// add the mouse listener.  This will only work for the 
	// first click, and then after the first click, there should
	// be no more mouse listening!
	private void addMouseListener() {
		if(firstClick) {
		MouseListener mouseListener = new MouseListener() {
			
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
				start = new Vector(arg0.getX(), arg0.getY());
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				start = new Vector(e.getX(), e.getY());
				firstClick = false;
				// TODO Auto-generated method stub
				
			}
		};
		this.addMouseListener(mouseListener);
		}
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// stuff to draw the board and knight

	}
	/* make random move just selects a new location at random
	 * if the knight is trapped (no new locations to move to)
	 * then false is returned.  Otherwise, true is returned.
	 * The knight's location should be updated and the 
	 */
	public boolean makeRandomMove() {
		
		return false;
	}
	/* make a move to a new location that ensures the best chance
	 * for a complete traversal of the board.
	 * if the knight is trapped (no new locations to move to)
	 * then false is returned.  Otherwise, true is returned.
	 */
	public boolean makeThoughtfulMove() {

		return false;
	}
	
	
}
