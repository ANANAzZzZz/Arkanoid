package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Color;
import ANANAzZzZz.game.entities.Colors;

public class UnbreakableBrick extends Brick {
    public UnbreakableBrick(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean hit() {
        return false;
    }

    @Override
    public Color getColor() {
        return Colors.gray;
    }
}