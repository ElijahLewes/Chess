package game;

public class Pawn extends Piece {
    public Pawn(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        //terinary conditional expression that sets value of forwardDirection to -1, else set to 1
        int forwardDirection = color == PieceColor.WHITE ? 1 : -1;
        int rowDiff = newPosition.getRow() - position.getRow() * forwardDirection;
        int colDiff = newPosition.getColumn() - position.getColumn();


        //initial move 2 square move
        int absColDiff = Math.abs(colDiff);
        int absRowDiff = Math.abs(rowDiff);
        //If pawn is on starting space, and there is no piece in the way, allow 2 square move forward for either color
        boolean isAtStartingPosition = (color == PieceColor.WHITE && position.getRow() == 6) || (color == PieceColor.BLACK && position.getRow() == 1);
        if (absRowDiff == 2 && colDiff == 0 && isAtStartingPosition && board[newPosition.getRow()][newPosition.getColumn()] == null) {
            //Check square in between is empty
            int middleRow = position.getRow() + forwardDirection;
            if (board[middleRow][position.getColumn()] == null) {
                return true;
            }
        }
        //Diagonal Capture
        //Check for diagonal move of 1 square and presence of opponent piece
        if (absRowDiff == 1 && absColDiff == 1 && (board[newPosition.getRow()][newPosition.getColumn()] != null) && (board[newPosition.getRow()][newPosition.getColumn()].getColor() != this.color)) {
            return true;
        }
    
        return false;
    }
    
    /**
     * Validates the move and throws specific exceptions for different invalid move types
     * @param newPosition The target position
     * @param board The current board state
     * @throws InvalidMoveException with specific error message for the type of invalid move
     */
    public void validateMove(Position newPosition, Piece[][] board) throws InvalidMoveException {
        if (isValidMove(newPosition, board)) {
            return; // Move is valid
        }
        
        // Determine specific error based on the attempted move
        int forwardDirection = color == PieceColor.WHITE ? 1 : -1;
        int rowDiff = newPosition.getRow() - position.getRow() * forwardDirection;
        int colDiff = newPosition.getColumn() - position.getColumn();
        int absColDiff = Math.abs(colDiff);
        int absRowDiff = Math.abs(rowDiff);
        
        // Check if trying to move backwards
        if ((color == PieceColor.WHITE && newPosition.getRow() < position.getRow()) ||
            (color == PieceColor.BLACK && newPosition.getRow() > position.getRow())) {
            throw new InvalidMoveException("Pawns cannot move backwards.");
        }
        
        // Check if trying to move sideways without capturing
        if (absColDiff > 0 && board[newPosition.getRow()][newPosition.getColumn()] == null) {
            throw new InvalidMoveException("Pawns can only move sideways when capturing an opponent piece.");
        }
        
        // Check if trying to move more than 2 squares
        if (absRowDiff > 2) {
            throw new InvalidMoveException("Pawns cannot move more than 2 squares at once.");
        }
        
        // Check if trying to make 2-square move from non-starting position
        if (absRowDiff == 2 && colDiff == 0) {
            boolean isAtStartingPosition = (color == PieceColor.WHITE && position.getRow() == 6) || 
                                         (color == PieceColor.BLACK && position.getRow() == 1);
            if (!isAtStartingPosition) {
                throw new InvalidMoveException("Pawns can only move 2 squares from their starting position.");
            }
            if (board[newPosition.getRow()][newPosition.getColumn()] != null) {
                throw new InvalidMoveException("Cannot move 2 squares - target square is occupied.");
            }
            int middleRow = position.getRow() + forwardDirection;
            if (board[middleRow][position.getColumn()] != null) {
                throw new InvalidMoveException("Cannot move 2 squares - path is blocked.");
            }
        }
        
        // Check if trying to capture own piece
        if (absRowDiff == 1 && absColDiff == 1 && board[newPosition.getRow()][newPosition.getColumn()] != null &&
            board[newPosition.getRow()][newPosition.getColumn()].getColor() == this.color) {
            throw new InvalidMoveException("Cannot capture your own piece.");
        }
        
        // Generic pawn error for any other invalid moves
        throw new InvalidMoveException(ErrorMessages.PAWN_ERROR);
    }

}

