package ANANAzZzZz.game.entities;

import ANANAzZzZz.game.entities.bricks.Brick;

public class Player extends Brick {

    public Player(int x, int y) {
        super(x, y);
    }

    public void move(int offset) {
        this.coordinate.x += offset;
    }

    @Override
    public boolean hit() {
        return false;
    }

    @Override
    public Color getColor() {
        return Colors.white;
    }

    @Override
    public int getHeight() {
        return 10;
    }

    @Override
    public int getWidth() {
        return 40;
    }
}
