import java.awt.Color;
import java.awt.Point;

public class ITetromino extends Tetromino {
    public ITetromino(Point pivot) {
        super(pivot, Color.CYAN);

        offsets = new Point[4];
        offsets[0] = new Point(0, 0);
        offsets[1] = new Point(0, -1);
        offsets[2] = new Point(0, -2);
        offsets[3] = new Point(0, -3);

        initialize();
    }

    public void initialize() {
        int x = (int) getPivot().x;
        int y = (int) getPivot().y;

        getTileList().add(new Tile(x, y));
        getTileList().add(new Tile(x, y));
        getTileList().add(new Tile(x, y));
        getTileList().add(new Tile(x, y));
    }
}