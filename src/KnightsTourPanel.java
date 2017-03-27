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
import java.util.ArrayList;

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
	private Image knight, x;
	private Vector start;
	private Vector loc;
	private Tiles[][] tiles;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private static final String COLS = "ABCDEFGH";
	private ImageIcon icon, visited;
	private boolean done = false;



	public KnightsTourPanel(int w, int h) {
		this.setLayout(new GridLayout(0, 9));
		this.setPreferredSize(new Dimension(w,h));
		this.setBackground(Color.green);
		tiles = new Tiles[8][8];
		setKnight();
		setVisited();
		populateTiles();
		makeBoard();
		makeIcons();
	}
	private void makeIcons() {
		icon = new ImageIcon();
		icon.setImage(getKnight());
		visited = new ImageIcon();
		visited.setImage(getVisitedImg());
	}
	private void setVisited() {
		try {
			URL url = getClass().getResource("visited.png");
			Image image = ImageIO.read(url);
			x = image;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private Image getVisitedImg() {
		return x;
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
							start = new Vector(currentCol, currentRow);
							loc = start;
							System.out.println(loc.x + " , "+ loc.y);
							b.setIcon(icon);
							firstClick = false;
							tiles[currentRow][currentCol].visited = true;
							System.out.println("Tile at " + loc.x + "x and " + loc.y + "y is " + tiles[currentRow][currentCol].visited);
							updatePossibles();
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
	private void updatePossibles() {
		for(int r = 0; r < chessBoardSquares.length; r++) {
			for(int c = 0; c < chessBoardSquares[r].length; c++) {
				tiles[r][c].setMoves((findPossibleMoves(tiles[r][c].getRC()).length));
			}
		}
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
		updatePossibles();
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
		updatePossibles();
		Tiles[] possibleMoves = findPossibleMoves(loc);
		if(possibleMoves.length == 0) {
			done = true;
			return false;
		}
		Tiles best = possibleMoves[0];
		if(possibleMoves.length > 1) {
		for(Tiles tile: possibleMoves) {
			if(tile.getMoves() > best.getMoves()) {
				best = tile;
			}
			else if(tile.getMoves() == best.getMoves()) {
				int xAway = Math.abs((tile.getRC().x - tiles.length / 2));
				int yAway = Math.abs((tile.getRC().y - tiles.length / 2));
				int bxAway = Math.abs((best.getRC().x - tiles.length / 2));
				int byAway = Math.abs((tile.getRC().y - tiles.length /2 ));		
				if(Math.sqrt((xAway * xAway) + (yAway * yAway)) < Math.sqrt((bxAway * bxAway) + (byAway * byAway))) {
					best = tile;
				}

			}
		}
		}
		moveTo(best);
		return true;
	}
	private void moveTo(Tiles best) {

		chessBoardSquares[loc.x][loc.y].setIcon(visited);
		chessBoardSquares[best.getRC().x][best.getRC().y].setIcon(icon);
		best.visited = true;
		loc = best.getRC();
	}
	public boolean isDone() {
		return done;
	}
	private Tiles[] findPossibleMoves(Vector loc) {
		ArrayList<Tiles> possibles = new ArrayList<Tiles>();
		if(UL(loc) != null && UL(loc).visited == false) {		
			possibles.add(UL(loc));
		}
		if(UR(loc) != null && UR(loc).visited == false){
			possibles.add(UR(loc));
		}
		if(DL(loc) != null && DL(loc).visited == false) {
			possibles.add(DL(loc));
		}
		if(DR(loc) != null && DR(loc).visited == false) {
			possibles.add(DR(loc));
		}
		if(LU(loc) != null && LU(loc).visited == false) {
			possibles.add(LU(loc));
		}
		if(LD(loc) != null && LD(loc).visited == false) {
			possibles.add(LD(loc));
		}
		if(RD(loc) != null && RD(loc).visited == false) {
			possibles.add(RD(loc));
		}
		if(RU(loc) != null && RU(loc).visited == false) {
			possibles.add(RU(loc));
		}
		Tiles[] arr = new Tiles[possibles.size()];
		return possibles.toArray(arr);
	}
	private Tiles RU(Vector loc) {
		if(loc.x < tiles.length - 2) {
			if(loc.y > 0) {
				return tiles[loc.x + 2][loc.y - 1];
			}
		}
		return null;
	}
	private Tiles RD(Vector loc) {
		if(loc.x < tiles.length - 2) {
			if(loc.y < tiles.length - 1) {
				return tiles[loc.x + 2][loc.y + 1];
			}
		}
		return null;
	}
	private Tiles LD(Vector loc) {
		if(loc.x > 1) {
			if(loc.y < tiles.length - 1) {
				return tiles[loc.x - 2][loc.y + 1];
			}
		}
		return null;
	}
	private Tiles LU(Vector loc) {
		if(loc.x > 2) {
			if(loc.y > 0) {
				return tiles[loc.x - 2][loc.y - 1];
			}
		}
		return null;
	}
	private Tiles DR(Vector loc) {
		if(loc.x < tiles.length - 1) {
			if(loc.y < tiles.length - 2) {
				return tiles[loc.x + 1][loc.y + 2];
			}
		}
		return null;
	}
	private Tiles DL(Vector loc) {
		if(loc.x > 0) {
			if(loc.y < tiles.length - 3) {
				return tiles[loc.x -1][loc.y + 2];
			}
		}
		return null;
	}
	private Tiles UR(Vector loc) {
		if(loc.x < tiles.length - 1) {
			if(loc.y > 1) {
				return tiles[loc.x + 1][loc.y - 2];
			}
		}
		return null;
	}
	private Tiles UL(Vector loc) {
		if(loc.x > 0) {
			if(loc.y > 1) {
				return tiles[loc.x - 1][loc.y - 2];
			}
		}
		return null;
	}


}
