package pentago;

//import java.util.Arrays;

public class Board {
	
//	 	There should be only one Board object. It contains (most likely 4) QuadrantGrids, which
//	 	each contain (most likely 9) Marbles.
//		In loops and other functions:
//			a and b will refer to the QuadrantGrid in QuadrantGrid[][] board.
//			c and d will refer to the Marble in Marble[][] grid.

	public QuadrantGrid[][] board;
	public int boardHeight; //number of QuadrantGrids in one dimension (default 2)
	public int quadHeight; //number of Marbles in one dimension (default 3)
	public int target; //length of winning line (default 5)
	
	public Board() {
		this(2,3,5);
	}
	
	public Board(int boardHeight, int quadHeight, int target) {
		this.boardHeight = boardHeight;
		this.quadHeight = quadHeight;		
		this.target = target;
		
		board = new QuadrantGrid[boardHeight][boardHeight];
		
		for (int a = 0; a < boardHeight; a++) {
			for (int b = 0; b < boardHeight; b++) {
				board[a][b] = new QuadrantGrid(quadHeight);
			}
		}
	}

	public String checkForWinner() {
		
//		If there's a color, check if there's room for target # of Marbles in all directions. Then investigate.
//		Thus only end pieces of winning lines will be checked; checks will most likely not start in the middle.
//		Draw games are possible, so don't stop when one winner is found.
//		*** The code references to "Up" "Down" etc are flipped on the printed representation because the
//		origin is at the top.***
//		Possible improvement: if a space doesn't work for Down, ignore the corresponding one that goes Up, etc.
		
		String pWinner; //potential winner -- i.e. the current color to be checked
		String winner = null; //whichever color already has a confirmed win
		int totalMarbleHeight = boardHeight * quadHeight;
		int vertical, horizontal; //distance from origin (0,0) at the top left of the board
		
		for (int a = 0; a < boardHeight; a++) {
			for (int b = 0; b < boardHeight; b++) {
				for (int c = 0; c < quadHeight; c++) {
					for (int d = 0; d < quadHeight; d++) {
						pWinner = board[a][b].grid[c][d].getMode();
						if (pWinner != null) { //saves time
							vertical = a*quadHeight + c;
							horizontal = b*quadHeight + d;

							Boolean roomForUp = vertical - (target-1) >= 0;
							Boolean roomForLeft = horizontal - (target-1) >= 0;
							Boolean roomforDown = vertical + (target-1) < totalMarbleHeight;
							Boolean roomForRight = horizontal + (target-1) < totalMarbleHeight;

							if (roomForUp) {
								if (checkInThisDirection(pWinner,-1,0,a,b,c,d)) {
									if (winner != null && winner != pWinner) return "draw";
									else winner = pWinner;
								}
							}
							if (roomForUp && roomForRight) {
								if (checkInThisDirection(pWinner,-1,1,a,b,c,d)) {
									if (winner != null && winner != pWinner) return "draw";
									else winner = pWinner;
								}
							}
							if (roomForRight) {
								if (checkInThisDirection(pWinner,0,1,a,b,c,d)) {
									if (winner != null && winner != pWinner) return "draw";
									else winner = pWinner;
								}
							}
							if (roomforDown && roomForRight) {
								if (checkInThisDirection(pWinner,1,1,a,b,c,d)) {
									if (winner != null && winner != pWinner) return "draw";
									else winner = pWinner;
								}
							}
							if (roomforDown) {
								if (checkInThisDirection(pWinner,1,0,a,b,c,d)) {
									if (winner != null && winner != pWinner) return "draw";
									else winner = pWinner;
								}
							}
							if (roomforDown && roomForLeft) {
								if (checkInThisDirection(pWinner,1,-1,a,b,c,d)) {
									if (winner != null && winner != pWinner) return "draw";
									else winner = pWinner;
								}
							}
							if (roomForLeft) {
								if (checkInThisDirection(pWinner,0,-1,a,b,c,d)) {
									if (winner != null && winner != pWinner) return "draw";
									else winner = pWinner;
								}
							}
							if (roomForUp && roomForLeft) {
								if (checkInThisDirection(pWinner,-1,-1,a,b,c,d)) {
									if (winner != null && winner != pWinner) return "draw";
									else winner = pWinner;
								}
							}
						}
					}
				}
			}
		}
		return winner;
	}

	private Boolean checkInThisDirection(String pWinner, int acDir, int bdDir, int a, int b, int c, int d) {
		
//		a,b,c,d is the current Marble, as before in CheckForWinner(). (ac|bd)Dir is the direction to check.
//		(ac|bd)Dir is either -1, 0, or 1.
//		e.g. going Up (toward the origin): acDir = -1 and bdDir = 0
		
		for (int i = 0; i < target-1; i++) {
			
			//If c or d is at the end of a quad and continuing in that direction, adjust
			//a or b accordingly.
			
			if ((c == 0 && acDir == -1) || (c == quadHeight-1 && acDir == 1)) a += acDir;
			if ((d == 0 && bdDir == -1) || (d == quadHeight-1 && bdDir == 1)) b += bdDir;
			
			//The following means c and d will cycle up or down within the range allowed by quadHeight.
			//The % operation is fine for positives, but negatives need a while loop.		
			c += acDir; d += bdDir;
			if (c > 0) c %= quadHeight;
			while (c < 0) c += quadHeight;
			if (d > 0) d %= quadHeight;
			while (d < 0) d += quadHeight;
			
			if (board[a][b].grid[c][d].getMode() != pWinner) {
				return false;
			}
		}
		return true;
	}

	public void printAll(String attribute) {
		
//		This method is useful now for debugging or otherwise before graphics are implemented.
		
		String curString = "";
		
		for (int a = 0; a < boardHeight; a++) {
			for (int b = 0; b < quadHeight; b++) {
				for (int c = 0; c < boardHeight; c++) {
					for (int d = 0; d < quadHeight; d++) {
						if (attribute == "colors") {
							String mode = board[a][c].grid[b][d].getMode();
							if (mode == null) curString += "[   ] ";
							if (mode == "red") curString += "[red] ";
							if (mode == "white") curString += "[wht] ";
						}
					}
					if (c < boardHeight-1) curString += "| "; //adds space between vertical rows
				}
				System.out.println(curString);
				curString = "";
			}
			if (a < boardHeight-1) System.out.println("-------------------------------------");
			//adds space between horizontal rows. its length is meant for a 2x2 board
		}
	}
}