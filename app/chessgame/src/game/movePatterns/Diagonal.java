package game.movePatterns;

import game.model.Piece;
import game.model.PieceColor;

/** Represents Diagonal move pattern
 * Tradtional pattern for Bishops and Queens.
*/

public class Diagonal implements MoveType {

private static Diagonal move = null;

     
    @Override
    public boolean isValidMove(Position start, Position end, Piece[][] board){
        int rowDist = Math.abs(start.getRow() - end.getRow());
        int colDist = Math.abs(start.getColumn() - end.getColumn());
        if (colDist != rowDist) {
            throw new InvalidMoveException("Invalid move for Bishop. Must move diagonally.");
        } else if (board[end.getRow()][end.getColumn()] != null) {

            //Check if spaces between start and end are empty
                 for (int i = Math.min(start.getRow(), end.getRow()) + 1; i < Math.max(start.getRow(), end.getRow()); i++) {
                    for (int j = Math.min(start.getColumn(), end.getColumn()) + 1; j < Math.max(start.getColumn(), end.getColumn()); j++) {
                        if (board[i][j] != null) {
                            return false;
                            throw new InvalidMoveException("There is a piece in the way.");
                        }
                    }
                }
                
            if (board[end.getRow()][end.getColumn()].getColor() == this.getColor()) {
                throw new InvalidMoveException("Cannot capture your own piece.");
                return false; // Cannot capture own piece
            }
        }
        return true;
    }



    public static Diagonal getMove() {
        if (move == null) {
            move = new Diagonal();
        }
        return move;
    }
     
    public static void resetMove() {
        move = null;
    }
    
}
