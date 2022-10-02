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

    public Point predictMove() {
        Point coordinateCopy = new Point(coordinate);

        coordinateCopy.x += moveDirection.x;
        coordinateCopy.y += moveDirection.y;

        return coordinateCopy;
    }

    public void changeMoveDirection(int x, int y) {
        this.moveDirection.x *= x;
        this.moveDirection.y *= y;
    }
}