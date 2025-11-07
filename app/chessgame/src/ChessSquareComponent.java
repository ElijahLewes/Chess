import javax.swing.*;
import java.awt.*;

/**
 * Custom JButton component representing a single square on the chess board.
 * Each square knows its position and can display chess piece symbols.
 */
public class ChessSquareComponent extends JButton {
    private final int row;
    private final int col;
    private String pieceSymbol = "";
    
    /**
     * Creates a new chess square component
     * @param row The row position on the board (0-7)
     * @param col The column position on the board (0-7)
     */
    public ChessSquareComponent(int row, int col) {
        this.row = row;
        this.col = col;
        
        // Set up the appearance
        setPreferredSize(new Dimension(80, 80));
        setFont(new Font("Arial Unicode MS", Font.PLAIN, 40));
        setFocusable(false);
        setBorderPainted(true);
        
        // Create checkerboard pattern
        if ((row + col) % 2 == 0) {
            setBackground(new Color(240, 217, 181)); // Light squares
        } else {
            setBackground(new Color(181, 136, 99));  // Dark squares
        }
        
        setOpaque(true);
    }
    
    /**
     * Sets the piece symbol and color for this square
     * @param symbol Unicode symbol for the piece
     * @param color Color of the piece (WHITE or BLACK)
     */
    public void setPieceSymbol(String symbol, game.model.PieceColor color) {
        this.pieceSymbol = symbol;
        setText(symbol);
        
        // Set text color based on piece color
        if (color == game.model.PieceColor.WHITE) {
            setForeground(Color.WHITE);
        } else {
            setForeground(Color.BLACK);
        }
    }
    
    /**
     * Clears any piece symbol from this square
     */
    public void clearPieceSymbol() {
        this.pieceSymbol = "";
        setText("");
    }
    
    /**
     * Gets the current piece symbol
     * @return The Unicode symbol of the piece, or empty string if no piece
     */
    public String getPieceSymbol() {
        return pieceSymbol;
    }
    
    /**
     * Gets the board row of this square
     * @return Row position (0-7)
     */
    public int getBoardRow() {
        return row;
    }
    
    /**
     * Gets the board column of this square
     * @return Column position (0-7)
     */
    public int getBoardCol() {
        return col;
    }
    
    /**
     * Highlights this square (for showing selected piece or valid moves)
     */
    public void highlight() {
        setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
    }
    
    /**
     * Removes highlight from this square
     */
    public void removeHighlight() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
}