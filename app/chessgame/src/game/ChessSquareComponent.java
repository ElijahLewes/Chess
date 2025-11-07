import javax.swing.*;
import java.awt.*;

package game;

/** Implements each square as a button 
 * Constructor for chess square component
 * Defines appearance and methods to set/clear piece symbols
 * Font stiling and sizing
 * Background color based on position to create checkerboard pattern
 * 
*/

public class ChessSquareComponent extends JButton {
    private int row;
    private int col;

    public ChessSquareComponent(int row, int col) {
        this.row = row;
        this.col = col;
        initButton();
    }
private void initButton(){
    // Set preferred size
    setPreferredSize(new Dimension(64, 64));

    //background colors based on row and column to create a checkerboard pattern
    if ((row + col) % 2 == 0) {
        setBackground(Color.LIGHT_GRAY);
    } else {
        setBackground(Color.WHITE);
    }

    //Ensure text (chess piece Unicode symbol) is centered
    setHorizontalAlignment(SwingConstants.CENTER);
    setVerticalAlignment(SwingConstants.CENTER);

    //Font settings
    setFont(new Font("Serif", Font.BOLD, 36));

}
    public void setPieceSymbol(String symbol, Color color) {
        setText(symbol);
        setForeground(color);
    }
    public void clearPieceSymbol() {
        setText("");
    }

    
}
