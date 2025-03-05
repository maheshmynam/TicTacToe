import java.util.Scanner;
import java.util.Random;
class TicTacToe{
	static char[][] board;
	public TicTacToe() {
		board = new char[3][3];
		initBoard();
	}
	void initBoard() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j] = ' ';
			}
		}
	}
	static void displayBoard() {

		System.out.println("-------------");
		for(int i=0;i<3;i++) {
			System.out.print("| ");
			for(int j=0;j<3;j++) {
				System.out.print(board[i][j]+" | ");
				
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	static void placeMark(int row,int column,char mark) {
		if(row>=0&&row<=2&&column>=0&&column<=2)
			board[row][column] = mark;
		else
			System.out.println("Invalid Position");
	}
	static boolean checkColumnWin() {
		for(int j=0;j<=2;j++) {
			if(board[0][j]!=' '&&board[0][j]==board[1][j]&&board[1][j]==board[2][j]) {
				return true;
			}
		}
		return false;
	}
	static boolean checkRowWin() {
		for(int i=0;i<=2;i++) {
			if(board[i][0]!=' '&&board[i][0]==board[i][1]&&board[i][1]==board[i][2]) {
				return true;
			}
		}
		return false;
	}
	static boolean checkDiagonalWin() {
		if(board[0][0]!=' '&&board[0][0]==board[1][1]&&board[1][1]==board[2][2]||board[0][2]!=' '&&board[0][2]==board[1][1]&&board[1][1]==board[2][0])
			return true;
		else
			return false;
	}
	static boolean checkDraw() {
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(board[i][j]==' ') {
					return false;
				}
			}
		}
		return true;
	}
}

abstract class Player{
	String name;
	char mark;
	abstract void makeMove();
	boolean isValidMove(int row,int column) {
		if(row>=0&&row<=2&&column>=0&&column<=2) {
			if(TicTacToe.board[row][column]==' ') {
				return true;
			}
		}
		return false;
	}
}

class HumanPlayer extends Player{
	HumanPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		Scanner sc=new Scanner(System.in);
		int row,column;
		do {
			System.out.println("Enter the row and col");
			row=sc.nextInt();
			column=sc.nextInt();
		}while(!isValidMove(row,column));
		TicTacToe.placeMark(row, column, mark);
		
	}
}

class AiPlayer extends Player{
	AiPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		Scanner sc=new Scanner(System.in);
		int row,column;
		do {
			Random r=new Random();
			row=r.nextInt(3);
			column=r.nextInt(3);
		}while(!isValidMove(row,column));
		TicTacToe.placeMark(row, column, mark);
		
	}
}

public class LaunchGame {

	public static void main(String[] args) {
		TicTacToe t = new TicTacToe();
		HumanPlayer person1=new HumanPlayer("Sai",'X');
		AiPlayer person2=new AiPlayer("AI",'O');
		Player cp;
		cp=person1;
		while(true) {
			System.out.println(cp.name+" turn");
			cp.makeMove();
			TicTacToe.displayBoard();
			if(TicTacToe.checkColumnWin()||TicTacToe.checkRowWin()||TicTacToe.checkDiagonalWin()) {
				System.out.println(cp.name+" has won");
				break;
			}
			else if(TicTacToe.checkDraw()) {
				System.out.println("Game is a draw");
				break;
			}
			else {
				if(cp==person1) {
					cp=person2;
				}
				else {
					cp=person1;
				}
			}
		}
	}

}

