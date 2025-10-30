package game;
class ErrorMessages {
    public static final String SPACE_FULL = "There is already a piece there. Try again.";
    public static final String SAME_POSITION = "The starting and ending positions are the same.";
    public static final String NO_PIECE = "There is no piece at the starting position.";
    public static final String PAWN_ERROR = "Invalid move for a Pawn.";
    public static final String KNIGHT_ERROR = "Invalid move for a knight";
}

class InvalidMoveException extends Exception {
    public InvalidMoveException( String message) {
        super(message);
    }
}