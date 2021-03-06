package chess;

import boardgame.Board;
import boardgame.Piece;
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
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source,target);
		return (ChessPiece) capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = this.board.removePiece(source);
		Piece capturedPiece = this.board.removePiece(target);
		this.board.placePiece(p, target);
		return capturedPiece;
		
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece in the source position");
		}
		
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece.");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if(!this.board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece connot be moved to the target position");
		}
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void initialSetup() {
		//Placing Rooks
		placeNewPiece('a', 8, new Rook(this.board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(this.board, Color.BLACK));
		placeNewPiece('a', 1, new Rook(this.board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(this.board, Color.WHITE));
		//Placing Kings
		placeNewPiece('e', 8, new King(this.board, Color.BLACK));
		placeNewPiece('e', 1, new King(this.board, Color.WHITE));
	}
	
}
