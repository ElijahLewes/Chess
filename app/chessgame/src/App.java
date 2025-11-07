import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) throws Exception {
        // Launch the Chess GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new ChessGameGUI().setVisible(true);
        });
    }
}
