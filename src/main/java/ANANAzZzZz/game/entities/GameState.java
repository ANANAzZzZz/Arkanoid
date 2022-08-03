package ANANAzZzZz.game.entities;

import ANANAzZzZz.game.entities.bricks.Brick;

import java.util.ArrayList;

public class GameState {
    public final Player player;
    public final ArrayList<Brick> bricks;

    public GameState(Player player, ArrayList<Brick> bricks) {
        this.player = player;
        this.bricks = bricks;
    }
}
