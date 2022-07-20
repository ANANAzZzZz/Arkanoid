package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Color;

// TODO: 7/20/2022 make color field static
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
