import java.awt.Color;
import java.awt.Point;

public class Tetromino extends Sprite {
    Point pivot;
    Point[] offsets;

    public Tetromino(Point pivot, Color color) {
        super(Color.CYAN);

        this.pivot = pivot;
    }

    public void moveAllTiles(int dx, int dy) {
        pivot.translate(dx, dy);

        for (int i = 0; i < tileList.size(); i++) {
            Tile tile = tileList.get(i);
            int x = (int) getPivot().x;
            int y = (int) getPivot().y;

            tile.setPosition(x + offsets[i].x, y + offsets[i].y);
        }
    }

    public Point getPivot() {
        return pivot;
    }
}