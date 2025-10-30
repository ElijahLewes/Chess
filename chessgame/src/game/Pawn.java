package game;

/**
 * Represents pawn piece
 * Validates 2 square first move
 * Checks if forward space is available
 * Validates forward-diagonal capture
 * @see Piece
 * @see Position    
 * 
 */

 //TODO: Add castling function to create new instance of desired piece

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

}

