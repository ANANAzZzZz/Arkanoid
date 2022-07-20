package ANANAzZzZz.game.foundation;

import ANANAzZzZz.game.entities.GameState;
import ANANAzZzZz.game.entities.bricks.GoldenBrick;
import ANANAzZzZz.game.entities.bricks.SimpleBrick;
import ANANAzZzZz.game.entities.bricks.UnbreakableBrick;

public class LogicProcessor {
    public GameState initGameState() {
        GameState gameState = new GameState();

        for (int i = -9; i < 10; i++) {
            gameState.bricks.add(new UnbreakableBrick(i * 2, 11));
            gameState.bricks.add(new GoldenBrick(i * 2, 10));
            gameState.bricks.add(new SimpleBrick(i * 2, 9));
        }

        return gameState;
    }
}
