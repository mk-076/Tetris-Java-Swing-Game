import java.awt.Color;
import java.awt.Point;

public class ITetromino extends Tetromino {
    public ITetromino(Point pivot) {
        super(pivot, Color.CYAN);        

        initialize();
    }

    public void initialize() {
        offsets = new Point[4];
        offsets[0] = new Point(-1, 0);
        offsets[1] = new Point(0, 0);
        offsets[2] = new Point(1, 0);
        offsets[3] = new Point(2, 0);

        for (int i = 0; i < offsets.length; i++) {
            int x = (int) getPivot().getX();
            int y = (int) getPivot().getY();

            getTileList().add(new Tile(x + offsets[i].x, y + offsets[i].y));
        }
    }
}