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
