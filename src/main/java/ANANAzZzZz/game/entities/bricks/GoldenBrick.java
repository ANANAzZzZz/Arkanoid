package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Colors;

// TODO: 7/19/2022 make it give more points
public class GoldenBrick extends ConstantColorBrick {
    public GoldenBrick(int x, int y) {
        super(x, y, Colors.yellow);
    }

    @Override
    public boolean hit() {
        return true;
    }
}
