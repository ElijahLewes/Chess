package game.model;

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
    protected String name;

    public Piece(Position position, PieceColor color, String name) {
        this.position = position;
        this.color = color;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Abstract method required to be implemented to validate moves for each piece
    public abstract boolean isValidMove(Position newPosition, Piece[][] board);


}
