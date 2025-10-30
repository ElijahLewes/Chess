package game.movePatterns;

public class Jump implements MoveType {

private static Jump move = null;

    @Override
    public boolean isValidMove(Position start, Position end, game.movePatterns.Piece[][] board) {
        int rowDiff = end.getRow() - start.getRow();
        int colDiff = end.getColumn() - start.getColumn();

        // If new position is same row || same column
        // If rowDiff !=0 && colDiff !== return false
        if (rowDiff != 0 && colDiff != 0) {
            return false;
        }
    int i = 0;
       if (rowDiff != 0) {
           for (i = start.getRow() + 1; i <= end.getRow(); i++) {
               if (board[i][start.getColumn()] != null) {
                   return false;
               }
           }
       } else if (colDiff != 0) {
           for (i = start.getColumn() + 1; i < end.getColumn(); i++) {
               if (board[start.getRow()][i] != null) {
                   return false;
               }
           }
       }

       return true;
    }


    public static Jump getMove() {
        if (move == null) {
            move = new Jump();
        }
        return move;
    }
     
    public static void resetMove() {
        move = null;
    }

}