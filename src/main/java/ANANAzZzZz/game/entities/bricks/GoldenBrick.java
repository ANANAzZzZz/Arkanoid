package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Color;

// TODO: 7/19/2022 make it give more points
public class GoldenBrick extends ConstantColorBrick {
    public GoldenBrick(int x, int y) {
        super(x, y, new Color(255, 215, 0));
    }

    @Override
    public boolean hit() {
        return true;
    }
}
