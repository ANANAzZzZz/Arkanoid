package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Colors;

// TODO: 7/19/2022 add logical unbreakability
public class UnbreakableBrick extends ConstantColorBrick {
    public UnbreakableBrick(int x, int y) {
        super(x, y, Colors.gray);
    }

    @Override
    public boolean hit() {
        return false;
    }
}