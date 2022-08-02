package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Color;
import ANANAzZzZz.game.entities.Colors;

public class SimpleBrick extends Brick {
    public SimpleBrick(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean hit() {
        return true;
    }

    @Override
    public Color getColor() {
        return Colors.white;
    }
}