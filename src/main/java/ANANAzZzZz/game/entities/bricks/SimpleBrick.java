package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Colors;

public class SimpleBrick extends ConstantColorBrick {
    public SimpleBrick(int x, int y) {
        super(x, y, Colors.white);
    }

    @Override
    public boolean hit() {
        return true;
    }
}