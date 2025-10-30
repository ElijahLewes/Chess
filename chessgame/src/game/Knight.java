package game;


public class Knight extends Piece {
    public Knight(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position start, Position end, Piece[][] board) {
        int rowDiff = Math.abs(this.position.getRow() - end.getRow());
        int colDiff = Math.abs(this.position.getColumn() - end.getColumn());
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            // Check if the destination square is occupied by a piece of the same color
            Piece destinationPiece = board[end.getRow()][end.getColumn()];
            if (destinationPiece != null && destinationPiece.getColor() == this.getColor()) {
                throw new InvalidMoveException("Cannot capture your own piece");
                return false; // Cannot capture own piece
            }
            return true; // Valid move
        }
        throw new InvalidMoveException(ErrorMessages.KNIGHT_ERROR);
        return false;
    }
}