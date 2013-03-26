package pentago;

public class QuadrantGrid {
	
	private int len;
	public Marble[][] grid;
	
	public QuadrantGrid() {
		this(3);
	}
	
	public QuadrantGrid(int quadHeight) {
		len = quadHeight;
		grid = new Marble[len][len];
		
		for (int i = 0; i < quadHeight; i++) {
			for (int j = 0; j < quadHeight; j++) {
				grid[i][j] = new Marble();
			}
		}
	}
	
	public void rotateCW() {
		//clockwise <--> rotate right
		Marble[][] rotatedGrid = new Marble[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				rotatedGrid[i][j] = grid[(len-1)-j][i];
			}
		}
		grid = rotatedGrid;
	}
	
	public void rotateCCW() {
		//counterclockwise <--> rotate left
		Marble[][] rotatedGrid = new Marble[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				rotatedGrid[i][j] = grid[j][(len-1)-i];
			}
		}
		grid = rotatedGrid;
	}
}