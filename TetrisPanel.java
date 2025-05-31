import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TetrisPanel extends JPanel {
    int horizontalLines, verticalLines, tileSize;
    Sprite[] currentTetrominoes;

    public TetrisPanel(int horizontalLines, int verticalLines, int tileSize) {
        super();

        // Adjusting JPanel
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(horizontalLines * tileSize, verticalLines * tileSize));

        // Initializing Params
        this.horizontalLines = horizontalLines;
        this.verticalLines = verticalLines;
        this.tileSize = tileSize;

        // Initializing Tetrominoes
        currentTetrominoes = new Sprite[1];
        currentTetrominoes[0] = new ISprite(5, 5);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Drawing Grid
        for (int row = 0; row < verticalLines; row++) {
            int x = (int) row * tileSize;
            g.drawLine(x, 0, x, getHeight());
        }

        for (int col = 0; col < verticalLines; col++) {
            int y = (int) col * tileSize;
            g.drawLine(0, y, getWidth(), y);
        }

        // Drawing Parts
        for (Sprite tetromino : currentTetrominoes) {
            for (Tile tile : tetromino.getTileList()) {
                int x = tile.x * tileSize;
                int y = tile.y * tileSize;

                g.setColor(tetromino.getColor());
                g.fillRect(x, y, tileSize, tileSize);
            }
        }
    }
}
