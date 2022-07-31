package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Color;
import ANANAzZzZz.game.entities.Point;

public abstract class Brick {
    private final Point coordinate;

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