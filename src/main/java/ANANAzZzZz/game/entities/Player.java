package ANANAzZzZz.game.entities;

public class Player {
    public static final int width;
    public static final int height;

    private final Point coordinate;

    static {
        width = 40;
        height = 10;
    }

    public Player(Point coordinate) {
        this.coordinate = coordinate;
    }

    public Point getCoordinate() {
        return new Point(coordinate);
    }

    public void move(int offset) {
        coordinate.x += offset;
    }
}
