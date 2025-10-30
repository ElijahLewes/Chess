package game;

public class Rook extends Piece{

    @Override
    public boolean isValidMove(Position start, Position end, Piece[][] board) {
        int rowDiff = end.getRow() - start.getRow();
        int colDiff = end.getColumn() - start.getColumn();

        If newpostion is same row || same column 
        // If rowDiff !=0 && colDiff !== return false
        and

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

}

//move indefinately along any row or column without jumping

//Detect other pieces in the way