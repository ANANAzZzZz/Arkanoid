package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Color;

public abstract class ConstantColorBrick extends Brick {
    private final Color color;

    public ConstantColorBrick(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
