import java.awt.Color;
import java.awt.Point;

public class ITetromino extends Tetromino {
    public ITetromino(Point pivot) {
        super(pivot, Color.CYAN);

        initialize();
    }

    public void initialize() {
        int x = (int) getPivot().x;
        int y = (int) getPivot().y;

        getTileList().add(new Tile(x, y));
        getTileList().add(new Tile(x, y - 1));
        getTileList().add(new Tile(x, y - 2));
        getTileList().add(new Tile(x, y - 3));
    }
}