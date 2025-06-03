import java.awt.Color;
import java.awt.Point;

public class Tetromino extends Sprite {
    Point pivot;

    public Tetromino(Point pivot, Color color) {
        super(Color.CYAN);

        this.pivot = pivot;
    }

    public void moveAllTiles(int dx, int dy) {
        pivot.translate(dx, dy);
    }

    public Point getPivot() {
        return pivot;
    }
}