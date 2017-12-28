package chess;

import java.util.*;
import chess.Main.Color;

public class State {
	// char array board representation
	public char[][] board;
	
	// king positions for win checking
	public Vector blackKing;
	public Vector whiteKing;
	
	// construct state from board
	public State (char[][] _board) {
		this.board = Util.boardCopy(_board);	// make copy of board
		
		// find king positions
		for (int r = 0; r < this.board.length; r++) {
			for (int c = 0; c < this.board[r].length; c++) {
				char piece = this.board[r][c];
				if (piece == Main.BLACK_KING) {
					this.blackKing = new Vector(r, c);
				} else if (piece == Main.WHITE_KING) {
					this.whiteKing = new Vector(r, c);
				}
			}
		}
	}

	// construct state from a previous state with a move being made
	public State(State prevState, Move mv) {
		this.board = Util.boardCopy(prevState.board);	// copy previous board
		this.whiteKing = prevState.whiteKing.copy();	// copy previous king positions
		this.blackKing = prevState.blackKing.copy();
		
		this.makeMove(mv);
	}
	
	// get the piece at a given position on this board
	public char pieceAt(Vector pos) {
		return this.board[pos.row][pos.col];
	}
	
	// alter this state to reflect a move
	public void makeMove(Move mv) {
		Vector from = mv.from, to = mv.to;	// get to and from positions
		char fromPiece = this.board[from.row][from.col];	// get piece to move
		
		// update king positions if necessary
		if (fromPiece == Main.BLACK_KING) {
			this.blackKing = to;
		} else if (fromPiece == Main.WHITE_KING) {
			this.whiteKing = to;
		}
		
		// move piece
		this.board[from.row][from.col] = Main.NULL_CHAR;
		this.board[to.row][to.col] = fromPiece;
	}
	
	// check if state is a win state
	public boolean isWin() {
		// fill this out
		return true;
	}

	// get all available legal moves for a given position in this state
	public ArrayList<Move> getAllPossibleMoves(Vector position) {
		
		char piece = this.pieceAt(position);
		ArrayList<Move> moves = new ArrayList<Move>();
		Color oppositeColor = Util.getColor(piece) == Color.BLACK ? Color.WHITE : Color.BLACK;
		int side = oppositeColor == Color.BLACK ? 1 : -1;
		
		Vector movePosition;
		Move mv;
		
		if (Util.isPawn(piece)) {
			// for every pawn move
			for (Vector delta : Main.pawnMoves) {
				movePosition = position.add(delta.scalarMult(side));
				
				if (movePosition.isLegalPosition()) {
					
					char movePiece = this.pieceAt(movePosition);
					
					// if horizontal move
					if (delta.row == 0) {
						// if one forward and space empty
						if (Math.abs(delta.col) == 1 && movePiece == Main.NULL_CHAR) {
							mv = new Move(position, movePosition);
							
							// finally if move does not lead to check
							if (this.moveMeetsCheckConstraint(mv)) {
								moves.add(mv);
							}
							
						// if two forward and first move
						} else if (Math.abs(delta.col) == 2 && (position.col == 1 || position.col == 6)) {
							Vector forward = new Vector(0, 1);
							forward = forward.scalarMult(side);
							Vector oneAhead = position.add(forward);
							
							// if both space directly in front and two ahead are free
							if (
									movePiece == Main.NULL_CHAR &&
									this.board[oneAhead.row][oneAhead.col] == Main.NULL_CHAR
								) {
								
								mv = new Move(position, movePosition);
								if (this.moveMeetsCheckConstraint(mv)) {	// check check constraint
									moves.add(mv);
								}
							}
						}

					// if diagonal move
					} else {
						// if attacking, allow move
						if (Util.getColor(this.pieceAt(movePosition)) == oppositeColor) {
							mv = new Move(position, movePosition);
							
							if (this.moveMeetsCheckConstraint(mv)) {
								moves.add(mv);
							}
						}
					}
				}
			}
			
		} else if (Util.isKnight(piece)) {
			// for every knight move
			for (Vector delta : Main.knightMoves) {
				movePosition = position.add(delta);
				// if legal position
				if (movePosition.isLegalPosition()) {
					char movePiece = this.pieceAt(movePosition);
					// if space empty or opposite color
					if (movePiece == Main.NULL_CHAR || Util.getColor(movePiece) == oppositeColor) {
						mv = new Move(position, movePosition);
						if (this.moveMeetsCheckConstraint(mv)) {	// check check constraint
							moves.add(mv);
						}
					}
				}
			}
			
		} else if (Util.isNMove(piece) || Util.isKing(piece)) {
			
			ArrayList<Vector> possibleDeltas = new ArrayList<Vector>();
			if (Util.isRook(piece) || Util.isQueen(piece) || Util.isKing(piece)) {
				possibleDeltas.addAll(Arrays.asList(Util.vectorArrayCopy(Main.axisMoves)));
			}
			if (Util.isBishop(piece) || Util.isQueen(piece) || Util.isKing(piece)) {
				possibleDeltas.addAll(Arrays.asList(Util.vectorArrayCopy(Main.diagMoves)));
			}
			
			while (possibleDeltas.size() > 0) {
				
				ListIterator<Vector> iter = possibleDeltas.listIterator();
				while (iter.hasNext()) {
					Vector delta = iter.next();
					movePosition = position.add(delta);
					mv = new Move(position, movePosition);
					
					if (
							movePosition.isLegalPosition() && 
							(this.pieceAt(movePosition) == Main.NULL_CHAR || Util.getColor(this.pieceAt(movePosition)) == oppositeColor) && 
							this.moveMeetsCheckConstraint(mv)
						) {
						
						moves.add(mv);	// accept move
						
						//  if more room to search and piece able to move further (not king)
						if (this.pieceAt(movePosition) == Main.NULL_CHAR && !Util.isKing(piece)) {
							delta.increment();
						} else {
							iter.remove();
						}
					
					} else {
						iter.remove();
					}
				}
			}
		}
		
		return moves;
	}
	
	// determine whether a position is being threatened on this state
	public boolean isThreatened(Vector position) {
		
		char piece = this.board[position.row][position.col];
		Color oppositeColor = Util.getColor(piece) == Color.BLACK ? Color.WHITE : Color.BLACK;
		int side = oppositeColor == Color.BLACK ? 1 : -1;
		
		Vector possibleThreat;
		char threatPiece;
		
		// for every pawn attacking move
		for (Vector delta : Main.pawnAttacks) {
			possibleThreat = position.add(delta.scalarMult(side));
			
			if (possibleThreat.isLegalPosition()) {
				
				threatPiece = this.board[possibleThreat.row][possibleThreat.col];
				
				if (Util.isPawn(threatPiece) && Util.getColor(threatPiece) == oppositeColor) {
					System.out.print("PAWN THREAT FOUND @ "); // debug
					possibleThreat.log();
					return true;
				}
			}
		}
		
		// for every knight move
		for (Vector delta : Main.knightMoves) {
			possibleThreat = position.add(delta);
			
			if (possibleThreat.isLegalPosition()) {
				
				threatPiece = this.board[possibleThreat.row][possibleThreat.col];
				
				if (Util.isKnight(threatPiece) && Util.getColor(threatPiece) == oppositeColor) {
					System.out.print("KNIGHT THREAT FOUND @ ");
					possibleThreat.log();
					return true;
				}
			}
		}
		
		// handle nmove threats
		ArrayList<Vector> remainingDeltas = new ArrayList<Vector>();
		remainingDeltas.addAll(Arrays.asList(Util.vectorArrayCopy(Main.axisMoves)));
		remainingDeltas.addAll(Arrays.asList(Util.vectorArrayCopy(Main.diagMoves)));
		
		while (remainingDeltas.size() > 0) {
			
			ListIterator<Vector> iter = remainingDeltas.listIterator();
			while(iter.hasNext()) {
				Vector delta = iter.next();
				
				possibleThreat = position.add(delta);
				
				if (possibleThreat.isLegalPosition()) {
					
					threatPiece = this.board[possibleThreat.row][possibleThreat.col];
					
					if (threatPiece != Main.NULL_CHAR) {
						
						if (Util.getColor(threatPiece) == oppositeColor) {
						
							// if directly next to piece & king
							if (Util.isKing(threatPiece) && (Math.abs(delta.row) == 1 || Math.abs(delta.col) == 1)) {
								System.out.print("KING THREAT FOUND @ ");
								possibleThreat.log();
								return true;
							}
							
							// if nmove
							if (Util.isNMove(threatPiece)) {
								if (
										(Util.isQueen(threatPiece)) ||
										(Util.isRook(threatPiece) && Util.onSameAxis(position, possibleThreat)) ||
										(Util.isBishop(threatPiece) && Util.onSameDiag(position, possibleThreat))
								) {
									System.out.print("NMOVE THREAT AT ");
									possibleThreat.log();
									return true;
								}
							}
						}
						
						// remove since piece hit
						iter.remove();
						
					} else {
						delta.increment();
					}
					
				} else {
					// remove from remaining deltas if out of bounds
					iter.remove();
				}
			}
		}
		return false;
	}
	
	// determine whether a move puts its king in check
	public boolean moveMeetsCheckConstraint(Move mv) {
		Color pieceColor = Util.getColor(this.board[mv.from.row][mv.from.col]);
		Vector relevantKing = pieceColor == Color.BLACK ? this.blackKing : this.whiteKing;
		
		// if previously potentially protecting king
		if (Util.onSameLineOfThreat(mv.from, relevantKing)) {
			State moveState = new State(this, mv);
			Vector relKingInMove = pieceColor == Color.BLACK ? moveState.blackKing : moveState.whiteKing;
			
			// return whether king is threatened as result of move
			return !moveState.isThreatened(relKingInMove);
		} else {
			return true;
		}
	}
	
	public void log() {
		System.out.print("\n  ");
		// DEBUG
		for (int j = 0; j < Main.BOARD_DIMENSIONS; j++) {
			System.out.print(j + " ");
		}
		System.out.println("");
		
		int i = 0;
		for (char[] row : this.board) {
			System.out.print(i++);
			for (char c : row) {
				if (c == Main.NULL_CHAR) {
					System.out.print("_ ");
				} else {
					System.out.print(c + " ");
				}
			}
			System.out.println("");
		}	
	}

}
