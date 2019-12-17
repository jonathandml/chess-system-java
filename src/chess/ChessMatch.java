package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	Board board;

	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	private void initialSetup() {
		//Placing Rooks
		this.board.placePiece(new Rook(this.board, Color.BLACK), new Position(0, 0));
		this.board.placePiece(new Rook(this.board, Color.BLACK), new Position(0, 7));
		this.board.placePiece(new Rook(this.board, Color.WHITE), new Position(7, 0));
		this.board.placePiece(new Rook(this.board, Color.WHITE), new Position(7, 7));
		//Placing Kings
		this.board.placePiece(new King(this.board, Color.BLACK), new Position(0, 4));
		this.board.placePiece(new King(this.board, Color.WHITE), new Position(7, 4));
	}
	
}
