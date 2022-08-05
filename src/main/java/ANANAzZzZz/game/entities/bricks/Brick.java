package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Color;
import ANANAzZzZz.game.entities.Point;

public abstract class Brick {
    public static final int width;
    public static final int height;

    private final Point coordinate;

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

    // returns true - if brick is destroyed
    public abstract boolean hit();

    public abstract Color getColor();
}