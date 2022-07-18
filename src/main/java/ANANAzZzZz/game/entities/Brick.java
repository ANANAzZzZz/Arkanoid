package ANANAzZzZz.game.entities;

public class Brick {
    private final Point coordinate;

    public Brick(int x, int y) {
        this.coordinate = new Point(x, y);
    }

    public Point getCoordinate() {
        return new Point(coordinate);
    }
}