package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Color;
import ANANAzZzZz.game.entities.Colors;

// TODO: 7/19/2022 make it give more points
public class GoldenBrick extends Brick {
    public GoldenBrick(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean hit() {
        return true;
    }

    @Override
    public Color getColor() {
        return Colors.yellow;
    }
}
