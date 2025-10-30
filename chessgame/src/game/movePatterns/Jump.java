package game.movePatterns;

<<<<<<< HEAD:chessgame/src/game/movePatterns/Jump.java
public class Jump implements MoveType {

private static Jump move = null;

    @Override
    public boolean isValidMove(Position start, Position end, game.movePatterns.Piece[][] board) {
        int rowDiff = end.getRow() - start.getRow();
        int colDiff = end.getColumn() - start.getColumn();

        // If new position is same row || same column
        // If rowDiff !=0 && colDiff !== return false
        if (rowDiff != 0 && colDiff != 0) {
            return false;
        }
    int i = 0;
       if (rowDiff != 0) {
           for (i = start.getRow() + 1; i <= end.getRow(); i++) {
               if (board[i][start.getColumn()] != null) {
                   return false;
               }
           }
       } else if (colDiff != 0) {
           for (i = start.getColumn() + 1; i < end.getColumn(); i++) {
               if (board[start.getRow()][i] != null) {
                   return false;
               }
           }
       }

       return true;
    }


    public static Jump getMove() {
        if (move == null) {
            move = new Jump();
        }
        return move;
    }
     
    public static void resetMove() {
        move = null;
    }

}
=======

/**
 * Represents a rook chess piece.
 * The rook can move any number of squares along a row or column.
 * It cannot jump over other pieces.
 * @see Piece
 * @see Position
 * @see InvalidMoveException
 * @see PieceColor
 * @see Board
 * @see Move
 * @see Player
 * @see Game
 */
public class Rook extends Piece{

    @Override
    public boolean isValidMove(Position start, Position end, Piece[][] board) {

        //Check if rook moves vertically
        if (position.getRow() == end.getRow()) {
            int columnStart = Math.min(position.getColumn(), end.getColumn()) + 1;
            int columnEnd = Math.max(position.getColumn(), end.getColumn());
            for (int col = columnStart; col < columnEnd; col++) {
                if (board[position.getRow()][col] != null) {
                    return false; // there is a piece in the way
                    //TODO: Come back and check for capturing logic based on color
                }
            }
        } else if (position.getColumn() == newPosition.getColumn()){
            int rowStart = Math.min(position.getRow(), end.getRow()) + 1;
            int rowEnd = Math.max(position.getRow(), end.getRow());
            for (int row = rowStart; row < rowEnd; row++) {
                if (board[row][position.getColumn()] != null) {
                    return false; // there is a piece in the way
                }
            }
        }

        else {
            throw new InvalidMoveException("Invalid move for Rook.");
            return false; // Rook can only move in straight lines
        }


        //Check for potential capture on destination square
        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        if (destinationPiece != null && destinationPiece.getColor() == this.getColor()) {
            return false; // Cannot capture own piece
        } else if (destinationPiece != null && destinationPiece.getColor() != this.getColor()) {
            return true; // Valid Capture
        }
        
        throw new UnsupportedOperationException("Unimplemented method 'isValidMove'. Unexpected case reached in Rook.java");
        return false;
    }
}


>>>>>>> main:chessgame/src/game/Rook.java
