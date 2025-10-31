package game.model;
import game.movePatterns.MoveType;
import game.movePatterns.Jump;

public class Knight extends Piece implements MoveType {
    public Knight(PieceColor color, Position position) {
        super(color, position);
        this.name = "Knight";
    }
    private final Jump jumpMove = Jump.getMove();

    @Override
    public boolean isValidMove(Position newPosition, Move move) {
        return jumpMove.isValidMove(this.position, newPosition, move.getBoard());
    }
}
