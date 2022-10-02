package ANANAzZzZz.game.entities;

import ANANAzZzZz.game.entities.bricks.Brick;

import java.util.ArrayList;

public class GameState {
    public final int boardLength;
    public final Player player;
    public final ArrayList<Brick> bricks;
    public final Ball ball;

    public GameState(int boardLength, Player player, ArrayList<Brick> bricks, Ball ball) {
        this.boardLength = boardLength;
        this.player = player;
        this.bricks = bricks;
        this.ball = ball;
    }
}
