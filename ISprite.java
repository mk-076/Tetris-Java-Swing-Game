import java.awt.Color;

public class ISprite extends Sprite {
    public ISprite(int x, int y) {
        super(Color.CYAN);

        tileList.add(new Tile(x, y));
        tileList.add(new Tile(x, y - 1));
        tileList.add(new Tile(x, y - 2));
        tileList.add(new Tile(x, y - 3));
    }
}