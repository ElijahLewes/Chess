package game.model;
class ErrorMessages {
    public static final String SPACE_FULL = "There is already a piece there. Try again.";
    public static final String NO_PIECE = "There is no piece at the starting position.";
    public static final String PAWN_ERROR = "Invalid move for a Pawn.";
}

class InvalidMoveException extends Exception {
    public InvalidMoveException( String message) {
        super(message);
    }
}