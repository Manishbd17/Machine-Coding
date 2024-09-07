package tictactoe;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import tictactoe.model.Board;
import tictactoe.model.PieceType;
import tictactoe.model.Player;
import tictactoe.model.PlayingPieceO;
import tictactoe.model.PlayingPieceX;

public class GameRunner {
	
	Deque<Player> players;
	Board board;
	
	public void initializeGame() {
		players = new LinkedList<>(); 
		PlayingPieceX crossPiece = new PlayingPieceX();
		Player player1 = new Player("Player 1",crossPiece); 
		
		PlayingPieceO noughtPiece = new PlayingPieceO(); 
		Player player2 = new Player("Player 2",noughtPiece);
		
		players.add(player1);
		players.add(player2); 
	
		board = new Board(3); 
	}
	
	public String startGame() {
		boolean noWinner = true; 
		while(noWinner) {
		//take the player out whose turn is, and put in the back
			Player playerTurn = players.removeFirst(); 
			
		//get the free space from the board 
			board.printBoard(); 
			List<Pair<Integer,Integer>> freeSpaces = board.getFreeCells(); 
			if(freeSpaces.isEmpty()) {
				noWinner = false;
				continue; 
			}
		
		//read the user input
			System.out.println("Player: "+playerTurn.getName() +"Enter the row and column: ");
			Scanner sc=new Scanner(System.in);
			String res=sc.nextLine(); 
			String[] values = res.split(",");
			int row = Integer.parseInt(values[0]);
			int col = Integer.parseInt(values[1]); 
		
		//place the piece
			boolean pieceAddedSuccessfully = board.addPiece(row, col, playerTurn.getPiece());
			if(!pieceAddedSuccessfully) {
				System.out.println("Incorrect Position chosen , please try again");
				players.addFirst(playerTurn);
				continue; 
			}
			players.addLast(playerTurn);
			boolean winner = isThereWinner(row,col,playerTurn.getPiece().piece); 
			if(winner) {
				return playerTurn.getName(); 
			}
		}
		return "tie"; 
	}
	
	public boolean isThereWinner(int row,int col,PieceType piece) {
		boolean rowMatch = true; 
		boolean colMatch = true; 
		boolean diagonalMatch = true; 
		boolean antiDiagonalMatch = true; 
		
		for(int i=0;i<board.size;i++) {
			if(board.board[row][i]!=null && board.board[row][i].piece!=piece) {
				rowMatch = false; 
			}
		}
		
		for(int i=0;i<board.size;i++) {
			if(board.board[i][col]!=null && board.board[i][col].piece!=piece) {
				colMatch = false; 
			}
		}
		
		for(int i=0,j=0;i<board.size;i++,j++) {
			if(board.board[i][j]!=null && board.board[i][j].piece!=piece) {
				diagonalMatch=false; 
			}
		}
		
		for(int i=0,j=board.size-1;i<board.size;i++,j--) {
			if(board.board[i][j]!=null && board.board[i][j].piece!=piece) {
				antiDiagonalMatch=false; 
			}
		}
		return rowMatch || colMatch || diagonalMatch || antiDiagonalMatch ; 
	}
	
	//initializeGame
	//startGame
	//isThereWinner
}
