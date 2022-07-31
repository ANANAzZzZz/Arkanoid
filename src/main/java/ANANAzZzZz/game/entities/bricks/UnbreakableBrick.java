package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Color;

// TODO: 7/19/2022 add logical unbreakability
public class UnbreakableBrick extends ConstantColorBrick {
    public UnbreakableBrick(int x, int y) {
        super(x, y, new Color(60, 60, 60));
    }

    @Override
    public boolean hit() {
        return false;
    }
}