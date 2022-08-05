package ANANAzZzZz.game.entities;

import ANANAzZzZz.game.entities.bricks.Brick;

import java.util.ArrayList;

public class GameState {
    public final int boardLength;
    public final Player player;
    public final ArrayList<Brick> bricks;


    public GameState(int boardLength, Player player, ArrayList<Brick> bricks) {
        this.boardLength = boardLength;
        this.player = player;
        this.bricks = bricks;
    }
}
