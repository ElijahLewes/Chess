package game;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
       this.board = new Piece[8][8];
        // Initialize pieces on the board
        startingPieces();
    }
    private void startingPieces(){
        // Define piece position
        // Rooks
        board[0][0] = new Rook(new Position(0, 0), PieceColor.BLACK);
        board[0][7] = new Rook(new Position(0, 7), PieceColor.BLACK);
        board[7][0] = new Rook(new Position(7, 0), PieceColor.WHITE);
        board[7][7] = new Rook(new Position(7, 7), PieceColor.WHITE);
        // Knights
        board[0][1] = new Knight(new Position(0, 1), PieceColor.BLACK);
        board[0][6] = new Knight(new Position(0, 6), PieceColor.BLACK);
        board[7][1] = new Knight(new Position(7, 1), PieceColor.WHITE);
        board[7][6] = new Knight(new Position(7, 6), PieceColor.WHITE);
        // Bishops
        board[0][2] = new Bishop(new Position(0, 2), PieceColor.BLACK);
        board[0][5] = new Bishop(new Position(0, 5), PieceColor.BLACK);
        board[7][2] = new Bishop(new Position(7, 2), PieceColor.WHITE);
        board[7][5] = new Bishop(new Position(7, 5), PieceColor.WHITE);
        // Queens
        board[0][3] = new Queen(new Position(0, 3), PieceColor.BLACK);
        board[7][3] = new Queen(new Position(7, 3), PieceColor.WHITE);
        // Kings
        board[0][4] = new King(new Position(0, 4), PieceColor.BLACK);
        board[7][4] = new King(new Position(7, 4), PieceColor.WHITE);
        // Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(new Position(1, i), PieceColor.BLACK);
            board[6][i] = new Pawn(new Position(6, i), PieceColor.WHITE);
        }
    }
//Define a move piece method that checks if move is possible and valid 
    /**
     * @param start
     * @param end
     * @throws InvalidMoveException
     */
    public void movePiece(Position start, Position end) throws InvalidMoveException {
        //Check if piece exists at starting position
        if (board[start.getRow()][start.getColumn()] == null) {
            throw new InvalidMoveException(ErrorMessages.NO_PIECE);
        }
        //Check if destination is empty 
        else if (board[start.getRow()][start.getColumn()].isValidMove(end, board)){
            //Move piece to new position
            board[end.getRow()][end.getColumn()] = board[start.getRow()][start.getColumn()];
            board[start.getRow()][start.getColumn()] = null;
            //Update piece position
            board[end.getRow()][end.getColumn()].setPosition(end);

            //Clear starting position for next move
            board[start.getRow()][start.getColumn()] = null;
        }
        //Exception for unavailable space
        else {
            throw new InvalidMoveException(ErrorMessages.SPACE_FULL);
        }
    
 }
}

