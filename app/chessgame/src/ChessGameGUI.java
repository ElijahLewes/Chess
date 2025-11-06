import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

// Import all classes from game.model package
import game.model.*;


/**
 * This class represents the graphical user interface, and literal window frame for a chess game.
 * It handles the display of the chessboard, pieces, and user interactions.
 * 
 * Extensds JFrame to create a windowed application.
 */



public class ChessGameGUI extends JFrame{
    private final ChessSquareComponent[][] squares = new ChessSquareComponent[8][8];
    private final ChessGame game = new ChessGame();


/** HashMap of Unicode icons for pieces */
    private final Map<Class<? extends Piece>, String> pieceUnicodeMap = new HashMap<>();{
        {
            put(Pawn.class, "\u2659");
            put(Rook.class, "\u2656");
            put(Knight.class, "\u2658");
            put(Bishop.class, "\u2657");
            put(Queen.class, "\u2655");
            put(King.class, "\u2654");
        }
    };

    public ChessGameGUI() {
    setTitle("Chess Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(8, 8));
    initializeBoard();
    pack(); //Adjusting window to fit components
    setVisible(true);
    }

    /**Associate each square with a mouse listener to handle clicks */
    private void initializeBoard(){
        for (int row = 0; row < squares.length; row++){
            for (int col = 0; col <squares[row].length; col++) {
                final int finalRow = row;
                final int finalCol = col;
                ChessSquareComponent sqaare = new ChessSqureCOmponent(row, col);
                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleSquareClick(finalRow, finalCol);
                    }
                });
                add(square);
                squares[row][col] = square;

            }
        }

    }   

    /** Refresh the board display. Iterates through each square, updating the board based on state of position.
     * @return void
     * @calls getBoard() from ChessGame to get current state of the board
     * @calls getPieceAt() from ChessBoard to get piece at specific position
     * @calls setPieceSymbol() from ChessSquareComponent to update the square display
     * @calls clearPieceSymbol() from ChessSquareComponent to clear the square display
     * TODO: implement ChessSquareComponent class?
    */

    private void refreshBoard(){
        ChessBoard board = game.getBoard();
        for (int row = 0; row < 8; row++)
        {
            for (int col = 0; col < 8; col++){
                Piece piece = board.getPieceAt(row, col); 
               if (piece != null){
                //Get the Unicode symbol for the piece
                String symbol = pieceUnicodeMap.get(piece.getClass());
                Color color = (piece.getColor() == PieceColor.WHITE) ? Color.WHITE : Color.BLACK;
                squares[row][col].setPieceSymbol(symbol, color);
               } else {
                squares[row][col].clearPieceSymbol();
                /** If I end up using other icons changes implemented here */
               }
            }
        }

}

/** Gets user input and connects it to game logic. Click could be to select a piece or move a piece. Updates board if move is valid.
 * Refers to ChessGame for game logic.
 * @param row The row index of the clicked square
 * @param col The column index of the clicked square
 * @return void 
 * @calls ChessGame.handleSquareClick() */

    private void handleSquareClick(int row, int col){
        if (game.handleSquareSelection(row, col)) {
            refreshBoard();
            checkGameState();
        
        }

    }

    private void checkGameState() {

    }
    
public static void main(String[] args) {
    SwingUtilities.invokeLater(ChessGameGUI::new);
    }
}