package game.model;
import game.movePatterns.MoveType;
import game.movePatterns.Diagonal;

public class Bishop extends Piece implements MoveType {
    public Bishop(PieceColor color, Position position) {
        super(color, position);
        this.name = "Bishop";
    }
    private final Diagonal diagonalMove = Diagonal.getMove();

    @Override
    public boolean isValidMove(Position newPosition, Move move) {
        return diagonalMove.isValidMove(this.position, newPosition, move.getBoard());
    }
}
