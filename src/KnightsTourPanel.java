import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
	private Tiles[][] tiles;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private static final String COLS = "ABCDEFGH";
	private ImageIcon icon;
	
	

	public KnightsTourPanel(int w, int h) {
		this.setLayout(new GridLayout(0, 9));
		this.setPreferredSize(new Dimension(w,h));
		this.setBackground(Color.green);
		tiles = new Tiles[8][8];
		setKnight();
		populateTiles();
		makeBoard();
		makeIcon();
	}
	private void makeIcon() {
		icon = new ImageIcon();
		icon.setImage(getKnight());
	}
	private void populateTiles() {
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[x].length; y++) {
				tiles[x][y] = new Tiles(x, y);
			}
		
		}
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
            	int currentRow = ii;
            	int currentCol = jj;
                JButton b = new JButton();
                if ((jj % 2 == 1 && ii % 2 == 1)|| (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                b.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
					@Override
					public void mousePressed(MouseEvent e) {
						if(firstClick) {
						start = new Vector(currentRow, currentCol);
						loc = start;
						b.setIcon(icon);
						firstClick = false;
						}
					}
					@Override
					public void mouseReleased(MouseEvent e) {}
                	
                	});
                chessBoardSquares[jj][ii] = b;
            }
        }
		
		fillBoard();
	}
	private void fillBoard() {
		 this.add(new JLabel(""));
	        for (int ii = 0; ii < 8; ii++) {
	            this.add(
	                    new JLabel(COLS.substring(ii, ii + 1),
	                    SwingConstants.CENTER));
	        }
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
		Tiles[] possibleMoves = findPossibleMoves(loc);
		if(possibleMoves.length == 0) {
			return false;
		}
		int index = (int) (Math.random() * possibleMoves.length);
		moveTo(possibleMoves[index]);
		return true;
	}
	/* make a move to a new location that ensures the best chance
	 * for a complete traversal of the board.
	 * if the knight is trapped (no new locations to move to)
	 * then false is returned.  Otherwise, true is returned.
	 */
	public boolean makeThoughtfulMove() {
		Tiles[] possibleMoves = findPossibleMoves(loc);
		if(possibleMoves.length == 0) {
			return false;
		}
		Tiles best = null;
		for(Tiles tile: possibleMoves) {
			if(tile.getMoves() > best.getMoves()) {
				best = tile;
			}
		}
		moveTo(best);
		return true;
	}
	private void moveTo(Tiles best) {
		chessBoardSquares[loc.x][loc.y].setIcon(null);
		chessBoardSquares[best.getRC().x][best.getRC().y].setIcon(icon);
		loc = best.getRC();
	}
	private Tiles[] findPossibleMoves(Vector loc) {
		//TODO find all possible moves
		return null;
	}
	
	
}
