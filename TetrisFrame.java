import javax.swing.JFrame;

public class TetrisFrame extends JFrame {
    // Customizable
    final static String TITLE = "Tetris";
    final int HORIZONTAL_LINES = 10;
    final int VERTICAL_LINES = 20;
    final int TILE_SIZE = 40;
    
    public TetrisFrame() {
        super(TITLE);

        // Adjusting JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Adding JPanel
        add(new TetrisPanel(HORIZONTAL_LINES, VERTICAL_LINES, TILE_SIZE));

        // Finalizing...
        pack();
        setVisible(true);
    }
}