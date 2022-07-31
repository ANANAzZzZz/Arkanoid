package ANANAzZzZz.game.entities.bricks;

import ANANAzZzZz.game.entities.Color;

public class MultiHitBrick extends Brick {
    private final Color threeHealthColor;
    private final Color twoHealthColor;
    private final Color oneHealthColor;

    private int health;

    public MultiHitBrick(int x, int y) {
        super(x, y);

        this.threeHealthColor = new Color(255, 0, 255);
        this.twoHealthColor = new Color(160, 0, 160);
        this.oneHealthColor = new Color(60, 0, 60);

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
            return threeHealthColor;
        } else if (health == 2) {
            return twoHealthColor;
        } else {
            return oneHealthColor;
        }
    }
}