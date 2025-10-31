package game.model;
import game.movePatterns.MoveType;
import game.movePatterns.Linear;

public class Rook extends Piece implements MoveType {
    public Rook(PieceColor color, Position position) {
        super(color, position);
        this.name = "Rook";
    }
    private final Linear linearMove = Linear.getMove();

    @Override
    public boolean isValidMove(Position newPosition, Move move) {
        return linearMove.isValidMove(this.position, newPosition, move.getBoard());
        
    }
}
