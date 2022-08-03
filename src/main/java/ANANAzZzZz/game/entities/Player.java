package ANANAzZzZz.game.entities;

public class Player {
    private final Point coordinate;

    public Player(Point coordinate) {
        this.coordinate = coordinate;
    }

    public Point getCoordinate() {
        return new Point(coordinate);
    }
}
