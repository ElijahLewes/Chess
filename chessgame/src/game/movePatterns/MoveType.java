package game.movePatterns;

public interface MoveType {
    public boolean move(Position start, Position end, Piece[][] board);
}  
