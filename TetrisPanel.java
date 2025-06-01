import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisPanel extends JPanel implements KeyListener {
    int horizontalLines, verticalLines, tileSize;
    ArrayList<Sprite> placedTetrominoesList;
    Sprite[] tetrominoes;
    Sprite currentTetromino;

    // Game Loop
    Timer timer;

    public TetrisPanel(int horizontalLines, int verticalLines, int tileSize) {
        super();

        // Adjusting JPanel
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(horizontalLines * tileSize, verticalLines * tileSize));
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow(true);

        // Initializing Params
        this.horizontalLines = horizontalLines;
        this.verticalLines = verticalLines;
        this.tileSize = tileSize;

        // Initializing Tetrominoes
        tetrominoes = new Sprite[2];
        tetrominoes[0] = new ISprite(horizontalLines, 0);
        tetrominoes[1] = new ISprite(horizontalLines, 0);

        // Initializing PlacedTetrominoesList
        placedTetrominoesList = new ArrayList<Sprite>();
        currentTetromino = new ISprite(5, 0);

        // Initializing Game Loop
        timer = new Timer(200, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // CurrentTetromino Movement
                if (!checkBelowCollision(currentTetromino)) {
                    currentTetromino.moveAllTiles(0, 1);
                } else {
                    placedTetrominoesList.add(currentTetromino);

                    if (!checkBelowCollision(new Sprite(5, -2, getBackground()))) {
                        currentTetromino = new ISprite(5, 0);
                    }
                }

                repaint();
            }

        });

        timer.start();
    }

    public boolean checkSideCollision(Sprite t) {
        for (Sprite tetromino : placedTetrominoesList) {
            for (Tile tile : tetromino.getTileList()) {
                if (tile.getY() == t.getY() && tile.getX() == t.getX() + 1 || tile.getX() == t.getX() - 1) {
                    return true;
                }
            }
        }

        if (t.getX() >= horizontalLines - 1 || t.getX() <= 0) {
            return true;
        }

        return false;
    }

    public boolean checkBelowCollision(Sprite t) {
        for (Sprite tetromino : placedTetrominoesList) {
            for (Tile tile : tetromino.getTileList()) {
                if (tile.getX() == t.getX() && tile.getY() == t.getY() + 1) {
                    return true;
                }
            }
        }

        if (t.getY() >= verticalLines - 1) {
            return true;
        }

        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Drawing Grid
        for (int row = 0; row < horizontalLines; row++) {
            int x = (int) row * tileSize;
            g.drawLine(x, 0, x, getHeight());
        }

        for (int col = 0; col < verticalLines; col++) {
            int y = (int) col * tileSize;
            g.drawLine(0, y, getWidth(), y);
        }

        // Drawing Parts
        for (Sprite tetromino : placedTetrominoesList) {
            for (Tile tile : tetromino.getTileList()) {
                int x = tile.x * tileSize;
                int y = tile.y * tileSize;

                g.setColor(tetromino.getColor());
                g.fillRect(x, y, tileSize, tileSize);
            }
        }

        for (Tile tile : currentTetromino.getTileList()) {
            int x = tile.x * tileSize;
            int y = tile.y * tileSize;

            g.setColor(currentTetromino.getColor());
            g.fillRect(x, y, tileSize, tileSize);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!checkSideCollision(currentTetromino)) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    currentTetromino.moveAllTiles(-1, 0);
                    break;

                case KeyEvent.VK_LEFT:
                    currentTetromino.moveAllTiles(-1, 0);
                    break;

                case KeyEvent.VK_D:
                    currentTetromino.moveAllTiles(1, 0);
                    break;

                case KeyEvent.VK_RIGHT:
                    currentTetromino.moveAllTiles(1, 0);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
