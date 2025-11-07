package game.model;

/**
 * Initialize new chess game
 * 
 */
//TODO: Define players for any player-specific features or settings
public class ChessGame {
    private ChessBoard board;
    private boolean whiteTurn = true;

    public ChessGame() {
        board = new ChessBoard();
    }



//MAKING MOVES ------------------------
    /**
     * Make a move on the chessboard.   
     * Checks if it's the player's turn and if the move is valid.
     * Moves the piece if valid and switches turns.
     * @param start
     * @param end
     * @return
     * @throws InvalidMoveException
     * 
     */
    public boolean makeMove(Position start, Position end) {
        Piece movingPiece = board.getPiece(start.getRow(), start.getColumn());
        if (movingPiece == null || movingPiece.getColor() != (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)) {
            return false; // No piece at starting position or not player's turn
        }
        if(movingPiece.isValidMove (end, board.getBoard()) && !wouldMoveCauseCheck(movingPiece.getColor(), start, end) && isPositionValid(end)) {
            //Execute move
            try {
                board.movePiece(start, end);
                whiteTurn = !whiteTurn; // Switch turns
                return true;
            } catch (InvalidMoveException e) {
                return false;
            }
        }

        return false;

    } 

    /**
     * Check if a position is within the bounds of the chessboard   
     * 
     * @param position
     * if (mouseClicked on a square) { @return
     */
    private boolean isPositionValid(Position position) {
        return position.getRow() >= 0 && position.getRow() < board.getBoard().length &&
               position.getColumn() >= 0 && position.getColumn() < board.getBoard()[0].length;
    }

    /**
     * Check if moving a piece would put its own king in check
     * Temporarily makes the move, checks for check, then reverts the move
     * @param kingColor
     * @param from
     * @param to
     * @return
     */
    private boolean wouldMoveCauseCheck(PieceColor kingColor, Position from, Position to) {
        //Temporary move hold
        Piece temp = board.getPiece(to.getRow(), to.getColumn());
        //IDs the new position
        board.setPiece(to.getRow(), to.getColumn(),
        //IDs the piece being moved
        board.getPiece(from.getRow(), from.getColumn()));
        //Clears original position 
        board.setPiece(from.getRow(), from.getColumn(), temp);

        boolean inCheck = isInCheck(kingColor);
        board.setPiece(to.getRow(), to.getColumn(), board.getPiece(from.getRow(), from.getColumn()));
        board.setPiece(from.getRow(), from.getColumn(), temp);
        return inCheck;

    }




//CHECK AND CHECKMATE LOGIC ------------------------
    /**
     * Find the position of the king of the specified color
     * 
     * @param kingColor
     * @return Position of the king
     * @throws RuntimeException
     */
    private Position findKingPosition(PieceColor color) {
        for (int row =0; row < board.getBoard().length; row++) {
            for (int col =0; col < board.getBoard()[row].length; col++) {

                Piece piece = board.getPiece(row, col);
                if (piece != null && piece instanceof King && piece.getColor() == color) {
                    return new Position(row, col);
                }
            }
        }
        throw new RuntimeException("King not found on the board");
    }
     /**
      * Status check for if king is in Check
      * @param kingColor
      * @return
      */

    public boolean isInCheck(PieceColor kingColor) {
        Position kingPosition = findKingPosition(kingColor);
        //Iterate through all on the board to find opponent pieces
        for (int row = 0; row < board.getBoard().length; row++) {
            for (int col = 0; col < board.getBoard()[row].length; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null && piece.getColor() != kingColor) {
                    //Check if opponent piece can move to king's position
                    if (piece.isValidMove(kingPosition, board.getBoard())) {
                        return true; // King is in check
                    }
                }
            }

        } return false; // King is not in check
    }


    /**
     * Status check for Checkmate
     * Ensures king is in check
     * IDs king's position
     * iterates all possible single squre moves for the king, skipping current position
     * Check if move is within bounds
     * @param kingColor
     * @return
     */
    public boolean isCheckmate(PieceColor kingColor) {
        if (!isInCheck(kingColor)) {
            return false; // Not in check, so cannot be checkmate
        }
        Position kingPosition = findKingPosition(kingColor);
        //Iterate through all pieces of the king's color
        King king = (King) board.getPiece(kingPosition.getRow(), kingPosition.getColumn());
        //Check all possible moves for the king
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                // Skip the current position
                if (rowOffset == 0 && colOffset == 0) {
                Position newPosition = new Position(kingPosition.getRow() + rowOffset, kingPosition.getColumn() + colOffset);
                // Check if the new position is within bounds
                if (isPositionValid(newPosition)) {
                    // Temporarily move the king
                    board.movePiece(kingPosition, newPosition);
                    // Check if the king is in check
                    if (!isInCheck(kingColor)) {
                        // If the king is not in check return false
                        return false;
                    }
                    // Restore the position
                    board.movePiece(newPosition, kingPosition);
                
                }
                
                }
            }
        } return true; // No valid moves to escape check, so it's checkmate

    }

private Position selectPosition; // To track selected position for moves

public boolean isPieceSelected() {
    return selectPosition != null;
}


/**
 *  Handles square selection and piece movement logic   
 * @param row
 * @param col
 * @return boolean indicating if a move was made or moveMade
 * @calls getPiece() from ChessBoard to get piece at specific position
 * @calls makeMove() from ChessGame to attempt to make the move
 * @calls isPieceSelected() from ChessGame to check if a piece is selected
 */
public boolean handleSquareSelection(int row, int col) {
    if (selectedPosition == null) {
        Piece slectedPiece = board.getPiece(row, col);
        if (selectedPiece != null && selectedPiece.getColor() == (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)) {
            // Piece selected, waiting for destination
            selectedPosition = new Position(row, col);
            return false; 
        } else {
            //move selected piece to destination
            boolean moveMade = makeMove(selectedPosition, new Position (row, col));
            selectedPosition = null; // Clear selection after move attempt
            return moveMade;
        }
        return false; // No piece selected and no move made
    }



    public Piece[][] getBoardArray() {
        return board.getBoard();
    }

    public Piece getPiece(int row, int column) {
        return board.getPiece(row, column);
    }   

    public void setPiece(int row, int column, Piece piece) {
        board.setPiece(row, column, piece);
        if (piece != null) {
            piece.setPosition(new Position(row, column));
        }
    }

    /** getBoard provides access to current board state for the GUI
     * @return ChessBoard object representing current game state
     */
    public ChessBoard getBoard() {
        return this.board;
    }

/** resetGame resets the game to the initial state
     */
    public void resetGame() {
        this.board = new ChessBoard();
        this.whiteTurn = true;
    }
    /** modular way to access current player color */
    public PieceColor getCurrentPlayer() {
        return whiteTurn ? PieceColor.WHITE : PieceColor.BLACK;
    }





}