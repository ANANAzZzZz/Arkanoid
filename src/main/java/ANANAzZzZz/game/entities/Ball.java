package ANANAzZzZz.game.entities;

public class Ball {
    private final Point coordinate;

    public Ball(int x, int y) {
        this.coordinate = new Point(x, y);
    }

    public Point getCoordinate() {
        return new Point(coordinate);
    }
}