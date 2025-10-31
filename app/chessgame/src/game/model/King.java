package game.model;

public class King extends Piece {
    public King(PieceColor color, Position position) {
        super(color, position);
        this.name = "King";
    }       
    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int rowDiff = Math.abs(newPosition.getRow() - this.position.getRow());
        int colDiff = Math.abs(newPosition.getColumn() - this.position.getColumn());

        // King can move one square in any direction
       
        if (rowDiff <= 1 && colDiff <= 1){
            return true;
        }
        return false;

        }

    
}
