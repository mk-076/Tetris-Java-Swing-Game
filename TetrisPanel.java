import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisPanel extends JPanel implements KeyListener {
    int horizontalLines, verticalLines, tileSize;
    ArrayList<Tetromino> placedTetrominoesList;
    Tetromino[] tetrominoes;
    Tetromino currentTetromino;

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
        tetrominoes = new Tetromino[2];

        // Initializing PlacedTetrominoesList
        placedTetrominoesList = new ArrayList<Tetromino>();
        currentTetromino = new ITetromino(new Point(5, 0));

        // Initializing Game Loop
        timer = new Timer(200, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // CurrentTetromino Movement
                if (!checkBelowCollision(currentTetromino)) {
                    currentTetromino.moveAllTiles(0, 1);
                } else {
                    placedTetrominoesList.add(currentTetromino);

                    currentTetromino = new ITetromino(new Point(5, 0));
                }

                repaint();
            }

        });

        timer.start();
    }

    public boolean checkLeftCollision(Tetromino tetromino) {
        for (Tile tile : tetromino.getTileList()) {
            for (Tetromino placedTetromino : placedTetrominoesList) {
                for (Tile placedTile : placedTetromino.getTileList()) {
                    System.out.println(tile.getY() == placedTile.getY());
                    if (tile.getY() == placedTile.getY() && tile.getX() - 1 == placedTile.getX()) {
                        return true;
                    }
                }
            }

            if (tile.getX() - 1 < 0) {
                return true;
            }
        }

        return false;
    }

    public boolean checkRightCollision(Tetromino tetromino) {
        for (Tile tile : tetromino.getTileList()) {
            for (Tetromino placedTetromino : placedTetrominoesList) {
                for (Tile placedTile : placedTetromino.getTileList()) {
                    if (tile.getY() == placedTile.getY() && tile.getX() + 1 == placedTile.getX()) {
                        return true;
                    }
                }
            }

            if (tile.getX() + 1 >= horizontalLines) {
                return true;
            }
        }

        return false;
    }

    public boolean checkBelowCollision(Tetromino tetromino) {
        for (Tile tile : tetromino.getTileList()) {
            for (Tetromino placedTetromino : placedTetrominoesList) {
                for (Tile placedTile : placedTetromino.getTileList()) {
                    if (tile.getX() == placedTile.getX() && tile.getY() + 1 == placedTile.getY()) {
                        return true;
                    }
                }
            }

            if (tile.getY() >= verticalLines - 1) {
                return true;
            }
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
        for (Tetromino tetromino : placedTetrominoesList) {
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

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                if (!checkLeftCollision(currentTetromino)) {
                    currentTetromino.moveAllTiles(-1, 0);
                    repaint();
                }
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                if (!checkRightCollision(currentTetromino)) {
                    currentTetromino.moveAllTiles(1, 0);
                    repaint();
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
