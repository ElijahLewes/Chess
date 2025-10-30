package game.model;
import game.movePatterns.MoveType;
import game.movePatterns.Linear;
import game.movePatterns.Diagonal;
/**
 * Class representing a Queen chess piece.
 * Used as a wrapper class to create composition of Rook and Bishop movement patterns.
 * @see Piece
 * @see Rook
 * @see Bishop
 */
public class Queen extends Piece implements MoveType {
    public Queen(PieceColor color, Position position) {
        super(color, position);
        this.name = "Queen";
    }

    private final Linear linearMove = Linear.getMove();
    private final Diagonal diagonalMove = Diagonal.getMove();

    @Override
    public boolean isValidMove(Position newPosition, Move move) {
        return linearMove.isValidMove(this.position, newPosition, move.getBoard()) ||
               diagonalMove.isValidMove(this.position, newPosition, move.getBoard());
    }
}
