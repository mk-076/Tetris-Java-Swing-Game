import java.awt.Color;
import java.util.ArrayList;

public class Sprite {
    ArrayList<Tile> tileList;
    Color color;

    public Sprite(Color color) {
        tileList = new ArrayList<Tile>();
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Tile> getTileList() {
        return tileList;
    }

    public Color getColor() {
        return color;
    }
}
