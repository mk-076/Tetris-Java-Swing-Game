import java.awt.Color;
import java.util.ArrayList;

public class Sprite extends Tile {
    ArrayList<Tile> tileList;
    Color color;

    public Sprite(int x, int y, Color color) {
        super(x, y);

        tileList = new ArrayList<Tile>();
        this.color = color;
    }

    public void moveAllTiles(int x, int y) {
        for (Tile tile : tileList) {
            tile.x += x;
            tile.y += y;
        }

        setPosition(tileList.get(0).getX(), tileList.get(0).getY());
    }

    public ArrayList<Tile> getTileList() {
        return tileList;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
