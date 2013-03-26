package pentago;

import java.util.Arrays;

public class XTesting {

	public static void main(String[] args) {
		
		Board main = new Board();
		
		main.board[0][0].grid[0][2].setMode("white");
		main.board[1][0].grid[1][2].setMode("white");
		main.board[1][1].grid[2][2].setMode("white");
		main.board[0][0].grid[2][2].setMode("red");
		main.board[0][0].grid[0][2].setMode("red");
		main.board[0][1].grid[1][0].setMode("red");
		main.board[0][1].grid[0][1].setMode("red");
		main.board[1][0].grid[0][1].setMode("red");
		main.board[1][0].grid[1][0].setMode("red");
		
		main.printAll("colors");
		System.out.println("");
		System.out.println(main.checkForWinner());
		
		main.board[0][0].grid[2][2].setMode("white");
		main.board[1][1].grid[0][0].setMode("white");
		main.board[1][1].grid[1][1].setMode("white");
		main.board[0][0].grid[1][1].setMode("white");
		System.out.println("");
		main.printAll("colors");
		System.out.println("");
		System.out.println(main.checkForWinner());
		
		main.board[0][0].grid[0][1].setMode("red");
		main.board[0][1].grid[0][0].setMode("red");
		main.board[0][1].grid[0][2].setMode("red");
		System.out.println("");
		main.printAll("colors");
		System.out.println("");
		System.out.println(main.checkForWinner());
		
	}
}