import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisPanel extends JPanel implements KeyListener {
    int horizontalLines, verticalLines, tileSize;
    ArrayList<Tetromino> placedTetrominoesList;
    Tetromino currentTetromino;
    Random random;

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

        // Initializing PlacedTetrominoesList
        placedTetrominoesList = new ArrayList<Tetromino>();

        random = new Random();

        currentTetromino = getRandomTetromino();

        // Initializing Game Loop
        timer = new Timer(200, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // CurrentTetromino Movement
                if (!checkBelowCollision(currentTetromino)) {
                    currentTetromino.moveAllTiles(0, 1);
                } else {
                    placedTetrominoesList.add(currentTetromino);
                    currentTetromino = null;
                    currentTetromino = getRandomTetromino();
                }

                // Updating Visuals
                repaint();
            }

        });

        timer.start();
    }

    public Tetromino getRandomTetromino() {
        int i = random.nextInt(3);

        switch (i) {
            case 1:
                return new ITetromino(new Point(5, 0));
            
            case 2:
                return new LTetromino(new Point(5, 0));

            default:
                return new ITetromino(new Point(5, 0));
        }
    }

    public boolean checkLeftCollision(Tetromino tetromino) {
        for (Tile tile : tetromino.getTileList()) {
            for (Tetromino placedTetromino : placedTetrominoesList) {
                for (Tile placedTile : placedTetromino.getTileList()) {
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

            case KeyEvent.VK_R:
                if (!checkRightCollision(currentTetromino) & !checkLeftCollision(currentTetromino)
                        & !checkBelowCollision(currentTetromino)) {
                    currentTetromino.rotateClockwise();
                    repaint();
                }

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
