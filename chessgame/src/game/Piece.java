package game;

/**
 * Abstract class representing a chess piece.
 * Each piece has a position and a color, and must implement the isValidMove method to validate its moves.
 * @see Position
 * @see PieceColor
 * @see Board
 * @see Move
 * @see Player
 * @see Game
 */

public abstract class Piece {
    protected Position position;
    protected PieceColor color;

    public Piece(Position position, PieceColor color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }
     public void setPosition(Position position) {
        this.position = position;
    }

    public PieceColor getColor() {
        return color;
    }

    // Abstract method required to be implemented to validate moves for each piece
    public abstract boolean isValidMove(Position newPosition, Piece[][] board);


}
