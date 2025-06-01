import java.awt.Color;
import java.util.ArrayList;

public class Sprite {
    ArrayList<Tile> tileList;
    Color color;

    public Sprite(Color color) {
        tileList = new ArrayList<Tile>();
        this.color = color;
    }

    public void moveAllTiles(int dx, int dy) {
        for (Tile tile : tileList) {
            int x = tile.getX();
            int y = tile.getY();

            tile.setPosition(x + dx, y + dy);
        }
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
