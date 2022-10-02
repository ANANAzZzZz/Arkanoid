package ANANAzZzZz.game.entities;

import ANANAzZzZz.game.entities.bricks.Brick;

public class Player extends Brick {
    private int width;

    public Player(int x, int y) {
        super(x, y);
        width = 40;
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
        return 2;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void decreaseWidth() {
        if (width < 20) {
            return;
        }
        width -= 10;
    }

    public void increaseWidth() {
        width += 10;
    }
}
