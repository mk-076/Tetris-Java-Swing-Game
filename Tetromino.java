import java.awt.Color;
import java.awt.Point;

public class Tetromino extends Sprite {
    Point pivot;
    Point[] offsets;

    public Tetromino(Point pivot, Color color) {
        super(color);

        this.pivot = pivot;
    }

    public void moveAllTiles(int dx, int dy) {
        pivot.translate(dx, dy);

        for (int i = 0; i < tileList.size(); i++) {
            Tile tile = tileList.get(i);
            int x = (int) getPivot().getX();
            int y = (int) getPivot().getY();

            tile.setPosition(x + offsets[i].x, y + offsets[i].y);
        }
    }

    public void rotateClockwise() {
        for (int i = 0; i < tileList.size(); i++) {
            offsets[i].setLocation(offsets[i].getY(), offsets[i].getX());
        }
    }

    public Point getPivot() {
        return pivot;
    }

    public Point[] getOffsets() {
        return offsets;
    }
}