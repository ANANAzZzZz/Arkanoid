package ANANAzZzZz.game.entities;

public class Ball {
    private final Point coordinate;
    private final Point moveDirection;

    public Ball(int x, int y, int moveDirectionX, int moveDirectionY) {
        this.coordinate = new Point(x, y);
        this.moveDirection = new Point(moveDirectionX, moveDirectionY);
    }

    public Point getCoordinate() {
        return new Point(coordinate);
    }

    public void move() {
        coordinate.x += moveDirection.x;
        coordinate.y += moveDirection.y;
    }
}