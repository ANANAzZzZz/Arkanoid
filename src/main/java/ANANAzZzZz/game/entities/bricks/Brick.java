package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Color;
import ANANAzZzZz.game.entities.Point;

public abstract class Brick {
    public static final int width;
    public static final int height;

    protected final Point coordinate;

    static {
        width = 20;
        height = 10;
    }

    public Brick(int x, int y) {
        this.coordinate = new Point(x, y);
    }

    public Point getCoordinate() {
        return new Point(coordinate);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    // returns true - if brick is destroyed
    public abstract boolean hit();

    public abstract Color getColor();
}