package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Color;
import ANANAzZzZz.game.entities.Colors;

public class MultiHitBrick extends Brick {
    private int health;

    public MultiHitBrick(int x, int y) {
        super(x, y);
        this.health = 3;
    }

    @Override
    public boolean hit() {
        health--;
        return health == 0;
    }

    @Override
    public Color getColor() {
        if (health == 3) {
            return Colors.lightPurple;
        } else if (health == 2) {
            return Colors.purple;
        } else {
            return Colors.darkPurple;
        }
    }
}